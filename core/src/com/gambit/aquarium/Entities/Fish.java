package com.gambit.aquarium.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Fish {

    private Vector3 fishPosition , fishDestinationPos;
    private float fishWidth, fishHeight;
    private float fishXVelocity , fishYVelocity;
    private Color fishColor;
    private ShapeRenderer renderer;

    public Fish(float fishWidth , float fishHeight) {
        this.fishWidth = fishWidth;
        this.fishHeight = fishHeight;
        fishColor = pickFishColor();
        renderer = new ShapeRenderer();
        fishPosition = getRandomPos();
        fishDestinationPos = fishPosition;
    }

    private Vector3 getRandomPos() {
        Random r = new Random();
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();
        return new Vector3(r.nextInt(screenWidth) , r.nextInt(screenHeight) , 0);
    }

    //calculate how much to move per frame for smooth movement
    private void calcVelocity(float destinationX , float destinationY) {
        float frames = 30.0f;
        float deltaX = fishPosition.x > destinationX ?
                fishPosition.x - destinationX :
                destinationX - fishPosition.x;
        fishXVelocity = deltaX / frames;
        float deltaY = fishPosition.y > destinationY ?
                fishPosition.y - destinationY :
                destinationY - fishPosition.y;
        fishYVelocity = deltaY / frames;
    }

    public void renderNextFrame() {
        boolean newDestinationNecessary = needNewDestination();
        if (newDestinationNecessary) {
            fishDestinationPos = getRandomPos();
        }

//        markPoint(fishDestinationPos);

        calcVelocity(fishDestinationPos.x , fishDestinationPos.y);

        if (fishPosition.x == fishDestinationPos.x) {
            if (fishXVelocity < 0)
                reverseXVelocity();
            normalizeFishYPos(fishDestinationPos.y);
            return;
        }

        if (fishPosition.x > fishDestinationPos.x) {
            if (fishPosition.x - fishDestinationPos.x < fishXVelocity) {
                fishPosition.x = fishDestinationPos.x;
            }
            if (fishXVelocity > 0)
                reverseXVelocity();
        }

        float m = (fishPosition.y - fishDestinationPos.y) / (fishPosition.x - fishDestinationPos.x);
        fishPosition.x += fishXVelocity;
        fishPosition.y = findNewYPos(fishPosition.x - fishXVelocity ,
                fishPosition.x , fishPosition.y , m);
    }

    private void normalizeFishYPos(float destinationY) {
        if (fishPosition.y > destinationY) {
            if (fishPosition.y - destinationY < fishYVelocity) {
                fishPosition.y = destinationY;
                reverseYVelocity();
                return;
            }
            if (fishYVelocity > 0)
                reverseYVelocity();
        }
        fishPosition.y += fishYVelocity;
    }

    public void drawNextFrame() {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(fishColor);
        renderer.rect(fishPosition.x, fishPosition.y, fishWidth, fishHeight);
        renderer.end();
    }

    private float findNewYPos(float prevFishXPos , float fishXPos , float fishYPos , float m) {
        return m * (fishXPos - prevFishXPos) + fishYPos;
    }

    private void markPoint(Vector3 pos) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.WHITE);
        renderer.circle(pos.x , pos.y , 10);
        renderer.end();
    }

    private Color pickFishColor() {
        Color[] colors = {Color.WHITE,
                Color.BLACK,
                Color.BLUE,
                Color.BROWN,
                Color.CHARTREUSE,
                Color.CORAL,
                Color.CYAN,
                Color.DARK_GRAY,
                Color.FIREBRICK,
                Color.FOREST,
                Color.GOLD,
                Color.GOLDENROD,
                Color.SALMON};

        return colors[MathUtils.random(0 , colors.length)];
    }

    private boolean needNewDestination() {
        int chances = MathUtils.random(1000);
        return chances >= 990;
    }

    public void disposeShapeRenderer() {
        renderer.dispose();
    }

    private void reverseXVelocity() {
        fishXVelocity = -fishXVelocity;
    }

    private void reverseYVelocity() {
        fishYVelocity = -fishYVelocity;
    }

    public float getFishWidth() {
        return fishWidth;
    }

    public void setFishWidth(float fishWidth) {
        this.fishWidth = fishWidth;
    }

    public float getFishHeight() {
        return fishHeight;
    }

    public void setFishHeight(float fishHeight) {
        this.fishHeight = fishHeight;
    }

    public Color getFishColor() {
        return fishColor;
    }

    public void setFishColor(Color fishColor) {
        this.fishColor = fishColor;
    }

    public float getFishXVelocity() {
        return fishXVelocity;
    }

    public void setFishXVelocity(float fishXVelocity) {
        this.fishXVelocity = fishXVelocity;
    }

    public float getFishYVelocity() {
        return fishYVelocity;
    }

    public void setFishYVelocity(float fishYVelocity) {
        this.fishYVelocity = fishYVelocity;
    }

    public Vector3 getFishPosition() {
        return fishPosition;
    }

    public void setFishPosition(Vector3 fishPosition) {
        this.fishPosition = fishPosition;
    }

    public Vector3 getFishDestinationPos() {
        return fishDestinationPos;
    }

    public void setFishDestinationPos(Vector3 fishDestinationPos) {
        this.fishDestinationPos = fishDestinationPos;
    }
}
