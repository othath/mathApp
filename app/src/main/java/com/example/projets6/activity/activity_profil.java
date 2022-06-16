package com.example.projets6.activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.projets6.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class activity_profil extends AppCompatActivity {

    String username;
    String username1;
    SharedPreferences prefs;
    private DatabaseReference mDatabase;
    TextView usertext;
    TextView usertext1;
    int point;
    int point1;
    TextView pointtext;
    TextView pointtext1;
    TextView lvlplayer;
    Context context;
    Resources resources;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mDatabase = FirebaseDatabase.getInstance().getReference("Player");


        ImageButton retour= (ImageButton) findViewById(R.id.retour);
        retour.setOnClickListener(v -> retour());

        ImageButton deconnexion= (ImageButton) findViewById(R.id.sedeco);
        deconnexion.setOnClickListener(v -> deconnexion());

        usertext = findViewById(R.id.username);
        pointtext = findViewById(R.id.pointNumber);
        pointtext1 = findViewById(R.id.pointNumber1);
        lvlplayer = findViewById(R.id.lvlplayer);
        TextView pourcent = findViewById(R.id.pourcent);
        usertext1 = findViewById(R.id.classuser);

        prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        username = prefs.getString("username", "UNKNOWN");
        point = prefs.getInt("score", 0);
        point1 =prefs.getInt("score", 0);
        username1=prefs.getString("username","UNKNOWN");

        usertext.setText(username);
        pointtext.setText(String.valueOf(point));
        pointtext1.setText(String.valueOf(point1)+" points");
        usertext1.setText(username1);

        setlvl(lvlplayer,pourcent);

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
        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        prefs.edit().putString("username", "UNKOWN").commit();
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        Intent intent = new Intent(this, com.example.projets6.activity.logInActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

    public void setlvl(TextView lvlplayer,TextView pourcent){
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (point>=0){
            lvlplayer.setText(R.string.novice);

            int pc = (int) ((point)/2);
            pourcent.setText(Integer.toString(pc)+"%");
        }
        if (point>200){
            lvlplayer.setText(R.string.beginner);

            int pc = (int) ((point-200)/2);
            pourcent.setText(Integer.toString(pc)+"%");
        }
        if (point>400){
            lvlplayer.setText(R.string.intermediary);

            int pc = (int) ((point-400)/2);
            pourcent.setText(Integer.toString(pc)+"%");
        }
        if (point>600){
            lvlplayer.setText(R.string.advanced);

            int pc = (int) ((point-600)/2);
            pourcent.setText(Integer.toString(pc)+"%");
        }
        if (point>800){
            lvlplayer.setText(R.string.confirmed);

            int pc = (int) ((point-800)/2);
            pourcent.setText(Integer.toString(pc)+"%");
        }
        if (point>1000){
            lvlplayer.setText(R.string.expert);

            int pc = 100;
            pourcent.setText(Integer.toString(pc)+"%");
        }
        pointtext.setText(Integer.toString(point));

    }

    @Override
    public void onBackPressed() {
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value",true)) {
            MediaPlayer sound = MediaPlayer.create(activity_profil.this, R.raw.ui_sound);
            sound.start();
        }
        Intent intent = new Intent(this, com.example.projets6.activity.MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
