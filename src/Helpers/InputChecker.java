package Helpers;

import Models.Discipline;
import Models.Swimmer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class InputChecker {

    public static LocalDate readDate(Scanner scanner) {

        String input;
        do {
            System.out.print("Dato (dd-mm-yyyy): ");
            input = scanner.nextLine();
        }
        while (!isValidDate(input));
        return LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    //checker først for regex efterfulgt af parse til Date
    public static boolean isValidDate(String input) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19\\d{2}|20\\d{2})$";
        if (!input.matches(regex)) return false;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalTime readTime(Scanner scanner) {
        String input;
        do {
            System.out.print("Tid (mm:ss): ");
            input = scanner.nextLine();
        }
        while (!isValidTimeFormat(input));
        return LocalTime.parse("00:" + input, DateTimeFormatter.ofPattern("HH:mm:ss"));

    }
    // fungere på samme måde som isValidDate
    public static boolean isValidTimeFormat(String input) {
        String regex = "^([0-5][0-9]):([0-5][0-9])$";
        if (!input.matches(regex)) return false;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime.parse("00:" + input, formatter); //LocalTime SKAL åbenbart bruge timer med i formatted...
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    //TODO out of bounce
    public static Discipline readDiscipline(Scanner scanner){
        int i = 1;
        System.out.println();
        for (Discipline dis : Discipline.values()) {
            System.out.println(i + ") " + dis);
            i++;
        }
        System.out.print("Vælg relevant id: ");
        int input = readValidInt(scanner);
        if (input < Discipline.values().length && input > 0) return Discipline.values()[input-1];
        else return null;
    }

    public static Discipline readDiscipline(Scanner scanner, Set<Discipline> disciplines) {
        int i = 1;
        System.out.println();
        for (Discipline dis : Discipline.values()) {
            if (disciplines.contains(dis))
                System.out.println(i + ") " + dis);
            i++;
        }
        System.out.print("Vælg relevant id (input bliver ikke valideret): ");
        return Discipline.values()[readValidInt(scanner)-1];
    }

    //if no int is found, prompt the user to give a valid int
    public static int readValidInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Inputtet var ikke et heltal, prøv igen!");
            scanner.nextLine();
        }
        int returnVal = scanner.nextInt();
        scanner.nextLine();
        return returnVal;
    }

    public static double readValidDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Inputtet kan ikke læsses som et tal, prøv igen!");
            scanner.nextLine();
        }
        double returnVal = scanner.nextDouble();
        scanner.nextLine();
        return returnVal;
    }

    public static Swimmer findMemberFromId(Scanner scanner, ArrayList<Swimmer> swimmers) {
        System.out.println();
        System.out.printf("%-3s %3s%n", "Id", "Navn");
        for (Swimmer swimmer : swimmers){
            System.out.printf("%-3d %3s%n", swimmer.getMemberId(), swimmer.getName());
        }
        System.out.print("Skriv Id'et på medlem: ");
        int option = readValidInt(scanner);
        for (Swimmer swimmer : swimmers){
            if (swimmer.getMemberId() == option){
                return swimmer;
            }
        }
        System.out.println("Medlem " + option + " kan ikke findes!");
        return null;
    }
}
