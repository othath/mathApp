import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



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


    /*public String minusXinf10(){
        String res = Integer.toString(-((int)(Math.random()*10)) -1);
        return res; //chiffre entre 1 et 10
    }*/







}
