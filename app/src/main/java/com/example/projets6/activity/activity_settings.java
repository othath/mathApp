package com.example.projets6.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.projets6.R;

public class activity_settings extends AppCompatActivity {

        Switch switch1;
        Switch switch2;
        MediaPlayer coche;
        MediaPlayer decoche;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings);

            switch1 = (Switch) this.findViewById(R.id.fond);
            switch2 = (Switch) this.findViewById(R.id.bruitage);
            SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
            switch1.setChecked(sharedPreferences.getBoolean("value",true));
            switch2.setChecked(sharedPreferences.getBoolean("value2",true));

            switch1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (switch1.isChecked())
                    {
                        SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                        editor.putBoolean("value",true);
                        editor.apply();
                        switch1.setChecked(true);
                        Toast.makeText(activity_settings.this, "Musique de fond activée", Toast.LENGTH_SHORT).show();
                        if (sharedPreferences.getBoolean("value2",true)) {
                            coche = MediaPlayer.create(activity_settings.this, R.raw.case_coche);
                            coche.start();
                        }
                    }
                    else
                    {
                        SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                        editor.putBoolean("value",false);
                        editor.apply();
                        switch1.setChecked(false);
                        Toast.makeText(activity_settings.this, "Musique de fond désactivée", Toast.LENGTH_SHORT).show();
                        if (sharedPreferences.getBoolean("value2",true)) {
                            decoche = MediaPlayer.create(activity_settings.this, R.raw.case_decoche);
                            decoche.start();
                        }
                    }
                }
            });

            switch2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (switch2.isChecked())
                    {
                        SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                        editor.putBoolean("value2",true);
                        editor.apply();
                        switch2.setChecked(true);
                        Toast.makeText(activity_settings.this, "Bruitage activé", Toast.LENGTH_SHORT).show();
                        if (sharedPreferences.getBoolean("value2",true)) {
                            coche = MediaPlayer.create(activity_settings.this, R.raw.case_coche);
                            coche.start();
                        }
                    }
                    else
                    {
                        SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                        editor.putBoolean("value2",false);
                        editor.apply();
                        switch2.setChecked(false);
                        Toast.makeText(activity_settings.this, "Bruitage désactivé", Toast.LENGTH_SHORT).show();
                        if (sharedPreferences.getBoolean("value2",true)) {
                            decoche = MediaPlayer.create(activity_settings.this, R.raw.case_decoche);
                            decoche.start();
                        }
                    }
                }
            });


        }







        public void finish() {
            super.finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        }
    }
