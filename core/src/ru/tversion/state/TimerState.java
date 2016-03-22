package ru.tversion.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import ru.tversion.Timer;

public class TimerState extends State {
    private Texture background;
    private Timer timer;

    private static int THREE_SECOND = 3;

    public TimerState(GameStateManager gsm) {
        super(gsm);
        timer = new Timer(THREE_SECOND);
        background = new Texture("background.png");
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
        timer.draw(sb, gsm);
        if (timer.isEnd()) {
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
