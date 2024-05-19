package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
//import sprites.Tank;


public class play implements Screen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    Array<Body> tempbodies = new Array<Body>();
    private TmxMapLoader mapLoader;
//    private TiledMap T_map;

//    private OrthogonalTiledMapRenderer Renderer;

    //2d box

    private World world;

    public Body b2body;

    public  Body tank;

    public static Vector2 movement = new Vector2();
    private Box2DDebugRenderer  b2br;


    FitViewport view;
    private Stage stage;
    private MyGdxGame game;

    private TextButton pause;
    Skin butt_skin;

    public play(MyGdxGame game){
        this.game = game;
        camera = new OrthographicCamera();
        view = new FitViewport(1400, 788,camera );
        stage = new Stage(view);



        mapLoader = new TmxMapLoader();
        map = mapLoader.load("denmamap.tmx");

        camera.setToOrtho(false,1400,788);

//        camera.position.set(view.getWorldHeight() /2, view.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -10), true);
        b2br = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();

        FixtureDef fdef = new FixtureDef();

        Body body;
        // layer

        for(MapObject object :map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class) ){

            Rectangle rec = ( (RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rec.getX() + rec.getWidth() / 2, rec.getY() + rec.getHeight()/ 2 );

            body = world.createBody(bdef);

            shape.setAsBox(rec.getWidth() / 2, rec.getHeight() / 2);
            fdef.shape = shape;

            fdef.friction = 0.1f;

            body.createFixture(fdef);
        }

        // layer


    }


    public Body defineTank(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(500, 400);

        bdef.fixedRotation = true;
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();

        CircleShape shape = new CircleShape();
        shape.setRadius(20);

        fdef.shape= shape;
        fdef.density = 1f;
        fdef.friction = 0.1f;


        b2body.createFixture(fdef);

        shape.dispose();

        return b2body;
    }
    public void update(float delT) {

        world.step(1/60f, 8, 3);
//        handleInput(delT);
//        camera.update();
//        renderer.setView(camera);
    }

    @Override
    public void show() {
        pause = new TextButton("Pause", butt_skin);

        pause.setSize(150, 75);
        pause.setPosition(50, 675);
        stage.addActor(pause);
        Gdx.input.setInputProcessor(stage);

        pause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.clear();

                fun();
            }
        });

        tank = defineTank();


        Gdx.input.setInputProcessor(new InputController() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.W:
                        movement.y = 1200;
                        break;

                    case Input.Keys.A:
                        movement.x = -10000000;
                        break;

                    case Input.Keys.S:
                        movement.y = -1200;
                        break;

                    case Input.Keys.D:
                        movement.x = 10000000;
                        break;
                }
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                switch (keycode) {
                    case Input.Keys.W:
                        movement.y = 0;
                        break;

                    case Input.Keys.A:
                        movement.x = 0;
                        break;

                    case Input.Keys.S:
                        movement.y = 0;
                        break;

                    case Input.Keys.D:
                        movement.x = 0;
                        break;
                }
                return true;
            }
        });

        butt_skin = new Skin(Gdx.files.internal("gdx-skins-master/freezing/skin/freezing-ui.json"));
        map = new TmxMapLoader().load("denmamap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        view = new FitViewport(1900, 1250, camera);
        camera.setToOrtho(false, 1900, 1250);



    }

    public void fun(){
//        stage add background and buttons
//                last jab resume button click sare elemnts remove kar diyo
//        Texture img = new Texture(Gdx.files.internal("pause_used.jpg"));
//        TextureRegion img_r = new TextureRegion(img, 619, 788);
//        Image img_i = new Image(img_r);
        TextButton resume;
        TextButton sound;
        final TextButton save;
        TextButton quit;

        resume = new TextButton("resume", butt_skin);
        sound = new TextButton("sound", butt_skin);
        save = new TextButton("save", butt_skin);
        quit = new TextButton("quit", butt_skin);

        resume.setSize(300, 100);
        sound.setSize(300, 100);
        save.setSize(300, 100);
        quit.setSize(300, 100);

        resume.setPosition(600, 650);
        save.setPosition(600, 500);
        sound.setPosition(600, 350);
        quit.setPosition(600, 200);

        stage.addActor(resume);
        stage.addActor(save);
        stage.addActor(sound);
        stage.addActor(quit);
//        stage.addActor(img_i);

        Gdx.input.setInputProcessor(stage);

        save.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Save(game));
            }
        });

        resume.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new play(game));
            }
        });

        quit.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MyHome(game));
            }
        });

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);


        tank.applyLinearImpulse(movement, new Vector2(tank.getPosition().x,tank.getPosition().y),true);
        tank.applyForceToCenter(movement, true);

        renderer.setView(camera);
        renderer.render();
        camera.update();

        b2br.render(world, camera.combined);
//        game.batch.begin();
//
//        world.getBodies(tempbodies);
//        for(Body b: tempbodies){
//            if(b.getUserData() != null && b.getUserData() instanceof Sprite){
//                Sprite sprite = (Sprite) b.getUserData();
//                sprite.setPosition(b.getPosition().x - sprite.getWidth()/2, b.getPosition().y - sprite.getHeight()/2);
//                sprite.setRotation(b.getAngle()* MathUtils.radiansToDegrees);
//                sprite.draw(game.batch);
//            }
//        }
//        game.batch.end();
//        Renderer.render();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

        camera.viewportWidth= width;
        camera.viewportHeight= height;
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
    }
}