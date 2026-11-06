
package banking;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        boolean running = true;

        while (running) {

            System.out.println("\n========== BANKING SYSTEM ==========");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Transfer Money");
            System.out.println("6. Check Balance");
            System.out.println("7. View Transaction History");
            System.out.println("8. Generate Account Statement");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            String input = sc.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid menu number.");
                continue;
            }

            try {
                switch (choice) {

                    case 1:
                        System.out.print("Enter account holder name: ");
                        String savingsName = sc.nextLine().trim();

                        System.out.print("Enter initial balance (minimum Rs. 1000): ");
                        double savingsBalance =
                                Double.parseDouble(sc.nextLine().trim());

                        if (savingsName.isEmpty()) {
                            System.out.println("Name cannot be empty.");
                        } else {
                            bank.createSavingsAccount(
                                    savingsName, savingsBalance);
                        }
                        break;

                    case 2:
                        System.out.print("Enter account holder name: ");
                        String currentName = sc.nextLine().trim();

                        System.out.print("Enter initial balance: ");
                        double currentBalance =
                                Double.parseDouble(sc.nextLine().trim());

                        System.out.print("Enter overdraft limit: ");
                        double overdraft =
                                Double.parseDouble(sc.nextLine().trim());

                        if (currentName.isEmpty()) {
                            System.out.println("Name cannot be empty.");
                        } else {
                            bank.createCurrentAccount(
                                    currentName, currentBalance, overdraft);
                        }
                        break;

                    case 3:
                        System.out.print("Enter account number: ");
                        int depositAccount =
                                Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter deposit amount: ");
                        double depositAmount =
                                Double.parseDouble(sc.nextLine().trim());

                        bank.deposit(depositAccount, depositAmount);
                        break;

                    case 4:
                        System.out.print("Enter account number: ");
                        int withdrawAccount =
                                Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount =
                                Double.parseDouble(sc.nextLine().trim());

                        bank.withdraw(withdrawAccount, withdrawAmount);
                        break;

                    case 5:
                        System.out.print("Enter sender account number: ");
                        int fromAccount =
                                Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter receiver account number: ");
                        int toAccount =
                                Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter transfer amount: ");
                        double transferAmount =
                                Double.parseDouble(sc.nextLine().trim());

                        bank.transfer(
                                fromAccount, toAccount, transferAmount);
                        break;

                    case 6:
                        System.out.print("Enter account number: ");
                        int balanceAccount =
                                Integer.parseInt(sc.nextLine().trim());

                        bank.checkBalance(balanceAccount);
                        break;

                    case 7:
                        System.out.print("Enter account number: ");
                        int historyAccount =
                                Integer.parseInt(sc.nextLine().trim());

                        bank.showTransactions(historyAccount);
                        break;

                    case 8:
                        System.out.print("Enter account number: ");
                        int statementAccount =
                                Integer.parseInt(sc.nextLine().trim());

                        bank.getStatement(statementAccount);
                        break;

                    case 9:
                        running = false;
                        System.out.println("Thank you for using our Banking System.");
                        break;

                    default:
                        System.out.println("Invalid choice. Select 1 to 9.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        sc.close();
    }
}
