package ru.tversion.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class TimerState extends State {
    private Texture background;
    private long timeStart;
    private static long SECOND_LONG = 1000000000;
    private Array<Texture> seconds;
    private static int THREE_SECOND = 3;

    public TimerState(GameStateManager gsm) {
        super(gsm);
        seconds = new Array<Texture>();
        for (int i = THREE_SECOND; i >= 0; i--) {
            String nameTextures = i + ".png";
            seconds.add(new Texture(nameTextures));
        }
        background = new Texture("background.png");
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
        sb.draw(seconds.get((int) (TimeUtils.nanoTime() / SECOND_LONG - timeStart / SECOND_LONG)), (gsm.getWidth() / 2) - (seconds.get((int) (TimeUtils.nanoTime() / SECOND_LONG - timeStart / SECOND_LONG)).getWidth() / 2), gsm.getHeight() / 2 - (seconds.get((int) (TimeUtils.nanoTime() / SECOND_LONG - timeStart / SECOND_LONG)).getHeight() / 2));
        if (TimeUtils.nanoTime() / SECOND_LONG - timeStart / SECOND_LONG >= THREE_SECOND) {
            gsm.set(new PlayState(gsm));
        }
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        for (int i = 0; i < seconds.size; i++) {
            seconds.get(i).dispose();
        }

    }
}
