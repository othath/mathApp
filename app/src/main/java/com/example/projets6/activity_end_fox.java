package com.example.projets6;

import static com.example.projets6.RunningFow.getcount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projets6.activity.activity_settings;

public class activity_end_fox extends AppCompatActivity {

    int count = getcount(), point;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_fox);

        ImageButton retour= (ImageButton) findViewById(R.id.retour);
        retour.setOnClickListener(v -> retour());

        TextView stat_fox = findViewById(R.id.stat_fox);
        TextView scoretext = findViewById(R.id.score_fox);
        TextView pointtext = findViewById(R.id.points);
        TextView usernametext = findViewById(R.id.username);
        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        username = prefs.getString("username", "UNKNOWN");
        usernametext.setText(username);
        point = prefs.getInt("score", 100);
        pointtext.setText(Integer.toString(point)+" Points");
        Button replay=(Button) findViewById(R.id.btn_replay);
        replay.setOnClickListener(v -> openrunfox());
        int score = point/10;
        score *= 10 ;
        scoretext.setText(score+" ");
        int plus3 = count+3;
        stat_fox.setText(count + "/"+ plus3);

    }
    private void openrunfox(){
        Intent intent = new Intent(this, RunningFow.class);
        startActivity(intent);
    }
    private void retour() {
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value",true)) {
            MediaPlayer sound = MediaPlayer.create(activity_end_fox.this, R.raw.ui_sound);
            sound.start();
        }
        Intent intent = new Intent(this, com.example.projets6.activity.MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}