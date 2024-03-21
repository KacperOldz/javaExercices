# Repository made to store exercices that I made while learning JAVA language

## Subjects:

1. Threads
2. Data persistence

## Documentation:

### 1.Threads

1. `thenApply()`: This method applies a function to the result of a CompletableFuture when it completes, returning a new CompletableFuture with the transformed result.
Example:
```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> modifiedFuture = future.thenApply(result -> result + " World");
```
</br>
2. `thenCompose()`: It applies a function returning a CompletableFuture to the result of a CompletableFuture, flattening the nested CompletableFutures into a single CompletableFuture.
Example:
```java
CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> " World");
CompletableFuture<String> combinedFuture = future1.thenCompose(result1 -> future2.thenApply(result2 -> result1 + result2));
```
</br>
3. `thenAccept()`: This method accepts a consumer that will be executed with the result of the CompletableFuture when it completes, but it doesn't return a result.
Example:
```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
future.thenAccept(result -> System.out.println(result));
```

</br>
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

</br>
5. `allOf()`: Waits for all of the provided CompletableFutures to complete, returning a CompletableFuture that completes when all of them have completed, regardless of their individual results.
Example:
```java
CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> " World");
CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(future1, future2);
```

</br>
6. `anyOf()`: Waits for any of the provided CompletableFutures to complete, returning a CompletableFuture that completes as soon as any of them completes, regardless of their individual results.
Example:
```java
CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> " World");
CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2);
```

</br>
7. `Executor`: The Executor interface provides a way to manage and execute tasks asynchronously in a thread pool. It decouples task submission from task execution, allowing you to focus on the logic of your tasks while the Executor handles the details of thread management.
Example:
```java
Executor executor = Executors.newFixedThreadPool(5);
executor.execute(() -> {
    // Task logic here
    System.out.println("Task executed asynchronously.");
});
```

</br>
8. `Thread`: In Java, a Thread represents an independent path of execution within a program. It allows concurrent execution of multiple tasks or operations within a single process.
Example:
```java
Thread thread = new Thread(() -> {
    // Task logic here
    System.out.println("Thread executing.");
});
thread.start(); // Start the thread
```

</br>
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

</br>
10. `Callable`: The `Callable` interface in Java is similar to `Runnable`, but it can return a result and throw a checked exception. It represents a task that can be executed asynchronously, typically by submitting it to an `ExecutorService` for execution.
Example:
```java
import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        // Task logic here
        return 42;
    }
}
```

</br>
11. `synchronized`: The `synchronized` keyword in Java is used to control access to critical sections of code by allowing only one thread to execute a synchronized block or method at a time. It provides a simple and effective way to achieve mutual exclusion and thread safety.
Example:
```java
public class SynchronizedExample {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }
}
```

</br>
12. `Atomic` types: Atomic types in Java, such as `AtomicInteger`, `AtomicLong`, etc., provide atomic operations on single variables without the need for explicit synchronization. These classes ensure that operations like incrementing, decrementing, or updating a variable are performed atomically, without interference from other threads.
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

</br>
### 2. Data persistence

In Java JDBC (Java Database Connectivity), a connection and statement are fundamental objects used for interacting with a relational database.
1. Connection: 
   - A Connection object represents a connection to a specific database. 
   - It is typically established using the `DriverManager.getConnection()` method by providing the database URL, username, and password.
Example of creating a connection:
```java
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
```

</br>
2. Statement:
   - A Statement object represents a SQL statement that you want to execute against a database.
   - There are two main types of statements: Statement and PreparedStatement.
   - `Statement`: It is used to execute static SQL statements that do not contain parameters.
   - Statements are executed using methods like `executeQuery()` for SELECT statements that return a ResultSet, `executeUpdate()` for SQL statements that modify data, and `execute()` for general-purpose execution.
Example of creating and executing a Statement:
```java
Statement statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery("SELECT * FROM my_table");
while (resultSet.next()) {
    // Process the retrieved data
}
```
In JDBC (Java Database Connectivity), `Connection` objects provide methods to execute SQL statements against a database and manage transactions. Two important methods related to these tasks are `execute()` and `commit()`:

</br>
3. connection.execute():
   - The `execute()` method of the `Connection` interface is used to execute SQL statements against the database.
Example of using `execute()` to execute a SQL statement:
```java
Statement statement = connection.createStatement();
boolean success = statement.execute("CREATE TABLE my_table (id INT PRIMARY KEY, name VARCHAR(50))");
```

</br>
4. connection.commit():
   - The `commit()` method of the `Connection` interface is used to commit changes made in the current transaction to the database.
Example of using `commit()` to commit a transaction:
```java
connection.setAutoCommit(false); // Disable auto-commit mode
// Execute multiple SQL statements as part of the transaction
// ...
connection.commit(); // Commit the transaction
```

</br>
5. EntityManagerFactory:
`EntityManagerFactory` is an interface that represents a factory for `EntityManager` instances.
Example of creating an `EntityManagerFactory`:
```java
EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
```

</br>
6. EntityManager:
   - `EntityManager` is an interface that represents a JPA runtime interface for interacting with the persistence context, managing entity instances, and performing database operations.
   - It serves as a bridge between the application code and the underlying database, providing methods for CRUD (Create, Read, Update, Delete) operations, JPQL queries, and transaction management.
Example of using an `EntityManager`:
```java
EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
EntityManager entityManager = entityManagerFactory.createEntityManager();
entityManager.getTransaction().begin();
// Perform database operations using EntityManager
entityManager.persist(entity);
entityManager.merge(entity);
entityManager.remove(entity);
// Commit the transaction
entityManager.getTransaction().commit();

entityManager.close();
entityManagerFactory.close();
```

</br>
7. @Embedded, @Embeddable - Makes class automatically convent to the part of table while executing
```
@Embeddable
public class Address {
    private String city;
    private String street;
    private String postalCode;

    public Address(String city, String street, String postalCode) {
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }
}
```
```
    @Embedded
    Address address;
```

</br>
## Definitions

### 1.Threads

1. Race Condition - A race condition in occurs when multiple threads access shared resources or data concurrently, leading to unpredictable outcomes due to the timing of their execution. To avoid make problematic method synchronized or wrap the problematic code block/variable in synchronized(this) { } or Atimic type. Atomic types are more efficient but compel to use of object which makes code take use more memory.

</br>
### 2. Data persistence

1. Maven - is a build automation tool used primarily for Java projects. It helps manage the project's build process, dependencies, and documentation in a consistent and efficient manner. Maven uses a project object model (POM) file to describe the project's structure, dependencies, and build process configuration.
   </br>
2. Java Database Connectivity (JDBC) is an API (Application Programming Interface) provided by Java that allows Java applications to interact with relational databases. It provides a standard way for Java applications to perform database operations such as querying data, updating records, and executing stored procedures. Old and rarely used, but may be useful for migration into more efficient tech
</br>
3. JPA stands for Java Persistence API. It is a standard specification for ORM (Object-Relational Mapping) frameworks in Java, which allows developers to map Java objects to relational database tables and vice versa. JPA provides a high-level abstraction over the underlying database interactions, making it easier for developers to work with databases in Java applications.
</br>
4. Hibernate is a popular open-source ORM (Object-Relational Mapping) framework for Java applications. It provides a powerful and flexible way to map Java objects to relational database tables and vice versa, simplifying database interactions and reducing the amount of boilerplate code required for database access.
</br>
5. Entity - is a lightweight, persistent domain object that represents a data entity stored in a relational database
</br>
6. CRUD - stands for Create, Read, Update, and Delete. It is an acronym commonly used in the context of database management and programming to describe the four basic functions that are often implemented in database applications
