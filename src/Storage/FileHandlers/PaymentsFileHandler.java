package Storage.FileHandlers;

import Models.Payment;
import Models.Swimmer;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PaymentsFileHandler {
    private static final String PAYMENT_FILE = "payments.txt";

    //load all payments
    public ArrayList<Payment> loadPayments() {
        ArrayList<Payment> payments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PAYMENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int memberId = Integer.parseInt(parts[0]);
                LocalDate date = LocalDate.parse(parts[1]);
                double amount = Double.parseDouble(parts[2]);
                payments.add(new Payment(memberId, date, amount));
            }
        } catch (IOException e) {
            System.out.println("Could not load payments: " + e.getMessage());
        }
        return payments;
    }

    //Saves a single payment to the file
    public void addPayment(Payment payment) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PAYMENT_FILE, true))) {
            writer.printf("%s;%s;%.2f%n",
                    payment.getMemberId(),
                    payment.getPaymentDate(),
                    payment.getAmount());
        } catch (IOException e) {
            System.out.println("Could not append chill swimmer: " + e.getMessage());
        }
    }

    //overwrites the file with new data from the system
    public void savePayments(ArrayList<Payment> payments) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PAYMENT_FILE))) {
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
}
