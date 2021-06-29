import java.util.LinkedList;
import java.util.ListIterator;
import java.util.UUID;

public class Program {
    public static void main(String []argv) {

        User userFirst = new User("John", 800);
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

        System.out.println("\nuserFirst TransactionList:\nfirst -> last");
        MyLinkedList<Transaction> tmp = userFirst.getTransactionsList().begin();

        while (tmp != null) {
            tmp.getValue_type().printTransatction();
            tmp = tmp.next;
        }

        System.out.println("\nuserFirst TransactionList:\nlast -> first");
        tmp = userFirst.getTransactionsList().end();
        while (tmp != null) {
            tmp.getValue_type().printTransatction();
            tmp = tmp.previous;
        }

        System.out.println("\nuserSecond TransactionList:\nfirst -> last");
        tmp = userSecond.getTransactionsList().begin();
        while (tmp != null) {
            tmp.getValue_type().printTransatction();
            tmp = tmp.next;
        }

        System.out.println("\nuserSecond TransactionList:\nlast -> first");
        tmp = userSecond.getTransactionsList().end();
        while (tmp != null) {
            tmp.getValue_type().printTransatction();
            tmp = tmp.previous;
        }


        Transaction transactionArrayUserFirst[] = userFirst.getTransactionsList().transformIntoArray();
        System.out.println("\nuserFirst transformIntoArray:");
        for(int i = 0; i < transactionArrayUserFirst.length; ++i) {
            transactionArrayUserFirst[i].printTransatction();
        }

        Transaction transactionArrayUserSecond[] = userSecond.getTransactionsList().transformIntoArray();
        System.out.println("\nuserSecond transformIntoArray:");
        for(int i = 0; i < transactionArrayUserSecond.length; ++i) {
            transactionArrayUserSecond[i].printTransatction();
        }

        System.out.println("\nuserFirst TransactionList after removeTransactionByUUID:");

        tmp = userFirst.getTransactionsList().begin();
        userFirst.deleteTransaction(tmp.getValue_type().getIdentifier());

        tmp = userFirst.getTransactionsList().begin();
        while (tmp != null) {
            tmp.getValue_type().printTransatction();
            tmp = tmp.next;
        }

        System.out.println("\nuserFirst TransactionList after removeTransactionByUUID: exception");
        userFirst.getTransactionsList().removeTransactionByUUID(UUID.randomUUID());
    }
}
