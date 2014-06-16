public class Matr {
    int[][] mat = new int[40][20];

    public void insertToMatrix(int x, int y, int val) {
        mat[x + 20][y + 10] = val;
    }

    public int getVal(int x, int y) {
        return mat[x][y];
    }

    public static void main(String[] args) {
        Matr mat = new Matr();

        mat.insertToMatrix(0, 0, 1);
        mat.insertToMatrix(-3, 4, 3);


        for (int y = 19; y >= 0; y--) {
            for (int x = 0; x < 40; x++) {
                if (x == 20) {
                    System.out.print("|");
                }
                System.out.print(mat.getVal(x, y));
            }
            System.out.println(" ");
            if (y == 10) {
                System.out.println("-----------------------------------------");
            }
        }
    }
}