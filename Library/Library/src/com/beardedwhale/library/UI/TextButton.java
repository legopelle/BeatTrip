package com.beardedwhale.library.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.beardedwhale.library.Global;

public class TextButton extends Button{

	public BitmapFont font;
	public String text;
	
	public TextButton(Vector2 pos, float width, TextureAtlas Atlas, String Pic, BitmapFont fnt, String txt) {
		this("", pos, width, Atlas, Pic, fnt, txt);
	}
	
	public TextButton(String ID, Vector2 pos, float width, TextureAtlas Atlas, String Pic, BitmapFont fnt, String txt) {
		super(ID, pos, width, Atlas, Pic, "rect");
		font = fnt;
		text = txt;
	}
	
	@Override
	public void draw(SpriteBatch batch, float alpha) {
		sprite.draw(batch, alpha);
		TextBounds TB = font.getBounds(text);
		float PressOffset = Global.uiWidth/300;
		
		Color fontColor = font.getColor();
		font.setColor(fontColor.r, fontColor.g, fontColor.b, alpha);
		
		if (!isPressed) {
			font.draw(batch, text, pos.x - TB.width/2, pos.y + TB.height/2);
		}
		else {
			font.draw(batch, text, pos.x - TB.width/2 + PressOffset, pos.y + TB.height/2 - PressOffset);
		}
		
		font.setColor(fontColor);
	}


}
