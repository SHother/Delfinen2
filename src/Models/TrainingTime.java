package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class TrainingTime implements Comparable<TrainingTime> {

    LocalTime time;
    LocalDate trainingDate;
    Discipline discipline;
    int memberID;

    public TrainingTime(LocalTime time, Discipline discipline, LocalDate trainingDate, int memberID) {
        this.time = time;
        this.discipline = discipline;
        this.trainingDate = trainingDate;
        this.memberID = memberID;
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
