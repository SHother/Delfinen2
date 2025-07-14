import java.time.*;
import java.util.ArrayList;


public class Member {
    static int passiveMembershipCost = 500;
    static int youthMembershipCost = 1000;
    static int seniorMembershipCost = 1600;
    static double seniorMembershipDiscount = 0.25;

    String name;
    LocalDate birthday;
    ArrayList<Payment> payments;
    boolean paymentStatus;
    boolean membership;
    LocalDate joinedDate;
    double quota;

    public double calQuota(){
        double quota = passiveMembershipCost;
        if (membership){
            if (this.getAge() < 19){
                quota = youthMembershipCost;
            }
            else {quota = seniorMembershipCost;}

        }
        if (this.getAge() >= 60){
            quota *= 1.0 - seniorMembershipDiscount;
        }
        return quota;
    }
    public int membershipYears(){
        return Period.between(joinedDate, LocalDate.now()).getYears();
    }
    // assumes that the member has been active in all their time
    public double calTotalQuota(){
        double total = 0.0;

        int yearsAsSenior = 0;
        if (getAge() > 59){
            yearsAsSenior = getAge() - 60;
        }
        int yearsAsMemberUnder18 = 18 - ageAtJoinedDate();
        if (yearsAsMemberUnder18 < 0){
            yearsAsMemberUnder18 = 0;
        }
        int yearsAsMemberBetween18And60 = membershipYears() - yearsAsMemberUnder18 - yearsAsSenior;
        if (yearsAsMemberBetween18And60 < 0){
            yearsAsMemberBetween18And60 = 0;
        }

        total += yearsAsMemberUnder18 * youthMembershipCost;
        total += yearsAsMemberBetween18And60 * seniorMembershipCost;
        total += yearsAsSenior * seniorMembershipCost * (1 - seniorMembershipCost);
        total += getQuota();

        return total;
    }

    public int ageAtJoinedDate(){return Period.between(birthday, joinedDate).getYears();}

    public double calMemberBalance(){
        double payments = this.calTotalPayments();
        double quotas = this.calTotalQuota();
        return payments - quotas;
    }
    public double calTotalPayments(){
        double totalPayments= 0;
        for (Payment payment : this.payments){
            totalPayments += payment.getAmount();
        }
        return totalPayments;
    }
    public String creationPrint() {
        return "test";
    }
    Member(){
    }

    //to create a new member
    Member(String name, LocalDate birthday, Boolean membership){
        this.name = name;
        this.birthday = birthday;
        this.payments = new ArrayList<>();
        this.membership = membership;
        this.joinedDate = LocalDate.now();
        this.quota = calQuota();
    }
    //to create older members
    Member(String name, LocalDate birthday, Boolean membership, LocalDate joinedDate){
        this.name = name;
        this.birthday = birthday;
        this.payments = new ArrayList<>();
        this.membership = membership;
        this.joinedDate = joinedDate;
        this.quota = calQuota();
    }
    public int getAge(){
        return Period.between(birthday, LocalDate.now()).getYears();
    }
    public void createPayment(LocalDate paymentDate, double amount){
        payments.add(new Payment(paymentDate, amount, getName()));
    }
    public void addPayment(Payment payment){
        payments.add(payment);
    }
    public String paymentsToString(){
        StringBuilder paymentsString = new StringBuilder();
        for (Payment p: payments){
            paymentsString.append(p).append("\n");
        }
        return paymentsString.toString();
    }
    @Override
    public String toString() {
        return name + ", " +  this.getAge();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
         this.birthday = birthday;
    }
    public ArrayList<Payment> getPayments() {
        return payments;
    }
    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }
    public Boolean isPaymentStatus() {
        return paymentStatus;

    }
    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public Boolean isMembership() {
        return membership;
    }
    public void setMembership(Boolean membership) {
        this.membership = membership;
    }
    public LocalDate getJoinedDate() {
        return joinedDate;
    }
    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }
    public double getQuota() {return quota;}
    public void setQuota(double quota) {this.quota = quota;}


}
