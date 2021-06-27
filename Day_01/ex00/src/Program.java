public class Program {
    public static void main(String []argv) {
        User userFirst = new User(0, "John", 500);
        userFirst.printUser();
        User userSecond = new User(1, "Mike", 0);
        userSecond.printUser();

        int transferAmount = 500;

        Transaction transaction_00 = new Transaction(userSecond.getName(), userFirst.getName(), Category.credit, transferAmount);
        transaction_00.printTransatction();
        userFirst.setBalance(userFirst.getBalance() + transaction_00.getTransferAmount());
        userFirst.printUser();

        Transaction transaction_01 = new Transaction(userFirst.getName(), userSecond.getName(), Category.debit, transferAmount);
        transaction_01.printTransatction();
        userSecond.setBalance(userSecond.getBalance() + transaction_01.getTransferAmount());
        userSecond.printUser();
    }
}
