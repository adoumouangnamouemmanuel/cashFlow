public class Admin extends User {
    private String role;

    public Admin(String userID, String name, String email, String phoneNumber, String country, String password, String role) {
        super(userID, name, email, phoneNumber, country, password);
        this.role = role;
    }

    public void approveTransaction(Transaction transaction) {
        transaction.setStatus("Approved");
        System.out.println("Transaction " + transaction.getTransactionID() + " approved.");
    }

    public void setExchangeRate(String fromCurrency, String toCurrency, double rate) {
        System.out.println("Exchange rate set: 1 " + fromCurrency + " = " + rate + " " + toCurrency);
    }

    public void generateReport(String type) {
        System.out.println("Generating report: " + type);
    }
}