import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class DN05 {
    // 1. NALOGA - Preberi urejeno
    public static char[][] preberiUrejenoDatoteko(String imeDatoteke) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(imeDatoteke))) {
            sc.nextLine();

            String[] dim = sc.nextLine().split("x");
            int sirina = Integer.parseInt(dim[0]);
            int visina = Integer.parseInt(dim[1]);

            char[][] tabela = new char[visina][sirina];
            int r = 0;
            while (r < visina) {
                if (!sc.hasNextLine()) {
                    System.out.println("Napaka: nepravilne dimenzije strani.");
                    return null;
                }
                String vrstica = sc.nextLine();
                if (vrstica.length() != sirina) {
                    System.out.println("Napaka: nepravilne dimenzije strani.");
                    return null;
                }
                int c = 0;
                while (c < sirina) {
                    tabela[r][c] = vrstica.charAt(c);
                    c++;
                }
                r++;
            }

            if (sc.hasNextLine()) {
                System.out.println("Napaka: nepravilne dimenzije strani.");
                return null;
            }
            return tabela;
        }
    }
    // 1. NALOGA - Preberi Neurejeno
    public static char[][] preberiNeurejenoDatoteko(String imeDatoteke) throws FileNotFoundException {
        int sirina;
        int visina;
        StringBuilder besedilo = new StringBuilder();

        try (Scanner sc = new Scanner(new File(imeDatoteke))) {
            sc.nextLine();

            String[] dim = sc.nextLine().trim().split("x");
            sirina = Integer.parseInt(dim[0]);
            visina = Integer.parseInt(dim[1]);

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (!line.isEmpty()) {
                    if (!besedilo.isEmpty()) {
                        besedilo.append(' ');
                    }
                    besedilo.append(line);
                }
            }
        }

        if (besedilo.isEmpty()) {
            System.out.println("Napaka: nepravilen format datoteke.");
            return null;
        }

        char[][] tabela = new char[visina][sirina];
        int r = 0;
        while (r < visina) {
            Arrays.fill(tabela[r], '_');
            r++;
        }
        // nisem vedel če je split dovoljejn v prvi nalogi, saj je bila omejitev napisana šele pri navodilu druge, upam da ni narobe
        String[] besede = besedilo.toString().split("\\s+");
        r = 0;
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
                int i = 0;
                while (i<len) {
                    tabela[r][c + i] = beseda.charAt(i);
                    i++;
                }
            } else {
                r++;
                c = 0;
                if (r >= visina) {
                    System.out.println("Napaka: premajhne dimenzije strani.");
                    return null;
                }
                int i = 0;
                while (i < len) {
                    tabela[r][c + i] = beseda.charAt(i);
                    i++;
                }
            }
            c += len;
        }

        return tabela;
    }
    // Pomožna metoda za izpis tabel, ki jih dobim iz metod za branje in urejanje
    public static void izpisi(char[][] tabela) {
        if (tabela == null) {
            return;
        }
        for (char[] vrstica : tabela) {
            System.out.println(new String(vrstica));
        }
    }
    // 2. NALOGA - Poravnava vrstic na podan način
    public static char[][] poravnajVrstice(char[][] tabela, String nacin) {
        if (tabela == null) return null;

        int steviloVrstic = tabela.length;
        int steviloStolpcev = tabela[0].length;
        char[][] novaTabela = new char[steviloVrstic][steviloStolpcev];
        int vrstica = 0;
        while (vrstica < steviloVrstic) {
            char[] staraVrstica = tabela[vrstica];
            char[] novaVrstica = novaTabela[vrstica];

            int najvecBesed = steviloStolpcev / 2 + 1;
            char[][] besede = new char[najvecBesed][steviloStolpcev];
            int[] dolzineBesed = new int[najvecBesed];
            int steviloBesed = 0;
            int steviloCrk = 0;

            int indeks = 0;
            while (indeks < steviloStolpcev) {
                while (indeks < steviloStolpcev && staraVrstica[indeks] == '_') {
                    indeks++;
                }

                if (indeks < steviloStolpcev) {
                    int dolzina = 0;
                    while (indeks < steviloStolpcev && staraVrstica[indeks] != '_') {
                        besede[steviloBesed][dolzina] = staraVrstica[indeks];
                        dolzina++;
                        indeks++;
                        steviloCrk++;
                    }
                    dolzineBesed[steviloBesed] = dolzina;
                    steviloBesed++;
                }
            }
            int stolpec = 0;
            while (stolpec < steviloStolpcev) {
                novaVrstica[stolpec] = '_';
                stolpec++;
            }

            if (steviloBesed == 0) {
                continue;
            }

            switch (nacin) {
                case "levo" -> {
                    int zacetniPolozaj = 0;
                    poravnava(novaVrstica, besede, dolzineBesed, steviloBesed, zacetniPolozaj);
                }
                case "desno" -> {
                    int uporabljeniZnaki = steviloCrk + steviloBesed - 1;
                    int zacetniPolozaj = steviloStolpcev - uporabljeniZnaki;
                    poravnava(novaVrstica, besede, dolzineBesed, steviloBesed, zacetniPolozaj);
                }
                case "sredina" -> {
                    int uporabljeniZnaki = steviloCrk + steviloBesed - 1;
                    int prostaMesta = steviloStolpcev - uporabljeniZnaki;
                    int zacetniPolozaj = prostaMesta / 2;
                    poravnava(novaVrstica, besede, dolzineBesed, steviloBesed, zacetniPolozaj);
                }
                case "obojestransko" -> {
                    if (steviloBesed == 1) {
                        int polozaj = 0;
                        int i = 0;
                        while (i < dolzineBesed[0]) {
                            novaVrstica[polozaj++] = besede[0][i];
                            i++;
                        }
                    } else {
                        int steviloPresledkov = steviloBesed - 1;
                        int steviloPodcrtajevZaPresledke = steviloStolpcev - steviloCrk;
                        int osnovnoSteviloPodcrtajev = steviloPodcrtajevZaPresledke / steviloPresledkov;
                        int ostanekPodcrtajev = steviloPodcrtajevZaPresledke % steviloPresledkov;

                        int polozaj = 0;
                        int b = 0;
                        while (b < steviloBesed) {
                            int i = 0;
                            while (i < dolzineBesed[b]) {
                                novaVrstica[polozaj++] = besede[b][i];
                                i++;
                            }

                            if (b < steviloBesed - 1) {
                                int podcrtajiVTemPresledku = osnovnoSteviloPodcrtajev + (b < ostanekPodcrtajev ? 1 : 0);
                                int p = 0;
                                while (p < podcrtajiVTemPresledku) {
                                    novaVrstica[polozaj++] = '_';
                                    p++;
                                }
                            }
                            b++;
                        }
                    }
                }
            }
            vrstica++;
        }

        return novaTabela;
    }

    // POMOŽNA METODA – poravnava za večkratno uporabo v Poravnaj Vrstice
    static void poravnava(char[] novaVrstica, char[][] besede, int[] dolzineBesed, int steviloBesed, int zacetniPolozaj) {
        int beseda = 0;
        while (beseda < steviloBesed) {
            int znak = 0;
            while (znak < dolzineBesed[beseda]) {
                novaVrstica[zacetniPolozaj++] = besede[beseda][znak];
                znak++;
            }
            if (beseda < steviloBesed - 1) {
                novaVrstica[zacetniPolozaj++] = '_';
            }
            beseda++;
        }
    }
    // NALOGA 3 - Vstavljanje slike
    public static char[][] vstaviSliko(char[][] tabela, int x, int y, int s, int v) {
        if (tabela == null) return null;
        int visina = tabela.length;
        int sirina = tabela[0].length;

        // Preverjanje mej slike
        if (x < 0 || y < 0 || x + s > sirina || y + v > visina) {
            System.out.println("Napaka: premajhne dimenzije strani.");
            return null;
        }

        // Najdemo vse besede v gridu, ter njihove zacetne pozicije
        int maxBesed = (visina * sirina) / 2 + 1;
        char[][] besede = new char[maxBesed][sirina];
        int[] dolzine = new int[maxBesed];
        int[] zacetkiX = new int[maxBesed];
        int[] zacetkiY = new int[maxBesed];
        int stBesed = 0;

        int r = 0;
        while (r < visina) {
            int c = 0;
            while (c < sirina) {
                while (c < sirina && tabela[r][c] == '_') {
                    c++;
                }
                if (c < sirina) {
                    int zacetekX = c;
                    int len = 0;
                    while (c < sirina && tabela[r][c] != '_') {
                        besede[stBesed][len] = tabela[r][c];
                        len++;
                        c++;
                    }
                    dolzine[stBesed] = len;
                    zacetkiX[stBesed] = zacetekX;
                    zacetkiY[stBesed] = r;
                    stBesed++;
                }
            }
            r++;
        }

        char[][] nova = new char[visina][sirina];
        r = 0;
        while (r < visina) {
            int c = 0;
            while (c < sirina) {
                nova[r][c] = '_';
                c++;
            }
            r++;
        }

        // Risanje slike z "#"
        r = y;
        while (r < y + v) {
            int c = x;
            while (c < x + s) {
                nova[r][c] = '#';
                c++;
            }
            r++;
        }

        int trenutnoX = 0;
        int trenutnoY = 0;
        boolean prisiliPreliv = false;
        int b = 0;
        while (b < stBesed) {
            boolean uporabiOriginal = false;

            if (!prisiliPreliv) {
                int ox = zacetkiX[b];
                int oy = zacetkiY[b];

                boolean sekaSliko = false;
                if (oy >= y && oy < y + v) {
                    if (!(ox + dolzine[b] <= x || ox >= x + s)) {
                        sekaSliko = true;
                    }
                }

                if (!sekaSliko) {
                    uporabiOriginal = true;
                } else {
                    prisiliPreliv = true;
                    trenutnoX = ox;
                    trenutnoY = oy;
                }
            }
            if (prisiliPreliv) {
                if (trenutnoY < zacetkiY[b] || (trenutnoY == zacetkiY[b] && trenutnoX <= zacetkiX[b])) {
                    int ox = zacetkiX[b];
                    int oy = zacetkiY[b];
                    boolean sekaSliko = false;
                    if (oy >= y && oy < y + v) {
                        if (!(ox + dolzine[b] <= x || ox >= x + s)) {
                            sekaSliko = true;
                        }
                    }

                    if (!sekaSliko) {
                        prisiliPreliv = false;
                        uporabiOriginal = true;
                    }
                }
            }

            if (uporabiOriginal) {
                if (dolzine[b] >= 0) System.arraycopy(besede[b], 0, nova[zacetkiY[b]], zacetkiX[b], dolzine[b]);
                trenutnoX = zacetkiX[b] + dolzine[b];
                trenutnoY = zacetkiY[b];
            } else {
                boolean postavljeno = false;
                while (!postavljeno) {
                    if (trenutnoY >= visina) {
                        System.out.println("Napaka: premajhne dimenzije strani.");
                        return null;
                    }
                    int len = dolzine[b];

                    if (trenutnoX + len > sirina) {
                        trenutnoY++;
                        trenutnoX = 0;
                        continue;
                    }
                    boolean presek = false;
                    int i = 0;
                    while (i < len) {
                        if (nova[trenutnoY][trenutnoX + i] == '#') {
                            presek = true;
                            trenutnoX = trenutnoX + i + 1;
                            break;
                        }
                        i++;
                    }
                    if (presek) {
                        continue;
                    }

                    if (len >= 0) System.arraycopy(besede[b], 0, nova[trenutnoY], trenutnoX, len);
                    trenutnoX += len;

                    if (trenutnoX < sirina) {
                        if (nova[trenutnoY][trenutnoX] == '#') {
                            trenutnoX++;
                        } else {
                            trenutnoX += 1;
                        }
                    }
                    postavljeno = true;
                }
            }
            b++;
        }
        return nova;
    }
    // NALOGA 7 - Navpicno besedilo
    public static char[][] navpicnoBesedilo(char[][] tabela) {
        if (tabela == null || tabela.length == 0 || tabela[0].length == 0) {
            return null;
        }

        int visina = tabela.length;
        int novaVisina = tabela[0].length;

        int novaSirina = visina * 2 - 1; // povečaanje grida, da bo dovolj prostora za "_" med vsakim stolpcem

        char[][] nova = new char[novaVisina][novaSirina];


        // filanje nove tabele, z samimi podčrtaji
        int r = 0;

        while (r < novaVisina) {
            int c = 0;
            while (c < novaSirina) {
                nova[r][c] = '_';
                c++;
            }
            r++;
        }
        r = 0; // resetiranje kazalca na vrstice na 0
        // zanka, ki gre skozi staro tabelo, le da vrstice stare tabele prepiše v stolpce nove
        while (r < visina) {
            int c = 0;
            while (c < novaVisina) {
                nova[c][2 * r] = tabela[r][c];
                c++;
            }
            r++;
        }

        return nova;
    }
    // METODA MAIN
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Napaka: neustrezni argumenti.");
            return;
        }

        String operacija;
        String imeDatoteke;
        if (args.length == 2 && args[1].equals("navpicno")) {
            // preverjanje argumentov posebej za 7. nalogo, kjer je operacija prvi argument, ime datoteke pa drugi
            operacija = "navpicno";
            imeDatoteke = args[0];
        } else {
            // preverjanje argumentov za ostale naloge, 1. ime datoteke, 2. operacija
            operacija = args[0];
            imeDatoteke = args[1];
            if (!operacija.equals("branje") && !operacija.equals("poravnaj") && !operacija.equals("slika") && !operacija.equals("navpicno")) {
                System.out.println("Napaka: neustrezni argumenti.");
                return;
            }
        }

        if (operacija.equals("branje") && args.length != 2) {
            System.out.println("Napaka: neustrezni argumenti.");
            return;
        }
        if (operacija.equals("poravnaj") && args.length != 3) {
            System.out.println("Napaka: neustrezni argumenti.");
            return;
        }
        if (operacija.equals("slika") && args.length != 6) {
            System.out.println("Napaka: neustrezni argumenti.");
            return;
        }

        if (operacija.equals("navpicno") && args.length != 2) {
            System.out.println("Napaka: neustrezni argumenti.");
            return;
        }

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
            // pridobitev dimenzij s split po "x"
            String[] dimStr = sc.nextLine().trim().split("x");
            int testSirina = Integer.parseInt(dimStr[0]);
            int testVisina = Integer.parseInt(dimStr[1]);
            if (testSirina <= 0 || testVisina <= 0) {
                System.out.println("Napaka: nepravilen format datoteke.");
                sc.close();
                return;
            }
            sc.close();

            char[][] tabela;
            if (tip.equals("UREJENO")) {
                tabela = preberiUrejenoDatoteko(imeDatoteke);
            } else if (tip.equals("NEUREJENO")) {
                tabela = preberiNeurejenoDatoteko(imeDatoteke);
            } else {
                System.out.println("Napaka: nepravilen format datoteke.");
                return;
            }
            if (tabela == null) {
                return;
            }
            switch (operacija) {
                case "poravnaj" -> {
                    String nacin = args[2];
                    if (!nacin.equals("levo") && !nacin.equals("desno") && !nacin.equals("sredina") && !nacin.equals("obojestransko")) {
                        System.out.println("Napaka: neustrezni argumenti.");
                        return;
                    }
                    char[][] poravnana = poravnajVrstice(tabela, nacin);
                    izpisi(poravnana);
                }
                case "slika" -> {
                    try {
                        int x = Integer.parseInt(args[2]);
                        int y = Integer.parseInt(args[3]);
                        int s = Integer.parseInt(args[4]);
                        int v = Integer.parseInt(args[5]);
                        char[][] slikaTabela = vstaviSliko(tabela, x, y, s, v);
                        izpisi(slikaTabela);
                    } catch (NumberFormatException e) {
                        System.out.println("Napaka: neustrezni argumenti.");
                    }
                }
                case "navpicno" -> {
                    char[][] navpicna = navpicnoBesedilo(tabela);
                    izpisi(navpicna);
                }
                default -> izpisi(tabela);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Napaka: datoteka ne obstaja.");
        }
    }
}
