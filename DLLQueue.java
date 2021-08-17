

import java.util.NoSuchElementException;

/**
 * Queue implementation using Doubly-linked list.
 */
public class DLLQueue {

    private DoublyLinkedList queue;

    public DLLQueue() {
        queue=new DoublyLinkedList();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void enqueue(int data) {
        queue.add(data);
    }

    public int dequeue() {
        if(queue.isEmpty()) throw new NoSuchElementException();
        return queue.remove(0);
    }

    public int peek() {
        if(queue.isEmpty()) throw new NoSuchElementException();
        return queue.get(0);
    }

}
