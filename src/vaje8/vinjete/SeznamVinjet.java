package vaje8.vinjete;

import java.io.File;
import java.util.Scanner;



public class SeznamVinjet {
    Vinjeta[] prodaneVinjete;

    public boolean preberiPodatke(String vir) {
        try {
            Scanner sc = new Scanner(new File(vir));
            int st_vinjet = sc.nextInt();

            prodaneVinjete = new Vinjeta[st_vinjet];

            for (int i = 0; i<st_vinjet; i++){
                String[] prebrano = sc.next().split(";");
                prodaneVinjete[i] = new Vinjeta(prebrano[0], prebrano[1], prebrano[2], prebrano[3]);
            }
            sc.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void izpisiVinjete(){

        if (prodaneVinjete == null || prodaneVinjete.length == 0){
            System.out.println("V sistemu ni vinjet");
            return;
        }
        int st_vinjet = prodaneVinjete.length;
        System.out.printf("V sistemu so zabeležene prodane vinjete (%s):", st_vinjet);
        for (Vinjeta v : prodaneVinjete){
            System.out.println(v.toString());
        }

    }


}
