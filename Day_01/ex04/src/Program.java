import java.util.LinkedList;
import java.util.ListIterator;
import java.util.UUID;

public class Program {
    public static void main(String []argv) throws UserNotFoundException {

        TransactionService service = new TransactionService();

        service.addUser(new User("John", 10000));
        service.addUser(new User( "Mike", 10000));

        for (int i = 100; i < 500; i += 100) {
            service.performingTransferTransaction(1, 2, i);
        }

        int balanceUserFirst = service.retrieverUserBalans(1);
        int balanceUserSecond = service.retrieverUserBalans(2);

        System.out.println("Balance user_1 = " + balanceUserFirst + " Balance user_2 = " + balanceUserSecond + '\n');

        Transaction[] transactions = service.transfersArray(1);
        for(int i = 0; i< transactions.length; ++i) {
            transactions[i].printTransatction();
        }
        service.removeTransaction(2, transactions[1].getIdentifier());

        Transaction[] unpairedTransaction = service.unpairedTransactionArray();

        System.out.println("Unacknowledged transfers:\n");
        for (int i = 0; i < unpairedTransaction.length; ++i) {
            unpairedTransaction[i].printTransatction();
        }

    }
}

