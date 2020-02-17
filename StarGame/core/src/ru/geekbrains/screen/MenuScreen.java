package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Vector2 touch;
    private Vector2 v;
    private Vector2 pos;
    private float mX;
    private float mY;
    private float distance;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        touch = new Vector2();
        v = new Vector2(1f, 1f);
        pos = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.5f, 0.9f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if ((int) pos.x != (int) touch.x) {
            pos.add(v);
        }

        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        System.out.println("touch.x = " + touch.x + " touch.y " + touch.y);
        distance = pos.cpy().sub(touch).len();

        if (touch.x > pos.x) mX = touch.x - pos.x;
        else mX = -pos.x + touch.x;

        if (touch.y > pos.y) mY = touch.y - pos.y;
        else mY = -pos.y + touch.y;

        v = new Vector2(mX / distance, mY / distance);
        return false;
    }

}
