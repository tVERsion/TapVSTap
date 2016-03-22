package ru.tversion.logic2screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

import ru.tversion.figures.TapCircle;
import ru.tversion.state.GameStateManager;

public interface Circles {

    public void draw(SpriteBatch sb);
    public void spawn(Texture[][] textures, int[] marks, int diameter);
    public Circle getCircleWithRandomXY(int diameter);
    public boolean overlapsCircles(Circle circle);
    public void delete();
    public TapCircle getCircle(int i);
    public void processPress(GameStateManager gsm, float x, float y, Texture[][] textures, int[] marks, int diameter);
}
