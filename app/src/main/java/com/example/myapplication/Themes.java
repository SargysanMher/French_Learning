package com.example.myapplication;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.lang.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Themes extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    String french="";
    int i = 0; int hint1 =0;int j=1;int y_size=0;int y_size1=0;int answer1;int char2;int n = 1;
    StringBuilder hint2;String letters;
    int x_size;int random;int row;int gh = 0;
    Workbook wb;Sheet sheet;
    EditText editText;
    Button next,hint,check,answer;
    String word_from_edittext,fr,eng,ru;
    ArrayList<String> Random_words_eng;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_themes);
        SharedPreferences savedData = getSharedPreferences("savedresult2",MODE_PRIVATE);
        SharedPreferences.Editor editor = savedData.edit();
        final Set<String> H = new HashSet<>(savedData.getStringSet("Random_words",new HashSet<>()));
        Random_words_eng = new ArrayList<>(H);

        Log.d("asd", String.valueOf(Random_words_eng));

        x_size = savedData.getInt("x",1);
        hint = findViewById(R.id.hint);
        next = findViewById(R.id.next);
        check = findViewById(R.id.check);
        answer = findViewById(R.id.answer);
        TextView text = findViewById(R.id.text);
        i = savedData.getInt("result",0);
        TextView x = findViewById(R.id.x);
        DisplayMetrics metrics = getResources().getDisplayMetrics();

        int DeviceTotalWidth = metrics.widthPixels;
        int DeviceTotalHeight = metrics.heightPixels;
        TextView y = findViewById(R.id.y);
        LinearLayout linearLayout = findViewById(R.id.linear2);
        linearLayout.setBottom(DeviceTotalHeight/4);

        TextView slash = findViewById(R.id.slash);
        TextView checked = findViewById(R.id.checked);
        TextView textView = findViewById(R.id.textview);
        editText = findViewById(R.id.edittext);
        text.setTextSize(DeviceTotalWidth/40);
        editText.setMaxWidth(DeviceTotalWidth/20);
        editText.setWidth(DeviceTotalWidth/3);
        editText.setTextSize(DeviceTotalWidth/40);
        textView.setTextSize(DeviceTotalWidth/40);
        checked.setTextSize(DeviceTotalWidth/40);
        x.setTextSize(DeviceTotalWidth/50);
        y.setTextSize(DeviceTotalWidth/50);
        slash.setTextSize(DeviceTotalWidth/50);
        answer.setWidth((int) (DeviceTotalWidth/2.5));
        answer.setHeight(DeviceTotalHeight/20);
        check.setWidth((int) (DeviceTotalWidth/2.5));
        check.setHeight(DeviceTotalHeight/20);
        hint.setWidth((int) (DeviceTotalWidth/2.5));
        hint.setHeight(DeviceTotalHeight/20);
        next.setWidth((int) (DeviceTotalWidth/2.5));
        next.setHeight(DeviceTotalHeight/20);
        next.setMaxLines(1);
        Bundle extras = getIntent().getExtras();
        int which_theme = extras.getInt("which_theme");
        x.setText(String.valueOf(x_size));
        InputStream fis =  getResources().openRawResource(R.raw.words);

        try {
            wb = new HSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(which_theme!=0){
            sheet = wb.getSheetAt(which_theme - 1);
            y_size= sheet.getLastRowNum()+1;
        }else{
            random = savedData.getInt("rand1",savedData.getInt("rand",(int) (Math.random() * 12)));
            int hj = savedData.getInt("asd",0);
            Log.d("qwe", "onCreate: "+String.valueOf(hj));

            if(hj==0){
                for(int i = 0;i<12;i++){
                    sheet = wb.getSheetAt(i);
                    y_size+= sheet.getLastRowNum()+1;
                }
                sheet = wb.getSheetAt((int) (Math.random() * 12) );

                hj++;
                editor.putInt("asd",hj);
                editor.putInt("y",y_size);
                editor.putInt("rand",random);

                editor.apply();
            }
            Log.d("qwe", "onCreate: "+String.valueOf(random));

            sheet=wb.getSheetAt(random);
            y_size=savedData.getInt("y",y_size);

        }

        y.setText(String.valueOf(y_size));


        y_size1=y_size;

        y_size= sheet.getLastRowNum()+1;

        row = (int) (Math.random() * y_size);
        row=savedData.getInt("row",0);
        eng = sheet.getRow(row).getCell(0).getStringCellValue();
        ru = sheet.getRow(row).getCell(1).getStringCellValue();
        fr = sheet.getRow(row).getCell(2).getStringCellValue();
        if(Locale.getDefault().getLanguage().equals("en")){
            text.setText(eng);

        }else{
            text.setText(ru);
        }
         gh =savedData.getInt("gh", 0);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j=1;
                n=1;
                InputStream fis =  getResources().openRawResource(R.raw.words);

                Workbook wb = null;
                    try {
                        wb = new HSSFWorkbook(fis);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert wb != null;
                if (Random_words_eng.size()<y_size1) {
                    if(gh ==0){
                        Random_words_eng.add(eng);
                        gh++;
                        editor.putInt("gh", gh);
                    }
                    Log.d("asd", String.valueOf(Random_words_eng.size()));
                    if(which_theme!=0){
                        sheet = wb.getSheetAt(which_theme - 1);
                        y_size= sheet.getLastRowNum()+1;
                    }
                    else{
                        random=(int) (Math.random() * 12);
                        sheet = wb.getSheetAt( random);
                        editor.putInt("rand1",random);

                        y_size= sheet.getLastRowNum()+1;

                    }
                    int row = (int)(Math.random() * y_size );
                    while(true){
                        eng = sheet.getRow(row).getCell(0).getStringCellValue();

                        if (Random_words_eng.contains(eng)){
                            if(which_theme==0){
                                random=(int) (Math.random() * 12);
                                sheet = wb.getSheetAt( random);
                                editor.putInt("rand1",random);

                                y_size= sheet.getLastRowNum()+1;
                            }


                            row = (int)(Math.random() * y_size );

                        }else{
                            Random_words_eng.add(eng);
                            break;
                        }
                    }
                    word_from_edittext=editText.getText().toString().toLowerCase(Locale.ROOT).trim();
                    if (word_from_edittext.equals(fr.toLowerCase(Locale.ROOT))) {
                        i++;
                    }

                    fr = sheet.getRow(row).getCell(2).getStringCellValue();
                    ru = sheet.getRow(row).getCell(1).getStringCellValue();
                    if(Locale.getDefault().getLanguage().equals("en")){
                        text.setText(eng);

                    }else{
                        text.setText(ru);
                    }

                    editText.setText("");
                    x_size = Random_words_eng.size();
                    editor.putInt("result",i);
                    editor.putInt("x",x_size);
                    HashSet <String> hashSet = new HashSet<>(Random_words_eng);
                    editor.putStringSet("Random_words", hashSet);
                    editor.putInt("row",row);
                    editor.apply();
                    x.setText(String.valueOf(x_size));


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
                            word_from_edittext=editText.getText().toString().toLowerCase(Locale.ROOT).trim();
                            checked.setVisibility(View.VISIBLE);

                            if(word_from_edittext.equals(sheet.getRow(finalRow1).getCell(2).getStringCellValue().toLowerCase(Locale.ROOT))){
                                checked.setText("✅");


                            }else{

                                checked.setText("❌");



                            }

                        }
                    });
                } else {

                    word_from_edittext = editText.getText().toString().toLowerCase(Locale.ROOT).trim();
                    if (word_from_edittext.equals(fr.toLowerCase(Locale.ROOT))) {
                        i++;
                    }
                    savedData.edit().clear().apply();

                    Intent intent = new Intent(Themes.this, Finish.class);
                    intent.putExtra("result", i);
                    intent.putExtra("which_theme", which_theme);
                    intent.putExtra("size", y_size1);
                    intent.putExtra("hint", hint1);
                    intent.putExtra("answer", answer1);
                    finish();

                    startActivity(intent);



                }

            }


        });
        Sheet finalSheet1 = sheet;
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText(finalSheet1.getRow(row).getCell(2).getStringCellValue());
                if(n==1) {
                    answer1++;
                }
                n=2;
            }
        });
        Sheet finalSheet2 = sheet;
        editText.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // вызывается после изменения текста
                checked.setVisibility(View.INVISIBLE);
            }
        });
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hint2= new StringBuilder((finalSheet2.getRow(row).getCell(2).getStringCellValue()));
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
        Sheet finalSheet3 = sheet;
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word_from_edittext=editText.getText().toString().toLowerCase(Locale.ROOT).trim();
                checked.setVisibility(View.VISIBLE);
                if(word_from_edittext.equals(finalSheet3.getRow(row).getCell(2).getStringCellValue().toLowerCase(Locale.ROOT))){
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

                Intent intent = new Intent(Themes.this,MainActivity.class);
                if(i>=1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Themes.this);
                    builder.setCancelable(true);
                    builder.setTitle(R.string.sure);
                    builder.setMessage(R.string.message);
                    builder.setPositiveButton(R.string.back_to_main_menu, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.fa.finish();
                            finish();
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
                }else{
                    MainActivity.fa.finish();
                    finish();
                    startActivity(intent);
                }

                return true;

            case R.id.restart:
                recreate();
                SharedPreferences savedData = getSharedPreferences("savedresult2",MODE_PRIVATE);
                savedData.edit().clear().apply();
                editText.setText("");
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


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
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
    public void onBackPressed() {
        if(i>=1){
            AlertDialog.Builder builder = new AlertDialog.Builder(Themes.this);
            builder.setCancelable(true);
            builder.setTitle(R.string.sure);
            builder.setMessage(R.string.message);
            builder.setPositiveButton(R.string.back_to_main_menu, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Themes.super.onBackPressed();

                }
            });
            builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.show();
        }else{
            Intent intent = new Intent(Themes.this,MainActivity.class);
            if(i>=1){
                AlertDialog.Builder builder = new AlertDialog.Builder(Themes.this);
                builder.setCancelable(true);
                builder.setTitle(R.string.sure);
                builder.setMessage(R.string.message);
                builder.setPositiveButton(R.string.back_to_main_menu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.fa.finish();
                        finish();
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }else{
                MainActivity.fa.finish();
                finish();
                startActivity(intent);
            }
        }
    }
}