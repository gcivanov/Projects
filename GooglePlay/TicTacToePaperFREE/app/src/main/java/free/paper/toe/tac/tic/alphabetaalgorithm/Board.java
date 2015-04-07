package free.paper.toe.tac.tic.alphabetaalgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import free.paper.toe.tac.tic.tictactoepaperfree.Constants;

//http://www.codebytes.in/2014/11/alpha-beta-pruning-minimax-algorithm.html

public class Board {

    private static final char EMPTY = Constants.E;
    private char COMP ;
	private char PLAYER ;

    private int player2Turns=0;

    List<Point> availablePoints;

    //Set this to some value if you want to have some specified depth limit for search
    // max deap == 7
    private int uptoDepth;

    private char[][] board = new char[3][3];

    List<PointsAndScores> rootsChildrenScore = new ArrayList<>();

    public Board(){
        this(-1,Constants.O, Constants.X );
    }

    public Board(int uptoDeapth, char playerSymbor, char compSymbol){
        resetBoard();
        COMP = compSymbol;
        PLAYER = playerSymbor;
        this.uptoDepth = uptoDeapth;
    }


    public int evaluateBoard() {
        int score = 0;

        //Check all rows
        for (int i = 0; i < 3; ++i) {
            int blank = 0;
            int X = 0;
            int O = 0;
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == EMPTY) {
                    blank++;
                } else if (board[i][j] == COMP) {
                    X++;
                } else {
                    O++;
                }

            }
            score+=changeInScore(X, O);
        }

        //Check all columns
        for (int j = 0; j < 3; ++j) {
            int blank = 0;
            int X = 0;
            int O = 0;
            for (int i = 0; i < 3; ++i) {
                if (board[i][j] == EMPTY) {
                    blank++;
                } else if (board[i][j] == COMP) {
                    X++;
                } else {
                    O++;
                }
            }
            score+=changeInScore(X, O);
        }

        int blank = 0;
        int X = 0;
        int O = 0;

        //Check diagonal (first)
        for (int i = 0, j = 0; i < 3; ++i, ++j) {
            if (board[i][j] == COMP) {
                X++;
            } else if (board[i][j] == PLAYER) {
                O++;
            } else {
                blank++;
            }
        }

        score+=changeInScore(X, O);

        blank = 0;
        X = 0;
        O = 0;

        //Check Diagonal (Second)
        for (int i = 2, j = 0; i > -1; --i, ++j) {
            if (board[i][j] == COMP) {
                X++;
            } else if (board[i][j] == PLAYER) {
                O++;
            } else {
                blank++;
            }
        }

        score+=changeInScore(X, O);

        return score;
    }

    private int changeInScore(int X, int O){
        int change;
        if (X == 3) {
            change = 100;
        } else if (X == 2 && O == 0) {
            change = 10;
        } else if (X == 1 && O == 0) {
            change = 1;
        } else if (O == 3) {
            change = -100;
        } else if (O == 2 && X == 0) {
            change = -10;
        } else if (O == 1 && X == 0) {
            change = -1;
        } else {
            change = 0;
        }
        return change;
    }

    public int alphaBetaMinimax(int alpha, int beta, char turn) {
    	return alphaBetaMinimax(alpha, beta, 0 , turn);
    }
    private int alphaBetaMinimax(int alpha, int beta, int depth, char turn){
        
        if(beta<=alpha){ System.out.println("Pruning at depth = "+depth);
        if(turn == COMP) return Integer.MAX_VALUE; else return Integer.MIN_VALUE; }
        
        if(depth == uptoDepth || isGameOver()) {
        	return evaluateBoard();
        }
        
        List<Point> pointsAvailable = getAvailableStates();
        
        if(pointsAvailable.isEmpty()) return 0;
        
        if(depth==0) rootsChildrenScore.clear(); 
        
        int maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE;
        
        for(int i=0;i<pointsAvailable.size(); ++i){
            Point point = pointsAvailable.get(i);
            
            int currentScore = 0;
            
            if(turn == COMP){
                placeAMove(point, COMP); 
                currentScore = alphaBetaMinimax(alpha, beta, depth+1, PLAYER);
                maxValue = Math.max(maxValue, currentScore); 
                
                //Set alpha
                alpha = Math.max(currentScore, alpha);
                
                if(depth == 0)
                    rootsChildrenScore.add(new PointsAndScores(currentScore, point));
            }else if(turn == PLAYER){
                placeAMove(point, PLAYER);
                currentScore = alphaBetaMinimax(alpha, beta, depth+1, COMP); 
                minValue = Math.min(minValue, currentScore);
                
                //Set beta
                beta = Math.min(currentScore, beta);
            }
            //reset board
            board[point.x][point.y] = EMPTY; 
            
            //If a pruning has been done, don't evaluate the rest of the sibling states
            if(currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE) break;
        }
        return turn == COMP ? maxValue : minValue;
    }  

    public boolean isGameOver() {
        //Game is over is someone has won, or board is full (draw)
        return (hasCompWon() || hasPlayerWon() || getAvailableStates().isEmpty());
    }
    public boolean hasNoWinner() {
        return getAvailableStates().isEmpty();
    }

    public boolean hasCompWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == COMP)
                || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == COMP)) {
            //System.out.println("X Diagonal Win");
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == COMP)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == COMP))) {
                // System.out.println("X Row or Column win");
                return true;
            }
        }
        return false;
    }

    public boolean hasPlayerWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == PLAYER)
                || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == PLAYER)) {
            // System.out.println("O Diagonal Win");
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == PLAYER)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == PLAYER)) {
                //  System.out.println("O Row or Column win");
                return true;
            }
        }
        return false;
    }
    public boolean hasWinner(char playerSymbol) {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == playerSymbol)
                || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == playerSymbol)) {

            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == playerSymbol)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == playerSymbol))) {
                return true;
            }
        }
        return false;
    }

    public List<Point> getAvailableStates() {
        availablePoints = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == EMPTY) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }

    public Point returnBestMove() {
        int MAX = -100000;
        int best = -1;

        for (int i = 0; i < rootsChildrenScore.size(); ++i) {
            if (MAX < rootsChildrenScore.get(i).score) {
                MAX = rootsChildrenScore.get(i).score;
                best = i;
            }
        }

        return rootsChildrenScore.get(best).point;
    }

    public void displayBoard() {
        System.out.println();

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();

        }
    }

    public void placeAMove(Point point, char player) {
        if(player == COMP) {
            ++player2Turns;
        }
        board[point.x][point.y] = player;   //player = 1 for X, 2 for O
    }

    public int getPlayer2Turns(){
        return player2Turns;
    }

    public void placeARandomMove( char player){
        Random rand = new Random();
        Point p = null;

        do {
            p = new Point(rand.nextInt(3), rand.nextInt(3));
        } while (!isEmptyElement(p));

        placeAMove(p, player);
    }


    public void placeAMove(int position, char player) {
        board[position/board.length][position%board[0].length] = player;   //player = 1 for X, 2 for O
    }
    public boolean isEmptyElement(Point point) {
        return (board[point.x][point.y] == EMPTY);
    }

    public char getElement(Point point){
        return board[point.x][point.y];
    }

    public boolean isEmptyElement(int position) {
        return (board[position/board.length][position%board[0].length] == EMPTY);
    }

    public char getElement(int position){
        return board[position/board.length][position%board[0].length];
    }
    

    public char[][] getBoard(){
        return board;
    }

    public void resetBoard() {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                board[i][j] = EMPTY;
            }
        }
        player2Turns = 0;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                s.append(board[i][j] + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}