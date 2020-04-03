package projectCode20280.assignment;

import projectCode20280.DoublyLinkedList;
import projectCode20280.HeapPriorityQueue;
import projectCode20280.List;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class HeapSort {

    public static void main(String[] args) throws IOException {
        int start = 4000;
        int end = 50000;
        double factor = 1.08;
        System.out.println("starting unsortedListSort...");
        unsortedListSort(start, end, factor);
        System.out.println("unsortedListSort completed.");
        System.out.println("starting naiveHeapSort...");
        naiveHeapSort(start, end, factor);
        System.out.println("naiveHeapSort completed.");
        System.out.println("starting fasterHeapSort...");
        fasterHeapSort(start, end, factor);
        System.out.println("fasterHeapSort completed.");
    }

    public static void unsortedListSort(int start, int limit, double factor) throws IOException {
        FileWriter fw = new FileWriter("unsorted.txt");
        fw.append("Unsorted heap sort\n");
        while (start < limit) {
            // Generate distinct data set and store dataSet in dllist
            DoublyLinkedList<Integer> dataSet = new DoublyLinkedList<>();
            new Random().ints(start, 0, Integer.MAX_VALUE).boxed().forEach(dataSet::addLast);
            // Start timer
            long startTime = System.nanoTime();
            // Load data into heap
            DoublyLinkedList<Integer> heap = new DoublyLinkedList<>();
            for (int i = 0; i < start; i++) {
                heap.addLast(dataSet.removeLast());
            }
            // Call removeMin for every element
            for (int i = 0; i < start; i++) {
                dataSet.addLast(removeMin(heap));
            }
            // Stop timer
            long elapsedTime = System.nanoTime() - startTime;
            fw.append(String.format("%d, %d, %s\n", start, elapsedTime, isSorted(dataSet)));
            start *= factor;
        }
        fw.close();
    }

    public static void fasterHeapSort(int start, int limit, double factor) throws IOException {
        FileWriter fw = new FileWriter("fasterHeapSort.txt");
        fw.append("Faster Heap Sort\n");
        while (start < limit) {
            // Generate distinct data set and store dataSet in dllist
            DoublyLinkedList<Integer> dataSet = new DoublyLinkedList<>();
            new Random().ints(start, 0, Integer.MAX_VALUE).boxed().forEach(dataSet::addLast);
            // Start timer
            long startTime = System.nanoTime();
            // Load data into array first, then load all into heap
            Integer[] array = new Integer[start];
            for (int i = 0; i < start; i++) {
                array[i] = dataSet.removeFirst();
            }
            HeapPriorityQueue<Integer, Integer> heap = new HeapPriorityQueue<>(array, array);
            // Call removeMin for every element
            for (int i = 0; i < start; i++) {
                dataSet.addLast(heap.removeMin().getKey());
            }
            // Stop timer
            long elapsedTime = System.nanoTime() - startTime;
            fw.append(String.format("%d, %d, %s\n", start, elapsedTime, isSorted(dataSet)));
            start *= factor;
        }
        fw.close();
    }

    public static void naiveHeapSort(int start, int limit, double factor) throws IOException {
        FileWriter fw = new FileWriter("naiveHeapSort.txt");
        fw.append("Naive Heap Sort\n");
        while (start < limit) {
            // Generate distinct data set and store dataSet in dllist
            DoublyLinkedList<Integer> dataSet = new DoublyLinkedList<>();
            new Random().ints(start, 0, Integer.MAX_VALUE).boxed().forEach(dataSet::addLast);
            // Start timer
            long startTime = System.nanoTime();
            // Load data into heap
            HeapPriorityQueue<Integer, Integer> heap = new HeapPriorityQueue<>();
            for (int i = 0; i < start; i++) {
                int val = dataSet.removeFirst();
                heap.insert(val, val);
            }
            // Call removeMin for every element
            for (int i = 0; i < start; i++) {
                dataSet.addLast(heap.removeMin().getKey());
            }
            // Stop timer
            long elapsedTime = System.nanoTime() - startTime;
            fw.append(String.format("%d, %d, %s\n", start, elapsedTime, isSorted(dataSet)));
            start *= factor;
        }
        fw.close();
    }

    public static int removeMin(List<Integer> list) {
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }
        int minIndex = 0;
        int minValue = list.get(0);
        int i = 0;
        for (Integer val : list) {
            if (val < minValue) {
                minValue = val;
                minIndex = i;
            }
            i++;
        }
        list.remove(minIndex);
        return minValue;
    }


    public static boolean isSorted(List<Integer> list) {
        Integer prev = null;
        for (Integer elem : list) {
            if (prev != null && prev.compareTo(elem) > 0) {
                return false;
            }
            prev = elem;
        }
        return true;
    }
}
