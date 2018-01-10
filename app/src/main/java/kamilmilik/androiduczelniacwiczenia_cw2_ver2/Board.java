package kamilmilik.androiduczelniacwiczenia_cw2_ver2;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamil on 09.01.2018.
 */

public class Board {
    private static final String TAG = "Board";
    public static final int NUM_OF_COLS = 3;
    public static final int NUM_OF_ROWS = 3;
    private final Activity activity;


    private WhoWin whoWin;
    private List<Point> availablePoints;

    private int[][] board = new int[NUM_OF_COLS][NUM_OF_ROWS];
    private Button[][] boardButtons = new Button[NUM_OF_COLS][NUM_OF_ROWS];
    public WhoWin getWhoWin() {
        return whoWin;
    }

    public Board(Activity activity){
        this.activity = activity;
        this.whoWin = new WhoWin(this);
    }
    public int[][] getBoard() {
        return board;
    }
    public Button[][] getBoardButtons() {
        return boardButtons;
    }
    public void makeMoveToGivenPoint(Point point, int turn){
        board[point.x][point.y] = turn;
    }
    public void setUpBoardButtons(){
        boardButtons[0][0] =  activity.findViewById(R.id.button1);
        boardButtons[0][1] =  activity.findViewById(R.id.button2);
        boardButtons[0][2] =  activity.findViewById(R.id.button3);
        boardButtons[1][0] =  activity.findViewById(R.id.button4);
        boardButtons[1][1] =  activity.findViewById(R.id.button5);
        boardButtons[1][2] =  activity.findViewById(R.id.button6);
        boardButtons[2][0] =  activity.findViewById(R.id.button7);
        boardButtons[2][1] =  activity.findViewById(R.id.button8);
        boardButtons[2][2] =  activity.findViewById(R.id.button9);
    }
    public void initializeGame(){
        for(int i=0; i < NUM_OF_COLS; i++){
            for(int j=0; j < NUM_OF_ROWS; j++){
                board[i][j] = Identifier.BLANK_IDENTIFIER;
                boardButtons[i][j].setBackground(activity.getResources().getDrawable(R.drawable.bialy));
                boardButtons[i][j].setEnabled(true);
            }
        }
    }
    public List<Point> getAvailableStates() {
        availablePoints = new ArrayList<>();
        for (int i = 0; i < NUM_OF_COLS; ++i) {
            for (int j = 0; j < NUM_OF_ROWS; ++j) {
                if (board[i][j] == Identifier.BLANK_IDENTIFIER) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }
}
