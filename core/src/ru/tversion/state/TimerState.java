package ru.tversion.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import ru.tversion.Timer;
import ru.tversion.design.Font;

public class TimerState extends State {
    private Texture background;
    private Timer timer;
    private Texture score;
    private BitmapFont fontBottom;
    private BitmapFont fontTop;

    private static int THREE_SECOND = 3;

    public TimerState(GameStateManager gsm) {
        super(gsm);
        timer = new Timer(THREE_SECOND);
        background = new Texture("background.png");
        score = new Texture("score.png");
        fontBottom = new Font("material.ttf", 35, Color.WHITE, false).getFont();
        fontTop = new Font("material.ttf", 35, Color.WHITE, true).getFont();
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

        sb.draw(score, 0, gsm.getHeight() / 2 - score.getHeight());
        sb.draw(score, gsm.getWidth() - score.getWidth(), gsm.getHeight() / 2);

        fontTop.draw(sb, ""+gsm.getScore().getTotalScoreTop(), gsm.getWidth() - 50, gsm.getHeight() / 2 + 10);
        fontBottom.draw(sb, ""+gsm.getScore().getTotalScoreBottom(), 50, gsm.getHeight() / 2 - 25);


        if (timer.isEnd()) {
            gsm.set(new PlayState(gsm, gsm.getCurrentRound()));
        }
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        timer.dispose();
    }
}
