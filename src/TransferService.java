import java.util.*;

public class TransferService {
    private Map<String, User> users;
    private List<Transaction> transactions;

    public TransferService() {
        this.users = new HashMap<>();
        this.transactions = new ArrayList<>();
    }

    public void addUser(User user) {
        users.put(user.getUserID(), user);
    }

    public boolean sendMoney(String senderID, String receiverID, double amount) {
        User sender = users.get(senderID);
        User receiver = users.get(receiverID);

        if (sender == null || receiver == null) {
            System.out.println("Invalid sender or receiver ID");
            return false;
        }

        if (sender.getBalance() < amount) {
            System.out.println("Insufficient balance");
            return false;
        }

        Transaction transaction = new Transaction(senderID, receiverID, amount);
        sender.deductBalance(amount);
        receiver.addBalance(amount);
        transaction.setStatus("Completed");
        transactions.add(transaction);
        sender.addTransaction(transaction);
        receiver.addTransaction(transaction);

        System.out.println("Transaction completed successfully");
        System.out.println(transaction);
        return true;
    }

    public void printUserBalance(String userID) {
        User user = users.get(userID);
        if (user != null) {
            System.out.printf("User %s balance: %.2f%n", user.getName(), user.getBalance());
        } else {
            System.out.println("User not found");
        }
    }

    public void printTransactionHistory(String userID) {
        User user = users.get(userID);
        if (user != null) {
            System.out.println("Transaction History for " + user.getName() + ":");
            for (Transaction transaction : user.getTransactionHistory()) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("User not found");
        }
    }

    public User getUser(String userID) {
        return users.get(userID);
    }
}