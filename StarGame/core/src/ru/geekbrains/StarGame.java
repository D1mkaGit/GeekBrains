package ru.geekbrains;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StarGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Texture background;
    //TextureRegion region;

    @Override
    public void create() {
        batch = new SpriteBatch();
        //img = new Texture("badlogic.jpg");
        //region = new TextureRegion(img, 20, 25, 150, 100);
        background = new Texture("star-lord-2048x2048.jpg");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.5f, 0.9f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, 650, 650);
        //batch.setColor(0.1f, 0.9f, 0.5f, 0.9f);
        //batch.draw(img, 0, 0);
        //batch.setColor(0.6f, 0.3f, 0.2f, 0.3f);
        //batch.draw(region, 200, 200);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
