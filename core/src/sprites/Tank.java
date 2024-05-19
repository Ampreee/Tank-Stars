//package sprites;
//
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.physics.box2d.*;
//
//import java.security.PublicKey;
//
//public class Tank extends Sprite {
//
//    public World world;
//    public static Body b2body;
//
//    public Tank(World world) {
//        this.world = world;
//        b2body = defineTank();
//
//    }
//
//    public Body defineTank(){
//        BodyDef bdef = new BodyDef();
//
//        bdef.type = BodyDef.BodyType.DynamicBody;
//
//        bdef.position.set(200, 400);
//        CircleShape shape = new CircleShape();
//        shape.setRadius(20);
//
//        FixtureDef fdef = new FixtureDef();
//
//        fdef.shape= shape;
//
//        b2body.createFixture(fdef);
//
//        return b2body;
//    }
//
//}
