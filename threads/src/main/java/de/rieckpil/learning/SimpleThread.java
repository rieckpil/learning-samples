package de.rieckpil.learning;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SimpleThread {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		Runnable r = () -> {
			System.out.println(Thread.currentThread().getName() + ": Hello World!");
		};

		Thread t = new Thread(r);
		t.start();

		System.out.println(Thread.currentThread().getName());

		HelloThread t1 = new HelloThread();

		HelloThread t2 = new HelloThread();

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		ExecutorService executorServicePool = Executors.newFixedThreadPool(5);

		executorService.execute(t1);
		executorService.execute(t2);

		executorServicePool.execute(t1);
		executorServicePool.execute(t2);

		Future<String> result = executorServicePool.submit(new HelloCallable());

		System.out.println(result.isDone());
		System.out.println(result.isCancelled());
		System.out.println(result.get());

	}
}
