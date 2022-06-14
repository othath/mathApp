package com.example.projets6;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projets6.activity.activity_classicmode;
import com.example.projets6.activity.activity_settings;

import pl.droidsonroids.gif.GifImageView;

public class RunningFow extends AppCompatActivity {
    activity_classicmode classicmode = new activity_classicmode();
    TextView answer, timer, textEquation, goodAnswertext;
    int lives = 3, point, count = 0, goodAnswer = 0;
    String res, an;
    Equation eq;
    GifImageView foxjump, foxrunning;
    ImageView death1,death2,death3;
    Handler handler;
    CountDownTimer cdtimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_fow);

        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        an="";
        point = prefs.getInt("score", 100);
        foxjump = (GifImageView) findViewById(R.id.foxjump);
        foxrunning = (GifImageView) findViewById(R.id.foxrunning);
        death1 = findViewById(R.id.death1);
        death2 = findViewById(R.id.death2);
        death3 = findViewById(R.id.death3);
        goodAnswertext = findViewById(R.id.count);
        goodAnswertext.setText(Integer.toString(goodAnswer));
        timer = findViewById(R.id.timer);
        answer = findViewById(R.id.inputanswer);
        answer.setShowSoftInputOnFocus(false);
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });
        displayGame();

    }
    public void generateEquation() {
        if (count < 5) {
            eq = new Equation(1);
        }
        if (count > 5) {
            eq = new Equation(2);
        }
        if (count >= 10) {
            eq = new Equation(3);
        }
        if (count >= 20) {
            eq = new Equation(4);
        }
        if (count >= 30) {
            eq = new Equation(5);
        }
        if (count >= 40) {
            eq = new Equation(7);
        }
        if (count >= 50) {
            eq = new Equation(8);
        }
        if (count >= 60) {
            eq = new Equation(9);
        }
        if (count >= 70) {
            eq = new Equation(10);
        }
        res = classicmode.StringToCalcul(eq.equations);
        textEquation = findViewById(R.id.textequation);
        textEquation.setText(classicmode.affichageEquation(eq.equations));
    }

    private void updateAnswer(String strToAdd) {
        String oldString = answer.getText().toString();
        int cursorPos = answer.getSelectionStart();
        String leftStr = oldString.substring(0,cursorPos);
        String rightStr = oldString.substring(cursorPos);
        answer.setText(String.format("%s%s%s",rightStr,strToAdd,leftStr));
        an = classicmode.StringToCalcul(answer.getText().toString());
    }

    public void displayGame(){
        if (cdtimer!=null){
            cdtimer.cancel();
        }
        generateEquation();
        timer.setVisibility(View.VISIBLE);
        cdtimer = new CountDownTimer(20000, 10) {

            public void onTick(long millisUntilFinished) {
                NumberFormat f = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    f = new DecimalFormat("00");
                }
                long sec = (millisUntilFinished / 1000) % 60;
                long ms = (millisUntilFinished) / 10 % 60;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    timer.setText(f.format(sec) + ":" + f.format(ms));
                }

                if (an.equals(res)) {
                    goodAnswer();
                }
            }

            public void onFinish() {
                lives-=1;
                if (lives == 2){
                    death3.setVisibility(View.VISIBLE);
                }
                if (lives == 1){
                    death2.setVisibility(View.VISIBLE);
                }
                if (lives == 0){
                    death1.setVisibility(View.VISIBLE);
                }
                if (lives !=0){
                    displayGame();
                }
            }
        }.start();
    }


public void goodAnswer(){
    goodAnswer+=1;
    SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
    if (sharedPreferences.getBoolean("value2",true)) {
        MediaPlayer sound= MediaPlayer.create(RunningFow.this,R.raw.bonne_reponse);
        sound.start();
    }
    goodAnswertext.setText(Integer.toString(goodAnswer));
    answer.setText("");
    timer.setVisibility(View.INVISIBLE);
    foxrunning.setVisibility(View.INVISIBLE);
    foxjump.setVisibility(View.VISIBLE);

    final Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            foxjump.setVisibility(View.GONE);
            foxrunning.setVisibility(View.VISIBLE);

        }
    }, 1000);
    count++;
    displayGame();
}

    public void sound_ui(){
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value",true)) {
            MediaPlayer sound = MediaPlayer.create(RunningFow.this, R.raw.ui_sound);
            sound.start();
        }
    }

    public void button0(View view){
        sound_ui();
        updateAnswer("0");
    }
    public void button1(View view){
        sound_ui();
        updateAnswer("1");
    }
    public void button2(View view){

        sound_ui();
        updateAnswer("2");
    }
    public void button3(View view){

        sound_ui();
        updateAnswer("3");
    }
    public void button4(View view){

        sound_ui();
        updateAnswer("4");
    }
    public void button5(View view){

        sound_ui();
        updateAnswer("5");
    }
    public void button6(View view){

        sound_ui();
        updateAnswer("6");
    }
    public void button7(View view){

        sound_ui();
        updateAnswer("7");
    }
    public void button8(View view){

        sound_ui();
        updateAnswer("8");
    }
    public void button9(View view){

        sound_ui();
        updateAnswer("9");
    }
    public void buttonminus(View view){

        sound_ui();
        updateAnswer("-");
    }
    public void buttoncomma(View view){
        sound_ui();
        updateAnswer(",");
    }
    public void buttondivision(View view){

        sound_ui();
        updateAnswer("/");
    }
    public void buttondel(View view){
        sound_ui();
        int textLen = answer.getText().length();
        if(textLen!=0){
            SpannableStringBuilder selection = (SpannableStringBuilder)  answer.getText();
            selection.replace(textLen-1 , textLen,"");
            answer.setText(selection);
        }
    }
    public void buttondelall(View view){

        sound_ui();
        answer.setText("");
    }

    public void finish() {
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value",true)) {
            MediaPlayer sound = MediaPlayer.create(RunningFow.this, R.raw.ui_sound);
            sound.start();
        }
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

}
