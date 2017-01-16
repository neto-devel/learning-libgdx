package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.DemoGame;

/**
 * Created by josevieira on 1/15/17.
 */
public class Mario extends Sprite {

    public World world; //mundo
    public Body b2body; //objeto do mundo

    public Mario(World world){
        this.world = world;
        defineMario();
    }

    public void defineMario() {
        BodyDef bdef = new BodyDef(); //definições do objeto do mundo
        bdef.position.set(32 / DemoGame.PPM, 32 / DemoGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        b2body = world.createBody(bdef);

        FixtureDef fixtureDef = new FixtureDef(); //forma do objeto
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / DemoGame.PPM);

        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef);
    }

}
