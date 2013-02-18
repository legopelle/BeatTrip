package com.beardedwhale.library.Dialog;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.beardedwhale.library.Global;
import com.beardedwhale.library.Dialog.DialogListener;

public class ArrowToast extends ClickToast {

	public Sprite arrow;
	private float arrowwidth = Global.uiWidth/8;

	public ArrowToast(DialogListener Parent, String ID, String text, Vector2 point) {
		super(Parent, ID, text);

		//TODO make arrow sprite
		
		if (point.y >= 0) {
			setVerticalCenter(point.y - arrowwidth - height/2);
			arrow.setPosition(point.x, top);
		}
		else {
			top = point.y + height + Global.uiWidth/8;
			setVerticalCenter(point.y + arrowwidth + height/2);
			arrow.setPosition(point.x, bottom);
			arrow.setRotation(180);
		}
	}

	public void render(SpriteBatch batch, float dt) {
		super.render(batch, dt);
		arrow.draw(batch, alpha);
	}

	public void setPos(Vector2 point) {
	    	//TODO Take consideration to vertical limits too
	    
		if (arrow != null) {
			if (point.y >= 0) {
				top = point.y - arrowwidth;
				bottom = top - height;
				setVerticalCenter(top - height/2);
				arrow.setPosition(point.x - arrow.getOriginX(), top - arrow.getOriginY());
				arrow.setRotation(0);
			}
			else {
				bottom = point.y + arrowwidth;
				top = point.y + arrowwidth + height;
				setVerticalCenter(bottom + height/2);
				arrow.setPosition(point.x - arrow.getOriginX(), bottom - arrow.getOriginY());
				arrow.setRotation(180);
			}
		}
		pos.set(point.x, top - height/2);
		super.setPos(pos);
	}
}
