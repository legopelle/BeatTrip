package com.beardedwhale.library;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Assets {

    public static Sprite blackSprite;
    public static BitmapFont toastFont;
    public static NinePatch toastBackground;
    public static NinePatch dialogBackground;
    public static BitmapFont dialogFontLarge;
    public static BitmapFont dialogFontSmall;
    
    public static void initiate() {
	setupTextures();
	setupFonts();
	setupSounds();
    }

    public static void setupSounds() {	
    }

    public static void setupFonts() {
    }

    public static void setupTextures() {
    }

}
