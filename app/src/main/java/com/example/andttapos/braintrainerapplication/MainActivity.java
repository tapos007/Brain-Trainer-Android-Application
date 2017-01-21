package com.example.andttapos.braintrainerapplication;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.security.SecureRandom;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    SecureRandom random;
    TextView sumTextView;
    TextView resultTextView;
    TextView pointTextView;
    TextView timerTextView;
    ArrayList<Integer> answer = new ArrayList<>();
    GridLayout gL;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    int locationOfCorrectAnswer;
    int score = 0;
    int totalQuestion = 0;
    boolean gameStatus = false;

    public void chooseAnswer(View view){
        if(gameStatus){
            Button button = (Button) view;
            if(button.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
                resultTextView.setText("Currect Answer!!");
                score++;
            }else {
                resultTextView.setText("Wrong Answer!!");
            }
            totalQuestion++;
            randomNumberGenerator();
            pointTextView.setText(Integer.toString(score)+"/"+ Integer.toString(totalQuestion));
        }


    }
    public void start(View view) {
        gameStatus = true;
        startButton.setVisibility(View.INVISIBLE);
        gL.setVisibility(View.VISIBLE);
        randomNumberGenerator();

        new CountDownTimer(30 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                int remainingTime = (int) l / 1000;
                timerTextView.setText(String.format("%02ds",remainingTime));
            }

            @Override
            public void onFinish() {
                timerTextView.setText(String.format("%02ds",0));
                resultTextView.setText("your score is:\n"+ Integer.toString(score)+"/"+ Integer.toString(totalQuestion));
                gameStatus = false;
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.startButton);
        gL = (GridLayout) findViewById(R.id.gridLayout);
        random = new SecureRandom();

        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        pointTextView = (TextView) findViewById(R.id.pointTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);




    }

    public void randomNumberGenerator() {
        answer.clear();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        int incurrectAnswer;
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrectAnswer = random.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answer.add(a + b);
            } else {
                incurrectAnswer = random.nextInt(41);

                while (incurrectAnswer == a + b) {
                    incurrectAnswer = random.nextInt(41);
                }
                answer.add(incurrectAnswer);
            }
        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }
}
