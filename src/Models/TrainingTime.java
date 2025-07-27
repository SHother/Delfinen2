package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class TrainingTime implements Comparable<TrainingTime> {
    int memberID;
    LocalTime time;
    LocalDate trainingDate;
    Discipline discipline;


    public TrainingTime(int memberID, LocalTime time, Discipline discipline, LocalDate trainingDate) {
        this.memberID = memberID;
        this.time = time;
        this.discipline = discipline;
        this.trainingDate = trainingDate;

    }

    @Override
    public int compareTo(TrainingTime o) {
        return this.time.compareTo(o.time);
    }
    @Override
    public String toString() {
        return "Tid " + time + ", Dato " + trainingDate;
    }
}
