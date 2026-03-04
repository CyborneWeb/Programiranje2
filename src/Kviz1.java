public class Kviz1 {

    public static void krog(double r, int d){
        if(r < 0){
            System.out.printf("Napaka: negativen polmer");
            return;
        }
        if(d < 0){
            System.out.printf("Napaka: negativen d");
            return;
        }

        double obseg = 2 * Math.PI * r;
        double ploscina = Math.PI * Math.pow(r, 2);
        System.out.printf("Obseg kroga s polmerom r=%.2f je %." + d + "f \n", r, obseg);
        System.out.printf("Ploscina kroga s polmerom r=%.2f je %." + d + "f \n", r, ploscina);

    }
    static void java() {
        System.out.println(
                "   J    a   v     v  a                                                 \n" +
                "   J   a a   v   v  a a                                                \n" +
                "J  J  aaaaa   V V  aaaaa                                               \n" +
                " JJ  a     a   V  a     a"

        );
    }

    static void kalkulator(int a, int b){
        if (b == 0){
            System.out.println("Napaka: deljenje z 0");
            return;
        }

        System.out.printf("%d + %d = %d\n", a, b, a+b);
        System.out.printf("%d - %d = %d\n", a, b, a-b);
        System.out.printf("%d x %d = %d\n", a, b, a*b);
        System.out.printf("%d / %d = %d\n", a, b, a/b);
        System.out.printf("%d %% %d = %d\n", a, b, a%b);

    }
    static void nicli(int a, int b, int c) {
        double d = Math.pow(b, 2) - (4 * a * c);

        if (d < 0){
            System.out.println("Napaka: nicli enacbe ne obstajata");
            return;
        }

        double nicla1 = (-b + Math.sqrt(d)) / (2 * a);
        double nicla2 = (-b - Math.sqrt(d)) / (2 * a);

        System.out.printf("x1=%.2f, x2=%.2f", nicla1, nicla2);
    }
    static String pretvoriSekunde(int sekunde){
        if (sekunde < 0) {
            return "Število sekund ne more biti negativno";
        }
        int ure = sekunde / 3600;

        int minute = (sekunde - ure * 3600) / 60;

        int ostale = (sekunde - ure * 3600 - minute * 60);

        return String.format("%02d:%02d:%02d", ure, minute, ostale);


    }
    public static void main(String[] args){
        //kalkulator(42, 13);
        //nicli(1, 2, 2);
        System.out.println(pretvoriSekunde(65));

    }
}
