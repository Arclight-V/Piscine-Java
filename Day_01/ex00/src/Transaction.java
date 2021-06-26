
import java.util.UUID;
enum Category {debit, credit};

public class Transaction {
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

        // Установить проверки.
        TransferAmount = transferAmount;
    }

    public UUID getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(UUID identifier) {
        Identifier = identifier;
    }

    public String getRecipient() {
        return Recipient;
    }

    public void setRecipient(String recipient) {
        Recipient = recipient;
    }

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
        TransferAmount = transferAmount;
    }
}
