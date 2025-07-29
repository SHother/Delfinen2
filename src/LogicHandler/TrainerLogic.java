package LogicHandler;

import Models.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import static LogicHandler.InputChecker.*;

public class TrainerLogic {
    private static final Scanner scanner = new Scanner(System.in);

    public static void trainerMenu(ArrayList<CompetitionSwimmer> swimmers) {
        System.out.println("\nKonkurrence svømmer menu");
        System.out.println("1. Register træningstid"); //TODO test'
        System.out.println("2. Register konkurrencetid"); //TODO test
        System.out.println("3. Se junior top 5"); //TODO test
        System.out.println("4. Se senior top 5"); //TODO test
        System.out.println("9. Gå tilbage");
        System.out.print("Vælg en mulighed: ");
        Scanner scanner = new Scanner(System.in);
        int option = readValidInt(scanner);
        switch (option) {
            case 1, 2:
                registerTime(swimmers, option);
                break;
            case 3:
                printTop5times(getJuniorCompSwimmers(swimmers));
                break;
            case 4:
                printTop5times(getSeniorCompSwimmers(swimmers));
                break;
            case 9:
                break;
        }
    }

    private static ArrayList<CompetitionSwimmer> getJuniorCompSwimmers(ArrayList<CompetitionSwimmer> swimmers) {//TODO
        ArrayList<CompetitionSwimmer> youngGuns = new ArrayList<>();
        for (CompetitionSwimmer swimmer : swimmers) {
            if (swimmer.getAge() < 18){
                youngGuns.add(swimmer);
            }
        }
        return youngGuns;
    }


    private static ArrayList<CompetitionSwimmer> getSeniorCompSwimmers(ArrayList<CompetitionSwimmer> swimmers) { //TODO
        ArrayList<CompetitionSwimmer> oldGuys = new ArrayList<>();
        for (CompetitionSwimmer swimmer : swimmers) {
            if (swimmer.getAge() > 17){
                oldGuys.add(swimmer);
            }
        }
        return oldGuys;
    }

    private static void printTop5times(ArrayList<CompetitionSwimmer> CompSwimmers) {

        for (Discipline dis : Discipline.values()) {
            System.out.println(dis);
            ArrayList<TrainingTime> bestTimeInDis = new ArrayList<>();
            for (CompetitionSwimmer cs : CompSwimmers) {
                if (cs.hasDiscipline(dis)) {
                    bestTimeInDis.add(cs.fastestDisTime(dis));
                }
            }

            bestTimeInDis.sort(null);
            for(int i = 0; i < 4; i++) {
                System.out.println(bestTimeInDis.get(i).toString());
            }
        }
    }

    private static void registerTime(ArrayList<CompetitionSwimmer> swimmers, int badCoding) {
        System.out.println("Vælg medlem hvis tid skal registres");
        CompetitionSwimmer com = findComSwimmerFromId(swimmers);
        if (com != null) {
            TrainingTime tt = registerTrainingTime(com);
            if (tt != null) {
                if(badCoding == 2){
                    tt = registerCompetitionTime(com, tt);
                }
                com.addRecordTime(tt);
                System.out.println("Tid registreret");
            }
        }
    }
    public static TrainingTime registerTrainingTime(CompetitionSwimmer com) {
        System.out.print("Hvilken disiplin blev svømmet?");
        Discipline dis = readDiscipline(scanner);
        if(!com.getDisciplines().contains(dis)){
            System.out.println("Svømmeren er ikke registrert i denne disiplin.");
            System.out.print("Vil du tilføje denne disiplin til svømmerens liste? (Y/N): ");
            String choice = scanner.nextLine();
            if(choice.equalsIgnoreCase("y")){
                com.addDiscipline(dis);
                System.out.println("Disiplin registrert");
            } else {
                System.out.println("Tid ikke registrert");
                return null;
            }
        }
        LocalDate date = readDate(scanner);
        LocalTime time = readTime(scanner, dis);

        return new TrainingTime(com.getMemberId(), time, dis, date);

    }
    private static CompetitionTime registerCompetitionTime(CompetitionSwimmer swimmer, TrainingTime tt) {
        System.out.print("Skriv navn på stævne: ");
        String comp = scanner.nextLine();
        System.out.print("Skriv " + swimmer.getName() + "'s placering til " + comp + ": ");
        int placement = readValidInt(scanner);
        return new CompetitionTime(tt, comp, placement);
    }

    public static CompetitionSwimmer findComSwimmerFromId(ArrayList<CompetitionSwimmer> swimmers) {

        for (Swimmer swimmer : swimmers){
            System.out.println(swimmer.getMemberId() + ") " + swimmer.getName());
        }
        //TODO try catch
        System.out.print("Skriv Id'et på medlem: ");
        int option = readValidInt(scanner);
        for (CompetitionSwimmer swimmer : swimmers){
            if (swimmer.getMemberId() == option){
                return swimmer;
            }
        }
        System.out.println("Medlem " + option + " kan ikke findes!");
        return null;
    }
}
