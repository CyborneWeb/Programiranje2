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
            if (dodajPloscico()) dodanih++;

        }
        return;


    }
    private static boolean dodajPloscico(){
        int velikost = polja[0].length;
        int i = rnd.nextInt(velikost);
        int j = rnd.nextInt(velikost);

        if (polja[i][j] == 0){
            polja[i][j] = (rnd.nextInt(10) < 9 ? 2 : 4);
            return true;
        } return false;
    }
    public static void koncajIgro(){
        if (konec) System.exit(0);
        else konec = true;
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
                if (polje == 2048) {
                    konec = true;
                    return true;
                };
            }
        }
        return false;
    }
    public static boolean jeKonec(){
        return konec;
    }
    public static void naslednjaPoteza(int smer){
        boolean sprememba = false;
        switch (smer){
            case 0:
                sprememba = zdruziLevo();
                break;
            case 1: // DESNO
                rotiraj();
                rotiraj();
                sprememba = zdruziLevo();
                rotiraj();
                rotiraj();
                break;

            case 2: // GOR
                rotiraj();
                rotiraj();
                rotiraj();
                sprememba = zdruziLevo();
                rotiraj();
                break;

            case 3: // DOL
                rotiraj();
                sprememba = zdruziLevo();
                rotiraj();
                rotiraj();
                rotiraj();
                break;

        }

        if (sprememba){ // če se je zgodila sprememba polj
            boolean dodana = false;
            while (!dodana){
               dodana = dodajPloscico();
            }
        }

    }

    private static boolean zdruziLevo(){
        boolean zgodila_sprememba = false;

        for (int[] vrstica : polja){
            // korak 1: premakni vse ne-nične vrednosti v levo
            int[] nova = new int[vrstica.length];
            int idx = 0;
            for (int val : vrstica){
                if (val != 0){
                    nova[idx++] = val;
                }
            }

            // korak 2: združi enake sosednje
            for (int i = 0; i < nova.length - 1; i++){
                if (nova[i] != 0 && nova[i] == nova[i+1]){
                    nova[i] *= 2;
                    tocke += nova[i];
                    nova[i+1] = 0;
                }
            }

            // korak 3: spet premakni v levo po zdruzevanju
            int[] koncna = new int[vrstica.length];
            idx = 0;
            for (int val : nova){
                if (val != 0){
                    koncna[idx++] = val;
                }
            }

            // korak 4: preveri, ali se je kaj spremenilo
            for (int i = 0; i < vrstica.length; i++){
                if (vrstica[i] != koncna[i]){
                    zgodila_sprememba = true;
                }
            }

            // korak 5: prepiši izvorno vrstico
            System.arraycopy(koncna, 0, vrstica, 0, vrstica.length);
        }

        return zgodila_sprememba;
    }




    private static void rotiraj() {
        int VELIKOST = polja.length;
        // najprej transponiramo tabelo - zamenjamo stolpce in vrstice
        for (int i = 0; i < VELIKOST; i++) {
            for (int j = i + 1; j < VELIKOST; j++) {
                int tmp = polja[i][j];
                polja[i][j] = polja[j][i];
                polja[j][i] = tmp;
            }
        }
        // če rotiramo v desno (v smeri urinega kazalca), obrnemo še vrstni red stolpcev
        for (int i = 0; i < VELIKOST; i++) {
            for (int j = 0; j < VELIKOST / 2; j++) {
                int tmp = polja[i][j];
                polja[i][j] = polja[i][VELIKOST - 1 - j];
                polja[i][VELIKOST - 1 - j] = tmp;
            }
        }
    }

}
