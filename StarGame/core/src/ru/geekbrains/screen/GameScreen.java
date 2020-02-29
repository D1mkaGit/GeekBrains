package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.Enemy0;
import ru.geekbrains.sprite.MainShip;
import ru.geekbrains.sprite.Star;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;

    private TextureAtlas atlas;
    private Music bgMusic;

    private Texture bg;
    private Background background;
    private Star[] stars;

    private MainShip mainShip;
    private Enemy0 enemy0;

    private BulletPool bulletPool;
    private BulletPool enemyBulletPool;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);
        atlas = new TextureAtlas(Gdx.files.internal("textures/mainAtlas.tpack"));
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        enemyBulletPool = new BulletPool();
        mainShip = new MainShip(atlas, bulletPool);
        enemy0 = new Enemy0(atlas, enemyBulletPool);
        bgMusic.setLooping(true);
        bgMusic.play();
    }

    @Override
    public void render(float delta) {
        update(delta);
        freeAllDestroyed();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        mainShip.resize(worldBounds);
        enemy0.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        enemyBulletPool.dispose();
        bgMusic.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        mainShip.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        mainShip.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        mainShip.update(delta);
        enemy0.update(delta);
        bulletPool.updateActiveSprites(delta);
        enemyBulletPool.updateActiveSprites(delta);
    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyedActiveObjects();
        enemyBulletPool.freeAllDestroyedActiveObjects();
    }

    private void draw() {
        Gdx.gl.glClearColor(0.5f, 0.9f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        mainShip.draw(batch);
        enemy0.draw(batch);
        bulletPool.drawActiveSprites(batch);
        enemyBulletPool.drawActiveSprites(batch);
        batch.end();
    }
}
