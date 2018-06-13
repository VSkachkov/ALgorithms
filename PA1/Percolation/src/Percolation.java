import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] array;
    private WeightedQuickUnionUF uf;
    private int rowSize;
    private int colSize;

    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        rowSize = n;
        colSize = n;
        array = new int[n][n];
        uf = new WeightedQuickUnionUF(array.length * array[0].length);
    }

    /**
     *
     * @param row value between 1 and row size
     * @param col value between 1 and col size
     */
    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        testCoordinates(row, col);
        if (isOpen(row, col)){
            return;
        }
        array[row - 1][col - 1] = 1;

        //updating connections:
        int numCell = getCellNumber(row, col);
        int leftNeib = getCellNumber(row, col - 1);
        int rightNeib = getCellNumber(row, col + 1);
        int upperNeib = getCellNumber(row - 1, col);
        int lowerNeib = getCellNumber(row + 1, col);
        if (leftNeib != 0 && isOpenByOrderedNumber(leftNeib)) {
            uf.union(numCell - 1, leftNeib - 1);
        }
        if (rightNeib != 0 && isOpenByOrderedNumber(rightNeib)) {
            uf.union(numCell - 1, rightNeib - 1);
        }
        if (upperNeib != 0 && isOpenByOrderedNumber(upperNeib)) {
            uf.union(numCell - 1, upperNeib - 1);
        }
        if (lowerNeib != 0 && isOpenByOrderedNumber(lowerNeib)) {
            uf.union(numCell - 1, lowerNeib - 1);
        }
    }

//    private boolean areConnected(int el1row, int el1col, int el2row, int el2col) {
//        return uf.connected(getCellNumber(el1row, el1col) - 1, getCellNumber(el2row, el2col) - 1);
//    }
//
//    private boolean areConnectedBySerialNums(int el1, int el2) {
//        return uf.connected(el1 - 1, el2 - 1);
//    }

    private int getCellNumber(int row, int col) {
        if (row < 1 || col < 1 || row > rowSize || col > colSize) {
            return 0;
        }
        return (row - 1) * rowSize + col;
    }

    private void testCoordinates(int row, int col) {
        if (row < 1 || row > array.length || col < 1 || col > array[0].length)
            throw new IllegalArgumentException();
    }


    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        testCoordinates(row, col);
        return (array[row - 1][col - 1] == 1);
    }

    private boolean isOpenByOrderedNumber(int num) {
        if (num == 0 || num > colSize * rowSize) {
            throw new IllegalArgumentException();
        }
        int colNum = (num - 1) % colSize + 1;
        int rowNum = (num - 1) / colSize + 1;
        return isOpen(rowNum, colNum);
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        testCoordinates(row, col);
        int currentSite = getCellNumber(row, col);
        if (isOpen(row, col)){
            for (int num = 1; num<=array.length; num++) {
                if (uf.connected(num-1, currentSite-1))
                    return true;
            }
        }
        return false;
    }

    public int numberOfOpenSites()       // number of open sites
    {
        int count = 0;
        for (int j = 0; j < array[0].length; j++) {
            for (int i = 0; i < array.length; i++) {
                if (array[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean percolates()              // does the system percolate?
    {
        for (int a = 1; a <= array.length; a++) {
            if (isFull(colSize, a)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
