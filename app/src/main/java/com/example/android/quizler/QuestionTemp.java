package com.example.android.quizler;

public class QuestionTemp {
    private int questionId;
    private boolean answer;

    public QuestionTemp(int questionId, boolean answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
