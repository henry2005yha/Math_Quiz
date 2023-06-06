package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity implements View.OnClickListener {



    TextView result, scoreGot;
    Button home, exit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);



        result=findViewById(R.id.Result);
        scoreGot = findViewById(R.id.scoreGot);
        home = findViewById(R.id.homebtn);
        exit = findViewById(R.id.exit);

        home.setOnClickListener(this);
        exit.setOnClickListener(this);

        Intent intent =getIntent();
        int score = intent.getIntExtra("score",0);



        scoreGot.setText(String.format("Your Score is "+ score +" out of "+ (questions.questions.length)));
    }

    @Override
    public void onClick(View v) {

        Button clickButton = (Button) v;

        if (clickButton.equals(home)){
            Intent intent = new Intent(Result.this, MainActivity.class);
            startActivity(intent);
        }

        else {
            finishAffinity();
        }
    }




}