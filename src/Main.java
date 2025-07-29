import LogicHandler.AdminLogic;
import Models.CompetitionSwimmer;
import Storage.LocalStorage;

public class Main {

    public static void main(String[] args) {
        LocalStorage storage = new LocalStorage();
        AdminLogic.mainMenu(storage);

    }
}
