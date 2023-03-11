package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

public class Sentence extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_sentence);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        SharedPreferences savedData = getSharedPreferences("savedresult",MODE_PRIVATE);
        SharedPreferences.Editor editor = savedData.edit();
        i = savedData.getInt("x",1);
        Bundle extras = getIntent().getExtras();
        int which_theme = extras.getInt("which_theme");
        float DeviceTotalWidth = metrics.widthPixels;
        float DeviceTotalHeight = metrics.heightPixels;
        TextView x = findViewById(R.id.x);
        TextView y = findViewById(R.id.y);
        TextView slash = findViewById(R.id.slash);


        x.setTextSize(DeviceTotalWidth/50);
        y.setTextSize(DeviceTotalWidth/50);
        slash.setTextSize(DeviceTotalWidth/50);         TextView from_which = findViewById(R.id.from_which_is_translated);


        TextView first_option = findViewById(R.id.first_option);
        first_option.setHeight((int) (DeviceTotalHeight/10));

        TextView second_option = findViewById(R.id.second_option);
        second_option.setHeight((int) (DeviceTotalHeight/10));

        TextView third_option = findViewById(R.id.third_option);
        third_option.setHeight((int) (DeviceTotalHeight/10));

        TextView fourth_option = findViewById(R.id.fourth_option);
        fourth_option.setHeight((int) (DeviceTotalHeight/10));

        from_which.setHeight((int) (DeviceTotalHeight/10));
        InputStream fis =  getResources().openRawResource(R.raw.school);

        final Workbook[] wb = {null};
        try {
            wb[0] = new HSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Sheet[] sheet = new Sheet[1];

        sheet[0] = wb[0].getSheetAt(which_theme);
        int y_size= sheet[0].getLastRowNum()+1;
        y.setText(String.valueOf(y_size));
        x.setText(String.valueOf(i));

        ArrayList<Integer> repeated = new ArrayList<>(4);
        ArrayList<Integer> repeated1 = new ArrayList<>(y_size);

        final int[] row = {(int) (Math.random() * y_size)};
        repeated1.add(row[0]);
        final String[] eng_harc = {sheet[0].getRow(row[0]).getCell(0).getStringCellValue()};
        final String[] ru_harc = {sheet[0].getRow(row[0]).getCell(5).getStringCellValue()};
        int row1 = (int)(Math.random() * 4 );
        repeated.add(row1+1);
        final String[] fr_1 = {sheet[0].getRow(row[0]).getCell(row1+1).getStringCellValue()};
        row1 = (int)(Math.random() * 4 );
        while (true){
            if(repeated.contains(row1+1)){
                row1 = (int)(Math.random() * 4 );
            }
            else{
                repeated.add(row1+1);

                break;

            }
        }

        final String[] fr_2 = {sheet[0].getRow(row[0]).getCell(row1+1).getStringCellValue()};
        row1 = (int)(Math.random() * 4 );
        while (true){
            if(repeated.contains(row1+1)){
                row1 = (int)(Math.random() * 4 );
            }
            else{
                repeated.add(row1+1);

                break;
            }
        }
        final String[] fr_3 = {sheet[0].getRow(row[0]).getCell(row1+1).getStringCellValue()};
        row1 = (int)(Math.random() * 4 );
        while (true){
            if(repeated.contains(row1+1)){
                row1 = (int)(Math.random() * 4 );
            }
            else{
                repeated.add(row1+1);

                break;
            }
        }
        final String[] fr_4 = {sheet[0].getRow(row[0]).getCell(row1+1).getStringCellValue()};

        if(Locale.getDefault().getLanguage().equals("en")){
            from_which.setText(eng_harc[0]);

        }else{
            from_which.setText(ru_harc[0]);

        }
        first_option.setText(fr_1[0]);
        second_option.setText(fr_2[0]);
        third_option.setText(fr_3[0]);
        fourth_option.setText(fr_4[0]);

        Button next = findViewById(R.id.next);
        next.setHeight((int) (DeviceTotalHeight/15));
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values,null));
                second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values,null));
                first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values,null));
                third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values,null));

                if(i<y_size){
                    repeated.clear();
                    i++;
                    x.setText(String.valueOf(i));
                    int row = (int) (Math.random() * y_size);
                    while (true){
                        if(repeated1.contains(row)){
                            row = (int)(Math.random() * y_size );
                        }
                        else{
                            repeated1.add(row);

                            break;

                        }
                    }
                    final String[] eng_harc = {sheet[0].getRow(row).getCell(0).getStringCellValue()};
                    final String[] ru_harc = {sheet[0].getRow(row).getCell(5).getStringCellValue()};
                    int row1 = (int)(Math.random() * 4 );
                    repeated.add(row1+1);
                    final String[] fr_1 = {sheet[0].getRow(row).getCell(row1+1).getStringCellValue()};
                    row1 = (int)(Math.random() * 4 );
                    while (true){
                        if(repeated.contains(row1+1)){
                            row1 = (int)(Math.random() * 4 );
                        }
                        else{
                            repeated.add(row1+1);

                            break;

                        }
                    }

                    final String[] fr_2 = {sheet[0].getRow(row).getCell(row1+1).getStringCellValue()};
                    row1 = (int)(Math.random() * 4 );
                    while (true){
                        if(repeated.contains(row1+1)){
                            row1 = (int)(Math.random() * 4 );
                        }
                        else{
                            repeated.add(row1+1);

                            break;
                        }
                    }
                    final String[] fr_3 = {sheet[0].getRow(row).getCell(row1+1).getStringCellValue()};
                    row1 = (int)(Math.random() * 4 );
                    while (true){
                        if(repeated.contains(row1+1)){
                            row1 = (int)(Math.random() * 4 );
                        }
                        else{
                            repeated.add(row1+1);

                            break;
                        }
                    }
                    final String[] fr_4 = {sheet[0].getRow(row).getCell(row1+1).getStringCellValue()};

                    if(Locale.getDefault().getLanguage().equals("en")){
                        from_which.setText(eng_harc[0]);

                    }else{
                        from_which.setText(ru_harc[0]);

                    }
                    first_option.setText(fr_1[0]);
                    second_option.setText(fr_2[0]);
                    third_option.setText(fr_3[0]);
                    fourth_option.setText(fr_4[0]);

                    editor.putInt("x",i);
                    editor.apply();
                    int finalRow = row;
                    first_option.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onClick(View v) {

                            if(first_option.getText().toString().equals(sheet[0].getRow(finalRow).getCell(1).getStringCellValue())){
                                first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                            }
                            else{
                                first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values2,null));
                                if(second_option.getText().toString().equals(sheet[0].getRow(finalRow).getCell(1).getStringCellValue())){
                                    second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                                }
                                else if(third_option.getText().toString().equals(sheet[0].getRow(finalRow).getCell(1).getStringCellValue())){
                                    third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                                }
                                else {
                                    fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                                }

                            }
                        }
                    });
                    int finalRow1 = row;
                    second_option.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onClick(View v) {
                            if(second_option.getText().toString().equals(sheet[0].getRow(finalRow1).getCell(1).getStringCellValue())){
                                second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                            }
                            else{
                                second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values2,null));
                                if(first_option.getText().toString().equals(sheet[0].getRow(finalRow1).getCell(1).getStringCellValue())){
                                    first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                                }
                                else if(third_option.getText().toString().equals(sheet[0].getRow(finalRow1).getCell(1).getStringCellValue())){
                                    third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                                }
                                else {
                                    fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                                }

                            }
                        }
                    });
                    int finalRow2 = row;
                    third_option.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onClick(View v) {
                            if(third_option.getText().toString().equals(sheet[0].getRow(finalRow2).getCell(1).getStringCellValue())){
                                third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                            }
                            else{
                                third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values2,null));
                                if(second_option.getText().toString().equals(sheet[0].getRow(finalRow2).getCell(1).getStringCellValue())){
                                    second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                                }
                                else if(first_option.getText().toString().equals(sheet[0].getRow(finalRow2).getCell(1).getStringCellValue())){
                                    first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                                }
                                else {
                                    fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                                }

                            }
                        }
                    });
                    int finalRow3 = row;
                    fourth_option.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onClick(View v) {
                            if(fourth_option.getText().toString().equals(sheet[0].getRow(finalRow3).getCell(1).getStringCellValue())){
                                fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                            }
                            else{
                                fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values2,null));
                                if(second_option.getText().toString().equals(sheet[0].getRow(finalRow3).getCell(1).getStringCellValue())){
                                    second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                                }
                                else if(third_option.getText().toString().equals(sheet[0].getRow(finalRow3).getCell(1).getStringCellValue())){
                                    third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                                }
                                else {
                                    first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                                }


                            }




                        }
                    });
                }

                else{
                    Intent intent = new Intent(Sentence.this,Start.class);
                    startActivity(intent);
                }

            }
        });
        first_option.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                if(first_option.getText().toString().equals(sheet[0].getRow(row[0]).getCell(1).getStringCellValue())){
                    first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                }
                else{
                    first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values2,null));
                    if(second_option.getText().toString().equals(sheet[0].getRow(row[0]).getCell(1).getStringCellValue())){
                        second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                    }
                    else if(third_option.getText().toString().equals(sheet[0].getRow(row[0]).getCell(1).getStringCellValue())){
                        third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                    }
                    else {
                        fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                    }

                }
            }
        });
        second_option.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(second_option.getText().toString().equals(sheet[0].getRow(row[0]).getCell(1).getStringCellValue())){
                    second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                }
                else{
                    second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values2,null));
                    if(first_option.getText().toString().equals(sheet[0].getRow(row[0]).getCell(1).getStringCellValue())){
                        first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                    }
                    else if(third_option.getText().toString().equals(sheet[0].getRow(row[0]).getCell(1).getStringCellValue())){
                        third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                    }
                    else {
                        fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                    }

                }
            }
        });
        third_option.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(third_option.getText().toString().equals(sheet[0].getRow(row[0]).getCell(1).getStringCellValue())){
                    third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                }
                else{
                    third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values2,null));
                    if(second_option.getText().toString().equals(sheet[0].getRow(row[0]).getCell(1).getStringCellValue())){
                        second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                    }
                    else if(first_option.getText().toString().equals(sheet[0].getRow(row[0]).getCell(1).getStringCellValue())){
                        first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                    }
                    else {
                        fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                    }

                }
            }
        });
        fourth_option.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(fourth_option.getText().toString().equals(sheet[0].getRow(row[0]).getCell(1).getStringCellValue())){
                    fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                }
                else{
                    fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values2,null));
                    if(second_option.getText().toString().equals(sheet[0].getRow(row[0]).getCell(1).getStringCellValue())){
                        second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                    }
                    else if(third_option.getText().toString().equals(sheet[0].getRow(row[0]).getCell(1).getStringCellValue())){
                        third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                    }
                    else {
                        first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                    }


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
                Intent i = new Intent(Sentence.this,Start.class);
                finish();

                startActivity(i);
                return true;

            case R.id.restart:
                recreate();
                SharedPreferences savedData = getSharedPreferences("savedresult",MODE_PRIVATE);
                savedData.edit().clear().apply();
                return true;
            case R.id.change_language:
                if(Locale.getDefault().getLanguage().equals("en")){
                    setlocale("ru");
                    recreate();
                }else{
                    setlocale("en");
                    recreate();
                }

            default:
                return false;
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


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}