package ua.edu.ucu.utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {
    
    @Test
    public void testPeek() {
        Queue queue = new Queue();
        queue.enqueue(1);
        assertEquals(queue.peek(), 1);
    }
    @Test
    public void testDequeue(){
        Queue queue = new Queue();
        queue.enqueue(2);
        assertEquals(queue.peek(), 2);
        queue.enqueue(1);
        assertEquals(queue.peek(), 2);
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(3);
        assertEquals(queue.peek(), 3);
    }
}
