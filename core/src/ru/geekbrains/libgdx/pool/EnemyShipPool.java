package ru.geekbrains.libgdx.pool;

import ru.geekbrains.libgdx.base.SpritesPool;
import ru.geekbrains.libgdx.math.Rect;
import ru.geekbrains.libgdx.sprite.EnemyShip;

public class EnemyShipPool extends SpritesPool<EnemyShip> {

    private final BulletPool bulletPool;
    private final Rect worldBounds;

    public EnemyShipPool(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool, worldBounds);
    }
}
