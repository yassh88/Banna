package com.jodhpur.banna.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Yashwant on 9/27/2015.
 */
public class Cactus {
    private Texture topCactus, bottomCactus;
    private Vector2 posTopTube, posBottomTube;
    private Random rand;
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int lOWEST_OPENING = 120;
    public static final int TUBE_WIDTH = 60;
    private Rectangle boundsTop, boundsBot;

    public Cactus(int x){
        topCactus =  new Texture("cactus.png");
        bottomCactus =  new Texture("cactus.png");
        rand =  new Random();
        posTopTube = new Vector2(x,rand.nextInt(FLUCTUATION)+TUBE_GAP+lOWEST_OPENING);
        posBottomTube = new Vector2(x,posTopTube.y - TUBE_GAP - bottomCactus.getHeight());
        boundsTop =  new Rectangle(posTopTube.x,posTopTube.y, topCactus.getWidth(), topCactus.getHeight());
        boundsBot =  new Rectangle(posBottomTube.x,posBottomTube.y, bottomCactus.getWidth(), bottomCactus.getHeight());
    }

    public Texture getTopCactus() {
        return topCactus;
    }

    public void setTopCactus(Texture topCactus) {
        this.topCactus = topCactus;
    }

    public Texture getBottomCactus() {
        return bottomCactus;
    }

    public void setBottomCactus(Texture bottomCactus) {
        this.bottomCactus = bottomCactus;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public void setPosTopTube(Vector2 posTopTube) {
        this.posTopTube = posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public void setPosBottomTube(Vector2 posBottomTube) {
        this.posBottomTube = posBottomTube;
    }

    public void  repostion(float x) {
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + lOWEST_OPENING);
        posBottomTube.set(x, posTopTube.y - TUBE_GAP - bottomCactus.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBottomTube.x,posBottomTube.y);

    }

    public boolean collides(Rectangle player){
        return  player.overlaps(boundsTop)||player.overlaps(boundsBot);
    }

    public void dispose(){
        bottomCactus.dispose();
        topCactus.dispose();
    }
}
