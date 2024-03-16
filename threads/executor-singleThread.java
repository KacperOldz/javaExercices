package testProject;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Uruchomienie!");
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		Runnable countdown = () -> {
			System.out.println("Wątek: " + Thread.currentThread().getName());
			try {
				for(int i = 1; i <= 10; i++) {
					System.out.println(i);
					TimeUnit.MILLISECONDS.sleep(100);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Runnable blastOff = () -> {
			System.out.println("Wątek: " + Thread.currentThread().getName());
			System.out.println("Blast off!");
		};
		
		executor.submit(countdown);
		executor.submit(blastOff);
		
		// executor.shutdownNow()	Zamyka nie czekając aż przekazane runnable sie wykonają
		executor.shutdown();
	}

}
