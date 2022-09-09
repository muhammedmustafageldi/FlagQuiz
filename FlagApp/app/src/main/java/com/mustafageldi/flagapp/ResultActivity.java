package com.mustafageldi.flagapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewResult,textViewPercent;
    private Button buttonAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewResult = findViewById(R.id.textViewResult);
        textViewPercent = findViewById(R.id.textViewPercent);
        buttonAgain = findViewById(R.id.buttonAgain);

        int trueCount = getIntent().getIntExtra("trueCount",0);

        textViewResult.setText("Doğru: "+trueCount + " Yanlış: "+(5-trueCount));
        int percent = (100*trueCount)/5;
        textViewPercent.setText("Success: %"+percent);


        buttonAgain.setOnClickListener(view -> {
            Intent intent = new Intent(ResultActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        });




    }
}