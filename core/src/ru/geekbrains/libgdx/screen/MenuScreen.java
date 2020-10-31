package ru.geekbrains.libgdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.libgdx.base.BaseScreen;
import ru.geekbrains.libgdx.math.Rect;
import ru.geekbrains.libgdx.sprite.Background;
import ru.geekbrains.libgdx.sprite.Ship;

public class MenuScreen extends BaseScreen {

    private static final float V_LEN = 0.05f;

    private Texture bg;
    private Texture shipTexture;
    private Ship ship;
    private Background background;
    private Vector2 touch;
    private Vector2 velocity;


    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        shipTexture = new Texture("textures/ship.png");
        background = new Background(bg);
        ship = new Ship(shipTexture);
        touch = new Vector2();
        velocity = new Vector2();
        ship.pos.set(new Vector2(0.0f, -0.4f));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        ship.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        shipTexture.dispose();
        batch.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        ship.touchDown(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        ship.update(delta);
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        ship.draw(batch);
        batch.end();
    }
}
