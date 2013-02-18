package com.beardedwhale.beattrip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.beardedwhale.library.Cons;
import com.beardedwhale.library.Global;
import com.beardedwhale.library.Lib;
import com.beardedwhale.library.MainGame;

public class Level implements Screen {

    private Music music;
    int beatnr = 0;

    public MainGame game;
    private float beatclock;
    private float clock;
    private float bpm;
    private NinePatch tilebeat4;
    private int points;
    private NinePatch tilebeat1;
    private NinePatch nullbeat;

    public Level(MainGame parent) {
	game = parent;

	bpm = 160;

	music = Gdx.audio.newMusic(Gdx.files.internal("samples/music.ogg"));
	music.setLooping(true);
	music.setVolume(0.5f);
	music.play();

	tilebeat4 = new NinePatch(GameAssets.tiles.findRegion("tilebeat4"),2,2,5,2);
	tilebeat1 = new NinePatch(GameAssets.tiles.findRegion("tilebeat1"),2,2,5,2);
	nullbeat = new NinePatch(GameAssets.tiles.findRegion("nulltile"),2,2,5,2);
	//beatbox = Lib.makeSprite(GameAssets.tiles, "beatbox", Global.uiWidth/8, Cons.uiCenter, 0);

	points = 0;
    }

    @Override
    public void render(float dt) {		
	Gdx.gl.glClearColor(1, 1, 1, 1);
	//Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

	clock += dt;

	String target = "10-00-00:10-00-01:10-10-10:10-00-01";
	target += target;
	target += target;
	target = target.replaceAll("-", "");
	target = target.replaceAll(":", "");
	float beatlength = 60/bpm;

	beatclock = music.getPosition() - beatnr*beatlength;

	boolean currenthit = (target.charAt(beatnr) == 49);
	boolean nexthit = (target.charAt((beatnr+1)%target.length()) == 49);

	if (currenthit) {
	    Gdx.gl.glClearColor(0, beatclock/beatlength, 1-beatclock/beatlength, 1);
	}
	else {
	    Gdx.gl.glClearColor(0, 1, 0, 1);
	}

	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

	for (int i = 0; i < target.length(); i++) {
	    float pos = Global.uiLeft + (i + 0.5f) * Global.uiWidth/8 - music.getPosition()/beatlength*Global.uiWidth/8;
	    if (target.charAt(i) == 49) {
		if (i%6 == 0) {
		    tilebeat1.draw(Global.batch, pos, Global.uiBottom, Global.uiWidth/8, Global.uiHeight/2);
		}
		else {
		    tilebeat4.draw(Global.batch, pos, Global.uiBottom, Global.uiWidth/8, Global.uiHeight/2);
		}
	    }
	    else {
		nullbeat.draw(Global.batch, pos, Global.uiBottom, Global.uiWidth/8, Global.uiHeight/2);
	    }
	}

	if (currenthit) {
	    Lib.drawText(GameAssets.cambriaWhiteLarge, 0.5f, "HIT", Cons.uiCenter);
	}

	Lib.drawText(GameAssets.cambriaWhiteLarge, 1f, "!", new Vector2(Global.uiLeft + Global.uiWidth/8, 0));
	Lib.drawText(GameAssets.cambriaWhiteLarge, 1f, "Points: " + points, new Vector2(0, -100));


	if (music.getPosition() > (beatnr + 1)*beatlength) {
	    beatnr++;
	    beatnr = beatnr%target.length();
	}

	if (Gdx.input.justTouched()) {
	    if (currenthit) {
		float diff = beatclock;
		if (diff < 1/40f) {
		    points += 100;
		}
		else if (diff < 1/20f) {
		    points += 50;
		}
	    }
	    if (nexthit) {
		float diff = beatlength - beatclock;
		if (diff < 1/40f) {
		    points += 100;
		}
		else if (diff < 1/20f) {
		    points += 50;
		}
	    }

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
