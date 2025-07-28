package Models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Trainer extends Swimmer {

    public Trainer(String name, LocalDate birthday, Boolean membership){
        super(name, birthday, membership);
        this.payments = new ArrayList<>();
        this.joinedDate = LocalDate.now();
    }
}
