import java.util.Scanner;

// Bank Account Class
class BankAccount {

    private double balance;

    // Constructor
    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    // Deposit Method
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful!");
    }

    // Withdraw Method
    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance!");
            return false;
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful!");
            return true;
        }
    }

    // Check Balance Method
    public double getBalance() {
        return balance;
    }
}


// ATM Class
class ATM {

    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Current Balance: ₹" + account.getBalance());
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();

                    if (depositAmount > 0) {
                        account.deposit(depositAmount);
                    } else {
                        System.out.println("Invalid deposit amount!");
                    }
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();

                    if (withdrawAmount > 0) {
                        account.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Invalid withdrawal amount!");
                    }
                    break;

                case 4:
                    System.out.println("Thank you for using the ATM!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 4);

        scanner.close();
    }
}


// Main Class
public class ATMInterface {

    public static void main(String[] args) {

        BankAccount userAccount = new BankAccount(5000); // Initial balance
        ATM atm = new ATM(userAccount);

        atm.start();
    }
}