import java.time.LocalDate;

public class AccountingLedgerApp {

    public static void main(String[] args) {
        Ledger ledger = new Ledger();
        Report report = new Report();

        boolean isRunning = true;

        while (isRunning) {
            // Display Home Menu with header
            UIHelper.printHeader("🏠 HOME MENU");
            System.out.println("[D] Add Deposit");
            System.out.println("[P] Make Payment (Debit)");
            System.out.println("[L] Ledger / Reports");
            System.out.println("[X] Exit");

            // Prompt user choice
            String choice = UIHelper.promptString("Select option").toUpperCase();

            switch (choice) {
                case "D": // Add Deposit
                    String description = UIHelper.promptString("Enter description");
                    String vendor = UIHelper.promptString("Enter vendor");
                    double amount = UIHelper.promptDouble("Enter amount");
                    ledger.addDeposit(description, vendor, amount);
                    break;

                case "P": // Make Payment
                    description = UIHelper.promptString("Enter payment description");
                    vendor = UIHelper.promptString("Enter vendor");
                    amount = UIHelper.promptDouble("Enter amount");
                    ledger.makePayment(description, vendor, amount);
                    break;

                case "L": // Ledger / Reports menu
                    showLedgerMenu(ledger, report);
                    break;

                case "X": // Exit
                    System.out.println("👋 Goodbye!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("⚠️ Invalid choice. Try again!");
            }
        }
    }

    public static void showLedgerMenu(Ledger ledger, Report report) {
        boolean inLedgerMenu = true;

        while (inLedgerMenu) {
            UIHelper.printHeader("📊 LEDGER / REPORT MENU");
            System.out.println("[1] Display All Transactions");
            System.out.println("[2] Search by Vendor");
            System.out.println("[3] Month-to-Date Transactions");
            System.out.println("[4] Previous Month Transactions");
            System.out.println("[5] Year-to-Date Transactions");
            System.out.println("[6] Previous Year Transactions");
            System.out.println("[7] Custom Search (Optional)");
            System.out.println("[0] Back to Home Menu");

            String choice = UIHelper.promptString("Select option");

            switch (choice) {
                case "1":
                    ledger.displayAll();
                    break;

                case "2":
                    String vendor = UIHelper.promptString("Enter vendor to search");
                    report.searchByVendor(ledger.getTransactions(), vendor);
                    break;

                case "3":
                    report.monthToDate(ledger.getTransactions());
                    break;

                case "4":
                    report.previousMonth(ledger.getTransactions());
                    break;

                case "5":
                    report.yearToDate(ledger.getTransactions());
                    break;

                case "6":
                    report.previousYear(ledger.getTransactions());
                    break;

                case "7":
                    // Optional Custom Search
                    LocalDate startDate = UIHelper.promptDate("Enter start date (YYYY-MM-DD)");
                    LocalDate endDate = UIHelper.promptDate("Enter end date (YYYY-MM-DD)");
                    vendor = UIHelper.promptString("Enter vendor filter (or leave blank)");
                    String description = UIHelper.promptString("Enter description filter (or leave blank)");
                    String minAmountStr = UIHelper.promptString("Enter minimum amount (or leave blank)");
                    String maxAmountStr = UIHelper.promptString("Enter maximum amount (or leave blank)");

                    // Convert to numeric or null
                    Double minAmount = minAmountStr.isEmpty() ? null : Double.parseDouble(minAmountStr);
                    Double maxAmount = maxAmountStr.isEmpty() ? null : Double.parseDouble(maxAmountStr);

                    if (vendor.isEmpty()) vendor = null;
                    if (description.isEmpty()) description = null;

                    report.customSearch(ledger.getTransactions(), startDate, endDate, vendor, description, minAmount, maxAmount);
                    break;

                case "0":
                    inLedgerMenu = false;
                    break;

                default:
                    System.out.println("⚠️ Invalid choice. Try again!");
            }
        }
    }
}
