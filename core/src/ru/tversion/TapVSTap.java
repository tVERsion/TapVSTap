package ru.tversion;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TapVSTap extends ApplicationAdapter {
	private static int WIDTH_DISPLAY;
	private static int HEIGHT_DISPLAY;
	SpriteBatch batch;

	public TapVSTap(int width, int height) {
		WIDTH_DISPLAY = width;
		HEIGHT_DISPLAY = height;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		batch.end();
	}
}
