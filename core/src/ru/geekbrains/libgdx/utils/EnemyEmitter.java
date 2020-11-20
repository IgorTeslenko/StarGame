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
    private final EnemySettingsDto enemyMediumSettingsDto;
    private final EnemySettingsDto enemyBigSettingsDto;

    private int level = 1;

    public EnemyEmitter(Rect worldBounds, EnemyShipPool enemyShipPool, Sound bulletSound, TextureAtlas atlas) {
        this.worldBounds = worldBounds;
        this.enemyShipPool = enemyShipPool;
        enemySmallSettingsDto = new EnemySmallSettingsDto(atlas, bulletSound);
        enemyMediumSettingsDto = new EnemyMediumSettingsDto(atlas, bulletSound);
        enemyBigSettingsDto = new EnemyBigSettingsDto(atlas, bulletSound);
    }

    public int getLevel() {
        return level;
    }

    public void generate(float delta, int frags) {
        level = frags / 10 + 1;
        generateTimer += delta;
        if (generateTimer >= GENERATE_INTERVAL - (float)level * 0.5f) {
            generateTimer = 0;
            EnemyShip enemyShip = enemyShipPool.obtain();
            float type = (float) Math.random();
            if (type < 0.5f) {
                enemySmallSettingsDto.setDamageForLevel(level);
                enemyShip.set(enemySmallSettingsDto);
            } else if (type < 0.8f) {
                enemyMediumSettingsDto.setDamageForLevel(level);
                enemyShip.set(enemyMediumSettingsDto);
            } else {
                enemyBigSettingsDto.setDamageForLevel(level);
                enemyShip.set(enemyBigSettingsDto);
            }
            enemyShip.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemyShip.getHalfWidth(), worldBounds.getRight() - enemyShip.getHalfWidth());
            enemyShip.setBottom(worldBounds.getTop());
        }
    }
}
