package com.tmags.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tmags.game.Scenes.Hud;
import com.tmags.game.TooMuchAGoodSpin;

public class PlayScreen implements Screen {
    private TooMuchAGoodSpin game;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;


    public PlayScreen(TooMuchAGoodSpin game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(TooMuchAGoodSpin.V_WIDTH, TooMuchAGoodSpin.V_HEIGHT, gamecam);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Balantines.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gameport.getWorldWidth()/2, gameport.getWorldHeight()/2,0);

        world = new World(new Vector2(0,0),true);
        b2dr = new Box2DDebugRenderer();


        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        for (MapObject object: map.getLayers().get(2).getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                bdef.type = BodyDef.BodyType.DynamicBody;
                bdef.position.set(rect.getX() + rect.getWidth() /2,rect.getY() + rect.getHeight()/2);

                body = world.createBody(bdef);
                shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
                fdef.shape = shape;
                body.createFixture(fdef);
            }

            if (object instanceof PolygonMapObject) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
                bdef.type = BodyDef.BodyType.DynamicBody;
                bdef.position.set(polygon.getX(),polygon.getY());
                body = world.createBody(bdef);
                float[] vertices = polygon.getVertices().clone();
                for (int i = 0; i < vertices.length; i++) vertices[i] += 1;
                shape.set(vertices);
                fdef.shape = shape;
                body.createFixture(fdef);
            }
        }
    }

    @Override
    public void show() {

    }

    public void update (float dt) {
        handleInput(dt);
        gamecam.update();
        hud.update(dt);
        gamecam.update();
        renderer.setView(gamecam);
    }

    private void handleInput(float dt) {
        if(Gdx.input.isTouched()) gamecam.position.x += 100*dt;
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cameraRotate(delta);
        renderer.render();
        b2dr.render(world,gamecam.combined);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    private void cameraRotate(float delta) {
        gamecam.rotate(1.0f * -delta);
        gamecam.update();
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
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
