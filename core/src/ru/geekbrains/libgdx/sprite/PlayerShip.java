package ru.geekbrains.libgdx.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.libgdx.base.Sprite;
import ru.geekbrains.libgdx.math.Rect;

public class PlayerShip extends Sprite {

    private Vector2 v;

    public PlayerShip(TextureAtlas atlas) {
        super(new TextureRegion(atlas.findRegion("main_ship"), 0, 0, atlas.findRegion("main_ship").getRegionWidth() / 2, atlas.findRegion("main_ship").getRegionHeight()));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.2f);
        setBottom(worldBounds.getBottom() + 0.02f);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
    }
}
