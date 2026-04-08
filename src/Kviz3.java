public class Kviz3 {
    public int[] sestejPolinoma(int[] a, int[] b){
        int max = Math.max(a.length, b.length);
        int[] rezultat = new int[max];
        for (int i = 0; i<max; i++){
            int vrednost1 = (i >= a.length ? 0 : a[i]);
            int vrednost2 = (i >= b.length ? 0 : b[i]);
            rezultat[i] = vrednost1 + vrednost2;
        }
        return rezultat;
    }
 }
