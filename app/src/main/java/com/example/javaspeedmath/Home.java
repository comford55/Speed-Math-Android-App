package com.example.javaspeedmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button startB = findViewById(R.id.startButton);
        startB.setOnClickListener(v -> startActivity(new Intent(Home.this,MainActivity.class)));

    }
}