package UI;

import Models.Discipline;
import Models.Swimmer;
import Models.Trainer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import static LogicHandler.InputChecker.readValidInt;

public class AdminMenu implements MenuI {

    private static Scanner scanner = null;

    public AdminMenu(Scanner scanner) {
        AdminMenu.scanner = scanner;
    }

    public int showMenu() {
        System.out.println("\nAdmin menu");
        System.out.println("1. Opret nyt medlem");
        System.out.println("2. Opret ny træner (deaktiveret)");
        System.out.println("3. Skift medlems medlemsstatus (deaktiveret)");
        System.out.println("9. Tilbage");
        System.out.print("Vælg: ");
        return readValidInt(scanner);
    }

    public static void promptNewSwimmer() {
        System.out.println("Opret nyt medlem");
        System.out.println("Indtast oplysninger");
    }

    public static String readName() {
        System.out.print("Navn: ");
        return scanner.nextLine();
    }

    public static LocalDate readBirthday() {
        System.out.print("Fødselsdato (yyyy-MM-dd): ");
        return LocalDate.parse(scanner.nextLine()); // Tilføj evt. validering
    }

    public static boolean askMembership() {
        System.out.print("Aktivt medlem? (y/n): ");
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("y");
    }

    public static boolean isCompSwimmer() {
        System.out.print("Er medlem konkurrencesvømmer? (y/n): ");
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("y");
    }

    public static int chooseTrainer(ArrayList<Trainer> trainers) { //only returns an int with no checks at all: A+
        System.out.println("Vælg trænerens ID:");
        for (Trainer trainer : trainers) {
            System.out.println("ID: "+ trainer.getMemberId() + ") " + trainer.getName());
        }
        return readValidInt(scanner);
    }

    public static Set<Discipline> chooseDisciplines() {
        System.out.println("Vælg svømmerens disipliner:");
        System.out.println("1. Butterfly");
        System.out.println("2. Crawl");
        System.out.println("3. Back");
        System.out.println("4. Breast");
        System.out.print("Indtast de relevante ID'er: ");
        String answer = scanner.nextLine();
        String[] splitted = answer.split("[-+*/=, ]");
        Set<Discipline> disciplines = new java.util.HashSet<>(Set.of());
        for (String split: splitted) {
            switch(split){
                case "1", "Butterfly":
                    disciplines.add(Discipline.BUTTERFLY);
                    break;
                case "2", "Crawl":
                    disciplines.add(Discipline.CRAWL);
                    break;
                case "3", "Back":
                    disciplines.add(Discipline.BACK);
                    break;
                case "4", "Breast":
                    disciplines.add(Discipline.BREAST);
                    break;
                default:
                    System.out.println("Ingen disipliner valgt");
                    return null;
            }
        }
        return disciplines;
    }
    public static boolean creationConformation(Swimmer swimmer) {
        return true;
    }

}