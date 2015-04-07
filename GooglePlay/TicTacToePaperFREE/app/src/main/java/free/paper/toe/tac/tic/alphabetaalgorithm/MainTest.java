package free.paper.toe.tac.tic.alphabetaalgorithm;

import java.util.Random;
import java.util.Scanner;


public class MainTest {
 
    public static void main(String[] args) {
        Board b = new Board();
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);


        b.displayBoard();

        int n = 3;

        System.out.println("Who's gonna move first? (1)Computer (2)User: ");
        int choice = scan.nextInt();

        //random 1st comp:
        if (choice == 1) {
            Point p = new Point(rand.nextInt(n), rand.nextInt(n));
            b.placeAMove(p, 'o'); //'x'
            b.displayBoard();
        }

        //play
        while (!b.isGameOver()) {
            System.out.println("Your move: row col");
            Point userMove = new Point(scan.nextInt(), scan.nextInt());

            b.placeAMove(userMove, 'x'); //2 for O and O is the user
            b.displayBoard();

            if (b.isGameOver()) break;

            //comp play:
            b.alphaBetaMinimax(Integer.MIN_VALUE, Integer.MAX_VALUE, 'o'); // 1 is comp

            //I dont know .. score :
            for (PointsAndScores pas : b.rootsChildrenScore)
                System.out.println("Point: " + pas.point + " Score: " + pas.score);

            //get best moove for comp add place it...
            b.placeAMove(b.returnBestMove(), 'o');

            b.displayBoard();
        }
        if (b.hasCompWon()) {
            System.out.println("Unfortunately, you lost!");
        } else if (b.hasPlayerWon()) {
            System.out.println("You win!");
        } else {
            System.out.println("It's a draw!");
        }
        scan.close();
    }
}
