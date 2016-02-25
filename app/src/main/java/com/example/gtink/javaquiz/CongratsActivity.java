package com.example.gtink.javaquiz;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
/**
 * Created by gtink on 2/24/2016.
 */
public class CongratsActivity extends AppCompatActivity{
    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);
        initScore();
    }
    private void initScore() {
        int score = getIntent().getIntExtra(MainActivity.EXTRA_SCORE, 0);
        TextView view = (TextView) findViewById(R.id.score_textview);
        view.setText("Score: " + score);
    }
}