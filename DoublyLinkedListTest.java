

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DoublyLinkedListTest {
    @Test
    public void ConstructorTest(){
        DoublyLinkedList list=new DoublyLinkedList();
        assertEquals(0, list.size());
        assertEquals(true, list.isEmpty());
        assertEquals(list.toString(), "[(head) -> (tail)]");
    }

    @Test
    public void nonGetterSetterTest(){
        DoublyLinkedList list=new DoublyLinkedList();
        for(int i=0;i<10;++i){
            list.add(i);
        }
        assertEquals(10, list.size());
        assertEquals(false, list.isEmpty());
        assertEquals(true, list.contains(9));
        assertEquals(false, list.contains(10));
        assertEquals(list.toString(), "[(head) -> 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> (tail)]");
        assertEquals(0, list.remove(0));
        assertEquals(list.toString(), "[(head) -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> (tail)]");
        assertEquals(9, list.remove(8));
        assertEquals(1, list.remove(0));
        assertEquals(false, list.contains(0));
        assertEquals(false, list.isEmpty());
        assertEquals(7,list.size());
        list.clear();
        assertEquals(list.toString(), "[(head) -> (tail)]");
        assertEquals(0, list.size());
        assertEquals(true, list.isEmpty());
        list.add(0,1);
        list.add(1,1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void expectionTest(){
        DoublyLinkedList list=new DoublyLinkedList();
        list.add(2,1);
    }

    @Test
    public void getterSetterTest(){
        DoublyLinkedList list=new DoublyLinkedList();
        for(int i=0;i<10;++i){
            list.add(i);
        }
        list.set(0, 2);
        assertEquals(2, list.get(0));
        list.remove(0);
        assertEquals(1, list.get(0));
        assertEquals(9, list.get(8));
    }
}
