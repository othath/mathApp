package com.example.projets6.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Sampler;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import org.mariuszgromada.math.mxparser.Expression;
import android.media.MediaPlayer;

import com.example.projets6.Equation;
import com.example.projets6.R;
import com.example.projets6.RunningFow;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class activity_classicmode extends AppCompatActivity {
    EditText answer;
    int point ;
    Equation eq;
    String res;
    TextView textequation;
    TextView textpoint;


    private TextView plusTen;
    private TextView minusFive;

    int count = 0;
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    int coordY,moovY;
    String userName;
    TextView messageView;
    Context context;
    Resources resources;
    SharedPreferences prefs;
    DatabaseReference mDatabase ;
    MediaPlayer sound2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classicmode);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value",true)) {
            sound2 = MediaPlayer.create(activity_classicmode.this, R.raw.classic);
            sound2.start();
            sound2.setLooping(true);
        }

        messageView = (TextView) findViewById(R.id.textView);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

         mDatabase = FirebaseDatabase.getInstance().getReference("Player");
         prefs = getSharedPreferences("MyApp", MODE_PRIVATE);

        if (sharedPreferences.getBoolean("langue2",true)) {
            context = LocaleHelper.setLocale(activity_classicmode.this, "hi");
            resources = context.getResources();
            messageView.setText(resources.getString(R.string.classicmode));


        }
        else{
            context = LocaleHelper.setLocale(activity_classicmode.this, "fr");
            resources = context.getResources();
            messageView.setText(resources.getString(R.string.classicmode));

        }

         point= prefs.getInt("score", 0);
         userName=prefs.getString("username","UNKNOWN");


        ImageButton retour=findViewById(R.id.retour);
        retour.setOnClickListener(v -> retour());

        answer = findViewById(R.id.inputanswer);
        answer.setShowSoftInputOnFocus(false);
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }

        });
        //animation
        plusTen =  (TextView)findViewById(R.id.plusTen);
        minusFive = (TextView)findViewById(R.id.minusFive);
        moovY = 330 ;
        coordY =moovY;


        //equation = new Equation(point);
        generatEquation();
        textpoint = findViewById(R.id.points);
        textpoint.setText("Score : " + String.valueOf(point));
        Log.i("user",userName);

    }



    private void changePosWin() {
        coordY -= 1;
        plusTen.setY(coordY);


    }
    private void  changeposLoose(){
        coordY -=1;
        minusFive.setY(coordY);
    }

    private void generatEquation(){
        eq= new Equation((int)(point/100));
        res = StringToCalcul(eq.equations);
        textequation = findViewById(R.id.textequation);
        textequation.setText(affichageEquation(eq.equations));
    }

    private void retour() {
        Intent intent = new Intent(this, com.example.projets6.activity.MainActivity.class);
        startActivity(intent);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value2",true)) {
            MediaPlayer sound = MediaPlayer.create(activity_classicmode.this, R.raw.ui_sound);
            sound.start();
        }
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        if (sound2.isPlaying()) {
            sound2.stop();
            sound2.release();
            sound2=null;
        }
    }

    public void updateAnswer(String strToAdd){
        String oldString = answer.getText().toString();
        int cursorPos = answer.getSelectionStart();
        String leftStr = oldString.substring(0,cursorPos);
        String rightStr = oldString.substring(cursorPos);
        answer.setText(String.format("%s%s%s",rightStr,strToAdd,leftStr));

        String an = StringToCalcul(answer.getText().toString());

        if (res.equals(an)){
            answer.setText("");
            generatEquation();
            textpoint = findViewById(R.id.points);
            point+=10;

            SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
            if (sharedPreferences.getBoolean("value2",true)) {
                MediaPlayer sound= MediaPlayer.create(activity_classicmode.this,R.raw.bonne_reponse);
                sound.start();
            }
            mDatabase.child(userName).child("score").setValue(point);
            prefs.edit().putInt("score", point).commit();
            textpoint.setText("Score : "+String.valueOf(point));



            //animation +10
            handler = new Handler();
            timer = new Timer();
            coordY = moovY;
            count = 0;
            plusTen.setVisibility(View.VISIBLE);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePosWin();
                            count+=1;
                            if (count>40){
                                plusTen.setVisibility(View.INVISIBLE);
                                timer.cancel();
                                timer.purge();

                            }
                        }
                    });
                }
            },0,15);

        }
    }
    public void sound_ui(){
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value2",true)) {
            MediaPlayer sound = MediaPlayer.create(activity_classicmode.this, R.raw.ui_sound);
            sound.start();
        }
    }
    public void button0(View view){
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value2",true)) {
            MediaPlayer sound = MediaPlayer.create(activity_classicmode.this, R.raw.ui_sound);
            sound.start();
        }
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
    public void buttonskip(View view){
        sound_ui();
        updateAnswer("");
        generatEquation();
        if (point>0) {
            point -= 5;
            //animation -5
            handler = new Handler();
            timer = new Timer();
            coordY = moovY;
            count = 0;

            mDatabase.child(userName).child("score").setValue(point);
            prefs.edit().putInt("score", point).commit();

            minusFive.setVisibility(View.VISIBLE);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            changeposLoose();
                            count+=1;
                            if (count>40){
                                minusFive.setVisibility(View.INVISIBLE);
                                timer.cancel();
                            }
                        }
                    });
                }
            },0,15);
        }
        else {
            point = 0;
        }
        textpoint = findViewById(R.id.points);
        textpoint.setText("Score : " +String.valueOf(point));



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

    public String StringToCalcul(String exp){
        String sol="";

        if(!exp.contains("x") && !exp.contains("(")){
            sol =  calculSimple(exp);
        }
        if(!exp.contains("x") && exp.contains("(")){
            sol = calculSimple(exp);
        }
        if(exp.contains("x") && !exp.contains("(")){
            // sol = CalculX(exp);
        }
        if(exp.contains("x") && exp.contains("(")){
            // sol = CalculX(exp);
        }
        return sol;
    }

    public String calculSimple(String userExp){

        Expression exp = new Expression(userExp);
        String result = String.valueOf(exp.calculate());
        //int res = Integer.parseInt(result);
        return result;
    }


    public String affichageEquation(String equation){

        equation = equation.replaceAll("\\*","×");
        equation = equation.replace("^(2)", "² ");
        equation = equation.replace("^(3)", "³ ");
        equation = equation.replace("^(4)", "⁴ ");
        equation = equation.replace("^(5)", "⁵ ");
        equation = equation.replace("^(6)", "⁶ ");
        equation = equation.replace("^(7)", "⁷ ");
        equation = equation.replace("^(8)", "⁸ ");
        equation = equation.replace("^(9)", "⁹ ");

        if (equation.contains("+(")){
            String frac = equation.substring(equation.indexOf("+(")+2, equation.indexOf(")"));
            String numerateur= frac.split("/")[0], denominateur = frac.split("/")[1];
            equation = equation.replace("("+numerateur+"/"+denominateur+")"," "+numerateur+"/"+denominateur+ " ");
        }
        if (equation.contains("^(1/2)")){
            String res="";
            int i=i=equation.indexOf("^(1/2)");
            while(isInteger(String.valueOf(equation.charAt(i-1)))){
                res+=equation.charAt(i-1);
                i--;
            }
            res = new StringBuilder(res).reverse().toString();
            equation = equation.replace(res+"^(1/2)"," √("+res+") ");
        }
        return equation;
    }
    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

    public void finish() {
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("value2",true)) {
            MediaPlayer sound = MediaPlayer.create(activity_classicmode.this, R.raw.ui_sound);
            sound.start();
        }
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        if (sound2.isPlaying()) {
            sound2.stop();
            sound2.release();
            sound2=null;
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (sound2.isPlaying()){
            sound2.stop();
            if (isFinishing()){
                sound2.stop();
                sound2.release();
            }
        }
    }
}