/**
 * MainActivity class extends AppCompatActivity and creates the on create method, while also
 * setting listeners on the buttons.
 * @author Greg Tinkham
 * @version 1.4
 */
package com.example.gtink.javaquiz;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private TextView mQuestionTextView;
    private static final String KEY_INDEX = "index";
    public int score = 0;
    public static final String EXTRA_SCORE= "score";

    private com.example.gtink.javaquiz.QuestionBank mQuestionBank;
    public boolean MaxScore() {return score > 4;}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);

        mQuestionBank = new com.example.gtink.javaquiz.QuestionBank();
        if (savedInstanceState != null) {
            int mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mQuestionBank.setCurrentQuestionIndex(mCurrentIndex);
        }
        mQuestionBank.generateQuestion();
        mQuestionTextView.setText(mQuestionBank.questionTextID());

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestionBank.hasMoreQuestion()) {
                    mQuestionBank.generateQuestion();
                    mQuestionTextView.setText(mQuestionBank.questionTextID());
                    mFalseButton.setEnabled(true);
                    mTrueButton.setEnabled(true);
                } else {

                }
                if(MaxScore()){
                    Intent intent = new Intent(MainActivity.this, CongratsActivity.class);
                    intent.putExtra(EXTRA_SCORE, score);
                    startActivity(intent);
                }
            }
        });
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestionBank.hasLessQuestion())  {
                    mQuestionBank.generatePastQuestion();
                    mQuestionTextView.setText(mQuestionBank.questionTextID());
                } else {
                    mQuestionTextView.setText(R.string.question_text_view);
                    mFalseButton.setEnabled(false);
                    mTrueButton.setEnabled(false);
                    mPreviousButton.setEnabled(false);
                }
            }
        });
    }

    /**
     * These methods are to create logcat logs.
     */
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){

        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX,mQuestionBank.getCurrentQuestionIndex());
        Log.i(TAG, "onSaveInstanceState");

    }

    private void checkAnswer(boolean userPressed) {
        if (userPressed == mQuestionBank.questionAnswer()) {
            Toast.makeText(this, R.string.correct,Toast.LENGTH_SHORT).show();
            score = score +1;
            mFalseButton.setEnabled(false);
            mTrueButton.setEnabled(false);

        } else {
            Toast.makeText(this, R.string.incorrect,Toast.LENGTH_SHORT).show();
        }
    }
}