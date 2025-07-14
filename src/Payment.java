import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Payment {
    String memberName;
    LocalDate paymentDate;
    double amount;

    Payment(LocalDate paymentDate, double amount, String memberName) {
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy dd/MM");
        return "Date: " + paymentDate + "\t" + amount + "kr";
    }

    public LocalDate getPaymentDate() {return paymentDate;}
    public void setPaymentDate(LocalDate paymentDate) {this.paymentDate = paymentDate;}
    public double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount = amount;}
    public String getMemberName() {return memberName;}
    public void setMemberName(String memberName) {this.memberName = memberName;}
}
