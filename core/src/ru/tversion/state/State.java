package ru.tversion.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
// абстрактный класс State
// потомки: MenuState, PlayState, RestartState
public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;

// конструктор на входе экземпляр класса GameStateManager
    public State(GameStateManager gsm) {
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }
// обработка действий устройств ввода
    protected abstract void handleInput();
// обновление состояния
    public abstract void update(float dt);
// отрисовка экрана
    public abstract void render(SpriteBatch sb);
// очистка текстур
    public abstract void dispose();

}
