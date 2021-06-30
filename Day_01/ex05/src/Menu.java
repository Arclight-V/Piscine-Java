import java.util.Scanner;
import java.util.UUID;

public class Menu {
    TransactionService transactionService;
    Scanner in;
    int menuСommand = 0;
    String menuDev =   "1. Add a user\n" +
            "2. View user balances\n" +
            "3. Perform a transfer\n" +
            "4. View all transactions for a specific user\n" +
            "5. DEV - remove a transfer by ID\n" +
            "6. DEV - check transfer validity\n" +
            "7. Finish execution\n";

    String menuBase =   "1. Add a user\n" +
            "2. View user balances\n" +
            "3. Perform a transfer\n" +
            "4. View all transactions for a specific user\n" +
            "5. Finish execution\n";
    String str;


    public Menu() {
        transactionService = new TransactionService();
        in = new Scanner(System.in);
    }

    private void addedUser() {
        int id = 0;

        System.out.println("Enter a user name and a balance\n");
        str = in.next();
        if (in.hasNextInt()) {
            id = in.nextInt();
            User userToAdd = new User(str, id);
            transactionService.addUser(userToAdd);
        } else {
            System.out.println("ID must be a number\n");
            return;
        }
    }

    private void viewUserBalances() {
        int id = 0;
        System.out.println("Enter a user ID\n");
        if (in.hasNextInt()) {
            id = in.nextInt();
            System.out.println(transactionService.retrieverUserBalans(id) + '\n');
        } else {
            System.out.println("ID must be a number\n");
            return;
        }
    }

    private void perfomTransfer() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount\n");
        int sender = 0, recepient = 0, amount = 0;
        if (in.hasNextInt()) {
            sender = in.nextInt();
            if (in.hasNextInt()) {
                recepient = in.nextInt();
                if (in.hasNextInt()) {
                    amount = in.nextInt();
                    transactionService.performingTransferTransaction(recepient, sender, amount);
                } else {
                    System.out.println("Amount must be a number\n");
                    return;
                }
            } else {
                System.out.println("Recipient must be a number\n");
                return;
            }
        } else {
            System.out.println("Sender must be a number\n");
            return;
        }
    }

    private void viewAllTransactionsForASpecificUser() {
        System.out.println("Enter a user ID\n");
        int Id = 0;
        if (in.hasNextInt()) {
            Id = in.nextInt();
            Transaction[] transactions = transactionService.transfersArray(Id);
            for (int i = 0; i < transactions.length; ++i) {
                transactions[i].printTransatction();
            }
        } else {
            System.out.println("ID must be a number\n");
            return;
        }
    }

    private void removeATransferByID() {
        System.out.println("Enter a user ID and a transfer ID\n");
        int id = 0;
        String str;
        if (in.hasNextInt()) {
            id = in.nextInt();
            str =  in.next();
            UUID uuid = UUID.fromString(str);
            transactionService.removeTransaction(id, uuid);
        } else {
            System.out.println("ID must be a number\n");
            return;
        }
    }

    public void checkTransferValidity() {
        Transaction[] transactions = transactionService.unpairedTransactionArray();
        for (int i = 0; i < transactions.length; ++i) {
            transactions[i].printTransatction();
            System.out.println("unacknowledged transfer\n");
        }
    }

    public void menuBaseFunc() {
        while (menuСommand != 5) {
            System.out.println(menuBase);
//            str = in.next();
            try {
                if (in.hasNextInt()) {
                    menuСommand = in.nextInt();
                    if (menuСommand == 1) {
                        addedUser();
                    }
                    else if (menuСommand == 2) {
                        viewUserBalances();
                    }
                    else if (menuСommand == 3) {
                        perfomTransfer();
                    }
                    else if (menuСommand == 4) {
                        viewAllTransactionsForASpecificUser();
                    }
                    else if (menuСommand == 5) {
                        menuСommand = 5;
                    } else {
                        System.out.println("Invalid command number\n");
                    }
                }
            }
            catch (RuntimeException exception) {
                exception.getMessage();
            }
        }
    }

    public void menuDevFunc() {
        while (menuСommand != 7) {
            System.out.println(menuDev);
            str = in.next();
            try {
                if (in.hasNextInt()) {
                    menuСommand = in.nextInt();
                    if (menuСommand == 1) {
                        addedUser();
                    }
                    else if (menuСommand == 2) {
                        viewUserBalances();
                    }
                    else if (menuСommand == 3) {
                        perfomTransfer();
                    }
                    else if (menuСommand == 4) {
                        viewAllTransactionsForASpecificUser();
                    }
                    else if (menuСommand == 5) {
                        removeATransferByID();
                    }
                    else if (menuСommand == 6 ) {
                        checkTransferValidity();
                    }
                    else if (menuСommand == 7) {
                        menuСommand = 7;
                    } else {
                        System.out.println("Invalid command number\n");
                    }
                }
            }
            catch (RuntimeException exception) {
                exception.getMessage();
            }
        }
    }

    public void menuBase() {}
}
