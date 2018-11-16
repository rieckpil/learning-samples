package de.rieckpil.learning.codingchallenges.interviews;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;

public class SelectableConcurrentHashMapTest {

	private static final int MAP_SIZE = 5_000;
	private static final int SELECT_SIZE = 15;
	private static SelectableConcurrentHashMap<Integer, String> dataMap = new SelectableConcurrentHashMap<>(MAP_SIZE);

	@BeforeClass
	public static void populateDataMap() {
		for (int i = 0; i < MAP_SIZE; i++) {
			dataMap.put(i, "value" + i);
		}
	}

	@Test
	public void testCorrectness() {
		final Collection<Map.Entry<Integer, String>> selection = dataMap.selectRandomEntries(SELECT_SIZE);
		assertEquals(SELECT_SIZE, selection.size());
		for (Map.Entry<Integer, String> e : selection)
			assertTrue(dataMap.containsKey(e.getKey()));
	}

	@Test
	public void testPerformance() {
		operationsPerSecond(); // warmup
		assertTrue(
				TimeUnit.SECONDS.toNanos(1) / operationsPerSecond() <= TimeUnit.MICROSECONDS.toNanos(2 * SELECT_SIZE));
	}

	private long operationsPerSecond() {
		return 10000l;
	}

}
