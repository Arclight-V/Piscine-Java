import java.util.LinkedList;
import java.util.ListIterator;
import java.util.UUID;

public class Program {
    public static void main(String []argv) {
        Menu menu =  new Menu();

        if (argv.length > 0 && argv[0].equals("--profile=dev")) {
                menu.menuDevFunc();
        } else {
            menu.menuBaseFunc();
        }
    }
}

