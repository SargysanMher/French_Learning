package com.example.myapplication;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.lang.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class Themes extends AppCompatActivity {
    String french="";
    int i = 0; int hint1 =0;int j=1;
    Button next,hint,check;
    String word_from_edittext;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        hint = findViewById(R.id.hint);
        next = findViewById(R.id.next);
        check = findViewById(R.id.check);
        TextView text = findViewById(R.id.text);
        TextView x = findViewById(R.id.x);
        TextView y = findViewById(R.id.y);
        TextView checked = findViewById(R.id.checked);
        TextView textView = findViewById(R.id.textview);
        EditText editText = findViewById(R.id.edittext);
        try {
            InputStream fis =  getResources().openRawResource(R.raw.school);

            Workbook wb = new HSSFWorkbook(fis);
            String r = wb.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
            Log.d("iskander", r );
        } catch (FileNotFoundException e) {
            Log.d("iskander", "che blyat chexav " + e);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bundle extras = getIntent().getExtras();
        int which_theme = extras.getInt("theme");
        Map<String,String> map = null;

        ///////////////////////////////////////////
        Map<String,String> House = new HashMap<>();
        House.put("house","une maison");
        House.put("door","une porte");
        House.put("bedroom","une chambre");
        House.put("bathroom","une salle de bain");
        House.put("table","une table");
        if(which_theme==1){
            map = new HashMap<>(House);
        }
        ///////////////////////////////////////////
        Map<String,String> Animals = new HashMap<>();
        Animals.put("animals","des animaux");
        Animals.put("cow","une vache");
        Animals.put("goat(f)","une chèvre");
        Animals.put("giraffe","une girafe");
        Animals.put("cat(m)","un chat");
        if(which_theme==2) {
            map = new HashMap<>(Animals);
        }
        ///////////////////////////////////////////
        Map<String,String> Person = new HashMap<>();
        Person.put("person","une personne");
        Person.put("head","une tête");
        Person.put("hand","une main");
        Person.put("girl","une fille");
        Person.put("boy","un garçon");
        if(which_theme==3) {
            map = new HashMap<>(Person);
        }
        ///////////////////////////////////////////
        Map<String,String> Clothes = new HashMap<>();
        Clothes.put("clothes","des vêtements");
        Clothes.put("trouser","un pantalon");
        Clothes.put("skirt","une jupe");
        Clothes.put("the tailor","un tailleur");
        Clothes.put("gylet","un gylet");
        if(which_theme==11) {
            map = new HashMap<>(Clothes);
        }
        ///////////////////////////////////////////
        Map<String,String> School = new HashMap<>();
        School.put("school","une école");
        School.put("pencil","un crayon");
        School.put("pen","un stylo");
        School.put("book","un livre");
        School.put("cafeteria","une cantine");
        if(which_theme==10) {
            map = new HashMap<>(School);
        }
        ///////////////////////////////////////////
        Map<String,String> Food = new HashMap<>();
        Food.put("food","une nourriture");
        Food.put("apple","une pomme");
        Food.put("banana","une banane");
        Food.put("carrot","une carotte");
        Food.put("strawberry","une fraise");
        if(which_theme==9) {
            map = new HashMap<>(Food);
        }
        ///////////////////////////////////////////
        Map<String,String> Seasons = new HashMap<>();
        Seasons.put("seasons","дом");
        Seasons.put("pencil","карандаш");
        Seasons.put("pen","ручка");
        Seasons.put("book","книга");
        Seasons.put("boy","парень");
        if(which_theme==12) {
            map = new HashMap<>(Seasons);
        }
        ///////////////////////////////////////////
        Map<String,String> Professions = new HashMap<>();
        Professions.put("professions","дом");
        Professions.put("pencil","карандаш");
        Professions.put("pen","ручка");
        Professions.put("book","книга");
        Professions.put("boy","парень");
        if(which_theme==8) {
            map = new HashMap<>(Professions);
        }
        ///////////////////////////////////////////
        Map<String,String> Character = new HashMap<>();
        Character.put("character","дом");
        Character.put("pencil","карандаш");
        Character.put("pen","ручка");
        Character.put("book","книга");
        Character.put("boy","парень");
        if(which_theme==7) {
            map = new HashMap<>(Character);
        }
        ///////////////////////////////////////////
        Map<String,String> Travelling = new HashMap<>();
        Travelling.put("travelling","дом");
        Travelling.put("pencil","карандаш");
        Travelling.put("pen","ручка");
        Travelling.put("book","книга");
        Travelling.put("boy","парень");
        if(which_theme==6) {
            map = new HashMap<>(Travelling);
        }
        ///////////////////////////////////////////
        Map<String,String> Nature = new HashMap<>();
        Nature.put("nature","дом");
        Nature.put("pencil","карандаш");
        Nature.put("pen","ручка");
        Nature.put("book","книга");
        Nature.put("boy","парень");
        if(which_theme==5) {
            map = new HashMap<>(Nature);
        }
        ///////////////////////////////////////////
        Map<String,String> Technique = new HashMap<>();
        Technique.put("technique","дом");
        Technique.put("pencil","карандаш");
        Technique.put("pen","ручка");
        Technique.put("book","книга");
        Technique.put("boy","парень");
        if(which_theme==4) {
            map = new HashMap<>(Technique);
        }
        ///////////////////////////////////////////





        Iterator<Map.Entry<String, String>> itr = map.entrySet().iterator();
        Map.Entry<String,String> entry=itr.next();
        String eng = entry.getKey();
        final String[] french = {entry.getValue()};

        text.setText(eng);
        x.setText(String.valueOf(House.size()-map.size()));
        y.setText(String.valueOf(House.size()));
        itr.remove();


        Map<String, String> finalMap = map;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(itr.hasNext()){
                    Map.Entry<String,String> entry=itr.next();
                    String eng = entry.getKey();
                    word_from_edittext=editText.getText().toString();
                    editText.setText("");
                    if(word_from_edittext.equals(french[0])){
                        i++;
                    }
                    french[0] = entry.getValue();

                    j=2;


                    text.setText(eng);
                    x.setText(String.valueOf(House.size()- finalMap.size()));
                    y.setText(String.valueOf(House.size()));
                    itr.remove();
                    hint.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("ad", french[0]);
                            Toast.makeText(Themes.this, french[0], Toast.LENGTH_SHORT).show();
                        }
                    });
                    checked.setText("");
                    textView.setText("");

                }

                else{

                    x.setText(String.valueOf(House.size()- finalMap.size()));
                    word_from_edittext=editText.getText().toString();

                    if(word_from_edittext.equals(french[0])){
                        i++;
                    }
                    Intent intent = new Intent(Themes.this,Finish.class);
                    intent.putExtra("result",i);
                    intent.putExtra("which_theme",which_theme);
                    intent.putExtra("size",House.size());
                    intent.putExtra("hint", hint1);
                    startActivity(intent);
                    finish();
                        editText.setText("");


                }
                hint.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SuspiciousIndentation")
                    @Override
                    public void onClick(View v) {
                        textView.setText(french[0]);
                        if(j==2)
                            hint1++;
                            j=3;
                        }


                });

            }


        });
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(french[0]);
                if(j==1) {
                    hint1++;
                    j=2;
                }else{
                    j=2;
                }
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word_from_edittext=editText.getText().toString();
                if(word_from_edittext.equals(french[0])){
                    checked.setText("✅");


                }else{

                    checked.setText("❌");



                }

            }
        });






    }




}