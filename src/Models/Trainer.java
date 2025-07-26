package Models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Trainer extends Swimmer {
    public Trainer(String name, LocalDate birthday, Boolean membership){
        this.name = name;
        this.birthday = birthday;
        this.payments = new ArrayList<>();
        this.membership = membership;
        this.joinedDate = LocalDate.now();
    }
}
