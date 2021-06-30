import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private MyLinkedList<Transaction>  first;
    private MyLinkedList<Transaction>  last;
    int         size;

    public TransactionsLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public void addTransaction(Transaction transactionToAdd) {
        if (size == 0) {
            first = last = new MyLinkedList<Transaction>(transactionToAdd);
        } else {
            last.next = new MyLinkedList<Transaction>(transactionToAdd, null, last);
            last = last.next;
        }
        ++size;
    }

    @Override
    public MyLinkedList<Transaction> removeTransactionByUUID(UUID uuidToDelete) throws TransactionNotFoundException {
        MyLinkedList<Transaction> tmp = first;

        for (int i = 0; i < size; ++i) {
            if (tmp.getValue_type().getIdentifier().equals(uuidToDelete)) {
                if (tmp == first) {
                    first = tmp.next;
                }
                if (tmp.next != null) {
                    tmp.next.previous = tmp.previous;
                }
                if (tmp.previous != null) {
                    tmp.previous.next = tmp.next;
                }
                --size;
                return tmp;
            }
            tmp = tmp.next;
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] transformIntoArray() {
        Transaction arrayTransaction[] = new Transaction[size];
        MyLinkedList<Transaction> tmp = first;

        for(int i = 0; i < size; ++i) {
            arrayTransaction[i] = tmp.getValue_type();
            tmp = tmp.next;
        }
        return arrayTransaction;
    }

    public MyLinkedList<Transaction> begin() {
        return first;
    }

    public MyLinkedList<Transaction> end() {
        return last;
    }

    public int getSize() {
        return size;
    }
}
