import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
public class TrainingTime implements Comparable<TrainingTime> {

    LocalTime time;
    LocalDate trainingDate;
    Discipline discipline;

    TrainingTime(LocalTime time, Discipline discipline, LocalDate trainingDate) {
        this.time = time;
        this.discipline = discipline;
        this.trainingDate = trainingDate;
    }

    @Override
    public int compareTo(TrainingTime o) {
        return time.compareTo(o.time);
    }
}
