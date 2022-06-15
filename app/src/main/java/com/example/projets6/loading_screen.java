package com.example.projets6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

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

public class loading_screen extends AppCompatActivity{
    LottieAnimationView lottie;
    LottieAnimationView lottie2;
    Button start_button;
    MediaPlayer sound;
    TextView messageView;
    Button messageView2;
    Context context;
    Resources resources;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        lottie=findViewById(R.id.lottie);
        lottie2=findViewById(R.id.lottie2);
        lottie.playAnimation();
        lottie2.playAnimation();
        messageView2 = (Button) findViewById(R.id.button_start);




        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("langue2",true)) {
            context = LocaleHelper.setLocale(loading_screen.this, "hi");
            resources = context.getResources();
            messageView2.setText(resources.getString(R.string.startgame));
        }
        else{
            context = LocaleHelper.setLocale(loading_screen.this, "fr");
            resources = context.getResources();
            messageView2.setText(resources.getString(R.string.startgame));

        }

        if (sharedPreferences.getBoolean("value",true)) {
            sound = MediaPlayer.create(loading_screen.this, R.raw.musique_menu);
            sound.start();
        }



        Button start=(Button) findViewById(R.id.button_start);
        start.setOnClickListener(v -> change());
        start_button=(Button) findViewById(R.id.button_start);
        Animation animation= AnimationUtils.loadAnimation(loading_screen.this,R.anim.blink_anim);
        start_button.startAnimation(animation);
        Toast.makeText(loading_screen.this, "Hello !", Toast.LENGTH_LONG).show();

    }

    public void change(){
        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        String username = prefs.getString("username", "UNKNOWN");
        Intent intent;
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sound.isPlaying()) {
            sound.stop();
            sound.release();
            sound=null;
        }
        if(username.equals("UNKNOWN")) {
            intent = new Intent(this, logInActivity.class);
        }
        else{
            intent = new Intent(this,MainActivity.class);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sound.isPlaying()){
            sound.stop();
            if (isFinishing()){
                sound.stop();
                sound.release();
            }
        }
    }




}
