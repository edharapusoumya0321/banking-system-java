
package banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {

    private Map<Integer, BankAccount> accounts = new HashMap<>();
    private Map<Integer, List<Transaction>> transactionHistory =
            new HashMap<>();

    private int nextAccountNumber = 1001;

    // Create a savings account
    public int createSavingsAccount(String name, double initialBalance) {
        int accountNumber = nextAccountNumber++;

        try {
            BankAccount account = new SavingsAccount(
                    accountNumber, name, initialBalance);

            accounts.put(accountNumber, account);
            transactionHistory.put(accountNumber, new ArrayList<>());

            recordTransaction(accountNumber, "OPENING",
                    initialBalance, "Savings account created");

            System.out.println("Savings account created successfully.");
            System.out.println("Account number: " + accountNumber);

            return accountNumber;

        } catch (IllegalArgumentException e) {
            nextAccountNumber--;
            System.out.println("Account creation failed: " + e.getMessage());
            return -1;
        }
    }

    // Create a current account
    public int createCurrentAccount(String name, double initialBalance,
                                    double overdraftLimit) {
        int accountNumber = nextAccountNumber++;

        try {
            BankAccount account = new CurrentAccount(
                    accountNumber, name, initialBalance, overdraftLimit);

            accounts.put(accountNumber, account);
            transactionHistory.put(accountNumber, new ArrayList<>());

            recordTransaction(accountNumber, "OPENING",
                    initialBalance, "Current account created");

            System.out.println("Current account created successfully.");
            System.out.println("Account number: " + accountNumber);

            return accountNumber;

        } catch (IllegalArgumentException e) {
            nextAccountNumber--;
            System.out.println("Account creation failed: " + e.getMessage());
            return -1;
        }
    }

    // Find an account
    public BankAccount getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    // Deposit money
    public void deposit(int accountNumber, double amount) {
        BankAccount account = getAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }

        double oldBalance = account.getBalance();
        account.deposit(amount);

        if (account.getBalance() > oldBalance) {
            recordTransaction(accountNumber, "DEPOSIT",
                    amount, "Money deposited");
        }
    }

    // Withdraw money
    public void withdraw(int accountNumber, double amount) {
        BankAccount account = getAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }

        double oldBalance = account.getBalance();
        account.withdraw(amount);

        if (account.getBalance() < oldBalance) {
            recordTransaction(accountNumber, "WITHDRAWAL",
                    amount, "Money withdrawn");
        }
    }

    // Transfer money between accounts
    public void transfer(int fromAccount, int toAccount, double amount) {
        BankAccount source = getAccount(fromAccount);
        BankAccount destination = getAccount(toAccount);

        if (source == null || destination == null) {
            System.out.println("One or both accounts were not found.");
            return;
        }

        if (fromAccount == toAccount) {
            System.out.println("Cannot transfer to the same account.");
            return;
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Invalid transfer amount.");
            return;
        }

        double oldSourceBalance = source.getBalance();

        source.withdraw(amount);

        if (source.getBalance() >= oldSourceBalance) {
            System.out.println("Transfer failed.");
            return;
        }

        destination.deposit(amount);

        recordTransaction(fromAccount, "TRANSFER OUT",
                amount, "Transferred to account " + toAccount);

        recordTransaction(toAccount, "TRANSFER IN",
                amount, "Received from account " + fromAccount);

        System.out.println("Transfer completed successfully.");
    }

    // Display account balance
    public void checkBalance(int accountNumber) {
        BankAccount account = getAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        account.displayBalance();
    }

    // Record transaction details
    private void recordTransaction(int accountNumber, String type,
                                   double amount, String description) {
        transactionHistory.get(accountNumber).add(
                new Transaction(type, amount, description));
    }

    // Display transaction history
    public void showTransactions(int accountNumber) {
        if (!accounts.containsKey(accountNumber)) {
            System.out.println("Account not found.");
            return;
        }

        List<Transaction> transactions =
                transactionHistory.get(accountNumber);

        System.out.println("\n========== TRANSACTION HISTORY ==========");
        System.out.printf("%-22s %-15s %-15s %s%n",
                "Date", "Type", "Amount", "Description");

        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }
    }

    // Generate account statement
    public void getStatement(int accountNumber) {
        BankAccount account = getAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        account.displayBalance();
        showTransactions(accountNumber);
    }
}
