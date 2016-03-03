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

import java.util.Collections;


public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private Button mStatusButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private TextView mQuestionTextView;
    private static final String KEY_INDEX = "index";
    private int uscore = 0;
    private int cheat = 0;
    public static final String EXTRA_SCORE= "score";
    public static final String EXTRA_CHEAT= "cheat";
    private boolean mIsCheater;

    private com.example.gtink.javaquiz.QuestionBank mQuestionBank;


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
        mStatusButton = (Button) findViewById(R.id.status_button);

        mQuestionBank = new com.example.gtink.javaquiz.QuestionBank();
        mQuestionBank.score();
        mQuestionBank.cheat();
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
                mIsCheater = false;
                if (mQuestionBank.hasMoreQuestion()) {
                    mQuestionBank.generateQuestion();
                    mQuestionTextView.setText(mQuestionBank.questionTextID());
                    mFalseButton.setEnabled(true);
                    mTrueButton.setEnabled(true);
                }
                else {
                    int uscore = Collections.frequency(mQuestionBank.mScore.values(), 1);
                    int cheat = Collections.frequency(mQuestionBank.mCheat.values(), 1);
                    Intent e = new Intent(MainActivity.this, ScoreActivity.class);
                    e.putExtra(EXTRA_SCORE, uscore);
                    e.putExtra(EXTRA_CHEAT, cheat);
                    startActivity(e);
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
        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent j = new Intent(MainActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuestionBank.questionAnswer();
                j.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                startActivityForResult(j, 0);
            }
        });

        mStatusButton = (Button)findViewById(R.id.status_button);

        mStatusButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int uscore = Collections.frequency(mQuestionBank.mScore.values(), 1);
                int cheat = Collections.frequency(mQuestionBank.mCheat.values(), 1);
                Intent e = new Intent(MainActivity.this, ScoreActivity.class);
                e.putExtra(EXTRA_SCORE, uscore);
                e.putExtra(EXTRA_CHEAT, cheat);
                startActivity(e);
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
        if (mIsCheater) {
            Toast.makeText(this, R.string.judgment_toast, Toast.LENGTH_SHORT).show();
            mQuestionBank.checkCheat();
        }

        else {
            if(userPressed == mQuestionBank.questionAnswer()) {
                Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
                mQuestionBank.setScoretrue();
            }
            else {
                    Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show();
                    mQuestionBank.setScorefalse();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }
}