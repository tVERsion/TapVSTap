package ru.tversion;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import ru.tversion.state.GameStateManager;

public class Timer {
    private static long SECOND_LONG = 1000000000;
    private Array<Texture> seconds;
    private long timeStart;
    private int sec;

    public Timer(int sec) {
        this.sec = sec;
        timeStart = TimeUtils.nanoTime();
        seconds = new Array<Texture>();
        for (int i = sec; i >= 0; i--) {
            String nameTextures = i + ".png";
            seconds.add(new Texture(nameTextures));
        }
    }

    public void draw(SpriteBatch sb, GameStateManager gsm) {
        sb.draw(seconds.get((int) (TimeUtils.nanoTime() / SECOND_LONG - timeStart / SECOND_LONG)), (gsm.getWidth() / 2) - (seconds.get((int) (TimeUtils.nanoTime() / SECOND_LONG - timeStart / SECOND_LONG)).getWidth() / 2), gsm.getHeight() / 2 - (seconds.get((int) (TimeUtils.nanoTime() / SECOND_LONG - timeStart / SECOND_LONG)).getHeight() / 2));
    }
    public boolean isEnd() {
        return TimeUtils.nanoTime() / SECOND_LONG - timeStart / SECOND_LONG >= sec;
    }
    public void dispose() {
        for (int i = 0; i < seconds.size; i++) {
            seconds.get(i).dispose();
        }
    }
}
