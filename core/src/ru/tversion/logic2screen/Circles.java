package ru.tversion.logic2screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

import ru.tversion.figures.TapCircle;
import ru.tversion.state.GameStateManager;
// интерфейс описывающий действия, которые необходимы для реализации логики с
// двумя разноориентированными экранами
public interface Circles {
// отрисовка кружочков
    public void draw(SpriteBatch sb);
// добавление кружочков
    public void spawn(Texture[][] textures, int[] marks, int diameter);
// возвращает случайно сгенерированный экземпляр класса Circle, на входе диаметр
    public Circle getCircleWithRandomXY(int diameter);
// проверяет пересекаются ли кружочки
    public boolean overlapsCircles(Circle circle);
// удаление кружочков
    public void delete();
// возвращает кружочек по индексу
    public TapCircle getCircle(int i);
// обрабатывает нажатие на экран
    public void processPress(GameStateManager gsm, float x, float y, Texture[][] textures, int[] marks, int diameter);
}
