package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button lv0, lv1, lv2;
    TextView welcome;
    ImageButton info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dialog dialog = new Dialog(this);


        lv0 = findViewById(R.id.level0);
        lv1 = findViewById(R.id.level1);
        lv2 = findViewById(R.id.level2);
        info = findViewById(R.id.info);

        lv0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent level0=new Intent(MainActivity.this, Level_0.class);
                startActivity(level0);
            }
        });

        lv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent level1=new Intent(MainActivity.this,Level_1.class);
                startActivity(level1);
            }
        });

      lv2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent level2=new Intent(MainActivity.this, Level_2.class);
              startActivity(level2);
          }
      });

      info.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              dialog.setContentView(R.layout.dialog1);
              dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
              dialog.setCancelable(false);

              Button btnok = dialog.findViewById(R.id.ok);
              btnok.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      dialog.dismiss();
                  }
              });dialog.show();
          }
      });
    }
}

