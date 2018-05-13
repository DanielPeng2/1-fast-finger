package net.danielpeng.fastfinger;

import android.graphics.Color;

import java.util.Random;

public class ColorPicker {

    private String[] mColors = {
            "#ffb1b1", //red
            "#ffd380", //orange
            "#77dd77", //green
            "#71b2fb"  //blue
    };

    public int getColor() {
        String color;
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mColors.length);
        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }

}
