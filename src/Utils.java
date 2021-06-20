import java.util.*;

public class Utils {
	public static float getSlope(float x1, float y1, float x2, float y2) {
		float slope = (y2 - y1) / (x2 - x1);
		
		return slope;
	}
	
	public static float getB(float y, float a, float x) {
		float b = y - a * x;
		
		return b;
	}
	
	public static float getY(float a, float x, float b) {
		float y = a * x + b;
		
		return y;
	}
	
	// returns true if num is between num1 and num2
	public static boolean inRange(float num1, float num2, float num) {
		if(num >= num1 && num <= num2) return true;
		else return false;
	}
	
	public static float fuzzify(float range[], float value) {
		for(int i=0; i<range.length-3; i+=2) {
			if(inRange(range[i], range[i+2], value)) {
				float slope = getSlope(range[i], range[i+1], range[i+2], range[i+3]);
				float b = getB(range[i+1], slope, range[i]);
				float y = getY(slope, value, b);
				return y;
			}
		}
		
		return 0;
	}
	
	public static float getCentroid(float range[]) {
		float sum = 0;
		for(int i=0; i<range.length; i+=2) {
			sum += range[i];
		}
		
		return sum / (range.length / 2);
	}
	
	public static float defuzzify(float r1, float r2, float r3, float r4, float R_H[], 
			float R_N[], float R_L[]) {
		float centroidL = getCentroid(R_L);
		float centroidN = getCentroid(R_N);
		float centroidH = getCentroid(R_H);
		
		float upperHand = centroidL * r1 + centroidN * r2 + centroidH * r3 + centroidH * r4;
		float lowerHand = r1 + r2 + r3 + r4;
		
		return upperHand / lowerHand;
	}
}
