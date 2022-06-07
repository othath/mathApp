package com.example.projets6.back;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnector {

        public Connection databaseLink;

        public Connection getConnection(){
            String databaseName = "6NKcrYGk1m";
            String databaseUser = "6NKcrYGk1m";
            String databasePassword = "MmXYuswXMr";
            String url = "jdbc:mysql://remotemysql.com/"+databaseName;


            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
            } catch (Exception e){
                e.printStackTrace();
            }
            return databaseLink;
        }
    }


