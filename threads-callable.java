import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        Callable<Integer> test1 = () -> {
            TimeUnit.SECONDS.sleep(3);
            return 20;
        };

        Callable<Integer> test2 = () -> {
            TimeUnit.SECONDS.sleep(5);
            return 5;
        };

        Callable<Integer> test3 = () -> {
            TimeUnit.SECONDS.sleep(2);
            return 12;
        };

        List<Callable<Integer>> callableList = Arrays.asList(test1, test2, test3);
        List<Future<Integer>> futures = executor.invokeAll(callableList);

        Integer result = executor.invokeAny(callableList);

        for(Future<Integer> f : futures){
            System.out.println(f.get());
        }

        executor.shutdown();
    }

}
