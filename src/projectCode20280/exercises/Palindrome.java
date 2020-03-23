package projectCode20280.exercises;

public class Palindrome {
    public static boolean isPalindrome(String input) {
        input = input.toLowerCase().replaceAll("[\\s,?]", "");
        return isPalindromeHelper(input, 0, input.length() - 1);
    }

    private static boolean isPalindromeHelper(String input, int start, int end) {
        if (start >= end) {
            return true;
        }
        return (input.charAt(start) == input.charAt(end))
                && isPalindromeHelper(input, start + 1, end - 1);
    }

    public static void main(String[] args) {
        String[] inputs = {
                "Racecar",
                "Radar",
                "Step on no pets",
                "Top spot",
                "Was it a cat I saw?",
                "eva, can I see bees in a cave?",
                "no lemon, no melon",
                "stupid",
                "?not b ton?"
        };

        for (String input : inputs) {
            System.out.println(Palindrome.isPalindrome(input) + " -> " + input);
        }
    }
}
