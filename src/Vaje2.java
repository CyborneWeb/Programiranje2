public class Vaje2 {

    // izračuni z celoštevilski tipi "long"

    static long fakultetaL(int n){
        long rezultat = 1;

        for (int i=2; i<=n; i++){
            rezultat *= i;
        }
        return rezultat;
    }

    static long stirlingL(int n){
        long rezultat = (long) Math.round(Math.sqrt(2*Math.PI * n)  * Math.pow((n/Math.E), n));

        return rezultat;
    }

    // izračuni z približkom v realni vrednosti "double"

    static double fakultetaD(int n){
        double rezultat = 1;

        for (int i=2; i<=n; i++){
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
            long f = fakultetaL(i);
            long s = stirlingL(i);

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

    // Dodatne naloge

    static double izracunajPiNilakantha(int k){
        double vsota = 3.0;

        for (int i = 1; i<k; i++){
            double imenovalec = (2.0*i) * (2.0*i + 1) * (2.0*i + 2);

            if (i%2 == 1){
                vsota += (4.0/imenovalec);
            } else {
                vsota -= (4.0/imenovalec);
            }
        }

        return vsota;
    }

    // Izpis PI ter Približek Pi z nilakanthavo vrsto

    static void izpisiPi(){
        System.out.printf("%3s %10s %27s %11s\n", "k", "Math.PI", "PI (Nilakantha)", "razlika");
        System.out.println("-----------------------------------------------------------------");

        for (int k = 1; k<=22; k++){
            double pi_priblizek = izracunajPiNilakantha(k);

            float razlika = (float) (Math.PI - pi_priblizek);

            String razlika_format;


            if (razlika > 0) {
                razlika_format = "+" + String.format("%.15f", razlika);
            } else {
                razlika_format = String.format("%.15f", razlika);
            }


            System.out.printf("%3d %19.15f %19.15f %20s\n", k, Math.PI, pi_priblizek, razlika_format);
        }

    }

    public static void main(String[] args){


        // NALOGA 1 - klici funckij "fakulteta" in "stirling":

        System.out.println(fakultetaL(5));
        System.out.println(stirlingL(5));

        // NALOGA 2 - izpis prvih 20 fakultet

        izpis20();

        // NALOGA 3 - poskus - do katere vrendosti n je tip "long" primeren za izračun fakultete

        for(int i = 1; i<100; i++){
            long f = fakultetaL(i);
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

        // DODATNA NALOGA - PRIBLIŽEK PI Z UPORABO NILAKANTHOVE VRSTE

        System.out.println(izracunajPiNilakantha(2));

        izpisiPi();


    }
}
