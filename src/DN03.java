import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class DN03 {
    public static void main(String[] args) throws Exception{
        args = new String[]{"gesla.txt", "10", "100"};
        Scanner sc = new Scanner(new File(args[0]));


        // pridobitev števila besed iz prve vrstice v datoteki ter ustvarjanje tabele "besede"
        int n = sc.nextInt(); sc.nextLine();
        String[] besede = new String[n];


        
        for (int i = 0; i < n; i++) {
            besede[i] = sc.nextLine();
        }


        String geslo = "";
        Random rnd = new Random(Integer.parseInt(args[2]));
        for (int i = 0; i<Integer.parseInt(args[1]);i++){
            String naklj_beseda = besede[rnd.nextInt(besede.length)];
            char nakljucna_crka = naklj_beseda.charAt(rnd.nextInt(naklj_beseda.length()));
            geslo += nakljucna_crka;

        }
        System.out.println(geslo);


    }
}
