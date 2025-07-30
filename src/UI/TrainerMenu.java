package UI;

import LogicHandler.TrainerHelper;
import Models.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import static LogicHandler.InputChecker.*;

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
    public void printTop5times(ArrayList<TrainingTime>[] top5inAllDis) {
        int i = 0;
        for(ArrayList<TrainingTime> dis : top5inAllDis) {
            System.out.println("Bedste svømmere i " + Discipline.values()[i]);
            for(TrainingTime t : dis) {
                String name = TrainerHelper.findComSwimmerFromId(t.getMemberID()).getName();
                System.out.println(name + " " + t.getTime() + " " + t.getTrainingDate() + t.getDiscipline()); //TODO fjern Dis efter tests
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

    public Discipline askDiscipline() {
        System.out.print("Hvilken disiplin blev svømmet?");
        return readDiscipline(scanner);
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
