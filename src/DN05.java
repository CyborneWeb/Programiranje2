import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class DN05 {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Napaka: neustrezni argumenti.");
            return;
        }

        String operacija = args[0];

        if (!operacija.equals("branje") && !operacija.equals("poravnaj")) {
            System.out.println("Napaka: neustrezni argumenti.");
            return;
        }

        if (operacija.equals("branje") && args.length != 2) {
            System.out.println("Napaka: neustrezni argumenti.");
            return;
        }

        if (operacija.equals("poravnaj") && args.length != 3) {
            System.out.println("Napaka: neustrezni argumenti.");
            return;
        }

        String imeDatoteke = args[1];

        // MAIN opravi vse začetne preveritve (obstoj datoteke in prve 2 vrstici)
        try {
            Scanner sc = new Scanner(new File(imeDatoteke));

            if (!sc.hasNextLine()) {
                System.out.println("Napaka: nepravilen format datoteke.");
                sc.close();
                return;
            }
            String tip = sc.nextLine().trim();

            if (!sc.hasNextLine()) {
                System.out.println("Napaka: nepravilen format datoteke.");
                sc.close();
                return;
            }
            int[] dim = preberiDimenzije(sc.nextLine().trim());
            if (dim == null) {
                System.out.println("Napaka: nepravilen format datoteke.");
                sc.close();
                return;
            }
            sc.close(); // Zapremo, ker bodo datoteko dejansko obdelovale posamezne metode

            char[][] tabela;
            if (tip.equals("UREJENO")) {
                tabela = preberiUrejenoDatoteko(imeDatoteke);
            } else if (tip.equals("NEUREJENO")) {
                tabela = preberiNeurejenoDatoteko(imeDatoteke);
            } else {
                System.out.println("Napaka: nepravilen format datoteke.");
                return;
            }

            // Če je bila napaka v vsebini, so metode vrnile null in samo prekinemo
            if (tabela == null) {
                return;
            }

            // Če gre za prvo nalogo samo izpišemo, če za drugo razširimo pot
            if (operacija.equals("poravnaj")) {
                String nacin = args[2];
                if (!nacin.equals("levo") && !nacin.equals("desno") && !nacin.equals("sredina") && !nacin.equals("obojestransko")) {
                    System.out.println("Napaka: neustrezni argumenti.");
                    return;
                }
                char[][] poravnana = poravnajVrstice(tabela, nacin);
                izpisi(poravnana);
            } else {
                izpisi(tabela);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Napaka: datoteka ne obstaja.");
        } catch (Exception e) {
            // Za ostale nepredvidene sistemske izjeme
        }
    }

    public static char[][] poravnajVrstice(char[][] tabela, String nacin) {
        if (tabela == null) return null;
        int visina = tabela.length;
        int sirina = tabela[0].length;
        char[][] novaTabela = new char[visina][sirina];

        for (int r = 0; r < visina; r++) {
            char[] stara = tabela[r];
            char[] nova = novaTabela[r];

            // Izključno z indeksi poberemo dolžine in vsebino besed kot narekuje navodilo (prepoved metod nad String razredom)
            int maxBesed = sirina / 2 + 1; // v najslabšem primeru pri enem črki na besedo in '_' prekopu
            char[][] besede = new char[maxBesed][sirina];
            int[] dolzine = new int[maxBesed];
            int stBesed = 0;
            int stZnakov = 0;

            int i = 0;
            while (i < sirina) {
                // Preskoči zaporedne podčrtaje
                while (i < sirina && stara[i] == '_') {
                    i++;
                }
                if (i < sirina) {
                    // Prepišemo besedo na ustrezen indeks
                    int len = 0;
                    while (i < sirina && stara[i] != '_') {
                        besede[stBesed][len] = stara[i];
                        len++;
                        i++;
                        stZnakov++;
                    }
                    dolzine[stBesed] = len;
                    stBesed++;
                }
            }

            // Osnovno polnenje nove vrstice samo s presledki / '_'
            for (int k = 0; k < sirina; k++) {
                nova[k] = '_';
            }

            if (stBesed == 0) {
                // Vrstica samo s praznim prekopom (ne povzroči napake, pustimo same '_')
                continue;
            }

            if (nacin.equals("levo")) {
                int pos = 0;
                for (int w = 0; w < stBesed; w++) {
                    for (int k = 0; k < dolzine[w]; k++) {
                        nova[pos++] = besede[w][k];
                    }
                    if (w < stBesed - 1) {
                        nova[pos++] = '_';
                    }
                }
            } else if (nacin.equals("desno")) {
                // Skupno znakov vključno z enojnim '_' med besedami
                int totalUsed = stZnakov + stBesed - 1;
                int pos = sirina - totalUsed;
                for (int w = 0; w < stBesed; w++) {
                    for (int k = 0; k < dolzine[w]; k++) {
                        nova[pos++] = besede[w][k];
                    }
                    if (w < stBesed - 1) {
                        nova[pos++] = '_';
                    }
                }
            } else if (nacin.equals("sredina")) {
                int totalUsed = stZnakov + stBesed - 1;
                int freeSpaces = sirina - totalUsed;
                // Integer roundana dol. 5/2 = 2 (torej manj na levi za liho število, kot pravi navodilo)
                int leftSpaces = freeSpaces / 2;
                int pos = leftSpaces;
                for (int w = 0; w < stBesed; w++) {
                    for (int k = 0; k < dolzine[w]; k++) {
                        nova[pos++] = besede[w][k];
                    }
                    if (w < stBesed - 1) {
                        nova[pos++] = '_';
                    }
                }
            } else if (nacin.equals("obojestransko")) {
                if (stBesed == 1) {
                    // Ko imaš v vrstici smo 1 besedo ob rob samo nalimaš in dopolniš (pada nazaj na levi primer)
                    int pos = 0;
                    for (int k = 0; k < dolzine[0]; k++) {
                        nova[pos++] = besede[0][k];
                    }
                } else {
                    int gaps = stBesed - 1;
                    int distSpaces = sirina - stZnakov;
                    int baseSpace = distSpaces / gaps;
                    int extraSpace = distSpaces % gaps;

                    int pos = 0;
                    for (int w = 0; w < stBesed; w++) {
                        for (int k = 0; k < dolzine[w]; k++) {
                            nova[pos++] = besede[w][k];
                        }
                        if (w < stBesed - 1) {
                            int actualSpaces = baseSpace + (w < extraSpace ? 1 : 0);
                            for (int s = 0; s < actualSpaces; s++) {
                                nova[pos++] = '_';
                            }
                        }
                    }
                }
            }
        }
        return novaTabela;
    }

    // DELOVNE METODE (samo String, brez try/catch in brez preverjanja začetnega formata)

    public static char[][] preberiUrejenoDatoteko(String imeDatoteke) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(imeDatoteke))) {
            sc.nextLine(); // preskočimo prvo vrstico (preveril jo je main)

            int[] dim = preberiDimenzije(sc.nextLine().trim());
            int sirina = dim[0];
            int visina = dim[1];

            char[][] tabela = new char[visina][sirina];

            for (int r = 0; r < visina; r++) {
                if (!sc.hasNextLine()) {
                    System.out.println("Napaka: nepravilne dimenzije strani.");
                    return null;
                }
                String vrstica = sc.nextLine();
                if (vrstica.length() != sirina) {
                    System.out.println("Napaka: nepravilne dimenzije strani.");
                    return null;
                }
                for (int c = 0; c < sirina; c++) {
                    tabela[r][c] = vrstica.charAt(c);
                }
            }

            if (sc.hasNextLine()) {
                System.out.println("Napaka: nepravilne dimenzije strani.");
                return null;
            }

            return tabela;
        }
    }

    public static char[][] preberiNeurejenoDatoteko(String imeDatoteke) throws FileNotFoundException {
        int sirina;
        int visina;
        StringBuilder besedilo = new StringBuilder();

        try (Scanner sc = new Scanner(new File(imeDatoteke))) {
            sc.nextLine(); // preskočimo prvo vrstico (preveril jo je main)

            int[] dim = preberiDimenzije(sc.nextLine().trim());
            sirina = dim[0];
            visina = dim[1];

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (!line.isEmpty()) {
                    if (besedilo.length() > 0) {
                        besedilo.append(' ');
                    }
                    besedilo.append(line);
                }
            }
        }

        // Če besedila ni, vrnemo napako
        if (besedilo.length() == 0) {
            System.out.println("Napaka: nepravilen format datoteke.");
            return null;
        }

        char[][] tabela = new char[visina][sirina];
        for (int r = 0; r < visina; r++) {
            Arrays.fill(tabela[r], '_');
        }

        String[] besede = besedilo.toString().split("\\s+");
        int r = 0;
        int c = 0;

        for (String beseda : besede) {
            int len = beseda.length();

            if (len > sirina) {
                System.out.println("Napaka: premajhne dimenzije strani.");
                return null;
            }

            int potrebniZnaki = (c == 0) ? len : (1 + len);

            if (c + potrebniZnaki <= sirina) {
                if (c != 0) {
                    tabela[r][c] = '_';
                    c++;
                }
                for (int i = 0; i < len; i++) {
                    tabela[r][c + i] = beseda.charAt(i);
                }
                c += len;
            } else {
                r++;
                c = 0;
                if (r >= visina) {
                    System.out.println("Napaka: premajhne dimenzije strani.");
                    return null;
                }
                for (int i = 0; i < len; i++) {
                    tabela[r][c + i] = beseda.charAt(i);
                }
                c += len;
            }
        }

        return tabela;
    }

    private static int[] preberiDimenzije(String dimenzije) {
        String[] deli = dimenzije.split("x");
        if (deli.length != 2) {
            return null;
        }
        try {
            int sirina = Integer.parseInt(deli[0]);
            int visina = Integer.parseInt(deli[1]);
            if (sirina <= 0 || visina <= 0) {
                return null;
            }
            return new int[]{sirina, visina};
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static void izpisi(char[][] tabela) {
        if (tabela == null) {
            return;
        }
        for (char[] vrstica : tabela) {
            System.out.println(new String(vrstica));
        }
    }
}
