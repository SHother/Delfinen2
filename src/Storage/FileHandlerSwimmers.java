package Storage;

import Models.Swimmer;
import Models.Payment;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileHandlerSwimmers {

    private static final String MEMBER_FILE = "members.txt";

    public void saveMembers(ArrayList<Swimmer> swimmers) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MEMBER_FILE))) {
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

    public ArrayList<Swimmer> loadMembers() {
        ArrayList<Swimmer> swimmers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MEMBER_FILE))) {
            String line;
            int highetsId = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String name = parts[0];
                LocalDate birthday = LocalDate.parse(parts[1]);
                boolean membership = Boolean.parseBoolean(parts[2]);
                LocalDate joinedDate = LocalDate.parse(parts[3]);
                int id = Integer.parseInt(parts[4]);
                if (id > highetsId) {
                    highetsId = id;
                }

                swimmers.add(new Swimmer(name, birthday, membership, joinedDate, id));
            }
            swimmers.get(0).setMemberIdCounter(highetsId);
        } catch (IOException e) {
            System.out.println("Could not load members: " + e.getMessage());
        }
        return swimmers;
    }
}