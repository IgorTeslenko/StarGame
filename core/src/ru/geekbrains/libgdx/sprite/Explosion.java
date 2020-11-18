package ru.geekbrains.libgdx.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.libgdx.base.Sprite;

public class Explosion extends Sprite {

    private static final float ANIMATE_INTERVAL = 0.02f;

    private float animateTimer;

    private Sound explosionSound;

    public Explosion(TextureRegion region, int rows, int cols, int frames, Sound explosionSound) {
        super(region, rows, cols, frames);
        this.explosionSound = explosionSound;
    }

    public void set(float height, Vector2 pos) {
        setHeightProportion(height);
        this.pos.set(pos);
        this.explosionSound.play();
    }

    @Override
    public void update(float delta) {
        animateTimer += delta;
        if (animateTimer >= ANIMATE_INTERVAL) {
            animateTimer = 0f;
            if (++frame == regions.length) {
                destroy();
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        frame = 0;
    }
}
