package com.beardedwhale.library.Dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.beardedwhale.library.Dialog.DialogListener;
import com.beardedwhale.library.Actor.continousAction;

public class ClickToast extends Toast {

    public ClickToast(DialogListener Parent, String ID, String text, Vector2 center, float maxWidth) {
	super(Parent, ID, text, center, maxWidth);
    }

    public ClickToast(DialogListener Parent, String ID, String text, float maxWidth) {
	super(Parent, ID, text, maxWidth);
    }

    public ClickToast(DialogListener Parent, String ID, String text) {
	super(Parent, ID, text);
    }

    @Override
    public void setupAction() {
	continousAction show = new continousAction() {

	    @Override
	    public void doMainAction(float dt) {
		alpha += 4*dt;
		alpha = Math.min(1,alpha);
	    }

	    @Override
	    public boolean endCondition() {
		return (alpha == 1);
	    }

	    @Override
	    public void cleanUp() {
		instantActions.add(waitForClick());
	    }

	};

	instantActions.add(show);
    }

    public continousAction waitForClick() {
	return new continousAction() {

	    @Override
	    public void cleanUp() {

		continousAction fadeOut = new continousAction() {
		    @Override
		    public void doMainAction(float dt) {
			alpha -= 4*dt;
			alpha = Math.max(0,alpha);
		    }
		    @Override
		    public boolean endCondition() {
			return (alpha == 0);
		    }
		    @Override
		    public void cleanUp() {
			dialogParent.onClose(THIS);
		    }

		};

		instantActions.add(fadeOut);

	    }

	    @Override
	    public boolean endCondition() {
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
		    return true;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
		    return true;
		}
		return (clock > 1 && Gdx.input.isTouched());
	    }
	};
    }


    @Override
    public void setInput() {
	//TODO fix this.
    }

}
