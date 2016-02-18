package ru.tversion.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import ru.tversion.TapVSTap;

public class MenuScreen implements Screen {

    TapVSTap game;
    OrthographicCamera camera;
    public MenuScreen(TapVSTap game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getWidthDisplay(), game.getHeightDisplay());
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.getBatch().setProjectionMatrix(camera.combined);
        game.getFont().setColor(Color.BLACK);

        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "Start", 100, 100);
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
