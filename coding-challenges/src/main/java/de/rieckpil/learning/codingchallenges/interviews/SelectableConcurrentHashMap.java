package de.rieckpil.learning.codingchallenges.interviews;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;

//You are free to choose one of the following implementation approaches:
//1. extend ConcurrentHashMap;
//2. create a class which wraps ConcurrentHashMap;
//3. copy ConcurrentHashMap code into your class and modify it.
public class SelectableConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> {

	private static final long serialVersionUID = 902479025702562000L;

	public SelectableConcurrentHashMap(int initialCapacity) {
		super(initialCapacity);
	}

	public SelectableConcurrentHashMap(int initialCapacity, float loadFactor, int concurrencyLevel) {
		super(initialCapacity, loadFactor, concurrencyLevel);
	}

	@Override
	public V get(Object key) {
		System.out.println("Halloo");
		return super.get(key);
	}

	/**
	 * Selects, at random, the requested number of entries from this map. Latency is
	 * less than 2 µs per requested item, regardless of map size. Assumes that the
	 * map is much larger than the number of requested items. Supports arbitrarily
	 * large maps.
	 * 
	 * @param count the number of entries to select, a positive integer less than
	 *              100
	 */
	public List<Map.Entry<K, V>> selectRandomEntries(int count) {
		return null;
	}

	public static void main(String[] args) {

		SelectableConcurrentHashMap<Long, String> x = new SelectableConcurrentHashMap<>(10, 0.75f, 10);

		LongStream.range(0, 10).forEach(l -> x.put(l, "Hello"));
		
		long start = System.nanoTime();
		x.get(ThreadLocalRandom.current().nextLong(11));
		long end = System.nanoTime();
		long microseconds = (end - start) / 1000;

		System.out.println("µs seconds for retrieving the value: " + microseconds);

	}

}
