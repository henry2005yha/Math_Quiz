package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Level_0 extends AppCompatActivity implements View.OnClickListener {

    TextView level,questionNumber, timer, question;
    Button opt1, opt2, opt3, opt4, submit;

    int questionIndex = 0;
    private int score = 0;
    int totalQuestion = questions.questions.length;

    String selectedAns = "";

    MediaPlayer successSound, failSound;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level0);

        successSound = MediaPlayer.create(this, R.raw.yay);
        failSound = MediaPlayer.create(this, R.raw.fail);
        successSound.setLooping(false);
        successSound.setLooping(false);



        //link with XML with ID
        level = findViewById(R.id.level);
        questionNumber = findViewById(R.id.questionindex);
        timer = findViewById(R.id.timer);

        timer.setText("Have Fun Without Time Limit");

        question = findViewById(R.id.question);

        opt1 = findViewById(R.id.option1);
        opt2 = findViewById(R.id.option2);
        opt3 = findViewById(R.id.option3);
        opt4 = findViewById(R.id.option4);
        submit = findViewById(R.id.submit);

        opt1.setOnClickListener(this);
        opt2.setOnClickListener(this);
        opt3.setOnClickListener(this);
        opt4.setOnClickListener(this);
        submit.setOnClickListener(this);

        submit.setEnabled(false);//initially disable the submit


        //set level text
        level.setText("Level 0");

        //load question
        loadNewQuestion();
    }

    //getRandomNonRepeatingIntegers function
    public static ArrayList getRandomNonRepeatingIntegers(int size, int min, int max) {
        ArrayList numbers = new ArrayList();
        Random random = new Random();
        while (numbers.size() < size) {
            int randomNumber = random.nextInt((max - min) + 1) + min;
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }
        return numbers;
    }

    //on click function
    @Override
    public void onClick(View v) {


        Button clickButton = (Button) v;

        if (clickButton.equals(submit)){
            if (selectedAns.equals(questions.answer[(int) List.get(questionIndex)])){
                score++;
            }

            questionIndex++;
            loadNewQuestion();
        }
        else {

            selectedAns = clickButton.getText().toString();
            if (selectedAns == null) {
                submit.setEnabled(false); // Disable the submit button if selectedAnswer is null
            } else {
                submit.setEnabled(true); // Enable the submit button if selectedAnswer is not null
            }

            //check answer
            if (selectedAns.equals(questions.answer[(int) List.get(questionIndex)])){

                if (successSound == null){
                    successSound = MediaPlayer.create(this, R.raw.yay);
                }
                successSound.start();
                clickButton.setBackgroundColor(Color.parseColor("#66ff66"));

            }
            else {
                if (failSound == null){
                failSound = MediaPlayer.create(this, R.raw.fail);

                }
                failSound.start();
                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(500);
                clickButton.setBackgroundColor(Color.parseColor("#ff4d4d"));
            }
            showAns();

        }
    }

    ArrayList List = getRandomNonRepeatingIntegers(questions.questions.length, 0, (questions.questions.length-1));
    void loadNewQuestion(){

        if (questionIndex == totalQuestion) {
            finishQuiz();
            return;
        }
        submit.setEnabled(false);
        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);


        opt1.setBackgroundColor(Color.parseColor("#069AA8"));
        opt2.setBackgroundColor(Color.parseColor("#069AA8"));
        opt3.setBackgroundColor(Color.parseColor("#069AA8"));
        opt4.setBackgroundColor(Color.parseColor("#069AA8"));


        questionNumber.setText("Question : "+(questionIndex+1)+" / "+totalQuestion );
        question.setText(questions.questions[(int) List.get(questionIndex)]);
        opt1.setText(questions.choices[(int) List.get(questionIndex)][0]);
        opt2.setText(questions.choices[(int) List.get(questionIndex)][1]);
        opt3.setText(questions.choices[(int) List.get(questionIndex)][2]);
        opt4.setText(questions.choices[(int) List.get(questionIndex)][3]);
    }

    //finish quiz function
    void finishQuiz(){

        Intent intent = new Intent(Level_0.this,Result.class);
        intent.putExtra("score",score);
        startActivity(intent);
        finish();
    }


        void showAns(){
            String b1=opt1.getText().toString();
            String b2=opt2.getText().toString();
            String b3=opt3.getText().toString();
            String b4=opt4.getText().toString();

             opt1.setEnabled(false);
             opt2.setEnabled(false);
             opt3.setEnabled(false);
             opt4.setEnabled(false);


            // Get the correct answer
            String correctAns = questions.answer[(int) List.get(questionIndex)];

             if (b1.equals(questions.answer[(int) List.get(questionIndex)])){
                 opt1.setBackgroundColor(Color.parseColor("#66ff66"));
             }


             //opt2
             if (b2.equals(questions.answer[(int) List.get(questionIndex)])){
                 opt2.setBackgroundColor(Color.parseColor("#66ff66"));
             }


             //opt3
             if (b3.equals(questions.answer[(int) List.get(questionIndex)])){
                 opt3.setBackgroundColor(Color.parseColor("#66ff66"));
             }


             //opt4
             if (b4.equals(questions.answer[(int) List.get(questionIndex)])){
                 opt4.setBackgroundColor(Color.parseColor("#66ff66"));
             }


   }


}