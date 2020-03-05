package com.traf1.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SecondActivity extends AppCompatActivity {
    Timer timer = new Timer();
    int duration = 0;
    TextView timeDisplay;
    int questionNumber = 0;
    int score = 0;
    String[] questionArray = QuestionLibrary.getQuestions();
    String[][] answersArray = QuestionLibrary.getChoices();
    String[] answers = QuestionLibrary.getCorrectAnswers();

    TextView question;
    Button answerA;
    Button answerB;
    Button answerC;
    Button answerD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        timeDisplay = findViewById(R.id.gameTime);
        timeDisplay.setText(getString(R.string.time, 60));

        Intent intent = getIntent();
        TextView label = findViewById(R.id.label);
        label.setText(intent.getStringExtra("com.lowejimmy.quizapp.extra.MESSAGE"));

        question = findViewById(R.id.question);
        answerA = findViewById(R.id.answerA);
        answerB = findViewById(R.id.answerB);
        answerC = findViewById(R.id.answerC);
        answerD = findViewById(R.id.answerD);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String currentTime = getString(R.string.time,59- duration++);
                        /*
                        if(duration>=5){
                            timer.cancel();
                            timer.purge();
                            timer=new Timer();
                            duration=0;
                            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                            intent.putExtra("com.traf1.demo.extra.MESSAGE",timeDisplay.getText().toString());
                            //startActivityForResult(intent, 1);
                        }
                        */
                        timeDisplay.setText(currentTime);

                        if(duration>=60){
                            timeOut();
                        }
                    }
                });
            }
        }, 1000, 1000);
    }

    public void returnToPrevious(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("com.lowejimmy.quizapp.extra.REPLY", "Results");//insert message

        returnIntent.putExtra("com.lowejimmy.quizapp.extra.REPLY", "Results");//insert message
        setResult(RESULT_OK, returnIntent);//indicate response was successful
        finish();//close activity and return to main activity
    }

    private void timeOut() {
        submit();
    }

    public void selectA(View view) {
        String theirAnswer = answersArray[questionNumber][0];
        if(theirAnswer.equals(answers[questionNumber])) {
            score += 1;
        }
        nextQuestion();
    }

    public void selectB(View view) {
        String theirAnswer = answersArray[questionNumber][1];
        if(theirAnswer.equals(answers[questionNumber])) {
            score += 1;
        }
        nextQuestion();
    }

    public void selectC(View view) {
        String theirAnswer = answersArray[questionNumber][2];
        if(theirAnswer.equals(answers[questionNumber])) {
            score += 1;
        }
        nextQuestion();
    }

    public void selectD(View view) {
        String theirAnswer = answersArray[questionNumber][3];
        if(theirAnswer.equals(answers[questionNumber])) {
            score += 1;
        }
        nextQuestion();
    }

    private void nextQuestion() {
        if (questionNumber < 9) {
            questionNumber += 1;
            question.setText(questionArray[questionNumber]);
            answerA.setText(answersArray[questionNumber][0]);
            answerB.setText(answersArray[questionNumber][1]);
            answerC.setText(answersArray[questionNumber][2]);
            answerD.setText(answersArray[questionNumber][3]);
        }
        else {
            submit();
        }
    }

    private void submit() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("com.lowejimmy.quizapp.extra.REPLY", "Results: " + score);//insert message
        setResult(RESULT_OK, returnIntent);//indicate response was successful
        finish();//close activity and return to main activity
    }
}