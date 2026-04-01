package zbirke;

import java.util.ArrayList;
import java.util.Arrays;

public class Seznam {
    static int st_elementov;
    static String[] nakupi;

    /**
     * Metoda, ki ustvari seznam podane dolžine, če še ne obstaja
     * @param n - dolžina ustvarjenega seznama
     * @return true, če je bil seznam uspešno ustvarjen, false, če seznam že obstaja oz. ni bil uspešno ustvarjen
     */
    public static boolean narediSeznam(int n){
        if (nakupi == null){
            nakupi = new String[n];
            st_elementov = 0;
            return true;
        } return false;
    }

    /**
     * Metoda, ki doda podan element na konec nakupovalnega seznama
     * @param element niz, ki bo dodan na konec seznama
     * @return true če je bil element uspešno dodan, false če ne
     */

    public static boolean dodajNaKonecSeznama(String element){
        if (nakupi != null && st_elementov < nakupi.length){
            nakupi[st_elementov] = element;
            st_elementov++;
            return true;
        } return false;
    }

    /**
     * Metoda, ki z zanko izpiše vse trenutne elemente v seznamu
     */
    public static void izpisiSeznam(){
        if (nakupi != null && st_elementov != 0){
        for (int i = 1; i<=st_elementov; i++){
            System.out.printf("%d: %s\n", i, nakupi[i-1]);
        }
        } else {
            System.out.println("Seznam je prazen [nima elementov]");
        }
    }

    /**
     * Metoda odstrai element iz podanega mesta, ter vrne odstranjen element
     * @param mesto mesto na seznamu iz katerega bomo odstranili element, prvi element v seznamu je na mestu 1
     * @return null, če seznam ne obstaja ali argument mesto kaže na nepravilno mesto v seznamu oz. niz odstranjenega elementa, če je bil element uspešno odstranjen
     */
    public static String odstraniIzSeznama(int mesto) {

        if (nakupi == null || st_elementov == 0 || mesto < 1 || mesto > st_elementov) {
            return null;
        }

        int idx = mesto - 1;
        String odstranjen = nakupi[idx];

        // Shift elements left to fill the gap
        for (int i = idx; i < st_elementov - 1; i++) {
            nakupi[i] = nakupi[i + 1];
        }

        // Clear old last used slot
        nakupi[st_elementov - 1] = null;
        st_elementov--;

        return odstranjen;
    }

    /**
     *
     * @param element niz, ki bo vrinjen na podano mesto v seznamu
     * @param mesto mesto, na katerega bo vrinjen podan element, mesta se štejejo od 1 do dolžine seznama
     * @return false, če seznam ne obstaja, je poln, je argument mesto podan napačno (premajhna ali prevelika vrednost) ali true, če je bil element uspešno vstavljen
     */
    public static boolean dodajVSeznam(String element, int mesto) {
        // mesto is 1-based; valid insert positions: 1 .. st_elementov + 1
        if (nakupi == null || st_elementov == nakupi.length || mesto < 1 || mesto > st_elementov + 1) {
            return false;
        }

        int idx = mesto - 1; // convert to 0-based
        String[] temp = new String[nakupi.length];

        // copy before insertion point
        for (int i = 0; i < idx; i++) {
            temp[i] = nakupi[i];
        }

        // insert new element
        temp[idx] = element;

        for (int i = idx; i < st_elementov; i++) {
            temp[i + 1] = nakupi[i];
        }

        nakupi = temp;
        st_elementov++;
        return true;
    }
    /**
     * Metoda, ki vrne trenutno dolžino seznama (št. elementov)
     * @return -1, če seznam ne obstaja, oz. število elementvo če je seznam ustvarjen
     */
    public static int dolzinaSeznama(){
        if (nakupi == null) return -1;
        return st_elementov;
    }

    /**
     * Metoda, ki uniči trenutni seznam
     * @return false, če seznam ne obstaja, true če seznam obstaja in je bil uničen
     */

    public static boolean uniciSeznam(){
        if (nakupi == null) return false;
        nakupi = null;
        st_elementov = 0;
        return true;
    }

}
