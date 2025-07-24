package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class CompetisionTime extends TrainingTime {
    String competition;
    int placement;

    public CompetisionTime(LocalTime time, Discipline discipline, LocalDate trainingDate, String competition, int placement) {
        super(time, discipline, trainingDate);
        this.competition = competition;
        this.placement = placement;
    }

    public CompetisionTime(TrainingTime tt, String competition, int placement) {
        super(tt.time, tt.discipline, tt.trainingDate);
        this.competition = competition;
        this.placement = placement;
    }
}
