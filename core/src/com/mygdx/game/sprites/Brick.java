package com.mygdx.game.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.DemoGame;

/**
 * Created by josevieira on 1/22/17.
 */
public class Brick extends InteractiveTileObject {
    public Brick(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set( (bounds.getX() + bounds.getWidth() / 2) / DemoGame.PPM  , (bounds.getY() + bounds.getHeight() / 2) / DemoGame.PPM) ;
        body = world.createBody(bdef);
        shape.setAsBox(bounds.getWidth() / 2 / DemoGame.PPM , bounds.getHeight() / 2 / DemoGame.PPM );
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }
}
