import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

//The Ledger class manages a list of financial transactions (deposits and payments),
// saves them to a CSV file, and loads them when the program starts.
public class Ledger {
    private List<Transaction> transactions = new ArrayList<>();
    private static final String FILE_NAME = "Transaction.csv";

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
            System.out.println(t);
        }
    }

    // Display table header
    private void displayHeader() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Date        | Time     | Description        | Vendor   | Amount");
        System.out.println("-------------------------------------------------------------");
    }

    // Search transactions by vendor and is case insensentive
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
                String[] parts = line.split("\\|");
                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

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
        try {
            File file = new File(FILE_NAME);
            boolean fileExists = file.exists();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                // If file already has data and does not end with a newline, add one
                if (fileExists && file.length() > 0) {
                    bw.newLine();
                }
                bw.write(String.format("%s|%s|%s|%s|%.2f",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount()));
                //can be replaced by bw.write(String.format("%s|%s|%s|%s|%.2f\n",
                //        t.getDate(),
                //        t.getTime().withNano(0),
                //        t.getDescription(),
                //        t.getVendor(),
                //        t.getAmount())); to remove the nano seconds
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving transaction: " + e.getMessage());
        }
    }


    // Provide access to the transaction list
    public List<Transaction> getTransactions() {
        return transactions;
    }
}
