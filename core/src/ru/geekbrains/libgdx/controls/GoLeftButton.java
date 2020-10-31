package ru.geekbrains.libgdx.controls;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.libgdx.base.BaseButton;
import ru.geekbrains.libgdx.math.Rect;

/**
 * Не уверен, что пошел правильным путем здесь. То есть задумка была сделать две прозрачные кнопки по полэкрана каждая, слева и справа, но вот как их с кораблем подружить - не придумал.
 * Оставляю для истории.
 */

public class GoLeftButton extends BaseButton {

    public GoLeftButton(TextureAtlas atlas) {
        super(atlas.findRegion("emptySprite"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(1);
        setWidth(worldBounds.getHalfWidth());
        setLeft(worldBounds.getLeft());
    }

    @Override
    public void action() {

    }

}
