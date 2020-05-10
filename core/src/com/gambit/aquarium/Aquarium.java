package com.gambit.aquarium;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.gambit.aquarium.Entities.Fish;

public class Aquarium extends ApplicationAdapter {

	private OrthographicCamera camera;
	private Vector3 touchPosition;
	private Array<Fish> fish;

	@Override
	public void create () {
		fish = initFishes(10);
		camera = new OrthographicCamera();
		camera.setToOrtho(false , Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
		touchPosition = new Vector3();
	}

	@Override
	public void render () {
		camera.update();
		if (fish != null) {
			if (Gdx.input.isTouched()) {
				touchPosition.set(Gdx.input.getX() , Gdx.input.getY() , 0);
				camera.unproject(touchPosition);
				for (Fish currentFish : fish) {
					currentFish.setFishDestinationPos(touchPosition);
				}
			}
		}

		Gdx.gl.glClearColor(52.0f/255.0f,
				171.0f/255.0f,
				235.0f/255.0f,
				1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderFishes();
	}
	
	@Override
	public void dispose () {
		for (Fish currentFish : fish) {
			currentFish.disposeShapeRenderer();
		}
	}

	private Array<Fish> initFishes(int numberOfFish) {
		Array<Fish> fish = new Array<>();
		for (int i = 0; i < numberOfFish; i++) {
			fish.add(new Fish(250, 100));
		}
		return fish;
	}

	private void renderFishes() {
		for (Fish currentFish : fish) {
			currentFish.renderNextFrame();
			currentFish.drawNextFrame();
		}
	}
}
