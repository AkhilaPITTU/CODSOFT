import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== CURRENCY CONVERTER =====");

        System.out.print("Enter base currency (USD / INR / EUR): ");
        String base = scanner.next().toUpperCase();

        System.out.print("Enter target currency (USD / INR / EUR): ");
        String target = scanner.next().toUpperCase();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        double rate = getExchangeRate(base, target);

        if (rate == -1) {
            System.out.println("Currency conversion not supported.");
        } else {
            double result = amount * rate;

            System.out.println("\n----- RESULT -----");
            System.out.println("Base Currency   : " + base);
            System.out.println("Target Currency : " + target);
            System.out.println("Amount          : " + amount);
            System.out.println("Converted Amount: " + result);
        }

        scanner.close();
    }

    public static double getExchangeRate(String base, String target) {

        if (base.equals("USD") && target.equals("INR")) return 83.0;
        if (base.equals("INR") && target.equals("USD")) return 0.012;
        if (base.equals("USD") && target.equals("EUR")) return 0.92;
        if (base.equals("EUR") && target.equals("USD")) return 1.08;
        if (base.equals("INR") && target.equals("EUR")) return 0.011;
        if (base.equals("EUR") && target.equals("INR")) return 90.0;

        if (base.equals(target)) return 1;

        return -1;
    }
}