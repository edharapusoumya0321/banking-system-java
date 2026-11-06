
package banking;

public class BankAccount {

    private int accountNumber;
    private String accountHolder;
    protected double balance;

    public BankAccount(int accountNumber,
                       String accountHolder,
                       double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0 || !Double.isFinite(amount)) {
            System.out.println("Invalid deposit amount.");
            return;
        }

        balance += amount;
        System.out.println("Deposit successful.");
    }

    public void withdraw(double amount) {
        if (amount <= 0 || !Double.isFinite(amount)) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return;
        }

        balance -= amount;
        System.out.println("Withdrawal successful.");
    }

    public void displayBalance() {
        System.out.printf("Account Holder: %s%n", accountHolder);
        System.out.printf("Account Number: %d%n", accountNumber);
        System.out.printf("Available Balance: Rs. %.2f%n", balance);
    }
}
