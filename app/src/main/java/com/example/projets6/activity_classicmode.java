package com.example.projets6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class activity_classicmode<editingActionListener> extends AppCompatActivity {
    EditText answer;
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
        TextView textequation = findViewById(R.id.textequation);
    }

    private void updateAnswer(String strToAdd){
        String oldString = answer.getText().toString();
        int cursorPos = answer.getSelectionStart();
        String leftStr = oldString.substring(0,cursorPos);
        String rightStr = oldString.substring(cursorPos);
        answer.setText(String.format("%s%s%s",rightStr,strToAdd,leftStr));
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
}