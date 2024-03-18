import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditions {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Counter count = new Counter();

        for(int i=0;i<1000;i++) {
            executor.submit(()->{
                count.increase();
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(count.getValue());
}
}

class Counter {
    private AtomicInteger value = new AtomicInteger(0);

    /*  synchronized public */ void increase(){
        // value = value + 1;
        value.getAndIncrement();
    }

    public int getValue(){
        //return value;
        return value.get();
    }
}



