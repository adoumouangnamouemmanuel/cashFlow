import java.util.HashMap;
import java.util.Map;

public class BankAccountLinking {
    private Map<String, BankAccount> linkedAccounts;

    public BankAccountLinking() {
        this.linkedAccounts = new HashMap<>();
    }

    public void linkBankAccount(User user, BankAccount bankAccount) {
        linkedAccounts.put(user.getUserID(), bankAccount);
        System.out.println("Bank account linked successfully for user: " + user.getName());
    }

    public void unlinkBankAccount(User user) {
        linkedAccounts.remove(user.getUserID());
        System.out.println("Bank account unlinked for user: " + user.getName());
    }

    public double calculateFee(double amount) {
        // Simple fee calculation, can be made more complex
        return amount * 0.01; // 1% fee
    }
}