package com.beardedwhale.beattrip;

import com.badlogic.gdx.math.Vector2;
import com.beardedwhale.library.Global;

public class GameGlobal extends Global {

	public static boolean animate;

	//The size determines what resolution of images to use.
	public static float bAreaH;
	public static float bAreaC;
	public static float bAreaB;
	public static float sourceH;
	public static float sourceC;
	public static Vector2 sourcePos;
	public static float sourceAspectRatio = 1/3f; //Height to width.

	public static void initiate() {
		Global.initiate();
	    
		bAreaH = GameGlobal.uiHeight * 0.6f; //Height of the bucket area.
		bAreaC = GameGlobal.uiHeight/2 - bAreaH/2; //Vertical center of the bucket area.
		bAreaB = bAreaC - bAreaH/2; //Vertical bottom of bucket area.
		
		sourceH = GameGlobal.uiHeight * 0.2f; //Source height.
		sourceC = bAreaB - sourceH/2; //Source center.
		sourcePos = new Vector2(0,sourceC);
	}

}
