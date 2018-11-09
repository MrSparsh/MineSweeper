
package minesweeper;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Board extends JPanel implements ActionListener{
    private final int rowCount,colCount,numOfBombs;
    private int tilesRemaining;
    private final Tile[][] tile;
    int[] DX={-1,-1,-1,1,1,1,0,0};
    int[] DY={0,-1,1,0,-1,1,-1,1};
    
    Board(int rowSize,int colSize,int numOfBombs){
        this.rowCount=rowSize;
        this.colCount=colSize;
        this.numOfBombs=numOfBombs;
        tile = new Tile[rowSize][colSize];
        tilesRemaining=rowSize*colSize-numOfBombs;
        setLayout(new GridLayout(rowSize,colSize));
        for(int row=0;row<rowSize;row++){
            for(int col=0;col<colSize;col++){
                tile[row][col]=new Tile(this,row,col,0);
                add(tile[row][col]);
            }
        }
        placeBomb(numOfBombs);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Tile clickedTile = (Tile) event.getSource();
        int value=clickedTile.getValue();
        if(value==-1){
            for(int row=0;row<rowCount;row++){
                for(int col=0;col<colCount;col++){
                    if(tile[row][col].getValue()==-1){
                        tile[row][col].unfold();
                    }
                }
            }
            createOptionPane("Lost");
        }else if(value==0){
            clickedTile.unfold();
            fillRequiredTiles(clickedTile);
        }else{
            clickedTile.unfold();
        }
        System.out.println(""+tilesRemaining);
        if(tilesRemaining==0){
            createOptionPane("Won");
        }
    }
    
    public void placeBomb(int numOfBombs){
        Random random = new Random();
        while(numOfBombs>0){
            int randRow = random.nextInt(rowCount);
            int randCol = random.nextInt(colCount);
            Tile randTile = tile[randRow][randCol];
            if(randTile.getValue()!=-1){
                tile[randRow][randCol].setValue(-1);
                int currRow= randRow;
                int currCol= randCol;
                for(int i=0;i<8;i++){
                    int adjRow=currRow+DX[i];
                    int adjCol=currCol+DY[i];
                    if(adjRow>=0 && adjRow<rowCount && adjCol>=0 && adjCol<colCount){
                        Tile adjTile = tile[adjRow][adjCol];
                        if(adjTile.getValue()!=-1){
                            adjTile.setValue(adjTile.getValue()+1);
                        }
                    }
                }
                numOfBombs--;
            }
        }
    }

    private void fillRequiredTiles(Tile startTile) {
        LinkedList<Tile> queue = new LinkedList<Tile>();
        queue.add(startTile);
        while(!queue.isEmpty()){
            Tile currTile = queue.getFirst();
            queue.removeFirst();
            int currRow= currTile.getRow();
            int currCol= currTile.getCol();
            for(int i=0;i<8;i++){
                int adjRow=currRow+DX[i];
                int adjCol=currCol+DY[i];
                if(adjRow>=0 && adjRow<rowCount && adjCol>=0 && adjCol<colCount){
                    Tile adjTile = tile[adjRow][adjCol];
                    if(!adjTile.isUnfolded()){
                        adjTile.unfold();
                        if(adjTile.getValue()==0){
                            queue.add(adjTile);
                        }
                    }
                }
            }
        }
    }
    void createOptionPane(String winningStatus){
        String[] options = {"Yes","No"};
        int choice = JOptionPane.showOptionDialog(this,
        "You have "+winningStatus+" the game\n"
        + "Do you want to play again?",
        "MineSweeper",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[1]);
        System.out.println(""+choice);
        if(choice==0){
            reset();
        }else{
        }
    }
    public void reset(){
        for(int row=0;row<rowCount;row++){
            for(int col=0;col<colCount;col++){
                tile[row][col].reset();
            }
        }
        placeBomb(numOfBombs);
        tilesRemaining=rowCount*colCount-numOfBombs;
    }
    
    public int getRowSize(){
        return rowCount;
    }
    public int getColSize(){
        return colCount;
    }
    public int getNumOfBombs(){
        return numOfBombs;
    }
    public void decreaseRemainingTiles(){
        tilesRemaining--;
    }
}
