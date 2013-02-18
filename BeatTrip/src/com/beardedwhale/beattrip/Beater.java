package com.beardedwhale.beattrip;

public class Beater {
    
    public static float beatDiff(float hitTime, float bpm) {
	float beatLength = 60/bpm;
	float posDiff = floatMod(hitTime, beatLength);
	return Math.min(posDiff, Math.abs(beatLength - posDiff));
    }
    
    public static float floatMod(float a, float b) {
	if (a > b) {
	    return floatMod(a-b, b);
	}
	else {
	    return a;
	}
    }
    
}
