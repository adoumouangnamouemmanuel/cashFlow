import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {
    private static final String USERS_FILE_PATH = "users.json";
    private static final String TRANSACTIONS_FILE_PATH = "transactions.json";
    private static final Gson gson = new Gson();

    public static void saveUsers(List<User> users) {
        saveToFile(USERS_FILE_PATH, users);
    }

    public static List<User> loadUsers() {
        List<User> users = loadFromFile(USERS_FILE_PATH, new TypeToken<ArrayList<User>>(){}.getType());
        return users != null ? users : new ArrayList<>();
    }

    public static void saveTransactions(Map<String, List<Transaction>> transactions) {
        saveToFile(TRANSACTIONS_FILE_PATH, transactions);
    }

    public static Map<String, List<Transaction>> loadTransactions() {
        Map<String, List<Transaction>> transactions = loadFromFile(TRANSACTIONS_FILE_PATH, new TypeToken<Map<String, List<Transaction>>>(){}.getType());
        return transactions != null ? transactions : new HashMap<>();
    }

    private static <T> void saveToFile(String filePath, T data) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static <T> T loadFromFile(String filePath, Type type) {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            return null;
        }

        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}