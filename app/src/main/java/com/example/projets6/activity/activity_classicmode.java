package com.example.projets6.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.SpannableStringBuilder;
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

import java.util.Timer;
import java.util.TimerTask;

public class activity_classicmode extends AppCompatActivity {
    EditText answer;
    MainActivity main = new MainActivity();
    int point = main.point;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classicmode);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

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
        moovY = 400;
        coordY =moovY;


        //equation = new Equation(point);
        generatEquation();
        textpoint = findViewById(R.id.points);
        textpoint.setText("Score : " +Integer.toString((int)(point)));
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
    }

    private void updateAnswer(String strToAdd){
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
            main.setPoint(point);
            SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
            if (sharedPreferences.getBoolean("value2",true)) {
                MediaPlayer sound= MediaPlayer.create(activity_classicmode.this,R.raw.bonne_reponse);
                sound.start();
            }
            textpoint.setText("Score : "+Integer.toString((int)(point)));



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
    public void button0(View view){
        updateAnswer("0");
    }
    public void button1(View view){
        updateAnswer("1");
    }
    public void button2(View view){
        updateAnswer("2");
    }
    public void button3(View view){
        updateAnswer("3");
    }
    public void button4(View view){
        updateAnswer("4");
    }
    public void button5(View view){
        updateAnswer("5");
    }
    public void button6(View view){
        updateAnswer("6");
    }
    public void button7(View view){
        updateAnswer("7");
    }
    public void button8(View view){
        updateAnswer("8");
    }
    public void button9(View view){
        updateAnswer("9");
    }
    public void buttonminus(View view){
        updateAnswer("-");
    }
    public void buttoncomma(View view){
        updateAnswer(",");
    }
    public void buttondivision(View view){
        updateAnswer("/");
    }
    public void buttonskip(View view){
        updateAnswer("");
        generatEquation();
        if (point>0) {
            point -= 5;
            main.setPoint(point);
            //animation -5
            handler = new Handler();
            timer = new Timer();
            coordY = moovY;
            count = 0;
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
        textpoint.setText("Score : " +Integer.toString((int)(point)));

    }



    public void buttondel(View view){
        int textLen = answer.getText().length();
        if(textLen!=0){
            SpannableStringBuilder selection = (SpannableStringBuilder)  answer.getText();
            selection.replace(textLen-1 , textLen,"");
            answer.setText(selection);
        }
    }
    public void buttondelall(View view){
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
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

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
}