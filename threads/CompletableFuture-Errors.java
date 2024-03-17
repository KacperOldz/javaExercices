import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.concurrent.ThreadLocalRandom;


public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        final boolean err = true;

        CompletableFuture<Integer> losowaLiczba = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());

            if (err) {
                try {
                    throw new IllegalAccessException("Wrong Value!!");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            return randomNumber();
        });

        losowaLiczba.thenCombine(losowaLiczba, (liczba1, liczba2) -> {
            return liczba1 + liczba2;
        }).exceptionally(exception -> {
            return 2;
        }).thenApply(r -> {
            return r * -1;
        }).thenAccept(r -> {
            System.out.println(r);
        });

        executor.shutdown();
    }

    public static Integer randomNumber() {
        return ThreadLocalRandom.current().nextInt();
    }
}
