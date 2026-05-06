package vaje8.aplikacija;

import vaje8.vinjete.SeznamVinjet;

public class Kontrola {
    public static void main(String[] args){
        SeznamVinjet seznam = new SeznamVinjet();

        seznam.preberiPodatke("vinjete.txt");

        seznam.izpisiVinjete();

        seznam.izpisiVinjete("tedenska");

        seznam.izpisiVeljavneLetne("15.04.2026");
    }
}
