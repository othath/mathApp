package com.example.projets6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.os.Handler;
import android.widget.Button;

import com.example.projets6.activity.LocaleHelper;
import com.example.projets6.activity.MainActivity;
import com.example.projets6.activity.activity_settings;
import com.example.projets6.activity.logInActivity;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;

public class go extends AppCompatActivity{
    LottieAnimationView lottie;

    MediaPlayer sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.go);
        lottie=findViewById(R.id.lottie);

        lottie.playAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
                if (sharedPreferences.getBoolean("value2",true)) {
                    sound = MediaPlayer.create(go.this, R.raw.bip_bip_bip);
                    sound.start();
                }

            }
        },500);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                change();

            }
        },5000);





    }
    public void change(){
        Intent intent=new Intent(this, RunningFow.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sound.isPlaying()) {
            sound.stop();
            sound.release();
            sound=null;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, com.example.projets6.activity.MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
