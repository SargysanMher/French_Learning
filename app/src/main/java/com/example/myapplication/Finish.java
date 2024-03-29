package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

public class Finish extends AppCompatActivity {
    String stars;
    int element;
    int answer=-1;
    int hint=-1;

    @SuppressLint({"SetTextI18n", "MissingInflatedId", "LocalSuppress"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_finish);

        Bundle extras = getIntent().getExtras();
        DisplayMetrics metrics = getResources().getDisplayMetrics();

        float DeviceTotalWidth = metrics.widthPixels;
        float DeviceTotalHeight = metrics.heightPixels;

        try{
            answer = extras.getInt("answer");
            hint = extras.getInt("hint");

        }catch (Exception e){
            e.printStackTrace();
        }
        int which_theme = extras.getInt("which_theme");
        int result = extras.getInt("result");
        int size = extras.getInt("size");


        TextView view = findViewById(R.id.view);
        view.setHeight((int) (DeviceTotalHeight/5.8));
        TextView view1 = findViewById(R.id.view1);
        view1.setHeight((int) (DeviceTotalHeight/5.8));

        TextView score = findViewById(R.id.score);


        TextView score1 = findViewById(R.id.score1);
        score.setText(" "+String.valueOf(result)+" ");
        score1.setText(" "+String.valueOf(size));
        TextView answers = findViewById(R.id.answers);
        answers.setTextSize(DeviceTotalWidth/30);
        TextView answer1 = findViewById(R.id.answer);
        answer1.setTextSize(DeviceTotalWidth/30);
        if(which_theme<12){
            if(answer==1){
                answer1.setText(R.string.answer_used);
            }
            else{
                if(Locale.getDefault().getLanguage().equals("en")) {
                    answer1.setText(R.string.answers_used);
                }
                else{
                    Resources res = getResources();

                    String bar = res.getString(R.string.answers_used) + verjavorutyun(answer);
                    answer1.setText(bar);
                }
            }
        }
        else{
            answer1.setVisibility(View.INVISIBLE);
            answers.setVisibility(View.INVISIBLE);

        }

        answers.setText(String.valueOf(answer)+" ");
        TextView hints = findViewById(R.id.hints);
        hints.setTextSize(DeviceTotalWidth/40);
        TextView hint1 = findViewById(R.id.hint);
        hint1.setTextSize(DeviceTotalWidth/30);
        if(which_theme<12){

            if(hint==1){
            hint1.setText(R.string.hint_used);
            } else {
                if (Locale.getDefault().getLanguage().equals("en")) {
                    hint1.setText(R.string.hints_used);
                } else {
                    Resources res = getResources();
                    String bar = res.getString(R.string.hints_used) + verjavorutyunigakan(hint);
                    hint1.setText(bar);
                }
            }
        }else{
            hint1.setVisibility(View.INVISIBLE);
            hints.setVisibility(View.INVISIBLE);

        }
        hints.setText(String.valueOf(hint)+" ");
        TextView mistakes = findViewById(R.id.mistakes);
        mistakes.setTextSize(DeviceTotalWidth/40);
        TextView mistake = findViewById(R.id.mistake);
        mistake.setTextSize(DeviceTotalWidth/30);
        if(size-result==1){
            mistake.setText(R.string.mistake);
        }else{
            if(Locale.getDefault().getLanguage().equals("en")){
                mistake.setText(R.string.mistakes);
            }else{
                Resources res = getResources();
                String bar = res.getString(R.string.mistakes) + verjavorutyunigakan(size - result);
                mistake.setText(bar);
            }

        }

        mistakes.setText(String.valueOf(size-result) + " ");
        TextView right = findViewById(R.id.right_answers);
        right.setTextSize(DeviceTotalWidth/30);
        TextView right_answer = findViewById(R.id.right_answer);
        right_answer.setTextSize(DeviceTotalWidth/30);
        if(result ==1){
            right_answer.setText(R.string.right_answer);
        }else{
            if(Locale.getDefault().getLanguage().equals("en")){
                right_answer.setText(R.string.right_answers);
            }else{
                Resources res = getResources();
                String bar = res.getString(R.string.right_answers) + verjavorutyun(result);
                right_answer.setText(bar);
            }

        }
        right.setText(String.valueOf(result)+" ");


        TextView stars1 = findViewById(R.id.stars);
        stars1.setTextSize(DeviceTotalWidth/40);
        int counter1 =(int) result*100/size;
        if(counter1<100/5){
            stars = " ✰✰✰";
            stars1.setText(stars);
        }
        else if(counter1<100/2){
            stars = " ★✰✰";

            stars1.setText(stars);

        }
        else if(counter1<100){
            stars = " ★★✰";

            stars1.setText(stars);
        }
        else if(counter1==100&hint==0&answer==0){
            stars = " ★★★";
            stars1.setText(stars);


        }
        else{
            stars = " ★★✰";

            stars1.setText(stars);
        }
        TextView tokos = findViewById(R.id.tokos);
        tokos.setTextSize(DeviceTotalWidth/40);
        tokos.setText(String.valueOf(counter1)+"%");
        Button button=findViewById(R.id.back);
        TextView your = findViewById(R.id.your_result);
        if(which_theme>=12){
            TextView correctly = findViewById(R.id.you_correctly_translated);
            TextView words = findViewById(R.id.words_out_of);
            words.setText(R.string.correct_answers_out_of);
            correctly.setText(R.string.you_chose);
            String level="";
            switch (which_theme){
                case 12:
                    level = "A1";
                    break;
                case 13:
                    level = "A2";
                    break;
                case 14:
                    level = "B1";
                    break;
                case 15:
                    level = "B2";
                    break;
            }
            Resources res = getResources();
            your.setText(res.getString(R.string.your_result) + " "+level);

        }
        your.setTextSize(DeviceTotalWidth/45);
        button.setHeight((int) (DeviceTotalHeight/15));
        button.setWidth((int) (DeviceTotalWidth/3));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(which_theme < 12){
                    MainActivity.fa.finish();

                }
                SharedPreferences savedData = getSharedPreferences("savedData",MODE_PRIVATE);
                SharedPreferences.Editor editor = savedData.edit();
                element = savedData.getInt(String.valueOf(which_theme+"int"),counter1);
                if(element<=counter1){
                    if(counter1<100/5){
                        stars= "✰✰✰";
                    }
                    else if(counter1<100/2){
                        stars= " ★✰✰";
                    }
                    else if(counter1<100){
                        stars= " ★★✰";
                    }
                    else if(counter1==100&hint==0&answer==0){
                        stars= " ★★★";
                    }
                    else {
                        stars="★★✰";
                    }
                    element=counter1;
                    editor.putString(String.valueOf(which_theme),stars);

                }



                editor.putInt(String.valueOf(which_theme)+"int",element);

                editor.apply();

                Intent intent;
                if(which_theme>=12){
                    intent  = new Intent(Finish.this,Start.class);

                }else{
                    intent = new Intent(Finish.this,MainActivity.class);

                }
                finish();

                startActivity(intent);
            }
        });
        Button restart=findViewById(R.id.restart);
        restart.setHeight((int) (DeviceTotalHeight/15));
        restart.setWidth((int) (DeviceTotalWidth/3));
        answers.setTextSize(DeviceTotalWidth/40);



        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(which_theme>=12){
                    intent  = new Intent(Finish.this,Sentence.class);

                }else{
                    intent = new Intent(Finish.this,Themes.class);

                }
                intent.putExtra("which_theme",which_theme);
                finish();
                startActivity(intent);

            }
        });
    }
    private String verjavorutyun(int dranic){
        String X="";

        if (dranic % 10 == 1 & dranic % 100!= 11) {

            X = "";
        }
        else if (dranic % 10 >= 2 & dranic % 10 <= 4 & dranic % 100!= 12 & dranic % 100!= 13 & dranic % 100!= 14 ) {
            X = "а";
        }
        else if (dranic % 10 >= 5) {
            X = "ов";
        }
        else if (dranic % 100 == 11 || dranic%100 == 12 || dranic%100 == 13 || dranic%100 == 14 || dranic%100 == 15 || dranic%100 == 16 || dranic%100 == 17 || dranic%100 == 18 || dranic%100 == 19)
        {
            X = "ов";
        }
        else if (dranic % 10 == 0)
        {
            X="ов";
        }
        return X;

    }
    private String verjavorutyunigakan(int dranic){
        String X="";

        if (dranic % 10 == 1 & dranic % 100!= 11) {

            X = "";
        }
        else if (dranic % 10 >= 2 & dranic % 10 <= 4 & dranic % 100!= 12 & dranic % 100!= 13 & dranic % 100!= 14 ) {
            X = "ки";
        }
        else if (dranic % 10 >= 5) {
            X = "ок";
        }
        else if (dranic % 100 == 11 || dranic%100 == 12 || dranic%100 == 13 || dranic%100 == 14 || dranic%100 == 15 || dranic%100 == 16 || dranic%100 == 17 || dranic%100 == 18 || dranic%100 == 19)
        {
            X = "ок";
        }
        else if (dranic % 10 == 0)
        {
            X="ок";
        }
        return X;

    }
    private void setlocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("Mylang",lang);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("Mylang","");
        setlocale(language);
    }
}