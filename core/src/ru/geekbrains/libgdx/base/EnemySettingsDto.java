package ru.geekbrains.libgdx.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class EnemySettingsDto {
    private TextureRegion[] regions;
    private Vector2 v0;
    private TextureRegion bulletRegion;
    private float bulletHeight;
    private Vector2 bulletV;
    private Sound bulletSound;
    private int damage;
    private float reloadInterval;
    private float height;
    private int hp;

    public TextureRegion[] getRegions() {
        return regions;
    }

    public void setRegions(TextureRegion[] regions) {
        this.regions = regions;
    }

    public Vector2 getV0() {
        return v0;
    }

    public void setV0(Vector2 v0) {
        this.v0 = v0;
    }

    public TextureRegion getBulletRegion() {
        return bulletRegion;
    }

    public void setBulletRegion(TextureRegion bulletRegion) {
        this.bulletRegion = bulletRegion;
    }

    public float getBulletHeight() {
        return bulletHeight;
    }

    public void setBulletHeight(float bulletHeight) {
        this.bulletHeight = bulletHeight;
    }

    public Vector2 getBulletV() {
        return bulletV;
    }

    public void setBulletV(Vector2 bulletV) {
        this.bulletV = bulletV;
    }

    public Sound getBulletSound() {
        return bulletSound;
    }

    public void setBulletSound(Sound bulletSound) {
        this.bulletSound = bulletSound;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public abstract void setDamageForLevel(int level);

    public float getReloadInterval() {
        return reloadInterval;
    }

    public void setReloadInterval(float reloadInterval) {
        this.reloadInterval = reloadInterval;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}

