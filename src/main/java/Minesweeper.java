
import java.util.*;

public class Minesweeper {

    //the original board game
    static char[][] arr;
    //empty bord game
    static char[][] temp;
    //A message that displays an alert accordingly
    static String msg;
    //num of rows on the bourd
    static int rows;
    //num of columns on the bourd
    static int columns;

    /**
     * The method places bombs on the board
     * @param numOfBombs 
     */
    public static void randomBomb(int numOfBombs) {
        int i, j;
        while (numOfBombs != 0) {
            i = (int) (Math.random() * arr.length);
            j = (int) (Math.random() * arr[0].length);
            if (arr[i][j] == '*') {
                continue;
            }
            arr[i][j] = '*';
            numOfBombs--;
        }
    }

    /**
     * the method count how many bombs there are around each place
     * @param x
     * @param y
     * @return 
     */
    public static int countTheBombs(int x, int y) {
        int count = 0, i, j;
        for (i = x - 1; i <= x + 1; i++) {
            for (j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (i < 0 || i > rows - 1 || j < 0 || j > columns - 1) {
                    continue;
                }
                if (arr[i][j] == '*') {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * The method fills the board by bombs
     */
    public static void fillTheBoard() {
        int count;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (arr[i][j] != '*') {
                    count = countTheBombs(i, j);
                    arr[i][j] = (char) (count + 48);
                }
            }
        }
    }

    /**
     * the method print the bord
     * @param arr 
     */
    public static void printTheBoard(char[][] arr) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(arr[i][j] + " | ");
            }
            System.out.println();
            for (int j = 0; j < columns; j++) {
                System.out.print("----");
            }
            System.out.println();
        }
    }

    /**
     * the function check if the user win
     * @return 
     */
    public static boolean checkIfWin() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //if the place fill a * and you didn't open this place
                if (arr[i][j] == '*' && temp[i][j] == ' ') {
                    continue;
                }
                //if you have an empty place
                if (temp[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * the method get rows and columns and prepare bouth the board
     */
    public static void PrepareBoard() {
        int i, j, bombs;
        do {
            System.out.println("enter num of rows :");
            rows = scan.nextInt();
            System.out.println("enter num of columns :");
            columns = scan.nextInt();
        } while (rows <= 0 || columns <= 0);
        arr = new char[rows][columns];
        do {
            System.out.println("how many bombs do you want ?");
            bombs = scan.nextInt();
        } while (bombs > rows * columns - 1 || bombs == 0);
        randomBomb(bombs);
        fillTheBoard();
        temp = new char[rows][columns];
        for (i = 0; i < rows; i++) {
            for (j = 0; j < columns; j++) {
                temp[i][j] = ' ';
            }
        }
        printTheBoard(temp);
        game();
    }
    
        /**
         * The method check which of the place should be open
         * according to what the user requested
         */
        public static void game() {
        int x, y;
        do {
            System.out.println("enter the loction that you want to see :");
            x = scan.nextInt();
            y = scan.nextInt();
        } while (x < 0 || y < 0 || x >= rows || y >= columns);
        if (arr[x][y] == '*') {
            msg = "yoe lose because you hit the bomb";
            printTheBoard(arr);
            System.out.println("GAME OVER");
            System.out.println(msg);
            return;
        } else {
            openArea(x, y);//open around the place
            printTheBoard(temp);
            System.out.println();
        }
        if (checkIfWin()) {
            msg = "You win!!!";
            printTheBoard(arr);
            System.out.println("GAME OVER");
            System.out.println(msg);
            return;
        }
        game();
    }

        /**
         * the method open more then one place
         * @param x
         * @param y 
         */
    public static void openArea(int x, int y) {
        //boundary check
        if (x < 0 || y < 0 || x >= rows || y >= columns) {
            return;
        }
        if (arr[x][y] != '0' && arr[x][y] != '*') {
            temp[x][y] = arr[x][y];
            return;
        }
        //if you have already checked the place and it contains 0
        if (temp[x][y] == '0') {
            return;
        }
        temp[x][y] = arr[x][y];
        //scan around the place
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                //boundary check
                if (i < 0 || j < 0 || i >= rows || j >= columns) {
                    continue;
                }
                //this is your place
                if (i == x && j == y) {
                    continue;
                }
                openArea(i, j);
            }
        }
    }

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        PrepareBoard();
        
    }

}
