package ru.tversion;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.tversion.screens.GameEndScreen;
import ru.tversion.screens.MenuScreen;

public class TapVSTap extends Game {
	private int WIDTH_DISPLAY;
	private int HEIGHT_DISPLAY;
	private SpriteBatch batch;
	private BitmapFont font;

	public TapVSTap(int width, int height) {
		setWidthDisplay(width);
		setHeightDisplay(height);
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new GameEndScreen(this));
	}

	@Override
	public void render () {
		super.render();
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
