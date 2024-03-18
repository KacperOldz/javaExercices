# Repository made to store exercices that I made while learning JAVA language

## Subjects:

1. Threads

## Documentation

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

In summary, `thenApply()` and `thenCompose()` are used to process the result of a CompletableFuture, `thenAccept()` executes a task with the result without returning a value, `exceptionally()` handles exceptions, while `allOf()` and `anyOf()` are used for waiting on multiple CompletableFutures to complete, with `allOf()` waiting for all and `anyOf()` waiting for any.
