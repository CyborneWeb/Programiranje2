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

    public int[] zmnoziPolinoma(int[] a, int[] b) {

        int[] rezultat = new int[a.length + b.length - 1];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                rezultat[i + j] += a[i] * b[j];
            }
        }

        return rezultat;
    }

    public boolean jeAnagram(String prvaBeseda, String drugaBeseda, boolean zanemariVelikost){
        if (prvaBeseda.length() != drugaBeseda.length()) return false;
        if (zanemariVelikost) {
            prvaBeseda = prvaBeseda.toUpperCase();
            drugaBeseda = drugaBeseda.toUpperCase();
        }
        char[] chars1 = prvaBeseda.toCharArray();
        char[] chars2 = drugaBeseda.toCharArray();
        java.util.Arrays.sort(chars1); java.util.Arrays.sort(chars2);
        return java.util.Arrays.equals(chars1, chars2);
    }
 }
