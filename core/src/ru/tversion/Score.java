package ru.tversion;

import ru.tversion.figures.TapCircle;

public class Score {
    private int currentScoreTop;
    private int currentScoreBottom;
    private int totalScoreTop;
    private int totalScoreBottom;

    public Score() {
        this.currentScoreTop = 0;
        this.currentScoreBottom = 0;
        this.totalScoreTop = 0;
        this.totalScoreBottom = 0;
    }

    public void addMark(TapCircle circle, boolean party) {
        if (party) {
            currentScoreTop = getCurrentScoreTop() + circle.getWeight();
        } else {
            currentScoreBottom = getCurrentScoreBottom() + circle.getWeight();
        }
    }

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

    public void setToZero() {
        this.currentScoreTop = 0;
        this.currentScoreBottom = 0;
        this.totalScoreTop = 0;
        this.totalScoreBottom = 0;
    }

    public boolean isHeadHeat() {
        return currentScoreBottom == currentScoreTop;
    }

    public int getCurrentScoreTop() {
        return currentScoreTop;
    }

    public int getCurrentScoreBottom() {
        return currentScoreBottom;
    }

    public int getTotalScoreTop() {
        return totalScoreTop;
    }

    public int getTotalScoreBottom() {
        return totalScoreBottom;
    }

    public boolean isWinTop() {
        return totalScoreTop > totalScoreBottom;
    }
}
