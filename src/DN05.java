import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class DN05 {
    public static void main(String[] args) {
        String imeDatoteke = args[1];
        try (Scanner sc = new Scanner(new File(imeDatoteke))) {
            if (!sc.hasNextLine()) {
                System.out.println("Napaka: nepravilen format datoteke.");
                return;
            }

            String tip = sc.nextLine().trim();
            char[][] tabela;

            if (tip.equals("UREJENO")) {
                tabela = preberiUrejenoDatoteko(imeDatoteke);
            } else if (tip.equals("NEUREJENO")) {
                tabela = preberiNeurejenoDatoteko(imeDatoteke);
            } else {
                System.out.println("Napaka: nepravilen format datoteke.");
                return;
            }

            izpisi(tabela);
        } catch (FileNotFoundException e) {
            System.out.println("Napaka: datoteka ne obstaja.");
        }
    }

    public static char[][] preberiUrejenoDatoteko(String imeDatoteke) {
        try (Scanner sc = new Scanner(new File(imeDatoteke))) {
            sc.nextLine(); // skip first line (already read in main)

            if (!sc.hasNextLine()) {
                System.out.println("Napaka: nepravilen format datoteke.");
                return null;
            }
            int[] dim = preberiDimenzije(sc.nextLine().trim());
            if (dim == null) {
                System.out.println("Napaka: nepravilen format datoteke.");
                return null;
            }

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
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static char[][] preberiNeurejenoDatoteko(String imeDatoteke) {
        try (Scanner sc = new Scanner(new File(imeDatoteke))) {
            sc.nextLine(); // skip first line (already read in main)

            if (!sc.hasNextLine()) {
                System.out.println("Napaka: nepravilen format datoteke.");
                return null;
            }
            int[] dim = preberiDimenzije(sc.nextLine().trim());
            if (dim == null) {
                System.out.println("Napaka: nepravilen format datoteke.");
                return null;
            }

            int sirina = dim[0];
            int visina = dim[1];

            StringBuilder besedilo = new StringBuilder();
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (!line.isEmpty()) {
                    if (besedilo.length() > 0) {
                        besedilo.append(' ');
                    }
                    besedilo.append(line);
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

                if (c == 0) {
                    if (c + len <= sirina) {
                        for (int i = 0; i < len; i++) {
                            tabela[r][c + i] = beseda.charAt(i);
                        }
                        c += len;
                    } else {
                        r++;
                        if (r >= visina) {
                            System.out.println("Napaka: premajhne dimenzije strani.");
                            return null;
                        }
                        for (int i = 0; i < len; i++) {
                            tabela[r][i] = beseda.charAt(i);
                        }
                        c = len;
                    }
                } else {
                    if (c + 1 + len <= sirina) {
                        tabela[r][c] = '_';
                        for (int i = 0; i < len; i++) {
                            tabela[r][c + 1 + i] = beseda.charAt(i);
                        }
                        c += 1 + len;
                    } else {
                        r++;
                        if (r >= visina) {
                            System.out.println("Napaka: premajhne dimenzije strani.");
                            return null;
                        }
                        for (int i = 0; i < len; i++) {
                            tabela[r][i] = beseda.charAt(i);
                        }
                        c = len;
                    }
                }
            }

            return tabela;
        } catch (FileNotFoundException e) {
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

    private static int[] preberiDimenzije(String dimenzije) {
        if (dimenzije == null) {
            return null;
        }

        String[] deli = dimenzije.trim().split("x");
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
}
