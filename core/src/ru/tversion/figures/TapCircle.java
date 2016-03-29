package ru.tversion.figures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
// наследник класса Circle имеет дополнительно текстуру и вес
public class TapCircle extends Circle {
    private int weight;
    private Texture texture;
// конструктор принимающий на вход вес
    public TapCircle(int weight) {
        super();
        this.setWeight(-weight);
        this.setWeight(weight);
    }
// конструктор принимающий на вход текстуру, базовый экземпляр класса Circle, вес
    public TapCircle(Texture texture, Circle circle, int weight) {
        super(circle);
        this.setWeight(-weight);
        this.setWeight(weight);
        this.setTexture(texture);
    }
// метод возвращает вес
    public int getWeight() {
        return weight;
    }
// метод принимает вес
    public void setWeight(int weight) {
        this.weight = weight;
    }
// метод возвращает текстуру
    public Texture getTexture() {
        return texture;
    }
// метод принимает текстуру
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
// метод возвращает клон текущего экземпляра
    public TapCircle clone() {
        TapCircle cloneTapCircle = new TapCircle(this.weight);
        cloneTapCircle.set(this.x, this.y, this.radius);
        cloneTapCircle.setTexture(this.getTexture());
        return cloneTapCircle;
    }
}
