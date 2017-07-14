package com.vijay;

/**
 * Created by vkbalakr on 7/12/17.
 */
public class PercolationDetector {

    public static boolean[][] flow(boolean[][] isOpen) {
        int n = isOpen.length;
        boolean[][] isFull = new boolean[n][n];
        //for vertical percolation, start from top row with full sites corr to open sites
        for (int col = 0; col < n; col++) {//starts at 0,0  important to cover all cols of 1st row
            flow(isOpen, isFull, 0, col);//site is marked as full only if it is connected to 1 of the sites on the top row
        }
        return isFull;
    }

    /**
     * Depth first search for flow percolation
     * @param isOpen
     * @param isFull
     * @param row
     * @param col
     */
    private static void flow(boolean[][] isOpen, boolean[][] isFull, int row, int col) {
        //fill every site reachable from row,col
        //base case
        int n = isFull.length;
        if ((row < 0) || (row >= n)) return;//null call
        if ((col < 0 ) || (col >= n)) return;
        if (isOpen[row][col] == false) return;//blocked
        if (isFull[row][col] == true) return;//full site

        //recursion always finishes because it terminate on null case above or isFull being full.
        //reduction case
        //mark site as filled if it got here
        isFull[row][col] = true;//anything that can be reached here is set to full
        //make calls to all its neighbors
        flow(isOpen, isFull, row + 1, col);//down
        flow(isOpen, isFull, row -1, col);//up
        flow(isOpen, isFull, row, col +1);//right
        flow(isOpen, isFull, row, col -1);//left
    }

    public static boolean percolates(boolean[][] isOpen) {

        boolean[][] isFull = flow(isOpen);
        int n = isOpen.length;
        for (int col = 0; col < n; col++) {
            if (isFull[n-1][col]) return true;//check bottom col
        }
        return false;
    }
}
