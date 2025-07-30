package LogicHandler;

import Models.CompetitionSwimmer;
import Models.CompetitionTime;
import Models.Discipline;
import Models.TrainingTime;
import Storage.LocalStorage;
import UI.TrainerMenu;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrainerHelper {
    private static LocalStorage localStorage;
    private static TrainerMenu trainerMenu;

    public TrainerHelper(LocalStorage localStorage, Scanner scanner) {
        TrainerHelper.localStorage = localStorage;
        TrainerHelper.trainerMenu = new TrainerMenu(scanner);
    }
    public void run(){
        boolean quit = false;
        while (!quit) {
            int choice = trainerMenu.showMenu();
            switch (choice) {
                case 1, 2:
                    registerTime(choice);
                    break;
                case 3:
                    ArrayList<TrainingTime>[] top5inAllDis = getTop5times(getJuniorCompSwimmers());
                    trainerMenu.printTop5times(top5inAllDis);
                    break;
                case 4:
                    ArrayList<TrainingTime>[] top5inAllDiss = getTop5times(getSeniorCompSwimmers());
                    trainerMenu.printTop5times(top5inAllDiss);
                    break;
                case 9:
                    break;
            }
        }
    }
    private TrainingTime registerTime(int option){
        int swimmerId = trainerMenu.readSwimmerId(localStorage.getCompetitionSwimmers());

        CompetitionSwimmer swimmer = findComSwimmerFromId(swimmerId);

        if (swimmer == null) return null;

        Discipline dis = trainerMenu.askDiscipline();

        if (dis == null) return null;
        if (!swimmer.hasDiscipline(dis))
            if (trainerMenu.askToAddDiscipline(swimmer, dis))
                localStorage.addDisciplineToSwimmer(swimmer, dis);

        LocalDate date = trainerMenu.readTrainingDate();
        LocalTime time = trainerMenu.readTrainingTime();

        TrainingTime tt = new TrainingTime(swimmerId, time, dis, date);
        if (option == 2) {
            String cupName = trainerMenu.readCup();
            int placement = trainerMenu.readPlacement();
            tt = new CompetitionTime(tt, cupName,placement);
        }
        localStorage.addTimeToSwimmer(swimmer, tt);
        return tt;
    }

    private ArrayList<TrainingTime>[] getTop5times(ArrayList<CompetitionSwimmer> CompSwimmers){
        ArrayList<TrainingTime>[] top5inAllDis = new ArrayList[Discipline.values().length]; // creates an array of 4 arraylists
        int i = 0;
        for (Discipline dis : Discipline.values()) {
            ArrayList<TrainingTime> bestTimeInDis = new ArrayList<>();
            for (CompetitionSwimmer cs : CompSwimmers) {
                if (cs.hasDiscipline(dis)) {
                    bestTimeInDis.add(cs.fastestDisTime(dis));
                }
            }
            bestTimeInDis.sort(null);
            top5inAllDis[i] = bestTimeInDis;
            i++;
        }
        return top5inAllDis;
    }
    private static ArrayList<CompetitionSwimmer> getJuniorCompSwimmers() {
        ArrayList<CompetitionSwimmer> youngGuns = new ArrayList<>();
        for (CompetitionSwimmer swimmer : localStorage.getCompetitionSwimmers()) {
            if (swimmer.getAge() < 18){
                youngGuns.add(swimmer);
            }
        }
        return youngGuns;
    }
    private static ArrayList<CompetitionSwimmer> getSeniorCompSwimmers() {
        ArrayList<CompetitionSwimmer> oldGuys = new ArrayList<>();
        for (CompetitionSwimmer swimmer : localStorage.getCompetitionSwimmers()) {
            if (swimmer.getAge() > 17){
                oldGuys.add(swimmer);
            }
        }
        return oldGuys;
    }
    public static CompetitionSwimmer findComSwimmerFromId(int id){
        for (CompetitionSwimmer swimmer : localStorage.getCompetitionSwimmers()){
            if (swimmer.getMemberId() == id){
                return swimmer;
            }
        }
        return null;
    }
}
