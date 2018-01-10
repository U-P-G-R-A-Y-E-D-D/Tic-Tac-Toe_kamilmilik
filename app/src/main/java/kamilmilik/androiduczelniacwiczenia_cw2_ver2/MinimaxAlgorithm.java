package kamilmilik.androiduczelniacwiczenia_cw2_ver2;

import android.util.Log;

import java.util.List;

/**
 * Created by kamil on 10.01.2018.
 */

public class MinimaxAlgorithm {
    private static final String TAG = "MinimaxAlgorithm";
    private Board board;
    private WhoWin whoWin;

    private Point computerMove;
    public Point getComputerMove() {
        return computerMove;
    }
    public MinimaxAlgorithm(Board board,WhoWin whoWin){
        this.board = board;
        this.whoWin = whoWin;
    }
    public int minimax(int depth, int turn) {

        if (whoWin.hasXWon()) return +1;
        if (whoWin.hasOWon()) return -1;

        List<Point> pointsAvailable = board.getAvailableStates();
        if (pointsAvailable.isEmpty()) return 0;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < pointsAvailable.size(); ++i) {
            Point point = pointsAvailable.get(i);

            if (turn == Identifier.CROSS_INDETIFIER) { //X's turn select the highest from below minimax() call
                board.makeMoveToGivenPoint(point, turn);
                int currentScore = minimax(depth + 1, Identifier.CIRCLE_INDETIFIER);
                Log.i(TAG,"currentScore for X " + currentScore);
                max = Math.max(currentScore,max);
                if(currentScore >= 0){
                    if(depth == 0){
                        computerMove = point;
                    }
                }
                if (currentScore == 1) {
                    board.makeMoveToGivenPoint(point,Identifier.BLANK_IDENTIFIER);
                    break;
                }
                if (i == pointsAvailable.size() - 1 && max < 0) {
                    if (depth == 0) computerMove = point;
                }
            } else if (turn == Identifier.CIRCLE_INDETIFIER) {//O's turn select the lowest from below minimax() call
                board.makeMoveToGivenPoint(point, turn);
                int currentScore = minimax(depth + 1, Identifier.CROSS_INDETIFIER);
                Log.i(TAG,"currentScore for O " + currentScore);
                min = Math.min(currentScore,min);

                if(currentScore <= 0){
                    if(depth == 0){
                        computerMove = point;
                    }
                }
                if (min == -1) {
                    board.makeMoveToGivenPoint(point,Identifier.BLANK_IDENTIFIER);
                    break;
                }
                if (i == pointsAvailable.size() - 1 && min < 0) {
                    if (depth == 0) computerMove = point;
                }
            }
            board.makeMoveToGivenPoint(point,Identifier.BLANK_IDENTIFIER);//Reset this point
        }
        return turn == 1 ? max : min;
    }
}
