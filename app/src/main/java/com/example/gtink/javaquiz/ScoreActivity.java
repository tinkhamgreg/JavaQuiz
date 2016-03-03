package com.example.gtink.javaquiz;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
/**
 * Created by gtink on 3/2/2016.
 */

public class ScoreActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        initScore();
        initCheat();
        initCongrats();
    }
    private void initScore() {
        int uscore = getIntent().getIntExtra(MainActivity.EXTRA_SCORE, 0);
        TextView view = (TextView) findViewById(R.id.score_textview);
        view.setText("Score: " + uscore);
    }
    private void initCheat() {
        int cheat = getIntent().getIntExtra(MainActivity.EXTRA_CHEAT, 0);
        TextView view = (TextView) findViewById(R.id.cheat_textview);
        view.setText("Questions Cheated:" + cheat);
    }
    private void initCongrats() {
        int uscore = getIntent().getIntExtra(MainActivity.EXTRA_SCORE, 0);
        if (uscore == 10) {
            TextView view = (TextView) findViewById(R.id.congrats_textview);
            view.setText("Congratulations, You Completed The Quiz!");
        }
    }
}
