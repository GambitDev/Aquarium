package com.gambit.aquarium.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class Fish {

    private float fishXPos , fishYPos;
    private float fishWidth, fishHeight;
    private float fishXVelocity , fishYVelocity;
    private Color fishColor;
    private ShapeRenderer renderer;

    public Fish(float fishWidth , float fishHeight) {
        this.fishWidth = fishWidth;
        this.fishHeight = fishHeight;
        fishColor = pickFishColor();
        renderer = new ShapeRenderer();
    }

    //calculate how much to move per frame for smooth movement
    private void calcVelocity(float destinationX , float destinationY) {
        float frames = 15.0f;
        float deltaX = fishXPos > destinationX ?
                fishXPos - destinationX :
                destinationX - fishXPos;
        fishXVelocity = deltaX / frames;
        float deltaY = fishYPos > destinationY ?
                fishYPos - destinationY :
                destinationY - fishYPos;
        fishYVelocity = deltaY / frames;
    }

    public void renderNextFrame(float x , float y) {
        calcVelocity(x , y);

        if (fishXPos == x) {
            if (fishXVelocity < 0)
                reverseXVelocity();
            normalizeFishYPos(y);
            return;
        }
        if (fishXPos > x) {
            if (fishXPos - x < fishXVelocity) {
                fishXPos = x;
                return;
            }
            if (fishXVelocity > 0)
                reverseXVelocity();
        }
        float m = (fishYPos - y) / (fishXPos - x);
        fishXPos += fishXVelocity;
        fishYPos = findNewYPos(fishXPos - fishXVelocity , fishXPos , fishYPos , m);
    }

    private void normalizeFishYPos(float destinationY) {
        if (fishYPos > destinationY) {
            if (fishYPos - destinationY < fishYVelocity) {
                fishYPos = destinationY;
                reverseYVelocity();
                return;
            }
            if (fishYVelocity > 0)
                reverseYVelocity();
        }
        fishYPos += fishYVelocity;
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
                Color.CORAL,
                Color.CYAN,
                Color.DARK_GRAY,
                Color.FIREBRICK,
                Color.FOREST,
                Color.GOLD,
                Color.GOLDENROD,
                Color.SALMON};

        Random r = new Random();
        return colors[r.nextInt(colors.length)];
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
