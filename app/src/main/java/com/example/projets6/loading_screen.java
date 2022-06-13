package com.example.projets6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import com.example.projets6.activity.MainActivity;
import com.example.projets6.activity.logInActivity;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

public class loading_screen extends AppCompatActivity{
    Button start_button;
    MediaPlayer sound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);


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
        Intent intent=new Intent(this, logInActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value",true)) {
            sound.stop();
        }
    }




}
