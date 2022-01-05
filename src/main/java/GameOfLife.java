
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author malchi
 */
public class GameOfLife {

    /**
     * This function make an game board
     */
    public static int[][] makeArray(int x, int y) {
        int[][] original = new int[x][y];
        int i, j;
        for (i = 0; i < original.length; i++) {
            for (j = 0; j < original[i].length; j++) {
                original[i][j] = (int) (Math.random() * 2);
            }
        }
        return original;
    }

    /**
     * This function count how many lives there are around a given organ
     */
    public static int howManyLife(int row, int column, int locI, int locJ, int[][] mat) {
        int i, j, sum = 0;
        for (i = locI - 1; i <= locI + 1; i++) {
            for (j = locJ - 1; j <= locJ + 1; j++) {
                if (i == locI && j == locJ) {
                    continue;
                }
                if (i < 0 || i > row - 1 || j < 0 || j > column - 1) {
                    continue;
                }
                if (mat[i][j] == 1) {
                    sum = sum + 1;
                }
            }
        }
        return sum;
    }

    /**
     * This function check if a given organ should live
     */
    public static boolean isWakeUp(int row, int column, int locI, int locJ, int[][] mat) {
        if (howManyLife(row, column, locI, locJ, mat) == 3) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This function check if a given organ should die
     */
    public static boolean isDead(int row, int column, int locI, int locJ, int[][] mat) {
        if (howManyLife(row, column, locI, locJ, mat) <= 1) {
            return true;
        } else if (howManyLife(row, column, locI, locJ, mat) >= 4) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method takes a number of rows and columns and displays the board
     * before and after the change.
     */
    public static void scanTheMat() {
        int row, column, i, j, temp[][];
        do {
            System.out.println("enter num of rows and column : ");
            row = scan.nextInt();
            column = scan.nextInt();
        } while (row <= 0 || column <= 0);
        int[][] mat = makeArray(row, column);
        temp = new int[row][column];
        for (i = 0; i < mat.length; i++) {
            for (j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    if (isWakeUp(row, column, i, j, mat)) {
                        temp[i][j] = 1;
                    } else {
                        temp[i][j] = 0;
                    }
                } else {
                    if (isDead(row, column, i, j, mat)) {
                        temp[i][j] = 0;
                    } else {
                        temp[i][j] = 1;
                    }
                }
            }
        }
        for (i = 0; i < mat.length; i++) {
            for (j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("------------------");

        for (i = 0; i < mat.length; i++) {
            for (j = 0; j < mat[i].length; j++) {
                mat[i][j] = temp[i][j];
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        // TODO code application logic here
        scanTheMat();
    }

}
