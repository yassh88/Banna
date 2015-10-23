package com.jodhpur.banna.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jodhpur.banna.Banna;

/**
 * Created by Yashwant on 9/27/2015.
 */
public class MenuState extends State {
    private Texture background;
    private Texture playButton;
    private String highScoreString;
    private BitmapFont bitmapHighScoreFontName;
    private int score;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false,Banna.WIDTH/2,Banna.HEIGHT/2);
        background = new Texture("bannabg.png");
        playButton = new Texture("playbtn.png");
        score = getHighScore();
        highScoreString = "HIGH SCORE: "+score;
        bitmapHighScoreFontName = new BitmapFont();
        bitmapHighScoreFontName.setColor(Color.BLACK);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
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
        sb.draw(playButton,cam.position.x-playButton.getWidth()/2,cam.position.y );

        bitmapHighScoreFontName.draw(sb, highScoreString, cam.position.x-playButton.getWidth()/2, 150);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }

    private int getHighScore(){
        Preferences prefs = Gdx.app.getPreferences("MyPreferences");
        int lastHighScore = prefs.getInteger("score", 0);
        return prefs.getInteger("score",0);
    }
}
