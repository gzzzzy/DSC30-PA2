

import static org.junit.Assert.assertEquals;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import org.junit.Test;

public class DLLQueneTest {
    @Test
    public void constructorTest() {
        DLLQueue ddlq = new DLLQueue();
        assertEquals(0, ddlq.size());
        assertEquals(true, ddlq.isEmpty());
        try {
            ddlq.dequeue();
        } catch (NoSuchElementException e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    public void methodTest(){
        DLLQueue ddlq = new DLLQueue();
        int i;
        for(i=0;i<5;++i)
            ddlq.enqueue(i);
        while(i!=0){
            assertEquals(i, ddlq.size());
            assertEquals(5-i, ddlq.peek());
            assertEquals(5-i, ddlq.dequeue());
            assertEquals(i-1==0?true:false,ddlq.isEmpty());
            --i;
        }
    }
}

