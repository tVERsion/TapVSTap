package ru.tversion.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;
    private Vector3 touchPos;
    private Circle circle;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        touchPos = new Vector3();
        Gdx.input.setCatchBackKey(true);
        background = new Texture("background.png");
        playBtn = new Texture("buttonStart.png");
        circle = new Circle(gsm.getWidth() / 2 - playBtn.getWidth() / 2, gsm.getHeight() / 2 - playBtn.getWidth() / 2, playBtn.getWidth() / 2);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), gsm.getHeight() - Gdx.input.getY(), 0);
            if (circle.contains(touchPos.x - playBtn.getWidth() / 2, touchPos.y - playBtn.getHeight() / 2)) {
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
        sb.draw(playBtn, (gsm.getWidth() - playBtn.getWidth()) / 2.0f, (gsm.getHeight() - playBtn.getHeight()) / 2.0f,
                playBtn.getWidth() / 2.0f, playBtn.getHeight() / 2.0f, playBtn.getWidth(), playBtn.getHeight(),
                1, 1, 180, 0, 0, playBtn.getWidth(), playBtn.getHeight(), false, false);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
