package projectCode20280.exercises;

public class Recursion {
    public static int sumOfDigits(int n) {
        // Base case when no. of digits is 1
        if (n / 10 == 0) {
            return n;
        }
        // Recursive case: getLastDigit + sumOfDigits(lastDigitRemoved)
        return n % 10 + sumOfDigits(n / 10);
    }

    public static void collatz(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input can only be positive");
        }
        System.out.println(n);
        if (n == 1) {
            return;
        }
        if (n % 2 == 0) {
            collatz(n / 2);
        } else {
            collatz(3 * n + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(sumOfDigits(54321));
        collatz(9);
    }
}
