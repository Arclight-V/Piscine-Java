import java.util.LinkedList;
import java.util.ListIterator;
import java.util.UUID;

public class Program {
    public static void main(String []argv) {

        User userFirst = new User("John", 500);
        userFirst.printUser();
        User userSecond = new User( "Mike", 0);
        userSecond.printUser();

        int transferAmount = 500;
        for (int i = 100; i < transferAmount; i += 100) {
            Transaction transactionCredit = new Transaction(userSecond.getName(), userFirst.getName(), Category.credit, i);
            userFirst.getTransactionsList().addTransaction(transactionCredit);
            userFirst.setBalance(userFirst.getBalance() + transactionCredit.getTransferAmount());

            Transaction transactionDebit = new Transaction(userFirst.getName(), userSecond.getName(), Category.debit, i);
            userSecond.getTransactionsList().addTransaction(transactionDebit);
            userSecond.setBalance(userFirst.getBalance() + transactionDebit.getTransferAmount());
        }

        System.out.println("userFirst TransactionList:");
        Transaction tmp = null;

        ListIterator<Transaction> itBegin = userFirst.getTransactionsList().transactionList.listIterator();
        while(itBegin.hasNext()) {
            tmp = itBegin.next();
            tmp.printTransatction();
        }

        System.out.println("userSecond TransactionList:");
        itBegin = userSecond.getTransactionsList().transactionList.listIterator();
        while(itBegin.hasNext()) {
            tmp = itBegin.next();
            tmp.printTransatction();
        }


        Transaction transactionArrayUserFirst[] = userFirst.getTransactionsList().transformIntoArray();
        System.out.println("userFirst transformIntoArray:");
        for(int i = 0; i < transactionArrayUserFirst.length; ++i) {
            transactionArrayUserFirst[i].printTransatction();
        }

        Transaction transactionArrayUserSecond[] = userSecond.getTransactionsList().transformIntoArray();
        System.out.println("userSecond transformIntoArray:");
        for(int i = 0; i < transactionArrayUserSecond.length; ++i) {
            transactionArrayUserSecond[i].printTransatction();
        }

        System.out.println("userFirst TransactionList after removeTransactionByUUID:");
        userFirst.getTransactionsList().removeTransactionByUUID(userFirst.getTransactionsList().getTransactionList().getLast().getIdentifier());
        itBegin = userFirst.getTransactionsList().transactionList.listIterator();
        while(itBegin.hasNext()) {
            tmp = itBegin.next();
            tmp.printTransatction();
        }

        System.out.println("userFirst TransactionList after removeTransactionByUUID: exception");
        userFirst.getTransactionsList().removeTransactionByUUID(UUID.randomUUID());
    }
}
