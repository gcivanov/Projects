package com.example.gosho.ninrow;


import java.util.Random;

/**
 * Created by gosho on 5.2.2015 г..
 */
public class Board {


    private char [][]board;
    private final static char emptySymb = 'e';
    private final static char neotralSumb = '.';
    private char playerSymb;
    private char compSymb;

    private int boardSize;
    private int connectN;


    private Point[] lastCoord = new Point[2];

    public Board(){
        this(3,3,'o','x');
    }


    public Board(int boardSize, int connectN, char playerSymb, char compSymb){
        this.boardSize = boardSize;
        this.connectN = connectN;
        this.playerSymb = playerSymb;
        this.compSymb = compSymb;

        board = new char[boardSize][boardSize];
        generateEmptyBoard();
    }

    public void generateEmptyBoard() {
        for (int i = 0; i < board.length; ++i) {
            for(int j = 0 ; j < board[0].length; ++j) {
                board[i][j]= emptySymb;
            }
        }
    }


    public boolean checkCoord(int rc) {
        if(rc >= 0 && rc < boardSize )
            return true;
        return false;
    }

    public void compMove(){

        for(int i = connectN ; i > ((connectN/2 > 1) ? 1 : connectN/2); --i) {

            if(checkRows(playerSymb, i)) {
                System.out.println(" rows "+i+" "+ lastCoord[0] + " "+ lastCoord[1]);
                if(checkCoord(lastCoord[0].x) && checkCoord(lastCoord[0].y) && checkCoord(lastCoord[0].y-1 ) && board[lastCoord[0].x][lastCoord[0].y-1] == emptySymb ) {
                    System.out.println(" 1");
                    board[lastCoord[0].x][lastCoord[0].y-1] = compSymb;
                    return;
                }else if(checkCoord(lastCoord[0].x) && checkCoord(lastCoord[0].y) && checkCoord(lastCoord[0].y+1 ) && board[lastCoord[0].x][lastCoord[0].y+1] == emptySymb ) {
                    System.out.println(" 2");
                    board[lastCoord[0].x][lastCoord[0].y+1] = compSymb;
                    return;
                } else if(checkCoord(lastCoord[1].x) && checkCoord(lastCoord[1].y) && checkCoord(lastCoord[1].y-1) && board[lastCoord[1].x][lastCoord[1].y-1] == emptySymb ) {
                    System.out.println(" 3");
                    board[lastCoord[1].x][lastCoord[1].y-1] = compSymb;
                    return;
                }else if(checkCoord(lastCoord[1].x) && checkCoord(lastCoord[1].y) && checkCoord(lastCoord[1].y+1) && board[lastCoord[1].x][lastCoord[1].y+1] == emptySymb ) {
                    System.out.println(" 4");
                    board[lastCoord[1].x][lastCoord[1].y+1] = compSymb;
                    return;
                }
            }

            if(checkColumns(playerSymb, i)) {
                System.out.println(" columns "+i+" "+ lastCoord[0] + " "+ lastCoord[1] );
                if(checkCoord(lastCoord[0].x) && checkCoord(lastCoord[0].y) && checkCoord(lastCoord[0].x-1 ) && board[lastCoord[0].x-1][lastCoord[0].y] == emptySymb ) {
                    System.out.println(" 1");
                    board[lastCoord[0].x-1][lastCoord[0].y] = compSymb;
                    return;
                }else if(checkCoord(lastCoord[0].x) && checkCoord(lastCoord[0].y) && checkCoord(lastCoord[0].x+1 ) && board[lastCoord[0].x+1][lastCoord[0].y] == emptySymb ) {
                    System.out.println(" 2");
                    board[lastCoord[0].x+1][lastCoord[0].y] = compSymb;
                    return;
                } else if(checkCoord(lastCoord[1].x) && checkCoord(lastCoord[1].y) && checkCoord(lastCoord[1].x-1) && board[lastCoord[1].x-1][lastCoord[1].y] == emptySymb ) {
                    System.out.println(" 3");
                    board[lastCoord[1].x-1][lastCoord[1].y] = compSymb;
                    return;
                }else if(checkCoord(lastCoord[1].x) && checkCoord(lastCoord[1].y) && checkCoord(lastCoord[1].x+1) && board[lastCoord[1].x+1][lastCoord[1].y] == emptySymb ) {
                    System.out.println(" 4");
                    board[lastCoord[1].x+1][lastCoord[1].y] = compSymb;
                    return;
                }
            }
            if(checkLeftDiagonals(playerSymb, i)) {
                System.out.println("left Diag"+i + " "+ lastCoord[0] + " "+ lastCoord[1] );

                if(lastCoord[0].x > 0 && lastCoord[0].y > 0 && board[lastCoord[0].x-1][lastCoord[0].y-1] == emptySymb ) {
                    board[lastCoord[0].x-1][lastCoord[0].y-1] = compSymb;
                    return;
                } else if(lastCoord[1].x < boardSize-1 && lastCoord[1].y < boardSize-1 && board[lastCoord[1].x+1][lastCoord[1].y+1] == emptySymb ) {
                    board[lastCoord[1].x+1][lastCoord[1].y+1] = compSymb;
                    return;
                }
            }
            if(checkRightDiagonals(playerSymb, i)) { //--i ++j
                System.out.println("Right diag"+i + " "+ lastCoord[0] + " "+ lastCoord[1] );

                if(lastCoord[0].x < boardSize - 1 && lastCoord[0].y > 0 && board[lastCoord[0].x+1][lastCoord[0].y-1] == emptySymb ) {
                    board[lastCoord[0].x+1][lastCoord[0].y-1] = compSymb;
                    return;
                } else if(lastCoord[1].x > 0 && lastCoord[1].y < boardSize-1 && board[lastCoord[1].x-1][lastCoord[1].y+1] == emptySymb ) {
                    board[lastCoord[1].x-1][lastCoord[1].y+1] = compSymb;
                    return;
                }
            }
        }
        for(int i = connectN ; i > 0; --i) {

            if(checkRows(compSymb, i)) {
                System.out.println(" rows "+i+" "+ lastCoord[0] + " "+ lastCoord[1]);
                if(checkCoord(lastCoord[0].x) && checkCoord(lastCoord[0].y) && checkCoord(lastCoord[0].y-1 ) && board[lastCoord[0].x][lastCoord[0].y-1] == emptySymb ) {
                    System.out.println(" 1");
                    board[lastCoord[0].x][lastCoord[0].y-1] = compSymb;
                    return;
                }else if(checkCoord(lastCoord[0].x) && checkCoord(lastCoord[0].y) && checkCoord(lastCoord[0].y+1 ) && board[lastCoord[0].x][lastCoord[0].y+1] == emptySymb ) {
                    System.out.println(" 2");
                    board[lastCoord[0].x][lastCoord[0].y+1] = compSymb;
                    return;
                } else if(checkCoord(lastCoord[1].x) && checkCoord(lastCoord[1].y) && checkCoord(lastCoord[1].y-1) && board[lastCoord[1].x][lastCoord[1].y-1] == emptySymb ) {
                    System.out.println(" 3");
                    board[lastCoord[1].x][lastCoord[1].y-1] = compSymb;
                    return;
                }else if(checkCoord(lastCoord[1].x) && checkCoord(lastCoord[1].y) && checkCoord(lastCoord[1].y+1) && board[lastCoord[1].x][lastCoord[1].y+1] == emptySymb ) {
                    System.out.println(" 4");
                    board[lastCoord[1].x][lastCoord[1].y+1] = compSymb;
                    return;
                }
            }

            if(checkColumns(compSymb, i)) {
                System.out.println(" columns "+i+" "+ lastCoord[0] + " "+ lastCoord[1] );
                if(checkCoord(lastCoord[0].x) && checkCoord(lastCoord[0].y) && checkCoord(lastCoord[0].x-1 ) && board[lastCoord[0].x-1][lastCoord[0].y] == emptySymb ) {
                    System.out.println(" 1");
                    board[lastCoord[0].x-1][lastCoord[0].y] = compSymb;
                    return;
                }else if(checkCoord(lastCoord[0].x) && checkCoord(lastCoord[0].y) && checkCoord(lastCoord[0].x+1 ) && board[lastCoord[0].x+1][lastCoord[0].y] == emptySymb ) {
                    System.out.println(" 2");
                    board[lastCoord[0].x+1][lastCoord[0].y] = compSymb;
                    return;
                } else if(checkCoord(lastCoord[1].x) && checkCoord(lastCoord[1].y) && checkCoord(lastCoord[1].x-1) && board[lastCoord[1].x-1][lastCoord[1].y] == emptySymb ) {
                    System.out.println(" 3");
                    board[lastCoord[1].x-1][lastCoord[1].y] = compSymb;
                    return;
                }else if(checkCoord(lastCoord[1].x) && checkCoord(lastCoord[1].y) && checkCoord(lastCoord[1].x+1) && board[lastCoord[1].x+1][lastCoord[1].y] == emptySymb ) {
                    System.out.println(" 4");
                    board[lastCoord[1].x+1][lastCoord[1].y] = compSymb;
                    return;
                }
            }
            if(checkLeftDiagonals(compSymb, i)) {
                System.out.println("left Diag"+i + " "+ lastCoord[0] + " "+ lastCoord[1] );

                if(lastCoord[0].x > 0 && lastCoord[0].y > 0 && board[lastCoord[0].x-1][lastCoord[0].y-1] == emptySymb ) {
                    board[lastCoord[0].x-1][lastCoord[0].y-1] = compSymb;
                    return;
                } else if(lastCoord[1].x < boardSize-1 && lastCoord[1].y < boardSize-1 && board[lastCoord[1].x+1][lastCoord[1].y+1] == emptySymb ) {
                    board[lastCoord[1].x+1][lastCoord[1].y+1] = compSymb;
                    return;
                }
            }
            if(checkRightDiagonals(compSymb, i)) { //--i ++j
                System.out.println("Right diag"+i + " "+ lastCoord[0] + " "+ lastCoord[1] );

                if(lastCoord[0].x < boardSize - 1 && lastCoord[0].y > 0 && board[lastCoord[0].x+1][lastCoord[0].y-1] == emptySymb ) {
                    board[lastCoord[0].x+1][lastCoord[0].y-1] = compSymb;
                    return;
                } else if(lastCoord[1].x > 0 && lastCoord[1].y < boardSize-1 && board[lastCoord[1].x-1][lastCoord[1].y+1] == emptySymb ) {
                    board[lastCoord[1].x-1][lastCoord[1].y+1] = compSymb;
                    return;
                }
            }
        }
        Random r = new Random();
        while(true) {
            int row = r.nextInt(boardSize);
            int col = r.nextInt(boardSize);

            if (board[row][col] == emptySymb) {
                board[row][col] = compSymb;
                return;
            }
        }
    }

    public char endOfGame() {
        if (checkRows(playerSymb,connectN) || checkColumns(playerSymb,connectN) ||
                checkLeftDiagonals(playerSymb,connectN) || checkRightDiagonals(playerSymb,connectN) )
            return playerSymb;
        if(checkRows(compSymb,connectN) || checkColumns(compSymb,connectN) ||
                checkLeftDiagonals(compSymb,connectN) || checkRightDiagonals(compSymb,connectN))
            return compSymb;

        for(int i = 0 ; i < board.length ; ++i) {
            for(int j = 0 ; j < board[0].length; ++j) {
                if(board[i][j] == emptySymb)
                    return emptySymb;
            }
        }

        return neotralSumb;
    }

    public boolean checkRows(char winnerSymb, int connectN) //tested .. OK
    {
//        Point[] lastCoord = new Point[2];
        int countWin=0;
        for(int i = 0; i < board.length; i++) {
            countWin = 0;
            for(int j = 0 ; j < board[0].length; j++) {
                if(winnerSymb == board[i][j]) {
                    ++countWin;
                } else {
                    countWin = 0;
                }
                if(countWin == connectN) {
                    lastCoord[1] = new Point(i,j);
                    lastCoord[0] = new Point(i, j-connectN + 1);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkRightDiagonals(char winnerSymb, int connectN) // tested...OK
    {
        int countWin = 0;

        int i = 0, j = 0;
        for (int k = connectN - 1; k < boardSize; ++k) //4...8
        {
            countWin = 0;
            for ( i = k, j = 0; i >= 0; --i, ++j)
            {
                if(winnerSymb == board[i][j]) {
                    ++countWin;
                } else {
                    countWin = 0;
                }
                if(countWin == connectN) {
                    lastCoord[1] = new Point(i,j);
                    lastCoord[0] = new Point(i+connectN -1, j-connectN +1);
                    return true;
                }
            }
        }

        countWin = 0;

        for (int k = 1; k < boardSize - (connectN - 1); ++k) //1...4
        {
            i = boardSize - 1;
            countWin = 0;

            for (j = k; j < boardSize; ++j, --i)
            {
                if(winnerSymb == board[i][j]) {
                    ++countWin;
                } else {
                    countWin = 0;
                }
                if(countWin == connectN) {
                    lastCoord[1] = new Point(i,j);
                    lastCoord[0] = new Point(i+connectN-1, j-connectN+1);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkColumns(char winnerSymb, int connectN) // tested .. OK
    {
        int countRowWin = 0;


        for(int j = 0 ; j < board[0].length; j++) {
            countRowWin = 0;

            for(int i = 0; i < board.length; i++) {


                if(winnerSymb == board[i][j]) {
                    ++countRowWin;
                } else {
                    countRowWin = 0;
                }
                if(countRowWin == connectN) {
                    lastCoord[1] = new Point(i,j);
                    lastCoord[0] = new Point(i - connectN + 1, j);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkLeftDiagonals(char winnerSymb, int connectN) //tested .. OK
    {
        int countWin = 0;

        int i = 0, j = 0;
        for (int k = boardSize - connectN ; k >= 0; --k) // rows 4...0
        {
            countWin = 0;
            for (i = k, j = 0; i < boardSize; ++i, ++j)
            {
                if(winnerSymb == board[i][j]) {
                    ++countWin;
                } else {
                    countWin = 0;
                }
                if(countWin == connectN) {
                    lastCoord[1] = new Point(i,j);
                    lastCoord[0] = new Point(i-connectN+1, j-connectN+1);
                    return true;
                }
            }
        }

        countWin = 0;

        for (int k = 1; k < boardSize - (connectN - 1); ++k) // columns 1...4
        {
            countWin = 0;
            for (i = 0, j = k; j < boardSize; ++j, ++i)
            {
                if(winnerSymb == board[i][j]) {
                    ++countWin;
                } else {
                    countWin = 0;
                }
                if(countWin == connectN) {
                    lastCoord[1] = new Point(i,j);
                    lastCoord[0] = new Point(i-connectN+1, j-connectN+1);
                    return true;
                }
            }
        }
        return false;
    }

    public void setMatrixPlayerElement(int row, int col) {
        if(board[row][col] == emptySymb)
            board[row][col] = playerSymb;
    }
    public char getMatrixElement(int row, int col) {
        return board[row][col];
    }




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < boardSize; ++i) {
            for(int j = 0 ; j < boardSize; ++j) {
                sb.append(board[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public char getEmptySymb() {
        return emptySymb;
    }

    public char getPlayerSymb() {
        return playerSymb;
    }

    public void setPlayerSymb(char playerSymb) {
        this.playerSymb = playerSymb;
    }

    public char getCompSymb() {
        return compSymb;
    }


    public void setCompSymb(char compSymb) {
        this.compSymb = compSymb;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getConnectN() {
        return connectN;
    }

    public void setConnectN(int connectN) {
        this.connectN = connectN;
    }

    public static char getNeotralSumb() {
        return neotralSumb;
    }

    public Point[] getLastCoord() {
        return  lastCoord;
    }


}
