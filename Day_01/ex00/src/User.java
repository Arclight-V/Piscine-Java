public class User {
    private int Identifier;
    private String Name;
    private int Balance;

    public User(int identifier, String name, int balance) {
        Identifier = identifier;
        Name = name;
        Balance = balance > 0 ? balance : 0;
    }

    public int getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(int identifier) {
        Identifier = identifier;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        Balance = balance > 0 ? balance : 0;
    }
}
