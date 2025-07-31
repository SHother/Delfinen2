package LogicHandler;

import Models.Payment;
import Models.Swimmer;
import Storage.LocalStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static LogicHandler.InputChecker.*;

public class EconLogic {

    private final Scanner scanner = new Scanner(System.in);
    private LocalStorage localStorage;

    public EconLogic(LocalStorage localStorage) {
        this.localStorage = localStorage;
    }

    public void econMenu() {
        boolean quit = false;
        do {
            System.out.println("\nØkonomi");
            System.out.println("1. Se medlemers balance");
            System.out.println("2. Se medlemer i restance");
            System.out.println("3. Se medlems betalinger (deaktiveret)"); //TODO Nope
            System.out.println("4. Indberet betaling");
            System.out.println("5. Kontigent i alt");
            System.out.println("9. Gå tilbage");
            System.out.print("Vælg en mulighed: ");
            int option = readValidInt(scanner);
            switch (option) {
                case 1-> membersBalancePrint();
                case 2-> membersInMinus();
                case 4-> promptNewPayment();
                case 5-> totalContingent();
                case 9-> quit = true;
                default-> System.out.println(option + " er ikke en mulighed. Vælg mellem:");
            }
        } while (!quit);
    }

    private void promptNewPayment() {
        System.out.println("Vælg medlem som har betalt");

        Swimmer swimmer = findMemberFromId(scanner, localStorage.getAllSwimmers());
        if (swimmer == null) return;

        LocalDate date = readDate(scanner);
        System.out.print("Indtast beløb: ");
        double amount = readValidDouble(scanner);
        Payment pay = new Payment (swimmer.getMemberId(), date, amount);
        localStorage.addPayment(pay, swimmer);
    }

    private void membersBalancePrint() {
        int i = 1;
        localStorage.getAllSwimmers().sort(new Swimmer.WorstEcon()); //TODO
        System.out.printf("%-3s %-20s %10s%n", "Id", "Navn", "Saldo");
        for (Swimmer swimmer : localStorage.getAllSwimmers()) {
            System.out.printf("%-3d %-20s %10.2f%n", swimmer.getMemberId(), swimmer.getName(), swimmer.calMemberBalance());
        }
    }

    private void membersInMinus() {
        double balance;
        System.out.printf("%-3s %-20s %10s%n", "Id", "Navn", "Saldo");
        for (Swimmer swimmer : localStorage.getAllSwimmers()) {
            balance = swimmer.calMemberBalance();
            if (balance < 0) {
                System.out.printf("%-3d %-20s %10.2f%n", swimmer.getMemberId(), swimmer.getName(), swimmer.calMemberBalance());
            }
        }
    }
    private void totalContingent() {
        double total = 0;
        for (Swimmer swimmer : localStorage.getAllSwimmers()) {
            total += swimmer.getQuota();
        }
        System.out.println(total);
    }

}
