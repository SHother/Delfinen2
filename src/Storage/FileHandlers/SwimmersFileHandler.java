package Storage.FileHandlers;

import Models.CompetitionSwimmer;
import Models.Discipline;
import Models.Swimmer;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static Models.DisciplineConverter.stringToDiscipline;

public class SwimmersFileHandler {

    private static final String CHILL_SWIMMER_FILE = "chillSwimmers.txt";
    private static final String COMPETITION_SWIMMER_FILE = "competitionSwimmers.txt";


    public ArrayList<Swimmer> loadChillSwimmers() {
        ArrayList<Swimmer> swimmers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CHILL_SWIMMER_FILE))) {
            String line;
            int highetsId = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String name = parts[0];
                LocalDate birthday = LocalDate.parse(parts[1]);
                boolean membership = Boolean.parseBoolean(parts[2]);
                LocalDate joinedDate = LocalDate.parse(parts[3]);
                int id = Integer.parseInt(parts[4]);

                if (id > highetsId) highetsId = id;
                swimmers.add(new Swimmer(name, birthday, membership, joinedDate, id));
            }
            Swimmer.setMemberIdCounter(highetsId);
        } catch (IOException e) {
            System.out.println("Could not load members: " + e.getMessage());
        }
        return swimmers;
    }

    public ArrayList<CompetitionSwimmer> loadCompetitionSwimmers() {
        ArrayList<CompetitionSwimmer> swimmers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(COMPETITION_SWIMMER_FILE))) {
            String line;
            int highetsId = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String name = parts[0];
                LocalDate birthday = LocalDate.parse(parts[1]);
                boolean membership = Boolean.parseBoolean(parts[2]);
                LocalDate joinedDate = LocalDate.parse(parts[3]);
                int id = Integer.parseInt(parts[4]);
                int trainerId = Integer.parseInt(parts[5]);

                String[] strDisciplines = parts[6].split(",");
                Set<Discipline> disciplines = new HashSet<>();
                for (String strDiscipline : strDisciplines) {
                    disciplines.add(stringToDiscipline(strDiscipline));
                }

                if (id > highetsId) highetsId = id;
                swimmers.add(new CompetitionSwimmer(name, birthday, membership, joinedDate, id, trainerId, disciplines));
            }
            if (highetsId > Swimmer.getMemberIdCounter()) Swimmer.setMemberIdCounter(highetsId);

        } catch (IOException e) {
            System.out.println("Could not load members: " + e.getMessage());
        }
        return swimmers;
    }

    public void addChillSwimmer(Swimmer swimmer) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CHILL_SWIMMER_FILE, true))) {
            writer.printf("%s;%s;%b;%s;%d%n",
                    swimmer.getName(),
                    swimmer.getBirthday(),
                    swimmer.isMembership(),
                    swimmer.getJoinedDate(),
                    swimmer.getMemberId());
        } catch (IOException e) {
            System.out.println("Could not append chill swimmer: " + e.getMessage());
        }
    }

    public void addCompetitionSwimmer(CompetitionSwimmer swimmer) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COMPETITION_SWIMMER_FILE, true))) {
            StringBuilder disciplineList = new StringBuilder();
            for (Discipline d : swimmer.getDisciplines()) {
                disciplineList.append(d.name()).append(",");
            }
            if (!swimmer.getDisciplines().isEmpty()) {
                disciplineList.setLength(disciplineList.length() - 1); // Remove trailing comma
            }

            writer.printf("%s;%s;%b;%s;%d;%d;%s%n",
                    swimmer.getName(),
                    swimmer.getBirthday(),
                    swimmer.isMembership(),
                    swimmer.getJoinedDate(),
                    swimmer.getMemberId(),
                    swimmer.getTrainerID(), // Assuming trainer is stored as ID
                    disciplineList);
        } catch (IOException e) {
            System.out.println("Could not append competition swimmer: " + e.getMessage());
        }
    }

    public void saveChillSwimmers(ArrayList<Swimmer> swimmers) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CHILL_SWIMMER_FILE))) {
            for (Swimmer m : swimmers) {
                writer.printf("%s;%s;%b;%s;%d%n",
                        m.getName(),
                        m.getBirthday(),
                        m.isMembership(),
                        m.getJoinedDate(),
                        m.getMemberId());
            }
        } catch (IOException e) {
            System.out.println("Could not save members: " + e.getMessage());
        }
    }

    public void saveCompetitionSwimmers(ArrayList<CompetitionSwimmer> swimmers) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COMPETITION_SWIMMER_FILE))) {
            for (CompetitionSwimmer swimmer : swimmers) {
                StringBuilder disciplineList = new StringBuilder();
                for (Discipline d : swimmer.getDisciplines()) {
                    disciplineList.append(d.name()).append(",");
                }
                if (!swimmer.getDisciplines().isEmpty()) {
                    disciplineList.setLength(disciplineList.length() - 1); // Remove trailing comma
                }

                writer.printf("%s;%s;%b;%s;%d;%d;%s%n",
                        swimmer.getName(),
                        swimmer.getBirthday(),
                        swimmer.isMembership(),
                        swimmer.getJoinedDate(),
                        swimmer.getMemberId(),
                        swimmer.getTrainerID(),
                        disciplineList);
            }
        } catch (IOException e) {
            System.out.println("Could not save competition swimmers: " + e.getMessage());
        }
    }
}