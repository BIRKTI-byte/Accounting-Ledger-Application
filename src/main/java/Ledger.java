import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;


//The Ledger class manages a list of financial transactions (deposits and payments),
// saves them to a CSV file, and loads them when the program starts.
public class Ledger {
    private List<Transaction> transactions = new ArrayList<>();
    private static final String FILE_NAME = "Transaction.csv";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    // Constructor: load existing transactions
    public Ledger() {
        loadTransactions();
    }

    // Add a deposit
    public void addDeposit(String description, String vendor, double amount) {
        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
        transactions.add(t);
        saveTransaction(t);
        System.out.println("✅ Deposit added successfully!");
    }

    // Make a payment (stored as negative amount)
    public void makePayment(String description, String vendor, double amount) {
        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, -amount);
        transactions.add(t);
        saveTransaction(t);
        System.out.println("✅ Payment recorded successfully!");
    }

    // Display all transactions
    public void displayAll() {
        displayHeader();
        for (Transaction t : transactions) {
            System.out.printf("%-12s | %-8s | %-20s | %-10s | %10.2f%n",
                    t.getDate(),
                    t.getTime().format(TIME_FORMATTER),
                    t.getDescription(),
                    t.getVendor(),
                    t.getAmount());
        }
    }

    // Display table header
    private void displayHeader() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Date        | Time     | Description        | Vendor   | Amount");
        System.out.println("-------------------------------------------------------------");
    }

    // Search transactions by vendor and is case insensentive. I have to get back to this
    public void searchByVendor(String vendor) {
        displayHeader();
        for (Transaction t : transactions) {
            if (t.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(t);
            }
        }
    }

    // Load transactions from CSV
    private void loadTransactions() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            br.readLine(); // skip the header part

            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove extra spaces

                // Skip completely empty or broken lines
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\|");

                // Make sure the line has all 5 parts before parsing
                if (parts.length < 5) continue;

                // Trim each value to prevent spacing errors
                String dateStr = parts[0].trim();
                String timeStr = parts[1].trim();
                String description = parts[2].trim();
                String vendor = parts[3].trim();
                String amountStr = parts[4].trim();

                // Parse date, time, and amount safely
                LocalDate date = LocalDate.parse(dateStr);
                LocalTime time = LocalTime.parse(timeStr);
                double amount = Double.parseDouble(amountStr);

                Transaction t = new Transaction(date, time, description, vendor, amount);
                transactions.add(t);
            }
        } catch (IOException e) {
            System.out.println("⚠️ No existing transaction file found. A new one will be created.");
        } catch (Exception e) {
            System.out.println("❌ Error reading transactions: " + e.getMessage());
        }
    }

    // Save a single transaction to CSV

    private void saveTransaction(Transaction t) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(String.format("%s|%s|%s|%s|%.2f%n",
                    t.getDate(),
                    t.getTime().withNano(0), // removes nanoseconds
                    t.getDescription(),
                    t.getVendor(),
                    t.getAmount()));
        } catch (IOException e) {
            System.out.println("❌ Error saving transaction: " + e.getMessage());
        }
    }




    // Provide access to the transaction list
    public List<Transaction> getTransactions() {
        return transactions;
    }
}
