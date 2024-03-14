import java.util.*;
import java.util.concurrent.TimeUnit;

public class App {

  public static void main(String[] args) throws InterruptedException {
    
    Runnable countdown = () -> {
      try {
        for(int i = 1; i <= 10; i++) {
          System.out.println(i);
          TimeUnit.MILLISECONDS.sleep(10);
        } 
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };
    
    Runnable blastOff = () -> {
      System.out.println("Blastoff");
    };
    
    Thread countdownThread = new Thread(countdown, "countdown-thread");
    Thread blastOffThread = new Thread(blastOff, "blastoff-thread");
    
    countdownThread.start();
    countdownThread.join();
    
    blastOffThread.start();
  }

}
