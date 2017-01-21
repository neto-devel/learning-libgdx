package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DemoGame;
import com.mygdx.game.scenes.Hud;
import com.mygdx.game.sprites.Mario;

/**

 * Created by josevieira on 1/12/17.
 */
public class PlayScreen implements Screen {

    private DemoGame game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Mario mario;

    public PlayScreen(DemoGame game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(DemoGame.V_WIDTH / DemoGame.PPM , DemoGame.V_HEIGHT / DemoGame.PPM , gamecam);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / DemoGame.PPM);

        gamecam.position.set(gamePort.getWorldWidth() /2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -10 ), true);
        b2dr = new Box2DDebugRenderer();

        mario = new Mario(world);

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();

        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set( (rect.getX() + rect.getWidth() / 2) / DemoGame.PPM  , (rect.getY() + rect.getHeight() / 2) / DemoGame.PPM) ;
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / DemoGame.PPM , rect.getHeight() / 2 / DemoGame.PPM );
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set( (rect.getX() + rect.getWidth() / 2) / DemoGame.PPM  , (rect.getY() + rect.getHeight() / 2) / DemoGame.PPM) ;
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / DemoGame.PPM , rect.getHeight() / 2 / DemoGame.PPM );
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set( (rect.getX() + rect.getWidth() / 2) / DemoGame.PPM  , (rect.getY() + rect.getHeight() / 2) / DemoGame.PPM) ;
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / DemoGame.PPM , rect.getHeight() / 2 / DemoGame.PPM );
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set( (rect.getX() + rect.getWidth() / 2) / DemoGame.PPM  , (rect.getY() + rect.getHeight() / 2) / DemoGame.PPM) ;
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / DemoGame.PPM , rect.getHeight() / 2 / DemoGame.PPM );
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            mario.b2body.applyLinearImpulse(new Vector2(0, 4f), mario.b2body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mario.b2body.getLinearVelocity().x <= 2)
            mario.b2body.applyLinearImpulse(new Vector2(0.1f, 0), mario.b2body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && mario.b2body.getLinearVelocity().x >= -2)
            mario.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), mario.b2body.getWorldCenter(), true);

    }

    public void update(float dt) {
        handleInput(dt);

        world.step(1/60f, 6, 2);

        gamecam.position.x = mario.b2body.getPosition().x;

        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override

    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
