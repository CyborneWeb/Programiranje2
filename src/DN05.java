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
        if (!operacija.equals("branje") && !operacija.equals("poravnaj") && !operacija.equals("slika")) {
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
        if (operacija.equals("slika") && args.length != 6) {
            System.out.println("Napaka: neustrezni argumenti.");
            return;
        }
        String imeDatoteke = args[1];

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


            if (operacija.equals("poravnaj")) {
                String nacin = args[2];
                if (!nacin.equals("levo") && !nacin.equals("desno") && !nacin.equals("sredina") && !nacin.equals("obojestransko")) {
                    System.out.println("Napaka: neustrezni argumenti.");
                    return;
                }
                char[][] poravnana = poravnajVrstice(tabela, nacin);
                izpisi(poravnana);
            } else if (operacija.equals("slika")) {
                try {
                    int x = Integer.parseInt(args[2]);
                    int y = Integer.parseInt(args[3]);
                    int s = Integer.parseInt(args[4]);
                    int v = Integer.parseInt(args[5]);
                    char[][] slikaTabela = vstaviSliko(tabela, x, y, s, v);
                    izpisi(slikaTabela);
                } catch (NumberFormatException e) {
                    System.out.println("Napaka: neustrezni argumenti.");
                    return;
                }
            } else {
                izpisi(tabela);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Napaka: datoteka ne obstaja.");
        } catch (Exception e) {

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


            int maxBesed = sirina / 2 + 1;
            char[][] besede = new char[maxBesed][sirina];
            int[] dolzine = new int[maxBesed];
            int stBesed = 0;
            int stZnakov = 0;

            int i = 0;
            while (i < sirina) {

                while (i < sirina && stara[i] == '_') {
                    i++;
                }
                if (i < sirina) {

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


            for (int k = 0; k < sirina; k++) {
                nova[k] = '_';
            }

            if (stBesed == 0) {
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



    public static char[][] preberiUrejenoDatoteko(String imeDatoteke) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(imeDatoteke))) {
            sc.nextLine();

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
            sc.nextLine();

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

        for (int r = 0; r < visina; r++) {
            int c = 0;
            while (c < sirina) {
                while (c < sirina && tabela[r][c] == '_') {
                    c++;
                }
                if (c < sirina) {
                    int zacetekX = c;
                    int zacetekY = r;
                    int len = 0;
                    while (c < sirina && tabela[r][c] != '_') {
                        besede[stBesed][len] = tabela[r][c];
                        len++;
                        c++;
                    }
                    dolzine[stBesed] = len;
                    zacetkiX[stBesed] = zacetekX;
                    zacetkiY[stBesed] = zacetekY;
                    stBesed++;
                }
            }
        }

        char[][] nova = new char[visina][sirina];
        for (int r = 0; r < visina; r++) {
            for (int c = 0; c < sirina; c++) {
                nova[r][c] = '_';
            }
        }

        // Risanje slike z "#"
        for (int r = y; r < y + v; r++) {
            for (int c = x; c < x + s; c++) {
                nova[r][c] = '#';
            }
        }


        int trenutnoX = 0;
        int trenutnoY = 0;
        boolean forceFlow = false;

        for (int b = 0; b < stBesed; b++) {
            boolean useOriginal = false;


            if (!forceFlow) {
                int ox = zacetkiX[b];
                int oy = zacetkiY[b];

                boolean sekaSliko = false;
                if (oy >= y && oy < y + v) {
                    if (!(ox + dolzine[b] <= x || ox >= x + s)) {
                        sekaSliko = true;
                    }
                }

                if (!sekaSliko) {
                    useOriginal = true;
                } else {
                    forceFlow = true;
                    trenutnoX = ox;
                    trenutnoY = oy;
                }
            }

            if (forceFlow) {
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
                        forceFlow = false;
                        useOriginal = true;
                    }
                }
            }

            if (useOriginal) {
                for (int i = 0; i < dolzine[b]; i++) {
                    nova[zacetkiY[b]][zacetkiX[b] + i] = besede[b][i];
                }
                trenutnoX = zacetkiX[b] + dolzine[b];
                trenutnoY = zacetkiY[b];
            } else { // Reflow block
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
                    for (int i = 0; i < len; i++) {
                        if (nova[trenutnoY][trenutnoX + i] == '#') {
                            presek = true;
                            trenutnoX = trenutnoX + i + 1;
                            break;
                        }
                    }
                    if (presek) {
                        continue;
                    }

                    for (int i = 0; i < len; i++) {
                        nova[trenutnoY][trenutnoX + i] = besede[b][i];
                    }
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
        }
        return nova;
    }
}
