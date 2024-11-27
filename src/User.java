import java.util.ArrayList;
import java.util.List;

public class User implements Notifiable, Localizable {
    private String userID;
    private String name;
    private String email;
    private String phoneNumber;
    private String country;
    private String languagePreference;
    private double balance;
    private String password;
    private List<Transaction> transactionHistory;

    public User(String userID, String name, String email, String phoneNumber, String country, String password) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.languagePreference = "English";
        this.balance = 500.0;
        this.password = password;
        this.transactionHistory = new ArrayList<>();
    }

    // Getters and setters
    public String getUserID() { return userID; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getCountry() { return country; }
    public String getLanguagePreference() { return languagePreference; }
    public double getBalance() { return balance; }

    public void setLanguagePreference(String language) {
        this.languagePreference = language;
        System.out.println("Language preference set to: " + language);
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public void deductBalance(double amount) {
        this.balance -= amount;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void addTransaction(Transaction transaction) {
        this.transactionHistory.add(transaction);
    }

    public List<Transaction> getTransactionHistory() {
        return this.transactionHistory;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Notification for " + name + ": " + message);
    }

    @Override
    public String getLocalizedString(String key) {
        // This would be implemented with a proper localization system
        return key;
    }
}