import java.util.LinkedList;
import java.util.ListIterator;
import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    LinkedList<Transaction> transactionList;


    public TransactionsLinkedList() {
        transactionList = new LinkedList<Transaction>();
    }

    @Override
    public void addTransaction(Transaction transactionToAdd) {
        transactionList.addLast(transactionToAdd);

    }

    @Override
    public void removeTransactionByUUID(UUID uuidToDelete) throws TransactionNotFoundException {
        ListIterator<Transaction> itBegin = transactionList.listIterator();
        while (itBegin.hasNext()) {
            Transaction tmp = itBegin.next();
            if (tmp.getIdentifier().equals(uuidToDelete)) {
                itBegin.remove();
                return;
            }
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] transformIntoArray() {
        Transaction arrayTransaction[] = new Transaction[transactionList.size()];
        ListIterator<Transaction> itBegin = transactionList.listIterator();

        for(int i = 0; i < transactionList.size(); ++i) {
            Transaction tmp = itBegin.next();
            arrayTransaction[i] = tmp;
        }
        return arrayTransaction;
    }


    public LinkedList<Transaction> getTransactionList() {
        return transactionList;
    }
}
