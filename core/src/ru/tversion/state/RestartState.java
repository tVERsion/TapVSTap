package ru.tversion.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

public class RestartState extends State {
    private Texture background;
    private Texture buttonRestart;
    private Texture youWin;
    private Texture youLose;
    private Vector3 touchPos;
    private Circle circle;

    public RestartState(GameStateManager gsm) {
        super(gsm);
        Gdx.input.setCatchBackKey(true);
        background = new Texture("background.png");
        buttonRestart = new Texture("buttonRestart.png");
        youWin = new Texture("You win.png");
        youLose = new Texture("You Lose.png");
        touchPos = new Vector3();
        circle = new Circle(gsm.getWidth()/2 - buttonRestart.getWidth()/2, gsm.getHeight()/2 - buttonRestart.getWidth()/2, buttonRestart.getWidth()/2);

    }
    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), gsm.getHeight() - Gdx.input.getY(), 0);
            if (circle.contains(touchPos.x - buttonRestart.getWidth()/2, touchPos.y - buttonRestart.getHeight() / 2)) {
                gsm.getScore().setToZero();
                gsm.set(new TimerState(gsm));
            }
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
        sb.draw(buttonRestart, (gsm.getWidth() / 2) - (buttonRestart.getWidth() / 2), gsm.getHeight() / 2 - (buttonRestart.getHeight() / 2));

        if (gsm.getScore().isWinTop()) {
            sb.draw(youWin, (gsm.getWidth() - youWin.getWidth()) / 2.0f, 3 * (gsm.getHeight() - youWin.getHeight()) / 4.0f,
                    youWin.getWidth() / 2.0f, youWin.getHeight() / 2.0f, youWin.getWidth(), youWin.getHeight(),
                    1, 1, 180, 0, 0, youWin.getWidth(), youWin.getHeight(), false, false);

            sb.draw(youLose, (gsm.getWidth() / 2) - (youLose.getWidth() / 2), gsm.getHeight() / 4 - (youLose.getHeight() / 2));
        } else {
            sb.draw(youLose, (gsm.getWidth() - youWin.getWidth()) / 2.0f, 3 * (gsm.getHeight() - youWin.getHeight()) / 4.0f,
                    youWin.getWidth() / 2.0f, youWin.getHeight() / 2.0f, youWin.getWidth(), youWin.getHeight(),
                    1, 1, 180, 0, 0, youWin.getWidth(), youWin.getHeight(), false, false);

            sb.draw(youWin, (gsm.getWidth() / 2) - (youLose.getWidth() / 2), gsm.getHeight() / 4 - (youLose.getHeight() / 2));
        }

        sb.end();
    }
    @Override
    public void dispose() {
        background.dispose();
        buttonRestart.dispose();
        youWin.dispose();
        youLose.dispose();
    }
}
