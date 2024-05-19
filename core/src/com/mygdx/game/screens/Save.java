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
import com.mygdx.game.data.saveload;

public class Save implements Screen {
    private Viewport view;

    Texture bg_image;

    private Stage stage;
    private OrthographicCamera camera;

    private MyGdxGame game;

    private TextButton save1;
    private TextButton save2;
    private TextButton save3;
    private TextButton save4;
    private TextButton back;

    Skin but_skin = new Skin(Gdx.files.internal("gdx-skins-master/freezing/skin/freezing-ui.json"));;

    public Save(MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        view = new FitViewport(1400, 788,camera );
        stage = new Stage(view);


        camera.setToOrtho(false,1400,788);
    }


    @Override
    public void show() {

        save1 = new TextButton("Save Slot 1", but_skin);
        save2 = new TextButton("Save Slot 2", but_skin);
        save3 = new TextButton("Save Slot 3", but_skin);
        save4 = new TextButton("Save Slot 4", but_skin);
        back = new TextButton("Back", but_skin);


        save1.setSize(1400, 100);
        save2.setSize(1400, 100);
        save3.setSize(1400, 100);
        save4.setSize(1400, 100);
//        save1.setSize(1400, 100);
        back.setSize(1400,100 );
        save1.setPosition(50, 650);
        save2.setPosition(50, 500);
        save3.setPosition(50, 350);
        save4.setPosition(50, 200);

        back.setPosition(50,50 );

        stage.addActor(save1);
        stage.addActor(save2);
        stage.addActor(save3);
        stage.addActor(save4);
        stage.addActor(back);

        bg_image = new Texture("bg_loadGame.png");

        Gdx.input.setInputProcessor(stage);
        save4.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                saveload save4=new saveload(game,4);
                save4.save();
            }
        });
        save1.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                saveload save1=new saveload(game,1);
                save1.save();
            }
        });
        save2.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                saveload save2=new saveload(game,2);
                save2.save();
            }
        });
        save3.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                saveload save3=new saveload(game,3);
                save3.save();
            }
        });
        back.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                saveload save3=new saveload(game,0);
                save3.save();
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
