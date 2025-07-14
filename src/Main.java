import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static FileHandler fh = new FileHandler();

    private static ArrayList<Trainer> trainers;
    private static Trainer trainer;
    private static ArrayList<Member> members;
    private static Member soren;
    private static ArrayList<Payment> payments;

    public static void testBootUp(){
        members = fh.loadMembers();
        soren = members.get(5);

        trainers = createTestTrainers();
        trainer = trainers.get(0);

        payments = fh.loadPayments();
        addPaymentsToMember();
    }

    public static void main(String[] args) {
        testBootUp();


    }

    public static void mainMenu(){
        boolean quit = false;
        do {
            System.out.println("\nDelfinen");
            System.out.println("1. Opret nyt medlem");
            System.out.println("2. Opret ny træner");
            System.out.println("3. Skift medlems medlemsstatus");
            System.out.println("5. Gå et økonomi menuen");
            System.out.println("8. Log ud");
            System.out.print("Vælg en mulighed: ");
            Scanner scanner = new Scanner(System.in);
            int option = readValidInt(scanner);
            switch (option) {
                case 1:
                    promptNewMember(scanner);
                    break;
                case 5:
                    econMenu();
                    break;
                case 8:
                    quit = true;
                    break;
                default:
                    System.out.println(option + " er ikke en mulighed. Vælg mellem:");
                    break;
            }
        } while (!quit);
    }
    public static void econMenu(){
        boolean quit = false;
        do {
            System.out.println("\nØkonomi");
            System.out.println("1. Se medlemers balance");
            System.out.println("2. Se medlemer i restance");
            System.out.println("3. Se medlems betalinger");
            System.out.println("4. Indberet betaling");
            System.out.println("5. Kontigent i alt");

            System.out.println("6. Gå tilbage");
            System.out.print("Vælg en mulighed: ");
            Scanner scanner = new Scanner(System.in);
            int option = readValidInt(scanner);
            switch (option) {
                case 1:
                    memberBalance();
                    break;
                case 2:
                    membersInMinus();
                    break;
                case 3:
                    promptNewPayment();
                case 4:

                case 5:
                    totalKontigent();
                    break;
                case 6:
                    quit = true;
                    break;
                default:
                    System.out.println(option + " er ikke en mulighed. Vælg mellem:");
                    break;
            }
        } while (!quit);
    }

    private static void promptNewPayment() {
        System.out.println("Vælg medlem som har betalt");
        int i = 1;
        for (Member member : members) {
            System.out.println(i + ") " + member.getName() + ", balance " + member.calMemberBalance() + "kr");
            i++;
        }
        System.out.print("Vælg relevant ID på medlem ");
        int option = readValidInt(scanner);
        Member m = members.get(option);
        System.out.print("Dato (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Beløb: ");
        double amount = Double.parseDouble(scanner.nextLine());
        createPayment(m, date, amount);

        //System.out.println("");
    }

    public static void createPayment(Member member, LocalDate date, double amount){
        member.createPayment(date, amount);
    }

    private static void memberBalance() {
        int i = 1;
        for (Member member : members) {
            System.out.println(i + ") " + member.name + "\tbalance: " + member.calMemberBalance());
            i++;
        }
    }

    //TODO
    private static void membersInMinus() {
    }

    private static void totalKontigent() {
        double total = 0;
        for (Member member : members) {
            total += member.getQuota();
        }
        System.out.println(total);
    }
    public static void promptNewMember(Scanner scanner){
        System.out.println("Opret nyt medlem");
        System.out.println("Indtast oplysninger");
        System.out.print("Navn: ");
        String name = scanner.nextLine();
        System.out.print("Fødselsdag (YYYY-MM-DD): ");
        LocalDate birthday = LocalDate.parse(scanner.nextLine()); //TODO: valider fødselsdag
        boolean membership;
        do{
            System.out.print("Aktivt medlem? Y/N: ");
            String answer = scanner.nextLine();
            if (answer.equals("Y")){
                membership = true;
                break;
            } else if (answer.equals("N")){
                membership = false;
                break;
            }
            System.out.println("Svar ikke godkendt!");
        } while (true);
        boolean competition;
        do{
            System.out.print("Medlem Motionist (1) eller Konkurrencesvømmer (2): ");
            String answer = scanner.nextLine();
            if (answer.equals("1")){
                competition = false;
                break;
            } else if (answer.equals("2")){
                competition = true;
                break;
            }
            System.out.println("Svar ikke godkendt!");
        } while (true);

                if (!competition){
            Member newMember = new Member(name, birthday, membership);
            System.out.println(newMember);
            System.out.print("Er medlem korrekt og skal oprettes? Y/N: ");
            String answer = scanner.nextLine();
            if (answer.equals("Y")){
                members.add(newMember);
                System.out.println("Medlem oprettet med kontigent:"); //TODO print forventet kontigent
                System.out.println(newMember.creationPrint());

            }
        }
        else {
            System.out.println("Vælg træner for svømmeren: ");
            int i = 1;
            for(Trainer trainer: trainers){
                System.out.println(i + ") " + trainer.getName());
            }
            System.out.print("Indtast træner id: ");
            int id = readValidInt(scanner);
            Trainer trainer = trainers.get(id - 1);

            System.out.println("Vælg svømmerens disipliner:");
            System.out.println("1. Butterfly");
            System.out.println("2. Crawl");
            System.out.println("3. Back");
            System.out.println("4. Breast");
            System.out.print("Indtast de relevante ID'er: ");
            String answer = scanner.nextLine();
            String[] splitted = answer.split("[-+*/=, ]");
            Set<Discipline> disciplines = new java.util.HashSet<>(Set.of());
            for (String split: splitted) {
                switch(split){
                    case "1", "Butterfly":
                        disciplines.add(Discipline.BUTTERFLY);
                        break;
                    case "2", "Crawl":
                        disciplines.add(Discipline.CRAWL);
                        break;
                    case "3", "Back":
                        disciplines.add(Discipline.BACK);
                        break;
                    case "4", "Breast":
                        disciplines.add(Discipline.BREAST);
                        break;
                    default:
                        break;
                }
            }
            CompetitionSwimmer newMember = new CompetitionSwimmer(name, birthday, membership, trainer, disciplines);
            members.add(newMember);
            System.out.println(newMember);
        }

    }
    public static Member createNewMember(String firstName, String birthdayStr) {
        LocalDate birthday = LocalDate.parse(birthdayStr);
        Member member = new Member(firstName, birthday, true);
        return member;
    }
    public double calculateQuota(Member member){
        return member.calQuota();
    }
    public static ArrayList<Member> createTestMembers() {
        ArrayList<Member> members = new ArrayList<>();
        members.add(createNewMember("Alice", "2019-01-21"));
        members.add(createNewMember("Bob", "1996-04-10"));
        members.add(createNewMember("Charlie", "2000-01-01"));
        return members;
    }
    public static ArrayList<Trainer> createTestTrainers() {
        ArrayList<Trainer> trainers = new ArrayList<>();
        trainers.add(createNewTrainer("Alice Super Trainer", "2019-01-21"));
        trainers.add(createNewTrainer("Bob uber Trainer", "1996-04-10"));
        return trainers;
    }
    public static Trainer createNewTrainer(String name, String birthdayStr) {
        LocalDate birthday = LocalDate.parse(birthdayStr);
        return new Trainer(name, birthday, true);
    }
    //TODO slow, stupid, unstable
    public static void addPaymentsToMember(){
        for(Payment p:payments){
            for (Member member : members){
                if (p.memberName.equals(member.name)){
                    member.addPayment(p);
                }
            }
        }
    }
    //if no int is found, promt the user to give a valid int
    public static int readValidInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Not a valid number. Try again:");
            scanner.nextLine();
        }
        int returnVal = scanner.nextInt();
        scanner.nextLine();
        return returnVal;
    }
}
