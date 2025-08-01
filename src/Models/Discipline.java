package Models;

public enum Discipline {
    BUTTERFLY,
    CRAWL,
    BACK,
    BREAST;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
    public static Discipline stringToDiscipline(String disciplineAsStr){
        try{
            return Discipline.valueOf(disciplineAsStr.toUpperCase());
        }catch(IllegalArgumentException e){
            System.out.println(disciplineAsStr + " findes ikke som disciplin");
            return null;
        }
    }
}
