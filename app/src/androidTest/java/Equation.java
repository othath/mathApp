import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Equation {

    public String Xmodulo10(){

        return Integer.toString(((int)(Math.random()*10))*10 +10);// nombre entre 10 et 100
    }

    public String Xinf10(){
        return Integer.toString(((int)(Math.random()*10)) +1); //chiffre entre 1 et 10
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
        return Integer.toString(modulo.get(rand.nextInt(modulo.size()))); // renvoie des nombres entre 5 et 100 avec un pas de 5

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
        return Integer.toString(modulo.get(rand.nextInt(modulo.size()))); // renvoie des nombres entre 2 et 100 avec un pas de 2

    }

    public String minusXmodulo10(){

        return Integer.toString((-((int)(Math.random()*10))*10 -10));// nombre entre 10 et 100
    }

    /*public String 5X100(){
        return Integer.toString(((int)(Math.random()*10))*10 +15);
    }*/





}
