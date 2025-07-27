package Storage;

import Models.Payment;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileHandlerTimes {
    private static final String TIMES_FILE = "times.txt";

    public void saveTimes(ArrayList<Payment> payments) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TIMES_FILE))) {
            for (Payment p : payments) {
                writer.printf("%s;%s;%.2f%n",
                        p.getMemberId(),
                        p.getPaymentDate(),
                        p.getAmount());
            }
        } catch (IOException e) {
            System.out.println("Could not save payments: " + e.getMessage());
        }
    }

    public ArrayList<Payment> loadTimes() {
        ArrayList<Payment> payments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TIMES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int memberId = Integer.parseInt(parts[0]);
                LocalDate date = LocalDate.parse(parts[1]);
                double amount = Double.parseDouble(parts[2]);
                payments.add(new Payment(date, amount, memberId));
            }
        } catch (IOException e) {
            System.out.println("Could not load payments: " + e.getMessage());
        }
        return payments;
    }
}