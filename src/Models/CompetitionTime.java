package Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class CompetitionTime extends TrainingTime {
    String competition;
    int placement;

    public CompetitionTime(int memberID, LocalTime time, Discipline discipline, LocalDate trainingDate, String competition, int placement) {
        super(memberID, time, discipline, trainingDate);
        this.competition = competition;
        this.placement = placement;
    }

    public CompetitionTime(TrainingTime tt, String competition, int placement) {
        super(tt.memberID, tt.time, tt.discipline, tt.trainingDate);
        this.competition = competition;
        this.placement = placement;
    }

    public String getCompetition() {
        return competition;
    }
    public int getPlacement() {
        return placement;
    }
    public void setCompetition(String competition) {
        this.competition = competition;
    }
    public void setPlacement(int placement) {
        this.placement = placement;
    }

}
