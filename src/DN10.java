import java.awt.*;
import java.util.Random;

public class DN10 {
    public static void main(String[] args){
        StdDraw.setCanvasSize(800,800);
        StdDraw.setScale(0,1);
        StdDraw.clear(Color.WHITE);

        Tocka[] oglisca = new Tocka[3];
        oglisca[0] = new Tocka(0.1, 0.1);
        oglisca[1] = new Tocka(0.9, 0.1);
        oglisca[2] = new Tocka(0.5, 0.9);


        StdDraw.setPenColor(Color.RED);
        StdDraw.setPenRadius(0.02);
        for (int i = 0; i < 3; i++) {
            StdDraw.point(oglisca[i].x, oglisca[i].y);
        }

        Tocka t = nakljTockaVTrikotniku(oglisca[0], oglisca[1], oglisca[2], new Random());


        StdDraw.setPenColor(Color.BLACK);

        StdDraw.setPenRadius(0.002);

        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            int idx = rand.nextInt(3);
            Tocka o = oglisca[idx];
            t = Tocka.sredina(t, o);
            StdDraw.point(t.x, t.y);
        }

        
        StdDraw.show();


    }

    private static Tocka nakljTockaVTrikotniku(Tocka a, Tocka b, Tocka c, Random rnd) {
        double r1 = rnd.nextDouble();
        double r2 = rnd.nextDouble();

        if (r1 + r2 > 1.0) {
            r1 = 1.0 - r1;
            r2 = 1.0 - r2;
        }

        double x = a.x + r1 * (b.x - a.x) + r2 * (c.x - a.x);
        double y = a.y + r1 * (b.y - a.y) + r2 * (c.y - a.y);
        return new Tocka(x, y);
    }
}

 class Tocka {
    public double x;
    public double y;


    public Tocka(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public static Tocka sredina(Tocka a, Tocka b){
        return new Tocka((a.x + b.x) / 2.0, (a.y + b.y) / 2.0);
    }


}



