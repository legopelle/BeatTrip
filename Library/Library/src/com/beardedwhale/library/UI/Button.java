package com.beardedwhale.library.UI;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.beardedwhale.library.Lib;

public class Button {

	public Object shape;

	public Sprite sprite;

	public Vector2 pos;
	public float width;
	public float height;

	public TextureRegion img;
	public TextureRegion img_pressed;

	public Boolean isPressed = false;
	public Boolean clickable = true;
	
	public String id;

	public Button(Vector2 position, float width, TextureAtlas asset, String pic, String type) {
		this("", position, width, asset, pic, type);
	}

	public Button(String ID, Vector2 position, float width, TextureAtlas asset, String pic, String type) {
		
		id = ID;
		
		sprite = Lib.makeSprite(asset, pic, width, position, 0);

		img = asset.findRegion(pic);
		img_pressed = asset.findRegion(pic+"_pressed");

		pos = position;
		height = sprite.getHeight();
		width = sprite.getWidth();

		if (type.equals("circle")) {
			shape = new Circle(pos.x - width/2, pos.y - height/2, width);
		}
		else if (type.equals("rect")) {
			shape = new Rectangle(pos.x - width/2, pos.y - height/2, width, height);
		}
	}

	public void draw(SpriteBatch batch, float alpha) {
		sprite.draw(batch, alpha);
	}

	public void onClick() {
		isPressed = true;
		sprite.setRegion(img_pressed);
	}

	public void onRelease() {
		isPressed = false;
		sprite.setRegion(img);
	}

	public Boolean contains(Vector2 point) {
		if (shape.getClass() == Circle.class) {
			return ((Circle) shape).contains(point.x, point.y);
		}
		else if (shape.getClass() == Rectangle.class) {
			return ((Rectangle) shape).contains(point.x, point.y);
		}
		else return false;
	}
}


