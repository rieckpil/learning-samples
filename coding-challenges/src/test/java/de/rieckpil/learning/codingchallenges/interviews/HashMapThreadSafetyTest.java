package de.rieckpil.learning.codingchallenges.interviews;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

public class HashMapThreadSafetyTest {

	private static final int UUIDS_PER_THREAD = 5000;
	private static final int AMOUNT_OF_THREADS = 15;

	/**
	 * The tests addresses the data loss of inserting map entries by several threads
	 * in parallel due to the unsynchronized resizing operation during the insertion
	 * of the values (explained in comment for second test)
	 */
	@Test
	public void proveHashMapIsNotThreadSafeFirst() throws InterruptedException {

		Map<String, String> cut = new HashMap<>();

		List<Thread> threads = new ArrayList<>();

		for (int i = 0; i < AMOUNT_OF_THREADS; i++) {
			Runnable runnable = () -> {
				for (int j = 0; j < UUIDS_PER_THREAD; j++) {
					cut.put(UUID.randomUUID().toString(), "John Doe");
				}

			};

			Thread t = new Thread(runnable);
			t.start();
			threads.add(t);
		}

		for (Thread thread : threads) {
			thread.join();
		}

		assertEquals(UUIDS_PER_THREAD * AMOUNT_OF_THREADS, cut.size());

	}

	/**
	 * This test addresses the weakness of the resizing during a .put(K,V) operation
	 * of the HashTable. If the size after the insertion of a new map entry is
	 * greater then the threshold (capacity * load factor), the HashTable will
	 * resize to a doubled size. During this .resize() operation (rehashing and
	 * rebuilding the internal data structure of the HashMap) a concurrent thread
	 * wants to access an already inserted value but will get a null reference in
	 * the worst case.
	 */
	@Test(timeout = 10_000)
	public void proveHashMapIsNotThreadSafeSecond() {

		Map<String, String> cut = new HashMap<>();

		String initialKey = UUID.randomUUID().toString();

		cut.put(initialKey, "John Doe");

		Runnable runnable = () -> {
			for (int i = 0; i < 1_000_000; i++) {
				cut.put(UUID.randomUUID().toString(), "John Doe " + i);
			}
			System.out.println("Finished inserting 1_000_000 UUIDs");
		};

		Thread t = new Thread(runnable);
		t.start();

		while (true) {
			if (cut.get(initialKey) == null) {
				System.out.println("Can't find initial UUID.");
			}
		}
	}

}
