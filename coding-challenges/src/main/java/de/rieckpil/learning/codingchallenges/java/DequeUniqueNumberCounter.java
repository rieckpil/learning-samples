package de.rieckpil.learning.codingchallenges.java;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DequeUniqueNumberCounter {
	
	/**
	 * Sample input: 
	 * 
	 * 6 3
     * 5 3 5 2 3 2
     * 
     * Sample output (max. unique ints in subset of size m):
     * 
     * 3
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Deque<Integer> deque = new ArrayDeque<>();
		Set<Integer> ints = new HashSet<>();

		int n = in.nextInt();
		int m = in.nextInt();
		int maxUnique = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			int num = in.nextInt();
			deque.addLast(Integer.valueOf(num));
			ints.add(Integer.valueOf(num));

			if (deque.size() == m) {
				if (ints.size() > maxUnique)
					maxUnique = ints.size();
				Integer firstInt = deque.removeFirst();
				if (!deque.contains(firstInt))
					ints.remove(firstInt);
			}
		}
		System.out.println(maxUnique);
		in.close();
	}

}
