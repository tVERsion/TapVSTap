package ru.tversion.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;
    private int height;
    private int width;
    private static int NUMBER_ROUND = 3;
    private int currentRound;


    public GameStateManager() {
        states = new Stack<State>();
    }

    public GameStateManager(int height, int width) {
        this.height = height;
        this.width = width;
        states = new Stack<State>();
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
        state.setRound(currentRound);
        currentRound++;
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
}
