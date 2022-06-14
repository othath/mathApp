package com.example.projets6;

import static com.example.projets6.RunningFow.getcount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class activity_end_fox extends AppCompatActivity {

    int count = getcount(), point, score;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_fox);
        TextView stat_fox = findViewById(R.id.stat_fox);
        TextView scoretext = findViewById(R.id.stat_fox);
        TextView pointtext = findViewById(R.id.points);
        TextView usernametext = findViewById(R.id.username);
        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        username = prefs.getString("username", "UNKNOWN");
        usernametext.setText(username);
        point = prefs.getInt("score", 100);
        pointtext.setText(Integer.toString(point)+" Points");
        Button replay=(Button) findViewById(R.id.btn_replay);
        replay.setOnClickListener(v -> openrunfox());
        score = ((int)((float)point/10))*10;
        scoretext.setText(Integer.toString(score));
        int plus3 = count+3;
        stat_fox.setText(count + "/"+ plus3);

    }
    private void openrunfox(){
        Intent intent = new Intent(this, RunningFow.class);
        startActivity(intent);
    }
}