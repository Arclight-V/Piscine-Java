
import java.util.UUID;

enum Category {debit, credit};

public class Transaction {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    private UUID Identifier;
    private String Recipient;
    private String Sender;
    private Category TransferCategory;
    private int TransferAmount;

    public Transaction(UUID identifier, String recipient, String sender, Category transferCategory, int transferAmount) {
        Identifier = identifier;
        Recipient = recipient;
        Sender = sender;
        TransferCategory = transferCategory;
//        if (TransferCategory == Category.debit && transferAmount < 0) {
//            TransferAmount = 0;
//        }
//        else if (TransferCategory == Category.credit && transferAmount > 0) {
//            TransferAmount = 0;
//        } else {
//            TransferAmount = transferAmount;
//        }
        if (TransferCategory == Category.credit) {
            TransferAmount = transferAmount * -1;
        } else {
            TransferAmount = transferAmount;
        }
    }

    public Transaction(String recipient, String sender, Category transferCategory, int transferAmount) {
        Identifier = UUID.randomUUID();
        Recipient = recipient;
        Sender = sender;
        TransferCategory = transferCategory;
//        if (TransferCategory == Category.debit && transferAmount < 0) {
//            TransferAmount = 0;
//        }
//        else if (TransferCategory == Category.credit && transferAmount > 0) {
//            TransferAmount = 0;
//        } else {
//            TransferAmount = transferAmount;
//        }
        if (TransferCategory == Category.credit) {
            TransferAmount = transferAmount * -1;
        } else {
            TransferAmount = transferAmount;
        }
    }

    public UUID getIdentifier() { return Identifier; }

    public void setIdentifier(UUID identifier) {
        Identifier = identifier;
    }

    public String getRecipient() {
        return Recipient;
    }

    public void setRecipient(String recipient) { Recipient = recipient; }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public Category getTransferCategory() {
        return TransferCategory;
    }

    public void setTransferCategory(Category transferCategory) {
        TransferCategory = transferCategory;
    }

    public int getTransferAmount() {
        return TransferAmount;
    }

    public void setTransferAmount(int transferAmount) {
//        if (TransferCategory == Category.credit && transferAmount > 0) {
//            TransferAmount = 0;
//        }
//        else if (TransferCategory == Category.debit && transferAmount < 0) {
//            TransferAmount = 0;
//        } else {
//            TransferAmount = transferAmount;
//        }
        if (TransferCategory == Category.credit) {
            TransferAmount = transferAmount * -1;
        } else {
            TransferAmount = transferAmount;
        }
    }

    public void printTransatction() {
        if (TransferCategory == Category.credit) {
            System.out.println(ANSI_RED + "Transaction: \n" + ANSI_RESET + Sender + " -> " + Recipient + ", " + TransferAmount + ',' + " OUTCOME, " + "transaction ID " + Identifier + "\n");
        } else {
            System.out.println(ANSI_RED + "Transaction: \n" + ANSI_RESET + Sender + " -> " + Recipient + ", +" + TransferAmount + ',' + " INCOME, " + "transaction ID " + Identifier + "\n");
        }
    }
}
