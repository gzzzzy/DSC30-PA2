

/*
 * NAME: Zhiyu Gao
 * PID: A17245309
 */

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Doubly-Linked List Implementation
 *
 * @author Zhiyu Gao
 * @since 8/14/2021
 */

    public class DoublyLinkedList {

    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {
        int data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(int element) {
            data=element;
            next=null;
            prev=null;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(int element, Node nextNode, Node prevNode) {
            data=element;
            next=nextNode;
            prev=prevNode;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            prev=p;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            next=n;
        }

        /**
         * Set the element
         *
         * @param e new element
         */
        public void setElement(int e) {
            data=e;
        }

        /**
         * Accessor to get the next Node in the list
         */
        public Node getNext() {
            return next; 
        }

        /**
         * Accessor to get the prev Node in the list
         */
        public Node getPrev() {
            return prev; 
        }

        /**
         * Accessor to get the Nodes Element
         */
        public int getElement() {
            return data;
        }

        /**
         * Remove this node from the list. Update previous and next nodes
         */
        public void remove() {
            getPrev().setNext(getNext());
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        nelems=0;
        head=new Node(0,null,null);
        tail=new Node(0, null, head);
        head.setNext(tail);
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return Number of elements currently on the list
     */
    public int size() {
        return nelems;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index The index of the desired element.
     * @return The element stored in the Node with the desired index.
     * @throws IndexOutOfBoundsException if index received is out of bounds for
     *                                   the current list.
     */
    public int get(int index) throws IndexOutOfBoundsException {
        if(index<0||index>=nelems) throw new IndexOutOfBoundsException();
        Node currNode=getNth(index);
        return currNode.getElement();
    }

    /**
     * Add an element to the end of the list
     *
     * @param data data to be added
     */
    public boolean add(int data) {
        Node addNode=new Node(data);
        addNode.prev=tail.prev;
        tail.prev.next=addNode; // also addNode.prev.next=addNode;
        addNode.next=tail;
        tail.prev=addNode;
        ++nelems;
        return true;
    }

    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room.
     *
     * @param index Where in the list to add the element.
     * @param data  Data to be added.
     * @throws IndexOutOfBoundsException if index received is out of bounds for
     *                                   the current list.
     */
    public void add(int index, int data)
            throws IndexOutOfBoundsException {
        if(index<0||index>nelems) throw new IndexOutOfBoundsException();
        Node addNode=new Node(data), currNode=getNth(index-1);
        addNode.next=currNode.next;
        addNode.next.prev=addNode;
        currNode.next=addNode;
        addNode.prev=currNode;
        ++nelems;
    }

    /**
     * Sets the value of an element at a certain index in the list.
     *
     * @param index Where in the list the data should be added.
     * @param data  Data to add.
     * @return Element that was previously at this index.
     * @throws IndexOutOfBoundsException if index received is out of bounds for
     *                                   the current list.
     */
    public int set(int index, int data)
            throws IndexOutOfBoundsException {
        if(index<0||index>=nelems) throw new IndexOutOfBoundsException();
        Node setNode=getNth(index);
        int re=setNode.getElement();
        setNode.setElement(data);
        return re;
    }

    /**
     * remove the element from position index in the list
     * @param index:index where in the list the data should be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if index<0 || index >= size
     */
    public int remove(int index) throws IndexOutOfBoundsException {
        if(index<0||index>=nelems) throw new IndexOutOfBoundsException();
        Node removeNode=getNth(index);
        removeNode.prev.next=removeNode.next;
        removeNode.next.prev=removeNode.prev;
        --nelems;
        return removeNode.getElement();
    }

    /**
     * Clear the linked list
     */
    public void clear() {
        head.next=tail;
        nelems=0;
    }

    /**
     * Determine if the list empty
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return nelems==0;
    }

    // Helper method to get the Node at the Nth index
    private Node getNth(int index) {
        Node currNode=head;
        while(index>=0){
            currNode=currNode.next;
            --index;
        }
        return currNode;
    }

    /**
     * Determine if this list contains the given data
     * @param data data to find
     * @return true if list contains given data, false otherwise
     */
    public boolean contains(int data) {
        Node currNode=head.next;
        while(currNode!=tail){
            if(currNode.data==data) 
                return true;
            currNode=currNode.next;
        }
        return false;
    }

    /**
     * String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"
     * @return string representation
     */
    @Override
    public String toString() {
        String re=new String("[(head) -> ");
        Node currNode=head.next;
        while(currNode!=tail){
            re+=String.format("%d -> ", currNode.data);
            currNode=currNode.getNext();
        }
        re+="(tail)]";
        return re;
    }
}

