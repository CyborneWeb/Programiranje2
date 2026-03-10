import java.util.Arrays;
import java.lang.Math;

public class Kviz1 {

    public static void krog(double r, int d){
        if(r < 0){
            System.out.printf("Napaka: negativen polmer");
            return;
        }
        if(d < 0){
            System.out.printf("Napaka: negativen d");
            return;
        }

        double obseg = 2 * Math.PI * r;
        double ploscina = Math.PI * Math.pow(r, 2);
        System.out.printf("Obseg kroga s polmerom r=%.2f je %." + d + "f \n", r, obseg);
        System.out.printf("Ploscina kroga s polmerom r=%.2f je %." + d + "f \n", r, ploscina);

    }
    static void java() {
        System.out.println(
                "   J    a   v     v  a                                                 \n" +
                "   J   a a   v   v  a a                                                \n" +
                "J  J  aaaaa   V V  aaaaa                                               \n" +
                " JJ  a     a   V  a     a"

        );
    }

    static void kalkulator(int a, int b){
        if (b == 0){
            System.out.println("Napaka: deljenje z 0");
            return;
        }

        System.out.printf("%d + %d = %d\n", a, b, a+b);
        System.out.printf("%d - %d = %d\n", a, b, a-b);
        System.out.printf("%d x %d = %d\n", a, b, a*b);
        System.out.printf("%d / %d = %d\n", a, b, a/b);
        System.out.printf("%d %% %d = %d\n", a, b, a%b);

    }
    static void nicli(int a, int b, int c) {
        double d = Math.pow(b, 2) - (4 * a * c);

        if (d < 0){
            System.out.println("Napaka: nicli enacbe ne obstajata");
            return;
        }

        double nicla1 = (-b + Math.sqrt(d)) / (2 * a);
        double nicla2 = (-b - Math.sqrt(d)) / (2 * a);

        System.out.printf("x1=%.2f, x2=%.2f", nicla1, nicla2);
    }
    static String pretvoriSekunde(int sekunde) {
        if (sekunde < 0) {
            return "Število sekund ne more biti negativno";
        }
        int ure = sekunde / 3600;

        int minute = (sekunde - ure * 3600) / 60;

        int ostale = (sekunde - ure * 3600 - minute * 60);

        return String.format("%02d:%02d:%02d", ure, minute, ostale);

    }

    static void javaJavaJava(int n){
        if (n<0){
            System.out.println("Napaka: negativen n");
            return;
        }
        System.out.print(
                ("     J    a   v     v  a   " ).repeat(n)  +  "\n" +
                ("     J   a a   v   v  a a  " ).repeat(n)  +  "\n" +
                ("  J  J  aaaaa   V V  aaaaa " ).repeat(n) +   "\n" +
                ("   JJ  a     a   V  a     a" ).repeat(n)
        );

        }

    static boolean jeFibonaccijevo(int n){
        if (n <=0) return false;
        if (n ==1) return true;

        long a =1;
        long b =1;

        while (b < n) {
            long next = a + b;
            a = b;
            b = next;
        }

        return b == n;
    }

    static boolean jePrastevilo(int n){

        if (n < 0) return false;
        for (int i = 2; i<n; i++){
            if (n%i == 0) return false;
        } return true;
    }

    static int vsotaPrvih(int n){
        int st_prastevil = 0;
        int vsota = 0;
        int stevilo = 2;


        while (st_prastevil < n) {

            if (jePrastevilo(stevilo)){
                vsota += stevilo;
                st_prastevil++;
            }
            stevilo++;


        }
        return vsota;

    }
    static int[] stik(int[] tabela1, int[] tabela2){
        int d1 = tabela1.length; // dolzina 1. tabele
        int d2 = tabela2.length; // dolzina 2. tabele
        int skupno = d1 + d2; // skupna dolzina tabel (za iniciliziranje združene tabele)
        int[] skupaj = new int[skupno];
        for(int i = 0; i<d1; i++){
            skupaj[i] = tabela1[i]; // zanka gre skozi vsak indeks v tabeli1 ter element na indeksu doda na mesto na indeksu v skupni tabeli
        }

        for(int i=0; i<d2; i++){
            skupaj[i+d1] = tabela2[i]; // podobno kot pri prvi zanki, vendar da elemente zanka dodaja v skupno tabelo z odmikom, ki je enak dolžini tabele1
        }

        return skupaj;
    }

    static int[] presek(int[] tabela1, int[] tabela2) {
        int[] temp = new int[tabela1.length + tabela2.length];
        int indeks = 0;
        for (int i = 0; i < tabela1.length; i++) {
            boolean zeDodan = false;
            for (int k = 0; k < indeks; k++) {
                if (temp[k] == tabela1[i]) {
                    zeDodan = true;
                    break;
                }
            }
            if (!zeDodan) {
                for (int j = 0; j < tabela2.length; j++) {
                    if (tabela1[i] == tabela2[j]) {
                        temp[indeks] = tabela1[i];
                        indeks++;
                        break;
                    }
                }
            }
        }
        int[] preseki = new int[indeks];
        for (int i = 0; i < indeks; i++) {
            preseki[i] = temp[i];
        }
        return preseki;
    }
    static void praDvojcek(int n){
        int naj_pra = 2;
        for (int i=2; i<=n; i++){
            if (jePrastevilo(i)){

                if((i - naj_pra)==2){
                    System.out.printf("(%d, %d)\n", naj_pra, i);
                }
                naj_pra = i;

            }
        }
    }

    static void veckratnikDeljitelj(int a, int b){
        if (Math.min(a, b) == 0){
            System.out.println("Napaka: obe števili morata biti različni od nič.");
            return;
        }

        // največji skupni deljitelj
        boolean najden_deljitelj = false;
        for (int i = Math.max(a,b); i>0; i--){
            if (a%i == 0 && b%i == 0 && !najden_deljitelj){

                System.out.printf("Največji skupni delitelj je %d.\n", i);
                najden_deljitelj = true;

            }

        }

        // najmanjši skupni večkratnik
        boolean najden_veckratnik = false;
        int st=1;
        while(!najden_veckratnik){
            if (st%a == 0 && st%b == 0){
                System.out.printf("Najmanjši skupni večkratnik je %d.\n", st);
                najden_veckratnik = true;
                return;
            }
            st++;
        }
    }

    static void vDesetisko(int n){
        // osnovni checki

        if (Integer.toString(n).contains("9")){
            System.out.printf("Število %d ni število v osmiškem sistemu (števka 9)", n);
            return;
        }

        if (Integer.toString(n).contains("8")){
            System.out.printf("Število %d ni število v osmiškem sistemu (števka 8)", n);
            return;
        }
        // pretvorba

        int rezultat = 0;
        int indeks = 0;
        for (int i = Integer.toString(n).length() - 1; i>=0; i--){
            rezultat += Integer.parseInt(String.valueOf(Integer.toString(n).charAt(indeks))) * Math.pow(8, i); // hope this works lmao, Edit: it works :)
            indeks++;

        }
        System.out.printf("%d(8) = %d(10)", n, rezultat);
    }

    static String izracunajRazliko(String prviCas, String drugiCas){

    }

    public static void main(String[] args){
//        kalkulator(42, 13);
//        nicli(1, 2, 2);
//        System.out.println(pretvoriSekunde(65));
//        javaJavaJava(3);

//        System.out.println(vsotaPrvih(10));
//        praDvojcek(20);
//        veckratnikDeljitelj(6, 14);
//        vDesetisko(127);






    }
}
