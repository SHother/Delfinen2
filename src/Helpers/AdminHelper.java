package Helpers;

import Models.CompetitionSwimmer;
import Models.Discipline;
import Models.Swimmer;
import Storage.LocalStorage;
import UI.AdminMenu;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;

public class AdminHelper {

    private final LocalStorage localStorage;
    private final AdminMenu adminMenu;

    public AdminHelper(LocalStorage localStorage, Scanner scanner) {
        this.localStorage = localStorage;
        this.adminMenu = new AdminMenu(scanner);
    }
    public void run() {
        boolean quit = false;
        while (!quit) {
            int choice = adminMenu.showMenu();
            switch (choice) {
                case 1 -> localStorage.addSwimmer(createNewSwimmer());
                case 9 -> quit = true;
                default -> System.out.println("Ugyldigt valg.");
            }
        }
    }

    private Swimmer createNewSwimmer() {
        AdminMenu.promptNewSwimmer();
        String name = AdminMenu.readName();
        LocalDate birthday = AdminMenu.readBirthday();
        boolean membership = AdminMenu.askMembership();

        boolean isCompSwimmer = AdminMenu.isCompSwimmer();

        if (!isCompSwimmer) {
            Swimmer swimmer = new Swimmer(name, birthday, membership);
            if (AdminMenu.creationConformation(swimmer))
                return swimmer;

        } else {

            int trainerId = AdminMenu.chooseTrainer(localStorage.getTrainers());
            Set<Discipline> disciplines = AdminMenu.chooseDisciplines();
            CompetitionSwimmer swimmer = new CompetitionSwimmer(name, birthday, membership, trainerId, disciplines);

            if (AdminMenu.creationConformation(swimmer))
                return swimmer;
        }
        return null;
    }
}
