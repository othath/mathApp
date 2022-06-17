package com.example.projets6.activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.projets6.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class activity_profil extends AppCompatActivity {

    String username;
    String username1;
    SharedPreferences prefs;
    private DatabaseReference mDatabase;
    TextView usertext;
    TextView usertext1;
    int point;
    int point1;
    Map<String,Integer> sortedMap;
    List<String> userList = new ArrayList<>();
    Map<String, Integer> unsortMap = new HashMap<String, Integer>();
    TextView pointtext;
    List<Map.Entry<String, Integer> > entryList;
    TextView pointtext1;
    TextView lvlplayer;
    Context context;
    Resources resources;
    String Key1,Key2,Key3,Key4;
    Integer value1,value2,value3,value4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mDatabase = FirebaseDatabase.getInstance().getReference("Player");

        ValueEventListener listenerTrouver =new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Map.Entry<String, Integer>> entryList = getAllUserName(dataSnapshot);
                Key1 = (String) entryList.get(1).getKey();
                value1 = entryList.get(1).getValue();
                Key2 = (String)entryList.get(2).getKey();
                value2 = entryList.get(2).getValue();
                Key3 = (String) entryList.get(3).getKey();
                value3 = entryList.get(3).getValue();
                Key4 = (String) entryList.get(4).getKey();
                value4 = entryList.get(4).getValue();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

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

        TextView username1  = findViewById(R.id.textView18);
        TextView username2  = findViewById(R.id.textView22);
        TextView username3  = findViewById(R.id.textView25);
        TextView username4  = findViewById(R.id.textView27);
        TextView score1  = findViewById(R.id.textView19);
        TextView score2  = findViewById(R.id.textView17);
        TextView score3  = findViewById(R.id.textView26);
        TextView score4  = findViewById(R.id.textView28);


        username1.setText("max");
        username2.setText("alex");
        username3.setText("jeremy");
        username4.setText("agathe");
        score1.setText("1150 Points");
        score2.setText("960 Points");
        score3.setText("940 Points");
        score4.setText("830 Points");


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
    public List<Map.Entry<String, Integer> > getAllUserName(DataSnapshot dataSnapshot) {
        List<String> userList = new ArrayList<>();
        if (dataSnapshot.exists()) {

            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                String user = ds.child("userName").getValue(String.class);
                Integer score = ds.child("score").getValue(Integer.class);
                unsortMap.put(user,score);
                Map<String,Integer> treeMap = new TreeMap<String, Integer>(unsortMap);
            }
            return entryList;
        }
        return null;

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





}
