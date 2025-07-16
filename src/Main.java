import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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

        System.out.println(readDiscipline());
    }

    public static void mainMenu(){
        boolean quit = false;
        do {
            System.out.println("\nDelfinen");
            System.out.println("1. Opret nyt medlem");
            System.out.println("2. Opret ny træner"); //TODO
            System.out.println("3. Skift medlems medlemsstatus"); //TODO
            System.out.println("5. Gå til økonomi menuen");
            System.out.println("6. Gå til træner menuen");

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
                case 6:
                    trainerMenu();
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

    private static void trainerMenu() {
        System.out.println("\nØkonomi");
        System.out.println("1. Register træningstid"); //IN PROGRESS
        System.out.println("2. Register konkurrencetid"); //TODO
        System.out.println("3. Se top 5"); //TODO
        System.out.println("9. Gå tilbage");
        System.out.print("Vælg en mulighed: ");
        Scanner scanner = new Scanner(System.in);
        int option = readValidInt(scanner);
        switch (option) {
            case 1, 2:
                registerTime(option);
                break;
            case 3:

            case 5:
        }
    }

    private static void registerTime(int badCoding) {
        System.out.println("Vælg medlem hvis tid skal registres");
        CompetitionSwimmer com = findComSwimmerFromId();
        if (com != null) {
            TrainingTime tt = registerTrainingTime(com);
            if (tt != null) {
                if(badCoding == 2){
                    tt = registerCompetisionTime(com, tt);
                }
                com.addRecordTime(tt);
                System.out.println("Tid registreret");
            }
        }
    }
    public static TrainingTime registerTrainingTime(CompetitionSwimmer com) {
        System.out.print("Hvilken disiplin blev svømmet?");
        Discipline dis = readDiscipline();
        if(!com.getDisciplines().contains(dis)){
            System.out.println("Svømmeren er ikke registrert i denne disiplin.");
            System.out.print("Vil du tilføje denne disiplin til svømmerens liste? (Y/N): ");
            String choice = scanner.nextLine();
            if(choice.equalsIgnoreCase("y")){
                com.addDisciplin(dis);
                System.out.println("Disiplin registrert");
            } else {
                System.out.println("Tid ikke registrert");
                return null;
            }
        }
        System.out.print("Dato (yyyy-mm-dd): ");
        LocalDate date = readDate();

        System.out.print("Registrert tid i " + dis.toString().toLowerCase() + ": ");
        LocalTime time = readTime();

        return new TrainingTime(time, dis, date);

    }
    private static CompetisionTime registerCompetisionTime(CompetitionSwimmer swimmer, TrainingTime tt) {
        System.out.print("Skriv navn på stævne: ");
        String comp = scanner.nextLine();
        System.out.print("Skriv " + swimmer.getName() + "'s placering til " + comp + ": ");
        int placement = readValidInt(scanner);
        return new CompetisionTime(tt, comp, placement);
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
            System.out.println("9. Gå tilbage");
            System.out.print("Vælg en mulighed: ");
            Scanner scanner = new Scanner(System.in);
            int option = readValidInt(scanner);
            switch (option) {
                case 1:
                    membersBalancePrint();
                    break;
                case 2:
                    membersInMinus();
                    break;
                case 3:
                    promptMemberPayments();
                    break;
                case 4:
                    promptNewPayment();
                    break;
                case 5:
                    totalContingent();
                    break;
                case 9:
                    quit = true;
                    break;
                default:
                    System.out.println(option + " er ikke en mulighed. Vælg mellem:");
                    break;
            }
        } while (!quit);
    }

    //TODO see a members payments
    private static void promptMemberPayments() {

    }
    private static void promptNewPayment() {
        System.out.println("Vælg medlem som har betalt");

        Member m = findMemberFromId();
        if (m != null) {
            System.out.print("Dato (yyyy-mm-dd): ");
            LocalDate date = readDate();
            System.out.print("Beløb: ");
            double amount = Double.parseDouble(scanner.nextLine());
            createPayment(m, date, amount);
        }
    }

    public static Member findMemberFromId() {
        for (Member member: members){
            System.out.println(member.getMemberId() + ") " + member.getName());
        }
        System.out.print("Skriv Id'et på medlem: ");
        int option = readValidInt(scanner);
        for (Member member: members){
            if (member.getMemberId() == option){
                return member;
            }
        }
        System.out.println("Medlem " + option + " kan ikke findes!");
        return null;
    }
    public static CompetitionSwimmer findComSwimmerFromId() {
        for (Member member: members){
            if (member instanceof CompetitionSwimmer){
                System.out.println(member.getMemberId() + ") " + member.getName());
            }
        }
        System.out.print("Skriv Id'et på medlem: ");
        int option = readValidInt(scanner);
        for (Member member: members){
            if (member.getMemberId() == option && member instanceof CompetitionSwimmer){
                return (CompetitionSwimmer) member;
            }
        }
        System.out.println("Medlem " + option + " kan ikke findes!");
        return null;
    }

    public static void createPayment(Member member, LocalDate date, double amount){
        member.createPayment(date, amount);
    }
    private static void membersBalancePrint() {
        int i = 1;
        for (Member member : members) {
            System.out.println(i + ")\t" + member.name + "\tbalance: " + member.calMemberBalance());
            i++;
        }
    }
    //TODO
    private static void membersInMinus() {
        double balance;
        for (Member member : members) {
            balance = member.calMemberBalance();
            if (balance < 0) {
                System.out.println(member.name + "\tbalance: " + member.calMemberBalance());
            }
        }
    }
    private static void totalContingent() {
        double total = 0;
        for (Member member : members) {
            total += member.getQuota();
        }
        System.out.println(total);
    }
    public static Trainer createNewTrainer(String name, String birthdayStr) {
        LocalDate birthday = LocalDate.parse(birthdayStr);
        return new Trainer(name, birthday, true);
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

    //TODO slow, stupid, unstable
    //Boot up info dump
    public static void addPaymentsToMember(){
        for(Payment p:payments){
            for (Member member : members){
                if (p.getMemberId() == member.getMemberId()){
                    member.addPayment(p);
                }
            }
        }
    }

    //TODO
    public static LocalDate readDate(){
        return LocalDate.parse(scanner.nextLine());
    }

    //TODO
    public static LocalTime readTime(){
        return LocalTime.parse(scanner.nextLine());
    }

    public static Discipline readDiscipline(){
        int i = 1;
        for (Discipline dis : Discipline.values()) {
            System.out.println(i + ") " + dis);
            i++;
        }
        System.out.print("Vælg relevant id: ");
        return Discipline.values()[readValidInt(scanner)-1];
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
