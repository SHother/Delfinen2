import Models.*;
import Storage.FileHandlers.PaymentsFileHandler;
import Storage.FileHandlers.SwimmersFileHandler;
import Storage.FileHandlers.TimesFileHandler;

import java.time.LocalDate;
import java.util.*;

import static java.util.Collections.sort;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SwimmersFileHandler s_fh = new SwimmersFileHandler();
    private static final PaymentsFileHandler p_fh = new PaymentsFileHandler();
    private static final TimesFileHandler t_fh = new TimesFileHandler();

    private static ArrayList<Trainer> trainers;
    private static Trainer trainer;
    private static ArrayList<Swimmer> swimmers;
    private static Swimmer soren;
    private static ArrayList<CompetitionSwimmer> comSwimmers;
    private static CompetitionSwimmer comSwimmer;
    private static ArrayList<Payment> payments;
    private static ArrayList<TrainingTime> trainingTimes;

    public static void testBootUp(){
        swimmers = s_fh.loadChillSwimmers();
        soren = swimmers.get(5);

        //trainers = createTestTrainers();
        trainer = trainers.getFirst();

        payments = p_fh.loadPayments();

    }

    public static void main(String[] args) {
        testBootUp();

    }


}
