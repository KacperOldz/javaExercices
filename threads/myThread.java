import java.util.*;
import java.util.stream.IntStream;

public class myThread extends Thread {
  
    public myThread(String name) {
      super(name);
    }
  
    @Override
    public void run() {
        IntStream.rangeClosed(1,20).forEach( index -> 
        {
          System.out.println(index + " |||   Wykonywany wątek: " + Thread.currentThread().getName());
        });
    }
}
