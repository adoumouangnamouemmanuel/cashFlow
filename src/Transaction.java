import java.util.Date;
import java.util.UUID;
import java.text.SimpleDateFormat;

public class Transaction implements Transactable, Localizable {
    private String transactionID;
    private String senderID;
    private String receiverID;
    private double amount;
    private Date date;
    private String status;

    public Transaction(String senderID, String receiverID, double amount) {
        this.transactionID = UUID.randomUUID().toString();
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.amount = amount;
        this.date = new Date();
        this.status = "Pending";
    }

    @Override
    public void processTransaction() {
        // This would be implemented with actual transaction processing logic
        System.out.println("Processing transaction: " + transactionID);
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getLocalizedString(String key) {
        // This would be implemented with a proper localization system
        return key;
    }

    // Getters
    public String getTransactionID() { return transactionID; }
    public double getAmount() { return amount; }
    public String getSenderID() { return senderID; }
    public String getReceiverID() { return receiverID; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return String.format("Transaction ID: %s, From: %s, To: %s, Amount: %.2f, Date: %s, Status: %s",
                transactionID, senderID, receiverID, amount, sdf.format(date), status);
    }
}