package com.example.projets6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import pl.droidsonroids.gif.GifImageView;

import com.example.projets6.activity.LocaleHelper;
import com.example.projets6.activity.activity_settings;

public class credit extends AppCompatActivity {
    Context context;
    Resources resources;
    MediaPlayer sound;
    GifImageView fox;

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
        fox=(GifImageView)  findViewById(R.id.fox_ground);

        if (sharedPreferences.getBoolean("value",true)) {
            sound = MediaPlayer.create(credit.this, R.raw.credit);
            sound.start();
        }
        if (sharedPreferences.getBoolean("langue2",true)) {

            textView_credit.setText("CREDITS \n" + "\n" + "\n" + "AUMARD Max \n" + "\n" + "HASSAINI Abdelkader \n" +"\n" + "RUDEAU Agathe \n" + "\n" + "TAHRI Othmane \n" + "\n" + "GÖKER Batuhan \n" + "\n" + "GIOVANNETTI Alex \n"+"\n"+ "Musiques/Bruitages : DJSIRNA \n");
        }
        else{
            textView_credit.setText("CREDITS \n" + "\n" + "AUMARD Max \n" + "\n" + "TAHRI Othmane  \n" + "\n" + "RUDEAU Agathe \n" + "\n" + "HASSAINI Abdelkader \n" + "\n" + "GÖKER Batuhan \n" + "\n" + "GIOVANNETTI Alex \n" +"\n"+ "Musiques/Bruitages : DJSIRNA \n");

        }

        textView_credit.setTextSize(20);
        fox.startAnimation(animation);
        textView_credit.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();

            }
        },25000);
    }
    public void finish() {
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value",true)) {
            MediaPlayer sound = MediaPlayer.create(credit.this, R.raw.ui_sound);
            sound.start();
        }
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        if (sound.isPlaying()) {
            sound.stop();
            sound.release();
            sound=null;
        }

    }

    public void stop_credit_song(){
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sound.isPlaying()) {
            sound.stop();
            sound.release();
            sound=null;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, activity_settings.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        stop_credit_song();
    }


}
