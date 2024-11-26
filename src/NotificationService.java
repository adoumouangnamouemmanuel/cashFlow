public class NotificationService implements Localizable {
    public void sendNotification(User user, String message) {
        user.sendNotification(message);
    }

    public void notifyTransactionStatus(Transaction transaction) {
        String message = "Transaction " + transaction.getTransactionID() + " status: " + transaction.getStatus();
        System.out.println(message);
    }

    @Override
    public String getLocalizedString(String key) {
        // This would be implemented with a proper localization system
        return key;
    }
}