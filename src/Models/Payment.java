package Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Payment {
    int memberId;
    LocalDate paymentDate;
    double amount;

    public Payment(LocalDate paymentDate, double amount, int memberId) {
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.memberId = memberId;
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
    public int getMemberId() {return memberId;}
    public void setMemberId(int memberName) {this.memberId = memberId;}
}
