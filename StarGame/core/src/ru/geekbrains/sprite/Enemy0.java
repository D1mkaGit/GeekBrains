package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.BulletPool;

public class Enemy0 extends Sprite {

    private final Vector2 v;

    private Rect worldBounds;

    private final BulletPool bulletPool;
    private final TextureRegion bulletRegion;
    private final Vector2 bulletV;
    private final Vector2 bulletPos;

    private float animateTimer;
    private float animateInterval = 2f;

    public Enemy0(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("enemy0"), 1, 2, 2);
        v = new Vector2();
        v.set(0, Rnd.nextFloat(-0.15f, -0.01f));
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("bulletEnemy");
        this.bulletV = new Vector2(0, -0.5f);
        this.bulletPos = new Vector2();
        animateTimer = Rnd.nextFloat(0, 1f);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posX = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        pos.set(posX, worldBounds.getTop() + 0.07f);
        setHeightProportion(0.15f);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (getTop() < worldBounds.getBottom()) {
            destroy();
        }
        animateTimer += delta;
        if (animateTimer >= animateInterval) {
            animateTimer = 0;
            shoot();
        }
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bulletPos.set(pos.x, getBottom());
        bullet.set(this, bulletRegion, bulletPos, bulletV, 0.01f, worldBounds, 1);
    }
}
