package com.jodhpur.banna.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.jodhpur.banna.Banna;
import com.jodhpur.banna.sprites.Bird;
import com.jodhpur.banna.sprites.Cactus;

/**
 * Created by Yashwant on 9/27/2015.
 */
public class PlayState extends State {
    private Bird bird;
    private Texture bg;
    private Array<Cactus> cactuses;
    private Texture ground;
    private static final int TUBE_SPACEING = 125;
    private static final int TUBE_COUNT = 4;
    private Vector2 groundPos1,groundPos2;
    private static final int GROUND_Y_OFFSET = -120;
    private int score;
    private String scoreString;
    private BitmapFont bitmapScoreFontName;


    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird  = new Bird(50,300);
        bg = new Texture("bannabg.png");
        ground =  new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x- cam.viewportWidth, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x- cam.viewportWidth)+ground.getWidth(), GROUND_Y_OFFSET);
        cam.setToOrtho(false,Banna.WIDTH/2,Banna.HEIGHT/2);
        cactuses = new Array<Cactus>();
        for (int i=1;i<=TUBE_COUNT;i++){
            cactuses.add(new Cactus(i * (TUBE_SPACEING + Cactus.TUBE_WIDTH)));

        }
        score = 1;
        scoreString = "SCORE: 1";
        bitmapScoreFontName = new BitmapFont();
        bitmapScoreFontName.setColor(Color.BLACK);
    }

    private void saveHighScore(int Score){
        Preferences prefs = Gdx.app.getPreferences("MyPreferences");
        int lastHighScore = prefs.getInteger("score", 0);
        if(lastHighScore< score) {
            prefs.putInteger("score", Score);
            prefs.flush();
        }
    }



    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x=bird.getPosition().x+80;
        for (int i=0;i<TUBE_COUNT;i++){
            Cactus tube =  cactuses.get(i);
            if(cam.position.x - (cam.viewportWidth/2)>tube.getPosTopTube().x+tube.getTopCactus().getWidth()){
                tube.repostion(tube.getPosTopTube().x  + ((tube.TUBE_WIDTH+TUBE_SPACEING)* TUBE_COUNT));
                score++;
                scoreString = "SCORE: " + score;
                //dropSound.play();
                // iter.remove();
            }

            if(tube.collides(bird.getBounds())) {
                saveHighScore(score);
                gsm.set(new GameOver(gsm));
            }
        }
        if(bird.getPosition().y<=ground.getHeight()+GROUND_Y_OFFSET){
            saveHighScore(score);
            gsm.set(new GameOver(gsm));
        }

        cam.update();
    }



    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,cam.position.x-(cam.viewportWidth/2),0 );
        sb.draw(bird.getTexture(),bird.getPosition().x,bird.getPosition().y);
        for(Cactus tube: cactuses) {
            sb.draw(tube.getTopCactus(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomCactus(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        bitmapScoreFontName.draw(sb, scoreString, cam.position.x, 100);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for(Cactus tube: cactuses){
            tube.dispose();
            bitmapScoreFontName.dispose();
        }
    }
    private void updateGround(){
        if(cam.position.x-(cam.viewportWidth/2)>groundPos1.x+ground.getWidth()){
            groundPos1.add(ground.getWidth()*2,0);
        }
        if(cam.position.x-(cam.viewportWidth/2)>groundPos2.x+ground.getWidth()){
            groundPos2.add(ground.getWidth()*2,0);
        }
    }

}
