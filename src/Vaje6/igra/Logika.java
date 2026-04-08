package Vaje6.igra;

import java.util.Random;

public class Logika {
    // definiranje spremenljivk potrebnih za delovanje igre
    static int[][] polja;
    static int tocke;
    private static boolean konec;
    // Objekt Random
    static Random rnd = new Random();

    // metode za delovanje igre
    public static void zacniNovoIgro(int velikost){
        polja = new int[velikost][velikost];
        tocke = 0;
        konec = false;
        int dodanih = 0;
        while (dodanih < 2){

            int i = rnd.nextInt(velikost);
            int j = rnd.nextInt(velikost);
            if (polja[i][j] == 0){
                polja[i][j] = (rnd.nextInt(10) < 9 ? 2 : 4);
                dodanih++;
            }

        }
        return;


    }
    public static void koncajIgro(){
        konec = true;
    }
    public static int vrniPloscico(int i, int j){
        return polja[i][j];
    }
    public static int vrniTocke(){
        return tocke;
    }
    public static boolean jeZmagal(){
        for (int[] vrstica : polja){
            for (int polje : vrstica){
                if (polje == 2048) return true;
            }
        }
        return false;
    }
    public static boolean jeKonec(){
        return konec;
    }
    public static void naslednjaPoteza(int smer){
        return;
    }
}
