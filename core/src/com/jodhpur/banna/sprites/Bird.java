package com.jodhpur.banna.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.jodhpur.banna.Banna;

/**
 * Created by Yashwant on 9/27/2015.
 */
public class Bird {
    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Rectangle bounds;
    private Texture bannaTexturem;
    private Sound flap;

    public Bird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        bannaTexturem = new Texture("bird.png");
        bounds =  new Rectangle(x,y, bannaTexturem.getWidth()/3, bannaTexturem.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("jump.mp3"));
    }

    public void update(float dt){
       // bannaTexturem.update(dt);
        if(position.y >0)
        velocity.add(0,GRAVITY,0);
        velocity.scl(dt);
        position.add(MOVEMENT*dt ,velocity.y,0);
        velocity.scl(1/dt);
        if(position.y<0){
            position.y= 0;
        }
        if(position.y >= Banna.HEIGHT/2){
            position.y = Banna.HEIGHT/2;
        }
        bounds.setPosition(position.x,position.y);
    }

    public Texture  getTexture() {
        return bannaTexturem;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void jump(){
        velocity.y= 250;
        flap.play(0.2f);
    }

    public Rectangle getBounds(){
      return   bounds;
    }

    public void dispose(){
        bannaTexturem.dispose();
        flap.dispose();
    }
}
