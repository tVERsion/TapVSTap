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

    private Texture timer;
    private Texture blue;
    private Texture yellow;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Vector3 touchPos;
    private Array<TapCircle> circlesTop;
    private Array<TapCircle> circlesBottom;
    private int numberCircle;
    private Texture background;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.png");
        timer = new Texture("timer.png");
        blue = new Texture("blue.png");
        yellow = new Texture("yellow.png");
        camera = new OrthographicCamera();
        font = new Font("material.ttf", 48, Color.BLUE).getFont();
        batch = new SpriteBatch();
        touchPos = new Vector3();
        circlesTop = new Array<TapCircle>();
        circlesBottom = new Array<TapCircle>();
        numberCircle = 0;
        Gdx.input.setCatchMenuKey(true);

        spawnCircle();
    }

    private void spawnCircle() {
        numberCircle = MathUtils.random(2, 3);
        Circle baseCircle = getCircleWithRandomXY(yellow.getWidth());

        for (int i = 0; i < numberCircle; i++) {
            while (overlapsCircles(baseCircle)) {
                baseCircle = getCircleWithRandomXY(yellow.getWidth());
            }
            circlesTop.add(new TapCircle(blue, baseCircle, 10, false));
        }
    }

    private Circle getCircleWithRandomXY(int diameter) {
        return new Circle(MathUtils.random(0, gsm.getWidth() - diameter), MathUtils.random(((gsm.getHeight() - diameter) / 2 + diameter), gsm.getHeight() - diameter), diameter / 2);
    }

    private boolean overlapsCircles(Circle circle) {
        for (int i = 0; i < circlesTop.size; i++) {
            if(circle.overlaps(circlesTop.get(i))) {
                return true;
            }
        }
        return false;
    }

    private void deleteCircles() {
        for(int i = 0; i < numberCircle; i++) {
            circlesTop.removeIndex(0);
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), gsm.getHeight() - Gdx.input.getY(), 0);
            for (int i = 0; i < numberCircle; i++) {
                if (circlesTop.get(i).contains(touchPos.x - circlesTop.get(i).getTexture().getWidth() / 2, touchPos.y - circlesTop.get(i).getTexture().getHeight() / 2)) {
                    deleteCircles();
                    spawnCircle();
                    break;
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

            for (int i = 0; i < circlesTop.size; i++) {
                sb.draw(circlesTop.get(i).getTexture(), circlesTop.get(i).x, circlesTop.get(i).y);
            }
        font.draw(sb, "Серега", 100, 100);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        yellow.dispose();
        blue.dispose();
        timer.dispose();
        batch.dispose();
    }
}
