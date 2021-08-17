

import java.util.EmptyStackException;

/**
 * Stack implementation using Doubly-linked list.
 */
public class DLLStack {

    private DoublyLinkedList stack;

    public DLLStack() {
        stack=new DoublyLinkedList();
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void push(int data) {
        stack.add(data);
    }

    public int pop() {
        if(stack.isEmpty()) throw new EmptyStackException();
        return stack.remove(stack.size()-1);
    }

    public int peek() {
        if(stack.isEmpty()) throw new EmptyStackException();
        return stack.get(stack.size()-1);
    }

}
