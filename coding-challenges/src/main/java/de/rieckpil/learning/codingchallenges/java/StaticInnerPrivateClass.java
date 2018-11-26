package de.rieckpil.learning.codingchallenges.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StaticInnerPrivateClass {

	public static void main(String[] args) throws Exception {

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int num = Integer.parseInt(br.readLine().trim());
			Object o;
			o = new Inner().new Private();
			System.out.println((num + " is " + ((StaticInnerPrivateClass.Inner.Private) o).powerof2(num)));
			System.out.println("An instance of class: " + o.getClass().getCanonicalName() + " has been created");
		} catch (Exception e) {

		}
	}

	static class Inner {
		private class Private {
			private String powerof2(int num) {
				return ((num & num - 1) == 0) ? "power of 2" : "not a power of 2";
			}
		}
	}

}
