import java.util.Scanner;

public class ATM {
    public static Scanner scanner = new Scanner(System.in);
    public static BankAccount account = new BankAccount();

    public static void displayMenu() {
        System.out.println("Welcome to the ATM!");
        System.out.println("Please choose an option:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check balance");
        System.out.println("4. Exit");
    }

    public static int getChoice() {
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        return choice;
    }

    public static void withdraw() {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        boolean success = account.withdraw(amount);
        if (success) {
            System.out.println("You have withdrawn " + amount + " from your account.");
            System.out.println("Your new balance is " + account.getBalance());
        } else {
            System.out.println("Sorry, you cannot withdraw more than your balance.");
            System.out.println("Your current balance is " + account.getBalance());
        }
    }

    public static void deposit() {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("You have deposited " + amount + " into your account.");
        System.out.println("Your new balance is " + account.getBalance());
    }

    public static void checkBalance() {
        System.out.println("Your current balance is " + account.getBalance());
    }

    public static void exit() {
        System.out.println("Thank you for using the ATM. Goodbye!");
        System.exit(0);
    }

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    exit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

class BankAccount {
    private double balance;

    public BankAccount() {
        balance = 1000;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }

    public void deposit(double amount) {
        balance += amount;
    }
}
