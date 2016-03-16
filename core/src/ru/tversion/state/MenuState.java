package ru.tversion.state;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.tversion.TapVSTap;

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.jpg");
        playBtn = new Texture("buttonStart.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, gsm.getWidth(), gsm.getHeight());
        sb.draw(playBtn,
                (gsm.getWidth() - playBtn.getWidth()) / 2.0f,
                (gsm.getHeight() - playBtn.getHeight()) / 2.0f,
                playBtn.getWidth() / 2.0f,
                playBtn.getHeight() / 2.0f,
                playBtn.getWidth(),
                playBtn.getHeight(),
                1,
                1,
                180,
                0,
                0,
                playBtn.getHeight(),
                playBtn.getHeight(),
                false,
                false);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
