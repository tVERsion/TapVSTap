package ru.tversion;

import ru.tversion.figures.TapCircle;
// класс Score обрабатывает подсчет очков
public class Score {
    private int currentScoreTop;
    private int currentScoreBottom;
    private int totalScoreTop;
    private int totalScoreBottom;
// конструктор по умолчанию
    public Score() {
        this.currentScoreTop = 0;
        this.currentScoreBottom = 0;
        this.totalScoreTop = 0;
        this.totalScoreBottom = 0;
    }
// добавить счет
    public void addMark(TapCircle circle, boolean party) {
        if (party) {
            currentScoreTop = getCurrentScoreTop() + circle.getWeight();
        } else {
            currentScoreBottom = getCurrentScoreBottom() + circle.getWeight();
        }
    }
// обнулить текущий счет
    public void setToZeroCurrentScore() {
        if (getCurrentScoreBottom() > getCurrentScoreTop()) {
            totalScoreBottom = getTotalScoreBottom() + 1;
        }
        if (getCurrentScoreBottom() < getCurrentScoreTop()) {
            totalScoreTop = getTotalScoreTop() + 1;
        }
        currentScoreTop = 0;
        currentScoreBottom = 0;
    }
// обнулить общий счет
    public void setToZero() {
        this.currentScoreTop = 0;
        this.currentScoreBottom = 0;
        this.totalScoreTop = 0;
        this.totalScoreBottom = 0;
    }
// проверка на ничью
    public boolean isHeadHeat() {
        return currentScoreBottom == currentScoreTop;
    }
// вернуть текущий счет
    public int getCurrentScoreTop() {
        return currentScoreTop;
    }

    public int getCurrentScoreBottom() {
        return currentScoreBottom;
    }
// вернуть общий счет
    public int getTotalScoreTop() {
        return totalScoreTop;
    }

    public int getTotalScoreBottom() {
        return totalScoreBottom;
    }
// проверить выиграл ли игрок на верхней части экрана
    public boolean isWinTop() {
        return totalScoreTop > totalScoreBottom;
    }
}
