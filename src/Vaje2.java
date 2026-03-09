public class Vaje2 {

    // izračuni z celoštevilski tipi "long"

    static long fakulteta(int n){
        long rezultat = n;

        for (int i=n-1; i>0; i--){
            rezultat *= i;
        }
        return rezultat;
    }

    static long stirling(int n){
        long rezultat = (long) Math.round(Math.sqrt(2*Math.PI * n)  * Math.pow((n/Math.E), n));

        return rezultat;
    }

    // izračuni z približkom v realni vrednosti "double"

    static double fakultetaD(int n){
        double rezultat = n;

        for (int i=n-1; i>0; i--){
            rezultat *= i;
        }
        return rezultat;
    }

    static double stirlingD(int n){
        return Math.sqrt(2*Math.PI * n)  * Math.pow((n/Math.E), n);
    };


    // izpis20 (z celoštevilskimi tipi podatkov) ter izpis100 z približki v tipu "double"

    static void izpis20(){
        System.out.printf("%3s %15s %22s %15s\n", "n", "n!", "Stirling(n)", "napaka (%)");
        System.out.println("-".repeat(58));

        for(int i = 1; i<=20; i++){
            long f = fakulteta(i);
            long s = stirling(i);

            // relativna napaka

            double napaka = 100.0 * (f-s) / f; // napaka postaja vedno manjša večje kot so vrednosti fakultet

            System.out.printf("%3d %20d %20d %11.7f\n", i, f, s, napaka);


        }
    }

    static void izpis100(){
        System.out.printf("%3s %10s %22s %14s\n", "n", "n!", "Stirling(n)", "napaka (%)");
        System.out.println("-".repeat(52));

        for (int i = 1; i<=100; i++){
            double f = fakultetaD(i);
            double s = stirlingD(i);

            double napaka = 100.0 * (f-s) / f;

            System.out.printf("%3d %17.9E %17.9E %11.7f\n", i, f, s, napaka);

        }


    }




    public static void main(String[] args){


        // NALOGA 1 - klici funckij:

        System.out.println(fakulteta(5));
        System.out.println(stirling(5));

        // NALOGA 2 - izpis prvih 20 fakultet

        izpis20();

        // NALOGA 3 - poskus - do katere vrendosti n je tip "long" primeren za izračun fakultete

        for(int i = 1; i<100; i++){
            long f = fakulteta(i);
            if (f <= 0) {
                System.out.printf("long preliv pri n = %d\n", i);
                break;
            }
            System.out.printf("%d %d\n", i, f); //pri n=21 je število že preveliko za tip long...
        }

        // NALOGA 4 - izpisi z približki v realnih številih tipa "double"

        System.out.printf("%7.3E\n", fakultetaD(100));

        System.out.printf("%7.3E\n", stirlingD(100));

        // NALOGA 5 - klic funkcije izpis100 za izris tabele z vrednostnmi in relativno napako
        
        izpis100();


    }
}
