
package banking;

public class CurrentAccount extends BankAccount {

    private double overdraftLimit;

    public CurrentAccount(int accountNumber,
                          String accountHolder,
                          double initialBalance,
                          double overdraftLimit) {

        super(accountNumber, accountHolder, initialBalance);

        if (!Double.isFinite(initialBalance) || initialBalance < 0) {
            throw new IllegalArgumentException(
                    "Initial balance cannot be negative.");
        }

        if (!Double.isFinite(overdraftLimit) || overdraftLimit < 0) {
            throw new IllegalArgumentException(
                    "Overdraft limit cannot be negative.");
        }

        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {

        if (amount <= 0 || !Double.isFinite(amount)) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }

        if (balance - amount < -overdraftLimit) {
            System.out.println("Withdrawal failed! Overdraft limit exceeded.");
            return;
        }

        balance -= amount;
        System.out.println("Current account withdrawal successful.");
    }
}
