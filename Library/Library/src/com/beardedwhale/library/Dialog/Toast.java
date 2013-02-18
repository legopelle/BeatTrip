package com.beardedwhale.library.Dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.beardedwhale.library.Assets;
import com.beardedwhale.library.Cons;
import com.beardedwhale.library.Global;
import com.beardedwhale.library.Lib;
import com.beardedwhale.library.Actor.timerAction;
import com.beardedwhale.library.Dialog.Dialog;
import com.beardedwhale.library.Dialog.DialogListener;

public class Toast extends Dialog {

	public Toast THIS = this;

	public String msg;
	private float textwidth;
	private float textheight;

	private BitmapFont font;

	static float defaultMaxWidth = Global.uiWidth * 0.95f;
	static float defaultTime = 1.5f;

	private float showTime;

	public Toast(DialogListener Parent, String ID, String text) {
		this(Parent, ID, text, Cons.uiCenter, defaultMaxWidth, defaultTime);
	}

	public Toast(DialogListener Parent, String ID, String text, float maxWidth) {
		this(Parent, ID, text, Cons.uiCenter, maxWidth, defaultTime);
	}

	public Toast(DialogListener Parent, String ID, String text, Vector2 center, float maxWidth) {
		this(Parent, ID, text, center, maxWidth, defaultTime);
	}

	public Toast(DialogListener Parent, String ID, String text, Vector2 center, float maxWidth, float Time) {
		super(Parent, ID);
		msg = text;
		setupLayout(maxWidth);
		setPos(center);

		showTime = Time;
		setupAction();
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

	@Override
	public void setupAction() {
		actionQueue.add(showForTime());
	}

	@Override
	public void render(SpriteBatch batch, float dt) {
		super.render(batch, dt);

		//blackBG.draw(batch, 0.5f*alpha);

		background.setColor(new Color(1,1,1,alpha));
		background.draw(batch, left, bottom, width, height);

		Color fontColor = font.getColor();
		font.setColor(fontColor.r, fontColor.g, fontColor.b, alpha);

		//Draw title
		//TODO make this left flushed.
		Lib.drawWrapText(font, 1f, msg, new Vector2(pos.x, top - height/2), textwidth);
		font.setColor(fontColor);
	}

	public timerAction showForTime() {
		return new timerAction(0.25f, showTime, 0.25f) {

			@Override
			public void doPreAction(float dt) {
				alpha += 1/preTime * dt;
				alpha = Math.min(1,alpha);
			}

			@Override
			public void doMainAction(float dt) {
				alpha = 1;
				if (Gdx.input.justTouched() && clock > 1) {
					clock = runTime;
				}
			}

			@Override
			public void doPostAction(float dt) {
				alpha -= 1/postTime * dt;
				alpha = Math.max(0,alpha);
			}

			@Override
			public void cleanUp() {
				alpha = 0;
				dialogParent.onClose(THIS);
			}
		};
	}

	@Override
	public void setInput() {
		//TODO have an inputprocessor rather than the Gdx.input-solution above, which doesn't multiplex.
	}

	
	@Override
	public void dialogBack() {
	}

	
	@Override
	public void buttonClicked(String id) {
	}
}
