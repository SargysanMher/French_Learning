package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class Rules extends AppCompatActivity {
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    int clickCount = 0;    int clickCount1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_rules);
        TextView text = findViewById(R.id.text);
        TextView text1 = findViewById(R.id.text1);
        TextView click1 = findViewById(R.id.click1);
        TextView click2 = findViewById(R.id.click2);
        TextView sentence_rules = findViewById(R.id.sentence_rules);

        TextView vocabRules = findViewById(R.id.vocab_rules);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int DeviceTotalWidth = metrics.widthPixels;
        int DeviceTotalHeight = metrics.heightPixels;
        text.setTextSize(DeviceTotalWidth/35);
        click1.setTextSize(DeviceTotalWidth/35);
        vocabRules.setTextSize(DeviceTotalWidth/35);
        Log.d("pppqb", String.valueOf(vocabRules.getHeight()));


// Скрываем элемент vocabRules при запуске приложения
        vocabRules.setVisibility(View.INVISIBLE);
        sentence_rules.setVisibility(View.INVISIBLE);
        Log.d("pppqb", String.valueOf(vocabRules.getHeight()));

// Обработчик нажатий на элемент text
        text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                clickCount++; // увеличиваем счетчик нажатий

                if (clickCount % 2 != 0) { // при нечетном количестве нажатий
                    // Показываем элемент vocabRules и перемещаем элемент text1 под vocabRules
                    vocabRules.setVisibility(View.VISIBLE);
                    Log.d("pppqb", String.valueOf(vocabRules.getHeight()));
                    text1.setY(vocabRules.getY()+vocabRules.getHeight());
                    click2.setY(vocabRules.getY() + vocabRules.getHeight());
                    sentence_rules.setY(vocabRules.getY() + vocabRules.getHeight()+text1.getHeight());

                    click1.setRotation(90);

                } else { // при четном количестве нажатий
                    // Скрываем элемент vocabRules и перемещаем элемент text1 под text
                    vocabRules.setVisibility(View.INVISIBLE);
                    click1.setRotation(-90);
                    click2.setY(text.getY() + text.getHeight());
                    sentence_rules.setY(text.getY() + text.getHeight()+text1.getHeight());


                    text1.setY(text.getY() + text.getHeight());
                }
            }
        });
        click1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++; // увеличиваем счетчик нажатий

                if (clickCount % 2 != 0) { // при нечетном количестве нажатий
                    // Показываем элемент vocabRules и перемещаем элемент text1 под vocabRules
                    vocabRules.setVisibility(View.VISIBLE);
                    text1.setY(vocabRules.getY() + vocabRules.getHeight());
                    click2.setY(vocabRules.getY() + vocabRules.getHeight());
                    sentence_rules.setY(vocabRules.getY() + vocabRules.getHeight()+text1.getHeight());


                    click1.setRotation(90);
                } else { // при четном количестве нажатий
                    // Скрываем элемент vocabRules и перемещаем элемент text1 под text
                    vocabRules.setVisibility(View.INVISIBLE);
                    click1.setRotation(-90);
                    click2.setY(text.getY() + text.getHeight());
                    text1.setY(text.getY() + text.getHeight());
                    sentence_rules.setY(text.getY() + text.getHeight()+text1.getHeight());

                }
            }
        });
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount1++; // увеличиваем счетчик нажатий

                if (clickCount1 % 2 != 0) { // при нечетном количестве нажатий
                    // Показываем элемент vocabRules и перемещаем элемент text1 под vocabRules
                    sentence_rules.setVisibility(View.VISIBLE);
                    click2.setRotation(90);
                } else { // при четном количестве нажатий
                    // Скрываем элемент vocabRules и перемещаем элемент text1 под text
                    sentence_rules.setVisibility(View.INVISIBLE);
                    click2.setRotation(-90);

                }
            }
        });
        click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount1++; // увеличиваем счетчик нажатий

                if (clickCount1 % 2 != 0) { // при нечетном количестве нажатий
                    // Показываем элемент vocabRules и перемещаем элемент text1 под vocabRules
                    sentence_rules.setVisibility(View.VISIBLE);
                    click2.setRotation(90);
                } else { // при четном количестве нажатий
                    // Скрываем элемент vocabRules и перемещаем элемент text1 под text
                    sentence_rules.setVisibility(View.INVISIBLE);
                    click2.setRotation(-90);

                }
            }
        });


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


    public void change(View view) {
        if(Locale.getDefault().getLanguage().equals("en")){
            setlocale("ru");
            recreate();
        }
        else{
            setlocale("en");
            recreate();
        }
    }

    public void back(View view) {
        Intent intent = new Intent(Rules.this,Start.class);
        finish();
        startActivity(intent);
    }
}