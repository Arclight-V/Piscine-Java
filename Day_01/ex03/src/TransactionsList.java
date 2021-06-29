import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transactionToAdd);
    void removeTransactionByUUID(UUID uuidToDelete) throws TransactionNotFoundException;
    Transaction[] transformIntoArray();


}
