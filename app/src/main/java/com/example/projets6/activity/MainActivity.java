package com.example.projets6.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.projets6.R;
import com.example.projets6.RunningFow;
import com.example.projets6.activity.activity_settings;
import com.example.projets6.activity.logInActivity;
import com.example.projets6.credit;
import com.example.projets6.loading_screen;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    int point;
    String username;
    Boolean multij;
    TextView pointtext;
    TextView messageView;
    TextView messageView2;
    ImageButton didacticiel;
    TextView messageView3;
    TextView messageView4;
    TextView messageView5;
    MediaPlayer sound;
    private DatabaseReference mDatabase;
    SharedPreferences prefs;



    Button classicmode;
    Context context;
    Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference("Player");
        prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        messageView = (TextView) findViewById(R.id.classicmode);
        messageView3 = (TextView) findViewById(R.id.lvlplayer);
        messageView4 = (TextView) findViewById(R.id.runfox);
        messageView5 = (TextView) findViewById(R.id.runfoxmulti);

        pointtext = findViewById(R.id.pointNumber);
        TextView usertext = findViewById(R.id.username);
        TextView lvlplayer = findViewById(R.id.lvlplayer);
        TextView pourcent = findViewById(R.id.pourcent);
        ImageButton test=(ImageButton) findViewById(R.id.userprofile);
        // username = getIntent().getStringExtra("userName");
        didacticiel = (ImageButton) findViewById(R.id.tuto);

        if (sharedPreferences.getBoolean("value",true)) {
            sound = MediaPlayer.create(MainActivity.this, R.raw.menu);
            sound.start();
        }

        didacticiel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activity_popup.class));
            }
        });

        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        username = prefs.getString("username", "UNKNOWN");
        point = prefs.getInt("score", 0);
        boolean multi=prefs.getBoolean("multijoueur",false);
        //  point = getIntent().getIntExtra("score",100);
        if (sharedPreferences.getBoolean("langue2",true)) {
            context = LocaleHelper.setLocale(MainActivity.this, "hi");
            resources = context.getResources();
            messageView.setText(resources.getString(R.string.classicmode));
            messageView4.setText(resources.getString(R.string.adventuremode));
            messageView5.setText(resources.getString(R.string.multimode));
            // messageView.setText(String.valueOf(multi));

        }
        else{
            context = LocaleHelper.setLocale(MainActivity.this, "fr");
            resources = context.getResources();
            messageView.setText(resources.getString(R.string.classicmode));
            messageView4.setText(resources.getString(R.string.adventuremode));
            messageView5.setText(resources.getString(R.string.multimode));
        }
        test.setOnClickListener(v -> Profil());

        Button runfox=(Button) findViewById(R.id.runfox);
        // runfox.setOnClickListener(v -> openrunfox());
        runfox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
                if (sharedPreferences.getBoolean("value2",true)) {
                    MediaPlayer sound = MediaPlayer.create(MainActivity.this, R.raw.ui_sound);
                    sound.start();
                }

                openrunfox();
            }
        });


        classicmode=(Button) findViewById(R.id.classicmode);
        classicmode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
                if (sharedPreferences.getBoolean("value2",true)) {
                    MediaPlayer sound = MediaPlayer.create(MainActivity.this, R.raw.ui_sound);
                    sound.start();
                }

                openclassicmode();
            }
        });

        ImageButton settings = (ImageButton) findViewById(R.id.btnSettings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
                if (sharedPreferences.getBoolean("value2",true)) {
                    MediaPlayer sound = MediaPlayer.create(MainActivity.this, R.raw.ui_sound);
                    sound.start();
                }



                openSettings();
            }
        });
        Button btn_multi=(Button) findViewById(R.id.runfoxmulti) ;
        btn_multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
                if (sharedPreferences.getBoolean("value2",true)) {
                    MediaPlayer sound = MediaPlayer.create(MainActivity.this, R.raw.ui_sound);
                    sound.start();
                }
                mDatabase.child(username).child("multijoueur").setValue(true);
                prefs.edit().putBoolean("multijoueur", true).commit();
                openMultiplayer();

            }
        });



        setlvl(lvlplayer,pourcent);



        usertext.setText(username);
        pointtext.setText(String.valueOf(point));


    }

    public void stop_menu_song(){
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sound.isPlaying() && sound!=null) {
            sound.stop();
            sound.release();
            sound=null;
        }
    }
    private void openrunfox(){
        Intent intent = new Intent(this, com.example.projets6.go.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        stop_menu_song();

    }

    private void openclassicmode() {
        Intent intent = new Intent(this, com.example.projets6.activity.activity_classicmode.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        stop_menu_song();
    }

    private void openSettings() {
        Intent intent = new Intent(this, activity_settings.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        stop_menu_song();
    }
    private void openMultiplayer() {
        Intent intent = new Intent(this, com.example.projets6.multiplayer_screen.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        stop_menu_song();
    }

    public void login(){
        Intent intent=new Intent(this,logInActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        stop_menu_song();
    }

    public void Profil(){
        Intent intent=new Intent(this,activity_profil.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        stop_menu_song();
    }

    public void finish() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
                        if (sharedPreferences.getBoolean("value",true)) {
                            MediaPlayer sound = MediaPlayer.create(MainActivity.this, R.raw.ui_sound);
                            sound.start();
                        }
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();



    }

    public void setPoint(int point){
        this.point= point;
    }
    public void setlvl(TextView lvlplayer,TextView pourcent){
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (point>=0){
            lvlplayer.setText(R.string.novice);
            if (sharedPreferences.getBoolean("langue2",true)) {
                context = LocaleHelper.setLocale(MainActivity.this, "hi");
                resources = context.getResources();
                messageView3.setText(resources.getString(R.string.novice));

            }
            else{
                context = LocaleHelper.setLocale(MainActivity.this, "fr");
                resources = context.getResources();
                messageView3.setText(resources.getString(R.string.novice));


            }
            int pc = (int) ((point)/2);
            pourcent.setText(Integer.toString(pc)+"%");
        }
        if (point>200){
            lvlplayer.setText(R.string.beginner);
            if (sharedPreferences.getBoolean("langue2",true)) {
                context = LocaleHelper.setLocale(MainActivity.this, "hi");
                resources = context.getResources();
                messageView3.setText(resources.getString(R.string.beginner));

            }
            else{
                context = LocaleHelper.setLocale(MainActivity.this, "fr");
                resources = context.getResources();
                messageView3.setText(resources.getString(R.string.beginner));


            }
            int pc = (int) ((point-200)/2);
            pourcent.setText(Integer.toString(pc)+"%");
        }
        if (point>400){
            lvlplayer.setText(R.string.intermediary);
            if (sharedPreferences.getBoolean("langue2",true)) {
                context = LocaleHelper.setLocale(MainActivity.this, "hi");
                resources = context.getResources();
                messageView3.setText(resources.getString(R.string.intermediary));

            }
            else{
                context = LocaleHelper.setLocale(MainActivity.this, "fr");
                resources = context.getResources();
                messageView3.setText(resources.getString(R.string.intermediary));


            }
            int pc = (int) ((point-400)/2);
            pourcent.setText(Integer.toString(pc)+"%");
        }
        if (point>600){
            lvlplayer.setText(R.string.advanced);
            if (sharedPreferences.getBoolean("langue2",true)) {
                context = LocaleHelper.setLocale(MainActivity.this, "hi");
                resources = context.getResources();
                messageView3.setText(resources.getString(R.string.advanced));

            }
            else{
                context = LocaleHelper.setLocale(MainActivity.this, "fr");
                resources = context.getResources();
                messageView3.setText(resources.getString(R.string.advanced));


            }
            int pc = (int) ((point-600)/2);
            pourcent.setText(Integer.toString(pc)+"%");
        }
        if (point>800){
            lvlplayer.setText(R.string.confirmed);
            if (sharedPreferences.getBoolean("langue2",true)) {
                context = LocaleHelper.setLocale(MainActivity.this, "hi");
                resources = context.getResources();
                messageView3.setText(resources.getString(R.string.confirmed));

            }
            else{
                context = LocaleHelper.setLocale(MainActivity.this, "fr");
                resources = context.getResources();
                messageView3.setText(resources.getString(R.string.confirmed));


            }
            int pc = (int) ((point-800)/2);
            pourcent.setText(Integer.toString(pc)+"%");
        }
        if (point>1000){
            lvlplayer.setText(R.string.expert);
            if (sharedPreferences.getBoolean("langue2",true)) {
                context = LocaleHelper.setLocale(MainActivity.this, "hi");
                resources = context.getResources();
                messageView3.setText(resources.getString(R.string.expert));

            }
            else{
                context = LocaleHelper.setLocale(MainActivity.this, "fr");
                resources = context.getResources();
                messageView3.setText(resources.getString(R.string.expert));


            }
            int pc = 100;
            pourcent.setText(Integer.toString(pc)+"%");
        }
        pointtext.setText(Integer.toString(point));

    }



}

