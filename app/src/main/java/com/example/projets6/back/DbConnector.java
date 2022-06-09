package com.example.projets6.back;

import android.os.StrictMode;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.projets6.Player;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnector {
    private DatabaseReference mDatabase;


    public void signUp(Player p) {
        mDatabase = FirebaseDatabase.getInstance().getReference("Player");
        mDatabase.child(p.getUserName()).setValue(p);
    }


   /* public Connection getConnection() {

            String databaseName = "6NKcrYGk1m";
            String databaseUser = "6NKcrYGk1m";
            String databasePassword = "MmXYuswXMr";
            String url = "jdbc:mysql://remotemysql.com/"+databaseName;


            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
            } catch (Exception e){
                e.printStackTrace();
            }
            return databaseLink;
        }*/

}


