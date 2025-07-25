import java.util.*;

class BankAccount {
    private String accountNumber;
    private double balance;
    private double dailyWithdrawn;
    private static final double DAILY_LIMIT = 10000;

    public BankAccount(String accountNumber, double startingBalance) {
        this.accountNumber = accountNumber;
        this.balance = startingBalance;
        this.dailyWithdrawn = 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₱" + amount + " deposited to account " + accountNumber);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Cannot withdraw. Not enough balance.");
        } else if (dailyWithdrawn + amount > DAILY_LIMIT) {
            System.out.println("Cannot withdraw. Daily limit of ₱" + DAILY_LIMIT + " exceeded.");
        } else {
            balance -= amount;
            dailyWithdrawn += amount;
            System.out.println("₱" + amount + " withdrawn from account " + accountNumber);
        }
    }

    public void resetDailyWithdrawn() {
        dailyWithdrawn = 0;
        System.out.println("Daily limit reset for account " + accountNumber);
    }

    public void checkBalance() {
        System.out.println("Account " + accountNumber + " balance: ₱" + balance);
    }
}

public class BankSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, BankAccount> accounts = new HashMap<>();

        // Pre-created accounts
        accounts.put("A101", new BankAccount("A101", 5000));
        accounts.put("A102", new BankAccount("A102", 10000));

        System.out.println("Welcome to the Bank System!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Reset Daily Limit");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");

            int option = scanner.nextInt();
            if (option == 5)
                break;

            System.out.print("Enter Account Number: ");
            String accNum = scanner.next();

            BankAccount account = accounts.get(accNum);
            if (account == null) {
                System.out.println("Account not found.");
                continue;
            }

            switch (option) {
                case 1:
                    System.out.print("Enter deposit amount: ₱");
                    double depAmount = scanner.nextDouble();
                    account.deposit(depAmount);
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ₱");
                    double withAmount = scanner.nextDouble();
                    account.withdraw(withAmount);
                    break;

                case 3:
                    account.checkBalance();
                    break;

                case 4:
                    account.resetDailyWithdrawn();
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
        System.out.println("Thank you for using the Bank System!");
    }
}
