package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    int stars_for_house;
    String stars_for_house_string;
    int hint;
    private final static String FILE_NAME = "House_star_six.txt";
    private final static String FILE_NAME_hint = "House_hint_six.txt";


    @Override
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView place_of_stars_of_house = findViewById(R.id.stars_of_house);
        TextView place_of_stars_of_animals = findViewById(R.id.stars_of_animals);
        TextView place_of_stars_of_person = findViewById(R.id.stars_of_person);
        TextView place_of_stars_of_clothes = findViewById(R.id.stars_of_clothes);
        TextView place_of_stars_of_school = findViewById(R.id.stars_of_school);
        TextView place_of_stars_of_food = findViewById(R.id.stars_of_food);
        TextView place_of_stars_of_seasons = findViewById(R.id.stars_of_seasons);
        TextView place_of_stars_of_professions = findViewById(R.id.stars_of_professions);
        TextView place_of_stars_of_character = findViewById(R.id.stars_of_character);
        TextView place_of_stars_of_travel = findViewById(R.id.stars_of_travel);
        TextView place_of_stars_of_nature = findViewById(R.id.stars_of_nature);
        TextView place_of_stars_of_technique = findViewById(R.id.stars_of_technique);

        FileOutputStream fos;
        FileOutputStream fos1;

        FileInputStream fin = null;
        FileInputStream fin1 = null;

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            try {
                fin = openFileInput(FILE_NAME_hint);
                hint = fin.read();
                fin1 = openFileInput(FILE_NAME);
                stars_for_house = fin1.read();

            } catch (IOException e) {
                stars_for_house=-1;
                hint=-1;
                e.printStackTrace();
            } finally {
                try {
                    if (fin != null) {
                        fin.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            hint = extras.getInt("hint_for_house");
            stars_for_house = extras.getInt("stars_for_house");
        }

        try {
            fos = openFileOutput(FILE_NAME,MODE_PRIVATE);
            fos.write(stars_for_house);
            fos1 = openFileOutput(FILE_NAME_hint,MODE_PRIVATE);
            fos1.write(hint);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(stars_for_house==0){
            stars_for_house_string= "✰✰✰";
        }
        else if(stars_for_house==1){
            stars_for_house_string= " ★✰✰";
        }
        else if(stars_for_house==2){
            stars_for_house_string= " ★★✰";
        }
        else if(stars_for_house==3&hint==0){
            stars_for_house_string= " ★★★";
        }
        else if(stars_for_house==3&hint>0){
            stars_for_house_string="★★✰";
        }



        place_of_stars_of_house.setText(stars_for_house_string);

    }

    public void house(View view) {

        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("theme",1);
        startActivity(intent);
    }

    public void animals(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("theme",2);
        startActivity(intent);
    }

    public void person(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("theme",3);
        startActivity(intent);
    }

    public void technique(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("theme",4);
        startActivity(intent);
    }

    public void nature(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("theme",5);
        startActivity(intent);
    }

    public void travel(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("theme",6);
        startActivity(intent);
    }

    public void character(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("theme",7);
        startActivity(intent);
    }

    public void professions(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("theme",8);
        startActivity(intent);
    }

    public void food(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("theme",9);
        startActivity(intent);
    }

    public void school(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("theme",10);
        startActivity(intent);
    }

    public void clothes(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("theme",11);
        startActivity(intent);
    }
    public void seasons(View view) {
        Intent intent = new Intent(MainActivity.this, Themes.class);
        intent.putExtra("theme",12);
        startActivity(intent);
    }
}