import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Vaje4 {

    // Konstante barv
    static final int BELA = 0;
    static final int CRNA = 1;
    static final int RUMENA = 2;
    static final int ZELENA = 3;

    // ANSI ukazi (za barvni izpis)
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_GREEN_BG = "\u001b[42m";
    static final String ANSI_YELLOW_BG = "\u001b[43m";
    static final String ANSI_WHITE_BG = "\u001b[47;1m";
    static final String ANSI_BLACK_BG = "\u001b[40m";
    static final String ANSI_WHITE = "\u001b[37m";
    static final String ANSI_BLACK = "\u001b[30m";

    static final String abeceda = "ABCČDEFGHIJKLMNOPRSŠTUVZŽ"; // Veljavne črke
    static final int MAX_POSKUSOV = 6; // Število poskusov

    static String[] seznamBesed; // Seznam vseh možnih besed
    static ArrayList<String> slovarBesed = new ArrayList<>();
    static String iskanaBeseda; // Iskana beseda trenutne igre
    static int[] barveAbecede; // Barve črk pri izpisu abecede

    static Scanner sc = new Scanner(System.in);

    // Izpiše znak v izbrani barvi
    static void izpisiZBarvo(char znak, int barva) {
        String slog;
        if (barva == ZELENA) {
            slog = ANSI_BLACK + ANSI_GREEN_BG;
        } else if (barva == RUMENA) {
            slog = ANSI_BLACK + ANSI_YELLOW_BG;
        } else if (barva == BELA) {
            slog = ANSI_BLACK + ANSI_WHITE_BG;
        } else {
            slog = ANSI_WHITE + ANSI_BLACK_BG;
        }
        System.out.print(slog + " " + znak + " " + ANSI_RESET);
    }

    // Prebere seznam besed iz datoteke
    static void preberiBesede(String datoteka) throws FileNotFoundException {
        // TODO: implementiraj
        Scanner sc = new Scanner(new File("besede.txt"));
        int st_besed = sc.nextInt();
        seznamBesed = new String[st_besed];

        for (int i = 0; i<st_besed; i++){
            seznamBesed[i]  = (sc.next()).toUpperCase();
        }
        sc.close();
    }

    static void preberiSlovar(String datoteka) throws FileNotFoundException {
        Scanner slovarScanner = new Scanner(new File("slovar.txt"));
        int st_besed = slovarScanner.nextInt();

        for (int i = 0; i<st_besed; i++){
            slovarBesed.add(slovarScanner.next().toUpperCase());
        }
        slovarScanner.close();
    }

    // Pripravi vse za novo igro
    static void novaIgra() {

        // pripravi Random
        Random rnd = new Random();
        iskanaBeseda = seznamBesed[rnd.nextInt(seznamBesed.length)];

        barveAbecede = new int[abeceda.length()];
        for ( int i = 0; i<abeceda.length(); i++){
            barveAbecede[i] = 0;
        }



        // The Debugging corner :)
        //System.out.println(Arrays.toString(seznamBesed));
        //System.out.println(iskanaBeseda);
        //System.out.println(Arrays.toString(barveAbecede));

    }

    // Izpiše abecedo
    static void izpisiAbecedo() {
        // TODO: implementiraj

        // Overwriting barve abecede
        //barveAbecede = new int[]{0,0,0,4,3,2,1,3,4,0,0,2,1,3,0,0,0,2,1,3,4,0,1};
        System.out.print("Preostale črke: ");
        for (int i = 0; i<barveAbecede.length; i++){
            izpisiZBarvo(abeceda.charAt(i), barveAbecede[i]);
        }
        System.out.println();
    }

    // Ali je beseda veljavna?
    static boolean veljavnaBeseda(String beseda) {
        if (beseda.length() != 5){
            System.out.println("Neveljavna dolžina besede!");
            return false;
        }
        for (int i = 0; i < beseda.length(); i++){
            if (!(abeceda.contains("" + beseda.charAt(i)))){
                System.out.println("V besedi so neveljavni znaki!");
                return false;
            }
        }
        if (!slovarBesed.contains(beseda)){
            System.out.println("Beseda ni v slovarju");
            return false;
        }

        return true;
    }

    // Določi barve črk v ugibani besedi
    static int[] pobarvajBesedo(String ugibanaBeseda) {

        int[] barveBesede = new int[5];

        for (int i = 0; i<5; i++){
            int index = abeceda.indexOf(ugibanaBeseda.charAt(i));
            if (ugibanaBeseda.charAt(i) == iskanaBeseda.charAt(i)){
                barveBesede[i] = 3;
                barveAbecede[index] = 3;
            }
            else if (iskanaBeseda.contains("" + ugibanaBeseda.charAt(i))){
                barveBesede[i] = 2;
                if (barveAbecede[index] != 3) {
                    barveAbecede[index] = 2;
                }
            }
            else{
                barveBesede[i] = 0;
                barveAbecede[index] = 1;
            }
        }
        return barveBesede;
    }

    // Izpiši besedo
    static void izpisiBesedo(String ugibanaBeseda, int[] barve) {
        int[] barve1 = pobarvajBesedo(ugibanaBeseda);
       for (int i = 0; i<ugibanaBeseda.length(); i++){
           izpisiZBarvo(ugibanaBeseda.charAt(i), barve1[i]);
       }
        System.out.println();
    }


    // Izvede eno igro
    static void igra() {
        novaIgra();
        //System.out.println(iskanaBeseda);

        int poskus = 1;
        boolean uganil = false;
        while (poskus <= MAX_POSKUSOV) {
            izpisiAbecedo();
            System.out.printf("Poskus %d/%d: ", poskus, MAX_POSKUSOV);
            String ugibanaBeseda = sc.nextLine().toUpperCase();

            // Preveri veljavnost
            if (!veljavnaBeseda(ugibanaBeseda))
                continue;

            // Pobarvaj crke v besedi (namigi)
            int[] barve = pobarvajBesedo(ugibanaBeseda);

            // Izpiši pobarvano besedo
            izpisiBesedo(ugibanaBeseda, barve);

            if (ugibanaBeseda.equals(iskanaBeseda)) {
                uganil = true;
                break;
            }
            poskus++;
        }

        if (uganil) {
            System.out.printf("Bravo! Besedo si uganil/a v %d poskusih.\n", poskus);
        } else {
            System.out.printf("Žal ti ni uspelo. Pravilna beseda: %s\n", iskanaBeseda);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        preberiBesede("besede.txt");
        preberiSlovar("slovar.txt");

        while (true) {
            igra();
            System.out.print("Nova igra? (d/n): ");
            String odg = sc.nextLine();
            if (odg.toLowerCase().charAt(0) != 'd') {
                break;
            }
        }
    }
}
