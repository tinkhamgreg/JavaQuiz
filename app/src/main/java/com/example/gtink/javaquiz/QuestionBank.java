/**
 * QuestionBank class handles the creation and use of questions.
 * Is created separately to hide the "bank" of questions used.
 * @author Greg Tinkham
 * @version 1.4
 */
package com.example.gtink.javaquiz;


import java.util.ArrayList;
import java.util.List;

public class QuestionBank {
    private int num_question;
    private List<Question> mQuestionList;
    public int mCur = -1;
    public QuestionBank() {
        mQuestionList = new ArrayList<>();
        mQuestionList.add(new Question(R.string.question_variable, false));
        mQuestionList.add(new Question(R.string.question_size, true));
        mQuestionList.add(new Question(R.string.question_default, false));
        mQuestionList.add(new Question(R.string.question_class, true));
        mQuestionList.add(new Question(R.string.question_poly, false));

        num_question = mQuestionList.size();
    }

    public int getNum_question() {
        return num_question;
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