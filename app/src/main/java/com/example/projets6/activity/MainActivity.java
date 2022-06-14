package com.example.projets6.activity;

import android.content.Context;
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


public class MainActivity extends AppCompatActivity {
    int point;
    String username;
    Boolean multij;
    TextView pointtext;
    TextView messageView;
    TextView messageView2;

    TextView messageView3;



    Button classicmode;
    Context context;
    Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        messageView = (TextView) findViewById(R.id.classicmode);
        messageView3 = (TextView) findViewById(R.id.lvlplayer);
        pointtext = findViewById(R.id.pointNumber);
        TextView usertext = findViewById(R.id.username);
        TextView lvlplayer = findViewById(R.id.lvlplayer);
        TextView pourcent = findViewById(R.id.pourcent);
        ImageButton test=(ImageButton) findViewById(R.id.userprofile);
       // username = getIntent().getStringExtra("userName");

        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        username = prefs.getString("username", "UNKNOWN");
        point = prefs.getInt("score", 0);
        boolean multi=prefs.getBoolean("multijoueur",false);
      //  point = getIntent().getIntExtra("score",100);
        if (sharedPreferences.getBoolean("langue2",true)) {
            context = LocaleHelper.setLocale(MainActivity.this, "hi");
            resources = context.getResources();
            messageView.setText(resources.getString(R.string.classicmode));
           // messageView.setText(String.valueOf(multi));

        }
        else{
            context = LocaleHelper.setLocale(MainActivity.this, "fr");
            resources = context.getResources();
            messageView.setText(resources.getString(R.string.classicmode));
        }
        test.setOnClickListener(v -> login());

        Button runfox=(Button) findViewById(R.id.runfox);
       // runfox.setOnClickListener(v -> openrunfox());
        runfox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
                if (sharedPreferences.getBoolean("value",true)) {
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
                if (sharedPreferences.getBoolean("value",true)) {
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
                if (sharedPreferences.getBoolean("value",true)) {
                    MediaPlayer sound = MediaPlayer.create(MainActivity.this, R.raw.ui_sound);
                    sound.start();
                }

                openSettings();
            }
        });



        setlvl(lvlplayer,pourcent);



        usertext.setText(username);
        pointtext.setText(String.valueOf(point));


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void openrunfox(){
        Intent intent = new Intent(this, com.example.projets6.go.class);
        startActivity(intent);
    }

    private void openclassicmode() {
        Intent intent = new Intent(this, com.example.projets6.activity.activity_classicmode.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void openSettings() {
        Intent intent = new Intent(this, activity_settings.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void login(){
        Intent intent=new Intent(this,logInActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

    public void setPoint(int point){
        this.point= point;
    }
    public void setlvl(TextView lvlplayer,TextView pourcent){
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (point>0){
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
