package ru.geekbrains.libgdx;

import com.badlogic.gdx.Game;

import ru.geekbrains.libgdx.screen.MenuScreen;

public class StarGame extends Game {

    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}