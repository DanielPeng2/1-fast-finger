package net.danielpeng.fastfinger;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private Button mClickButton;
    private Game mGame;
    private CountDownTimer mTimer;
    private TextView mTimerText;
    private TextView mDifficultyTextView;
    private int mGameLength;
    private int themeColor;
    private Boolean mGameRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        mGameLength = intent.getIntExtra("length", 10);
        themeColor = intent.getIntExtra("theme", Color.parseColor("#ffb1b1"));

        mGame = new Game();

        mClickButton = (Button) findViewById(R.id.clickButton);
        setThemeColor(themeColor);

        mDifficultyTextView = (TextView) findViewById(R.id.difficultyTextView);
        setDifficultyText();

        mTimerText = (TextView) findViewById(R.id.timerText);
        mTimerText.setText(mGameLength + "");

        mClickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGame.increaseScore();
                mClickButton.setText(Integer.toString(mGame.getScore()));
                if (!mGameRunning) {
                    startTimer();
                }
            }
        });

        mTimer = new CountDownTimer(mGameLength * 1000, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerText.setText(Long.toString(millisUntilFinished / 1000 + 1));
            }

            @Override
            public void onFinish() {
                endGame();
            }
        };
    }

    private void startTimer() {
        mTimer.start();
        mGameRunning = true;
    }

    private void cancelTimer() {
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    private void endGame() {
        cancelTimer();
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra("score", mGame.getScore());
        intent.putExtra("length", mGameLength);
        intent.putExtra("theme", themeColor);
        startActivity(intent);
        finish();
    }

    private void setThemeColor(int color) {
        mClickButton.setBackgroundColor(color);
    }

    private void setDifficultyText() {
        if (mGameLength == 5) {
            mDifficultyTextView.setText("Easy");
        } else if (mGameLength == 10) {
            mDifficultyTextView.setText("Normal");
        } else {
            mDifficultyTextView.setText("Hard");
        }
    }
}
