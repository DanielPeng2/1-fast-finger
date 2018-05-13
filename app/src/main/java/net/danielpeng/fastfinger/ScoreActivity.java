package net.danielpeng.fastfinger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private int mScore;
    private TextView mModeText;
    private TextView mScoreText;
    private TextView mHighScoreText;
    private TextView mRecordText;
    private View mBackgroundColor;
    private int mGameLength;
    private int mHighScore;
    private int themeColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Intent intent = getIntent();
        mScore = intent.getIntExtra("score", 0);
        mGameLength = intent.getIntExtra("length", 10);
        themeColor = intent.getIntExtra("theme", Color.parseColor("#ffb1b1"));

        mModeText = (TextView) findViewById(R.id.modeTextView);
        mRecordText = (TextView) findViewById(R.id.recordScoreText);

        if (mGameLength == 5) {
            updateHighScore("easy");
            mModeText.setText("Easy");
        } else if (mGameLength == 10) {
            updateHighScore("normal");
            mModeText.setText("Normal");
        } else {
            updateHighScore("hard");
            mModeText.setText("Hard");
        }

        mScoreText = (TextView) findViewById(R.id.scoreText);
        mScoreText.setText(mScore + " taps");

        mHighScoreText = (TextView) findViewById(R.id.highScoreText);
        mHighScoreText.setText("High Score: " + mHighScore);

        mBackgroundColor = findViewById(R.id.backgroundColor2);
        updateThemeColor(themeColor);
    }

    //TODO: add 1 second delay
    public void scoreButtonPress(View view) {
        Button clickedButton = (Button) view;
        Intent intent = null;
        switch (clickedButton.getId()) {
            case R.id.restartButton:
                intent = new Intent(this, GameActivity.class);
                break;
            case R.id.mainMenuButton:
                intent = new Intent(this, MainActivity.class);
                break;
        }
        intent.putExtra("length", mGameLength);
        startActivity(intent);
    }

    public void updateHighScore(String gameMode) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("HIGH_SCORES", 0);
        mHighScore =  sharedPref.getInt(gameMode + "_saved_high_score", 0);

        if (mScore > mHighScore) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(gameMode + "_saved_high_score", mScore);
            editor.commit();
            mHighScore =  sharedPref.getInt(gameMode + "_saved_high_score", 0);

            mRecordText.setText("NEW HIGH SCORE!");
        } else {
            mRecordText.setText("");
        }
    }

    private void updateThemeColor(int color) {
        if (color == Color.parseColor("#ffb1b1")) {
            mBackgroundColor.setBackgroundResource(R.drawable.layout_bg_top_red);
        } else if (color == Color.parseColor("#ffd380")) {
            mBackgroundColor.setBackgroundResource(R.drawable.layout_bg_top_orange);
        } else if (color == Color.parseColor("#77dd77")) {
            mBackgroundColor.setBackgroundResource(R.drawable.layout_bg_top_green);
        } else {
            mBackgroundColor.setBackgroundResource(R.drawable.layout_bg_top_blue);
        }
    }
}
