package be.christopher.utils;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

	public static Color randomColor() {
		Random random = new Random();
		float hue = random.nextFloat();
		float saturation = 0.9f; //1.0 for brilliant, 0.0 for dull
		float luminance = 1.0f; //1.0 for brighter, 0.0 for black
		return Color.getHSBColor(hue, saturation, luminance);
	}
	
	public static int randomInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	public static double randomDouble(double min, double max) {
		return ThreadLocalRandom.current().nextDouble(min, max + 1);
	}
	
	public static double randomVelocity(double min, double max) {
		double velocity = 0;
		
		do {
			velocity = randomDouble(min, max);
		} while(velocity > -0.2 && velocity < 0.2);
		
		return velocity;
	}
	
}
