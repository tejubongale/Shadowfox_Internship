import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;

public class EnhancedCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== ENHANCED CONSOLE CALCULATOR ===");
            System.out.println("1. Basic Arithmetic");
            System.out.println("2. Scientific Calculator");
            System.out.println("3. Unit Conversion");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    basicArithmetic(scanner);
                    break;
                case 2:
                    scientificCalculator(scanner);
                    break;
                case 3:
                    unitConversion(scanner);
                    break;
                case 4:
                    System.out.println("Exiting Calculator. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        } while (choice != 4);
    }

    // Print timestamp for every operation
    public static void printTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Timestamp: " + now.format(formatter));
    }

    // Append result to a log file
    public static void logToFile(String message) {
        try {
            FileWriter writer = new FileWriter("calculator_log.txt", true); // append mode
            writer.write(message + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    // 1. Basic Arithmetic
    public static void basicArithmetic(Scanner scanner) {
        System.out.print("Enter first number: ");
        double a = scanner.nextDouble();
        System.out.print("Enter second number: ");
        double b = scanner.nextDouble();

        System.out.println("Choose operation: + - * /");
        char op = scanner.next().charAt(0);
        double result;

        switch (op) {
            case '+': result = a + b; break;
            case '-': result = a - b; break;
            case '*': result = a * b; break;
            case '/':
                if (b != 0) result = a / b;
                else {
                    System.out.println("Error: Division by zero!");
                    return;
                }
                break;
            default:
                System.out.println("Invalid operator!");
                return;
        }

        System.out.println("Result = " + result);
        printTimestamp();
        logToFile("Basic Arithmetic [" + a + " " + op + " " + b + "] = " + result);
    }

    // 2. Scientific Calculator
    public static void scientificCalculator(Scanner scanner) {
        System.out.println("Choose operation: ");
        System.out.println("1. Square Root");
        System.out.println("2. Power (x^y)");
        int sciChoice = scanner.nextInt();

        switch (sciChoice) {
            case 1:
                System.out.print("Enter number: ");
                double num = scanner.nextDouble();
                if (num >= 0) {
                    double result = Math.sqrt(num);
                    System.out.println("Square Root = " + result);
                    printTimestamp();
                    logToFile("Square Root of " + num + " = " + result);
                } else {
                    System.out.println("Cannot find square root of negative number!");
                }
                break;
            case 2:
                System.out.print("Enter base: ");
                double base = scanner.nextDouble();
                System.out.print("Enter exponent: ");
                double exponent = scanner.nextDouble();
                double result = Math.pow(base, exponent);
                System.out.println("Power = " + result);
                printTimestamp();
                logToFile("Power [" + base + "^" + exponent + "] = " + result);
                break;
            default:
                System.out.println("Invalid scientific choice!");
        }
    }

    // 3. Unit Conversion
    public static void unitConversion(Scanner scanner) {
        System.out.println("Choose conversion:");
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Fahrenheit to Celsius");
        System.out.println("3. INR to USD (Fixed Rate rupees 83/USD)");
        System.out.println("4. USD to INR");
        int unitChoice = scanner.nextInt();

        switch (unitChoice) {
            case 1:
                System.out.print("Enter Celsius: ");
                double c = scanner.nextDouble();
                double fResult = (c * 9 / 5) + 32;
                System.out.println("Result = " + fResult + " °F");
                printTimestamp();
                logToFile("Celsius to Fahrenheit: " + c + "°C = " + fResult + "°F");
                break;
            case 2:
                System.out.print("Enter Fahrenheit: ");
                double f = scanner.nextDouble();
                double cResult = (f - 32) * 5 / 9;
                System.out.println("Result = " + cResult + " °C");
                printTimestamp();
                logToFile("Fahrenheit to Celsius: " + f + "°F = " + cResult + "°C");
                break;
            case 3:
                System.out.print("Enter INR: rupees");
                double inr = scanner.nextDouble();
                double usd = inr / 83;
                System.out.println("Result = $" + usd);
                printTimestamp();
                logToFile("INR to USD: rupees" + inr + " = $" + usd);
                break;
            case 4:
                System.out.print("Enter USD: $");
                double usdInput = scanner.nextDouble();
                double inrResult = usdInput * 83;
                System.out.println("Result = rupees" + inrResult);
                printTimestamp();
                logToFile("USD to INR: $" + usdInput + " = rupees" + inrResult);
                break;
            default:
                System.out.println("Invalid conversion choice!");
        }
    }
}
