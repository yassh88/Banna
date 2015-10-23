package com.jodhpur.banna.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.jodhpur.banna.Banna;

/**
 * Created by Yashwant on 9/23/2015.
 */
public abstract class State  {
    protected GameStateManager gsm;
    protected OrthographicCamera cam;
    protected Vector3 mouse;

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Banna.WIDTH,Banna.HEIGHT);
    }

    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

}