public class Vaje3 {
    private static final char crnaPika = '\u2B1B'; // črn kvadratek
    private static final char belaPika = '\u2B1C'; // prazen (bel) kvadratek

    private static final char[] abeceda = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' '};

    private static final short[] kodeZnakov16bit = {
            (short) 0b1111100111111001, // A
            (short) 0b1100101011011010, // B
            (short) 0b1111100010001111, // C
            (short) 0b1110100110011110, // D
            (short) 0b1111111010001111, // E
            (short) 0b1111100011101000, // F
            (short) 0b1111100010111111, // G
            (short) 0b1001100111111001, // H
            (short) 0b1111010001001111, // I
            (short) 0b1111000110011111, // J
            (short) 0b1011110010101001, // K
            (short) 0b1000100010001111, // L
            (short) 0b1111101110011001, // M
            (short) 0b1101101110011001, // N
            (short) 0b1111100110011111, // O
            (short) 0b1111100111111000, // P
            (short) 0b1111100110111111, // Q
            (short) 0b1111100111111010, // R
            (short) 0b1111100011110111, // S
            (short) 0b1111010001000100, // T
            (short) 0b1001100110011111, // U
            (short) 0b1001100110010110, // V
            (short) 0b1001100110111111, // W
            (short) 0b1001011001101001, // X
            (short) 0b1001100111110100, // Y
            (short) 0b1111001001001111, // Z
            (short) 0b0110100110010110, // 0
            (short) 0b0110001000101111, // 1
            (short) 0b1110001001001111, // 2
            (short) 0b1111011100011111, // 3
            (short) 0b1000100111110001, // 4
            (short) 0b1111100011110111, // 5
            (short) 0b1000111110011111, // 6
            (short) 0b1111000100010001, // 7
            (short) 0b1110101111010111, // 8
            (short) 0b1111100111110001, // 9
            0                           // presledek
    };


    static void izpisi16bit(short kodaZnaka) {


        for (int vrstica = 0; vrstica < 4; vrstica++) {
            for (int stolpec = 0; stolpec < 4; stolpec++) {
                int bitIndex = 15 - (vrstica * 4 + stolpec); // 15..0
                boolean prizgan = ((kodaZnaka >> bitIndex) & 1) == 1;
                System.out.print(prizgan ? crnaPika : belaPika);
            }
            System.out.println();
        }
    }


    static void izpisi16bit(short[] nizZnakov){


        for(int vrstica=0; vrstica < 4; vrstica++) {
            for (int znak = 0; znak < nizZnakov.length; znak++) {
                short koda_znaka = nizZnakov[znak];
                for(int stolpec = 0; stolpec < 4; stolpec++){
                    int bitIndex = 15 - (vrstica * 4 + stolpec);
                    boolean prizgan = ((koda_znaka >> bitIndex) & 1) == 1;
                    System.out.print(prizgan ? crnaPika : belaPika);

                } System.out.print(belaPika); // presledek




            }
            System.out.println();
        }

    }






    public static void main(){
        izpisi16bit((short)0b1111100111111001);

        izpisi16bit(new short[] {(short)0b1111100011101000, (short)0b1111100111111010, (short)0b1111010001001111});


    }
}
