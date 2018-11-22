package de.rieckpil.learning.codingchallenges.java;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

public class BitSetIntro {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		BitSet b1 = new BitSet(n);
		BitSet b2 = new BitSet(n);

		List<BitSet> list = new ArrayList<>();
		list.add(b1);
		list.add(b2);

		while (m > 0) {

			String operand = scan.next();
			int arg1 = scan.nextInt();
			int arg2 = scan.nextInt();

			if (operand.equals("AND")) {
				list.get(arg1 - 1).and(list.get(arg2 - 1));
			} else if (operand.equals("OR")) {
				list.get(arg1 - 1).or(list.get(arg2 - 1));
			} else if (operand.equals("XOR")) {
				list.get(arg1 - 1).xor(list.get(arg2 - 1));
			} else if (operand.equals("FLIP")) {
				list.get(arg1 - 1).flip(arg2);
			} else if (operand.equals("SET")) {
				list.get(arg1 - 1).set(arg2);
			}

			System.out.println(b1.cardinality() + " " + b2.cardinality());
			m--;
		}
		scan.close();
	}
}
