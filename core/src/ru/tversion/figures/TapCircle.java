package ru.tversion.figures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class TapCircle extends Circle {
    private int weight;
    private boolean sign;
    private Texture texture;

    public TapCircle(int weight, boolean sign) {
        super();
        if (!sign) {
            this.setWeight(-weight);
        } else {
            this.setWeight(weight);
        }
    }

    public TapCircle(Texture texture, Circle circle, int weight, boolean sign) {
        super(circle);
        if (!sign) {
            this.setWeight(-weight);
        } else {
            this.setWeight(weight);
        }
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

    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public TapCircle clone() {
        TapCircle cloneTapCircle = new TapCircle(this.weight, this.sign);
        cloneTapCircle.set(this.x, this.y, this.radius);
        cloneTapCircle.setTexture(this.getTexture());
        return cloneTapCircle;
    }
}
