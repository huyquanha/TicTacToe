/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class GameBoard {
    private String[][] cells;
    
    public GameBoard() {
        cells = new String[3][3];
        for (int i=0; i<3;i++) {
            for (int j=0; j<3;j++) {
                cells[i][j]="";
            }
        }
    }
    
    public GameBoard(String[][] otherCells) {
        cells=new String[3][3];
        for(int i=0; i<3;i++) {
            for (int j=0; j<3;j++) {
                cells[i][j]=otherCells[i][j];
            }
        }
    }
    
    public String[][] getCells() {
        return cells;
    }
    
    public void addSymbol(int locX, int locY, String symbol) {
        if (cells[locX-1][locY-1].equals("")) {
            cells[locX-1][locY-1]=symbol;
        }
    }
    
    public void clear() {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                cells[i][j]="";
            }
        }
    }
    
    public ArrayList<Move> getAvailableMoves() {
        ArrayList<Move> availMoves = new ArrayList<>();
        for (int i=0; i<3;i++) {
            for (int j=0; j<3;j++) {
                if (cells[i][j].equals("")) {
                    availMoves.add(new Move(i+1,j+1));
                }
            }
        }
        return availMoves;
    }
    
    public boolean checkWinState() {
        //check 2 diagonals
        if (cells[0][0].equals(cells[1][1]) && cells[0][0].equals(cells[2][2]) && !cells[0][0].equals("")) {
            return true;
        } 
        else if (cells[2][0].equals(cells[1][1]) && cells[2][0].equals(cells[0][2]) && !cells[2][0].equals("")) {
            return true;
        } 
        else {
            for (int i = 0; i < 3; i++) {
                //check columns
                if (cells[i][0].equals(cells[i][1]) && cells[i][0].equals(cells[i][2]) && !cells[i][0].equals("")) {
                    return true;
                }
                //check rows
                else if (cells[0][i].equals(cells[1][i]) && cells[0][i].equals(cells[2][i]) && !cells[0][i].equals("")) {
                    return true;
                }
            }
            return false;
        }
    }
}
