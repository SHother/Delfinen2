package LogicHandler;

import Models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import static LogicHandler.InputChecker.readValidInt;

public class AdminLogic {
    public static void mainMenu(ArrayList<Swimmer> swimmers, ArrayList<CompetitionSwimmer> compSwimmers, ArrayList<Trainer> trainers){
        boolean quit = false;
        do {
            System.out.println("\nDelfinen");
            System.out.println("1. Opret nyt medlem");
            System.out.println("2. Opret ny træner"); //TODO
            System.out.println("3. Skift medlems medlemsstatus"); //TODO Nope
            System.out.println("5. Gå til økonomi menuen");
            System.out.println("6. Gå til træner menuen");

            System.out.println("8. Log ud");
            System.out.print("Vælg en mulighed: ");
            Scanner scanner = new Scanner(System.in);

            int option = readValidInt(scanner);
            switch (option) {
                case 1:
                    promptNewMember(scanner, trainers);
                    break;
                case 5:
                    EconLogic.econMenu(swimmers);
                    break;
                case 6:
                    TrainerLogic.trainerMenu(compSwimmers);
                    break;
                case 8:
                    quit = true;
                    break;
                default:
                    System.out.println(option + " er ikke en mulighed. Vælg mellem:");
                    break;
            }
        } while (!quit);
    }

    public static Swimmer promptNewMember(Scanner scanner, ArrayList<Trainer> trainers){
        Swimmer newSwimmer = null;
        System.out.println("Opret nyt medlem");
        System.out.println("Indtast oplysninger");
        System.out.print("Navn: ");
        String name = scanner.nextLine();
        System.out.print("Fødselsdag (YYYY-MM-DD): ");
        LocalDate birthday = LocalDate.parse(scanner.nextLine()); //TODO: valider fødselsdag
        boolean membership;
        do{
            System.out.print("Aktivt medlem? Y/N: ");
            String answer = scanner.nextLine();
            if (answer.equals("Y")){
                membership = true;
                break;
            } else if (answer.equals("N")){
                membership = false;
                break;
            }
            System.out.println("Svar ikke godkendt!");
        } while (true);
        boolean competition;
        do{
            System.out.print("Medlem Motionist (1) eller Konkurrencesvømmer (2): ");
            String answer = scanner.nextLine();
            if (answer.equals("1")){
                competition = false;
                break;
            } else if (answer.equals("2")){
                competition = true;
                break;
            }
            System.out.println("Svar ikke godkendt!");
        } while (true);

        if (!competition){
            newSwimmer = new Swimmer(name, birthday, membership);
            System.out.println(newSwimmer);
            System.out.print("Er medlem korrekt og skal oprettes? Y/N: ");
            String answer = scanner.nextLine();
            if (answer.equals("Y")){
                System.out.println("Medlem oprettet med kontigent:"); //TODO print forventet kontigent
                return newSwimmer;

            }
        }
        else {

            //TODO trainer picking is BS
            System.out.println("Vælg træner for svømmeren: ");
            int i = 1;
            for(Trainer trainer: trainers){
                System.out.println(i + ") " + trainer.getName());
            }
            System.out.print("Indtast træner id: ");
            int id = readValidInt(scanner);
            Trainer trainer = trainers.get(id - 1);

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
                        break;
                }
            }
            CompetitionSwimmer newMember = new CompetitionSwimmer(name, birthday, membership, trainer.getMemberId(), disciplines);
            System.out.println(newMember);
            return newSwimmer;
        }
        return null;
    }
}
