package com.beardedwhale.beattrip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.beardedwhale.library.Assets;

public class GameAssets extends Assets{

    public static BitmapFont cambriaWhiteLarge;
    public static BitmapFont cambriaWhiteSmall;
    public static String[] lvlNames;
    public static TextureAtlas tiles;

    public static void initiate() {
	setupTextures();
	setupFonts();
	setupSounds();
	setupLevelData();
    }

    public static void setupTextures() {

	String outputDir = "../BeatTrip-android/assets/spritemaps/";
	String assets = "../Raw assets/Images/";
	String inputDir = assets+"/Tiles/H/";
	//TexturePacker2.process(inputDir, outputDir, "tilesH.map");

	tiles = new TextureAtlas(Gdx.files.internal("spritemaps/tiles"+GameGlobal.size+".map"));
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
