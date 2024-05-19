package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.screens.MyHome;

public class MyGdxGame extends Game {
	public SpriteBatch batch;
	Texture img;
	public Music music;

//	private Viewport viewPort;
//
//	private OrthographicCamera camera;
//
//	private Stage stage;
	//always for screen


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("main_clipdrop-cleanup.jpg");
		setScreen(new MyHome(this));
		music=Gdx.audio.newMusic(Gdx.files.internal("backgroundmusic.mp3"));
		music.setLooping(true);
		music.setVolume(0.12f);
		music.play();
//		setScreen(new MyHome(this));
	}

	@Override
	public void render () {
//		ScreenUtils.clear(1, 0, 0, 1);
//
//		Gdx.gl.glClearColor(0, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
		super.render();

	}


	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
