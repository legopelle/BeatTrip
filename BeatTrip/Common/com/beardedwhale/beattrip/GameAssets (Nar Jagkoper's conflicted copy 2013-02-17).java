package com.beardedwhale.beattrip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.beardedwhale.library.Assets;

public class GameAssets extends Assets{
	
	public static BitmapFont cambriaWhiteLarge;
	public static BitmapFont cambriaWhiteSmall;
	public static String[] lvlNames;
	
	public static void initiate() {
	    setupTextures();
	    setupFonts();
	    setupSounds();
	    setupLevelData();
	}
	
	public static void setupTextures() {
	}

	public static void dispose() {
	}

	public static void setupFonts() {
		cambriaWhiteLarge = new BitmapFont(Gdx.files.internal("fonts/cambria90white"+GameGlobal.size+".fnt"), Gdx.files.internal("fonts/cambria90white_0"+GameGlobal.size+".png"), false);
		cambriaWhiteLarge.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		cambriaWhiteSmall = new BitmapFont(Gdx.files.internal("fonts/cambria40white"+GameGlobal.size+".fnt"), Gdx.files.internal("fonts/cambria40white_0"+GameGlobal.size+".png"), false);
		cambriaWhiteSmall.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	public static void setupLevelData() {
	}
}
