package com.example.projets6.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.projets6.R;
import com.example.projets6.activity.activity_settings;
import com.example.projets6.activity.logInActivity;


public class MainActivity extends AppCompatActivity {
    static int point;
    TextView pointtext;
    Button classicmode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton test=(ImageButton) findViewById(R.id.userprofile);
        test.setOnClickListener(v -> login());

        classicmode=(Button) findViewById(R.id.classicmode);
        classicmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openclassicmode();
            }
        });

        ImageButton settings = (ImageButton) findViewById(R.id.btnSettings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
            }
        });


        pointtext = findViewById(R.id.pointNumber);
        TextView usertext = findViewById(R.id.username);
        TextView lvlplayer = findViewById(R.id.lvlplayer);
        TextView pourcent = findViewById(R.id.pourcent);
        setlvl(lvlplayer,pourcent);


        String user = getIntent().getStringExtra("userName");
        String pass = getIntent().getStringExtra("password");
        point = getIntent().getIntExtra("score",100);
        usertext.setText(user);
        pointtext.setText(String.valueOf(point));


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
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
        if (point>0){
            lvlplayer.setText("Débutant");
            int pc = (int) ((point)/2);
            pourcent.setText(Integer.toString(pc)+"%");
        }
        if (point>200){
            lvlplayer.setText("Débutant");
            int pc = (int) ((point-200)/2);
            pourcent.setText(Integer.toString(pc)+"%");
        }
        if (point>400){
            lvlplayer.setText("Intermédiaire");
            int pc = (int) ((point-400)/2);
            pourcent.setText(Integer.toString(pc)+"%");
        }
        if (point>600){
            lvlplayer.setText("Avancé");
            int pc = (int) ((point-600)/2);
            pourcent.setText(Integer.toString(pc)+"%");
        }
        if (point>800){
            lvlplayer.setText("Confirmé");
            int pc = (int) ((point-800)/2);
            pourcent.setText(Integer.toString(pc)+"%");
        }
        if (point>1000){
            lvlplayer.setText("Expert");
            int pc = 100;
            pourcent.setText(Integer.toString(pc)+"%");
        }
        pointtext.setText(Integer.toString(point));

    }



}
