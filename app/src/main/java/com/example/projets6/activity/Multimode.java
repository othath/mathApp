package com.example.projets6.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projets6.Equation;
import com.example.projets6.R;
import com.example.projets6.RunningFow;
import com.example.projets6.activity_end_fox;
import com.example.projets6.activity_winFox;
import com.example.projets6.multiplayer_wait;

import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

public class Multimode extends AppCompatActivity {



    activity_classicmode classicmode = new activity_classicmode();
    TextView answer, timertext, textEquation, goodAnswertext;
    int lives = 3;
    int point,screenWidth, screenHeight;
    static int counter= 0;
    int goodAnswer = 0;
    float backgroundleftX,backgroundleftBarriereX;
    Timer timer;
    String res, an, adversaire;
    Equation eq = new Equation(5);
    GifImageView foxjump, foxrunning, foxjump2, foxrunning2;
    ImageView death1,death2,death3, backgroundfox1,backgroundfox2, backgroundfox3, backgroundfoxbarriere;
    Handler handler;
    MediaPlayer sound;
    Animation animSlide, clignotant;
    SharedPreferences prefs;
    CountDownTimer cdtimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimode);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        prefs = getSharedPreferences("MyApp", MODE_PRIVATE);

        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);

        adversaire = prefs.getString("adversaire", "UNKNOWN");

        TextView adversairetext = (TextView) findViewById(R.id.adversaire);
        adversairetext.setText("VS "+adversaire);



        if (sharedPreferences.getBoolean("value",true)) {
            sound = MediaPlayer.create(com.example.projets6.activity.Multimode.this, R.raw.runningfox);
            sound.start();
            sound.setLooping(true);


        }


        textEquation = findViewById(R.id.textequation);
        an="";
        point = prefs.getInt("score", 100);
        foxjump = (GifImageView) findViewById(R.id.foxjump);
        foxrunning = (GifImageView) findViewById(R.id.foxrunning);
        foxjump2 = (GifImageView) findViewById(R.id.foxjump2);
        foxrunning2 = (GifImageView) findViewById(R.id.foxrunning2);
        death1 = findViewById(R.id.death1);
        death2 = findViewById(R.id.death2);
        death3 = findViewById(R.id.death3);


        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenHeight = size.x;
        screenWidth = size.y;
        backgroundfox1 = findViewById(R.id.backgroundfox1);
        backgroundfox2 = findViewById(R.id.backgroundfox2);
        backgroundfox3 = findViewById(R.id.backgroundfox3);
        backgroundfoxbarriere = findViewById(R.id.barriere);
        backgroundfoxbarriere.setVisibility(View.INVISIBLE);
        backgroundfoxbarriere.setX(-100f);
        //backgroundfox2.setVisibility(View.INVISIBLE);

        animSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);

        clignotant = new AlphaAnimation(1, 0); //to change visibility from visible to invisible
        clignotant.setDuration(100); //1 second duration for each animation cycle
        clignotant.setInterpolator(new LinearInterpolator());
        clignotant.setRepeatCount(3); //repeating indefinitely
        clignotant.setRepeatMode(Animation.REVERSE); //animation will start from end point once ended.


        timer = new Timer();
        handler = new Handler();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                    }
                });
            }
        },0,20);
        goodAnswertext = findViewById(R.id.count);
        goodAnswertext.setText(Integer.toString(goodAnswer));
        timertext = findViewById(R.id.timer);
        answer = findViewById(R.id.inputanswer);
        answer.setShowSoftInputOnFocus(false);
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });
        displayGame();
    }

    private void changePos() {
        backgroundleftX-=10;
        if (backgroundfox1.getX() + backgroundfox1.getWidth()*2 < 0){
            backgroundleftX = screenWidth-2*backgroundfox1.getWidth()+80;
            backgroundfox1.setX(backgroundleftX);
            backgroundfox2.setX(backgroundleftX+backgroundfox1.getWidth());
            backgroundfox3.setX(backgroundleftX+backgroundfox1.getWidth()*2);
        }
        backgroundfox1.setX(backgroundleftX);
        backgroundfox2.setX(backgroundleftX+backgroundfox1.getWidth());
        backgroundfox3.setX(backgroundleftX+backgroundfox1.getWidth()*2);
    }
    public void changePosBarriere(){
        backgroundleftBarriereX-=10;
        if (backgroundfoxbarriere.getX()<0){
            backgroundleftBarriereX = screenWidth;
        }
        backgroundfoxbarriere.setX(backgroundleftBarriereX);
    }

    public void generateEquation() {
        switch (point / 100){
            case 6:
            case 7:
                eq= new Equation(5);
                break;
            case 8:
                eq= new Equation(6);
                break;
            case 9:
                eq= new Equation(7);
                break;
            case 10:
                eq= new Equation(8);
                break;
            default:
                eq = new Equation((int)point/100);
                break;
        }
        res = classicmode.StringToCalcul(eq.equations);
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
        timertext.setVisibility(View.VISIBLE);
        cdtimer = new CountDownTimer(20000, 10) {

            public void onTick(long millisUntilFinished) {
                NumberFormat f = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    f = new DecimalFormat("00");
                }
                long sec = (millisUntilFinished / 1000) % 60;
                long ms = (millisUntilFinished) / 10 % 60;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    timertext.setText(f.format(sec) + ":" + f.format(ms));
                }

                if (an.equals(res)) {
                    goodAnswer();
                }
            }

            public void onFinish() {
                answer.setText("");
                final Handler handler3 = new Handler();

                handler3.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        foxrunning.startAnimation(clignotant);
                    }
                }, 1500);

                final Handler handler6 = new Handler();

                handler6.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        foxrunning.startAnimation(clignotant);
                    }
                }, 1300);

                backgroundfoxbarriere.startAnimation(animSlide);
                lives-=1;
                if (lives == 2){
                    death3.setVisibility(View.VISIBLE);
                    SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
                    if (sharedPreferences.getBoolean("value2",true)) {
                        MediaPlayer sound = MediaPlayer.create(com.example.projets6.activity.Multimode.this, R.raw.lose_life);
                        sound.start();
                    }
                }
                if (lives == 1){

                    death2.setVisibility(View.VISIBLE);
                    SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
                    if (sharedPreferences.getBoolean("value2",true)) {
                        MediaPlayer sound = MediaPlayer.create(com.example.projets6.activity.Multimode.this, R.raw.lose_life);
                        sound.start();
                    }
                }
                if (lives == 0){

                    death1.setVisibility(View.VISIBLE);
                    prefs.edit().putInt("myscoregame", counter).commit();

                    SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
                    if (sharedPreferences.getBoolean("value2",true)) {
                        MediaPlayer sound = MediaPlayer.create(com.example.projets6.activity.Multimode.this, R.raw.lose_life);
                        sound.start();
                    }
                    gotoend();
                }
                if (lives !=0){
                    displayGame();
                }
            }
        }.start();
    }



    public void goodAnswer(){
        goodAnswer+=1;
        backgroundfoxbarriere.startAnimation(animSlide);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value2",true)) {
            MediaPlayer sound= MediaPlayer.create(com.example.projets6.activity.Multimode.this,R.raw.bonne_reponse);
            sound.start();
        }
        goodAnswertext.setText(Integer.toString(goodAnswer));
        answer.setText("");
        timertext.setVisibility(View.INVISIBLE);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                foxrunning.setVisibility(View.INVISIBLE);
                foxjump.setVisibility(View.VISIBLE);
            }
        }, 1100);
        Handler handler2 = new Handler();

        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                foxrunning.setVisibility(View.VISIBLE);
                foxjump.setVisibility(View.GONE);


            }
        }, 2100);

        counter++;
        displayGame();
    }

    public void sound_ui(){
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value2",true)) {
            MediaPlayer sound = MediaPlayer.create(com.example.projets6.activity.Multimode.this, R.raw.ui_sound);
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
        if (sharedPreferences.getBoolean("value2",true)) {
            MediaPlayer sound = MediaPlayer.create(com.example.projets6.activity.Multimode.this, R.raw.ui_sound);
            sound.start();
        }
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        if (sharedPreferences.getBoolean("value",true)) {
            sound.stop();
        }

    }

    public void gotoend(){
        Intent intent = new Intent(this, multiplayer_wait.class);
        startActivity(intent);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value",true)) {
            sound.stop();
        }
    }
    public static int getcount(){
        return counter;
    }

    @Override
    protected void onStop() {
        super.onStop();
        cdtimer.cancel();
    }

}