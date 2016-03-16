package ru.tversion.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import ru.tversion.figures.TapCircle;

public class PlayState extends State {

    private Texture timer;
    private Texture blue;
    private Texture yellow;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Vector3 touchPos;
    private Array<TapCircle> circlesTop;
    private Array<TapCircle> circlesBottom;
    private int numberCircle;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        timer = new Texture("timer.png");
        blue = new Texture("blue.png");
        yellow = new Texture("yellow.png");
        camera = new OrthographicCamera();
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
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!" + touchPos.x + "      " + touchPos.y);
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@" + circlesTop.get(0).x + "   " + circlesTop.get(0).y);
            for (int i = 0; i < numberCircle; i++) {
                if (circlesTop.get(i).contains(touchPos.x, touchPos.y)) {
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
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();

        sb.draw(timer, (gsm.getWidth() / 2) - (timer.getWidth() / 2), gsm.getHeight() / 2 - (timer.getHeight() / 2));

            for (int i = 0; i < circlesTop.size; i++) {
                sb.draw(blue, circlesTop.get(i).x, circlesTop.get(i).y);
            }


        sb.end();
    }

    @Override
    public void dispose() {
        yellow.dispose();
        blue.dispose();
        timer.dispose();
        batch.dispose();
    }
}
