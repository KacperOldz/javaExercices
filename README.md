# Repository made to store exercices that I made while learning JAVA language

## Subjects:

1. Threads

## Documentation:

### 1.Threads
1. `thenApply()`: This method applies a function to the result of a CompletableFuture when it completes, returning a new CompletableFuture with the transformed result.

Example:
```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> modifiedFuture = future.thenApply(result -> result + " World");
```

2. `thenCompose()`: It applies a function returning a CompletableFuture to the result of a CompletableFuture, flattening the nested CompletableFutures into a single CompletableFuture.

Example:
```java
CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> " World");
CompletableFuture<String> combinedFuture = future1.thenCompose(result1 -> future2.thenApply(result2 -> result1 + result2));
```

3. `thenAccept()`: This method accepts a consumer that will be executed with the result of the CompletableFuture when it completes, but it doesn't return a result.

Example:
```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
future.thenAccept(result -> System.out.println(result));
```

4. `exceptionally()`: It handles exceptions that may occur during the execution of a CompletableFuture, allowing you to provide a fallback value or perform recovery actions.

Example:
```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    if (Math.random() < 0.5) {
        throw new RuntimeException("Error occurred!");
    }
    return "No error";
}).exceptionally(ex -> "Handled exception: " + ex.getMessage());
```

5. `allOf()`: Waits for all of the provided CompletableFutures to complete, returning a CompletableFuture that completes when all of them have completed, regardless of their individual results.

Example:
```java
CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> " World");
CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(future1, future2);
```

6. `anyOf()`: Waits for any of the provided CompletableFutures to complete, returning a CompletableFuture that completes as soon as any of them completes, regardless of their individual results.

Example:
```java
CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> " World");
CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2);
```

7. `Executor`: The Executor interface provides a way to manage and execute tasks asynchronously in a thread pool. It decouples task submission from task execution, allowing you to focus on the logic of your tasks while the Executor handles the details of thread management.

Example:
```java
Executor executor = Executors.newFixedThreadPool(5);
executor.execute(() -> {
    // Task logic here
    System.out.println("Task executed asynchronously.");
});
```

8. `Thread`: In Java, a Thread represents an independent path of execution within a program. It allows concurrent execution of multiple tasks or operations within a single process.

Example:
```java
Thread thread = new Thread(() -> {
    // Task logic here
    System.out.println("Thread executing.");
});
thread.start(); // Start the thread
```

9. `Runnable`: The Runnable interface represents a task or unit of work that can be executed asynchronously by a Thread. It provides a way to encapsulate the code to be executed into a separate entity.

Example:
```java
Runnable task = () -> {
    // Task logic here
    System.out.println("Runnable task executed.");
};
Thread thread = new Thread(task);
thread.start(); // Start the thread
```


10. `synchronized`: The `synchronized` keyword in Java is used to control access to critical sections of code by allowing only one thread to execute a synchronized block or method at a time. It provides a simple and effective way to achieve mutual exclusion and thread safety.

Example:
```java
public class SynchronizedExample {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }
}
```

11. `Atomic` types: Atomic types in Java, such as `AtomicInteger`, `AtomicLong`, etc., provide atomic operations on single variables without the need for explicit synchronization. These classes ensure that operations like incrementing, decrementing, or updating a variable are performed atomically, without interference from other threads.

Example with `AtomicInteger`:
```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }
}
```

## Definitions
1. Race Condition - A race condition in occurs when multiple threads access shared resources or data concurrently, leading to unpredictable outcomes due to the timing of their execution. To avoid make problematic method synchronized or wrap the problematic code block/variable in synchronized(this) { } or Atimic type. Atomic types are more efficient but compel to use of object which makes code take use more memory.
