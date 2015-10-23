package com.jodhpur.banna;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jodhpur.banna.state.GameStateManager;
import com.jodhpur.banna.state.MenuState;

public class Banna extends ApplicationAdapter {
	public static int WIDTH;
	public static int HEIGHT ;
	public static final String TITLE = "BANNA";

	private SpriteBatch spriteBatch;
	private GameStateManager gameStateManager;
	private Music music;

	public Banna(int width, int height) {
		this.WIDTH= 1300;
		this.HEIGHT= 700;
	}

	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		gameStateManager = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("bannasa.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		gameStateManager.push(new MenuState(gameStateManager));
		Gdx.gl.glClearColor(1, 0, 0, 1);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(spriteBatch);
	}

	@Override
	public void dispose() {
		music.stop();
	}
}
