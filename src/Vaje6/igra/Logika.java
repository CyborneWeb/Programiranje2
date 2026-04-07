package Vaje6.igra;

public class Logika {
    // definiranje spremenljivk potrebnih za delovanje igre
    static int[][] polja;
    static int tocke;

    private static boolean konec;
    // metode za delovanje igre
    public static void zacniNovoIgro(int velikost){
        polja = new int[velikost][velikost];
        tocke = 0;
        konec = false;
        return;
    }
    public static void koncajIgro(){
        return;
    }
    public static int vrniPloscico(int i, int j){
        return 0;
    }
    public static int vrniTocke(){
        return 0;
    }
    public static boolean jeZmagal(){
        return false;
    }
    public static boolean jeKonec(){
        return false;
    }
    public static void naslednjaPoteza(int smer){
        return;
    }
}
