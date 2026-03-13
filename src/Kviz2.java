import java.util.Arrays;
import java.util.Collections;

public class Kviz2 {

    //\\// NALOGE ZA 1 TOČKO \\//\\

    // Naloga 1 - Vsota stevk

    static int vsotaStevk(String str){
        int vsota = 0;
        for (int i = 0; i<str.length(); i++){
            char znak = str.charAt(i);
            if(Character.isDigit(znak)){
                vsota += Integer.parseInt(String.valueOf(znak));
            }
        }
        return vsota;
    }

    // Naloga 2 - Preveri Rep

    static boolean preveriRep(String a, String b){
        String[] besede = a.split(" ");


        String rep;
        if (a.length() > b.length()){
            rep = a.substring(a.length() - b.length(), a.length()).toLowerCase();
            if (b.toLowerCase().equals(rep)){
                return true;
            }

        }
        else {
            rep = b.substring(b.length() - a.length(), b.length()).toLowerCase();
            if (a.toLowerCase().equals(rep)){
                return true;
            }
        }
        return false;
    }

    // Naloga 3 - Range

    public static int[] range(int a, int b, int c) {
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();

        for (int i = a; i < b; i += c) {
            list.add(i);
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    // Naloga 4 - Rotiraj

    static void rotiraj(int[] tabela, int k){
        int[] rotirano = new int[tabela.length]; // placeholder tabela

        for ( int i = 0; i<tabela.length; i++){
            rotirano[i] = (tabela[(i + k) % tabela.length]);

        }

        for (int i = 0; i < tabela.length; i++){
            tabela[i] = rotirano[i];
        }
    }

    // Naloga 5 - Duplikati

    public static int[] duplikati(int[] tabela){
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();

        for (int i = 0; i<tabela.length; i++){
            if (!(list.contains(tabela[i]))){
                list.add(tabela[i]);
            }
        }

        int[] rezultat = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            rezultat[i] = list.get(i);
        }

        return rezultat;

    }


    // Naloga 8 - vsota skupnih Cifer

    static int vsotaSkupnihCifer(int a, int b){
        java.util.ArrayList<Integer> usedDigits = new java.util.ArrayList<>();


        int vsota = 0;
        for (int i = 0; i<Integer.toString(a).length(); i++){

            String digit = String.valueOf(Integer.toString(a).charAt(i));
            if (Integer.toString(b).contains(digit) && !usedDigits.contains(Integer.parseInt(digit))){
                vsota += Integer.parseInt(digit);
                usedDigits.add(Integer.parseInt(digit));

            }
        }

        return vsota;

    }

    // Naloga 16 - Kodiraj/Dekodiraj

    static String kodiraj(String  vhod, int odmik){
        String kodiran_niz = "";
        for (int i = 0; i<vhod.length(); i++){
            kodiran_niz += (char) (odmik + ((int) vhod.charAt(i)));
        }
        System.out.println(kodiran_niz);
        return kodiran_niz;
    }

    static String dekodiraj(String  vhod, int odmik){
        String dekodiran_niz = "";
        for (int i = 0; i<vhod.length(); i++){
            dekodiran_niz += (char) (((int) vhod.charAt(i)) - odmik);
        }
        System.out.println(dekodiran_niz);
        return dekodiran_niz;
    }

    // Naloga 17 - Nicle spredaj

    static int[] nicleSpredaj(int[] tabela){

        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        int[] rezultat = new int[tabela.length];

        // 1. vse ničle postavimo spredaj

        for (int i = 0; i<tabela.length; i++){
            if (tabela[i] == 0) list.add(tabela[i]);
        }

        // 2. ostale vrednosti v seznam

        for (int i = 0; i<tabela.length; i++){
            if (tabela[i] != 0) list.add(tabela[i]);
        }

        // 3. Prekopiraj elemente iz ArrayList v int[]
        for (int i = 0; i<list.size(); i++){
            rezultat[i] = list.get(i);
        }

        return rezultat;
    }




    //\\// NALOGE ZA 2 TOČKI \\//\\

    // Naloga 12 - Fibonacijeva tabela

    static int fibo(int n){
        int[][] tabela = new int[n][n];

        // začetna člena zaporedja

        int a1 = 1;
        int a2 = 1;

        // polnjenje tabele
        for (int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if (i == 0 && j<=1){
                    tabela[i][j] = 1;
                } else {
                    int naslednji = a1 + a2;
                    tabela[i][j] = naslednji;
                    a1 = a2;
                    a2 = naslednji;
                }
            }
        }

        // izračun vsote diagonal
        int vsota = 0;
        for (int i = 0; i < n; i++){
            vsota += tabela[i][i]  + tabela[i][tabela[i].length - 1 - i];
        }

        return vsota;

    }


    static int prevoriBinarno(String binary){
        int decimal = 0;
        int length = binary.length();

        for (int i = 0; i < length; i++){
            decimal += Integer.parseInt(String.valueOf(binary.charAt(i))) * Math.pow(2, length - 1 - i);
        }

        return decimal;
    }

    static String binarnoSestej(String s, String b){

        java.util.ArrayList<Integer> binary_digits = new java.util.ArrayList<>();

        int dec1 = prevoriBinarno(s);
        int dec2 = prevoriBinarno(b);

        int vsota_dec = dec1 + dec2; // sestevanje decimalnih vrednosti


        // pretvarjanje vsote nazaj v binarno
        while(vsota_dec > 0){
            int ostanek = vsota_dec % 2;
            binary_digits.add(ostanek);
            vsota_dec /= 2;


        }


        // obrne seznam da dobimo pravilno zaporedje binarnih števk
        java.util.Collections.reverse(binary_digits);

        String final_binary = "";

        for (int i = 0; i<binary_digits.size(); i++){
            final_binary += Integer.toString(binary_digits.get(i));
        }



        return final_binary;
    }


    static double koren(int x, int d) {
        double koren = 0.0;

        // Integer part
        for (int c = 1; c * c <= x; c++) {
            koren = c;
        }

        // Decimal part
        for (int i = 1; i <= d; i++) {
            double korak = Math.pow(10, -i);
            double osnova = koren;
            
            for (int j = 1; j <= 9; j++) {
                double kandidat = osnova + j * korak;
                if (kandidat * kandidat <= x) {
                    koren = kandidat;
                } else {
                    break;
                }
            }
        }

        return koren;
    }

    //\\// NALOGE ZA 3 TOČKE \\//\\

    static int[] pascal(int n){
        int[] trenutna = new int[]{1};

        int[] nova = new int[2];

        for (int i = 2; i<=n; i++){
            nova = new int[i];

            // dodajanje enice na zacetek ter konec

            nova[0] = 1; nova[nova.length - 1] = 1;

            // racunanje vmesnih

            for (int j = 1; j<=nova.length-2; j++){
                nova[j] = trenutna[j-1] + trenutna[j];
            }

            trenutna = nova;
        }

        return trenutna;

    }





    public static void main(String[] args){

//        System.out.println(vsotaStevk("1a2c"));
//
//        System.out.println(Arrays.toString(duplikati(new int[]{1, 2, 3, 8, 9, 10, 11, 4, 5, 6, 4, 2, 2, 3, 6,})));
//
//        rotiraj(new int[]{1, 2, 3, 4, 5}, 2);
//
//        fibo(1);
//
//        System.out.println(binarnoSestej("10011010010", "1000011100001"));
        System.out.println(koren(15, 3));
    }
}
