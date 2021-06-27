public class User {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private int Identifier;
    private String Name;
    private int Balance;

    public User(int identifier, String name, int balance) {
        Identifier = identifier;
        Name = name;
        Balance = balance > 0 ? balance : 0;
    }

    public int getIdentifier() { return Identifier; }

    public void setIdentifier(int identifier) { Identifier = identifier; }

    public String getName() { return Name; }

    public void setName(String name) { Name = name; }

    public int getBalance() { return Balance; }

    public void setBalance(int balance) { Balance = balance; }

    public void printUser() {
        System.out.print(ANSI_BLUE + "Profile:\n" + ANSI_RESET +
                        "Identifier: " + Identifier + '\n' +
                        "Name: " + Name + '\n' +
                        "Balance: " +  Balance + "\n\n");
    }
}
