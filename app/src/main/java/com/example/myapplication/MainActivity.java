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
    int hint,which_theme;

     static private String FILE_NAME ="aaaas.txt";
    static private String FILE_NAME_hint ="aasff.txt";




    @Override
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String stars[]= new String[]{"stars_of_housedp.txt","stars_of_animals.txt","stars_of_person.txt","stars_of_technique.txt","stars_of_nature.txt","stars_of_travel.txt","stars_of_character.txt","stars_of_professions.txt","stars_of_food.txt", "stars_of_school.txt","stars_of_clothes.txt","stars_of_seasons.txt"};
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
        Bundle extras = getIntent().getExtras();
        FileInputStream fin1 = null;FileInputStream fin2 = null;FileInputStream fin3 = null;FileInputStream fin4 = null;FileInputStream fin5 = null;FileInputStream fin6 = null;FileInputStream fin7 = null;FileInputStream fin8 = null;FileInputStream fin9 = null;FileInputStream fin10 = null;FileInputStream fin11 = null;FileInputStream fin12 = null;FileOutputStream fos1 = null;FileOutputStream fos2 = null;FileOutputStream fos3 = null;FileOutputStream fos4 = null;FileOutputStream fos5 = null;FileOutputStream fos6 = null;FileOutputStream fos7 = null;FileOutputStream fos8 = null;FileOutputStream fos9 = null;FileOutputStream fos10 = null;FileOutputStream fos11 = null;FileOutputStream fos12 = null;




        if (extras != null) {
            which_theme=extras.getInt("which_theme");
        }else{
            which_theme=-1;

        }
        switch (which_theme){
            case 1:
                FILE_NAME = "stars_of_housedp.txt";
                FILE_NAME_hint = "hints_of_house.txt";
                break;
            case 2:
                FILE_NAME = "stars_of_animals.txt";
                FILE_NAME_hint = "hints_of_animals.txt";
                break;
            case 3 :
                FILE_NAME = "stars_of_person.txt";
                FILE_NAME_hint = "hints_of_person.txt";
                break;
            case 4:
                FILE_NAME = "stars_of_technique.txt";
                FILE_NAME_hint = "hints_of_technique.txt";
                break;
            case 5:
                FILE_NAME = "stars_of_nature.txt";
                FILE_NAME_hint = "hints_of_nature.txt";
                break;
            case 6:
                FILE_NAME = "stars_of_travel.txt";
                FILE_NAME_hint = "hints_of_travel.txt";
                break;
            case 7:
                FILE_NAME = "stars_of_character.txt";
                FILE_NAME_hint = "hints_of_character.txt";
                break;
            case 8:
                FILE_NAME = "stars_of_professions.txt";
                FILE_NAME_hint = "hints_of_professions.txt";
                break;
            case 9:
                FILE_NAME = "stars_of_food.txt";
                FILE_NAME_hint = "hints_of_food.txt";
                break;
            case 10:
                FILE_NAME = "stars_of_school.txt";
                FILE_NAME_hint = "hints_of_school.txt";
                break;
            case 11:
                FILE_NAME = "stars_of_clothes.txt";
                FILE_NAME_hint = "hints_of_clothes.txt";
                break;
            case 12:
                FILE_NAME = "stars_of_seasons.txt";
                FILE_NAME_hint = "hints_of_seasons.txt";
                break;
        }
        FileOutputStream fos;
        FileInputStream fin = null;



        if(which_theme==-1) {
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