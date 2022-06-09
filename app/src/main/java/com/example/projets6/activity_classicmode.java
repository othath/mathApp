package com.example.projets6;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

public class activity_classicmode<editingActionListener> extends AppCompatActivity {
    EditText answer;
    int point = 10;
    Equation eq = new Equation(point);
    String res;
    TextView textequation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classicmode);
        answer = findViewById(R.id.inputanswer);
        answer.setShowSoftInputOnFocus(false);
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        //equation = new Equation(point);
        res =StringToCalcul(eq.equations);
        textequation = findViewById(R.id.textequation);
        textequation.setText(eq.equations);
    }

    private void updateAnswer(String strToAdd){
        String oldString = answer.getText().toString();
        int cursorPos = answer.getSelectionStart();
        String leftStr = oldString.substring(0,cursorPos);
        String rightStr = oldString.substring(cursorPos);
        answer.setText(String.format("%s%s%s",rightStr,strToAdd,leftStr));

        String an = StringToCalcul(answer.getText().toString());

        if (res.equals(an)){
            textequation.setText("oui");
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
}