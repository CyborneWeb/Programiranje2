import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class DN03 {
    void main(String[] args) throws Exception {

        //args = new String[]{"gesla.txt", "10", "100"};

        // ustvarjanje objekta Scanner, ki bo bral iz datoteke z imenom, ki je podan v argumentih
        Scanner sc = new Scanner(new File(args[0]));
        // pridobitev števila besed iz prve vrstice v datoteki ter ustvarjanje tabele "besede"
        int n = sc.nextInt();
        sc.nextLine();
        String[] besede = new String[n];
        // polnenje tabele z besedami iz datoteke
        for (int i = 0; i < n; i++) {
            besede[i] = sc.nextLine();
        }
        // praznen "placeholder" string za geslo
        String geslo = "";
        Random rnd = new Random(Integer.parseInt(args[2])); // ustvarjanje objekta rnd, z uporabo semena iz argumentov

        for (int i = 0; i < Integer.parseInt(args[1]); i++) {
            String naklj_beseda = besede[rnd.nextInt(besede.length)]; // izbira nakljucne besede

            geslo += naklj_beseda.charAt(rnd.nextInt(naklj_beseda.length())); // izbira nakljucnega znaka iz izbrane besede ter dodajanje geslu
        }
        System.out.println(geslo);
    }
}