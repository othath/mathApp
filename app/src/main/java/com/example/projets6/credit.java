package com.example.projets6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.projets6.activity.LocaleHelper;
import com.example.projets6.activity.activity_settings;

public class credit extends AppCompatActivity {
    Context context;
    Resources resources;
    MediaPlayer sound;

    public TextView textView_credit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);

        ConstraintLayout constraintLayout=findViewById(R.id.creditLayout);
        AnimationDrawable animationDrawable= (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        Animation animation= AnimationUtils.loadAnimation(credit.this,R.anim.credits);

        textView_credit=(TextView) findViewById(R.id.textViewCredit);
        if (sharedPreferences.getBoolean("value",true)) {
            sound = MediaPlayer.create(credit.this, R.raw.musique_menu);
            sound.start();
        }
        if (sharedPreferences.getBoolean("langue2",true)) {

            textView_credit.setText("CREDITS \n" + "\n" + "Project manager - HASSAINI Abdelkader \n" + "\n" + "Software engineer - AUMARD Max \n" + "\n" + "Design engineer - RUDEAU Agathe \n" + "\n" + "Data engineer - TAHRI Othmane \n" + "\n" + "Sound engineer - GÖKER Batuhan \n" + "\n" + "Animation engineer - GIOVANNETTI Alex \n");
        }
        else{
            textView_credit.setText("CREDITS \n" + "\n" + "Chef de projet - HASSAINI Abdelkader \n" + "\n" + "Ingénieur logiciel - AUMARD Max \n" + "\n" + "Ingénieure design - RUDEAU Agathe \n" + "\n" + "Ingénieur data - TAHRI Othmane \n" + "\n" + "Ingénieur son - GÖKER Batuhan \n" + "\n" + "Ingénieur animation - GIOVANNETTI Alex \n");

        }

        textView_credit.setTextSize(20);

        textView_credit.startAnimation(animation);
    }
    public void finish() {
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value",true)) {
            MediaPlayer sound = MediaPlayer.create(credit.this, R.raw.ui_sound);
            sound.start();
        }
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        if (sharedPreferences.getBoolean("value",true)) {
            sound.stop();
        }

    }


}
