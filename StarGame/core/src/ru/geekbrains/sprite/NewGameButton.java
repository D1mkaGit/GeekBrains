package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.ScaledButton;
import ru.geekbrains.math.Rect;

public class NewGameButton extends ScaledButton {

    private boolean isPressed;

    public NewGameButton(TextureAtlas atlas) {
        super(atlas.findRegion("button_new_game"));
    }

    @Override
    public void action() {
        isPressed = true;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.06f);
        setTop(-0.05f);
    }

    public boolean isPressed() {
        return isPressed;
    }

    public boolean flushPressed() {
        return isPressed = false;
    }
}
