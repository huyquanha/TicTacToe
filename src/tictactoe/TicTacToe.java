/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;
import java.util.*;

/**
 *
 * @author ASUS
 */
public class TicTacToe {
    private GameBoard board;
    private String userSymbol, machineSymbol;
    private String lastSymbol;
    private int depth;
    private Move machineMove;
    
    public TicTacToe() {
        board = new GameBoard();
        userSymbol="X";
        machineSymbol="O";
        depth=0;
    }
    
    public String getUserSymbol() {
        return userSymbol;
    }
    
    public String getMachineSymbol() {
        return machineSymbol;
    }
    
    public String getLastSymbol() {
        return lastSymbol;
    }
    
    public GameBoard getBoard() {
        return board;
    }
    
    public void addSymbol(int locX, int locY, String symbol) {
        board.addSymbol(locX, locY, symbol);
    }
    
    public void changeSymbol(String newUserSymbol)
    {
        userSymbol=newUserSymbol;
        if (newUserSymbol.equals("X")) {
            machineSymbol="O";
        }
        else {
            machineSymbol="X";
        }
    }
    
    public void reset() {
        board.clear();
        userSymbol="X";
        machineSymbol="O";
        depth=0;
    }
    
    public boolean makeUserMove(int locX,int locY) {
        if (board.checkWinState() || board.getAvailableMoves().size()==0) {
            return false;
        }
        else {
            board.addSymbol(locX,locY,userSymbol);
            lastSymbol=userSymbol;
            depth++;
            return true;
        }
    }
    
    public Move makeMachineMove() {
        if (board.checkWinState() || board.getAvailableMoves().isEmpty()) {
            return null;
        }
        else {
            int score = minimax(board,depth,machineSymbol,userSymbol);
            board.addSymbol(machineMove.getX(),machineMove.getY(),machineSymbol);
            lastSymbol=machineSymbol;
            depth++;
            return machineMove;
        }
    }
    
    private int minimax(GameBoard gameboard,int gameDepth, String activeTurnSymbol, String otherSymbol) {
        if (gameboard.checkWinState() || gameboard.getAvailableMoves().isEmpty()) {
            return getScore(gameDepth,otherSymbol); //because otherSymbol is the symbol who have won, not activeTurnSymbol.
        }
        else {
            gameDepth++;
            ArrayList<Move> availMoves = gameboard.getAvailableMoves();
            ArrayList<Integer> scores = new ArrayList<>();
            for (Move move : availMoves) {
                GameBoard possibleBoard = new GameBoard(gameboard.getCells());
                possibleBoard.addSymbol(move.getX(),move.getY(),activeTurnSymbol);
                scores.add(minimax(possibleBoard,gameDepth,otherSymbol,activeTurnSymbol));
            }
            if (activeTurnSymbol.equals(machineSymbol)) { //machine (who does the minimax algorithm) will try to get the highest score possible
                int maxIndex=findMaxIndex(scores);
                machineMove= availMoves.get(maxIndex);
                return scores.get(maxIndex);
            }
            else {
                int minIndex=findMinIndex(scores); //player as the opponent will be assumed to try to get the lowest score possible
                machineMove=availMoves.get(minIndex);
                return scores.get(minIndex);
            }
        }
    }
    
    private int getScore(int gameDepth, String symbol) {
        if (symbol.equals(machineSymbol)) { //machine Wins. Get positive score
            return 10-gameDepth;
        }
        else { //player wins.Get negative score
            return gameDepth-10;
        }
    }
    
    private int findMaxIndex(ArrayList<Integer> scores) {
        int maxIndex=0;
        boolean finished=false;
        while (!finished) {
            boolean foundNewMax=false;
            for (int i=maxIndex+1; i<scores.size();i++) {
               if (scores.get(i)>scores.get(maxIndex)) {
                   maxIndex=i;
                   foundNewMax=true;
                   break;
               }
            }
            if (foundNewMax==false) {
                finished=true;
            }
        }
        return maxIndex;
    }
    
    private int findMinIndex(ArrayList<Integer> scores) {
        int minIndex=0;
        boolean finished=false;
        while (!finished) {
            boolean foundNewMin=false;
            for (int i=minIndex+1; i<scores.size();i++) {
               if (scores.get(i)<scores.get(minIndex)) {
                   minIndex=i;
                   foundNewMin=true;
                   break;
               }
            }
            if (foundNewMin==false) {
                finished=true;
            }
        }
        return minIndex;
    }
}
