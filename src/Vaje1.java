public class Vaje1 {

    // 1. NALOGA


    // a)
    static void pravokotnikStevilVrstice(int sirina, int visina){
        for(int i=0; i<visina; i++){
            for(int j=0; j<sirina;j++){
                System.out.print((i+1)%10);
            }
            System.out.print("\n");
        }
    }

    // b)
    static void pravokotnikStevilStolpci(int sirina, int visina){
        for(int i=0; i<visina; i++){
            for(int j=0; j<sirina;j++){
                System.out.print((j+1)%10);
            }
            System.out.print("\n");
        }
    }

    // c)
    static void pravokotnik(int odmik, int sirina, int visina){
            System.out.print((" ".repeat(odmik) + "X".repeat(sirina) + "\n").repeat(visina));
    }

    // 2. NALOGA

    // a)
    static void trikotnikStevilVrstice(int visina){
        for(int i=1; i<=visina; i++){
            System.out.println(((String.valueOf(i%10)).repeat(i)));
        }
    }

    // b)
    static void trikotnikStevilStolpci(int visina){
        for(int i=1; i<=visina; i++){
            for(int j=1; j<=i; j++){
                System.out.print(j%10);
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args){

        System.out.println("\n1. Naloga, a)\n");
        pravokotnikStevilVrstice(7,3);

        System.out.println("\n1. Naloga, b) \n");
        pravokotnikStevilStolpci(15,3);

        System.out.println("\n1. Naloga, c) \n");
        pravokotnik(5,7,3);

        System.out.println("\n2. Naloga, a) \n");
        trikotnikStevilVrstice(3);

        System.out.println("\n2. Naloga, b) \n");
        trikotnikStevilStolpci(3);

        // change 1



    }
}
