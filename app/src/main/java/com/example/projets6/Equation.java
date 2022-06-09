package com.example.projets6;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


public class Equation {
    int points;
    String equations;
    public Equation(int points){
        this.points = points;
        this.equations = generateEquation(points);

    }

    private String generateEquation(int points) {

        return "equation";
    }
//niveau 1

    public String un(){
        return "+1";
    }
    public String cinq(){
        return "+5";
    }
    public String Xmodulo10(){

        String res ="+" + Integer.toString(((int)(Math.random()*10))*10 +10);
        return res;// nombre entre 10 et 100
    }

    //Niveau 2

    public String Xinf10(){
        String res = "+"+Integer.toString(((int)(Math.random()*10)) +1);
        return res; //chiffre entre 1 et 10
    }

    public String Xmodulo5(){
        int pas=5;
        Random rand = new Random();
        List<Integer> modulo = new ArrayList<>();
        modulo.add(pas);
        for(int i=1; i<20; i++) {
            pas += 5;
            modulo.add(pas);
        }
        String res = "+"+Integer.toString(modulo.get(rand.nextInt(modulo.size())));
        return res; // renvoie des nombres entre 5 et 100 avec un pas de 5

    }

    public String Xmodulo2(){
        int pas=2;
        Random rand = new Random();
        List<Integer> modulo = new ArrayList<>();
        modulo.add(pas);
        for(int i=1; i<50; i++) {
            pas += 2;
            modulo.add(pas);
        }
        String res="+"+Integer.toString(modulo.get(rand.nextInt(modulo.size())));
        return res; // renvoie des nombres entre 2 et 100 avec un pas de 2

    }

    public String minusXmodulo10(){

        return Integer.toString((-((int)(Math.random()*10))*10 -10));// nombre entre 10 et 100
    }
    public String times2(){

        return "*2";
    }


    //Niveau 3

    public String Xinf100sup5(){
        String res = "+"+Integer.toString(((int)(Math.random()*10))*10);
        return res;
    }


    public String minusXinf10(){
        String res = Integer.toString(-((int)(Math.random()*10)) -1);
        return res;
    }

    public String divisionXmodulo10(){

        String res ="/" + Integer.toString(((int)(Math.random()*10))*10 +10);
        return res;
    }

    //Niveau 4

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String minusX100(){
        int[] arr = IntStream.rangeClosed(11, 100).toArray();
        int r = (new Random()).nextInt(89);
        String res="-" + Integer.toString(arr[r]);
        return res;
    }

    public String diveXmodulo2(){
        int pas=2;
        Random rand = new Random();
        List<Integer> modulo = new ArrayList<>();
        modulo.add(pas);
        for(int i=1; i<50; i++) {
            pas += 2;
            modulo.add(pas);
        }
        String res="/"+ Integer.toString(modulo.get(rand.nextInt(modulo.size())));
        return res;

    }

    //Niveau 5

    public String timesXinf10(){
        String res = "*" + Integer.toString(((int)(Math.random()*10)) -1);
        return res;
    }

    //Niveau 6

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String timesminusX100(){
        int[] arr = IntStream.rangeClosed(11, 100).toArray();
        int r = (new Random()).nextInt(89);
        String res="*" + Integer.toString(arr[r]);
        return res;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String Xcoeff10(){
        int[] arr = IntStream.rangeClosed(2, 10).toArray();
        int r = (new Random()).nextInt(8);
        String res= "+"+Integer.toString(arr[r])+"^2";
        return res;
    }


    //{2,3,4,5,6,7,8,9,10,11,12}

    public String Xsqrt(){
        Random rand = new Random();
        List<Integer> square = new ArrayList<>();
        square.add(2);
        square.add(3);
        square.add(4);
        square.add(5);
        square.add(6);
        square.add(7);
        square.add(8);
        square.add(9);
        square.add(10);

        String res="+"+ Integer.toString(square.get(rand.nextInt(square.size())))+"^(1/2)";
        return res;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String Xcoeff100(){
        int[] arr = IntStream.rangeClosed(101, 1000).toArray();
        int r = (new Random()).nextInt(899);
        String res= "+"+Integer.toString(arr[r]);
        return res;
    }

    //Niveau 7

    public String divinf10(){
        String res = "/"+Integer.toString(((int)(Math.random()*10))+1);
        return res;
    }

    //Niveau 8

    public String Xsqrt2(){
        Random rand = new Random();
        List<Integer> square = new ArrayList<>();
        square.add(11);
        square.add(12);
        square.add(13);
        square.add(14);
        square.add(15);
        square.add(16);
        square.add(18);
        square.add(19);
        square.add(20);

        String res="+"+ Integer.toString(square.get(rand.nextInt(square.size())))+"^2";
        return res;
    }

    //Niveau 9

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String divXinf10(){
        int[] arr = IntStream.rangeClosed(2, 9).toArray();
        int r = (new Random()).nextInt(7);
        String res= "/"+Integer.toString(arr[r]);
        return res;
    }

    public String Xsqrt3(){
        Random rand = new Random();
        List<Integer> element = new ArrayList<>();
        List<Integer> square = new ArrayList<>();
        element.add(2);
        element.add(3);
        element.add(4);
        element.add(5);
        element.add(6);
        element.add(7);
        element.add(8);
        element.add(9);
        element.add(10);
        String res="+"+ Integer.toString(element.get(rand.nextInt(element.size())))+"^3";
        return res;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public String Xsup100sqrt(){
        int[] arr = IntStream.rangeClosed(2, 100).toArray();
        int r = (new Random()).nextInt(98);
        String res= "+"+Integer.toString(arr[r])+"^(1/2)";
        return res;
    }

    //Niveau 10

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String Xsup10sqrtY(){
        int[] arr = IntStream.rangeClosed(11, 100).   toArray();
        int r = (new Random()).nextInt(89);
        int[] sqrt = IntStream.rangeClosed(2, 9).toArray();
        int coeff = (new Random()).nextInt(7);
        String res= Integer.toString(arr[r])+"^"+Integer.toString(sqrt[coeff]);
        return res;
    }

}
