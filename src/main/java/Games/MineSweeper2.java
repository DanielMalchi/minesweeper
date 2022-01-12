package Games;

public class MineSweeper2 {

    /*
    num of mines
     */
    private int numOfMines;
    /*
    num of rows
     */
    private int numOfRows;
    /*
    num of columns
     */
    private int numOfColumns;
    /*
    the game board
     */
    private int board[][];
    /*
    max of rows
     */
    private final int MAXROWS = 20;
    /*
    max of columns
     */
    private final int MAXCOLUMNS = 20;
    /*
    A variable that announces if the code is running
     */
    private boolean isRuning = true;

    /**
     * create the game board validate the values
     *
     * @param numOfMines - num of mines
     * @param numOfRows - num of rows
     * @param numOfColumns - num of columns
     * @throws IllegalArgumentException
     */
    public MineSweeper2(int numOfRows, int numOfColumns, int numOfMines)
            throws Exception {
        validationsValues(numOfColumns, numOfRows, numOfMines);
        this.numOfColumns = numOfColumns;
        this.numOfRows = numOfRows;
        this.numOfMines = numOfMines;
        initBoard(numOfColumns, numOfRows, numOfMines);
    }

    /**
     * validate the values of the rows, columns and the mines
     *
     * @param numOfColumn - num of columns
     * @param numOfRow - num of rows
     * @param numOfMines - num of mines
     * @throws Exception
     */
    private void validationsValues(int numOfColumn, int numOfRow, int numOfMines)
            throws Exception {
        if (numOfColumn <= 0 || numOfColumn > MAXCOLUMNS) {
            throw new Exception("illegal num of columns");
        }
        if (numOfRow <= 0 || numOfRow > MAXROWS) {
            throw new Exception("illegal num of rows");
        }
        if (numOfMines > ((numOfRow * numOfColumn) - 1) || numOfMines <= 0) {
            throw new Exception("illegal num of mines, try again");
        }
    }

    /**
     * restart the board
     *
     * @param numOfColumns - num of columns
     * @param numOfRows - num of rows
     * @param numOfMines - num of mines
     */
    private void initBoard(int numOfColumns, int numOfRows, int numOfMines) {
        int i, j;
        board = new int[numOfRows][numOfColumns];
        randomMine(board, numOfMines);
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[i].length; j++) {
                if (board[i][j] != 9) {
                    board[i][j] = -1;
                }
            }

        }
    }

    /**
     * puts mines randomly in the board
     *
     * @param board - the game board
     * @param numOfMines - num of mines
     */
    private void randomMine(int[][] board, int numOfMines) {
        int i, j;
        while (numOfMines != 0) {
            i = (int) (Math.random() * board.length);
            j = (int) (Math.random() * board[0].length);
            if (board[i][j] == 9) {
                continue;
            }
            board[i][j] = 9;
            numOfMines--;
        }
    }

    /**
     * check how many mines there are around the position
     *
     * @param positionRow - position a row that is clicked on the board
     * @param positionColumn - position a column that is clicked on the board
     * @return how many mine there are around the position
     */
    public int chackNumOfMines(int positionRow, int positionColumn) {
        int i, arr[], count = 0, row, col;
        arr = getNearField(positionRow, positionColumn);
        for (i = 0; i < arr.length; i += 2) {
            row = arr[i];
            col = arr[i + 1];
            if (board[row][col] == 9) {
                count++;
            }
        }
        return count;
    }

    /**
     * the method open all the fields around a empty position
     *
     * @param positionRow - empty position a row on the board
     * @param positionColumn - empty position a column on the board
     */
    public void openEmptyField(int positionRow, int positionColumn) {
        int num, arr[], i;
        if (board[positionRow][positionColumn] != -1) {
            return;
        }
        num = chackNumOfMines(positionRow, positionColumn);
        if (num != 0) {
            board[positionRow][positionColumn] = num;
            return;
        }
        arr = getNearField(positionRow, positionColumn);
        for (i = 0; i < arr.length; i += 2) {
            openEmptyField(arr[i], arr[i + 1]);
        }
    }

    /**
     * enter the array all possible location around a given square
     *
     * @param positionRow - empty position a row on the board
     * @param positionColumn - empty position a column on the board
     * @return
     */
    public int[] getNearField(int positionRow, int positionColumn) {
        int arr[], i, j, p;
        if (positionRow == 0 || positionRow == board.length) {
            if (positionColumn == 0 || positionColumn == board[positionRow].length) {
                arr = new int[6];

            } else {
                arr = new int[10];
            }
        } else if (positionColumn == 0 || positionColumn == board[positionRow].length) {
            arr = new int[10];
        } else {
            arr = new int[16];
        }
        p = 0;
        for (i = positionRow - 1; i <= positionRow + 1; i++) {
            if (i < 0 || i >= board.length) {
                continue;
            }
            for (j = positionColumn - 1; j <= positionColumn + 1; j++) {
                if (j < 0 || j >= board[i].length) {
                    continue;
                }
                if (i == positionRow && j == positionColumn) {
                    continue;
                }
                arr[p] = i;
                arr[p + 1] = j;
                p = p + 2;
            }
        }
        return arr;
    }

    /**
     * the method check if you press on a mine. if you prees on a mine you lose
     * the game.
     *
     * @param positionRow -
     * @param positionColumn -
     */
    public void boom(int positionRow, int positionColumn) {
        if (board[positionRow][positionColumn] == 9) {
            return;
        }
        isRuning = false;
    }

    /**
     * the method check win. if there aren't squares with -1 there is win.
     */
    public void win() {
        int i, j;
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[i].length; j++) {
                if (board[i][j] == -1) {
                    return;
                }
            }
        }
        isRuning = false;
    }

}
