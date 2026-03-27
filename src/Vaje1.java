// 1. NALOGA


// a)
static void pravokotnikStevilVrstice(int sirina, int visina) {
    for (int i = 0; i < visina; i++) {
        for (int j = 0; j < sirina; j++) {
            IO.print((i + 1) % 10);
        }
        IO.print("\n");
    }
}

        // b)
        static void pravokotnikStevilStolpci(int sirina, int visina) {
            for (int i = 0; i < visina; i++) {
                for (int j = 0; j < sirina; j++) {
                    IO.print((j + 1) % 10);
                }
                IO.print("\n");
            }
        }

        // c)
        static void pravokotnik(int odmik, int sirina, int visina) {
            IO.print((" ".repeat(odmik) + "X".repeat(sirina) + "\n").repeat(visina));
        }

        // 2. NALOGA

        // a)
        static void trikotnikStevilVrstice(int visina) {
            for (int i = 1; i <= visina; i++) {
                IO.println(((String.valueOf(i % 10)).repeat(i)));
            }
        }

        // b)
        static void trikotnikStevilStolpci(int visina) {
            for (int i = 1; i <= visina; i++) {
                for (int j = 1; j <= i; j++) {
                    IO.print(j % 10);
                }
                IO.print("\n");
            }

        }

        static void trikotnikStevilVrsticeObrnjen(int visina) {
            for (int i = visina; i > 0; i--) {
                IO.println(((String.valueOf(i % 10)).repeat(i)));
            }
        }

        static void trikotnikStevilStolpciObrnjen(int visina) {
            for (int i = visina; i > 0; i--) {
                for (int j = 1; j <= i; j++) {
                    IO.print(j % 10);
                }
                IO.print("\n");
            }
        }

        static void trikotnikStevil(int visina) {
            for (int i = 1; i <= visina; i++) {
                IO.print(" ".repeat(visina - i));
                for (int j = 1; j <= (2 * i - 1); j++) {
                    IO.print(j % 10);
                }
                IO.print("\n");
            }
        }

        static void trikotnik(int odmik, int visina) {
            for (int i = 1; i <= visina; i++) {
                IO.print(" ".repeat(visina - i + odmik));
                IO.print("*".repeat(2 * i - 1));
                IO.print("\n");
            }
        }

        static void trikotnikObrnjen(int odmik, int visina) {
            for (int i = visina; i > 0; i--) {
                IO.print(" ".repeat(visina - i + odmik));
                IO.print("*".repeat(2 * i - 1));
                IO.print("\n");
            }

        }

        // 3. NALOGA

        static void romb(int odmik, int velikost) {
            trikotnik(odmik, velikost);
            trikotnikObrnjen(odmik + 1, velikost - 1);
        }

        // 4. NALOGA

        static void smreka(int v) {
            int velikost = 0;
            for (int i = 1; i <= v; i++) {
                velikost = 2 * i;
                trikotnik(2 * v - velikost, velikost);
            }
            int sirina;
            if (v % 2 == 0) {
                sirina = v + 1;
            } else sirina = v;


            pravokotnik((2 * v - velikost) + (2 * velikost - 1 - sirina) / 2, sirina, 2 * v);
        }


        void main() {
//        pravokotnikStevilVrstice(7,3);
//        pravokotnikStevilStolpci(15,3);
//        pravokotnik(5,7,3);
//        trikotnikStevilVrstice(3);
//        trikotnikStevilStolpci(3);
//        trikotnikStevilVrsticeObrnjen(3);
//        trikotnikStevilStolpciObrnjen(3);
//        trikotnikStevil(5);
//        trikotnik(1,5);
//        trikotnikObrnjen(1,5);

//        romb(2, 5);

//        smreka(7);


        }
