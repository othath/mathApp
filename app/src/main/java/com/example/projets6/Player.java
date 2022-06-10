package com.example.projets6;

public class Player {
    private String userName, password;
    int score;

    public Player(String un, String pass, int score) {
        this.userName = un;
        this.password = pass;
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    public int getScore(){
        return score;
    }

}