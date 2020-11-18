package ru.geekbrains.libgdx.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.libgdx.base.BaseButton;
import ru.geekbrains.libgdx.screen.PlayScreen;

public class ButtonNewGame extends BaseButton {

    private static final float HEIGHT = 0.05f;
    private static final float TOP_MARGIN = -0.012f;

    private PlayScreen playScreen;

    public ButtonNewGame(TextureAtlas atlas, PlayScreen playScreen) {
        super(atlas.findRegion("button_new_game"));
        setHeightProportion(HEIGHT);
        setTop(TOP_MARGIN);
        this.playScreen = playScreen;
    }

    @Override
    public void action() {
        playScreen.startNewGame();
    }
}
