package com.example.android.quizler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // TODO: Declare constants here

    Button true_button, false_button,instructionButton,testButton,exitButton,instPlayButton,instExitButton;
    TextView questionView;
    int questionIndex;
    int question;
    int score;
    ProgressBar progressBar;
    TextView viewScore;

    // TODO: Declare member variables here:


    // TODO: Uncomment to create question bank
    private QuestionTemp[] questionBank = new QuestionTemp[] {
            new QuestionTemp(R.string.question_1, true),
            new QuestionTemp(R.string.question_2, true),
            new QuestionTemp(R.string.question_3, true),
            new QuestionTemp(R.string.question_4, true),
            new QuestionTemp(R.string.question_5, true),
            new QuestionTemp(R.string.question_6, false),
            new QuestionTemp(R.string.question_7, true),
            new QuestionTemp(R.string.question_8, false),
            new QuestionTemp(R.string.question_9, true),
            new QuestionTemp(R.string.question_10, true)
    };
    final int progressBarIncrease= (int)Math.ceil(100.0/questionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState !=null){
            score =savedInstanceState.getInt("scoreKey");
            questionIndex = savedInstanceState.getInt("questionKey");
            question = questionBank[questionIndex].getQuestionId();

            questionView.setText(question);

            viewScore.setText("Score" + score + "/" + questionBank.length);
        }
        else {
            score = 0;
        }

        mainActivityControl();

    }

    public void mainActivityControl() {


        instructionButton = (Button) findViewById(R.id.instruction);
        testButton = (Button) findViewById(R.id.test);
        exitButton = (Button) findViewById(R.id.exit);
        instructionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.instruction);
                instPlayButton = (Button)findViewById(R.id.play_button);
                instExitButton = (Button)findViewById(R.id.exit_button);

                instPlayButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        questionActivity();
                    }
                });

                instExitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"Good Bye", Toast.LENGTH_SHORT);
                        finish();
                    }
                });
            }
        });

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionActivity();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void questionActivity(){

        setContentView(R.layout.question);

        true_button = (Button) findViewById(R.id.true_button);
        false_button = (Button) findViewById(R.id.false_button);
        questionView = (TextView) findViewById(R.id.question_text_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        viewScore = (TextView) findViewById(R.id.score);
        true_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                verifyAnswer(true);
                nextQuestion();
            }
        });
        false_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAnswer(false);
                nextQuestion();
            }
        });
        QuestionTemp question = new QuestionTemp(R.string.question_1,true);
        Random choseQuestion = new Random();
    }

    public  void nextQuestion(){
        questionIndex = (questionIndex + 1)%questionBank.length;

        if (questionIndex ==0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            if (score > 6) {
                alert.setMessage("Wow, Excellent marks! you scored " + score + " points.");
            }
            else {
                alert.setMessage("You scored "+score+" points");
            }
            alert.setCancelable(false);
            alert.setPositiveButton("Close App", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
        question = questionBank[questionIndex].getQuestionId();
        questionView.setText(question);
        progressBar.incrementProgressBy(progressBarIncrease);
        viewScore.setText("Score" + score +"/"+questionBank.length);

    }

    private void verifyAnswer(boolean userAnswer){
        boolean rightAnwser = questionBank[questionIndex].isAnswer();
        if(userAnswer ==rightAnwser){
            score = (score + 1);
            Toast.makeText(getApplicationContext(),"Answer is correct",Toast.LENGTH_SHORT).show();
        }
        else {

            Toast.makeText(getApplicationContext(),"Answer is wrong",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
    super.onSaveInstanceState(outState);
    outState.putInt("scoreKey",score);
    outState.putInt("questionKey",questionIndex);
    }

}
