package ru.tversion.logic2screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import ru.tversion.figures.TapCircle;
import ru.tversion.state.GameStateManager;
// реализация интерфейса Circles для нижней части экрана
public class BottomCircles implements Circles {
    private Array<TapCircle> circles;
    private GameStateManager gsm;
    private static int NUMBER_CIRCLES = 3;

    public BottomCircles(GameStateManager gsm) {
        circles = new Array<TapCircle>();
        this.gsm = gsm;
    }

    @Override
    public void draw(SpriteBatch sb) {
        for (int i = 0; i < circles.size; i++) {
            sb.draw(circles.get(i).getTexture(), circles.get(i).x, circles.get(i).y);
        }
    }

    @Override
    public void spawn(Texture[][] textures, int[] marks, int diameter) {
        Circle baseCircle = getCircleWithRandomXY(diameter);

        while (overlapsCircles(baseCircle)) {
            baseCircle = getCircleWithRandomXY(diameter);
        }
        int tmpRandomIndex = MathUtils.random(0, 1);
        circles.add(new TapCircle(textures[MathUtils.random(0, 3)][tmpRandomIndex], baseCircle, marks[tmpRandomIndex]));

        while (overlapsCircles(baseCircle)) {
            baseCircle = getCircleWithRandomXY(diameter);
        }
        tmpRandomIndex = MathUtils.random(2, 3);
        circles.add(new TapCircle(textures[MathUtils.random(0, 3)][tmpRandomIndex], baseCircle, marks[tmpRandomIndex]));

        while (overlapsCircles(baseCircle)) {
            baseCircle = getCircleWithRandomXY(diameter);
        }
        tmpRandomIndex = MathUtils.random(0, 3);
        circles.add(new TapCircle(textures[MathUtils.random(0, 3)][tmpRandomIndex], baseCircle, marks[tmpRandomIndex]));
    }

    @Override
    public Circle getCircleWithRandomXY(int diameter) {
        return new Circle(MathUtils.random(0, gsm.getWidth() - diameter), MathUtils.random(0, gsm.getHeight() / 2 - diameter - (int) diameter / 2), diameter / 2);
    }

    @Override
    public boolean overlapsCircles(Circle circle) {
        for (int i = 0; i < this.circles.size; i++) {
            if (circle.overlaps(circles.get(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete() {
        for (int i = 0; i < NUMBER_CIRCLES; i++) {
            this.circles.removeIndex(0);
        }
    }

    @Override
    public TapCircle getCircle(int i) {
        return this.circles.get(i);
    }

    @Override
    public void processPress(GameStateManager gsm, float x, float y, Texture[][] textures, int[] marks, int diameter) {
        for (int i = 0; i < NUMBER_CIRCLES; i++) {
            if (getCircle(i).contains(x - getCircle(i).getTexture().getWidth() / 2, y - getCircle(i).getTexture().getHeight() / 2)) {
                gsm.getScore().addMark(getCircle(i), false);
                delete();
                spawn(textures, marks, diameter);
                break;

            }
        }
    }

}
