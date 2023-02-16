package com.example.myapplication;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
    int i = 0; int hint1 =0;int j=1;int row = 0;
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

        Bundle extras = getIntent().getExtras();
        int which_theme = extras.getInt("theme");

        x.setText(String.valueOf(row+1));
        InputStream fis =  getResources().openRawResource(R.raw.school);

        Workbook wb = null;
        try {
            wb = new HSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(which_theme - 1);

        y.setText(String.valueOf(sheet.getLastRowNum()+1));
        int y_size=sheet.getLastRowNum()+1;

        try {
            fis =  getResources().openRawResource(R.raw.school);

            wb = new HSSFWorkbook(fis);



        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> Random_words_eng = new ArrayList<>(y_size);
        int row = (int)(Math.random() * y_size );
        String eng = wb.getSheetAt(which_theme-1).getRow(row).getCell(0).getStringCellValue();
        String ru = wb.getSheetAt(which_theme-1).getRow(row).getCell(1).getStringCellValue();
        String fr = wb.getSheetAt(which_theme-1).getRow(row).getCell(2).getStringCellValue();
        text.setText(eng);
        Random_words_eng.add(row);
        Log.d("iskander", eng );
        Log.d("iskander", fr );
        Log.d("iskander", ru );
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Workbook wb = null;
                if (sheet.getRow(row).getCell(0).getStringCellValue() != null) {
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





                    String eng = wb.getSheetAt(which_theme - 1).getRow(row).getCell(0).getStringCellValue();
                    Log.d("iskander", eng);
                    String ru = wb.getSheetAt(which_theme - 1).getRow(row).getCell(1).getStringCellValue();
                    String fr = sheet.getRow(row).getCell(2).getStringCellValue();
                    text.setText(eng);
                    word_from_edittext = editText.getText().toString();
                    editText.setText("");
                    if (word_from_edittext.equals(fr)) {
                        i++;
                    }





                    j = 2;

                    assert wb != null;
                    Sheet sheet = wb.getSheetAt(which_theme - 1);
                    x.setText(String.valueOf(Random_words_eng.size()));
                    y.setText(String.valueOf(sheet.getLastRowNum()+1));
                    Workbook finalWb = wb;
                    int finalRow = row;
                    hint.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(Themes.this, finalWb.getSheetAt(which_theme - 1).getRow(finalRow).getCell(2).getStringCellValue(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    checked.setText("");
                    textView.setText("");

                } else {

                    x.setText(String.valueOf(row));
                    word_from_edittext = editText.getText().toString();

                    if (word_from_edittext.equals(wb.getSheetAt(which_theme - 1).getRow(row).getCell(2).getStringCellValue())) {
                        i++;
                    }
                    Intent intent = new Intent(Themes.this, Finish.class);
                    intent.putExtra("result", i);
                    intent.putExtra("which_theme", which_theme);
                    intent.putExtra("size", sheet.getLastRowNum());
                    intent.putExtra("hint", hint1);
                    startActivity(intent);
                    finish();
                    editText.setText("");


                }
                Workbook finalWb1 = wb;
                hint.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SuspiciousIndentation")
                    @Override
                    public void onClick(View v) {
                        textView.setText(finalWb1.getSheetAt(which_theme - 1).getRow(row).getCell(2).getStringCellValue());
                        if (j == 2)
                            hint1++;
                        j = 3;
                    }


                });

            }


        });
        Workbook finalWb = wb;
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText(finalWb.getSheetAt(which_theme - 1).getRow(row).getCell(2).getStringCellValue());
                if(j==1) {
                    hint1++;
                    j=2;
                }else{
                    j=2;
                }
            }
        });
        Workbook finalWb1 = wb;
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word_from_edittext=editText.getText().toString();
                if(word_from_edittext.equals(finalWb1.getSheetAt(which_theme - 1).getRow(row).getCell(2).getStringCellValue())){
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
                row=0;
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