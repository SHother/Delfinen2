package Models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

public class CompetitionSwimmer extends Swimmer {
    ArrayList<TrainingTime> swimTimes;
    int trainerID;
    Set<Discipline> disciplines;

    //to create a new member
    public CompetitionSwimmer(String name, LocalDate birthday, Boolean membership, int trainerID, Set<Discipline> disciplines) {
        super(name, birthday, membership);
        this.payments = new ArrayList<>();
        this.joinedDate = LocalDate.now();
        this.trainerID = trainerID;
        this.disciplines = disciplines;
        this.swimTimes = new ArrayList<>();
        this.memberId = memberIdCounter++;
    }

    //to create swimmer from database
    public CompetitionSwimmer(String name, LocalDate birthday, Boolean membership, LocalDate joinedDate, int memberId, int trainerID, Set<Discipline> disciplines) {
        super(name, birthday, membership, joinedDate, memberId);
        this.trainerID = trainerID;
        this.disciplines = disciplines;
        this.swimTimes = new ArrayList<>();
    }

    public void addRecordTime(LocalTime time, LocalDate date, Discipline dis) {
        swimTimes.add(new TrainingTime(this.memberId, time, dis, date));
    }

    public ArrayList<TrainingTime> getTimesInDiscipline(Discipline dis) {
        ArrayList<TrainingTime> swimTimesInDis = new ArrayList<>();
        for (TrainingTime t : swimTimes) {
            if (t.discipline == dis) {
                swimTimesInDis.add(t);
            }
        }
        return swimTimesInDis;
    }

    public TrainingTime fastestDisTime(Discipline discipline) {
        ArrayList<TrainingTime> timesInDis = getTimesInDiscipline(discipline);
        timesInDis.sort(null);
        return timesInDis.getFirst();
    }

    public static class bestTimeInDis implements Comparator<CompetitionSwimmer>{
        private final Discipline discipline;
        public bestTimeInDis(Discipline discipline) {
            this.discipline = discipline;
        }
        @Override
        public int compare(CompetitionSwimmer o1, CompetitionSwimmer o2) {
            LocalTime best1 = o1.fastestDisTime(discipline).getTime();
            LocalTime best2 = o2.fastestDisTime(discipline).getTime();
            if (best1 == null) {best1 = LocalTime.MAX;}
            if (best2 == null) {best2 = LocalTime.MAX;}
            return best1.compareTo(best2);
        }
    }

    //Setters & getters
    public void setSwimTimes(ArrayList<TrainingTime> swimTimes) {this.swimTimes = swimTimes;}
    public ArrayList<TrainingTime> getSwimTimes() {return swimTimes;}
    public void setTrainerID(int trainerID) {this.trainerID = trainerID;}
    public int getTrainerID() {return trainerID;}
    public void setDisciplines(Set<Discipline> disciplines) {this.disciplines = disciplines;}
    public boolean hasDiscipline(Discipline dis) {return disciplines.contains(dis);}
    public Set<Discipline> getDisciplines() {return disciplines;}
    public void addRecordTime(TrainingTime trainingTime) {this.swimTimes.add(trainingTime);}
    public void addDiscipline(Discipline discipline) {this.disciplines.add(discipline);}
}
