package com.example.javaspeedmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.toBinaryString;
import static java.lang.Integer.valueOf;

public class MainActivity extends AppCompatActivity {
    private CountDownTimer countdownTimer;
    ArrayList<Integer> ansList = new ArrayList<>();
    ArrayList<Integer> UserList = new ArrayList<>();
    private int UserAnswer;
    private int index = 0,CorrectCount = 0;
    private EditText ansText;
    private TextView countdown_Text;
    private long timeLeftinMilli = 60000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countdown_Text = findViewById(R.id.countdonwText);
        startTimer();
        Random myRand = new Random();
        TextView textRand1 = findViewById(R.id.randText1);
        TextView textRand2 = findViewById(R.id.randText2);
        TextView operator = findViewById(R.id.randOperator);
        Button randButton = (Button) findViewById(R.id.randButton);
        ansText = findViewById(R.id.editAns);

        String[] array = {"+","-"};

        while (index==0){
            String n1 = String.valueOf(myRand.nextInt(50));
            String n2 = String.valueOf(myRand.nextInt(50));

            textRand1.setText(n1);
            textRand2.setText(n2);

            int randIndex = new Random().nextInt(array.length);
            String randOp = array[randIndex];

            int ans1 = parseInt(n1);
            int ans2 = parseInt(n2);


            operator.setText(randOp);
            if(randOp == "+"){
                ansList.add(ans1 + ans2);
                index += 1;
            }
            else{
                ansList.add(ans1 - ans2);
                index += 1;
            }
        }

        randButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String errorWarn = ansText.getText().toString();
                if(TextUtils.isEmpty(errorWarn)){
                    ansText.setError("Enter something");
                    return;
                }
                else{
                    UserAnswer = Integer.valueOf(errorWarn);
                    UserList.add(UserAnswer);
                }
                String n1 = String.valueOf(myRand.nextInt(50));
                String n2 = String.valueOf(myRand.nextInt(50));

                textRand1.setText(n1);
                textRand2.setText(n2);

                int randIndex = new Random().nextInt(array.length);
                String randOp = array[randIndex];

                int ans1 = parseInt(n1);
                int ans2 = parseInt(n2);


                operator.setText(randOp);

                if(randOp == "+"){
                    ansList.add(ans1 + ans2);
                    index += 1;
                }
                else{
                    ansList.add(ans1 - ans2);
                    index += 1;
                }
                ansText.setText("");
            }
        });

    }
    public void startTimer(){
        countdownTimer = new CountDownTimer(timeLeftinMilli,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftinMilli = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                CheckElements();
                CheckAnswer();
                getScore();
            }
        }.start();
    }
    public void updateTimer() {
        int second = (int) timeLeftinMilli % 60000 / 1000;

        String timeLeftText;
        timeLeftText = "";
        if(second<1) timeLeftText += "0";
        timeLeftText += second;
        countdown_Text.setText(timeLeftText);
    }
    public void CheckAnswer(){
        for(int i=0;i<ansList.size();i++){
            if (ansList.get(i) == UserList.get(i)) {
                CorrectCount += 1;
            }
            else{
                CorrectCount += 0;
            }
        }
    }

    public void CheckElements(){
        if(ansList.size()>UserList.size()){
            UserList.add(101);
        }
    }
    public void getScore(){
        Intent intent = new Intent(getApplicationContext(),Answer.class);
        intent.putExtra("SCORE",CorrectCount);
        startActivity(intent);
    }
}