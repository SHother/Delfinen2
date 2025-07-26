package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class CompetitionTime extends TrainingTime {
    String competition;
    int placement;

    public CompetitionTime(LocalTime time, Discipline discipline, LocalDate trainingDate, int memberID, String competition, int placement) {
        super(time, discipline, trainingDate, memberID);
        this.competition = competition;
        this.placement = placement;
    }

    public CompetitionTime(TrainingTime tt, String competition, int placement) {
        super(tt.time, tt.discipline, tt.trainingDate, tt.memberID);
        this.competition = competition;
        this.placement = placement;
    }
}
