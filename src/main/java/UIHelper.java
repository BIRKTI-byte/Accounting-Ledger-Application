import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
//It handles  input validation, formatted prompts, and visual enhancements such as headers and dividers for a cleaner UI
public class UIHelper {
    private static final Scanner scanner = new Scanner(System.in);

    // Prompt for a String input
    public static String promptString(String message) {
        System.out.print(message + ": ");
        return scanner.nextLine().trim();
    }

    // Prompt for a double input
    public static double promptDouble(String message) {
        while (true) {
            try {
                System.out.print(message + ": ");
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid number. Please try again.");
            }
        }
    }

    // Prompt for a date input in format YYYY-MM-DD
    // Also return null if user presses Enter
    public static LocalDate promptDate(String message) {
        while (true) {
            System.out.print(message + " (YYYY-MM-DD, leave blank to skip): ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                return null; // if the user chose to skip
            }

            try {
                return LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                System.out.println("⚠️ Invalid date format. Try again (e.g., 2025-10-13).");
            }
        }
    }

    // Print divider line just for appealing visual
    public static void printLine() {
        System.out.println("-----------------------------------");
    }

    // Print a formatted header
    public static void printHeader(String title) {
        printLine();
        System.out.println("📘 " + title);
        printLine();
    }
}
