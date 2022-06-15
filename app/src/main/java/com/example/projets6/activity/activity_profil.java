package com.example.projets6.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projets6.activity.LocaleHelper;
import com.example.projets6.activity.MainActivity;
import com.example.projets6.activity.activity_settings;
import com.example.projets6.activity.logInActivity;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.projets6.R;

public class activity_profil extends AppCompatActivity {

    Button deconnecter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageButton retour= (ImageButton) findViewById(R.id.retour);
        retour.setOnClickListener(v -> retour());

        deconnecter=(Button) findViewById(R.id.sedeco);
        deconnecter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
                deconnexion();
            }
        });
    }

    private void retour() {
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value",true)) {
            MediaPlayer sound = MediaPlayer.create(activity_profil.this, R.raw.ui_sound);
            sound.start();
        }
        Intent intent = new Intent(this, com.example.projets6.activity.MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void deconnexion() {
        Intent intent = new Intent(this, com.example.projets6.activity.logInActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


}
