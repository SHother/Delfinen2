package LogicHandler;

import Models.Payment;
import Models.Swimmer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static LogicHandler.InputChecker.*;

public class EconLogic {

    private static final Scanner scanner = new Scanner(System.in);

    public static void econMenu(ArrayList<Swimmer> swimmers){
        boolean quit = false;
        do {
            System.out.println("\nØkonomi");
            System.out.println("1. Se medlemers balance");
            System.out.println("2. Se medlemer i restance");
            System.out.println("3. Se medlems betalinger"); //TODO Nope
            System.out.println("4. Indberet betaling");
            System.out.println("5. Kontigent i alt");
            System.out.println("9. Gå tilbage");
            System.out.print("Vælg en mulighed: ");
            Scanner scanner = new Scanner(System.in);
            int option = readValidInt(scanner);
            switch (option) {
                case 1:
                    membersBalancePrint(swimmers);
                    break;
                case 2:
                    membersInMinus(swimmers);
                    break;
                case 3:
                    promptMemberPayments();
                    break;
                case 4:
                    promptNewPayment(swimmers);
                    break;
                case 5:
                    totalContingent(swimmers);
                    break;
                case 9:
                    quit = true;
                    break;
                default:
                    System.out.println(option + " er ikke en mulighed. Vælg mellem:");
                    break;
            }
        } while (!quit);
    }
    //TODO see a members payments
    private static void promptMemberPayments() {

    }
    private static void promptNewPayment(ArrayList<Swimmer> swimmers) {
        System.out.println("Vælg medlem som har betalt");

        Swimmer m = findMemberFromId(scanner, swimmers);
        if (m != null) {
            System.out.print("Dato (yyyy-mm-dd): ");
            LocalDate date = readDate(scanner);
            System.out.print("Beløb: ");
            double amount = Double.parseDouble(scanner.nextLine());
            createPayment(m, date, amount);
        }
    }
    public static void createPayment(Swimmer swimmer, LocalDate date, double amount){
        swimmer.createPayment(date, amount);
    }
    private static void membersBalancePrint(ArrayList<Swimmer> swimmers) {
        int i = 1;
        swimmers.sort(new Swimmer.WorstEcon());
        for (Swimmer swimmer : swimmers) {
            System.out.println(i + ")\t" + swimmer.getName() + "\tbalance: " + swimmer.calMemberBalance());
            i++;
        }
    }
    //TODO
    private static void membersInMinus(ArrayList<Swimmer> swimmers) {
        double balance;
        for (Swimmer swimmer : swimmers) {
            balance = swimmer.calMemberBalance();
            if (balance < 0) {
                System.out.println(swimmer.getName() + "\tbalance: " + swimmer.calMemberBalance());
            }
        }
    }
    private static void totalContingent(ArrayList<Swimmer> swimmers) {
        double total = 0;
        for (Swimmer swimmer : swimmers) {
            total += swimmer.getQuota();
        }
        System.out.println(total);
    }

}
