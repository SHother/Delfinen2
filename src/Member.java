import java.time.*;
public class Member {
    String name;
    LocalDate birthday;
    Payment[] payments;
    Boolean paymentStatus;
    Boolean membership;
    LocalDate joinedDate;


    public float calcualteQuota(){
        if (membership){
        }
        return 0;
    }
    Member(){
    }

    //to create a new member
    Member(String name, LocalDate birthday, Payment[] payments, Boolean membership){
        this.name = name;
        this.birthday = birthday;
        this.payments = payments;
        this.membership = membership;
        joinedDate = LocalDate.now();
    }
    //to create older members
    Member(String name, LocalDate birthday, Payment[] payments, Boolean membership, LocalDate joinedDate){
        this.name = name;
        this.birthday = birthday;
        this.payments = payments;
        this.membership = membership;
        this.joinedDate = joinedDate;
    }
    public int getAge(){
        return Period.between(birthday, LocalDate.now()).getYears();
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
    public Payment[] getPayments() {
        return payments;
    }
    public void setPayments(Payment[] payments) {
        this.payments = payments;
    }
    public Boolean getPaymentStatus() {
        return paymentStatus;

    }
    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public Boolean getMembership() {
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



}
