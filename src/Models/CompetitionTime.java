package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class CompetitionTime extends TrainingTime {
    String competition;
    int placement;

    public CompetitionTime(LocalTime time, Discipline discipline, LocalDate trainingDate, int memberID, String competition, int placement) {
        super(memberID, time, discipline, trainingDate);
        this.competition = competition;
        this.placement = placement;
    }

    public CompetitionTime(TrainingTime tt, String competition, int placement) {
        super(tt.memberID, tt.time, tt.discipline, tt.trainingDate);
        this.competition = competition;
        this.placement = placement;
    }
}
