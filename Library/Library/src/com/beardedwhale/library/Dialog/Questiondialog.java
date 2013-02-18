package com.beardedwhale.library.Dialog;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.beardedwhale.library.Assets;
import com.beardedwhale.library.Global;
import com.beardedwhale.library.Lib;
import com.beardedwhale.library.Dialog.Dialog;
import com.beardedwhale.library.Dialog.DialogListener;
import com.beardedwhale.library.Actor.timerAction;

public class Questiondialog extends Dialog {

    public Dialog THIS = this;

    //private Button yesBtn;
    //private Button noBtn;

    private String questionText;

    public Questiondialog(DialogListener D, String id, String Question) {

	super(D, id);

	background = Assets.dialogBackground;

	//TODO fix height depening on Question textbounds

	questionText = Question;

	//TODO make buttons		
    }

    public void render(SpriteBatch batch, float dt) {
	super.render(batch, dt);

	blackBG.draw(batch, 0.5f*alpha);

	background.setColor(new Color(1,1,1,alpha));
	background.draw(batch, -width/2, -height/2, width, height);

	BitmapFont fontL = Assets.dialogFontLarge;
	Color fontLColor = fontL.getColor();
	fontL.setColor(fontLColor.r, fontLColor.g, fontLColor.b, alpha);

	BitmapFont fontS = Assets.dialogFontSmall;
	Color fontSColor = fontS.getColor();
	fontS.setColor(fontSColor.r, fontSColor.g, fontSColor.b, alpha);

	//Draw title
	//TODO fix vertical position of text and flush left.
	Lib.drawWrapText(fontS, 1f, questionText, new Vector2(0, top/2), Global.uiWidth * 0.80f);

	//Draw button(s)
	//TODO this
	//noBtn.draw(batch, alpha);
	//yesBtn.draw(batch, alpha);

	fontL.setColor(fontLColor);
	fontS.setColor(fontSColor);
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
	    alpha = 1;
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
	    alpha = 0;


	    dialogParent.onClose(THIS);
	    dialogParent.onEvent(THIS,"exit");
	    alpha = 0;
	    Global.input.removeProcessor(input);
	}

	@Override
	public void prepare() {
	}
    };
    
    @Override
    public void setupAction() {
	actionQueue.add(fadeIn);
    }

    @Override
    public void dialogBack() {
	actionQueue.add(fadeOut);
	
    }

    @Override
    public void buttonClicked(String id) {
	//TODO yes/no-button clicked
    }

}
