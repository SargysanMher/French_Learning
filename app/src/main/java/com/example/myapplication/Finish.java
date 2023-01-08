package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Finish extends AppCompatActivity {
    String stars;
    int element;
    int stars2;
    private final static String FILE_NAME = "Best_for_house_six.txt";

    @SuppressLint({"SetTextI18n", "MissingInflatedId", "LocalSuppress"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        Bundle extras = getIntent().getExtras();
        int result = extras.getInt("result");
        int hint = extras.getInt("hint");
        int size = extras.getInt("size");
        TextView score = findViewById(R.id.score);
        TextView score1 = findViewById(R.id.score1);
        score.setText(" "+String.valueOf(result)+" ");
        score1.setText(" "+String.valueOf(size));
        TextView hints = findViewById(R.id.hints);
        if(hint==0){
            TextView hint1 = findViewById(R.id.hint);
            hint1.setText("hint used");
        }
        hints.setText(String.valueOf(hint)+" ");
        TextView mistakes = findViewById(R.id.mistakes);
        if(size-result==0){
            TextView mistake = findViewById(R.id.mistake);
            mistake.setText("mistake");
        }
        mistakes.setText(String.valueOf(size-result) + " ");
        TextView right = findViewById(R.id.right_answers);
        right.setText(String.valueOf(result)+" ");
        TextView stars1 = findViewById(R.id.stars);
        int counter1 =(int) result*100/size;
        if(counter1<100/3){
            stars = " ✰✰✰";
            stars2=0;
            stars1.setText(stars);
        }
        else if(counter1<100/2){
            stars = " ★✰✰";
            stars2=1;

            stars1.setText(stars);

        }
        else if(counter1<100){
            stars = " ★★✰";
            stars2=2;

            stars1.setText(stars);
        }
        else if(counter1==100&hint==0){
            stars = " ★★★";
            stars1.setText(stars);
            stars2=3;

        }
        else{
            stars = " ★★✰";
            stars2=2;

            stars1.setText(stars);
        }
        Button button=findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Finish.this,MainActivity.class);

                FileOutputStream fos = null;
                FileInputStream fin = null;

                try {
                    fin = openFileInput(FILE_NAME);
                    element = fin.read();

                } catch (IOException e) {
                    e.printStackTrace();
                    element=0;
                } finally {
                    try {
                        if (fin != null) {
                            fin.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                try {

                    if (counter1 > element) {
                        fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                        fos.write(counter1);
                    }
                    else{
                        if(element<100/3){
                            stars2=0;
                        }
                        else if(element<100/2){
                            stars2=1;
                        }
                        else if(element<100){
                            stars2=2;
                        }
                        else if(element==100&hint==0){
                            stars2=3;
                        }
                        else{
                            stars2=2;
                        }
                    }
                    intent.putExtra("stars_for_house",stars2);                    intent.putExtra("stars",String.valueOf(stars2));
                    intent.putExtra("hint_for_house",hint);



                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    try {
                        if (fos != null) {
                            fos.close();
                        }
                        if (fin != null) {
                            fin.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                startActivity(intent);
                finish();
            }
        });
        Button restart=findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Finish.this, Themes.class);
                startActivity(intent);
                finish();
            }
        });
    }


}