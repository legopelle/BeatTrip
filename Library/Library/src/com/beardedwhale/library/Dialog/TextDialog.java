package com.beardedwhale.library.Dialog;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.beardedwhale.library.Assets;
import com.beardedwhale.library.Global;
import com.beardedwhale.library.Lib;
import com.beardedwhale.library.Dialog.Dialog;
import com.beardedwhale.library.Dialog.DialogListener;
import com.beardedwhale.library.Actor.timerAction;

public class TextDialog extends Dialog {
	
	public TextDialog THIS = this;

	//public Button okBtn;
	
	public String msg;

	private float textwidth;
	private float textheight;

	private BitmapFont font;

	public TextDialog(DialogListener Parent, String ID, String text) {

		super(Parent, ID);
		
		BitmapFont fontS = Assets.dialogFontSmall;
		
		msg = text;
		
		float textwidth = Global.uiWidth * 0.9f;
		float textheight = fontS.getWrappedBounds(msg, textwidth).height;
		
		float buttonspace = Global.uiWidth/8;
		//float buttonwidth = Global.uiWidth/6;
		
		height = textheight + buttonspace + 2*padding;

		background = Assets.dialogBackground;	
		
		//okBtn =  new Button(new Vector2(0, bottom + padding + buttonspace/2), buttonwidth, Assets.ui, "okBtn", "rect");
		
		actionQueue.add(fadeIn);
		}

	public void setupLayout(float maxWidth) {

		font = Assets.toastFont;

		TextBounds TB = font.getWrappedBounds(msg, textwidth);
		textwidth = maxWidth - 2 * padding;
		textwidth = Math.min(textwidth, TB.width);
		TB = font.getWrappedBounds(msg, textwidth);
		textheight = TB.height; 

		height = textheight + 2 * padding;
		width = textwidth + 2 * padding;

		background = Assets.toastBackground;
	    }
	
	public void render(SpriteBatch batch, float dt) {

		act(dt);
		
		clock += dt;

		blackBG.draw(batch, 0.5f*alpha);

		background.setColor(new Color(1,1,1,alpha));
		background.draw(batch, -Global.uiWidth*0.48f, top - height, Global.uiWidth*0.96f, height);

		
		font.setColor(1, 1, 1, alpha);

		//Draw title
		Lib.drawWrapText(font, 1f, msg, new Vector2(0,top - height/2 + Global.uiWidth/8), Global.uiWidth * 0.9f);
		
		//Draw button(s)
		//okBtn.draw(batch, alpha);
	}

	public class DialogInput implements InputProcessor{

		public DialogInput() {
		}

		@Override
		public boolean keyDown(int keycode) {
			if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
				//actionQueue.add(noSelected);
			}
			return true;
		}


		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			//Vector2 click = Lib.getClick(Global.uiCamera);
			/*
			if (okBtn.contains(click)) {
				actionQueue.add(yesSelected);
			}
			*/
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
	
	public timerAction fadeIn = new timerAction(0, 0.5f, 0) {

		@Override
		public void doPreAction(float dt) {	
		}

		@Override
		public void doMainAction(float dt) {
			alpha += 2*dt;
			alpha = Math.min(1,alpha);
		}

		@Override
		public void doPostAction(float dt) {
		}

		@Override
		public void cleanUp() {
		}

		@Override
		public void prepare() {
		}
	};
	
	public timerAction fadeOut = new timerAction(0, 0.5f, 0) {

		@Override
		public void doPreAction(float dt) {	
		}

		@Override
		public void doMainAction(float dt) {
			alpha -= 2*dt;
			alpha = Math.max(0,alpha);
		}

		@Override
		public void doPostAction(float dt) {
		}

		@Override
		public void cleanUp() {
			dialogParent.onClose(THIS);
			Global.input.removeProcessor(input);
		}

		@Override
		public void prepare() {
		}
	};
	

	public timerAction fadeOutForExit = new timerAction(0, 0.5f, 0) {

		@Override
		public void doPreAction(float dt) {	
		}

		@Override
		public void doMainAction(float dt) {
			alpha -= 2*dt;
			alpha = Math.max(0,alpha);
		}

		@Override
		public void doPostAction(float dt) {
		}

		@Override
		public void cleanUp() {
			dialogParent.onClose(THIS);
			dialogParent.onEvent(THIS,"exit");
			Global.input.removeProcessor(input);
		}
		
		@Override
		public void prepare() {
		}
	};

	@Override
	public void setInput() {
		input = new DialogInput();
		Global.input.addProcessor(0, input);
	}

	@Override
	public void setupAction() {
	    // TODO Auto-generated method stub
	    
	}

	
	@Override
	public void dialogBack() {
	}
	

	@Override
	public void buttonClicked(String id) {  
	}

}
