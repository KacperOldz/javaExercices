import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        }, executor);

        CompletableFuture<Integer> result = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 12;
        }, executor);
        result.thenApply(r -> {
            return r * 2;
        }).thenAccept(r -> {
            System.out.println(r);
        });


        executor.shutdown();
    }

}
