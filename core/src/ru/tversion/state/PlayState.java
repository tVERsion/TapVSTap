package ru.tversion.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import ru.tversion.design.Font;
import ru.tversion.figures.TapCircle;

public class PlayState extends State {

    private static int NUMBER_CIRCLES = 3;
    private static int[] MARKS = {-10, -5, 5, 10};
    private static Texture[][] TEXTURES_CIRCLES = {{new Texture("B-5.png"), new Texture("B-10.png"), new Texture("B+5.png"), new Texture("B+10.png")},
            {new Texture("G-5.png"), new Texture("G-10.png"), new Texture("G+5.png"), new Texture("G+10.png")},
            {new Texture("R-5.png"), new Texture("R-10.png"), new Texture("R+5.png"), new Texture("R+10.png")},
            {new Texture("Y-5.png"), new Texture("Y-10.png"), new Texture("Y+5.png"), new Texture("Y+10.png")}};

    private static int DIAMETER_CIRCLES = 150;
    private Texture timer;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Vector3 touchPosTop;
    private Vector3 touchPosBottom;
    private Array<TapCircle> circlesTop;
    private Array<TapCircle> circlesBottom;
    private Texture background;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.png");
        timer = new Texture("timer.png");
        camera = new OrthographicCamera();
        font = new Font("material.ttf", 48, Color.BLUE, false).getFont();
        batch = new SpriteBatch();
        touchPosTop = new Vector3();
        touchPosBottom = new Vector3();

        circlesTop = new Array<TapCircle>();
        circlesBottom = new Array<TapCircle>();
        Gdx.input.setCatchMenuKey(true);

        spawnTopCircle();
        spawnBottomCircle();
    }

    private void spawnTopCircle() {
        Circle baseCircle = getTopCircleWithRandomXY(DIAMETER_CIRCLES);

        while (overlapsCircles(baseCircle)) {
            baseCircle = getTopCircleWithRandomXY(DIAMETER_CIRCLES);
        }
        int tmpRandomIndex = MathUtils.random(0, 1);
        circlesTop.add(new TapCircle(TEXTURES_CIRCLES[MathUtils.random(0, 3)][tmpRandomIndex], baseCircle, MARKS[tmpRandomIndex]));

        while (overlapsCircles(baseCircle)) {
            baseCircle = getTopCircleWithRandomXY(DIAMETER_CIRCLES);
        }
        tmpRandomIndex = MathUtils.random(2, 3);
        circlesTop.add(new TapCircle(TEXTURES_CIRCLES[MathUtils.random(0, 3)][tmpRandomIndex], baseCircle, MARKS[tmpRandomIndex]));

        while (overlapsCircles(baseCircle)) {
            baseCircle = getTopCircleWithRandomXY(DIAMETER_CIRCLES);
        }
        tmpRandomIndex = MathUtils.random(0, 3);
        circlesTop.add(new TapCircle(TEXTURES_CIRCLES[MathUtils.random(0, 3)][tmpRandomIndex], baseCircle, MARKS[tmpRandomIndex]));
    }

    private void spawnBottomCircle() {
        Circle baseCircle = getBottomCircleWithRandomXY(DIAMETER_CIRCLES);

        while (overlapsCircles(baseCircle)) {
            baseCircle = getBottomCircleWithRandomXY(DIAMETER_CIRCLES);
        }
        int tmpRandomIndex = MathUtils.random(0, 1);
        circlesBottom.add(new TapCircle(TEXTURES_CIRCLES[MathUtils.random(0, 3)][tmpRandomIndex], baseCircle, MARKS[tmpRandomIndex]));

        while (overlapsCircles(baseCircle)) {
            baseCircle = getBottomCircleWithRandomXY(DIAMETER_CIRCLES);
        }
        tmpRandomIndex = MathUtils.random(2, 3);
        circlesBottom.add(new TapCircle(TEXTURES_CIRCLES[MathUtils.random(0, 3)][tmpRandomIndex], baseCircle, MARKS[tmpRandomIndex]));

        while (overlapsCircles(baseCircle)) {
            baseCircle = getBottomCircleWithRandomXY(DIAMETER_CIRCLES);
        }
        tmpRandomIndex = MathUtils.random(0, 3);
        circlesBottom.add(new TapCircle(TEXTURES_CIRCLES[MathUtils.random(0, 3)][tmpRandomIndex], baseCircle, MARKS[tmpRandomIndex]));
    }

    private Circle getTopCircleWithRandomXY(int diameter) {
        return new Circle(MathUtils.random(0, gsm.getWidth() - diameter), MathUtils.random(((gsm.getHeight() - diameter) / 2 + diameter), gsm.getHeight() - diameter), diameter / 2);
    }

    private Circle getBottomCircleWithRandomXY(int diameter) {
        return new Circle(MathUtils.random(0, gsm.getWidth() - diameter), MathUtils.random(0, gsm.getHeight() / 2 - diameter - (int) diameter / 2), diameter / 2);
    }

    private boolean overlapsCircles(Circle circle) {
        for (int i = 0; i < circlesTop.size; i++) {
            if (circle.overlaps(circlesTop.get(i))) {
                return true;
            }
        }
        for (int i = 0; i < circlesBottom.size; i++) {
            if (circle.overlaps(circlesBottom.get(i))) {
                return true;
            }
        }
        return false;
    }

    private void deleteTopCircles() {
        for (int i = 0; i < NUMBER_CIRCLES; i++) {
            circlesTop.removeIndex(0);
        }
    }

    private void deleteBottomCircles() {
        for (int i = 0; i < NUMBER_CIRCLES; i++) {
            circlesBottom.removeIndex(0);
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched(0)) {

            touchPosTop.set(Gdx.input.getX(0), gsm.getHeight() - Gdx.input.getY(0), 0);
            if (gsm.getHeight() - Gdx.input.getY(0) > gsm.getHeight() / 2) {
                for (int i = 0; i < NUMBER_CIRCLES; i++) {
                    if (circlesTop.get(i).contains(touchPosTop.x - circlesTop.get(i).getTexture().getWidth() / 2, touchPosTop.y - circlesTop.get(i).getTexture().getHeight() / 2)) {
                        deleteTopCircles();
                        spawnTopCircle();
                        break;
                    }
                }
            } else {
                for (int i = 0; i < NUMBER_CIRCLES; i++) {
                    if (circlesBottom.get(i).contains(touchPosTop.x - circlesBottom.get(i).getTexture().getWidth() / 2, touchPosTop.y - circlesBottom.get(i).getTexture().getHeight() / 2)) {
                        deleteBottomCircles();
                        spawnBottomCircle();
                        break;

                    }
                }
            }

        }

        if (Gdx.input.isTouched(1)) {

            touchPosBottom.set(Gdx.input.getX(1), gsm.getHeight() - Gdx.input.getY(1), 0);
            if (gsm.getHeight() - Gdx.input.getY(1) > gsm.getHeight() / 2) {
                for (int i = 0; i < NUMBER_CIRCLES; i++) {
                    if (circlesTop.get(i).contains(touchPosBottom.x - circlesTop.get(i).getTexture().getWidth() / 2, touchPosBottom.y - circlesTop.get(i).getTexture().getHeight() / 2)) {
                        deleteTopCircles();
                        spawnTopCircle();
                        break;
                    }
                }
            } else {
                for (int i = 0; i < NUMBER_CIRCLES; i++) {
                    if (circlesBottom.get(i).contains(touchPosBottom.x - circlesBottom.get(i).getTexture().getWidth() / 2, touchPosBottom.y - circlesBottom.get(i).getTexture().getHeight() / 2)) {
                        deleteBottomCircles();
                        spawnBottomCircle();
                        break;
                    }
                }
            }

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();

        sb.draw(background, 0, 0, gsm.getWidth(), gsm.getHeight());
        sb.draw(timer, (gsm.getWidth() / 2) - (timer.getWidth() / 2), gsm.getHeight() / 2 - (timer.getHeight() / 2));

        for (int i = 0; i < circlesBottom.size; i++) {
            sb.draw(circlesBottom.get(i).getTexture(), circlesBottom.get(i).x, circlesBottom.get(i).y);
        }

        for (int i = 0; i < circlesTop.size; i++) {
            sb.draw(circlesTop.get(i).getTexture(),
                    circlesTop.get(i).x, //(gsm.getWidth() - circlesBottom.get(i).getTexture().getWidth()) / 2.0f,
                    circlesTop.get(i).y, //(gsm.getHeight() - circlesBottom.get(i).getTexture().getHeight()) / 2.0f,
                    circlesTop.get(i).getTexture().getWidth() / 2.0f,
                    circlesTop.get(i).getTexture().getHeight() / 2.0f,
                    circlesTop.get(i).getTexture().getWidth(),
                    circlesTop.get(i).getTexture().getHeight(),
                    1,
                    1,
                    180,
                    0,
                    0,
                    circlesTop.get(i).getTexture().getHeight(),
                    circlesTop.get(i).getTexture().getHeight(),
                    false,
                    false);
        }

        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        for (int i = 0; i < TEXTURES_CIRCLES.length; i++) {
            for (int j = 0; j < TEXTURES_CIRCLES[0].length; j++) {
                TEXTURES_CIRCLES[i][j].dispose();
            }
        }
        timer.dispose();
        batch.dispose();
    }
}
