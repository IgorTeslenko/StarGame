package ru.geekbrains.libgdx.pool;

import ru.geekbrains.libgdx.base.SpritesPool;
import ru.geekbrains.libgdx.math.Rect;
import ru.geekbrains.libgdx.sprite.EnemyShip;

public class EnemyShipPool extends SpritesPool<EnemyShip> {

    private final BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private final Rect worldBounds;

    public EnemyShipPool(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool, explosionPool, worldBounds);
    }
}
