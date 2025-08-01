package Storage.FileHandlers;

import Models.CompetitionTime;
import Models.Discipline;
import Models.TrainingTime;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TimesFileHandler {
    private static final String PATH = "src/Storage/Database/";
    private static final String TRAINING_TIMES = "trainingTimes.txt";
    private static final String COMPETITION_TIMES = "competitionTimes.txt";

    private static final String TRAINING_TIMES_FILE = PATH + TRAINING_TIMES;
    private static final String COMPETITION_TIMES_FILE = PATH + COMPETITION_TIMES;

    public ArrayList<TrainingTime> loadTrainingTimes(){
        ArrayList<TrainingTime> times = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TRAINING_TIMES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                int memberId = Integer.parseInt(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                Discipline dis = Discipline.stringToDiscipline(parts[2]);
                LocalDate trainingDate = LocalDate.parse(parts[3]);
                times.add(new TrainingTime(memberId, time, dis, trainingDate ));
            }
        } catch (IOException e) {
            System.out.println("Could not load payments: " + e.getMessage());
        }
        return times;
    }
    public ArrayList<CompetitionTime> loadCompetitionTimes() {
        ArrayList<CompetitionTime> times = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(COMPETITION_TIMES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                int memberId = Integer.parseInt(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                Discipline dis = Discipline.stringToDiscipline(parts[2]);
                LocalDate trainingDate = LocalDate.parse(parts[3]);
                String competition = parts[4];
                int placement = Integer.parseInt(parts[5]);

                times.add(new CompetitionTime(memberId, time, dis, trainingDate, competition, placement));
            }
        } catch (IOException e) {
            System.out.println("Could not load payments: " + e.getMessage());
        }
        return times;
    }

    public void addTrainingTime(TrainingTime time) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TRAINING_TIMES_FILE, true))) {
            writer.printf("%s\t%s\t%s\t%sn",
                    time.getMemberID(),
                    time.getTime(),
                    time.getDiscipline(),
                    time.getTrainingDate());
        } catch (IOException e) {
            System.out.println("Could not save time: " + e.getMessage());
        }
    }
    public void addCompetitionTimes(CompetitionTime time) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COMPETITION_TIMES_FILE, true))) {

            writer.printf("%s\t%s\t%s\t%s\t%s\t%d%n",
                    time.getMemberID(),
                    time.getTime(),
                    time.getDiscipline(),
                    time.getTrainingDate(),
                    time.getCompetition(),
                    time.getPlacement());

        } catch (IOException e) {
            System.out.println("Could not save payments: " + e.getMessage());
        }
    }

    public void saveTrainingTimes(ArrayList<TrainingTime> times) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TRAINING_TIMES_FILE))) {
            for (TrainingTime t : times) {
                writer.printf("%s\t%s\t%s\t%sn",
                        t.getMemberID(),
                        t.getTime(),
                        t.getDiscipline(),
                        t.getTrainingDate());
            }
        } catch (IOException e) {
            System.out.println("Could not save payments: " + e.getMessage());
        }
    }
    public void saveCompetitionTimes(ArrayList<CompetitionTime> times) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COMPETITION_TIMES_FILE))) {
            for (CompetitionTime t : times) {
                writer.printf("%s\t%s\t%s\t%s\t%s\t%d%n",
                        t.getMemberID(),
                        t.getTime(),
                        t.getDiscipline(),
                        t.getTrainingDate(),
                        t.getCompetition(),
                        t.getPlacement());
            }
        } catch (IOException e) {
            System.out.println("Could not save payments: " + e.getMessage());
        }
    }
}