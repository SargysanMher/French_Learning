package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
    private static String FILE_NAME = "Best_for_house_six.txt";

    @SuppressLint({"SetTextI18n", "MissingInflatedId", "LocalSuppress"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        Bundle extras = getIntent().getExtras();
        DisplayMetrics metrics = getResources().getDisplayMetrics();

        int DeviceTotalWidth = metrics.widthPixels;
        int DeviceTotalHeight = metrics.heightPixels;

        int answer = extras.getInt("answer");
        int which_theme = extras.getInt("which_theme");
        int result = extras.getInt("result");
        int hint = extras.getInt("hint");
        int size = extras.getInt("size");
        FileInputStream fin1 = null;
        FileInputStream fin2 = null;
        FileInputStream fin3 = null;
        FileInputStream fin4 = null;
        FileInputStream fin5 = null;
        FileInputStream fin6 = null;
        FileInputStream fin7 = null;
        FileInputStream fin8 = null;
        FileInputStream fin9 = null;
        FileInputStream fin10 = null;
        FileInputStream fin11 = null;
        FileInputStream fin12 = null;
        FileOutputStream fos1 = null;
        FileOutputStream fos2 = null;
        FileOutputStream fos3 = null;
        FileOutputStream fos4 = null;
        FileOutputStream fos5 = null;
        FileOutputStream fos6 = null;
        FileOutputStream fos7 = null;
        FileOutputStream fos8 = null;
        FileOutputStream fos9 = null;
        FileOutputStream fos10 = null;
        FileOutputStream fos11 = null;
        FileOutputStream fos12 = null;


        FileInputStream fins[] = new FileInputStream[1];
        FileOutputStream foses[] = new FileOutputStream[1];
        switch (which_theme){
            case 1:
                FILE_NAME="Best_for_house_six.txt";
                fins[0]=fin1;
                foses[0]=fos1;
                break;
            case 2:
                FILE_NAME = "Best_for_animal_six.txt";
                fins[0]=fin2;
                foses[0]=fos2;

                break;
            case 3:
                FILE_NAME="Best_for_person_six.txt";
                fins[0]=fin3;
                foses[0]=fos3;

                break;
            case 4:
                FILE_NAME="Best_for_clothes_six.txt";
                fins[0]=fin4;
                foses[0]=fos4;

                break;
            case 5:
                FILE_NAME="Best_for_school_six.txt";
                fins[0]=fin5;
                foses[0]=fos5;

                break;
            case 6:
                FILE_NAME="Best_for_food_six.txt";
                fins[0]=fin6;
                foses[0]=fos6;

                break;
            case 7:
                FILE_NAME="Best_for_seasons_six.txt";
                fins[0]=fin7;
                foses[0]=fos7;

                break;
            case 8:
                FILE_NAME="Best_for_professions_six.txt";
                fins[0]=fin8;
                foses[0]=fos8;

                break;
            case 9:
                FILE_NAME="Best_for_character_six.txt";
                fins[0]=fin9;
                foses[0]=fos9;

                break;
            case 10:
                FILE_NAME="Best_for_travelling_six.txt";
                fins[0]=fin10;
                foses[0]=fos10;

                break;
            case 11:
                FILE_NAME="Best_for_nature_six.txt";
                fins[0]=fin11;
                foses[0]=fos11;

                break;
            case 12:
                FILE_NAME="Best_for_technique_six.txt";
                fins[0]=fin12;
                foses[0]=fos12;

                break;
        }
        TextView score = findViewById(R.id.score);
        TextView score1 = findViewById(R.id.score1);
        score.setText(" "+String.valueOf(result)+" ");
        score1.setText(" "+String.valueOf(size));
        TextView answers = findViewById(R.id.answers);
        if(answer==0||answer==1){
            TextView answer1 = findViewById(R.id.answer);
            answer1.setTextSize(DeviceTotalWidth/40);

            answer1.setText("answer used");
        }
        answers.setText(String.valueOf(answer)+" ");
        TextView hints = findViewById(R.id.hints);
        hints.setTextSize(DeviceTotalWidth/40);
        if(hint==0||hint==1){
            TextView hint1 = findViewById(R.id.hint);
            hint1.setTextSize(DeviceTotalWidth/40);
            hint1.setText("hint used");
        }
        hints.setText(String.valueOf(hint)+" ");
        TextView mistakes = findViewById(R.id.mistakes);
        mistakes.setTextSize(DeviceTotalWidth/40);
        if(size-result==0||size-result==1){
            TextView mistake = findViewById(R.id.mistake);
            mistake.setTextSize(DeviceTotalWidth/40);
            mistake.setText("mistake");
        }
        mistakes.setText(String.valueOf(size-result) + " ");
        TextView right = findViewById(R.id.right_answers);
        right.setTextSize(DeviceTotalWidth/40);
        right.setText(String.valueOf(result)+" ");
        TextView stars1 = findViewById(R.id.stars);
        stars1.setTextSize(DeviceTotalWidth/50);
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
        TextView tokos = findViewById(R.id.tokos);
        tokos.setTextSize(DeviceTotalWidth/50);
        tokos.setText(String.valueOf(counter1)+"%");
        Button button=findViewById(R.id.back);
        TextView your = findViewById(R.id.your_result);
        your.setTextSize(DeviceTotalWidth/50);
        button.setHeight(DeviceTotalHeight/15);
        button.setWidth(DeviceTotalWidth/3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Finish.this,MainActivity.class);

                FileOutputStream fos = null;

                try {
                    fins[0] = openFileInput(FILE_NAME);
                    element = fins[0].read();

                } catch (IOException e) {
                    e.printStackTrace();
                    element=0;
                } finally {
                    try {
                        if (fins[0] != null) {
                            fins[0].close();
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
                        else if(element==100&hint==0&answer==0){
                            stars2=3;
                        }
                        else{
                            stars2=2;
                        }
                    }
                    intent.putExtra("stars_for_house",stars2);                    intent.putExtra("stars",String.valueOf(stars2));
                    intent.putExtra("hint_for_house",hint);
                    intent.putExtra("which_theme",which_theme);



                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    try {
                        if (fos != null) {
                            fos.close();
                        }
                        if (fins[0] != null) {
                            fins[0].close();
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
        restart.setHeight(DeviceTotalHeight/15);
        restart.setWidth(DeviceTotalWidth/3);
        answers.setTextSize(DeviceTotalWidth/40);
//        View view2 = findViewById(R.id.view2);
//        view2.setMinimumWidth(DeviceTotalWidth/9);
//        View view3 = findViewById(R.id.view3);
//        view3.setMinimumWidth(DeviceTotalWidth/9);
//        View view4 = findViewById(R.id.view4);
//        view4.setMinimumWidth(DeviceTotalWidth/9);


        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Finish.this, Themes.class);
                intent.putExtra("theme",which_theme);
                startActivity(intent);
                finish();

            }
        });
    }


}