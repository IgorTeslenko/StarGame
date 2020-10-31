package ru.geekbrains.libgdx.pool;

import ru.geekbrains.libgdx.base.SpritesPool;
import ru.geekbrains.libgdx.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
