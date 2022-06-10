package com.example.projets6.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projets6.Player;
import com.example.projets6.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class InscriptionActivity extends Activity {
    private DatabaseReference mDatabase;
    private Button signUp;
    private Button toLogIn;
    private EditText userField;
    private TextView userMessage;
    private EditText passwordField;
    private EditText confirmPasswordField;
    private String passEncrypted;
    private String confirmPassEncrypted;
    public Pattern p = Pattern.compile("^[A-Za-z]+[A-Za-z0-9._-]+");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        signUp = (Button) findViewById(R.id.signUpButton);
        toLogIn = (Button) findViewById(R.id.tologIn);
        confirmPasswordField = (EditText) findViewById(R.id.confirmPasswordField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        userField = (EditText) findViewById(R.id.userField);
        userMessage=(TextView)findViewById(R.id.userMessage);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(signupFormIsValid()){
                    Player j=new Player(userField.getText().toString()
                    ,passEncrypted,10);
                    Log.i("DEBUG",passEncrypted);
                    signUp(j);
                }

            }
        });


    }

    public void signUp(Player p) {
        mDatabase = FirebaseDatabase.getInstance().getReference("Player");
        mDatabase.child(p.getUserName()).setValue(p);
    }

    public boolean signupFormIsValid() {
        passEncrypted = getSHA(passwordField.getText().toString());
        confirmPassEncrypted = getSHA(confirmPasswordField.getText().toString());

        if (!p.matcher(userField.getText()).matches()) {
            userMessage.setText("Wrong userName !");
            return false;
        } else if (!passEncrypted.equals(confirmPassEncrypted)) {
            userMessage.setText("Passwords don't match");
            return false;
        } else {
            userMessage.setText("s");
            return true;
        }
    }

    public static String getSHA(String input) {

        try {

            // Static getInstance method is called with hashing SHA
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // digest() method called
            // to calculate message digest of an input
            // and return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
