package projectCode20280.assignment;

import projectCode20280.DoublyLinkedList;
import projectCode20280.List;

/**
 * Q2 for assignment one, linked list addition
 */
public class ListAddition {
    public static void main(String[] args) {
        List<Integer> list1 = new DoublyLinkedList<>();
        List<Integer> list2 = new DoublyLinkedList<>();
        list1.addLast(9);
        list1.addLast(8);
        list1.addLast(3);
        list2.addLast(1);
        list2.addLast(0);
        list2.addLast(4);
        list2.addLast(3);
        System.out.println(add(list1, list2));
    }

    private static List<Integer> add(List<Integer> l1, List<Integer> l2) {
        List<Integer> list1 = new DoublyLinkedList<>();
        List<Integer> list2 = new DoublyLinkedList<>();
        for (int i : l1) {
            list1.addFirst(i);
        }
        for (int i : l2) {
            list2.addFirst(i);
        }
        List<Integer> output = new DoublyLinkedList<>();
        int carry = 0;
        while (!list1.isEmpty() || !list2.isEmpty()) {
            int sum = 0;
            if (!list1.isEmpty()) {
                sum += list1.removeFirst();
            }
            if (!list2.isEmpty()) {
                sum += list2.removeFirst();
            }
            sum += carry;
            carry = sum / 10;
            output.addFirst(sum % 10);
        }
        if (carry != 0) {
            output.addFirst(1);
        }
        return output;
    }
}
