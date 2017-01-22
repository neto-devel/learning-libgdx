package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.DemoGame;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by josevieira on 1/15/17.
 */
public class Mario extends Sprite {

    public World world; //mundo
    public Body b2body; //objeto do mundo
    private TextureRegion marioStand;

    public Mario(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("little_mario"));
        this.world = world;
        defineMario();
        marioStand = new TextureRegion(getTexture(), 0, 0, 16, 16);
        setBounds(0 , 0 , 16 / DemoGame.PPM, 16 / DemoGame.PPM);
        setRegion(marioStand);
    }

    public void update() {
        setPosition(b2body.getPosition().x - getWidth() / 2 , b2body.getPosition().y - getHeight() / 2);
    }

    public void defineMario() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / DemoGame.PPM, 32 / DemoGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        b2body = world.createBody(bdef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / DemoGame.PPM);

        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef);
    }

}
