package ru.geekbrains.libgdx.utils;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.libgdx.dto.EnemyBigSettingsDto;
import ru.geekbrains.libgdx.dto.EnemyMediumSettingsDto;
import ru.geekbrains.libgdx.base.EnemySettingsDto;
import ru.geekbrains.libgdx.dto.EnemySmallSettingsDto;
import ru.geekbrains.libgdx.math.Rect;
import ru.geekbrains.libgdx.math.Rnd;
import ru.geekbrains.libgdx.pool.EnemyShipPool;
import ru.geekbrains.libgdx.sprite.EnemyShip;

public class EnemyEmitter {

    private static final float GENERATE_INTERVAL = 4f;

    private final Rect worldBounds;
    private final EnemyShipPool enemyShipPool;
    private float generateTimer;

    private final EnemySettingsDto enemySmallSettingsDto;
    private final EnemyMediumSettingsDto enemyMediumSettingsDto;
    private final EnemyBigSettingsDto enemyBigSettingsDto;

    public EnemyEmitter(Rect worldBounds, EnemyShipPool enemyShipPool, Sound bulletSound, TextureAtlas atlas) {
        this.worldBounds = worldBounds;
        this.enemyShipPool = enemyShipPool;
        enemySmallSettingsDto = new EnemySmallSettingsDto(atlas, bulletSound);
        enemyMediumSettingsDto = new EnemyMediumSettingsDto(atlas, bulletSound);
        enemyBigSettingsDto = new EnemyBigSettingsDto(atlas, bulletSound);
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= GENERATE_INTERVAL) {
            generateTimer = 0;
            EnemyShip enemyShip = enemyShipPool.obtain();
            float type = (float) Math.random();
//            System.out.println(type);
            if (type < 0.5f) {
                enemyShip.set(enemySmallSettingsDto);
            } else if (type < 0.8f) {
                enemyShip.set(enemyMediumSettingsDto);
            } else {
                enemyShip.set(enemyBigSettingsDto);
            }
            enemyShip.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemyShip.getHalfWidth(), worldBounds.getRight() - enemyShip.getHalfWidth());
            enemyShip.setBottom(worldBounds.getTop());
        }
    }
}
