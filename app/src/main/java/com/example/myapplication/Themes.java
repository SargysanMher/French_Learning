package com.example.myapplication;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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
    int i = 0; int hint1 =0;int j=1;int row;int y_size=0;int y_size1=0;int answer1;int char2;int n = 0;
    StringBuilder hint2;String letters;
    Button next,hint,check,answer;
    String word_from_edittext;
    ArrayList<String> Random_words_eng;

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
        final Sheet[] sheet = new Sheet[1];
        if(which_theme!=0){
            sheet[0] = wb[0].getSheetAt(which_theme - 1);
            y_size= sheet[0].getLastRowNum()+1;
        }else{
            for(int i = 0;i<12;i++){
                sheet[0] = wb[0].getSheetAt(i);
                y_size+= sheet[0].getLastRowNum()+1;
            }
            sheet[0] = wb[0].getSheetAt((int) (Math.random() * 12) );

        }

        y.setText(String.valueOf(y_size));


        Random_words_eng = new ArrayList<>(y_size);
        y_size1=y_size;

        y_size= sheet[0].getLastRowNum()+1;

        final int[] row = {(int) (Math.random() * y_size)};
        final String[] eng = {sheet[0].getRow(row[0]).getCell(0).getStringCellValue()};
        final String[] ru = {sheet[0].getRow(row[0]).getCell(1).getStringCellValue()};
        final String[] fr = {sheet[0].getRow(row[0]).getCell(2).getStringCellValue()};
        text.setText(eng[0]);
        Random_words_eng.add(eng[0]);
        Sheet finalSheet = sheet[0];
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j=1;
                n=1;
                InputStream fis =  getResources().openRawResource(R.raw.school);

                Workbook wb = null;
                    try {
                        wb = new HSSFWorkbook(fis);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert wb != null;
                if (Random_words_eng.size()!=y_size1) {
                    if(which_theme!=0){
                        sheet[0] = wb.getSheetAt(which_theme - 1);
                        y_size= sheet[0].getLastRowNum()+1;
                    }else{
                        sheet[0] = wb.getSheetAt((int) (Math.random() * 12) );
                        y_size= sheet[0].getLastRowNum()+1;

                    }
                    int row = (int)(Math.random() * y_size );
                    while(true){
                        eng[0] = sheet[0].getRow(row).getCell(0).getStringCellValue();

                        if (Random_words_eng.contains(eng[0])){
                            if(which_theme==0){
                                sheet[0] = wb.getSheetAt((int) (Math.random() * 12) );
                                y_size= sheet[0].getLastRowNum()+1;
                            }


                            row = (int)(Math.random() * y_size );

                        }else{
                            Random_words_eng.add(eng[0]);
                            break;
                        }
                    }
                    word_from_edittext=editText.getText().toString();

                    if (word_from_edittext.equals(fr[0])) {
                        i++;
                    }

                    fr[0] = sheet[0].getRow(row).getCell(2).getStringCellValue();
                    ru[0] = sheet[0].getRow(row).getCell(1).getStringCellValue();

                    text.setText(eng[0]);

                    editText.setText("");
                    x.setText(String.valueOf(Random_words_eng.size()));


                    int finalRow = row;
                    hint.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            hint2= new StringBuilder((sheet[0].getRow(finalRow).getCell(2).getStringCellValue()));
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
                          textView.setText(sheet[0].getRow(finalRow).getCell(2).getStringCellValue());
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
                            if(word_from_edittext.equals(sheet[0].getRow(finalRow1).getCell(2).getStringCellValue())){
                                checked.setText("✅");


                            }else{

                                checked.setText("❌");



                            }

                        }
                    });
                } else {

                    word_from_edittext = editText.getText().toString();
                    if (word_from_edittext.equals(eng[0])) {
                        i++;
                    }

                    Intent intent = new Intent(Themes.this, Finish.class);
                    intent.putExtra("result", i);
                    intent.putExtra("which_theme", which_theme);
                    intent.putExtra("size", y_size1);
                    intent.putExtra("hint", hint1);
                    intent.putExtra("answer", answer1);

                    startActivity(intent);
                    finish();



                }

            }


        });
        Sheet finalSheet1 = sheet[0];
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText(finalSheet1.getRow(row[0]).getCell(2).getStringCellValue());
                if(n==1) {
                    answer1++;
                }
                n=2;
            }
        });
        Sheet finalSheet2 = sheet[0];
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hint2= new StringBuilder((finalSheet2.getRow(row[0]).getCell(2).getStringCellValue()));
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
        Sheet finalSheet3 = sheet[0];
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word_from_edittext=editText.getText().toString();
                if(word_from_edittext.equals(finalSheet3.getRow(row[0]).getCell(2).getStringCellValue())){
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