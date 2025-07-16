import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Set;

public class CompetitionSwimmer extends Member {
    ArrayList<TrainingTime> trainingTimes;
    Trainer trainer;
    Set<Discipline> disciplines;

    //to create a new member
    public CompetitionSwimmer(String name, LocalDate birthday, Boolean membership, Trainer trainer, Set<Discipline> disciplines) {
        super(name, birthday, membership);
        this.payments = new ArrayList<>();
        this.joinedDate = LocalDate.now();
        this.trainer = trainer;
        this.disciplines = disciplines;
        this.trainingTimes = new ArrayList<>();
        this.memberId = memberIdCounter++;
    }
    public void addRecordTime(LocalTime time, LocalDate date, Discipline dis) {
        trainingTimes.add(new TrainingTime(time, dis, date));
    }

    public void setTrainingTimes(ArrayList<TrainingTime> trainingTimes) {this.trainingTimes = trainingTimes;}
    public ArrayList<TrainingTime> getTrainingTimes() {return trainingTimes;}
    public void setTrainer(Trainer trainer) {this.trainer = trainer;}
    public Trainer getTrainer() {return trainer;}
    public void setDisciplines(Set<Discipline> disciplines) {this.disciplines = disciplines;}
    public Set<Discipline> getDisciplines() {return disciplines;}
    public void addRecordTime(TrainingTime trainingTime) {this.trainingTimes.add(trainingTime);}
    public void addDisciplin(Discipline discipline) {this.disciplines.add(discipline);}
}
