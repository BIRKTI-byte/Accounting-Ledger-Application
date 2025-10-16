import java.time.LocalDate;
import java.util.List;

public class Report {

    // Month-to-date report
    public void monthToDate(List<Transaction> transactions) {
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();

        System.out.println("📊 Month-To-Date Transactions:");
        double totalDeposits = 0;
        double totalPayments = 0;

        for (Transaction t : transactions) {
            LocalDate transactionDate = t.getDate();
            if (transactionDate.getMonthValue() == currentMonth &&
                    transactionDate.getYear() == currentYear) {
                System.out.println(t);
                if (t.getAmount() > 0) totalDeposits += t.getAmount();
                else totalPayments += t.getAmount();
            }
        }

        printTotals(totalDeposits, totalPayments);
    }

    // Previous month report
    public void previousMonth(List<Transaction> transactions) {
        LocalDate today = LocalDate.now();
        int previousMonth = today.getMonthValue() - 1;
        int previousYear = today.getYear();
        if (previousMonth == 0) { // January → December previous year
            previousMonth = 12;
            previousYear--;
        }

        System.out.println("📊 Previous Month Transactions:");
        double totalDeposits = 0;
        double totalPayments = 0;

        for (Transaction t : transactions) {
            LocalDate transactionDate = t.getDate();
            if (transactionDate.getMonthValue() == previousMonth &&
                    transactionDate.getYear() == previousYear) {
                System.out.println(t);
                if (t.getAmount() > 0) totalDeposits += t.getAmount();
                else totalPayments += t.getAmount();
            }
        }


        printTotals(totalDeposits, totalPayments);
    }

    // Year-to-date report
    public void yearToDate(List<Transaction> transactions) {
        int currentYear = LocalDate.now().getYear();

        System.out.println("📊 Year-To-Date Transactions:");
        double totalDeposits = 0;
        double totalPayments = 0;

        for (Transaction t : transactions) {
            if (t.getDate().getYear() == currentYear) {
                System.out.println(t);
                if (t.getAmount() > 0) totalDeposits += t.getAmount();
                else totalPayments += t.getAmount();
            }
        }

        printTotals(totalDeposits, totalPayments);
    }

    // Previous year report
    public void previousYear(List<Transaction> transactions) {
        int previousYear = LocalDate.now().getYear() - 1;

        System.out.println("📊 Previous Year Transactions:");
        double totalDeposits = 0;
        double totalPayments = 0;

        for (Transaction t : transactions) {
            if (t.getDate().getYear() == previousYear) {
                System.out.println(t);
                if (t.getAmount() > 0) totalDeposits += t.getAmount();
                else totalPayments += t.getAmount();
            }
        }

        printTotals(totalDeposits, totalPayments);
    }

    // Search by vendor
    public void searchByVendor(List<Transaction> transactions, String vendor) {
        System.out.println("📊 Transactions for vendor: " + vendor);
        double totalDeposits = 0;
        double totalPayments = 0;

        for (Transaction t : transactions) {
            if (t.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(t);
                if (t.getAmount() > 0) totalDeposits += t.getAmount();
                else totalPayments += t.getAmount();
            }
        }

        printTotals(totalDeposits, totalPayments);
    }

    // Custom search with optional filters
    public void customSearch(List<Transaction> transactions, LocalDate startDate, LocalDate endDate,
                             String vendor, String description, Double minAmount, Double maxAmount) {
        System.out.println("📊 Custom Search Results:");
        double totalDeposits = 0;
        double totalPayments = 0;

        for (Transaction t : transactions) {
            boolean matches = true;
   //If the user gave a start date, and this transaction happened before that date, we don’t include it in the search results and this condition applies for the rest as well
            if (startDate != null && t.getDate().isBefore(startDate)) matches = false;
            if (endDate != null && t.getDate().isAfter(endDate)) matches = false;
            if (vendor != null && !t.getVendor().equalsIgnoreCase(vendor)) matches = false;
            if (description != null && !t.getDescription().toLowerCase().contains(description.toLowerCase()))
                matches = false;
            if (minAmount != null && t.getAmount() < minAmount) matches = false;
            if (maxAmount != null && t.getAmount() > maxAmount) matches = false;

            if (matches) {
                System.out.println(t);
                if (t.getAmount() > 0) totalDeposits += t.getAmount();
                else totalPayments += t.getAmount();
            }
        }

        printTotals(totalDeposits, totalPayments);
    }

    // Helper method to print totals
    private void printTotals(double totalDeposits, double totalPayments) {
        System.out.println("-------------------------------------------------");
        System.out.println("Total Deposits: " + String.format("$%,.2f", totalDeposits));
        System.out.println("Total Payments: " + String.format("$%,.2f", totalPayments));
        System.out.println("Net Total: " + String.format("$%,.2f", totalDeposits + totalPayments));
        System.out.println("-------------------------------------------------");
    }
}
