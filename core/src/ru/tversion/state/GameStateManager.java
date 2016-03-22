package ru.tversion.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

import ru.tversion.Score;

public class GameStateManager {
    private Stack<State> states;
    private int height;
    private int width;
    private static int NUMBER_ROUND = 3;
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
        currentRound = 0;
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
        currentRound++;
        if (currentRound > NUMBER_ROUND) {
            set(new MenuState(this));
        }
        state.setRound(currentRound);
        score.setToZeroCurrentScore();
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
}
