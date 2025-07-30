//package UI;
//
//import Models.Payment;
//import Models.Swimmer;
//import Storage.LocalStorage;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import static LogicHandler.EconHelper.*;
//import static LogicHandler.InputChecker.*;
//
//public class EconMenu implements MenuI{
//
//    private final Scanner scanner = new Scanner(System.in);
//    private final LocalStorage localStorage;
//
//    public EconMenu(LocalStorage localStorage) {
//        this.localStorage = localStorage;
//    }
//
//    public int showMenu() {
//        boolean quit = false;
//        do {
//            System.out.println("\nØkonomi");
//            System.out.println("1. Se medlemmernes balance");
//            System.out.println("2. Se medlemmer i restance");
//            System.out.println("3. Se medlems betalinger (deaktiveret)");
//            System.out.println("4. Indberet betaling");
//            System.out.println("5. Kontingent i alt");
//            System.out.println("9. Gå tilbage");
//            System.out.print("Vælg en mulighed: ");
//            int option = readValidInt(scanner);
//            switch (option) {
//                case 1 -> printMemberBalances();
//                case 2 -> printMembersInDebt();
//                case 4 -> promptNewPayment();
//                case 5 -> printTotalContingent();
//                case 9 -> quit = true;
//                default -> System.out.println(option + " er ikke en mulighed.");
//            }
//        } while (!quit);
//        return 0;
//    }
//
//    private void printMemberBalances() {
//        ArrayList<Swimmer> swimmers = sortByBalanceWorstFirst(localStorage.getAllSwimmers());
//        int i = 1;
//        for (Swimmer swimmer : swimmers) {
//            System.out.printf("%d)\t%s\tbalance: %.2f%n", i++, swimmer.getName(), swimmer.calMemberBalance());
//        }
//    }
//
//    private void printMembersInDebt() {
//        for (Swimmer swimmer : getSwimmersWithDebt(localStorage.getAllSwimmers())) {
//            System.out.printf("%s\tbalance: %.2f%n", swimmer.getName(), swimmer.calMemberBalance());
//        }
//    }
//
//    private void printTotalContingent() {
//        double total = calculateTotalContingent(localStorage.getAllSwimmers());
//        System.out.printf("Totalt kontingent: %.2f kr.%n", total);
//    }
//
//    private void promptNewPayment() {
//        System.out.println("Vælg medlem som har betalt:");
//        Swimmer swimmer = findMemberFromId(scanner, localStorage.getAllSwimmers());
//
//        if (swimmer != null) {
//            LocalDate date = readDate(scanner);
//            System.out.print("Indtast beløb: ");
//            double amount = readValidDouble(scanner);
//            Payment pay = new Payment(swimmer.getMemberId(), date, amount);
//            localStorage.addPayment(pay, swimmer);
//            System.out.println("Betaling indberettet.");
//        }
//    }
//}
