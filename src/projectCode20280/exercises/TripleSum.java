package projectCode20280.exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TripleSum {
    private ArrayList<Integer> list;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        while (in.hasNextInt()) {
            list.add(in.nextInt());
        }

        // Sorts list in O (n log n)
        Collections.sort(list);
        int triplets = 0;
        // Algorithm for finding the no. of triples is O (n ^ 2 log n)
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int sum = list.get(i) + list.get(j);
                int index = Collections.binarySearch(list, -1 * sum);
                if (index != -1 && index > j) {
                    triplets++;
                }
            }
        }

        // Overall runtime is O (n ^ 2 log n)
        System.out.println("Number of triplets: " + triplets);
    }
}
