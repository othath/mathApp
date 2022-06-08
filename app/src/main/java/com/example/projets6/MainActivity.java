package com.example.projets6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.projets6.activity.logInActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button test=(Button) findViewById(R.id.test);
        test.setOnClickListener(v -> login());
    }
    public void login(){
        Intent intent=new Intent(this,logInActivity.class);
        startActivity(intent);
    }
}