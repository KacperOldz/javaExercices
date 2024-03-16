import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Uruchomienie!");

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        Runnable work1 = () -> {
            try {
                System.out.println(Thread.currentThread().getName());
                System.out.println("Odkurzanie...");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable work2 = () -> {
            try {
                System.out.println(Thread.currentThread().getName());
                System.out.println("Zmywanie...");
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable work3 = () -> {
            try {
                System.out.println(Thread.currentThread().getName());
                System.out.println("Palenie opon...");
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        executor.scheduleAtFixedRate(work3, 0, 3, TimeUnit.SECONDS); // Nie powtarza się z powodu shutdown()
        executor.schedule(work1, 3,TimeUnit.SECONDS);
        executor.schedule(work2,1,TimeUnit.SECONDS);
        executor.schedule(work3,2,TimeUnit.SECONDS);

        executor.shutdown();
    }

}
