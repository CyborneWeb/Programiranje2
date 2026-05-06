package vaje9.aplikacija;

import vaje9.banka.Banka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Upravljanje {

    // Iz podane datoteke prebere podatke o računih in ustvari račune v podani banki.
    private static void dodajRacune(String vir, Banka banka) throws FileNotFoundException {
        Scanner vhod = new Scanner(new File(vir));
        while (vhod.hasNextLine()) {
            String[] podatki = vhod.nextLine().split(";");
            if (podatki[0].equalsIgnoreCase("tekoci")) { // ustvari tekoči račun
                banka.dodajTekociRacun(podatki[1], Double.parseDouble(podatki[2]));
            } else { // ustvari varčevalni račun
                banka.dodajVarcevalniRacun(podatki[1], Double.parseDouble(podatki[2]));
            }
            banka.polog(podatki[1], Double.parseDouble(podatki[3]));
        }
        vhod.close();
    }

    public static void main(String[] args) throws FileNotFoundException {

        // ustvarimo novo banko
        Banka bankaFRI = new Banka();

        // v banki naredimo račune z določenimi stanji, vse podatke preberemo iz datoteke
        dodajRacune("racuni.txt", bankaFRI);

        // izpiši vse račune
        System.out.println("------ VSI RAČUNI ------");
        bankaFRI.izpisiRacune();

        // izpiši vse tekoče račune
        System.out.println("\n------ SAMO TEKOČI RAČUNI ------");
        bankaFRI.izpisiRacune(false);

        String stevilkaRacuna = "SI56 1234 4321 1234 126"; // tekoči z limitom 100 €
        // String stevilkaRacuna = "SI56 7823 4563 8346 123"; // varčevalni z 0,1% obrestmi

        // položi znesek na račun in preveri novo stanje z izpisom računov
        System.out.println("\n------ POLOG 50 EUR ------");
        bankaFRI.polog(stevilkaRacuna, 50.0);
        bankaFRI.izpisiRacune();

        // dvigni znesek z računa in preveri novo stanje z izpisom računov
        System.out.println("\n------ DVIG 20 EUR ------");
        bankaFRI.dvig(stevilkaRacuna, 20.0);
        bankaFRI.izpisiRacune();

        // preveri še ostale metode banke
        System.out.println("\n------ DODAJ OBRESTI ------");
        bankaFRI.dodajObresti();

        System.out.println("\n------ IZPIS VARČEVALNIH RAČUNOV ------");
        bankaFRI.izpisiRacune(true);
    }
}
