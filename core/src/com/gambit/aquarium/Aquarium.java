package com.gambit.aquarium;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.gambit.aquarium.Entities.Fish;

import java.util.ArrayList;

public class Aquarium extends ApplicationAdapter {

	private ArrayList<Fish> fish;

	@Override
	public void create () {
		fish = initFishes(1);
	}

	@Override
	public void render () {
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

	private ArrayList<Fish> initFishes(int numberOfFish) {
		ArrayList<Fish> fish = new ArrayList<>();
		for (int i = 0; i < numberOfFish; i++) {
			fish.add(new Fish(250 , 100));
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
