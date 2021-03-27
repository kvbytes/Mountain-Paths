/*
* Mountain Paths Greedy Algorithm
* Math 140 - Data Structures
* Spring 2018
*/
import java.awt.*;
import java.io.*;
import java.util.*;

public class MapDataDrawer {

    public int[][] array;
    public int[] diffArray;
    int rows, cols, index;

    public MapDataDrawer(String filename, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        array = new int[rows][cols];
        try {
            Scanner input = new Scanner(new File(filename));
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < cols; k++) {
                    if (input.hasNextInt()) {
                        array[j][k] = input.nextInt();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error." + e);
        }
    }

    public int findMinValue() {
        int min = array[0][0];
        for (int j = 0; j < rows; j++) {
            for (int k = 0; k < cols; k++) {
                if (array[j][k] < min) {
                    min = array[j][k];
                }
            }
        }
        return min;
    }

    public int findMaxValue() {
        int max = array[0][0];
        for (int j = 0; j < rows; j++) {
            for (int k = 0; k < cols; k++) {
                if (array[j][k] > max) {
                    max = array[j][k];
                }
            }
        }
        return max;
    }

    public void drawMap(Graphics g) {
        int min = findMinValue();
        int max = findMaxValue();
        // Scale finds the amount of numbers that will correspond to a shade.
        double scale = 255.0 / (max - min);
        int c;
        /* If the scale is < 1 then multiple values in the array will
         * correspond to the same shade.
         */
        if (scale < 1) {
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    c = (int) (255 - ((max - array[y][x]) * scale));
                    g.setColor(new Color(c, c, c));
                    g.fillRect(x, y, 1, 1);
                }
            }
        } 
        /* If the difference between the max/min is smaller than 255,
         * then multiple values will not need the same shade.
         */ 
        else {
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    c = (int) (255 - ((array[y][x] - min) / scale));
                    g.setColor(new Color(c, c, c));
                    g.fillRect(x, y, 1, 1);
                }
            }
        }
    }

    public int indexOfMinInCol(int col) {
        int min = array[0][col];
        int row = 0;
        for (int j = 0; j < rows; j++) {
            if (array[j][col] < min) {
                min = array[j][col];
                row = j;
            }
        }
        return row;
    }

    public int drawLowestElevPath(Graphics g, int row) {
        int totalDiff = 0;
        int lowest;
        g.fillRect(0, row, 1, 1); // colors the first step

        // This for-loop will create the path in a given row.
        for (int k = 0; k < cols - 1; k++) {
            int current = array[row][k];
            int fwd = array[row][k + 1];
            int fwdUp;
            int fwdDown;

            // Both if-loops check if the current row is the first or last row.
            if (row == 0) {
                /* A large number will cause fwd-up to never be the lowest
                 * difference in case the current row is 0.
                 */
                fwdUp = 2147483647;
            } else {
                fwdUp = array[row - 1][k + 1];
            }
            if (row == rows - 1) {
                fwdDown = 2147483647;
            } else {
                fwdDown = array[row + 1][k + 1];
            }

            // Finding where to take the next step.
            if (Math.abs(fwdUp - current) != Math.abs(fwdDown - current)) {

                lowest = fwd;
                if (Math.abs(fwdDown - current) < Math.abs(lowest - current)) {
                    lowest = fwdDown;
                }
                if (Math.abs(fwdUp - current) < Math.abs(lowest - current)) {
                    lowest = fwdUp;
                }
            } else {
                lowest = fwd;
                if (Math.abs(fwdUp - current) < Math.abs(lowest - current)) {
                    double temp = Math.random();
                    if (temp >= 0.5) {
                        lowest = fwdUp;
                    } else {
                        lowest = fwdDown;
                    }

                }
            }

            // Adding to the total difference and taking the next step.
            if (lowest == fwdDown) {
                totalDiff += Math.abs(fwdDown - current);
                row++;
            } else if (lowest == fwdUp) {
                totalDiff += Math.abs(fwdUp - current);
                row--;
            } else {
                totalDiff += Math.abs(fwd - current);
            }

            // Coloring the chosen step.
            g.fillRect(k + 1, row, 1, 1);

        }
        return totalDiff;

    }

    public int indexOfLowestElevPath(Graphics g) {
        // Finding the lowest path and placing it in an array.
        int diff;
        diffArray = new int[rows];
        for (int j = 0; j < rows; j++) {
            diff = drawLowestElevPath(g, j);
            diffArray[j] = diff;
            //System.out.println("[index = " + j + "] [diff = " + diff + "]");
        }

        // Finding the lowest difference in the array.
        index = 0;
        int min = diffArray[0];
        for (int j = 0; j < diffArray.length; j++) {
            if (diffArray[j] < min) {
                min = diffArray[j];
                index = j;
            }
        }

        // Returns the row with the lowest elevation difference.
        return index;
    }
}
