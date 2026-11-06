
package banking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private LocalDateTime date;
    private String type;
    private double amount;
    private String description;

    public Transaction(String type, double amount, String description) {
        this.date = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public void displayTransaction() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.printf("%-22s %-15s Rs. %-10.2f %s%n",
                date.format(formatter),
                type,
                amount,
                description);
    }
}
