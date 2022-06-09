package com.example.projets6;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.projets6.R;

import org.mariuszgromada.math.mxparser.Expression;

public class TestAnswer extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classicmode);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

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

    /*public int Occurence(String text, String lettre){
        int c = 0;
        for (int j = 0; j < text.length(); j++) {
            if (text.contains(lettre)) {
                c += 1;
            }
        }
        return c;
    }
    public int CalculX(String equation){

        // s'il ya plus de 2x
        if(Occurence(equation,"x") >1){
            //il faut récuperer les éléments avec des x

            //il faut additioner ou soustraire les coefficients des elements avec les x

            //il faut ajouter les elements sans x

            //isoler x

        }

        //s'il ya qu'1 seul x

        return 1;

    }*/


    public boolean isCorrectAnswer(String sol, String rep_joueur){
        if(sol == rep_joueur){
            return true;
        }
        else{
            return false;
        }
    }

    /*public static int getCoefficient(String variable){
        String coefficient = "";
        if(variable.length() == 1) return 1;
        else if(variable.length() == 2 && variable.charAt(0) == '-') return -1;

        for(int i = 0; i < variable.length(); i++){
            if(Character.isDigit(variable.charAt(i)))coefficient+=variable.charAt(i);
        }
        if(variable.charAt(0) == '-')return Integer.parseInt("-" + coefficient);
        return Integer.parseInt(coefficient);
    }*/

}




