package com.gambit.aquarium;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.gambit.aquarium.Entities.Fish;

import java.util.Random;

public class Aquarium extends ApplicationAdapter {

	private Fish fish;
	private int screenWidth;
	private int screenHeight;
	private Random r;

	@Override
	public void create () {
		fish = new Fish();
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		fish.setFishHeight(100);
		fish.setFishWidth(250);
		fish.setFishXPos(screenWidth/2);
		fish.setFishYPos(screenHeight/2);
		fish.init();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(52.0f/255.0f,
				171.0f/255.0f,
				235.0f/255.0f,
				1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		float nextX = r.nextInt(screenWidth);
		float nextY = r.nextInt(screenHeight);
		fish.renderNextFrame(nextX , nextY);
		fish.drawNextFrame();
	}
	
	@Override
	public void dispose () {

	}
}
