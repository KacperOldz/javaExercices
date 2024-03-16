import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Uruchomienie!");

        ExecutorService executor = Executors.newFixedThreadPool(2);

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

        executor.submit(work1);
        executor.submit(work2);
        executor.submit(work3);

        executor.shutdown();
    }

}
