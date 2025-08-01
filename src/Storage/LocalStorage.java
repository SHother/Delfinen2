package Storage;

import Models.*;
import Storage.FileHandlers.*;


import java.time.LocalDate;
import java.util.ArrayList;

public class LocalStorage {
    private static final SwimmersFileHandler s_fh = new SwimmersFileHandler();
    private static final PaymentsFileHandler p_fh = new PaymentsFileHandler();
    private static final TimesFileHandler t_fh = new TimesFileHandler();

    private ArrayList<CompetitionSwimmer> competitionSwimmers = new ArrayList<>();
    private ArrayList<Swimmer> allSwimmers = new ArrayList<>();
    private ArrayList<Trainer> trainers = new ArrayList<>();
    private ArrayList<Payment> payments = new ArrayList<>();
    private ArrayList<TrainingTime> times = new ArrayList<>();

    public LocalStorage() {
        System.out.println("Loading...");
        readFileToLocalStorage();
        System.out.println("joining Times");
        addTimesToMember();
        System.out.println("joining Payments");
        addPaymentsToMember();
    }

    private void readFileToLocalStorage(){
        System.out.println("Loading Swimmers...");
        allSwimmers = s_fh.loadChillSwimmers();
        System.out.println("Loading Comp Swimmers...");
        competitionSwimmers = s_fh.loadCompetitionSwimmers();
        allSwimmers.addAll(competitionSwimmers);
        System.out.println("Loading Payments...");
        payments = p_fh.loadPayments();
        System.out.println("Loading Times...");
        times = t_fh.loadTrainingTimes();
        times.addAll(t_fh.loadCompetitionTimes());
        trainers = createTestTrainers();
    }

    //Boot up loading data
    private void addPaymentsToMember(){
        for (Swimmer swimmer : allSwimmers){
            for (Payment p : payments){
                if (p.getMemberId() == swimmer.getMemberId()){
                    swimmer.addPayment(p);
                }
            }
        }
    }
    //Boot up loading data
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
        trainers.add(createNewTrainer("Alice Super trainer", "2005-01-21"));
        trainers.add(createNewTrainer("Bob uber trainer", "1996-04-10"));
        return trainers;
    }

    public void addSwimmer(Swimmer swimmer) {
         allSwimmers.add(swimmer);
         if(swimmer instanceof CompetitionSwimmer){
             competitionSwimmers.add((CompetitionSwimmer) swimmer);
             s_fh.addCompetitionSwimmer((CompetitionSwimmer) swimmer);
         } else
             s_fh.addChillSwimmer(swimmer);

    }
    public void addPayment(Payment payment, Swimmer swimmer) {
        payments.add(payment);
        swimmer.addPayment(payment);
        p_fh.addPayment(payment);
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }
    public ArrayList<CompetitionSwimmer> getCompetitionSwimmers() {
        return competitionSwimmers;
    }
    public ArrayList<Swimmer> getAllSwimmers() {
        return allSwimmers;
    }
    public ArrayList<Payment> getPayments() {
        return payments;
    }
    public ArrayList<TrainingTime> getTimes() {
        return times;
    }


    public void addDisciplineToSwimmer(CompetitionSwimmer swimmer, Discipline dis) {
    swimmer.addDiscipline(dis);
    //s_fh.updateSwimmer(swimmer) //TODO ..
    }

    public void addTimeToSwimmer(CompetitionSwimmer swimmer, TrainingTime tt) {
        swimmer.addRecordTime(tt);
        if(tt instanceof CompetitionTime) t_fh.addCompetitionTimes((CompetitionTime) tt);
        else t_fh.addTrainingTime(tt);
    }
}
