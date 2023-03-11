package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Start extends AppCompatActivity {

int which_theme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        SharedPreferences savedData1 = getSharedPreferences("savedresult",MODE_PRIVATE);
        SharedPreferences savedData2 = getSharedPreferences("savedresult2",MODE_PRIVATE);

        savedData1.edit().clear().apply();
        savedData2.edit().clear().apply();
        int DeviceTotalWidth = metrics.widthPixels;
        int DeviceTotalHeight = metrics.heightPixels;
        TextView French_Learning = findViewById(R.id.french_learning);
        French_Learning.setTextSize(DeviceTotalWidth/40);

    }

    public void rules(View view) {

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
        if(language.equals("")){
            language="en";
        }
        setlocale(language);
    }

    public void vocabulary(View view) {


        Intent intent = new Intent(Start.this,MainActivity.class);
        startActivity(intent);
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
    public void sentence(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(Start.this);
        builder.setTitle("Which level?");
        builder.setItems(new CharSequence[]
                        {"A1", "A2", "B1", "B2"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which) {
                            case 0:
                                which_theme=12;
                                break;
                            case 1:
                                which_theme=13;
                                break;
                            case 2:
                                which_theme=14;
                                break;
                            case 3:
                                which_theme=15;
                                break;

                        }
                        Intent intent = new Intent(Start.this,Sentence.class);
                        intent.putExtra("which_theme",which_theme);
                        startActivity(intent);
                    }
                });
        builder.create().show();

    }
}