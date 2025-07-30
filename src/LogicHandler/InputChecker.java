package LogicHandler;

import Models.Discipline;
import Models.Swimmer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class InputChecker {
    //TODO Throw custom exception
    public static LocalDate readDate(Scanner scanner) {
        System.out.print("Dato (yyyy-mm-dd): ");
        return LocalDate.parse(scanner.nextLine());
    }

    //TODO Throw custom exception
    public static LocalTime readTime(Scanner scanner) {
        return LocalTime.parse(scanner.nextLine());
    }

    //TODO out of bounce
    public static Discipline readDiscipline(Scanner scanner){
        int i = 1;
        for (Discipline dis : Discipline.values()) {
            System.out.println(i + ") " + dis);
            i++;
        }
        System.out.print("Vælg relevant id: ");
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

    public static Swimmer findMemberFromId(Scanner scanner, ArrayList<Swimmer> swimmers) {
        for (Swimmer swimmer : swimmers){
            System.out.println(swimmer.getMemberId() + ") " + swimmer.getName());
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
