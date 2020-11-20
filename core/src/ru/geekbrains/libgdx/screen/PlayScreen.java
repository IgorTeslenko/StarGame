package ru.geekbrains.libgdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import ru.geekbrains.libgdx.base.BaseScreen;
import ru.geekbrains.libgdx.math.Rect;
import ru.geekbrains.libgdx.pool.BulletPool;
import ru.geekbrains.libgdx.pool.EnemyShipPool;
import ru.geekbrains.libgdx.pool.ExplosionPool;
import ru.geekbrains.libgdx.sprite.Background;
import ru.geekbrains.libgdx.sprite.Bullet;
import ru.geekbrains.libgdx.sprite.ButtonNewGame;
import ru.geekbrains.libgdx.sprite.EnemyShip;
import ru.geekbrains.libgdx.sprite.MessageGameOver;
import ru.geekbrains.libgdx.sprite.PlayerShip;
import ru.geekbrains.libgdx.sprite.Star;
import ru.geekbrains.libgdx.utils.EnemyEmitter;

public class PlayScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;

    private enum State { PLAYING, GAME_OVER }

    private TextureAtlas atlas;
    private Texture bg;
    private Music music;
    private Sound enemyBulletSound;
    private Sound explosionSound;

    private Background background;
    private Star[] stars;

    private PlayerShip playerShip;
    private BulletPool bulletPool;
    private EnemyShipPool enemyShipPool;
    private ExplosionPool explosionPool;
    private EnemyEmitter enemyEmitter;
    private MessageGameOver messageGameOver;
    private ButtonNewGame buttonNewGame;

    private State state;


    @Override
    public void show() {
        super.show();
        atlas = new TextureAtlas("textures\\mainAtlas.tpack");
        bg = new Texture("textures\\bg.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds\\music.mp3"));
        enemyBulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds\\bullet.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds\\explosion.wav"));

        background = new Background(bg);
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(atlas, explosionSound);
        enemyShipPool = new EnemyShipPool(bulletPool, explosionPool, worldBounds);
        playerShip = new PlayerShip(atlas, explosionPool, bulletPool);
        enemyEmitter = new EnemyEmitter(worldBounds, enemyShipPool, enemyBulletSound, atlas);

        messageGameOver = new MessageGameOver(atlas);
        buttonNewGame = new ButtonNewGame(atlas, this);

        music.setLooping(true);
        music.play();

        state = State.PLAYING;
    }

    public void startNewGame() {
        state = State.PLAYING;

        playerShip.startNewGame(worldBounds);

        bulletPool.freeAllActiveObjects();
        enemyShipPool.freeAllActiveObjects();
        explosionPool.freeAllActiveObjects();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollision();
        freeAllDestroyed();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        playerShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        music.dispose();
        explosionPool.dispose();
        explosionSound.dispose();
        enemyBulletSound.dispose();
        playerShip.dispose();
        enemyShipPool.dispose();
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state == State.PLAYING) {
            playerShip.keyDown(keycode);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (state == State.PLAYING) {
            playerShip.keyUp(keycode);
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (state == State.PLAYING) {
            playerShip.touchDown(touch, pointer, button);
        } else if (state == State.GAME_OVER) {
            buttonNewGame.touchDown(touch, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (state == State.PLAYING) {
            playerShip.touchUp(touch, pointer, button);
        } else if (state == State.GAME_OVER) {
            buttonNewGame.touchUp(touch, pointer, button);
        }
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        explosionPool.updateActiveSprites(delta);
        if (state == State.PLAYING) {
            bulletPool.updateActiveSprites(delta);
            enemyShipPool.updateActiveSprites(delta);
            enemyEmitter.generate(delta);
            playerShip.update(delta);
        }
    }

    private void checkCollision() {
        if (state == State.GAME_OVER) {
            return;
        }
        List<EnemyShip> enemyShipList = enemyShipPool.getActiveObjects();
        for (EnemyShip enemyShip : enemyShipList) {
            if (enemyShip.isDestroyed()) {
                continue;
            }
            float minDist = playerShip.getHalfWidth() + enemyShip.getHalfWidth();
            if (enemyShip.pos.dst(playerShip.pos) < minDist) {
                enemyShip.destroy();
                playerShip.damage(enemyShip.getDamage());
                if (playerShip.isDestroyed()) {
                    state = State.GAME_OVER;
                }
                return;
            }
        }
        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for (Bullet bullet : bulletList) {
            if (bullet.isDestroyed()) {
                continue;
            }
            if (bullet.getOwner() != playerShip) {
                if (playerShip.isBulletCollision(bullet)) {
                    playerShip.damage(bullet.getDamage());
                    if (playerShip.isDestroyed()) {
                        state = State.GAME_OVER;
                    }
                    bullet.destroy();
                    return;
                }
                continue;
            }
            for (EnemyShip enemyShip : enemyShipList) {
                if (enemyShip.isBulletCollision(bullet)) {
                    enemyShip.damage(bullet.getDamage());
                    bullet.destroy();
                    return;
                }
            }
        }
    }

    private void freeAllDestroyed () {
        bulletPool.freeAllDestroyedActiveSprites();
        enemyShipPool.freeAllDestroyedActiveSprites();
        explosionPool.freeAllDestroyedActiveSprites();
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        explosionPool.drawActiveSprites(batch);
        if (state == State.PLAYING) {
            playerShip.draw(batch);
            enemyShipPool.drawActiveSprites(batch);
            bulletPool.drawActiveSprites(batch);
        } else {
            messageGameOver.draw(batch);
            buttonNewGame.draw(batch);
        }
        batch.end();
    }
}
