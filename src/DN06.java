public class DN06 {
    public static void main(String[] args) {
        String numbers = args[0];

        // Začetno nastavljanje velikosti polja/celic, ter risanje osnovnega platna/nastavljanje "povečave"
        int velikost = 540;
        int velikostCelic = velikost / 9;

        StdDraw.setCanvasSize(velikost, velikost);
        StdDraw.setXscale(0, velikost);
        StdDraw.setYscale(0, velikost);
        StdDraw.clear(StdDraw.WHITE);

        // Začetno risanje tankih črt za vse celice
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.002);
        for (int i = 0; i <= 9; i++) {
            double pozicija = i * velikostCelic;
            StdDraw.line(pozicija, 0, pozicija, velikost);
            StdDraw.line(0, pozicija, velikost, pozicija);
        }

        // Risanje odebeljenih črt
        StdDraw.setPenRadius(0.007);
        for (int i = 0; i <= 9; i += 3) {
            double pozicija = i * velikostCelic;
            StdDraw.line(pozicija, 0, pozicija, velikost);
            StdDraw.line(0, pozicija, velikost, pozicija);
        }

        // Risanje številk na polja
        StdDraw.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 22));
        StdDraw.setPenColor(StdDraw.BLACK);

        for (int i = 0; i < 81; i++) {
            char c = numbers.charAt(i);
            if (c != '0') {
                int vrstica = i / 9;
                int stolpec = i % 9;
                double x = stolpec * velikostCelic + velikostCelic / 2.0;
                double y = velikost - (vrstica * velikostCelic + velikostCelic / 2.0);
                StdDraw.text(x, y, String.valueOf(c));
            }
        }
        //vrstica ki narejeno risbo direktno shrani kot datoteko
        //StdDraw.save("slika.png");
    }
}