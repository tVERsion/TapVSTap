package ru.tversion.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import ru.tversion.Timer;
import ru.tversion.design.Font;
import ru.tversion.logic2screen.BottomCircles;
import ru.tversion.logic2screen.Circles;
import ru.tversion.logic2screen.TopCircles;

public class PlayState extends State {

    private static int[] MARKS = {-5, -10, 5, 10};
    private Texture[][] textures;
    private Timer timer;

    private static int DIAMETER_CIRCLES = 150;
    private static int LENGTH_FIRST_ROUND = 5;
    private Texture timerTex;
    private SpriteBatch batch;
    private BitmapFont font;
    private Vector3 touchPosTop;
    private Vector3 touchPosBottom;
    private Circles circlesTop;
    private Circles circlesBottom;
    private Texture background;
    private int round;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        timer = new Timer(LENGTH_FIRST_ROUND);
        background = new Texture("background.png");
        textures = new Texture[][]{{new Texture("B-5.png"), new Texture("B-10.png"), new Texture("B+5.png"), new Texture("B+10.png")},
                                   {new Texture("G-5.png"), new Texture("G-10.png"), new Texture("G+5.png"), new Texture("G+10.png")},
                                   {new Texture("R-5.png"), new Texture("R-10.png"), new Texture("R+5.png"), new Texture("R+10.png")},
                                   {new Texture("Y-5.png"), new Texture("Y-10.png"), new Texture("Y+5.png"), new Texture("Y+10.png")}};
        timerTex = new Texture("timer.png");
        camera = new OrthographicCamera();

        batch = new SpriteBatch();
        touchPosTop = new Vector3();
        touchPosBottom = new Vector3();

        circlesTop = new TopCircles(gsm);
        circlesBottom = new BottomCircles(gsm);

        font = new Font("material.ttf", 40, Color.BLUE, false).getFont();

        //Gdx.input.setCatchMenuKey(true);
        Gdx.input.setCatchBackKey(false);

        circlesTop.spawn(textures, MARKS, DIAMETER_CIRCLES);
        circlesBottom.spawn(textures, MARKS, DIAMETER_CIRCLES);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            gsm.set(new MenuState(gsm));
            gsm.getScore().setToZero();
        }
        if (Gdx.input.isTouched(0)) {
            touchPosTop.set(Gdx.input.getX(0), gsm.getHeight() - Gdx.input.getY(0), 0);
            if (gsm.getHeight() - Gdx.input.getY(0) > gsm.getHeight() / 2) {
                circlesTop.processPress(gsm, touchPosTop.x, touchPosTop.y, textures, MARKS, DIAMETER_CIRCLES);
            } else {
                circlesBottom.processPress(gsm, touchPosTop.x, touchPosTop.y, textures, MARKS, DIAMETER_CIRCLES);
            }
        }
        if (Gdx.input.isTouched(1)) {
            touchPosBottom.set(Gdx.input.getX(1), gsm.getHeight() - Gdx.input.getY(1), 0);
            if (gsm.getHeight() - Gdx.input.getY(1) > gsm.getHeight() / 2) {
                circlesTop.processPress(gsm, touchPosBottom.x, touchPosBottom.y, textures, MARKS, DIAMETER_CIRCLES);
            } else {
                circlesBottom.processPress(gsm, touchPosBottom.x, touchPosBottom.y, textures, MARKS, DIAMETER_CIRCLES);
            }
        }
    }

    public void setRound(int round) {
        this.round = round;
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
        sb.draw(timerTex, (gsm.getWidth() / 2) - (timerTex.getWidth() / 2), gsm.getHeight() / 2 - (timerTex.getHeight() / 2));

        font.draw(sb, ""+gsm.getScore().getCurrentScoreTop(), 100, 100);
        font.draw(sb, ""+gsm.getScore().getCurrentScoreBottom(), 200, 100);

        circlesTop.draw(sb);
        circlesBottom.draw(sb);
        timer.draw(sb, gsm);
        if (timer.isEnd()) {

        }

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        for (int i = 0; i < textures.length; i++) {
            for (int j = 0; j < textures[0].length; j++) {
                textures[i][j].dispose();
            }
        }
        timer.dispose();
        batch.dispose();
    }
}
