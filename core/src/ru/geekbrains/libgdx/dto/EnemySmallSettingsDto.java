package ru.geekbrains.libgdx.dto;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.libgdx.base.EnemySettingsDto;
import ru.geekbrains.libgdx.utils.Regions;

public class EnemySmallSettingsDto extends EnemySettingsDto {

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final int ENEMY_SMALL_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_SMALL_HP = 1;

    public EnemySmallSettingsDto(TextureAtlas atlas, Sound bulletSound) {
        TextureRegion enemy0 = atlas.findRegion("enemy0");
        setRegions(Regions.split(enemy0, 1, 2, 2));
        setV0(new Vector2(0f, -0.2f));
        setBulletRegion(atlas.findRegion("bulletEnemy"));
        setBulletHeight(ENEMY_SMALL_BULLET_HEIGHT);
        setBulletV(new Vector2(0f, -0.3f));
        setBulletSound(bulletSound);
        setDamage(ENEMY_SMALL_DAMAGE);
        setReloadInterval(ENEMY_SMALL_RELOAD_INTERVAL);
        setHeight(ENEMY_SMALL_HEIGHT);
        setHp(ENEMY_SMALL_HP);
    }
}
