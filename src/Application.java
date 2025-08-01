import Helpers.AdminHelper;
import Helpers.EconLogic;
import Helpers.TrainerHelper;
import Storage.LocalStorage;

import java.util.Scanner;

import static Helpers.InputChecker.readValidInt;

public class Application {

    private final Scanner scanner = new Scanner(System.in);
    private final LocalStorage storage = new LocalStorage();

    public void start() {
        boolean quit = false;
        while (!quit) {
            System.out.println("\nVelkommen til Svømmeklubben");
            System.out.println("1. Admin menu");
            System.out.println("2. Træner menu");
            System.out.println("3. Økonomi menu");
            System.out.println("9. Afslut");
            System.out.print("Vælg en mulighed: ");

            int choice = readValidInt(scanner);
            switch (choice) {
                case 1 -> new AdminHelper(storage, scanner).run();
                case 2 -> new TrainerHelper(storage, scanner).run();
                case 3 -> new EconLogic(storage, scanner).econMenu(); // TODO split EconLogic -> Menu & Helper
                case 9 -> quit = true;
                default -> System.out.println("Ugyldigt valg.");
            }
        }

        System.out.println("Lukker");
    }

}