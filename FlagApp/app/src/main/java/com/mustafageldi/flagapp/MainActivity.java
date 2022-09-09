package com.mustafageldi.flagapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseCopy();
        buttonStart = findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(),QuizActivity.class);
            startActivity(intent);
        });

    }

    public void databaseCopy(){
        DatabaseCopyHelper databaseCopyHelper = new DatabaseCopyHelper(MainActivity.this);
        try {
            databaseCopyHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        databaseCopyHelper.openDataBase();
    }
}