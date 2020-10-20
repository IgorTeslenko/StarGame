package ru.geekbrains.libgdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.libgdx.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private static final float V_LEN = 0.05f;

    private Texture img;
    private Vector2 pos;
    private Vector2 touch;
    private Vector2 v;
    private Vector2 tmp;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        pos = new Vector2();
        touch = new Vector2();
        v = new Vector2();
        tmp = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();

        tmp.set(touch);

        if (tmp.sub(pos).len() <= v.len()) {
            pos.set(touch);
        } else {
            pos.add(v);
        }
    }

    @Override
    public void dispose() {
        img.dispose();
        batch.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        v = touch.cpy().sub(pos).scl(V_LEN);
        return false;
    }
}
