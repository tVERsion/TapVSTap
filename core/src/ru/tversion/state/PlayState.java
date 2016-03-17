package ru.tversion.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javafx.scene.input.KeyCode;

public class PlayState extends State {

    private Texture timer;
    public PlayState(GameStateManager gsm) {
        super(gsm);

        timer = new Texture("timer.png");
        Gdx.input.setCatchBackKey(false);




    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isCatchBackKey()){
            dispose();
            gsm.set(new MenuState(gsm));
        }

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
