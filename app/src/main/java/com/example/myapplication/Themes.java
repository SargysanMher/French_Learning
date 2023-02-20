package com.example.myapplication;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.lang.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Themes extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    String french="";
    int i = 0; int hint1 =0;int j=1;int row;int y_size=0;int answer1;int char2;int n = 0;
    StringBuilder hint2;String letters;
    Button next,hint,check,answer;
    String word_from_edittext;
    ArrayList<Integer> Random_words_eng;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        hint = findViewById(R.id.hint);
        next = findViewById(R.id.next);
        check = findViewById(R.id.check);
        answer = findViewById(R.id.answer);
        TextView text = findViewById(R.id.text);
        TextView x = findViewById(R.id.x);
        DisplayMetrics metrics = getResources().getDisplayMetrics();

        int DeviceTotalWidth = metrics.widthPixels;
        int DeviceTotalHeight = metrics.heightPixels;
        TextView y = findViewById(R.id.y);
        TextView slash = findViewById(R.id.slash);
        TextView checked = findViewById(R.id.checked);
        TextView textView = findViewById(R.id.textview);
        EditText editText = findViewById(R.id.edittext);
        text.setTextSize(DeviceTotalWidth/40);
        editText.setMaxWidth(DeviceTotalWidth/20);
        editText.setWidth(DeviceTotalWidth/3);
        editText.setTextSize(DeviceTotalWidth/40);
        textView.setTextSize(DeviceTotalWidth/40);
        checked.setTextSize(DeviceTotalWidth/40);
        x.setTextSize(DeviceTotalWidth/50);
        y.setTextSize(DeviceTotalWidth/50);
        slash.setTextSize(DeviceTotalWidth/50);
        answer.setWidth(DeviceTotalWidth/5);
        answer.setHeight(DeviceTotalHeight/20);
        check.setWidth(DeviceTotalWidth/5);
        check.setHeight(DeviceTotalHeight/20);
        hint.setWidth(DeviceTotalWidth/5);
        hint.setHeight(DeviceTotalHeight/20);
        next.setWidth(DeviceTotalWidth/5);
        next.setHeight(DeviceTotalHeight/20);
        Bundle extras = getIntent().getExtras();
        int which_theme = extras.getInt("theme");

        x.setText(String.valueOf(1));
        InputStream fis =  getResources().openRawResource(R.raw.school);

        final Workbook[] wb = {null};
        try {
            wb[0] = new HSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb[0].getSheetAt(which_theme - 1);

        y.setText(String.valueOf(sheet.getLastRowNum()+1));
        y_size=sheet.getLastRowNum()+1;

        try {
            fis =  getResources().openRawResource(R.raw.school);
            wb[0] = new HSSFWorkbook(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Random_words_eng = new ArrayList<>(y_size);
        final int[] row = {(int) (Math.random() * y_size)};
        final String[] eng = {wb[0].getSheetAt(which_theme - 1).getRow(row[0]).getCell(0).getStringCellValue()};
        final String[] ru = {wb[0].getSheetAt(which_theme - 1).getRow(row[0]).getCell(1).getStringCellValue()};
        final String[] fr = {wb[0].getSheetAt(which_theme - 1).getRow(row[0]).getCell(2).getStringCellValue()};
        text.setText(eng[0]);
        Random_words_eng.add(row[0]);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j=1;
                Workbook wb = null;
                if (Random_words_eng.size()!=y_size) {
                    InputStream fis = getResources().openRawResource(R.raw.school);

                    wb = null;
                    try {
                        wb = new HSSFWorkbook(fis);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert wb != null;
                    int row = (int)(Math.random() * y_size );
                    while(true){
                        if (Random_words_eng.contains(row)){
                            row = (int)(Math.random() * y_size );
                        }else{
                            Random_words_eng.add(row);
                            break;
                        }
                    }


                    word_from_edittext=editText.getText().toString();

                    if (word_from_edittext.equals(fr[0])) {
                        i++;
                    }
                    eng[0] = sheet.getRow(row).getCell(0).getStringCellValue();
                    ru[0] = sheet.getRow(row).getCell(1).getStringCellValue();
                    fr[0] = sheet.getRow(row).getCell(2).getStringCellValue();
                    text.setText(eng[0]);
                    editText.setText("");








                    x.setText(String.valueOf(Random_words_eng.size()));
                    y.setText(String.valueOf(sheet.getLastRowNum()+1));
                    int finalRow = row;
                    hint.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            hint2= new StringBuilder((sheet.getRow(finalRow).getCell(2).getStringCellValue()));
                            char2 = (int)(Math.random() * hint2.length());
                            letters= String.valueOf(hint2.charAt(char2));
                            for(int i = 0;i<(hint2.length()-3)/3;){
                                if(hint2.charAt(char2)!=' '& char2!=0& char2!=1& char2!=2){
                                    hint2.setCharAt(char2, '?');
                                    char2 = (int)(Math.random() * hint2.length());
                                    letters= String.valueOf(hint2.charAt(char2));

                                    i++;
                                }
                                else{
                                    char2 = (int)(Math.random() * hint2.length());
                                }
                            }
                            if(j==1) {
                                textView.setText(hint2);
                                hint1++;
                            }
                            j=2;
                        }
                    });
                    answer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                          textView.setText(sheet.getRow(finalRow).getCell(2).getStringCellValue());
                          if(n==1){
                              answer1++;
                              n=2;
                          }
                        }
                    });
                    checked.setText("");
                    textView.setText("");
                    int finalRow1 = row;
                    check.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            word_from_edittext=editText.getText().toString();
                            if(word_from_edittext.equals(sheet.getRow(finalRow1).getCell(2).getStringCellValue())){
                                checked.setText("✅");


                            }else{

                                checked.setText("❌");



                            }

                        }
                    });
                } else {

                    word_from_edittext = editText.getText().toString();
                    row[0] = Random_words_eng.get(Random_words_eng.size()-1);
                    if (word_from_edittext.equals(sheet.getRow(row[0]).getCell(2).getStringCellValue())) {
                        i++;
                    }

                    Intent intent = new Intent(Themes.this, Finish.class);
                    intent.putExtra("result", i);
                    intent.putExtra("which_theme", which_theme);
                    intent.putExtra("size", sheet.getLastRowNum()+1);
                    intent.putExtra("hint", hint1);
                    intent.putExtra("answer", answer1);

                    startActivity(intent);
                    finish();



                }

            }


        });
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText(sheet.getRow(row[0]).getCell(2).getStringCellValue());
                if(j==n) {
                    answer1++;
                }
                n=2;
            }
        });
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hint2= new StringBuilder((sheet.getRow(row[0]).getCell(2).getStringCellValue()));
                char2 = (int)(Math.random() * hint2.length());
                letters= String.valueOf(hint2.charAt(char2));
                for(int i = 0;i<(hint2.length()-3)/3;){
                    if(hint2.charAt(char2)!=' '& char2!=0& char2!=1& char2!=2){
                        hint2.setCharAt(char2, '?');
                        char2 = (int)(Math.random() * hint2.length());
                        letters= String.valueOf(hint2.charAt(char2));

                        i++;
                    }
                    else{
                        char2 = (int)(Math.random() * hint2.length());
                    }
                }

                if(j==1) {
                    textView.setText(hint2);
                    hint1++;
                }
                j=2;
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word_from_edittext=editText.getText().toString();
                if(word_from_edittext.equals(sheet.getRow(row[0]).getCell(2).getStringCellValue())){
                    checked.setText("✅");


                }else{

                    checked.setText("❌");



                }

            }
        });






    }

    public void showPopup(View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup);
        popupMenu.show();
    }



    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()){
            case R.id.back:
                Intent i = new Intent(Themes.this,MainActivity.class);
                startActivity(i);
                finish();
                return true;

            case R.id.restart:
                Random_words_eng.clear();
                Button next = this.next;
                next.callOnClick();
                return true;
            default:
                return false;
        }

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

}