package com.beardedwhale.library;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class MainGame extends Game implements ApplicationListener{

	public float clock = 0;

	@Override
	public void create() {
	    	initiate();
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setInputProcessor(Global.input);
	}
	
	public abstract void initiate();

	@Override
	public void render() {
		float dt = Gdx.graphics.getDeltaTime();
	
		clock += dt;

		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glDisable(GL10.GL_BLEND);

		Global.uiCamera.update();
		Global.batch.setProjectionMatrix(Global.uiCamera.combined);

		Global.batch.begin();
		
		gameRender(Global.batch, dt);
		
		Global.batch.end();
	}
	
	public abstract void gameRender(SpriteBatch batch, float dt);
}
