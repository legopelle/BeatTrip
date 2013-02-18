package com.beardedwhale.beattrip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.beardedwhale.library.Global;
import com.beardedwhale.library.Lib;
import com.beardedwhale.library.MainGame;

public class Level implements Screen {

    private Music music;
    int beatnr = 0;

    public MainGame game;
    private float beatclock;
    private float clock;

    public Level(MainGame parent) {
	game = parent;
	music = Gdx.audio.newMusic(Gdx.files.internal("samples/music.ogg"));
	music.setLooping(true);
	music.setVolume(0.5f);
	music.play();
    }

    @Override
    public void render(float dt) {		
	Gdx.gl.glClearColor(1, 1, 1, 1);
	//Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

	clock += Gdx.graphics.getDeltaTime();

	String target = "10-00-00:10-00-01:10-10-10:10-00-01";
	target += target;
	target += target;
	target = target.replaceAll("-", "");
	target = target.replaceAll(":", "");
	float beatlength = 60/160f;

	beatclock = music.getPosition() - beatnr*beatlength;

	Boolean currenthit = (target.charAt(beatnr) == 49);

	for (int i = beatnr; i < target.length(); i++) {
	    Vector2 pos = new Vector2(Global.uiLeft + i * Global.uiWidth/10 - music.getPosition()/beatlength*Global.uiWidth/10,0);
	    if (target.charAt(i) == 49) {
		Lib.drawText(GameAssets.cambriaWhiteLarge, 1f, "X", pos);
	    }
	    else {
		Lib.drawText(GameAssets.cambriaWhiteLarge, 1f, "-", pos);
	    }
	}

	if (music.getPosition() > (beatnr + 1)*beatlength) {
	    beatnr++;
	    beatnr = beatnr%target.length();
	}

	if (currenthit) {
	    Gdx.gl.glClearColor(0, beatclock/beatlength, 1-beatclock/beatlength, 1);
	}
	else {
	    Gdx.gl.glClearColor(0, 1, 0, 1);
	}

	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	
	if (Gdx.input.justTouched()) {
	    Lib.log(beatlength - beatclock);
	}
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void show() {
	// TODO Auto-generated method stub

    }

    @Override
    public void hide() {
	// TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
	// TODO Auto-generated method stub

    }
}
