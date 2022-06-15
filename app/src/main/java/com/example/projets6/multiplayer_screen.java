package com.example.projets6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.example.projets6.activity.LocaleHelper;
import com.example.projets6.activity.MainActivity;
import com.example.projets6.activity.Multimode;
import com.example.projets6.activity.activity_settings;
import com.example.projets6.activity.logInActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class multiplayer_screen extends AppCompatActivity{
    static int count=0;
    Intent intent ;
    String adversaire;
    boolean multijTest;
    LottieAnimationView lottie;
    LottieAnimationView lottie2;
    String username;
    private DatabaseReference playerRef;
    private DatabaseReference gamesRef;
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.multiplayer_screen);
        intent = new Intent(multiplayer_screen.this, Multimode.class);



        count++;
        lottie = findViewById(R.id.lottie2);
        lottie2 = findViewById(R.id.lottie3);
        lottie.playAnimation();
        lottie2.playAnimation();


        playerRef = FirebaseDatabase.getInstance().getReference("Player");
        gamesRef = FirebaseDatabase.getInstance().getReference("games");

        prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        username = prefs.getString("username", "UNKNOWN");

        multijTest=prefs.getBoolean("multij",false);
        playerRef.child(username).child("multijoueur").setValue(true);


        gamesRef.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("snap", snapshot.toString());
                if(isPlayerInGame(snapshot,username)) {
                    startActivity(intent);
                    gamesRef.removeEventListener(this);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        playerRef.addListenerForSingleValueEvent(listenerTrouver);


    }


    ValueEventListener listenerTrouver = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Log.i("DEBUG",dataSnapshot.toString());
            List<String> userList =getAllUserName(dataSnapshot);
            if(userList!=null){
                Log.i("userList", userList.toString());
                for(int i=0;i<userList.size();i++) {
                    Log.i("userList", userList.get(i).toString());
                    if(!userList.get(i).equals(username)) {
                        boolean multij = dataSnapshot.child(userList.get(i)).child("multijoueur").getValue(Boolean.class);
                        prefs.edit().putBoolean("multijPlayer2",multij).commit();//score d'adversaire
                        adversaire = dataSnapshot.child(userList.get(i)).child("userName").getValue(String.class);
                        int score = dataSnapshot.child(userList.get(i)).child("score").getValue(Integer.class);
                        if (multij == true) {
                            Log.i("user",username);
                            Log.i("adv",adversaire);
                            updateFireBase(username,adversaire);
                            playerRef.child(username).child("multijoueur").setValue(false);
                            prefs.edit().putInt("score2",score).commit();//score d'adversaire
                            playerRef.child(adversaire).child("multijoueur").setValue(false);
                            startActivity(intent);
                            break;
                            //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                        }
                    }
                }
            }

            }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
        };
    private boolean isPlayerInGame(DataSnapshot snapshot,String player){
        int nb=0;
        String player2=null,player1 = null;
        Log.i("snap",snapshot.toString());
        if (snapshot.exists()) {
            count+=snapshot.getChildrenCount();
            for (DataSnapshot ds : snapshot.getChildren()) {
                // String user = ds.getValue(String.class);

                player1 = ds.child("player1").getValue(String.class);
                player2 = ds.child("player2").getValue(String.class);
                if (player2.equals(player)) nb++;
            }
            Log.i("player2",player2);
        }
        if(nb==1) {
                playerRef.child(player2).child("multijoueur").setValue(false);

            return true;
        }
        return false;
    }
    private void updateFireBase(String p1,String p2) {
        DatabaseReference db;
        db = gamesRef.child(String.valueOf(count));
        db.child("player2").setValue(p2);
        db.child("player1").setValue(p1);



    }


    public int getScoreP1(){
        return prefs.getInt("score",0);
    }
    public int getScoreP2(){
        return prefs.getInt("score2",0);
    }
    public List<String> getAllUserName(DataSnapshot dataSnapshot) {
        List<String> userList = new ArrayList<>();
        if (dataSnapshot.exists()) {

            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                // String user = ds.getValue(String.class);
                String user = ds.child("userName").getValue(String.class);
                userList.add(user);
                Log.d("DEBUG", user + " / " + user);
            }
            return userList;
        }
        return null;

    }


}
