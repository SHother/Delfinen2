package LogicHandler;

import Models.*;
import Storage.LocalStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import static LogicHandler.InputChecker.readValidInt;

public class AdminLogic {

    public static void mainMenu(LocalStorage storage){
        boolean quit = false;
        do {
            System.out.println("\nDelfinen");
            System.out.println("1. Opret nyt medlem");
            System.out.println("2. Opret ny træner");
            System.out.println("3. Skift medlems medlemsstatus (deaktiveret)"); //TODO - Nope, buy the DLC
            System.out.println("5. Gå til økonomi menuen");
            System.out.println("6. Gå til træner menuen");

            System.out.println("9. Log ud");
            System.out.print("Vælg en mulighed: ");
            Scanner scanner = new Scanner(System.in);

            int option = readValidInt(scanner);
            switch (option) {
                case 1:
                    Swimmer swimmer = promptNewMember(scanner, storage.getTrainers());
                    storage.addSwimmer(swimmer);
                    break;
                case 5:
                    EconLogic.econMenu(storage.getAllSwimmers(), storage);
                    break;
                case 6:
                    TrainerLogic.trainerMenu(storage.getCompetitionSwimmers());
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

    private static Swimmer promptNewMember(Scanner scanner, ArrayList<Trainer> trainers){
        Swimmer newSwimmer;
        System.out.println("Opret nyt medlem");
        System.out.println("Indtast oplysninger");
        System.out.print("Navn: ");
        String name = scanner.nextLine(); //TODO check for bs tejn ,;
        System.out.println("Med medlems fødselsdag");
        LocalDate birthday;
        try{
            birthday = LocalDate.parse(scanner.nextLine());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

        boolean membership;
        do{
            System.out.print("Aktivt medlem? Y/N: ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("Y")){
                membership = true;
                break;
            } else if (answer.equalsIgnoreCase("N")){
                membership = false;
                break;
            }
            System.out.println("Svar ikke godkendt!");
        } while (true);
        boolean competition;
        do{
            System.out.print("Skal det nye medlem tilmeldes som Motionist (1) eller Konkurrencesvømmer (2): ");
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
            if (answer.equalsIgnoreCase("Y")){
                return newSwimmer;
            } else {
                return null;
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
            Trainer trainer;
            try {
                trainer = trainers.get(id - 1);}
            catch (IndexOutOfBoundsException e){
                System.out.println(e.getMessage());
                return null;
            }

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
            return new CompetitionSwimmer(name, birthday, membership, trainer.getMemberId(), disciplines);
        }
    }
}
