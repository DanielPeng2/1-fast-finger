package net.danielpeng.fastfinger;

public class Game {

    private int mScore;

    public Game() {
        mScore = 0;
    }

    public void increaseScore() {
        mScore ++;
    }

    public int getScore() {
        return mScore;
    }

}
