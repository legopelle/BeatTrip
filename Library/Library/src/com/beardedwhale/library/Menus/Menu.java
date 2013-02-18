package com.beardedwhale.library.Menus;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.beardedwhale.library.Global;
import com.beardedwhale.library.Lib;
import com.beardedwhale.library.MainGame;
import com.beardedwhale.library.Dialog.Dialog;
import com.beardedwhale.library.Dialog.DialogListener;
import com.beardedwhale.library.UI.Button;

public abstract class Menu implements Screen, DialogListener{

    //all the buttons in the menu
    public List<Button> buttons = new ArrayList<Button>();
    public MainGame game;

    //one dialog at a time can be displayed
    public Dialog currentDialog;

    //listens to signals when menu is displayed
    private MenuInput input;

    public float[] hPos;
    public float[] vPos;
    
    public Menu(MainGame parent) {
	game = parent;
	setupLayout();
    }

    public abstract void setupLayout() ;

    public void render(float dt) {
	render(Global.batch, dt);
    }
    
    public void render(SpriteBatch batch, float dt) {
	menuRender(batch, dt);

	if (currentDialog != null) {
	    currentDialog.render(batch, dt);
	}
    }

    public void menuRender(SpriteBatch batch, float dt) {
	for (Button B : buttons) {
	    B.draw(batch, 1);
	}
	
    }
    
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
	input = new MenuInput();
	Global.input.addProcessor(0, input);
	Lib.log(Global.input.getProcessors().size);
    }

    @Override
    public void hide() {
	removeInput();
    }

    public void removeInput() {
	Global.input.removeProcessor(input);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

    public abstract void menuBack();

    public abstract void buttonClicked(String id);

    private class MenuInput implements InputProcessor{

	public MenuInput() {
	}

	@Override
	public boolean keyDown(int keycode) {
	    if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
		menuBack();
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
	public boolean touchDown(int screenX, int screenY, int pointer, int mouseButton) {
	    Vector2 click = Lib.getClick(Global.uiCamera);
	    Lib.log(11);
	    for (Button B : buttons) {
		if (B.contains(click)) {
		    buttonClicked(B.id);
		}
	    }
	    return true;
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

    @Override
    public void onClose(Dialog D) {	    
    }

    @Override
    public void onEvent(Dialog D, String eventId) {    
    }

    

}


