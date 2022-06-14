package com.example.projets6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.example.projets6.activity.LocaleHelper;
import com.example.projets6.activity.MainActivity;
import com.example.projets6.activity.activity_settings;
import com.example.projets6.activity.logInActivity;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class multiplayer_screen extends AppCompatActivity{
    LottieAnimationView lottie;
    LottieAnimationView lottie2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplayer_screen);
        lottie = findViewById(R.id.lottie);
        lottie2 = findViewById(R.id.lottie2);
        lottie.playAnimation();
        lottie2.playAnimation();
    }
}
