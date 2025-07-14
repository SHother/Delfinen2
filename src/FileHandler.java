import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileHandler {

    private static final String MEMBER_FILE = "members.txt";
    private static final String PAYMENT_FILE = "payments.txt";

    public void saveMembers(ArrayList<Member> members) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MEMBER_FILE))) {
            for (Member m : members) {
                writer.printf("%s;%s;%b;%s;%n",
                        m.getName(),
                        m.getBirthday(),
                        m.isMembership(),
                        m.getJoinedDate());
            }
        } catch (IOException e) {
            System.out.println("Could not save members: " + e.getMessage());
        }
    }

    public void savePayments(ArrayList<Payment> payments) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PAYMENT_FILE))) {
            for (Payment p : payments) {
                writer.printf("%s;%s;%.2f%n",
                        p.getMemberName(),
                        p.getPaymentDate(),
                        p.getAmount());
            }
        } catch (IOException e) {
            System.out.println("Could not save payments: " + e.getMessage());
        }
    }

    public ArrayList<Member> loadMembers() {
        ArrayList<Member> members = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MEMBER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String name = parts[0];
                LocalDate birthday = LocalDate.parse(parts[1]);
                boolean membership = Boolean.parseBoolean(parts[2]);
                LocalDate joinedDate = LocalDate.parse(parts[3]);

                members.add(new Member(name, birthday, membership, joinedDate));
            }
        } catch (IOException e) {
            System.out.println("Could not load members: " + e.getMessage());
        }
        return members;
    }

    public ArrayList<Payment> loadPayments() {
        ArrayList<Payment> payments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PAYMENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String memberName = parts[0];
                LocalDate date = LocalDate.parse(parts[1]);
                double amount = Double.parseDouble(parts[2]);
                payments.add(new Payment(date, amount, memberName));
            }
        } catch (IOException e) {
            System.out.println("Could not load payments: " + e.getMessage());
        }
        return payments;
    }
}