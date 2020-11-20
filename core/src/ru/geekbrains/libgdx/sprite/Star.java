package ru.geekbrains.libgdx.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.libgdx.base.Sprite;
import ru.geekbrains.libgdx.math.Rect;
import ru.geekbrains.libgdx.math.Rnd;

public class Star  extends Sprite {

    protected final Vector2 v;
    private Rect worldBounds;

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        setHeightProportion(Rnd.nextFloat(0.005f, 0.015f));
        this.v = new Vector2(Rnd.nextFloat(-0.005f, 0.005f), getHeight() * -15);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float x = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float y = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(x, y);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        checkBounds();
    }

    protected void checkBounds() {
        if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }
        if (getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
        if (getTop() < worldBounds.getBottom()) {
            setBottom(worldBounds.getTop());
        }
        if (getBottom() > worldBounds.getTop()) {
            setTop(worldBounds.getBottom());
        }
    }
}
