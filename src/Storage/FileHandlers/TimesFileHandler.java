package Storage.FileHandlers;

import Models.CompetitionTime;
import Models.Discipline;
import Models.TrainingTime;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static Models.DisciplineConverter.stringToDiscipline;

public class TimesFileHandler {
    private static final String TRAINING_TIMES_FILE = "trainingTimes.txt";
    private static final String COMPETITION_TIMES_FILE = "competitionTimes.txt";


    public void saveTrainingTimes(ArrayList<TrainingTime> times) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TRAINING_TIMES_FILE))) {
            for (TrainingTime t : times) {
                writer.printf("%s;%s;%s;%sn",
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
        try (PrintWriter writer = new PrintWriter(new FileWriter(TRAINING_TIMES_FILE))) {
            for (CompetitionTime t : times) {
                writer.printf("%s;%s;%s;%s;%s;%d%n",
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

    public ArrayList<TrainingTime> loadTrainingTimes() {
        ArrayList<TrainingTime> times = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TRAINING_TIMES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int memberId = Integer.parseInt(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                Discipline dis = stringToDiscipline(parts[2]);
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
                String[] parts = line.split(";");
                int memberId = Integer.parseInt(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                Discipline dis = stringToDiscipline(parts[2]);
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
}