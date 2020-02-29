package ru.geekbrains.sprite;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class BattleShip extends Sprite {

    private final Vector2 touch;
    private final Vector2 v;
    private final Vector2 buf;
    private static final float V_LEN = 0.01f;
    private static final float PADDING = 0.005f;
    private final TextureRegion region;
    private final float scale = 0.35f;
    private int button;

    public BattleShip(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"));
        TextureRegion textureRegion = atlas.findRegion("main_ship");
        region = new TextureRegion(textureRegion, 0, 0, textureRegion.getRegionWidth() / 2, textureRegion.getRegionHeight());
        touch = new Vector2();
        v = new Vector2();
        buf = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(scale);
        setLeft(worldBounds.getLeft() - PADDING);
        setBottom(worldBounds.getBottom() + PADDING);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(
                region,
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                getWidth() / 2, getHeight(),
                scale, scale,
                angle
        );
    }

    @Override
    public void touchDown(Vector2 touch, int pointer, int button) {
        if (button == 22)
            touch.x = 0.5f;
        if (button == 21)
            touch.x = -0.5f;
        touch = touch.cpy().set(touch.x, pos.y);
        this.touch.set(touch);
        v.set(touch.sub(pos)).setLength(V_LEN);
        this.button = button;
    }

    @Override
    public void update(float delta) {
        buf.set(touch);
        if (pos.x < buf.x && buf.x > 0 || button == 22) {
            if (pos.x < scale)
                pos.add(v);
        }

        if (pos.x > buf.x && buf.x < 0 || button == 21) {
            if (pos.x > -scale + 0.1f - PADDING - V_LEN)
                pos.add(v);
        }
    }
}
