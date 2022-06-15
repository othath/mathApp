package com.example.projets6.activity;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import com.example.projets6.Player;
import com.example.projets6.R;
import com.example.projets6.loading_screen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class logInActivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText userField;
    private EditText passwordField;//en XML  android:inputType="Password"
    private EditText confirmPasswordField;
   // public Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    //Pattern.compile("^(.+)@(.+)$");
    public Pattern p=Pattern.compile("^[A-Za-z]+[A-Za-z0-9._-]+");
    private Button toSignUp;
    private TextView userMessage;
    private TextView signUpMessage;
    private EditText signUpName;
    private EditText signUpUser;
    private Context context;
    private DatabaseReference mDatabase;
    Context context2;
    Resources resources;
    TextView messageView;
    TextView messageView2;
    TextView messageView3;
    TextView messageView4;
    TextView messageView5;
    TextView messageView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_connexion);
        messageView = (TextView) findViewById(R.id.signInText);
        messageView2 = (TextView) findViewById(R.id.signInText2);
        messageView3 = (TextView) findViewById(R.id.userField);
        messageView4 = (TextView) findViewById(R.id.passwordField);
        messageView5 = (TextView) findViewById(R.id.logInBtn);
        messageView6 = (TextView) findViewById(R.id.toSignUp);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("langue2",true)) {
            context2 = LocaleHelper.setLocale(logInActivity.this, "hi");
            resources = context2.getResources();
            messageView.setText(resources.getString(R.string.singInTitle));
            messageView2.setText(resources.getString(R.string.signInText2));
          /*  messageView3.setText(resources.getString(R.string.hintUser));
            messageView4.setText(resources.getString(R.string.hintPassword));*/
            messageView5.setText(resources.getString(R.string.logInBtn));
            messageView6.setText(resources.getString(R.string.toSignUp));

        }
        else{
            context2 = LocaleHelper.setLocale(logInActivity.this, "fr");
            resources = context2.getResources();
            messageView.setText(resources.getString(R.string.singInTitle));
            messageView2.setText(resources.getString(R.string.signInText2));
          /*  messageView3.setText(resources.getString(R.string.hintUser));
            messageView4.setText(resources.getString(R.string.hintPassword));*/
            messageView5.setText(resources.getString(R.string.logInBtn));
            messageView6.setText(resources.getString(R.string.toSignUp));


        }
        mDatabase = FirebaseDatabase.getInstance().getReference("Player");
        loginBtn=(Button)findViewById(R.id.logInBtn);
        toSignUp=(Button)findViewById(R.id.toSignUp) ;


        userMessage=(TextView) findViewById(R.id.userMessage);
        //signInMessage=findViewById(R.id.signInMessage);
        //signInUser=findViewById(R.id.)
            userField=(EditText) findViewById(R.id.userField);
            passwordField=(EditText) findViewById(R.id.passwordField);
            toSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(logInActivity.this, InscriptionActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                }
            });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(loginFormIsValid()) {
                        logIn(userField.getText().toString());
                    }
            }

        });

    }

    public void logIn( String userName) {
                mDatabase.child(userName).addListenerForSingleValueEvent(valueEventListener); //listner on userName he msut be unique
    }
    ValueEventListener valueEventListener =new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Log.i("DEBUG",snapshot.toString());
            if(snapshot.exists()){
                String userName=snapshot.child("userName").getValue(String.class);
                String passEncrypted=getSHA(passwordField.getText().toString());
                String passDb=snapshot.child("password").getValue(String.class);
                int score=snapshot.child("score").getValue(Integer.class);
                boolean multij=snapshot.child("multijoueur").getValue(Boolean.class);
                Player p=new Player(userName,passDb, score,false);
                if(passDb.equals(passEncrypted)){//passEncrypted
                    Intent i = new Intent(logInActivity.this, MainActivity.class);
                    SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
                    prefs.edit().putString("username", userName).commit();
                    prefs.edit().putInt("score", score).commit();
                    prefs.edit().putBoolean("multijoueur",multij).commit();

                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);

                    userMessage.setText("successful");
                }
                else {
                    Log.i("DEBUG","wrong");
                    userMessage.setText("Wrong Password");
                }
            }
            else {
                Log.i("DEBUG","not found");
                userMessage.setText("Account not found !");
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            userMessage.setText("Error");
            Log.i("DEBUG",error.toString());
        }
    };


    //private  String passEncrypted=getSHA(passwordField.getText().toString());
    //private String confirmPassEncrypted=getSHA(confirmPasswordField.getText().toString());

    public boolean loginFormIsValid() {
        if (!p.matcher(userField.getText()).matches()) {
            userMessage.setText("userName Invalid !");
            return false;
        } else {
            userMessage.setText("true");
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
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown"
                    + " for incorrect algorithm: " + e);

            return null;
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
}

