package vaje8.vinjete;

import java.io.File;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        System.out.printf("V sistemu so zabeležene prodane vinjete (%s):\n", st_vinjet);
        for (Vinjeta v : prodaneVinjete){
            System.out.println(v.toString());
        }

    }

    public boolean preveriVinjeto(String registrska){
        for (Vinjeta v : prodaneVinjete){
            if (v.oznaka.equals(registrska)) return true;

        } return false;
    }

    public void izpisiVinjete(String vrsta){
        int st_vinjet_vrste = 0;
        System.out.printf("V sistemu je %s vinjeta za naslednja vozila\n", vrsta);
        for (Vinjeta v : prodaneVinjete){
            if (v.vrsta.equals(vrsta)){
                System.out.println(v.toString());
                st_vinjet_vrste++;
            }
        }
        System.out.printf("Skupaj - %s vinjeta: %d\n", vrsta, st_vinjet_vrste);
    }



    public void izpisiVeljavneLetne(String datum) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dan = LocalDate.parse(datum, f);

        System.out.printf("Letne vinjete, veljavne dne %s (z datumi veljavnosti):%n", datum);

        if (prodaneVinjete == null) {
            return;
        }

        for (Vinjeta v : prodaneVinjete) {


            if (v.vrsta.equals("letna")) {

                LocalDate zacetek = LocalDate.parse(v.zacetek_veljavnosti, f);
                LocalDate konec = zacetek.plusYears(1);

                boolean veljavna =
                        (!dan.isBefore(zacetek)) && (!dan.isAfter(konec));

                if (veljavna) {
                    System.out.printf(" -- %s [%s] (veljavna od %s do %s)%n", v.oznaka, v.razred, zacetek.format(f), konec.format(f));
                }
            }
        }


    }
}
