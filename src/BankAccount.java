public class BankAccount {
    private String bankAccountID;
    private String bankName;
    private String accountHolderName;
    private double balance;

    public BankAccount(String bankAccountID, String bankName, String accountHolderName, double initialBalance) {
        this.bankAccountID = bankAccountID;
        this.bankName = bankName;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    public double checkBalance() {
        return balance;
    }

    public void transferToCashflowAccount(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Transferred " + amount + " to Cashflow account.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    // Getters
    public String getBankAccountID() { return bankAccountID; }
    public String getBankName() { return bankName; }
    public String getAccountHolderName() { return accountHolderName; }
}