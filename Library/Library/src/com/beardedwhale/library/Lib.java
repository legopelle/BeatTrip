package com.beardedwhale.library;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Lib {

	public static int randInt(int max) {
		return (int) Math.round(Math.random() * max);
	}

	public static Sprite makeSprite(TextureAtlas map, String name, float width, Vector2 pos, float angle) {
		Sprite sprite = new Sprite(map.findRegion(name));
		float height = width * sprite.getHeight()/sprite.getWidth();
		sprite.setSize(width, height);
		sprite.setOrigin(width/2, height/2);
		sprite.setPosition(pos.x - sprite.getOriginX(), pos.y - sprite.getOriginY());
		sprite.setRotation(angle);
		return sprite;
	}

	/**
	 * Makes a texture with Linear filters. Don't forget to dispose it! 
	 * @param name The location of the file, e.g. "data/image.png".
	 * @return
	 */
	public static Texture makeTexture(String name) {
		Texture texture = new Texture(Gdx.files.internal(name));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return texture;
	}


	public static void log(int i) {
		Gdx.app.log("Logint", Integer.toString(i));
	}
	public static void log(Boolean i) {
		Gdx.app.log("Logbool", Boolean.toString(i));
	}
	public static void log(float i) {
		Gdx.app.log("Logfloat", Float.toString(i));
	}
	public static void log(String i) {
		Gdx.app.log("Logstring", i);
	}

	/**
	 * @param Min The minimum return value
	 * @param Max The maximum return value
	 * @return A random float between Min and Max
	 */
	public static float randF(float Min, float Max) {
		return (float) Math.random()*(Max-Min)+Min;
	}

	/**
	 * @param Min The minimum return value
	 * @param Max The maximum return value
	 * @return A random integer between Min and Max
	 */
	public static int randInt(int Min, int Max) {
		return (int) Math.round(Math.random()*(Max-Min)+Min);
	}

	public static Vector2 getClick(OrthographicCamera cam) {
		Vector3 touchPoint = new Vector3();
		cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
		Vector2 click = new Vector2(touchPoint.x, touchPoint.y);
		return click;
	}

	/**Draw a string (non-wrapped), centered at the given position.
	 * @param font The font.
	 * @param text The string to draw.
	 * @param pos The center of the text.
	 */
	public static void drawText(BitmapFont font, float scale, String text, Vector2 pos) { 
		font.setScale(scale);
		TextBounds TB = font.getBounds(text);
		font.draw(Global.batch, text, pos.x - TB.width/2, pos.y + TB.height/2);
		font.setScale(1);
	}

	/**Draw a string (wrapped), centered at the given position.
	 * @param font The font.
	 * @param text The string to draw.
	 * @param pos The center of the text.
	 */
	public static void drawWrapText(BitmapFont font, float scale, String text, Vector2 pos, float wrapWidth) { 
		font.setScale(scale);
		TextBounds TB = font.getWrappedBounds(text, wrapWidth);
		font.drawWrapped(Global.batch, text, pos.x - TB.width/2, pos.y + TB.height/2, wrapWidth);
		font.setScale(1);
	}

	
	/**Returns the greatest common divisor of two integers.
	 * @param a 
	 * @param b 
	 * @return The greatest common divisor of a and b.
	 */
	public static int gcd(int a, int b) {
		if (b==0) {
			return a;
		}
		else {
			return gcd(b,a%b);
		}
	}
}
