package projectCode20280.exercises;

import projectCode20280.LinkedStack;

public class ReverseStack {
    public static <E> LinkedStack<E> reverseStack(LinkedStack<E> stack) {
        LinkedStack<E> newStack = new LinkedStack<>();
        while (!stack.isEmpty()) {
            newStack.push(stack.pop());
        }
        return newStack;
    }
}
