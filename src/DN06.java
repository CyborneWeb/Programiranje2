public class DN06 {
    public static void main(String[] args) {
        String numbers = args[0];

        int size = 540;
        int cellSize = size / 9;

        StdDraw.setCanvasSize(size, size);
        StdDraw.setXscale(0, size);
        StdDraw.setYscale(0, size);
        StdDraw.clear(StdDraw.WHITE);

        // Draw numbers
        StdDraw.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 28));
        StdDraw.setPenColor(StdDraw.BLACK);

        for (int i = 0; i < 81; i++) {
            char c = numbers.charAt(i);
            if (c != '0') {
                int row = i / 9; // 0 = top row
                int col = i % 9;
                double x = col * cellSize + cellSize / 2.0;
                double y = size - (row * cellSize + cellSize / 2.0);
                StdDraw.text(x, y, String.valueOf(c));
            }
        }

        // Draw thin grid lines (all cells)
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.001);
        for (int i = 0; i <= 9; i++) {
            double pos = i * cellSize;
            StdDraw.line(pos, 0, pos, size);
            StdDraw.line(0, pos, size, pos);
        }

        // Draw thick lines for 3x3 boxes
        StdDraw.setPenRadius(0.005);
        for (int i = 0; i <= 9; i += 3) {
            double pos = i * cellSize;
            StdDraw.line(pos, 0, pos, size);
            StdDraw.line(0, pos, size, pos);
        }

        StdDraw.save("slika.png");
    }
}
