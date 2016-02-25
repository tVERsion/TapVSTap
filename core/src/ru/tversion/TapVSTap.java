package ru.tversion;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.tversion.design.Font;
import ru.tversion.screens.GameEndScreen;
import ru.tversion.screens.MenuScreen;
import ru.tversion.state.GameStateManager;

public class TapVSTap extends Game {
	private int WIDTH_DISPLAY;
	private int HEIGHT_DISPLAY;
	private GameStateManager gsm;
	private SpriteBatch batch;
	private BitmapFont font;

	public TapVSTap(int width, int height) {
		setWidthDisplay(width);
		setHeightDisplay(height);
	}




	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new Font("material.ttf", 48, Color.CYAN).getFont();
		gsm = new GameStateManager();
		this.setScreen(new GameEndScreen(this));
		Gdx.gl.glClearColor(1, 0, 0, 1);
	}


	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose() {
		super.dispose();
		getBatch().dispose();
		getFont().dispose();
	}

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
