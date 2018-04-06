package de.rieckpil.learning.enums;

public class PlanetWeightCalculator {
	
	public static void main(String[] args) {
		double earthWeight = 79.4;
		double mass = earthWeight / Planet.EARTH.surfaceGravity();
		for(Planet p : Planet.values()) {
			System.out.printf("Weight on %s is %f%n", p, p.surfaceWeight(mass));
		}
	}

}
