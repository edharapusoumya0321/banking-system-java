
package banking;

public class SavingsAccount extends BankAccount {

    private static final double MINIMUM_BALANCE = 1000.0;

    public SavingsAccount(int accountNumber,
                          String accountHolder,
                          double initialBalance) {

        super(accountNumber, accountHolder, initialBalance);

        if (!Double.isFinite(initialBalance)
                || initialBalance < MINIMUM_BALANCE) {
            throw new IllegalArgumentException(
                    "Initial balance must be at least Rs. 1000.");
        }
    }

    @Override
    public void withdraw(double amount) {

        if (amount <= 0 || !Double.isFinite(amount)) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }

        if (balance - amount < MINIMUM_BALANCE) {
            System.out.println(
                    "Withdrawal failed! Minimum balance of Rs. "
                    + MINIMUM_BALANCE + " must be maintained.");
            return;
        }

        balance -= amount;
        System.out.println("Savings withdrawal successful.");
    }
}
