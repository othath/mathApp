package com.example.projets6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.projets6.activity.activity_classicmode;

public class RunningFow extends AppCompatActivity {

    activity_classicmode classicmode = new activity_classicmode();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_fow);

    }


    public void button0(View view){
        classicmode.updateAnswer("0");
    }
    public void button1(View view){
        classicmode.updateAnswer("1");
    }
    public void button2(View view){
        classicmode.updateAnswer("2");
    }
    public void button3(View view){
        classicmode.updateAnswer("3");
    }
    public void button4(View view){
        classicmode.updateAnswer("4");
    }
    public void button5(View view){
        classicmode.updateAnswer("5");
    }
    public void button6(View view){
        classicmode.updateAnswer("6");
    }
    public void button7(View view){
        classicmode.updateAnswer("7");
    }
    public void button8(View view){
        classicmode.updateAnswer("8");
    }
    public void button9(View view){
        classicmode.updateAnswer("9");
    }
    public void buttonminus(View view){
        classicmode.updateAnswer("-");
    }
    public void buttoncomma(View view){
        classicmode.updateAnswer(",");
    }

}
