/**
 * QuestionBank class handles the creation and use of questions.
 * Is created separately to hide the "bank" of questions used.
 * @author Greg Tinkham
 * @version 1.4
 */
package com.example.gtink.javaquiz;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;


public class QuestionBank {
    private List<Question> mQuestionList;
    private Integer mCur = -1;
    public HashMap<Integer, Integer> mScore;
    public HashMap<Integer, Integer> mCheat;

    public QuestionBank() {
        mQuestionList = new ArrayList<>();
        mQuestionList.add(new Question(R.string.question_variable, false));
        mQuestionList.add(new Question(R.string.question_size, true));
        mQuestionList.add(new Question(R.string.question_default, false));
        mQuestionList.add(new Question(R.string.question_class, true));
        mQuestionList.add(new Question(R.string.question_poly, false));
        mQuestionList.add(new Question(R.string.question_def, true));
        mQuestionList.add(new Question(R.string.question_mem, true));
        mQuestionList.add(new Question(R.string.question_dec, true));
        mQuestionList.add(new Question(R.string.question_stat, false));
        mQuestionList.add(new Question(R.string.question_arr, false));


    }

    public int  getCurrentQuestionIndex(){
        return mCur;
    }
    public void setCurrentQuestionIndex(int new_index){
        mCur = new_index -1;
    }
    public boolean hasMoreQuestion(){
        return mCur < mQuestionList.size()-1;
    }

    public boolean hasLessQuestion(){
        return mCur < mQuestionList.size()+1;
    }


    public void generateQuestion(){
        if (hasMoreQuestion())
            mCur = mCur +1;
    }

    public void generatePastQuestion(){
        if (hasLessQuestion() && mCur > 0)
            mCur = mCur -1;
    }

    public int questionTextID() {
        return mQuestionList.get(mCur).getTextResID();
    }
    public boolean questionAnswer() {
        return mQuestionList.get(mCur).isAnswer();
    }


    public  void score() {
        mScore = new HashMap<>();
        mScore.put(0, 0);
        mScore.put(1, 0);
        mScore.put(2, 0);
        mScore.put(3, 0);
        mScore.put(4, 0);
        mScore.put(5, 0);
        mScore.put(6, 0);
        mScore.put(7, 0);
        mScore.put(8, 0);
        mScore.put(9, 0);
    }

    public void setScoretrue() {
        mScore.put(mCur,1);
    }
    public void setScorefalse() {
        mScore.put(mCur,0);
    }
    public int getScoreLength(){
        return mScore.size();
    }

    public  void cheat() {
        mCheat = new HashMap<>();
        mCheat.put(0, 0);
        mCheat.put(1, 0);
        mCheat.put(2, 0);
        mCheat.put(3, 0);
        mCheat.put(4, 0);
        mCheat.put(5, 0);
        mCheat.put(6, 0);
        mCheat.put(7, 0);
        mCheat.put(8, 0);
        mCheat.put(9, 0);
    }

    private void setCheattrue() {
        mCheat.put(mCur,1);
    }
    private void setCheatfalse() {
        mCheat.put(mCur,0);
    }
    public void checkCheat() {
        int check = mScore.get(mCur);
        if (check == 0) {
            setCheattrue();
        }
       if (check ==1){
           setCheatfalse();
       }
    }
}


class Question {
    private int mTextResID;
    private boolean mAnswer;

    public Question(int testResId, boolean answer) {
        mTextResID = testResId;
        mAnswer = answer;
    }

    public int getTextResID() {
        return mTextResID;
    }

    public void setTextResID(int textResID) {
        mTextResID = textResID;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}