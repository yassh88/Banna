package com.jodhpur.banna.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jodhpur.banna.Banna;

/**
 * Created by Yashwant on 10/3/2015.
 */
public class GameOver extends State {
    private Texture background;
    private Texture gameOverTexture;


    public GameOver(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Banna.WIDTH/2,Banna.HEIGHT/2);
        background = new Texture("bannabg.png");
        gameOverTexture = new Texture("gameover.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0,Banna.WIDTH, Banna.HEIGHT);
        sb.draw(gameOverTexture,cam.position.x- gameOverTexture.getWidth()/2,cam.position.y );
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameOverTexture.dispose();
    }
}
