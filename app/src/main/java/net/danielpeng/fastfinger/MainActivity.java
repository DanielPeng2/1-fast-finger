package net.danielpeng.fastfinger;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //TODO: Google Play Games integration
    //TODO: Add sounds
    //TODO: Add high scores page
    private View mBackgroundColor;
    private ColorPicker mColorPicker = new ColorPicker();
    private int themeColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        themeColor = mColorPicker.getColor();

        mBackgroundColor = findViewById(R.id.backgroundColor);

        updateThemeColor(themeColor);
    }

    private void startGame(int gameLength) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("length", gameLength);
        intent.putExtra("theme", themeColor);
        startActivity(intent);
    }

    public void mainButtonPress(View view) {
        Button clickedButton = (Button) view;
        switch (clickedButton.getId()) {
            case R.id.easyButton:
                startGame(5);
                break;
            case R.id.normalButton:
                startGame(10);
                break;
            case R.id.hardButton:
                startGame(20);
                break;
        }
    }

    private void updateThemeColor(int color) {
        if (color == Color.parseColor("#ffb1b1")) {
            mBackgroundColor.setBackgroundResource(R.drawable.layout_bg_bottom_red);
        } else if (color == Color.parseColor("#ffd380")) {
            mBackgroundColor.setBackgroundResource(R.drawable.layout_bg_bottom_orange);
        } else if (color == Color.parseColor("#77dd77")) {
            mBackgroundColor.setBackgroundResource(R.drawable.layout_bg_bottom_green);
        } else {
            mBackgroundColor.setBackgroundResource(R.drawable.layout_bg_bottom_blue);
        }
    }
}
