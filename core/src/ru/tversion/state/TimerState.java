package ru.tversion.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import ru.tversion.design.Font;

public class TimerState extends State {
    private Texture timer;
    private Texture background;
    private long timeStart;

    public TimerState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.png");
        timer = new Texture("timer.png");
        timeStart = TimeUtils.nanoTime();

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            return;
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
        sb.draw(timer, (gsm.getWidth() / 2) - (timer.getWidth() / 2), gsm.getHeight() / 2 - (timer.getHeight() / 2));
        if (TimeUtils.nanoTime()/1000000000 - timeStart/1000000000 > 3) {
            gsm.set(new PlayState(gsm));
        }

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        timer.dispose();

    }

}
