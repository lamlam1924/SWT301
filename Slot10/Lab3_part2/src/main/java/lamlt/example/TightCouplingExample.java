package lamlt.example;

import java.util.logging.Level;
import java.util.logging.Logger;

class Printer {
    private static final Logger logger = Logger.getLogger(Printer.class.getName());

    void print(String message) {
        logger.info(message);
    }
}

class Report {
    private static final Logger logger = Logger.getLogger(Report.class.getName());
    private final Printer printer;

    public Report(Printer printer) {
        this.printer = printer;
    }

    void generate(String message, int index) {
        if (index < 0) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Index cannot be negative");
            }
            return;
        }
        int[] arr = new int[]{10, 20, 30};
        if (index >= arr.length) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning(String.format("Index %d out of bounds", index));
            }
            return;
        }
        try {
            if (message != null && !message.isEmpty()) {
                printer.print(message);
            }
            int value = arr[index];
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Value at index %d: %d", index, value));
            }
        } catch (Exception e) {
            if (logger.isLoggable(Level.SEVERE)) {
                logger.log(Level.SEVERE, "Unexpected error during report generation", e);
            }
        }
    }

    public static void main(String[] args) {
        Printer printer = new Printer();
        Report report = new Report(printer);

        report.generate("Test message", 1);    // Gọi method generate để dùng class Report
        report.generate(null, -1);             // Thử trường hợp index âm
        report.generate("Another message", 5); // Thử index vượt mảng
    }
}


