import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<Long> userIdFuture = CompletableFuture.supplyAsync( ()->{
           try {
               TimeUnit.SECONDS.sleep(2);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           return getUserId();
        });

        CompletableFuture<Long> anotherUserIdFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 23L;
        });

        CompletableFuture<Void> future = userIdFuture.thenCompose(
                userId -> CompletableFuture.supplyAsync( () -> {
                    return getDicount(userId);
        })).thenAccept( disc -> {
                    System.out.println(disc);
                }
        );

        CompletableFuture<Long> future2 = userIdFuture.thenCombine(anotherUserIdFuture, (result1, result2) -> {
            return result1 + result2;
        });
        
        System.out.println(future2.get());

        executor.shutdown();
    }

    public static Long getUserId() {
        return 324L;
    }

    public static Double getDicount(Long userId) {
        return 0.15;
    }
}
