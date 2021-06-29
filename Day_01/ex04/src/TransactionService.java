import java.util.UUID;

public class TransactionService {
    UsersArrayList usersList;

    public TransactionService() {
    }

    void addUser(User userToAdd) {
        usersList.addUser(userToAdd);
    }

    int retrieverUserBalans(int userID) throws UserNotFoundException {
        User user = usersList.retrieveUserByID(userID);
        return user.getBalance();
    }

    void performingTransferTransaction(int recipientID, int senderID, int transferAmount) throws UserNotFoundException, IllegalTransactionException {
        User recipient = usersList.retrieveUserByID(recipientID);
        User sender = usersList.retrieveUserByID(senderID);

        if (sender.getBalance() - transferAmount < 0) {throw new IllegalTransactionException(); }

        Transaction debit = new Transaction(recipient.getName(), sender.getName(), Category.debit, transferAmount);
        Transaction credit = new Transaction(debit.getIdentifier(), sender.getName(), recipient.getName(), Category.credit, transferAmount);

        recipient.addTransaction(debit);
        sender.addTransaction(credit);
    }

    Transaction[] transfersArray(int userID) throws UserNotFoundException{
        User user = usersList.retrieveUserByID(userID);
        return user.getTransactionsList().transformIntoArray();
    }

    void removeTransaction(int userID, UUID transactID) throws UserNotFoundException, TransactionNotFoundException {
        User user = usersList.retrieveUserByID(userID);
        user.deleteTransaction(transactID);
    }
}
