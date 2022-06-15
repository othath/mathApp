package com.example.projets6;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Equation {
    public String equations;

    public Equation(int points){
        this.equations = generateEquation(points);
    }

    private String generateEquation(int points) {
        String res="";
        int r;
        switch(points){
            case 0:
                res = generate1()+generate1();
                break;
            case 1:
                res = generate1()+generate1();
                break;
            case 2:
                res = generate1()+generate1();
                break;
            case 3:
                res = generate1()+generate2();
                break;
            case 4:
                r=(new Random()).nextInt(2)+1;
                if (r==1){
                    res = generate1()+generate3();
                }else{
                    res = generate2()+generate2();
                }
                break;
            case 5:
                r=(new Random()).nextInt(2)+1;
                if (r==1){
                    res = generate4()+generate1();
                }else{
                    res = generate3()+generate2();
                }
                break;
            case 6:
                r=(new Random()).nextInt(3)+1;
                if (r==1){
                    res = generate3()+generate3();
                }else if (r==2){
                    res = generate5()+generate1();
                }else{
                    res = generate4()+generate2();
                }
                break;
            case 7:
                r=(new Random()).nextInt(3)+1;
                if (r==1){
                    res = generate6()+generate1();
                }else if (r==2){
                    res = generate5()+generate2();
                }else{
                    res = generate4()+generate3();
                }
                break;
            case 8:
                r=(new Random()).nextInt(4)+1;
                if (r==1){
                    res = generate7()+generate1();
                }else if (r==2){
                    res = generate6()+generate2();
                }else if (r==3){
                    res = generate4()+generate4();
                }else{
                    res = generate5()+generate3();
                }
                break;
            case 9:
                r=(new Random()).nextInt(5)+1;
                if (r==1){
                    res = generate8()+generate1();
                }else if (r==2){
                    res = generate7()+generate2();
                }else if (r==3){
                    res = generate6()+generate3();
                }else if (r==4){
                    res = generate5()+generate4();
                }else{
                    res = generate4()+generate5();
                }
                break;
            default:
                r=(new Random()).nextInt(5)+1;
                if (r==1){
                    res = generate9()+generate1();
                }else if (r==2){
                    res = generate8()+generate2();
                }else if (r==3){
                    res = generate7()+generate3();
                }else if (r==4){
                    res = generate5()+generate5();
                }else{
                    res = generate6()+generate4();
                }
                break;


        }
        return Integer.toString(new Random().nextInt(28)+1)+res;
    }


    public String generate1(){
        int r = (new Random()).nextInt(3)+1;
        String res="";
        switch(r){
            case 1:
                res = un();
                break;
            case 2:
                res = cinq();
                break;
            case 3:
                res = Xmodulo10();
                break;
        }
        return res;
    }

    public String generate2(){
        int r = (new Random()).nextInt(4)+1;
        String res="";
        switch(r){
            case 1:
                res = Xinf10();
                break;
            case 2:
                res = Xmodulo5();
                break;
            case 3:
                res = Xmodulo2();
                break;
            case 4:
                res = times2();
        }
        return res;
    }
    public String generate3(){
        int r = (new Random()).nextInt(4)+1;
        String res="";
        switch(r) {
            case 1:
                res = Xinf100sup5();
                break;
            case 2:
                res = minusXinf10();
                break;
            case 3:
                res = minusXmodulo10();
                break;
            case 4:
                res = timesXmodulo10();
                break;
        }
        return res;
    }
    public String generate4(){
        int r = (new Random()).nextInt(2)+1;
        String res="";
        switch(r) {
            case 1:
                res = minusX100();
                break;
            case 2:
                res= times3to9();
                break;
        }
        return res;
    }
    public String generate5(){
        int r = (new Random()).nextInt(2)+1;
        String res="";
        switch(r) {
            case 1:
                res = timesminusX100();
                break;
            case 2:
                res = diveXmodulo2();
                break;
        }
        return res;
    }
    public String generate6(){
        int r = (new Random()).nextInt(3)+1;
        String res="";
        switch(r){
            case 1:
                res = timesXinf10();
                break;
            case 2:
                res = Xcoeff10();
                break;
            case 3:
                res = Xcoeff100();
                break;
        }
        return res;
    }
    public String generate7(){
        String res=divinf10();

        return res;
    }
    public String generate8(){
        String res=Xsqrt2();
        return res;
    }
    public String generate9(){
        int r = (new Random()).nextInt(2)+1;
        String res="";
        switch(r){
            case 1:
                res = Xsup100sqrt();
                break;
            case 2:
                res = Xsqrt3();
                break;

        }
        return res;
    }
    public String generate10(){
        String res=Xsup10sqrtY();
        return res;
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

    public String timesXmodulo10(){
        String res="";
        res= "*" +"("+Integer.toString((-((int)(Math.random()*10))*10 -10))+")";
        return res;// nombre entre 10 et 100
    }

    public String times2(){return "*2";}


    //Niveau 3

    public String Xinf100sup5(){
        String res = "+"+Integer.toString(((int)(Math.random()*10))*10);
        return res;
    }


    public String minusXinf10(){
        String res = Integer.toString(-((int)(Math.random()*10)) -1);
        return res;
    }

    /*public String divisionXmodulo10(){

        String res ="/" + Integer.toString(((int)(Math.random()*10))*10 +10);
        return res;
    }*/

    //Niveau 4

    public String times3to9(){
        int res = (new Random()).nextInt(7)+3;
        return "*"+res;
    }


    public String minusX100(){
        int r = (new Random()).nextInt(90)+10;
        String res="-" + Integer.toString(r);
        return res;
    }

    public String diveXmodulo2(){
        int pas=2;
        Random rand = new Random();
        Random rand2 = new Random();
        List<Integer> modulo = new ArrayList<>();
        modulo.add(pas);
        for(int i=1; i<50; i++) {
            pas += 2;
            modulo.add(pas);

        }
        String res="+"+"("+Integer.toString(modulo.get(rand2.nextInt(modulo.size())))+"/"+ Integer.toString(modulo.get(rand.nextInt(modulo.size())))+")";
        return res;
    }

    //Niveau 5



    public String Xsqrt(){
        Random rand = new Random();
        List<Integer> element = new ArrayList<>();
        element.add(4);
        element.add(9);
        element.add(16);
        element.add(25);
        element.add(36);
        element.add(49);
        element.add(64);
        element.add(81);
        element.add(100);

        String res="+"+ Integer.toString(element.get(rand.nextInt(element.size())))+"^(1/2)";
        return res;
    }

    //Niveau 6

    public String timesminusX100(){
        int r = (new Random()).nextInt(90)+10;
        String res="*" + Integer.toString(r);
        return res;
    }

    public String Xcoeff10(){
        int r = (new Random()).nextInt(10)+1;
        String res= "+"+Integer.toString(r)+"^(2)";
        return res;
    }

    public String timesXinf10(){
        String res = "*" + Integer.toString(((int)(Math.random()*10)) -1);
        if(res.equals("*0")){
            res="*7";
        }
        else if(res.equals("*1") ){
            res ="*8";
        }
        return res;
    }


    public String Xcoeff100(){
        int r = (new Random()).nextInt(900)+100;
        String res= "+"+Integer.toString(r);
        return res;
    }

    //Niveau 7

    public String divinf10(){
        Random rand = new Random();
        List<Integer> square = new ArrayList<>();
        square.add(2);
        square.add(4);
        square.add(5);
        square.add(6);
        square.add(7);
        square.add(8);
        square.add(9);
        String res = "+"+"("+ Integer.toString((new Random()).nextInt(90)+20)+"/"+Integer.toString(square.get(rand.nextInt(square.size())))+")";
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

        String res="+"+ Integer.toString(square.get(rand.nextInt(square.size())))+"^(2)";
        return res;
    }

    //Niveau 9

   /* @RequiresApi(api = Build.VERSION_CODES.N)
    public String divXinf10(){
        int[] arr = IntStream.rangeClosed(2, 9).toArray();
        int r = (new Random()).nextInt(7);
        String res= "/"+Integer.toString(arr[r]);
        return res;
    }*/

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
        String res="+"+ Integer.toString(element.get(rand.nextInt(element.size())))+"^(3)";
        return res;
    }


    public String Xsup100sqrt(){
        Random rand = new Random();
        List<Integer> element = new ArrayList<>();
        element.add(121);
        element.add(144);
        element.add(169);
        element.add(196);
        element.add(225);
        element.add(256);
        element.add(289);
        element.add(324);
        element.add(361);
        element.add(400);
        element.add(441);
        element.add(484);
        element.add(529);
        element.add(576);
        element.add(625);
        element.add(676);
        element.add(729);
        element.add(784);
        element.add(841);
        element.add(900);
        element.add(961);
        element.add(1024);
        String res= "+"+Integer.toString(element.get(rand.nextInt(element.size())))+"^(1/2)";
        return res;
    }

    //Niveau 10

    public String Xsup10sqrtY(){
        int r = (new Random()).nextInt(90)+10;
        int coeff = (new Random()).nextInt(9)+1;
        String res= Integer.toString(r)+"^"+Integer.toString(coeff);
        return res;
    }


}
