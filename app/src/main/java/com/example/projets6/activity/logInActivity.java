package com.example.projets6.activity;



import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import com.example.projets6.MainActivity;
import com.example.projets6.Player;
import com.example.projets6.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
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
                }
            });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Log.i("DEBUG",userField.getText().toString());
                    logIn(userField.getText().toString());

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
                int score=snapshot.child("score").getValue(int.class);
                Player p=new Player(userName,passDb,score);
                if(passDb.equals(passwordField.getText().toString())){
                    Log.i("DEBUG",passwordField.getText().toString());
                    Intent i = new Intent(logInActivity.this, MainActivity.class);
                    i.putExtra("userName",userName);
                    i.putExtra("password",passDb);
                    startActivity(i);

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
            userMessage.setText("Wrong !");
            return false;
        } else {
            userMessage.setText("true");
            return true;
        }
    }

/*    public boolean signupFormIsValid() {
        if (!p.matcher(userField.getText()).matches()) {
            signUpMessage.setText("Wrong email !");
            return false;
        } else if (!passEncrypted.equals(confirmPassEncrypted)) {
            signUpMessage.setText("Passwords don't match");
            return false;
        } else {
            signUpMessage.setText("");
            return true;
        }
    }*/

    /*public void connectBtn(View view) {

        Log.i("DEBUG","on fucntion");
        if (loginFormIsValid()) {
                String connectQuery = "SELECT id,name,email, password FROM player WHERE email='" + userField.getText() + "' and password='" + passEncrypted + "';";

                if () {
              /*  if (queryOutput.next()) {
                    if (queryOutput.getString("email").contains(userField.getText()) && queryOutput.getString("password").contains(passEncrypted)) {
                        //then show l'interface de jeu
                    }*/

              /*  } else {
                    userMessage.setText("Account not found !");
                }
        }
        /*else if(signupFormIsValid()) {
            try{
                String connectQuery = "select count(id) from player;";
                Statement statement = connectDB.createStatement();
                ResultSet queryOutput = statement.executeQuery(connectQuery);
                queryOutput.next();
                //int current_id = queryOutput.getInt(1) + 1;
                String name =signUpName.getText().toString();
                String createQuery = String.format("INSERT INTO player(userName,password) VALUES('%s','%s')",userField.getText(), passEncrypted);
                statement.executeUpdate(createQuery);
                signUpMessage.setText("Account created successfully !");
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        //}


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

