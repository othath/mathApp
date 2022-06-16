package com.example.projets6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

public class activity_winFox extends AppCompatActivity {
    String winner, looser;
    String username, adversaire;
    Integer scoregame, opponantscoregame, mypoints,opponantpoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_fox);

        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        username = prefs.getString("username", "UNKNOWN");
        adversaire = prefs.getString("adversaire", "UNKNOWN");
        scoregame = prefs.getInt("myscoregame", 0);
        opponantscoregame = prefs.getInt("opponantscoregame", 0);
        opponantpoints = prefs.getInt("scoreAdv",0);
        mypoints = prefs.getInt("score",0);

        if (opponantscoregame > scoregame) {
            setWinner(adversaire, opponantscoregame,opponantpoints);
            setLooser(username, scoregame,mypoints);
        } else {
            setWinner(username, scoregame,mypoints);
            setLooser(adversaire, opponantscoregame,opponantpoints);
        }

        ImageButton retour= (ImageButton) findViewById(R.id.retour);
        retour.setOnClickListener(v -> retour());

    }
    public void setWinner(String name, Integer score, Integer point){
        TextView player = findViewById(R.id.player1);
        TextView player2 = findViewById(R.id.player1_2);
        TextView pointProfile = findViewById(R.id.point1);
        TextView pointGame = findViewById(R.id.point1_2);
        player.setText(name);
        player2.setText(name);
        pointProfile.setText(point.toString());
        pointGame.setText(score.toString());
    }
    public void setLooser(String name, Integer score, Integer point){
        TextView player = findViewById(R.id.player2);
        TextView player2 = findViewById(R.id.player2_2);
        TextView pointProfile = findViewById(R.id.point2);
        TextView pointGame = findViewById(R.id.point2_2);
        player.setText(name);
        player2.setText(name);
        pointProfile.setText(point.toString());
        pointGame.setText(score.toString());

    }

    private void retour() {
        Intent intent = new Intent(this, com.example.projets6.activity.MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}

