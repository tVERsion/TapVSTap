package ru.tversion;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.tversion.design.Font;
import ru.tversion.state.GameStateManager;
import ru.tversion.state.MenuState;
import ru.tversion.state.RestartState;
// главный класс игры
public class TapVSTap extends Game {
    private int WIDTH_DISPLAY;
    private int HEIGHT_DISPLAY;
    private GameStateManager gsm;
    private SpriteBatch batch;
    private BitmapFont font;

// кончтруктор на входе ширина и высота экрана устройства
    public TapVSTap(int width, int height) {
        setWidthDisplay(width);
        setHeightDisplay(height);
        System.out.println(width+ "      "+height+"      ");
    }
    @Override
    //создание необходимых элементов
    public void create () {
        batch = new SpriteBatch();
        font = new Font("material.ttf", 48, Color.CYAN, false).getFont();
        gsm = new GameStateManager(getHeightDisplay(), getWidthDisplay());
        Gdx.gl.glClearColor(1, 0, 0, 1);
        gsm.push(new MenuState(gsm));

    }
    @Override
    // отрисовка
    public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
    }
    @Override
    // очистка
    public void dispose() {
        super.dispose();
        getBatch().dispose();
        getFont().dispose();
    }
    // далее возвращение и получение полей класса
    public int getWidthDisplay() {
        return WIDTH_DISPLAY;
    }

    public void setWidthDisplay(int widthDisplay) {
        WIDTH_DISPLAY = widthDisplay;
    }

    public int getHeightDisplay() {
        return HEIGHT_DISPLAY;
    }

    public void setHeightDisplay(int heightDisplay) {
        HEIGHT_DISPLAY = heightDisplay;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }
}

