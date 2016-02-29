package ru.tversion.state;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayState extends State {

    private Texture timer;
    public PlayState(GameStateManager gsm) {
        super(gsm);

        timer = new Texture("timer.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        sb.draw(timer, (gsm.getWidth() / 2) - (timer.getWidth()/ 2), gsm.getHeight() / 2);
        sb.end();
    }

    @Override
    public void dispose() {
        timer.dispose();

    }
}
