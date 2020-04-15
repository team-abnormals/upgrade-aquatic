package com.teamabnormals.upgrade_aquatic.api.util;

import java.util.Random;

/**
 * @author - SmellyModder(Luke Tonon)
 */
public class MathUtil {

	public interface Equation {
		public double compute(double theta);
	}
	
	public static double makeNegativeRandomly(double value, Random rand) {
		return rand.nextBoolean() ? -value : value;
	}
	
	public static double makeNegativeRandomlyWithFavoritism(double value, Random rand, float negativeChance) {
		return rand.nextFloat() < negativeChance ? -value : value;
	}
	
}
