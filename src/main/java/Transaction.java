import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private Double amount;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, Double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
//    public LocalDate getDate() { return date; }
//    public void setDate(LocalDate date) { this.date = date; }
//
//    public LocalTime getTime() { return time; }
//    public void setTime(LocalTime time) { this.time = time; }
//
//    public String getDescription() { return description; }
//    public void setDescription(String description) { this.description = description; }
//
//    public String getVendor() { return vendor; }
//    public void setVendor(String vendor) { this.vendor = vendor; }
//
//    public Double getAmount() { return amount; }
//    public void setAmount(Double amount) { this.amount = amount; }

    @Override
    public String toString() {
        return String.format("%-12s | %-8s | %-20s | %-10s | %10.2f",
                date, time, description, vendor, amount);
    }
}
// Home Screen
//o The home screen should give the user the following options. The
//application should continue to run until the user chooses to exit.
//§ D) public void Add Deposit(){
// - prompt user for the deposit information and
//save it to the csv file
//§ P) Make Payment(){} (Debit) - prompt user for the debit
//information and save it to the csv file
//§ L) Ledger - display the ledger screen
//§ X) Exit - exit the application
//• Ledger - All entries should show the newest entries first
//o A) All - Display all entries
//o D) Deposits - Display only the entries that are deposits into the
//account
//o P) Payments - Display only the negative entries (or payments)
//o R) Reports - A new screen that allows the user to run pre-defined
//reports or to run a custom search
//§ 1) Month To Date(){}
//§ 2) Previous Month
//§ 3) Year To Date
//§ 4) Previous Year
//§ 5) Search by Vendor - prompt the user for the vendor name
//and display all entries for that vendor
//§ 0) Back - go back to the Ledger page
//o H) Home - go back to the home page