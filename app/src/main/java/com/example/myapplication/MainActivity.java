package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    String stars;
    public static  Activity fa;



    @Override
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);
        TextView house = findViewById(R.id.house);
        fa=this;
        SharedPreferences savedData2 = getSharedPreferences("savedresult2",MODE_PRIVATE);
        savedData2.edit().clear().apply();


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int DeviceTotalWidth = metrics.widthPixels;
        int DeviceTotalHeight = metrics.heightPixels;
        Log.d("asd", String.valueOf(DeviceTotalHeight));

        TextView french_learning = findViewById(R.id.french_learning);
        french_learning.setHeight(DeviceTotalWidth / 15);
        house.setTextSize((float) DeviceTotalHeight / 100);

        ImageView home = findViewById(R.id.home);

        Log.d("rt", String.valueOf(home.getLayoutParams()));
        home.setPadding(DeviceTotalWidth / 60, DeviceTotalHeight / 60, DeviceTotalWidth / 60, DeviceTotalHeight / 40);
        ImageView animals = findViewById(R.id.animals);
        animals.setPadding(DeviceTotalWidth / 60, DeviceTotalHeight / 60, DeviceTotalWidth / 60, DeviceTotalHeight / 40);
        ImageView person = findViewById(R.id.person);
        person.setPadding(DeviceTotalWidth / 100, DeviceTotalHeight / 60, DeviceTotalWidth / 100, DeviceTotalHeight / 40);
        ImageView clothes = findViewById(R.id.clothes);
        clothes.setPadding(DeviceTotalWidth / 60, DeviceTotalHeight / 60, DeviceTotalWidth / 60, DeviceTotalHeight / 40);
        ImageView school = findViewById(R.id.school);
        school.setPadding(DeviceTotalWidth / 60, DeviceTotalHeight / 60, DeviceTotalWidth / 60, DeviceTotalHeight / 40);
        ImageView food = findViewById(R.id.food);
        food.setPadding(DeviceTotalWidth / 60, DeviceTotalHeight / 60, DeviceTotalWidth / 60, DeviceTotalHeight / 40);
        ImageView seasons = findViewById(R.id.seasons);
        seasons.setPadding(DeviceTotalWidth / 60, DeviceTotalHeight / 60, DeviceTotalWidth / 60, DeviceTotalHeight / 40);
        ImageView proffesions = findViewById(R.id.professions);
        proffesions.setPadding(DeviceTotalWidth / 60, DeviceTotalHeight / 60, DeviceTotalWidth / 60, DeviceTotalHeight / 40);
        ImageView character = findViewById(R.id.character);
        character.setPadding(DeviceTotalWidth / 60, DeviceTotalHeight / 60, DeviceTotalWidth / 60, DeviceTotalHeight / 40);
        ImageView travelling = findViewById(R.id.travel);
        travelling.setPadding(DeviceTotalWidth / 60, DeviceTotalHeight / 60, DeviceTotalWidth / 60, DeviceTotalHeight / 40);
        ImageView nature = findViewById(R.id.nature);
        nature.setPadding(DeviceTotalWidth / 60, DeviceTotalHeight / 60, DeviceTotalWidth / 60, DeviceTotalHeight / 40);
        ImageView technique = findViewById(R.id.tecknology);
        technique.setPadding(DeviceTotalWidth / 60, DeviceTotalHeight / 60, DeviceTotalWidth / 60, DeviceTotalHeight / 40);
        animals.getLayoutParams().height=DeviceTotalHeight/6;
        person.getLayoutParams().height=DeviceTotalHeight/6;
        clothes.getLayoutParams().height=DeviceTotalHeight/6;
        school.getLayoutParams().height=DeviceTotalHeight/6;
        food.getLayoutParams().height=DeviceTotalHeight/6;
        seasons.getLayoutParams().height=DeviceTotalHeight/6;
        proffesions.getLayoutParams().height=DeviceTotalHeight/6;
        character.getLayoutParams().height=DeviceTotalHeight/6;
        home.getLayoutParams().height=DeviceTotalHeight/6;
        travelling.getLayoutParams().height=DeviceTotalHeight/6;
        nature.getLayoutParams().height=DeviceTotalHeight/6;
        technique.getLayoutParams().height=DeviceTotalHeight/6;



        ///////////////////////////////////////////////////////////
        TextView house_stars = findViewById(R.id.stars_of_house);
        TextView animals_stars = findViewById(R.id.stars_of_animals);
        TextView professions_stars = findViewById(R.id.stars_of_professions);
        TextView technique_stars = findViewById(R.id.stars_of_technique);
        TextView travelling_stars = findViewById(R.id.stars_of_travel);
        TextView school_stars = findViewById(R.id.stars_of_school);
        TextView food_stars = findViewById(R.id.stars_of_food);
        TextView character_stars = findViewById(R.id.stars_of_character);
        TextView seasons_stars = findViewById(R.id.stars_of_seasons);
        TextView clothes_stars = findViewById(R.id.stars_of_clothes);
        TextView nature_stars = findViewById(R.id.stars_of_nature);
        TextView all_stars = findViewById(R.id.stars_of_all);

        TextView Person_stars = findViewById(R.id.stars_of_person);
        TextView[] texts = {all_stars,house_stars,animals_stars,Person_stars,technique_stars,nature_stars,travelling_stars,character_stars,professions_stars,food_stars,school_stars,clothes_stars,seasons_stars};

        SharedPreferences savedData = getSharedPreferences("savedData",MODE_PRIVATE);
        for (int i = 0; i <= 12; i++) {
            String kk = String.valueOf(i);

            stars = savedData.getString(kk,"");
            texts[i].setText(stars);


        }





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

    public void house(View view) {

        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("which_theme",1);
        startActivity(intent);
    }

    public void animals(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("which_theme",2);
        startActivity(intent);
    }

    public void person(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("which_theme",3);
        startActivity(intent);
    }

    public void technique(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("which_theme",4);
        startActivity(intent);
    }

    public void nature(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("which_theme",5);
        startActivity(intent);
    }

    public void travel(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("which_theme",6);
        startActivity(intent);
    }

    public void character(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("which_theme",7);
        startActivity(intent);
    }

    public void professions(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("which_theme",8);
        startActivity(intent);
    }

    public void food(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("which_theme",9);
        startActivity(intent);
    }

    public void school(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("which_theme",10);
        startActivity(intent);
    }

    public void clothes(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("which_theme",11);
        startActivity(intent);
    }
    public void seasons(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("which_theme",12);
        startActivity(intent);
    }

    public void ALL(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("which_theme",0);
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(MainActivity.this,Start.class);
        finish();
        startActivity(intent);
    }
}