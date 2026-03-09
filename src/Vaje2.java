public class Vaje2 {

    static long fakulteta(int n){
        long rezultat = n;

        for (int i=n-1; i>0; i--){
            rezultat *= i;

        }
        return rezultat;


    }


    public static void main(String[] args){


        // NALOGA 1 - klici funckij:

        System.out.println(fakulteta(5));



    }
}
