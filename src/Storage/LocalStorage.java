package Storage;

import Models.*;
import Storage.FileHandlers.*;


import java.time.LocalDate;
import java.util.ArrayList;

public class LocalStorage {
    private static final SwimmersFileHandler s_fh = new SwimmersFileHandler();
    private static final PaymentsFileHandler p_fh = new PaymentsFileHandler();
    private static final TimesFileHandler t_fh = new TimesFileHandler();

    private ArrayList<CompetitionSwimmer> competitionSwimmers= new ArrayList<>();
    private ArrayList<Swimmer> swimmers = new ArrayList<>();
    private ArrayList<Trainer> trainers = new ArrayList<>();
    private ArrayList<Payment> payments = new ArrayList<>();
    private ArrayList<TrainingTime> times = new ArrayList<>();

    public LocalStorage() {

    }
    public void bootUp(){
        readFileToLocalStorage();
        addTimesToMember();
        addPaymentsToMember();
    }

    private void readFileToLocalStorage(){
        swimmers = s_fh.loadChillSwimmers();
        competitionSwimmers = s_fh.loadCompetitionSwimmers();
        payments = p_fh.loadPayments();
        times = t_fh.loadTrainingTimes();
        times.addAll(t_fh.loadCompetitionTimes());
        trainers = createTestTrainers();
    }

    //Boot up
    //all below is slow, stupid
    private void addPaymentsToMember(){
        for (Swimmer swimmer : swimmers){
            for (Payment p : payments){
                if (p.getMemberId() == swimmer.getMemberId()){
                    swimmer.addPayment(p);
                }
            }
        }
    }
    private void addTimesToMember(){
        for (CompetitionSwimmer swimmer : competitionSwimmers){
            for (TrainingTime t : times){
                if (t.getMemberID() == swimmer.getMemberId()){
                    swimmer.addRecordTime(t);
                }
            }
        }
    }
    public static Trainer createNewTrainer(String name, String birthdayStr) {
        LocalDate birthday = LocalDate.parse(birthdayStr);
        return new Trainer(name, birthday, true);
    }


    public static ArrayList<Trainer> createTestTrainers() {
        ArrayList<Trainer> trainers = new ArrayList<>();
        trainers.add(createNewTrainer("Alice Super Models.Trainer", "2019-01-21"));
        trainers.add(createNewTrainer("Bob uber Models.Trainer", "1996-04-10"));
        return trainers;
    }
}
