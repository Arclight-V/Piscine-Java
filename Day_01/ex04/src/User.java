import java.util.UUID;

public class User {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private final int Identifier;
    private String Name;
    private int Balance;
    private TransactionsLinkedList transactionsList;

    public User(String name, int balance) {
        Identifier = UserldsGeneration.getInstance().generateId();
        Name = name;
        Balance = balance > 0 ? balance : 0;
        transactionsList = new TransactionsLinkedList();
    }

    public int getIdentifier() { return Identifier; }

    public String getName() { return Name; }

    public void setName(String name) { Name = name; }

    public int getBalance() { return Balance; }

    public void setBalance(int balance) { Balance = balance; }

    public TransactionsLinkedList getTransactionsList() {
        return transactionsList;
    }

    public void addTransaction(Transaction transaction) { transactionsList.addTransaction(transaction);}

    public MyLinkedList<Transaction> deleteTransaction(UUID uuidToDelete) {
        return transactionsList.removeTransactionByUUID(uuidToDelete);
    }

    public void printUser() {
        System.out.print(ANSI_BLUE + "Profile:\n" + ANSI_RESET +
                        "Identifier: " + Identifier + '\n' +
                        "Name: " + Name + '\n' +
                        "Balance: " +  Balance + "\n\n");
    }

    public void printBalance() {
        System.out.print( Name + " - " +  Balance + "\n");
    }

}
