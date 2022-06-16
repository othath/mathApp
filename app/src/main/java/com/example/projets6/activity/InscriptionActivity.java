package com.example.projets6.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.projets6.Player;
import com.example.projets6.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;
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
    Context context2;
    Resources resources;
    TextView messageView;
    TextView messageView2;
    TextView messageView3;
    TextView messageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);


        messageView = (TextView) findViewById(R.id.signUpText);
        messageView2 = (TextView) findViewById(R.id.signUpText2);
        messageView3 = (TextView) findViewById(R.id.signUpButton);
        messageView4 = (TextView) findViewById(R.id.tologIn);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("langue2",true)) {
            context2 = LocaleHelper.setLocale(InscriptionActivity.this, "hi");
            resources = context2.getResources();
            messageView.setText(resources.getString(R.string.signUpTitle));
            messageView2.setText(resources.getString(R.string.signInText2));
            messageView3.setText(resources.getString(R.string.signInBtn));
            messageView4.setText(resources.getString(R.string.toLogIn));

        }
        else{
            context2 = LocaleHelper.setLocale(InscriptionActivity.this, "fr");
            resources = context2.getResources();
            messageView.setText(resources.getString(R.string.signUpTitle));
            messageView2.setText(resources.getString(R.string.signInText2));
            messageView3.setText(resources.getString(R.string.signInBtn));
            messageView4.setText(resources.getString(R.string.toLogIn));


        }
        signUp = (Button) findViewById(R.id.signUpButton);
        toLogIn = (Button) findViewById(R.id.tologIn);
        confirmPasswordField = (EditText) findViewById(R.id.confirmPasswordField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        userField = (EditText) findViewById(R.id.userField);
        userMessage=(TextView)findViewById(R.id.userMessage);

        mDatabase = FirebaseDatabase.getInstance().getReference("Player");
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(signupFormIsValid()){
                    Player j=new Player(userField.getText().toString()
                    ,passEncrypted,100,false);
                    mDatabase.orderByChild("userName").equalTo(userField.getText().toString()). ///where username= usr
                            addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                userMessage.setText("userName Taken");
                            }
                            else{
                                userMessage.setText("GOOD CHOICE");// idee d'attendre 5sec et switchi to the mainGam
                                mDatabase.child(j.getUserName()).setValue(j);
                                switchMainActivity(4000);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }

            }
        });
        toLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(InscriptionActivity.this,logInActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
public void switchMainActivity(int timeout){ //timeout = 4000 make the activity visible for 4 seconds

    Timer timer = new Timer();
    timer.schedule(new TimerTask() {

        @Override
        public void run() {
            finish();
            SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
            prefs.edit().putString("username", userField.getText().toString()).commit();
            prefs.edit().putInt("score", 100).commit();
            prefs.edit().putBoolean("multijoueur",false).commit();
            Intent i = new Intent(InscriptionActivity.this, MainActivity.class);
            startActivity(i);
        }
    }, timeout);

}
   /* public void signUp(Player p) {
        mDatabase = FirebaseDatabase.getInstance().getReference("Player");
        mDatabase.child(p.getUserName()).setValue(p);

    }*/

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
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
}
