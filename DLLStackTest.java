

import static org.junit.Assert.assertEquals;

import java.util.EmptyStackException;

import org.junit.Test;

public class DLLStackTest {
    @Test
    public void constructorTest() {
        DLLStack ddls = new DLLStack();
        assertEquals(0, ddls.size());
        assertEquals(true, ddls.isEmpty());
        try {
            ddls.pop();
        } catch (EmptyStackException e) {
            assertEquals(null, e.getMessage());
        }
    }

    @Test
    public void methodTest(){
        DLLStack ddls = new DLLStack();
        int i;
        for(i=0;i<5;++i)
            ddls.push(i);
        while(i!=0){
            assertEquals(i, ddls.size());
            assertEquals(i-1, ddls.peek());
            assertEquals(i-1, ddls.pop());
            assertEquals(i-1==0?true:false,ddls.isEmpty());
            --i;
        }
    }
}
