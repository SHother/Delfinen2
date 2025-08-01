package UI;

import Helpers.TrainerHelper;
import Models.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import static Helpers.InputChecker.*;

public class TrainerMenu implements MenuI{
    private static Scanner scanner = null;

    public TrainerMenu(Scanner scanner) {
        TrainerMenu.scanner = scanner;
    }

    @Override
    public int showMenu() {
        System.out.println("\nKonkurrence svømmer menu");
        System.out.println("1. Register træningstid");
        System.out.println("2. Register konkurrencetid");
        System.out.println("3. Se junior top 5");
        System.out.println("4. Se senior top 5");
        System.out.println("9. Gå tilbage");
        System.out.print("Vælg en mulighed: ");
        return readValidInt(scanner);
    }

    //kom aldrig rigtig frem til en løsning som virker fornuftig
    //måske nu?
    public void printTop5times(ArrayList<TrainingTime>[] top5inAllDis, String ageGroup) {
        int i = 0;
        for(ArrayList<TrainingTime> dis : top5inAllDis) {
            System.out.println();
            System.out.println("Bedste " +ageGroup+ " svømmere i " + Discipline.values()[i]);
            i++;
            int j = 0;
            for(TrainingTime t : dis) {
                String name = TrainerHelper.findComSwimmerFromId(t.getMemberID()).getName();
                String time = t.getTime().format(DateTimeFormatter.ofPattern("mm:ss"));
                String date = t.getTrainingDate().format(DateTimeFormatter.ofPattern("MMM yyyy"));
                System.out.printf("%-10s %10s %10s%n", name, time, date);
                j++;
                if (j == 5) break;
            }
        }
    }
    public int readSwimmerId(ArrayList<CompetitionSwimmer> swimmers) {
        System.out.println("Vælg medlem hvis tid skal registres");
        for (Swimmer swimmer : swimmers){
            System.out.println(swimmer.getMemberId() + ") " + swimmer.getName());
        }
        System.out.print("Skriv Id'et på medlem: ");
        return readValidInt(scanner);
    }

    public Discipline askDiscipline(Set<Discipline> disciplines) {
        System.out.print("Hvilken disiplin blev svømmet?");
        return readDiscipline(scanner, disciplines);
    }

    public boolean askToAddDiscipline(CompetitionSwimmer swimmer, Discipline dis){
        System.out.println(swimmer.getName() + " er ikke registrert  " + dis);
        System.out.print("Vil du tilføje "+dis+" til "+swimmer.getName()+" liste? (Y/N): ");
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("y");
    }

    public LocalDate readTrainingDate() {
        System.out.println("Indtast dato for tidssætningen");
        return readDate(scanner);
    }
    public LocalTime readTrainingTime() {
        System.out.println("Indtast tiden der er blev sat");
        return readTime(scanner);
    }
    public String readCup() {
        System.out.print("Skriv navnet på stævnet: ");
        return scanner.nextLine();
    }
    public int readPlacement(){
        System.out.print("Skriv placering til stævnet: ");
        return readValidInt(scanner);
    }
}
