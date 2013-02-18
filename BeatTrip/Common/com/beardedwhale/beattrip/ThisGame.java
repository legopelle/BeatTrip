package com.beardedwhale.beattrip;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.beardedwhale.library.MainGame;

public class ThisGame extends MainGame implements ApplicationListener {

    @Override
    public void create() {
	super.create();
	setScreen(new Level(this));
    }
    
    @Override
    public void initiate() {
	GameGlobal.initiate();
	GameAssets.initiate();
    }

    @Override
    public void dispose() {
    }

    @Override
    public void gameRender(SpriteBatch batch, float dt) {		
	getScreen().render(dt);
    }

    @Override
    public void resize(int width, int height) {
	GameGlobal.initiate();
	//Assets.setupTextures();
	getScreen().resize(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
