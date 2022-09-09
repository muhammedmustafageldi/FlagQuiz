package com.mustafageldi.flagapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    private TextView trueTextView,falseTextView,textViewQuestion;
    private Button buttonA, buttonB, buttonC, buttonD;
    private ImageView imageViewFlag;
    private ArrayList<Flags> trueAnswers = new ArrayList<>();
    private FlagDataBaseHelper helper;
    private ArrayList<Flags> falseAnswers = new ArrayList<>();
    private int count = -1;
    private Flags trueFlag;
    private HashSet<Flags> mixList = new HashSet<>();
    private ArrayList<Flags> okayList = new ArrayList<>();
    private int trueCount = 0;
    private int falseCount = 0;
    private MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        trueTextView = findViewById(R.id.trueTextView);
        falseTextView = findViewById(R.id.falseTextView);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);
        imageViewFlag = findViewById(R.id.imageViewFlag);


        buttonA.setOnClickListener(view -> {
            trueOrFalse(buttonA);
            setDesign();
        });

        buttonB.setOnClickListener(view -> {
            trueOrFalse(buttonB);
            setDesign();
        });

        buttonC.setOnClickListener(view -> {
            trueOrFalse(buttonC);
            setDesign();
        });

        buttonD.setOnClickListener(view -> {
            trueOrFalse(buttonD);
            setDesign();
        });


        helper = new FlagDataBaseHelper(this);
        trueAnswers = new FlagsDao().random5get(helper);

        setDesign();
    }


    public void setDesign(){
        count ++;

        System.out.println(count);

        trueFlag = new Flags(trueAnswers.get(count).getFlagId(), trueAnswers.get(count).getFlagName(), trueAnswers.get(count).getFlagImageName());
        falseAnswers = new FlagsDao().false3get(helper,trueFlag.getFlagId());

        textViewQuestion.setText("Soru: "+(count+1));
        imageViewFlag.setImageResource(getResources().getIdentifier(trueFlag.getFlagImageName(),"drawable",getPackageName()));

        //MIX LIST
        mixList.clear();
        mixList.add(trueFlag);
        mixList.add(falseAnswers.get(0));
        mixList.add(falseAnswers.get(1));
        mixList.add(falseAnswers.get(2));

        okayList.clear();

        for (Flags f : mixList){
            okayList.add(f);
        }



        buttonA.setText(okayList.get(0).getFlagName());
        buttonB.setText(okayList.get(1).getFlagName());
        buttonC.setText(okayList.get(2).getFlagName());
        buttonD.setText(okayList.get(3).getFlagName());

        if (count == 5){
            Intent intent = new Intent(QuizActivity.this,ResultActivity.class);
            intent.putExtra("trueCount",trueCount);
            startActivity(intent);
            finish();
        }

    }


    public void trueOrFalse(Button button){
        String buttonCountry = button.getText().toString().toUpperCase(Locale.ROOT).trim();
        String trueAnswer = trueFlag.getFlagName().toUpperCase(Locale.ROOT);


        if (buttonCountry.equals(trueAnswer)){
            trueCount ++;
            trueTextView.setText(String.valueOf(trueCount));
            mediaPlayer = MediaPlayer.create(QuizActivity.this,R.raw.success);
            mediaPlayer.start();
        }else{
            falseCount ++;
            falseTextView.setText(String.valueOf(falseCount));
            mediaPlayer = MediaPlayer.create(QuizActivity.this,R.raw.fail);
            mediaPlayer.start();
        }
    }

}