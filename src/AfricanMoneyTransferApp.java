import java.util.List;
import java.util.Scanner;

public class AfricanMoneyTransferApp {
    private static TransferService transferService = new TransferService();
    private static LanguageManager languageManager = new LanguageManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadUsers();
        while (true) {
            displayMainMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    User currentUser = login();
                    if (currentUser != null) {
                        showMenu(currentUser);
                    }
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Thank you for using African Money Transfer App. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n===== African Money Transfer App =====");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void loadUsers() {
        List<User> users = FileUtil.loadUsers();
        if (users.isEmpty()) {
            System.out.println("No existing users found. Starting with an empty user list.");
        } else {
            for (User user : users) {
                transferService.addUser(user);
            }
            System.out.println("Loaded " + users.size() + " user(s) from file.");
        }
    }

    private static void register() {
        scanner.nextLine(); // Consume newline
        System.out.println("\n===== User Registration =====");
        System.out.print("Enter user ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter country: ");
        String country = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User newUser = new User(userID, name, email, phoneNumber, country, password);
        transferService.addUser(newUser);
        List<User> users = FileUtil.loadUsers();
        users.add(newUser);
        FileUtil.saveUsers(users);
        System.out.println("Registration successful! Your initial balance is 500.");
    }

    private static User login() {
        scanner.nextLine(); // Consume newline
        System.out.println("\n===== User Login =====");
        System.out.print("Enter your user ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = transferService.getUser(userID);
        if (user != null && user.checkPassword(password)) {
            System.out.println("Login successful!");
            return user;
        } else {
            System.out.println("Invalid credentials. Please try again.");
            return null;
        }
    }

    private static void showMenu(User user) {
        while (true) {
            System.out.println("\n===== " + languageManager.getTranslation("WELCOME", user.getLanguagePreference()) + " =====");
            System.out.println("1. " + languageManager.getTranslation("MENU_CHECK_BALANCE", user.getLanguagePreference()));
            System.out.println("2. " + languageManager.getTranslation("MENU_SEND_MONEY", user.getLanguagePreference()));
            System.out.println("3. " + languageManager.getTranslation("MENU_TRANSACTION_HISTORY", user.getLanguagePreference()));
            System.out.println("4. " + languageManager.getTranslation("MENU_CHANGE_LANGUAGE", user.getLanguagePreference()));
            System.out.println("5. " + languageManager.getTranslation("MENU_EXIT", user.getLanguagePreference()));
            System.out.print("Enter your choice: ");

            int choice = getUserChoice();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    checkBalance(user);
                    break;
                case 2:
                    sendMoney(user);
                    break;
                case 3:
                    transferService.printTransactionHistory(user.getUserID());
                    break;
                case 4:
                    changeLanguage(user);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void checkBalance(User user) {
        System.out.println("\n===== Balance Check =====");
        System.out.println(languageManager.getTranslation("BALANCE", user.getLanguagePreference()) +
                ": " + user.getBalance());
    }

    private static void sendMoney(User sender) {
        System.out.println("\n===== Send Money =====");
        System.out.print("Enter recipient's user ID: ");
        String recipientID = scanner.nextLine();
        System.out.print("Enter amount to send: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        boolean success = transferService.sendMoney(sender.getUserID(), recipientID, amount);
        if (success) {
            System.out.println("Transaction successful!");
        }
    }

    private static void changeLanguage(User user) {
        System.out.println("\n===== Change Language =====");
        System.out.print("Select language (English/French/Spanish): ");
        String language = scanner.nextLine();
        user.setLanguagePreference(language);
    }
}