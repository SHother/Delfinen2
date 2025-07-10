import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Member[] testMembers = createTestMembers();
        for (Member testMember : testMembers) {
            System.out.println(testMember);
        }

    }
    public static Member createNewMember(String firstName, String birthdayStr) {
        LocalDate birthday = LocalDate.parse(birthdayStr);
        Member member = new Member(firstName, birthday, null, true);
        return member;
    }
    public void calculateQuota(Member member){
        member.calcualteQuota();
    }
    public static Member[] createTestMembers() {
        Member[] members = new Member[3];
        members[0] = createNewMember("Alice", "2019-01-21");
        members[1] = createNewMember("Bob", "1996-04-10");
        members[2] = createNewMember("Charlie", "2000-01-01");
        return members;
    }
}
