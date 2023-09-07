import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       
        Map<String, Double> exchangeRates = createExchangeRates();
        Map<String, String> currencyNames = createCurrencyNames();

        System.out.println("Welcome to the Currency Converter");
        displayAvailableCurrencies(exchangeRates, currencyNames);

        System.out.print("Enter the base currency code: ");
        String baseCurrencyCode = scanner.next().toUpperCase();
        if (!isValidCurrency(baseCurrencyCode, exchangeRates)) {
            System.out.println("Invalid base currency selection.");
            return;
        }

        System.out.print("Enter the target currency code: ");
        String targetCurrencyCode = scanner.next().toUpperCase();
        if (!isValidCurrency(targetCurrencyCode, exchangeRates)) {
            System.out.println("Invalid target currency selection.");
            return;
        }

        System.out.print("Enter the amount to convert from " + currencyNames.get(baseCurrencyCode) + " to " +
                currencyNames.get(targetCurrencyCode) + ": ");
        double amountToConvert;
        while (true) {
            if (scanner.hasNextDouble()) {
                amountToConvert = scanner.nextDouble();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.next(); 
            }
        }

        double convertedAmount = convertCurrency(amountToConvert, baseCurrencyCode, targetCurrencyCode, exchangeRates);

        System.out.println(amountToConvert + " " + currencyNames.get(baseCurrencyCode) + " is equal to " +
                convertedAmount + " " + currencyNames.get(targetCurrencyCode));
        scanner.close();
    }

    private static Map<String, Double> createExchangeRates() {
        Map<String, Double> exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.73);
        exchangeRates.put("INR", 73.0);
        exchangeRates.put("JPY", 110.0);
        exchangeRates.put("CNY", 6.45);
        return exchangeRates;
    }

    private static Map<String, String> createCurrencyNames() {
        Map<String, String> currencyNames = new HashMap<>();
        currencyNames.put("USD", "United States Dollar");
        currencyNames.put("EUR", "Euro");
        currencyNames.put("GBP", "British Pound Sterling");
        currencyNames.put("INR", "Indian Rupee");
        currencyNames.put("JPY", "Japanese Yen");
        currencyNames.put("CNY", "Chinese Yuan");
        return currencyNames;
    }

    private static void displayAvailableCurrencies(Map<String, Double> exchangeRates, Map<String, String> currencyNames) {
        System.out.println("Available currencies:");
        int i = 1;
        for (String currencyCode : exchangeRates.keySet()) {
            String currencyName = currencyNames.get(currencyCode);
            System.out.println(i + ". " + currencyCode + " (" + currencyName + ")");
            i++;
        }
    }

    private static boolean isValidCurrency(String currencyCode, Map<String, Double> exchangeRates) {
        return exchangeRates.containsKey(currencyCode);
    }

    private static double convertCurrency(double amount, String baseCurrencyCode, String targetCurrencyCode,
                                           Map<String, Double> exchangeRates) {
        double baseToUsdRate = exchangeRates.get(baseCurrencyCode);
        double usdToTargetRate = exchangeRates.get(targetCurrencyCode);
        return (amount / baseToUsdRate) * usdToTargetRate;
    }
}
