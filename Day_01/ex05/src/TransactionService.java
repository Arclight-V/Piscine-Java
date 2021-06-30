import java.util.UUID;

public class TransactionService {
    private UsersArrayList usersList;
    private TransactionsUnpairedTransactions unpairedTransactions;

    public TransactionService() {
        usersList = new UsersArrayList();
        unpairedTransactions = new TransactionsUnpairedTransactions();
    }

    public void addUser(User userToAdd) {
        usersList.addUser(userToAdd);
        System.out.println("User whith id = " + userToAdd.getIdentifier() + " is added\n");
    }

    public int retrieverUserBalans(int userID) throws UserNotFoundException {
        User user = usersList.retrieveUserByID(userID);
        System.out.print(user.getName() + " - ");
        return user.getBalance();
    }

    public void performingTransferTransaction(int recipientID, int senderID, int transferAmount) throws UserNotFoundException, IllegalTransactionException {
        User recipient = usersList.retrieveUserByID(recipientID);
        User sender = usersList.retrieveUserByID(senderID);

        if (sender.getBalance() - transferAmount < 0) {throw new IllegalTransactionException(); }

        Transaction debit = new Transaction(recipient.getName(), sender.getName(), Category.debit, transferAmount);
        Transaction credit = new Transaction(debit.getIdentifier(), sender.getName(), recipient.getName(), Category.credit, transferAmount);

        recipient.addTransaction(debit);
        sender.addTransaction(credit);

        recipient.setBalance(recipient.getBalance() + debit.getTransferAmount());
        sender.setBalance(sender.getBalance() + credit.getTransferAmount());

        System.out.println("The transfer is complited\n");
    }

    public Transaction[] transfersArray(int userID) throws UserNotFoundException{
        User user = usersList.retrieveUserByID(userID);
        return user.getTransactionsList().transformIntoArray();
    }

    public void removeTransaction(int userID, UUID transactID) throws UserNotFoundException, TransactionNotFoundException {
        User user = usersList.retrieveUserByID(userID);
        MyLinkedList<Transaction> transaction = user.deleteTransaction(transactID);
        unpairedTransactions.addTransaction(transaction.getValue_type());
        System.out.println("Transfer \n");
        transaction.getValue_type().printTransatction();
        System.out.println("removed \n");
    }

    public Transaction[] unpairedTransactionArray() {
        return unpairedTransactions.transformIntoArray();
    }
}
