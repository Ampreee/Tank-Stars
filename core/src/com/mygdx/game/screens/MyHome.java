package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import jdk.internal.net.http.frame.SettingsFrame;

import java.util.Set;

public class MyHome implements Screen {
    private Viewport view;
    Texture bg_image;

    private Stage stage;
    private OrthographicCamera camera;

    private MyGdxGame game;

    private TextButton NewGame;
    private TextButton Exit;
    private TextButton LoadGame;
    private TextButton Settings;

    Skin butt_skin = new Skin(Gdx.files.internal("gdx-skins-master/freezing/skin/freezing-ui.json"));

    public MyHome( MyGdxGame game) {

        this.game = game;
        camera = new OrthographicCamera();
        view = new FitViewport(1400, 788,camera );
        stage = new Stage(view);

        NewGame = new TextButton("New Game", butt_skin );
        Exit = new TextButton("Exit", butt_skin );
        LoadGame = new TextButton("Load Game", butt_skin );
        Settings = new TextButton("Settings", butt_skin);
        camera.setToOrtho(false,1400,788);

    }

    @Override
    public void show() {

        NewGame.setSize(300, 100);
        NewGame.setPosition(900, 500);

        LoadGame.setSize(300, 100);
        LoadGame.setPosition(900,350 );

        Settings.setSize(300, 100);
        Settings.setPosition(900, 200);

        Exit.setSize(300, 100);
        Exit.setPosition(900, 50);

        stage.addActor(NewGame);
        stage.addActor(LoadGame);
        stage.addActor(Exit);
        stage.addActor(Settings);
        bg_image = new Texture("main_1.jpg");
//        camera = new OrthographicCamera();

        Gdx.input.setInputProcessor(stage);   // main
        NewGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new chooseTank(game));
            }

        });

        Exit.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        }) ;
// clear stage
        // texture new image
        // draw
        LoadGame.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadGame(game));
            }
        });

        Settings.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Setting(game));
            }
        });

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(bg_image, 0, 0);
        game.batch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        view.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {

    }
}
