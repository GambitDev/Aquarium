package com.gambit.aquarium.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class Fish {

    private float fishXPos , fishYPos;
    private float fishWidth, fishHeight;
    private float fishXVelocity , fishYVelocity;
    private Color fishColor;
    private ShapeRenderer renderer;

    public Fish() {
        fishColor = pickFishColor();
        fishXVelocity = 5;
        fishYVelocity = 5;
    }

    public void init() {
        renderer = new ShapeRenderer();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(fishColor);
        renderer.rect(fishXPos, fishYPos, fishWidth, fishHeight);
        renderer.end();
//        getStartVelocity();
    }

    private void getStartVelocity() {
        Random r = new Random();
        int[] options = {-1 , 1};
        fishXVelocity *= options[r.nextInt(options.length)];
        fishYVelocity *= options[r.nextInt(options.length)];
    }

    public void renderNextFrame(float x , float y) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.WHITE);
        renderer.circle(x , y, 10.0f);
        renderer.end();

        if (fishXPos == x)
            return;
//        if (fishXPos > x) {
//            if (fishXVelocity > 0)
//                changeXVelocity();
//        }
        float m = (fishYPos - y) / (fishXPos - x);
        // y - fishYPos = m(x - fishXPos)
        fishXPos += fishXVelocity;
        fishYPos = findNewYPos(fishXPos - fishXVelocity , fishXPos , fishYPos , m);
    }

    public void drawNextFrame() {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(fishColor);
        renderer.rect(fishXPos, fishYPos, fishWidth, fishHeight);
        renderer.end();
    }

    private float findNewYPos(float prevFishXPos , float fishXPos , float fishYPos , float m) {
        return m * (fishXPos - prevFishXPos) + fishYPos;
    }

    private Color pickFishColor() {
        Color[] colors = {Color.WHITE,
                Color.BLACK,
                Color.BLUE,
                Color.BROWN,
                Color.CHARTREUSE,
                Color.CORAL};

        Random r = new Random();
        return colors[r.nextInt(colors.length)];
    }

    private void changeXVelocity() {
        fishXVelocity = -fishXVelocity;
    }

    private void changeYVelocity() {
        fishYVelocity = -fishYVelocity;
    }

    public float getFishXPos() {
        return fishXPos;
    }

    public void setFishXPos(float fishXPos) {
        this.fishXPos = fishXPos;
    }

    public float getFishYPos() {
        return fishYPos;
    }

    public void setFishYPos(float fishYPos) {
        this.fishYPos = fishYPos;
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
}
