package LogicHandler;

import Models.Payment;
import Models.Swimmer;

import java.util.ArrayList;

public class EconHelper {

    public static double calculateTotalContingent(ArrayList<Swimmer> swimmers) {
        double total = 0;
        for (Swimmer swimmer : swimmers) {
            total += swimmer.getQuota();
        }
        return total;
    }

    public static ArrayList<Swimmer> getSwimmersWithDebt(ArrayList<Swimmer> swimmers) {
        ArrayList<Swimmer> inDebt = new ArrayList<>();
        for (Swimmer swimmer : swimmers) {
            if (swimmer.calMemberBalance() < 0) {
                inDebt.add(swimmer);
            }
        }
        return inDebt;
    }

    public static ArrayList<Swimmer> sortByBalanceWorstFirst(ArrayList<Swimmer> swimmers) {
        ArrayList<Swimmer> sorted = new ArrayList<>(swimmers);
        sorted.sort(new Swimmer.WorstEcon());
        return sorted;
    }

    public static Payment createPaymentForSwimmer(Swimmer swimmer, double amount) {
        return new Payment(swimmer.getMemberId(), java.time.LocalDate.now(), amount);
    }
}
