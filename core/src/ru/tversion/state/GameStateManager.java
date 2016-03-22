package ru.tversion.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.Stack;

import ru.tversion.Score;

public class GameStateManager {
    private Stack<State> states;
    private int height;
    private int width;
    public static int NUMBER_ROUND = 3;
    private int currentRound;
    private Score score;


    public GameStateManager() {
        states = new Stack<State>();
    }

    public GameStateManager(int height, int width) {
        this.height = height;
        this.width = width;
        states = new Stack<State>();
        score = new Score();
        currentRound = 1;
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop().dispose();
    }

    public void set(State state) {
        states.pop().dispose();
        states.push(state);
    }

    public void set(PlayState state) {
        states.pop().dispose();
        states.push(state);


    }

    public void set(TimerState state) {
        if (!score.isHeadHeat()) {
            currentRound = getCurrentRound() + 1;
        }
        score.setToZeroCurrentScore();
        if (getCurrentRound() > NUMBER_ROUND || Math.abs(score.getTotalScoreBottom() - score.getTotalScoreTop()) > 1) {
            states.push(new RestartState(this));
            currentRound = 1;
            return;
        }

        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Score getScore() {
        return score;
    }

    public int getCurrentRound() {
        return currentRound;
    }
}
