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

    Button classicmode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button test=(Button) findViewById(R.id.test);
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


        TextView pointtext = findViewById(R.id.pointNumber);
        TextView usertext = findViewById(R.id.username);

        String user = getIntent().getStringExtra("userName");
        String pass = getIntent().getStringExtra("password");
        String point = getIntent().getStringExtra("score");
        usertext.setText(user);
        pointtext.setText(point);
        classicmode.setText(pass);

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



}
