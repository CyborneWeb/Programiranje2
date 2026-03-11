public class Kviz2 {

    // Naloga 1 - 1 točka

    static int vsotaStevk(String str){
        int vsota = 0;
        for (int i = 0; i<str.length(); i++){
            char znak = str.charAt(i);
            if(Character.isDigit(znak)){
                vsota += Integer.parseInt(String.valueOf(znak));
            }
        }
        return vsota;
    }

    // Naloga 2 - 1 točka

    static boolean preveriRep(String a, String b){
        String[] besede = a.split(" ");


        String rep;
        if (a.length() > b.length()){
            rep = a.substring(a.length() - b.length(), a.length()).toLowerCase();
            if (b.toLowerCase().equals(rep)){
                return true;
            }

        }
        else {
            rep = b.substring(b.length() - a.length(), b.length()).toLowerCase();
            if (a.toLowerCase().equals(rep)){
                return true;
            }
        }
        return false;
    }

    // Naloga 3 - 1 točka 

    public static int[] range(int a, int b, int c) {
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();

        for (int i = a; i < b; i += c) {
            list.add(i);
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    public static void main(String[] args){

        System.out.println(vsotaStevk("1a2c"));

    }
}
