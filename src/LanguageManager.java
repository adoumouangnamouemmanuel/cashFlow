import java.util.*;

public class LanguageManager {
    private Map<String, Map<String, String>> translations;

    public LanguageManager() {
        translations = new HashMap<>();
        initializeTranslations();
    }

    private void initializeTranslations() {
        // English translations
        Map<String, String> englishTranslations = new HashMap<>();
        englishTranslations.put("WELCOME", "Welcome to the African Money Transfer App");
        englishTranslations.put("BALANCE", "Your balance is");
        englishTranslations.put("MENU_CHECK_BALANCE", "Check Balance");
        englishTranslations.put("MENU_SEND_MONEY", "Send Money");
        englishTranslations.put("MENU_TRANSACTION_HISTORY", "Transaction History");
        englishTranslations.put("MENU_CHANGE_LANGUAGE", "Change Language");
        englishTranslations.put("MENU_EXIT", "Exit");
        translations.put("English", englishTranslations);

        // French translations
        Map<String, String> frenchTranslations = new HashMap<>();
        frenchTranslations.put("WELCOME", "Bienvenue dans l'application de transfert d'argent africaine");
        frenchTranslations.put("BALANCE", "Votre solde est de");
        frenchTranslations.put("MENU_CHECK_BALANCE", "Vérifier le solde");
        frenchTranslations.put("MENU_SEND_MONEY", "Envoyer de l'argent");
        frenchTranslations.put("MENU_TRANSACTION_HISTORY", "Historique des transactions");
        frenchTranslations.put("MENU_CHANGE_LANGUAGE", "Changer de langue");
        frenchTranslations.put("MENU_EXIT", "Quitter");
        translations.put("French", frenchTranslations);

        // Spanish translations
        Map<String, String> spanishTranslations = new HashMap<>();
        spanishTranslations.put("WELCOME", "Bienvenido a la aplicación de transferencia de dinero africana");
        spanishTranslations.put("BALANCE", "Tu saldo es");
        spanishTranslations.put("MENU_CHECK_BALANCE", "Consultar saldo");
        spanishTranslations.put("MENU_SEND_MONEY", "Enviar dinero");
        spanishTranslations.put("MENU_TRANSACTION_HISTORY", "Historial de transacciones");
        spanishTranslations.put("MENU_CHANGE_LANGUAGE", "Cambiar idioma");
        spanishTranslations.put("MENU_EXIT", "Salir");
        translations.put("Spanish", spanishTranslations);
    }

    public String getTranslation(String key, String language) {
        Map<String, String> languageTranslations = translations.get(language);
        if (languageTranslations != null) {
            return languageTranslations.getOrDefault(key, key);
        }
        return key;
    }
}