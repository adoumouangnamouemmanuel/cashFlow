import java.util.*;

public class TransferService {
    private Map<String, User> users;
    private Map<String, List<Transaction>> transactionHistory;

    public TransferService() {
        this.users = new HashMap<>();
        this.transactionHistory = FileUtil.loadTransactions();
        if (this.transactionHistory == null) {
            this.transactionHistory = new HashMap<>();
        }
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

        addTransactionToHistory(senderID, transaction);
        addTransactionToHistory(receiverID, transaction);

        FileUtil.saveUsers(new ArrayList<>(users.values()));
        FileUtil.saveTransactions(transactionHistory);

        System.out.println("Transaction completed successfully");
        System.out.println(transaction);
        return true;
    }

    private void addTransactionToHistory(String userID, Transaction transaction) {
        transactionHistory.computeIfAbsent(userID, k -> new ArrayList<>()).add(transaction);
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
        List<Transaction> userTransactions = transactionHistory.get(userID);
        if (userTransactions != null && !userTransactions.isEmpty()) {
            System.out.println("Transaction History for " + users.get(userID).getName() + ":");
            for (Transaction transaction : userTransactions) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("No transaction history found for this user.");
        }
    }

    public User getUser(String userID) {
        return users.get(userID);
    }
}