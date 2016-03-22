package ru.tversion.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class RestartState extends State {
    private Texture background;
    private Texture restartButton;

    public RestartState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.png");
        restartButton = new Texture("restartButton");



    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            return;
        }

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, gsm.getWidth(), gsm.getHeight());

        sb.end();
    }

    @Override
    public void dispose() {

    }
}
