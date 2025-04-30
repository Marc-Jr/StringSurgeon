import java.util.Scanner;
import java.util.regex.*;

public class StringUtilities {

    // 🎨 ANSI escape codes for colored output
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println(CYAN + "\n--- String Utilities ---" + RESET);
            System.out.println("1. Check Palindrome");
            System.out.println("2. Compress String");
            System.out.println("3. Reverse Sentence Words");
            System.out.println("4. Remove Vowels");
            System.out.println("5. Validate Password");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter a string to check palindrome: ");
                    String palindromeInput = scanner.nextLine();
                    System.out.println(isPalindrome(palindromeInput)
                            ? GREEN + "It's a palindrome!" + RESET
                            : RED + "Not a palindrome." + RESET);
                    break;

                case 2:
                    System.out.print("Enter a string to compress (spaces will be removed): ");
                    String toCompress = scanner.nextLine().replaceAll("\\s", "");
                    String compressed = compressString(toCompress);
                    System.out.println(YELLOW + "Compressed (if shorter): " +
                            (compressed.length() < toCompress.length() ? compressed : toCompress) + RESET);
                    break;

                case 3:
                    System.out.print("Enter a sentence to reverse: ");
                    String sentence = scanner.nextLine();
                    System.out.println(BLUE + "Reversed sentence: " + new StringBuilder(sentence).reverse() + RESET);
                    break;

                case 4:
                    System.out.print("Enter a string to remove vowels: ");
                    String vowelInput = scanner.nextLine();
                    System.out.println(PURPLE + "Without vowels: " +
                            vowelInput.replaceAll("(?i)[aeiou]", "") + RESET);
                    break;

                case 5:
                    System.out.print("Enter a password to validate: ");
                    String password = scanner.nextLine();
                    System.out.println(isValidPassword(password)
                            ? GREEN + "Password is valid!" + RESET
                            : RED + "Invalid password. Make sure it has 8-20 characters, a digit, upper/lower case letters, and a special character." + RESET);
                    break;

                case 6:
                    System.out.println(CYAN + "Exiting. Thank you!" + RESET);
                    break;

                default:
                    System.out.println(RED + "Invalid option. Try again." + RESET);
            }

        } while (choice != 6);

        scanner.close();
    }

    // 1. Palindrome Checker
    public static boolean isPalindrome(String text) {
        String clean = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return isPalindromeRecursive(clean, 0, clean.length() - 1);
    }

    private static boolean isPalindromeRecursive(String text, int left, int right) {
        if (left >= right) return true;
        if (text.charAt(left) != text.charAt(right)) return false;
        return isPalindromeRecursive(text, left + 1, right - 1);
    }

    // 2. String Compression
    public static String compressString(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 1; i <= s.length(); i++) {
            if (i < s.length() && s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                sb.append(s.charAt(i - 1)).append(count);
                count = 1;
            }
        }

        return sb.toString();
    }

    // 5. Password Validation
    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
        if (password == null) return false;
        return Pattern.matches(regex, password);
    }
}
