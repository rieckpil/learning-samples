package de.rieckpil.learning.enums;

public enum Planet {
	MERCURY(3.302e+23, 2.439e6), VENUS(4.869e+24, 6.052e6), EARTH(5.975e+24, 6.378e6), MARS(6.419e+23,
			3.393e6), SATURN(5.685e26, 6.027e7);

	private final double mass; // in kilograms
	private final double radius; // in meters
	private final double surfaceGravity; // in m / s^2

	private static final double G = 6.67300E-11;

	Planet(double mass, double radius) {
		this.mass = mass;
		this.radius = radius;
		this.surfaceGravity = G * mass / (radius * radius);
	}

	public double mass() {
		return mass;
	}

	public double radius() {
		return radius;
	}

	public double surfaceGravity() {
		return surfaceGravity;
	}

	public double surfaceWeight(double mass) {
		return mass * surfaceGravity;
	}

}
