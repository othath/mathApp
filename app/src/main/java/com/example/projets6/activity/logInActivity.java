package com.example.projets6.activity;



import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.BreakIterator;
import java.util.regex.Pattern;

import com.example.projets6.R;
import com.example.projets6.back.DbConnector;

public class logInActivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText userField;
    private EditText passwordField;//en XML  android:inputType="Password"
    private EditText confirmPasswordField;
    public Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    //Pattern.compile("^(.+)@(.+)$");
    private TextView userMessage;
    private TextView signupMessage;
    private Button signInOrUp;
    private EditText signUpName;
    private EditText signUpEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBtn=findViewById(R.id.logInBtn);
        userField=findViewById(R.id.userField);
        passwordField=findViewById(R.id.passwordField);
       // confirmPasswordField=findViewById(R.id.confirmPasswordField);
        userMessage=findViewById(R.id.userMessage);
        //signupMessage=findViewById(R.id.signupMessage);





    }



    private  String passEncrypted=getSHA(passwordField.getText().toString());
    private String confirmPassEncrypted=getSHA(confirmPasswordField.getText().toString());
    public boolean loginFormIsValid() {
        if (!p.matcher(userField.getText()).matches()) {
            userMessage.setText("Wrong !");
            return false;
        } else {
            userMessage.setText("");
            return true;
        }
    }

    public boolean signupFormIsValid() {
        if (!p.matcher(userField.getText()).matches()) {
            signupMessage.setText("Wrong email !");
            return false;
        } else if (!passEncrypted.equals(confirmPassEncrypted)) {
            signupMessage.setText("Passwords don't match");
            return false;
        } else {
            signupMessage.setText("");
            return true;
        }
    }

    public void ConnectBtn() {
        DbConnector connect = new DbConnector();
        Connection connectDB = connect.getConnection();

        if (loginFormIsValid()) {
            try {
                String connectQuery = "SELECT id,name,email, password FROM player WHERE email='" + userField.getText() + "' and password='" + passEncrypted + "';";
                Statement statement = connectDB.createStatement();
                ResultSet queryOutput = statement.executeQuery(connectQuery);

                if (queryOutput.next()) {
                    if (queryOutput.getString("email").contains(userField.getText()) && queryOutput.getString("password").contains(passEncrypted)) {
                        //then show l'interface de jeu
                    }

                } else {
                    userMessage.setText("Account not found !");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(signupFormIsValid()) {
            try{
                String connectQuery = "select count(id) from player;";
                Statement statement = connectDB.createStatement();
                ResultSet queryOutput = statement.executeQuery(connectQuery);
                queryOutput.next();
                int current_id = queryOutput.getInt(1) + 1;
                String name =signUpName.getText().toString();
                String createQuery = String.format("INSERT INTO client VALUES(%d,'%s','%s')",current_id,name, userField.getText(), passEncrypted);
                statement.executeUpdate(createQuery);
                signupMessage.setText("Account created successfully !");
            } catch (Exception e) {
                e.printStackTrace();
            }
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

}

