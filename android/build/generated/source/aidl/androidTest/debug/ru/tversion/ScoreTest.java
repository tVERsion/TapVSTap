package ru.tversion;

import junit.framework.TestCase;

import ru.tversion.figures.TapCircle;

public class ScoreTest extends TestCase {
     public void testScoreAddMark() {
         Score score = new Score();
         score.addMark(new TapCircle(15), true);

         assertEquals(score.getCurrentScoreTop(), 15);
     }

    public void testScoreIsHeadHeat() {
        Score score = new Score();
        score.addMark(new TapCircle(15), true);
        score.addMark(new TapCircle(15), false);

        assertTrue(score.isHeadHeat());
    }

    public void testScoreIsNotHeadHeat() {
        Score score = new Score();
        score.addMark(new TapCircle(15), true);
        score.addMark(new TapCircle(15), false);
        score.addMark(new TapCircle(15), false);

        assertFalse(score.isHeadHeat());
    }

    public void testScoreIsWinTop() {
        Score score = new Score();
        score.addMark(new TapCircle(15), true);
        score.setToZeroCurrentScore();
        score.addMark(new TapCircle(15), true);
        score.setToZeroCurrentScore();

        assertTrue(score.isWinTop());
    }

    public void testScoreIsWinBottom() {
        Score score = new Score();
        score.addMark(new TapCircle(15), true);
        score.setToZeroCurrentScore();
        score.addMark(new TapCircle(15), false);
        score.setToZeroCurrentScore();
        score.addMark(new TapCircle(15), false);
        score.setToZeroCurrentScore();

        assertFalse(score.isWinTop());
    }
}