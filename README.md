# Repository for Java Exercises

## Subjects:

1. Threads
2. Data persistence
3. Functional programming

## Documentation:

### 1. Threads

1. **thenApply()**:
   - This method applies a function to the result of a CompletableFuture when it completes, returning a new CompletableFuture with the transformed result.
   - Example:
     ```java
     CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
     CompletableFuture<String> modifiedFuture = future.thenApply(result -> result + " World");
     ```

2. **thenCompose()**:
   - It applies a function returning a CompletableFuture to the result of a CompletableFuture, flattening the nested CompletableFutures into a single CompletableFuture.
   - Example:
     ```java
     CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
     CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> " World");
     CompletableFuture<String> combinedFuture = future1.thenCompose(result1 -> future2.thenApply(result2 -> result1 + result2));
     ```

3. **thenAccept()**:
   - This method accepts a consumer that will be executed with the result of the CompletableFuture when it completes, but it doesn't return a result.
   - Example:
     ```java
     CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
     future.thenAccept(result -> System.out.println(result));
     ```

4. **exceptionally()**:
   - It handles exceptions that may occur during the execution of a CompletableFuture, allowing you to provide a fallback value or perform recovery actions.
   - Example:
     ```java
     CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
         if (Math.random() < 0.5) {
             throw new RuntimeException("Error occurred!");
         }
         return "No error";
     }).exceptionally(ex -> "Handled exception: " + ex.getMessage());
     ```

5. **allOf()**:
   - Waits for all of the provided CompletableFutures to complete, returning a CompletableFuture that completes when all of them have completed, regardless of their individual results.
   - Example:
     ```java
     CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
     CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> " World");
     CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(future1, future2);
     ```

6. **anyOf()**:
   - Waits for any of the provided CompletableFutures to complete, returning a CompletableFuture that completes as soon as any of them completes, regardless of their individual results.
   - Example:
     ```java
     CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
     CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> " World");
     CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2);
     ```

7. **Executor**:
   - The Executor interface provides a way to manage and execute tasks asynchronously in a thread pool. It decouples task submission from task execution, allowing you to focus on the logic of your tasks while the Executor handles the details of thread management.
   - Example:
     ```java
     Executor executor = Executors.newFixedThreadPool(5);
     executor.execute(() -> {
         // Task logic here
         System.out.println("Task executed asynchronously.");
     });
     ```

8. **Thread**:
   - In Java, a Thread represents an independent path of execution within a program. It allows concurrent execution of multiple tasks or operations within a single process.
   - Example:
     ```java
     Thread thread = new Thread(() -> {
         // Task logic here
         System.out.println("Thread executing.");
     });
     thread.start(); // Start the thread
     ```

9. **Runnable**:
   - The Runnable interface represents a task or unit of work that can be executed asynchronously by a Thread. It provides a way to encapsulate the code to be executed into a separate entity.
   - Example:
     ```java
     Runnable task = () -> {
         // Task logic here
         System.out.println("Runnable task executed.");
     };
     Thread thread = new Thread(task);
     thread.start(); // Start the thread
     ```

10. **Callable**:
    - The Callable interface in Java is similar to Runnable, but it can return a result and throw a checked exception. It represents a task that can be executed asynchronously, typically by submitting it to an ExecutorService for execution.
    - Example:
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

11. **synchronized**:
    - The synchronized keyword in Java is used to control access to critical sections of code by allowing only one thread to execute a synchronized block or method at a time. It provides a simple and effective way to achieve mutual exclusion and thread safety.
    - Example:
      ```java
      public class SynchronizedExample {
          private int count = 0;

          public synchronized void increment() {
              count++;
          }
      }
      ```

12. **Atomic types**:
    - Atomic types in Java, such as AtomicInteger, AtomicLong, etc., provide atomic operations on single variables without the need for explicit synchronization. These classes ensure that operations like incrementing, decrementing, or updating a variable are performed atomically, without interference from other threads.
    - Example with AtomicInteger:
      ```java
      import java.util.concurrent.atomic.AtomicInteger;

      public class AtomicExample {
          private AtomicInteger count = new AtomicInteger(0);

          public void increment() {
              count.incrementAndGet();
          }
      }
      ```

### 2. Data Persistence

In Java JDBC (Java Database Connectivity), a connection and statement are fundamental objects used for interacting with a relational database.

1. **Connection**:
   - A Connection object represents a connection to a specific database.
   - It is typically established using the DriverManager.getConnection() method by providing the database URL, username, and password.
   - Example of creating a connection:
     ```java
     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");
     ```

2. **Statement**:
   - A Statement object represents a SQL statement that you want to execute against a database.
   - There are two main types of statements: Statement and PreparedStatement.
   - Statement: It is used to execute static SQL statements that do not contain parameters.
   - Statements are executed using methods like executeQuery() for SELECT statements that return a ResultSet, executeUpdate() for SQL statements that modify data, and execute() for general-purpose execution.
   - Example of creating and executing a Statement:
     ```java
     Statement statement = connection.createStatement();
     ResultSet resultSet = statement.executeQuery("SELECT * FROM my_table");
     while (resultSet.next()) {
         // Process the retrieved data
     }
     ```

3. **EntityManagerFactory**:
   - EntityManagerFactory is an interface that represents a factory for EntityManager instances.
   - Example of creating an EntityManagerFactory:
     ```java
     EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
     ```

4. **EntityManager**:
   - EntityManager is an interface that represents a JPA runtime interface for interacting with the persistence context, managing entity instances, and performing database

5. @Embedded, @Embeddable
   - Makes class automatically convent to the part of table while executing
   - Example
      ```java
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
6. @OneToOne(mappedBy="NameOfMappingTable", cascade = CascadeType.ALL)
   - Is used to define a one-to-one relationship between two entities. The mappedBy attribute indicates the field name in the owning entity that owns the relationship. Cascading refers to the propagation of certain operations from a parent entity to its associated child entities. 
   - ```java
     @Entity
      public class Customer {
          @Id
          @GeneratedValue
          private Long id;
      
          @OneToOne(mappedBy = "customer")
          private CustomerRecord customerRecord;
      
          // getters and setters
      }
      
      @Entity
      public class CustomerRecord {
          @Id
          @GeneratedValue
          private Long id;
      
          @OneToOne
          @JoinColumn(name = "customer_id")
          private Customer customer;
      
          // getters and setters
      }
     ```
7. @GeneratedValue(strategy = GenerationType.AUTO)
   - Makes indexes generate automatically, which eliminates duty to write id for example
   - Example:
     ```java
       @Id
       @GeneratedValue(strategy = GenerationType.AUTO)
       private int id;
     ```

8. @OneToMany
   - Is used to define a one-to-many relationship between two entities.

9. @ManyToMany
   - Is an annotation used to define a many-to-many relationship between two entities.

10. EntityManager.createQuery("SELECT t.Entity FROM table t", Entity.class).getResultList()
   - Sends JPQL query to the database, returns array with found elements

11. @NamedQuery, @NamedQueries
   - Provide a way to define and manage database queries in a centralized and reusable manner.
   - Example:
     ```java
     @Entity
      @NamedQuery(
          name = "findEmployeeByName",
          query = "SELECT e FROM Employee e WHERE e.name = :name"
      )
      public class Employee {
          // Entity attributes and methods
      }
     ```

12. @Relation(fetch = FetchType.LAZY)
   - Defines if JPQL table should be fetched if it's not significant for needed operation
   - Example:
     ```java
       @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
       private Indeks indeks;
     ```
### 3. Functional Programming
1. lambda expression
   - is a concise way to represent an anonymous function
   - Example:
     ```java
        MyFunctionalInterface myLambda = (int x, int y) -> System.out.println("Sum: " + (x + y));
     ```

2. Predicate
   - represents a boolean-valued function of one argument. This interface is widely used in functional-style programming
   - Example:
     ```java
     public class Main {
          public static void main(String[] args) {
              List<String> words = Arrays.asList("apple", "banana", "pear", "orange", "grape");
      
              Predicate<String> lengthGreaterThanFive = s -> s.length() > 5;
      
              System.out.println("Words with length greater than 5:");
              filterAndPrint(words, lengthGreaterThanFive);
          }
      
          public static void filterAndPrint(List<String> words, Predicate<String> predicate) {
              for (String word : words) {
                  if (predicate.test(word)) {
                      System.out.println(word);
                  }
              }
          }
      }
     ```

3. Consumer
-  represents an operation that accepts a single input argument and returns no result

4. Supplier
-  represents an operation that accepts a single input argument and returns no result

5. Supplier
- provides a single abstract method called apply(), which accepts an argument of type T and returns a result of type R. It represents a function that accepts an argument of type T and returns a result of type R

6. Optional
- represents an object that may or may not contain a non-null value

7. Stream.filter()
- is used to filter elements of a stream based on a specified predicate

8. Stream.map()
- transform each element of a stream according to a given function

9. Stream.ForEach
- used to iterate over each element of a stream and perform an action for each element

10. Stream.findFirst()
- returns an Optional containing the first element of the stream, or an empty Optional if the stream is empty

11. Stream.anyMatch()
- returns true if any element of the stream matches the given predicate, otherwise returns false

12. Stream.allMatch()
- returns true if all elements of the stream match the given predicate, otherwise returns false
     
## Definitions

### 1.Threads

1. Race Condition - A race condition in occurs when multiple threads access shared resources or data concurrently, leading to unpredictable outcomes due to the timing of their execution. To avoid make problematic method synchronized or wrap the problematic code block/variable in synchronized(this) { } or Atimic type. Atomic types are more efficient but compel to use of object which makes code take use more memory.

### 2. Data persistence

1. Maven - is a build automation tool used primarily for Java projects. It helps manage the project's build process, dependencies, and documentation in a consistent and efficient manner. Maven uses a project object model (POM) file to describe the project's structure, dependencies, and build process configuration.
2. Java Database Connectivity (JDBC) is an API (Application Programming Interface) provided by Java that allows Java applications to interact with relational databases. It provides a standard way for Java applications to perform database operations such as querying data, updating records, and executing stored procedures. Old and rarely used, but may be useful for migration into more efficient tech
3. JPA stands for Java Persistence API. It is a standard specification for ORM (Object-Relational Mapping) frameworks in Java, which allows developers to map Java objects to relational database tables and vice versa. JPA provides a high-level abstraction over the underlying database interactions, making it easier for developers to work with databases in Java applications.
4. Hibernate is a popular open-source ORM (Object-Relational Mapping) framework for Java applications. It provides a powerful and flexible way to map Java objects to relational database tables and vice versa, simplifying database interactions and reducing the amount of boilerplate code required for database access.
5. Entity - is a lightweight, persistent domain object that represents a data entity stored in a relational database
6. CRUD - stands for Create, Read, Update, and Delete. It is an acronym commonly used in the context of database management and programming to describe the four basic functions that are often implemented in database applications
7. JPQL - query language used in Java Persistence API (JPA) to perform database operations on entities. It is similar to SQL
8. PostageSQL - open-source relational database management system (RDBMS) that is widely used in Java programming and other application development environments

### 3. Functional Programming

1. Functional programming - paradigm that treats computation as the evaluation of mathematical functions and avoids changing-state and mutable data
2. Functional intrface - a functional interface is an interface that contains exactly one abstract method
3. Lambda expressions - is a concise way to represent an anonymous function—a method without a name that can be passed around as an argument to other methods or stored in variables. They make code more readable and expressive, especially when working with interfaces that contain only one abstract method
4. primitive functional interface - functional interface that is specialized to work with primitive data types (e.g., int, double, long, etc.) rather than reference types
5. method reference - shorthand syntax for referring to methods or constructors of classes or objects (System.out::println)
6. Steam API - functional-style operations on sequences of elements, facilitating concise and readable code for filtering, mapping, aggregating, and transforming data
