package Models;

public class DisciplineConverter{
    public static Discipline stringToDiscipline(String disciplineAsStr){
        try{
            return Discipline.valueOf(disciplineAsStr);
        }catch(IllegalArgumentException e){
            System.out.println(disciplineAsStr + " findes ikke som disciplin");
            return null;
        }
    }
}
