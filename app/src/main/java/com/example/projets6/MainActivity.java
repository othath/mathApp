package com.example.projets6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;

import com.example.projets6.activity.logInActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button test=(Button) findViewById(R.id.test);
        test.setOnClickListener(v -> login());

        Button classicmode=(Button) findViewById(R.id.classicmode);
        classicmode.setOnClickListener(v -> openclassicmode());

        Button runfox=(Button) findViewById(R.id.runfox);
        runfox.setOnClickListener(v -> openrunfox());


        String s = getIntent().getStringExtra("userName");
        String pass = getIntent().getStringExtra("password");

            test.setText(s);
            classicmode.setText(pass);

        ImageButton settings = (ImageButton) findViewById(R.id.btnSettings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
            }
        });
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void openclassicmode() {
        Intent intent = new Intent(this, activity_classicmode.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void openrunfox(){
        Intent intent = new Intent(this, RunningFow.class);
        startActivity(intent);
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
