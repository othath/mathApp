package com.example.projets6;

public class Player {
    private String userName, password;
    int score;
    boolean multijoueur;

    public Player(String un, String pass, int score,boolean multijoueur) {
        this.userName = un;
        this.password = pass;
        this.score = score;
        this.multijoueur=multijoueur;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isMultijoueur() {
        return multijoueur;
    }

    public void setMultijoueur(boolean multijoueur) {
        this.multijoueur = multijoueur;
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