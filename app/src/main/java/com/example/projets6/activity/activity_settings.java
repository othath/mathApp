package com.example.projets6.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.ImageButton;

import com.example.projets6.R;
import com.example.projets6.activity.LocaleHelper;
import com.example.projets6.loading_screen;

import android.widget.Toast;
import android.content.Context;
import android.content.res.Resources;
import android.widget.TextView;

import java.util.Locale;

public class activity_settings extends AppCompatActivity {


    ImageButton btnFr,btnEn;
    Switch switch1;
    Switch switch2;
    MediaPlayer coche;
    MediaPlayer decoche;
    Context context;
    Resources resources;
    TextView messageView;
    TextView messageView2;
    TextView messageView3;
    TextView messageView4;
    Switch messageView5;
    Button messageView6;
    Button btn_credit;
    Button btn_connexion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        switch1 = (Switch) this.findViewById(R.id.fond);
        switch2 = (Switch) this.findViewById(R.id.bruitage);
        messageView = (TextView) findViewById(R.id.textView);
        messageView2 = (TextView) findViewById(R.id.musique);
        messageView3 = (TextView) findViewById(R.id.langue);
        messageView4 = (TextView) findViewById(R.id.fond);
        messageView5 = (Switch) findViewById(R.id.bruitage);
        messageView6 = (Button) findViewById(R.id.credit);
        btnFr = findViewById(R.id.french);
        btnEn = findViewById(R.id.english);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        switch1.setChecked(sharedPreferences.getBoolean("value",true));
        switch2.setChecked(sharedPreferences.getBoolean("value2",true));

        ImageButton retour= (ImageButton) findViewById(R.id.retour);
        retour.setOnClickListener(v -> retour());



        if (sharedPreferences.getBoolean("langue2",true)) {
            context = LocaleHelper.setLocale(activity_settings.this, "hi");
            resources = context.getResources();
            messageView.setText(resources.getString(R.string.reglage));
            messageView2.setText(resources.getString(R.string.musique));
            messageView3.setText(resources.getString(R.string.langue));
            messageView4.setText(resources.getString(R.string.fond));
            messageView5.setText(resources.getString(R.string.bruitage));
            messageView6.setText(resources.getString(R.string.credit));
        }
        else{
            context = LocaleHelper.setLocale(activity_settings.this, "fr");
            resources = context.getResources();
            messageView.setText(resources.getString(R.string.reglage));
            messageView2.setText(resources.getString(R.string.musique));
            messageView3.setText(resources.getString(R.string.langue));
            messageView4.setText(resources.getString(R.string.fond));
            messageView5.setText(resources.getString(R.string.bruitage));
            messageView6.setText(resources.getString(R.string.credit));
        }
        btn_credit=(Button) findViewById(R.id.credit);
        btn_credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
                if (sharedPreferences.getBoolean("value",true)) {
                    MediaPlayer sound = MediaPlayer.create(activity_settings.this, R.raw.ui_sound);
                    sound.start();
                }
                credit();

            }
        });

        btn_connexion=(Button) findViewById(R.id.seco_btn);
        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
                connexion();

            }
        });


        btnEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
                if (sharedPreferences.getBoolean("value",true)) {
                    MediaPlayer sound = MediaPlayer.create(activity_settings.this, R.raw.ui_sound);
                    sound.start();
                }
                SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                editor.putBoolean("langue2",true);
                editor.apply();
                context = LocaleHelper.setLocale(activity_settings.this, "hi");
                resources = context.getResources();
                messageView.setText(resources.getString(R.string.reglage));
                messageView2.setText(resources.getString(R.string.musique));
                messageView3.setText(resources.getString(R.string.langue));
                messageView4.setText(resources.getString(R.string.fond));
                messageView5.setText(resources.getString(R.string.bruitage));
                messageView6.setText(resources.getString(R.string.credit));

            }
        });



        btnFr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
                if (sharedPreferences.getBoolean("value",true)) {
                    MediaPlayer sound = MediaPlayer.create(activity_settings.this, R.raw.ui_sound);
                    sound.start();
                }
                SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                editor.putBoolean("langue2",false);
                editor.apply();
                context = LocaleHelper.setLocale(activity_settings.this, "fr");
                resources = context.getResources();
                messageView.setText(resources.getString(R.string.reglage));
                messageView2.setText(resources.getString(R.string.musique));
                messageView3.setText(resources.getString(R.string.langue));
                messageView4.setText(resources.getString(R.string.fond));
                messageView5.setText(resources.getString(R.string.bruitage));
                messageView6.setText(resources.getString(R.string.credit));
            }
        });

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


    private void connexion(){
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        Intent intent = new Intent(this, com.example.projets6.activity.logInActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
    private void retour() {
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value",true)) {
            MediaPlayer sound = MediaPlayer.create(activity_settings.this, R.raw.ui_sound);
            sound.start();
        }
        Intent intent = new Intent(this, com.example.projets6.activity.MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void credit() {
        Intent intent = new Intent(this, com.example.projets6.credit.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }



    public void finish() {
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value2",true)) {
            MediaPlayer sound = MediaPlayer.create(activity_settings.this, R.raw.ui_sound);
            sound.start();
        }
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
}