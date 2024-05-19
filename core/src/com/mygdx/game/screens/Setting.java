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

public class Setting implements Screen {
    private Viewport view;
    Texture bg_image;
    private int i=0;
    private Stage stage;
    private OrthographicCamera camera;

    private MyGdxGame game;

    private TextButton Setting;
    private TextButton Back;
    private TextButton Sound;
    private TextButton About;

    Skin butt_skin = new Skin(Gdx.files.internal("gdx-skins-master/freezing/skin/freezing-ui.json"));

    public Setting( MyGdxGame game) {

        this.game = game;
        camera = new OrthographicCamera();
        view = new FitViewport(1400, 788,camera );
        stage = new Stage(view);

        Back = new TextButton("Back", butt_skin );
        Sound = new TextButton("Sound", butt_skin );
        About = new TextButton("About", butt_skin);
        camera.setToOrtho(false,1400,788);

    }

    @Override
    public void show() {

        Sound.setSize(400, 200);
        Sound.setPosition(900,550 );

        About.setSize(400, 200);
        About.setPosition(900, 300);

        Back.setSize(400, 200);
        Back.setPosition(900, 50);

        stage.addActor(Sound);
        stage.addActor(Back);
        stage.addActor(About);
        bg_image = new Texture("main_1.jpg");
//        camera = new OrthographicCamera();

        Gdx.input.setInputProcessor(stage);   // main
        ;
        Back.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MyHome(game));
            }
        }) ;
// clear stage
        // texture new image
        // draw
        Sound.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(i==0) {
                    game.music.stop();
                    i=1;
                }
                else if(i==1){
                    i=0;
                    game.music.play();
                }
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
