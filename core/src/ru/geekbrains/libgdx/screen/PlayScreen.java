package ru.geekbrains.libgdx.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.libgdx.base.BaseScreen;
import ru.geekbrains.libgdx.controls.GoLeftButton;
import ru.geekbrains.libgdx.math.Rect;
import ru.geekbrains.libgdx.sprite.Background;
import ru.geekbrains.libgdx.sprite.PlayerShip;
import ru.geekbrains.libgdx.sprite.Star;

public class PlayScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;

    private TextureAtlas atlas;

    private Texture bg;
    private Background background;
    private Star[] stars;

    private PlayerShip playerShip;

    private GoLeftButton goLeftButton;

    @Override
    public void show() {
        super.show();
        atlas = new TextureAtlas("textures\\mainAtlas.tpack");

        bg = new Texture("textures\\bg.png");
        background = new Background(bg);
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas);
        }
        playerShip = new PlayerShip(atlas);
        goLeftButton = new GoLeftButton(atlas);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollision();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        playerShip.resize(worldBounds);
        goLeftButton.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        playerShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        playerShip.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        playerShip.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        playerShip.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        playerShip.update(delta);
    }

    private void checkCollision() {

    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        playerShip.draw(batch);
        goLeftButton.draw(batch);
        batch.end();
    }
}
