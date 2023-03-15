package com.example.myapplication;

import androidx.annotation.LongDef;
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
import android.widget.Switch;
import android.widget.TextView;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Sentence extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    int i = 1;String fr_2;
    int right_answers=0;String fr_3;String fr_4;
    String eng_harc;String fr_1;
    int row;
    Workbook wb;Sheet sheet;String ru_harc;
    int y_size=0;
    int clicked = 0;
    ArrayList<String> repeated1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_sentence);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        SharedPreferences savedData = getSharedPreferences("savedresult1",MODE_PRIVATE);
        SharedPreferences.Editor editor = savedData.edit();
        final Set<String> H = new HashSet<>(savedData.getStringSet("Random_words1", Collections.singleton("5")));
        repeated1 = new ArrayList<String>(H);
        i = savedData.getInt("x",1);
        Bundle extras = getIntent().getExtras();
        int which_theme = extras.getInt("which_theme");
        float DeviceTotalWidth = metrics.widthPixels;
        float DeviceTotalHeight = metrics.heightPixels;
        TextView x = findViewById(R.id.x);
        TextView y = findViewById(R.id.y);
        TextView slash = findViewById(R.id.slash);
        Log.d("asd", "onCreate: "+String.valueOf(which_theme));

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

        try {
            wb = new HSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = wb.getSheetAt(which_theme);
        y_size= sheet.getLastRowNum()+1;
        y.setText(String.valueOf(y_size));
        x.setText(String.valueOf(i));

        ArrayList<Integer> repeated = new ArrayList<>(4);

        row = savedData.getInt("row4",(int) (Math.random() * y_size));
        editor.putInt("row4",row);
        editor.apply();
        eng_harc= sheet.getRow(row).getCell(0).getStringCellValue();
        ru_harc = sheet.getRow(row).getCell(5).getStringCellValue();
        int row1 = (int)(Math.random() * 4 );
        repeated.add(row1+1);
        fr_1 = sheet.getRow(row).getCell(row1+1).getStringCellValue();
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

        fr_2 = sheet.getRow(row).getCell(row1+1).getStringCellValue();
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
        fr_3 = sheet.getRow(row).getCell(row1+1).getStringCellValue();
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
        fr_4 = sheet.getRow(row).getCell(row1+1).getStringCellValue();

        if(Locale.getDefault().getLanguage().equals("en")){
            from_which.setText(eng_harc);

        }else{
            from_which.setText(ru_harc);

        }
        first_option.setText(fr_1);
        second_option.setText(fr_2);
        third_option.setText(fr_3);
        fourth_option.setText(fr_4);

        Button next = findViewById(R.id.next);
        next.setHeight((int) (DeviceTotalHeight/15));
        repeated1.add(String.valueOf(row));

        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values,null));
                second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values,null));
                first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values,null));
                third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values,null));
                clicked=0;
                if(i<y_size){

                    repeated.clear();
                    i++;
                    x.setText(String.valueOf(i));
                    int row = (int) (Math.random() * y_size);
                    while (true){
                        if(repeated1.contains(String.valueOf(row))){
                            row = (int)(Math.random() * y_size );
                        }
                        else{
                            repeated1.add(String.valueOf(row));

                            break;

                        }
                    }
                    eng_harc = sheet.getRow(row).getCell(0).getStringCellValue();
                     ru_harc = sheet.getRow(row).getCell(5).getStringCellValue();
                    int row1 = (int)(Math.random() * 4 );
                    repeated.add(row1+1);
                     fr_1 = sheet.getRow(row).getCell(row1+1).getStringCellValue();
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

                     fr_2 = sheet.getRow(row).getCell(row1+1).getStringCellValue();
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
                     fr_3 = sheet.getRow(row).getCell(row1+1).getStringCellValue();
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
                     fr_4 = sheet.getRow(row).getCell(row1+1).getStringCellValue();

                    if(Locale.getDefault().getLanguage().equals("en")){
                        from_which.setText(eng_harc);

                    }else{
                        from_which.setText(ru_harc);

                    }
                    first_option.setText(fr_1);
                    second_option.setText(fr_2);
                    third_option.setText(fr_3);
                    fourth_option.setText(fr_4);

                    editor.putInt("x",i);
                    editor.putInt("row4",row);
                    int finalRow = row;
                    HashSet<String> hashSet = new HashSet<>(repeated1);
                    Log.d("asd", String.valueOf(hashSet));

                    editor.putStringSet("Random_words1", hashSet);
                    editor.apply();

                    first_option.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onClick(View v) {
                            if(clicked==0){
                                if(first_option.getText().toString().equals(sheet.getRow(finalRow).getCell(1).getStringCellValue())){
                                    first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                                    right_answers++;
                                }
                                else{
                                    first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values2,null));
                                    if(second_option.getText().toString().equals(sheet.getRow(finalRow).getCell(1).getStringCellValue())){
                                        second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                                    }
                                    else if(third_option.getText().toString().equals(sheet.getRow(finalRow).getCell(1).getStringCellValue())){
                                        third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                                    }
                                    else {
                                        fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                                    }

                                }
                                clicked++;
                            }

                        }
                    });
                    int finalRow1 = row;
                    second_option.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onClick(View v) {
                            if(clicked==0){
                                if(second_option.getText().toString().equals(sheet.getRow(finalRow1).getCell(1).getStringCellValue())){
                                    second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                                    right_answers++;
                                }
                                else{
                                    second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values2,null));
                                    if(first_option.getText().toString().equals(sheet.getRow(finalRow1).getCell(1).getStringCellValue())){
                                        first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                                    }
                                    else if(third_option.getText().toString().equals(sheet.getRow(finalRow1).getCell(1).getStringCellValue())){
                                        third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                                    }
                                    else {
                                        fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                                    }

                                }
                                clicked++;
                            }

                        }
                    });
                    int finalRow2 = row;
                    third_option.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onClick(View v) {
                            if(clicked==0){
                                if(third_option.getText().toString().equals(sheet.getRow(finalRow2).getCell(1).getStringCellValue())){
                                    third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                                    right_answers++;
                                }
                                else{
                                    third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values2,null));
                                    if(second_option.getText().toString().equals(sheet.getRow(finalRow2).getCell(1).getStringCellValue())){
                                        second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                                    }
                                    else if(first_option.getText().toString().equals(sheet.getRow(finalRow2).getCell(1).getStringCellValue())){
                                        first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                                    }
                                    else {
                                        fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                                    }

                                }
                                clicked++;
                            }

                        }
                    });
                    int finalRow3 = row;
                    fourth_option.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onClick(View v) {
                            if(clicked==0){
                                if(fourth_option.getText().toString().equals(sheet.getRow(finalRow3).getCell(1).getStringCellValue())){
                                    fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                                    right_answers++;
                                }
                                else{
                                    fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values2,null));
                                    if(second_option.getText().toString().equals(sheet.getRow(finalRow3).getCell(1).getStringCellValue())){
                                        second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                                    }
                                    else if(third_option.getText().toString().equals(sheet.getRow(finalRow3).getCell(1).getStringCellValue())){
                                        third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                                    }
                                    else {
                                        first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                                    }


                                }
                                clicked++;
                            }





                        }
                    });
                }

                else{
                    savedData.edit().clear().apply();
                    Intent intent = new Intent(Sentence.this,Finish.class);
                    switch (which_theme){
                        case 12:
                            intent.putExtra("which_theme",12);
                            break;
                        case 13:
                            intent.putExtra("which_theme",13);
                            break;
                        case 14:
                            intent.putExtra("which_theme",14);
                            break;
                        case 15:
                            intent.putExtra("which_theme",15);
                            break;
                    }
                    intent.putExtra("result",right_answers);
                    intent.putExtra("size",y_size);
                    finish();
                    startActivity(intent);
                }

            }
        });
        first_option.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(clicked==0){
                    if(first_option.getText().toString().equals(sheet.getRow(row).getCell(1).getStringCellValue())){
                        first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                        right_answers++;
                    }
                    else{
                        first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values2,null));
                        if(second_option.getText().toString().equals(sheet.getRow(row).getCell(1).getStringCellValue())){
                            second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                        }
                        else if(third_option.getText().toString().equals(sheet.getRow(row).getCell(1).getStringCellValue())){
                            third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                        }
                        else {
                            fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                        }

                    }
                    clicked++;
                }

            }
        });
        second_option.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(clicked==0){
                    if(second_option.getText().toString().equals(sheet.getRow(row).getCell(1).getStringCellValue())){
                        second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                        right_answers++;
                    }
                    else{
                        second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values2,null));
                        if(first_option.getText().toString().equals(sheet.getRow(row).getCell(1).getStringCellValue())){
                            first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                        }
                        else if(third_option.getText().toString().equals(sheet.getRow(row).getCell(1).getStringCellValue())){
                            third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                        }
                        else {
                            fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                        }

                    }
                    clicked++;
                }

            }
        });
        third_option.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(clicked==0){
                    if(third_option.getText().toString().equals(sheet.getRow(row).getCell(1).getStringCellValue())){
                        third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                        right_answers++;
                    }
                    else{
                        third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values2,null));
                        if(second_option.getText().toString().equals(sheet.getRow(row).getCell(1).getStringCellValue())){
                            second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                        }
                        else if(first_option.getText().toString().equals(sheet.getRow(row).getCell(1).getStringCellValue())){
                            first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                        }
                        else {
                            fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                        }

                    }
                    clicked++;
                }

            }
        });
        fourth_option.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(clicked==0){
                    if(fourth_option.getText().toString().equals(sheet.getRow(row).getCell(1).getStringCellValue())){
                        fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                        right_answers++;
                    }
                    else{
                        fourth_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values2,null));
                        if(second_option.getText().toString().equals(sheet.getRow(row).getCell(1).getStringCellValue())){
                            second_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));
                        }
                        else if(third_option.getText().toString().equals(sheet.getRow(row).getCell(1).getStringCellValue())){
                            third_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                        }
                        else {
                            first_option.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.values1,null));

                        }


                    }
                    clicked++;
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
                SharedPreferences savedData = getSharedPreferences("savedresult1",MODE_PRIVATE);
                savedData.edit().clear().apply();
                recreate();
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