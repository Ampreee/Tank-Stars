package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.datastorage;

import java.io.Serializable;


public class Play1 implements Screen{
    private TiledMap map;
    public Body proj;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private int i=0;
    Array<Body> tempbodies = new Array<Body>();

    InputMultiplexer inputMultiplexer = new InputMultiplexer();
    private TmxMapLoader loader;

    int flag = 0;
//    private TiledMap T_map;

//    private OrthogonalTiledMapRenderer Renderer;

    //2d box

    private World world;

    public Body b2body;

    public static  Body player;
    public static Body player1;

    public static Vector2 movement = new Vector2();
    private Box2DDebugRenderer  b2dr;


    FitViewport view;
    private Stage stage;
    private MyGdxGame game;

    private TextButton pause;
    Skin butt_skin;

    public Play1(MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();

//        gamePort = new FitViewport(384 * 5,216 *5,camera);
        loader = new TmxMapLoader();
        map = loader.load("denmamap.tmx");

        renderer = new OrthogonalTiledMapRenderer(map);
        view = new FitViewport(1400, 788,camera );
        stage = new Stage(view);
        camera.setToOrtho(false, 1400, 788);
//tank ki image
//        player= new Player(new Sprite(new Texture("withoutbacktanks.png")),(TiledMapTileLayer)map.getLayers().get(1));
//        setting position
//        player.setPosition(85*player.getCollisionLayer().getTileWidth(), 605* player.getCollisionLayer().getHeight());
        world = new World(new Vector2(0, -18), true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
//create body fixtures
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;

            bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fdef.shape = shape;
            fdef.friction=0.2f;
            body.createFixture(fdef);

        }
    }



    @Override
    public void show() {


        player  = definePlayer();
        flag = 1;
        player1 = definePlayer();
        flag = 2;
        proj = definePlayer();
//        player= cementary();
//        player = new Player(world);
//        player.definePlayer();

        butt_skin = new Skin(Gdx.files.internal("gdx-skins-master/freezing/skin/freezing-ui.json"));
        map = new TmxMapLoader().load("denmamap.tmx");
        renderer= new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        view = new FitViewport(1900, 1250, camera);
        camera.setToOrtho(false, 1900, 1250);

        pause = new TextButton("Pause", butt_skin);

        pause.setSize(150, 75);
        pause.setPosition(50, 675);
        stage.addActor(pause);

//        Gdx.input.setInputProcessor(stage);
        Gdx.input.setInputProcessor(stage);

        pause.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.clear();
                game.pause();
                fun();
            }
        });

//        Gdx.input.setInputProcessor(new InputController() {
//            @Override
//            public boolean keyDown(int keycode) {
//                switch (keycode) {
//                    case Input.Keys.W:
//                        movement.y = 1200;
//                        break;
//
//                    case Input.Keys.A:
//                        movement.x = -100000;
//                        break;
//
//                    case Input.Keys.S:
//                        movement.y = -1200;
//                        break;
//
//                    case Input.Keys.D:
//                      movement.x = 100000;
//                        break;
//                }
//                return true;
//            }
//
//            @Override
//            public boolean keyUp(int keycode) {
//                switch (keycode) {
//                    case Input.Keys.W:
//                        movement.y = 0;
//                        break;
//
//                    case Input.Keys.A:
//                        movement.x = 0;
//                        break;
//
//                    case Input.Keys.S:
//                        movement.y = 0;
//                        break;
//
//                    case Input.Keys.D:
//                      movement.x = 0;
//                        break;
//                }
//               return true;
//            }
//
//
//        });
        inputMultiplexer.addProcessor(stage);
    }
    public void handleInput(float dt){

        Gdx.input.setInputProcessor(inputMultiplexer);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && (player.getLinearVelocity().x <0.1)) {
            player1.applyLinearImpulse(new Vector2(-240000f, 0), player1.getWorldCenter(), true);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (player.getLinearVelocity().x <0.1)) {
            player1.applyLinearImpulse(new Vector2(240000f, 0), player1.getWorldCenter(), true);

        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) && (player1.getLinearVelocity().x < 0.1)){
            player.applyLinearImpulse(new Vector2(-240000f, 0), player1.getWorldCenter(), true);

        } else if (Gdx.input.isKeyPressed(Input.Keys.D) && (player1.getLinearVelocity().x <0.1) ) {
            player.applyLinearImpulse(new Vector2(240000f, 0), player1.getWorldCenter(), true);
        }
//        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
//            game.setScreen(new PauseMenuScreen(game,player1.gettankType(),player2.gettankType()));// your actions
//            dispose();
//        }
//        if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
//            if(flag){
//                weapon = new Weapon(world,player1.getX(),player1.getY());
//                flag=false;
//            }else {
//                world.destroyBody(weapon.b2body);
//                flag = true;
//            }
//        }
//        if(Gdx.input.isKeyPressed(Keys.W)){
//            player1.angle++;
//        }
//        if(Gdx.input.isKeyPressed(Keys.S)){
//            player1.angle--;
//        }
//        if(Gdx.input.isKeyPressed(Keys.A)){
//            player1.power-=100;
//        }
//        if(Gdx.input.isKeyPressed(Keys.D)){
//            player1.power+=100;
//        }
//        if(Gdx.input.isKeyPressed(Keys.SPACE)){
//            float vx=player1.angle;
//            float vy=player1.power;
//
//
////            float vx = (float) (30000f*player1.power*Math.cos(Math.max((Math.PI)/2,player1.angle)));
////            float vy = (float) (30000f*player1.power*Math.sin(Math.max((Math.PI)/2,player1.angle)));
//            System.out.println("vx->"+vx);
//            System.out.println(("vy")+vy);
//            weapon.b2body.setType(BodyDef.BodyType.DynamicBody);
//            weapon.b2body.applyLinearImpulse(new Vector2(vx, vy),new Vector2(17, 17),true);
//
//        }
    }


    //write a programme for factorial
    


    public void update(float dt){
        handleInput(dt);
        world.step(1/60f,8,3);


    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);

        player.applyForce(movement, new Vector2(player.getPosition().x,player.getPosition().y),true);

        player1.applyForce(movement, new Vector2(player.getPosition().x,player.getPosition().y),true);

        renderer.setView(camera);
        camera.update();

        renderer.render();
        b2dr.render(world,camera.combined);
        game.batch.begin();
        world.getBodies(tempbodies);
        for(Body b: tempbodies){
            if(b.getUserData() != null && b.getUserData() instanceof Sprite){
                Sprite sprite = (Sprite) b.getUserData();
                sprite.setPosition(b.getPosition().x - sprite.getWidth()/2, b.getPosition().y - sprite.getHeight()/2);
                sprite.setRotation(b.getAngle()* MathUtils.radiansToDegrees);
                sprite.draw(game.batch);
            }
        }
        game.batch.end();

        stage.act();
        stage.draw();


//        game.batch.draw(new Texture("untitled.png"), 0, 0, 1500, 750);

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth= width;
        camera.viewportHeight= height;
        camera.update();
//        gamePort.update(width,height);

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
//        player.getTexture().dispose();
    }


    public Body definePlayer(){
        BodyDef bdef =new BodyDef();
        if(flag == 0) {
            bdef.position.set(180,520);
        }
        if(flag == 1){
            bdef.position.set(1400,223);
        }
        if(flag == 2){
            bdef.position.set(1400,250);

        }

        bdef.fixedRotation= true;
        bdef.type= BodyDef.BodyType.DynamicBody;
        b2body= world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(15,15);


        CircleShape shape= new CircleShape();
        shape.setRadius(24);
        if(flag ==2) {
            shape.setRadius(14);
            fdef.density= 40;
        }
        else {

            fdef.density= 200;
        }

        fdef.shape= shape;

        fdef.friction= 0.3f;
        b2body.createFixture(fdef);
//        Sprite boxsprite
        if(flag ==0){
            Sprite boxsprite = new Sprite(new Texture("t3_tank.png"));
            boxsprite.setSize(100, 100);
            boxsprite.setOrigin(boxsprite.getHeight()/2, boxsprite.getWidth()/2);
            b2body.setUserData(boxsprite);
        }
        else if (flag ==1){
            Sprite boxsprite = new Sprite(new Texture("tan2_new.png"));
            boxsprite.setSize(100, 100);
            boxsprite.setOrigin(boxsprite.getHeight()/2, boxsprite.getWidth()/2);
            b2body.setUserData(boxsprite);
        }
//        else {
//            Sprite boxsprite = new Sprite();
//            boxsprite.setSize(100, 100);
//            boxsprite.setOrigin(boxsprite.getHeight()/2, boxsprite.getWidth()/2);
//            b2body.setUserData(boxsprite);
//        }


        shape.dispose();

        return b2body;
    }
    public void fun(){
        final datastorage ds =new datastorage();
        ds.tankposx1= Play1.player.getPosition().x;
        ds.tankposx2= Play1.player1.getPosition().x;
        ds.tankposy1= Play1.player.getPosition().y;
        ds.tankposy2= Play1.player1.getPosition().y;
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
        save.addListener(new ClickListener(){

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
        resume.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.resume();
                game.setScreen(new Play1(game));
                Play1.player.getPosition().x=ds.tankposx1;
                Play1.player1.getPosition().x=ds.tankposx2;
                Play1.player.getPosition().y= ds.tankposy1;
                Play1.player1.getPosition().y=ds.tankposy2;
            }

        });

        quit.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MyHome(game));
            }
        });



    }

}