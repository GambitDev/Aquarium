package com.gambit.aquarium;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.gambit.aquarium.Entities.Fish;

public class Aquarium extends ApplicationAdapter {

	private Fish fish;
	private OrthographicCamera camera;
	private Vector3 touchPos;

	@Override
	public void create () {
		fish = new Fish(250 , 100);
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float screenMiddleX = screenWidth /2.0f - fish.getFishWidth()/2.0f;
		float screenMiddleY = screenHeight /2.0f - fish.getFishHeight()/2.0f;
		camera = new OrthographicCamera();
		camera.setToOrtho(false , screenWidth, screenHeight);
		touchPos = new Vector3(screenMiddleX , screenMiddleY , 0);

		//centers fish on screen
		fish.setFishXPos(screenMiddleX);
		fish.setFishYPos(screenMiddleY);
	}

	@Override
	public void render () {
		//adjust camera to right pos
		camera.update();
		if (Gdx.input.isTouched()) {
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
		}

		//draw next frame
		Gdx.gl.glClearColor(52.0f/255.0f,
				171.0f/255.0f,
				235.0f/255.0f,
				1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		fish.renderNextFrame(touchPos.x , touchPos.y);
		fish.drawNextFrame();
	}
	
	@Override
	public void dispose () {
		fish.disposeShapeRenderer();
	}
}
