package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class chooseTank implements Screen {

    private Viewport view;

    Texture bg_image;

    private Stage stage;
    private OrthographicCamera camera;

    private MyGdxGame game;

//    private ImageButton tank1;
//    private ImageButton tank2;
//    private ImageButton tank3;
    private TextButton pick;
    private TextButton back;
    Skin butt_skin;

    public chooseTank(MyGdxGame game){

        this.game = game;
        camera = new OrthographicCamera();
        view = new FitViewport(1400, 788,camera );
        stage = new Stage(view);
//
//        pick = new TextButton("Pick", butt_skin);
//        back = new TextButton("back", butt_skin);
        camera.setToOrtho(false,1400,788);
    }


    @Override
    public void show() {
        butt_skin = new Skin(Gdx.files.internal("gdx-skins-master/freezing/skin/freezing-ui.json"));

        pick = new TextButton("Pick", butt_skin);
        back = new TextButton("back", butt_skin);

        pick.setSize(200, 80);
        pick.setPosition(600, 20 );

        back.setSize(100, 75);
        back.setPosition(70, 625);


        bg_image =  new Texture("with_logoMain.png");

        Gdx.input.setInputProcessor(stage);

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MyHome(game));
            }
        });

        stage.addActor(back);
        stage.addActor(pick);

//        Gdx.input.setInputProcessor(stage);

        pick.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Play1(game));
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
