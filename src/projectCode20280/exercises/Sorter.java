package projectCode20280.exercises;

import java.util.Arrays;
import java.util.Random;

public class Sorter {
    public static void main(String[] args) {
        // size of input
        int n = 20000;
        int[] input = generateInput(n);
        // calculate elapse time for bubble sort
        long startTime = System.nanoTime();
        bubbleSort(input);
        long endTime = System.nanoTime();
        System.out.println("Bubble Sorted Array: " + Arrays.toString(input));
        System.out.printf("Time elapsed for Bubble Sort (%d elements): %fs\n", n, (endTime - startTime) / 1e9);
        // calculate elapse time for selection sort
        input = generateInput(n);
        startTime = System.nanoTime();
        selectionSort(input);
        endTime = System.nanoTime();
        System.out.println("Selection Sorted Array: " + Arrays.toString(input));
        System.out.printf("Time elapsed for Selection Sort (%d elements): %fs\n", n, (endTime - startTime) / 1e9);
        // calculate elapse time for insertion sort
        input = generateInput(n);
        startTime = System.nanoTime();
        insertionSort(input);
        endTime = System.nanoTime();
        System.out.println("Insertion Sorted Array: " + Arrays.toString(input));
        System.out.printf("Time elapsed for Insertion Sort (%d elements): %fs\n", n, (endTime - startTime) / 1e9);

    }

    // Worst case: O(n ^ 2)
    // Best case: O(n ^ 2) for this implementation (can be shortened to O(n)
    // if we check if for 0 swaps in each iteration, if so terminate outer loop as the list is already sorted)
    private static void bubbleSort(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = 0; j < input.length - 1 - i; j++) {
                if (input[j] > input[j + 1]) {
                    int temp = input[j];
                    input[j] = input[j + 1];
                    input[j + 1] = temp;
                }
            }
        }
    }

    // Best case: O(n ^ 2)
    // Worst case: O(n ^ 2)
    // Slightly faster as the exact order of growth is n ^ 2/ 2
    private static void selectionSort(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            int currentMinIndex = i;
            int currentMin = input[i];
            for (int j = i + 1; j < input.length; j++) {
                if (input[j] < currentMin) {
                    currentMinIndex = j;
                    currentMin = input[j];
                }
            }
            int temp = input[i];
            input[i] = input[currentMinIndex];
            input[currentMinIndex] = temp;
        }
    }

    // Best case: O(n)
    // Worst case: O(n ^ 2)
    private static void insertionSort(int[] input) {
        for (int i = 1; i < input.length; i++) {
            int j = i;
            while (j >= 1 && input[j - 1] > input[j]) {
                int temp = input[j];
                input[j] = input[j - 1];
                input[j - 1] = temp;
                j--;
            }
        }
    }

    // generates an input array of length n with bound n
    private static int[] generateInput(int n) {
        Random random = new Random();
        // Initialise array with random generated numbers
        int[] input = new int[n];
        for (int i = 0; i < input.length; i++) {
            input[i] = random.nextInt(n);
        }
        return input;
    }
}
