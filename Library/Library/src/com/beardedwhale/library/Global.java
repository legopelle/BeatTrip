package com.beardedwhale.library;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Global {

    public static float uiWidth;
    public static float uiHeight;
    public static float scrWidth;
    public static float scrHeight;
    public static float aspectRatio;
    public static float uiBottom;
    public static float uiLeft;
    public static float uiTop;
    public static float uiRight;

    public static OrthographicCamera uiCamera;
    public static SpriteBatch batch;

    //The size determines what resolution of images to use.
    public static String size = "H"; //Can me H, M or L for High, Medium, Low
    
    public static InputMultiplexer input;

    public static void initiate() {
	scrWidth = Gdx.graphics.getWidth();
	scrHeight = Gdx.graphics.getHeight();
	aspectRatio = scrHeight/scrWidth;

	uiWidth = 600;
	uiHeight = uiWidth * aspectRatio;
	uiBottom = -uiHeight/2;   
	uiLeft = -uiWidth/2; 
	uiTop = uiHeight/2;   
	uiRight = uiWidth/2; 

	uiCamera = new OrthographicCamera(uiWidth, uiHeight);
	batch = new SpriteBatch();
	batch.setProjectionMatrix(uiCamera.combined);
	
	input = new InputMultiplexer();
    }
} 
