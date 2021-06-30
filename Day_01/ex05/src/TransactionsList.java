import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transactionToAdd);
    MyLinkedList<Transaction> removeTransactionByUUID(UUID uuidToDelete) throws TransactionNotFoundException;
    Transaction[] transformIntoArray();


}
