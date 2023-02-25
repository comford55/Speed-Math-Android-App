package com.example.javaspeedmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Answer extends AppCompatActivity {
    ArrayList<Integer> ans = new ArrayList<>();
    private TextView scoreText,highestText;
    private Button mReplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        mReplay = (Button) findViewById(R.id.replay);
        scoreText = (TextView) findViewById(R.id.textView);
        highestText = (TextView) findViewById(R.id.hightestscore);

        Intent intent = getIntent();
        int score = intent.getIntExtra("SCORE",0);
        scoreText.setText(String.valueOf(score));

        SharedPreferences setting = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highscore = setting.getInt("HIGH_SCORE",0);

        if(score>highscore){
            highestText.setText(String.valueOf(score));
            SharedPreferences.Editor editor = setting.edit();
            editor.putInt("HIGH_SCORE",score);
            editor.commit();
        }
        else{
            highestText.setText(String.valueOf(highscore));
        }

        mReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        Button backtomain = findViewById(R.id.backtomain);
        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Answer.this,Home.class));
            }
        });

    }
}