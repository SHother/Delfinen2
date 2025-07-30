package UI;

import Storage.LocalStorage;
import java.util.Scanner;

interface MenuI {
    final Scanner scanner = null;
    final LocalStorage storage = null;
    public int showMenu();
}
