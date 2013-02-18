package com.beardedwhale.library.Dialog;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.beardedwhale.library.Assets;
import com.beardedwhale.library.Cons;
import com.beardedwhale.library.Global;
import com.beardedwhale.library.Lib;
import com.beardedwhale.library.Actor.Actor;
import com.beardedwhale.library.UI.Button;

public abstract class Dialog extends Actor{

    public DialogListener dialogParent;

    public float clock = 0;
    public float alpha;

    public String id;

    public float height;
    public float width;
    public float left;
    public float right;
    public float top;
    public float bottom;

    public Vector2 pos = Cons.uiCenter;
    public float padding = Math.max(Global.uiWidth/20, Global.uiHeight/20);
    public NinePatch background;
    public Sprite blackBG = Assets.blackSprite;

    public InputProcessor input;
    
    public float[] hPos;
    public float[] vPos;
    
    List<Button> buttons = new ArrayList<Button>();

    public Dialog(DialogListener Parent, String ID) {
	dialogParent = Parent;
	id = ID;	
	setInput();
    }

    public void setPos(Vector2 position) {
	pos = position;

	top = pos.y + height/2;
	top = Math.min(top, Global.uiHeight/2);
	left = Math.max(top, -Global.uiWidth/2 + height);
	pos.set(pos.x, top - height/2);

	left = pos.x - width/2;
	left = Math.min(left, Global.uiWidth/2 - width);
	left = Math.max(left, -Global.uiWidth/2);
	pos.set(left + width/2, pos.y);

	bottom = top - height;
	right = left + width;
    }

    public void setVerticalCenter(float vertC) {
	setPos(new Vector2(pos.x, vertC));
    }

    public void setHorizontalCenter(float horC) {
	setPos(new Vector2(horC, pos.y));
    }

    public abstract void setupAction();

    public void render(SpriteBatch batch, float dt) {
	act(dt);		
	clock += dt;
    }

    public void close() {
	dialogParent.onClose(this);
    }

    public void setInput() {
	input = new DialogInput();
	Global.input.addProcessor(0, input);
    }
    
    public void removeInput() {
    	Global.input.removeProcessor(input);
    }
    
    public abstract void dialogBack();

    public abstract void buttonClicked(String id);

    private class DialogInput implements InputProcessor{

	public DialogInput() {
	}

	@Override
	public boolean keyDown(int keycode) {
	    
	    onKeyDown(keycode);
	    
	    if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
		dialogBack();
	    }
	    return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	    Vector2 click = Lib.getClick(Global.uiCamera);
	    onClick(click, pointer, button);
	    
	    for (Button B : buttons) {
		if (B.contains(click)) {
		    buttonClicked(B.id);
		}
	    }
	   
	    return true;
	}

	@Override
	public boolean keyUp(int keycode) {
	    return false;
	}

	@Override
	public boolean keyTyped(char character) {
	    return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
	    return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
	    return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
	    return false;
	}

	@Override
	public boolean scrolled(int amount) {
	    return false;
	}
    }

    public void onClick(Vector2 click, int pointer, int button){
    }

    public void onKeyDown(int keycode) {	
    }
}
