package com.example.mathquiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class Level_2  extends AppCompatActivity implements View.OnClickListener{

    TextView level,questionNumber, timer, question;
    Button opt1, opt2, opt3, opt4, submit;

    int questionIndex = 0;
    private int score = 0;
    int totalQuestion = questions.questions.length;

    String selectedAns = "";

    MediaPlayer successSound, failSound;
    CountDownTimer countDownTimer;
    boolean playAnimation = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);

        successSound = MediaPlayer.create(this, R.raw.yay);
        failSound = MediaPlayer.create(this, R.raw.fail);
        successSound.setLooping(false);
        successSound.setLooping(false);



        //link with XML with ID
        level = findViewById(R.id.LEVEL1);
        questionNumber = findViewById(R.id.QUESTIONINDEX1);
        timer = findViewById(R.id.TIMER1);
        question = findViewById(R.id.QUESTION1);

        opt1 = findViewById(R.id.OPTION11);
        opt2 = findViewById(R.id.OPTION21);
        opt3 = findViewById(R.id.OPTION31);
        opt4 = findViewById(R.id.OPTION41);
        submit = findViewById(R.id.SUBMIT1);

        opt1.setOnClickListener(this);
        opt2.setOnClickListener(this);
        opt3.setOnClickListener(this);
        opt4.setOnClickListener(this);
        submit.setOnClickListener(this);

        submit.setEnabled(false);//initially disable the submit


        //set level text
        level.setText("Level 2");

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
            countDownTimer.cancel();
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
        timer();
        if (questionIndex == totalQuestion) {
            finishQuiz();
            return;
        }
        submit.setEnabled(false);
        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);

        timer.clearAnimation();
        timer.setTextSize(16);
        timer.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));

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
        countDownTimer.cancel();
        Intent intent = new Intent(Level_2.this,Result.class);
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



    private void timer() {
        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // calculate seconds remaining and format as a string
                String seconds = String.format("%02d", millisUntilFinished / 1000);
                timer.setText("00:" + seconds);

                if (millisUntilFinished < 4000) {


                    if(playAnimation){
                        timer.setText("00:" + seconds);
                        timer.setTextSize(24);
                        timer.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                        timer.setTextColor(Color.RED);


                        Animation animation = new AlphaAnimation(0.2f, 1.0f);
                        animation.setDuration(1000);
                        animation.setInterpolator(new LinearInterpolator());
                        animation.setRepeatCount(Animation.INFINITE);
                        animation.setRepeatMode(Animation.REVERSE);
                        timer.startAnimation(animation);


                    }
                }

            }

            public void onFinish() {
                // When the timer finishes, load the next question
                questionIndex++;
                timer.clearAnimation();
                timer.setTextSize(16);
                timer.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                loadNewQuestion();

            }
        };

// Start the timer
        countDownTimer.start();
        playAnimation=true;
        timer.setTextColor(Color.BLACK);

    }

}