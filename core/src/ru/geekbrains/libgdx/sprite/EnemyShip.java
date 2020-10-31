package ru.geekbrains.libgdx.sprite;

import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.libgdx.base.Ship;
import ru.geekbrains.libgdx.base.EnemySettingsDto;
import ru.geekbrains.libgdx.math.Rect;
import ru.geekbrains.libgdx.pool.BulletPool;

public class EnemyShip extends Ship {

    private static final Vector2 V_INIT = new Vector2(0, -0.5F);
    private static final float MARGIN = 0.025f;

    private EnemySettingsDto settings;

    public EnemyShip(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        bulletPos.set(pos.x, getBottom());
        super.update(delta);
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
            reloadTimer = 0;
        }
        if (getTop() < worldBounds.getTop() - MARGIN) {
            this.v.set(settings.getV0());
            this.bulletV.set(settings.getBulletV());
        }
    }

    public void set(EnemySettingsDto settings) {
        this.settings = settings;
        this.regions = settings.getRegions();
        this.v.set(V_INIT);
        this.bulletRegion = settings.getBulletRegion();
        this.bulletHeight = settings.getBulletHeight();
        this.bulletV.set(V_INIT);
        this.bulletSound = settings.getBulletSound();
        this.damage = settings.getDamage();
        this.reloadInterval = settings.getReloadInterval();
        this.setHeightProportion(settings.getHeight());
        this.hp = settings.getHp();
    }
}
