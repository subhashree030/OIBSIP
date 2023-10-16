import java.util.ArrayList;
import java.util.Scanner;

class User {
    private String userId;
    private String userPin;

    public User(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }
}

class Account {
    private String accountId;
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(String accountId) {
        this.accountId = accountId;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited $" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn $" + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add("Transferred $" + amount + " to " + recipient.getAccountId());
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void displayTransactionHistory() {
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

class ATM {
    public static void main(String[] args) {
        User user = new User("12345", "1234");
        Account account1 = new Account("1001");
        Account account2 = new Account("1002");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String inputUserId = scanner.nextLine();
        System.out.print("Enter User PIN: ");
        String inputUserPin = scanner.nextLine();

        if (inputUserId.equals(user.getUserId()) && inputUserPin.equals(user.getUserPin())) {
            System.out.println("Welcome to the ATM!");
            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. View Transaction History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                System.out.print("Enter your option: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        account1.displayTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter the withdrawal amount: $");
                        double withdrawAmount = scanner.nextDouble();
                        account1.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter the deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        account1.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter the recipient's account ID: ");
                        String recipientAccountId = scanner.next();
                        Account recipient = recipientAccountId.equals(account2.getAccountId()) ? account2 : null;
                        if (recipient == null) {
                            System.out.println("Recipient account not found.");
                            break;
                        }
                        System.out.print("Enter the transfer amount: $");
                        double transferAmount = scanner.nextDouble();
                        account1.transfer(recipient, transferAmount);
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Have a great day!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a valid option.");
                }
            }
        } else {
            System.out.println("Invalid User ID or PIN. Access denied.");
        }
    }
}
