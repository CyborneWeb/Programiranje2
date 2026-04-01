void main(String[] args) {

    String binarno = args[0];
    String niz = "";

    for (int i = 0; i < binarno.length() / 8; i++) {
        // pridobivanje podnizov dolžine 8, kjer je začetni indeks vsakega poddniza 8*i (npr. 0, 8, 16) končni pa 8*(i+1) (npr. 8, 16, 24),
        // ter pretvarjanje v Ascii kodo z Integer.parseInt(... , 2);
        int ascii = Integer.parseInt(binarno.substring(8 * i, 8 * (i + 1)), 2);
        niz += (char) ascii;
    }
    IO.println(niz);
}
