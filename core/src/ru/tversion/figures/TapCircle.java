package ru.tversion.figures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class TapCircle extends Circle {
    private int weight;
    private Texture texture;

    public TapCircle(int weight) {
        super();
        this.setWeight(-weight);
        this.setWeight(weight);
    }

    public TapCircle(Texture texture, Circle circle, int weight, boolean sign) {
        super(circle);
        this.setWeight(-weight);
        this.setWeight(weight);
        this.setTexture(texture);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isTouch(float x, float y) {
        return contains(x, y);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public TapCircle clone() {
        TapCircle cloneTapCircle = new TapCircle(this.weight);
        cloneTapCircle.set(this.x, this.y, this.radius);
        cloneTapCircle.setTexture(this.getTexture());
        return cloneTapCircle;
    }
}
