import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

public class CompetitionSwimmer extends Member {
    TrainingTime[] trainingTimes;
    Trainer trainer;
    Set<Discipline> disciplines;

    //to create a new member
    public CompetitionSwimmer(String name, LocalDate birthday, Boolean membership, Trainer trainer, Set<Discipline> disciplines) {
        this.name = name;
        this.birthday = birthday;
        this.payments = new ArrayList<>();
        this.membership = membership;
        this.joinedDate = LocalDate.now();
        this.trainer = trainer;
        this.disciplines = disciplines;
        this.trainingTimes = new TrainingTime[0];
    }
}
