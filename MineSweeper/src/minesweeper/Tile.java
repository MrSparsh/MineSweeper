
package minesweeper;

import java.awt.*;
import javax.swing.*;

public class Tile extends JButton{
    private Board board;
    private int value,row,col,pos;
    private boolean unfolded;
    private final Font font = new Font("Arial",Font.PLAIN,20);
    
    Tile(Board board,int row,int col,int value){
        this.board=board;
        this.row=row;
        this.col=col;
        this.pos=row*board.getColSize()+col;
        this.value=value;
        unfolded=false;
        setFont(font);
        addActionListener(board);
        setBackground(Color.GRAY);
    }
    public void reset(){
        value=0;
        unfolded=false;
        setBackground(Color.GRAY);
        setEnabled(true);
        setLabel("");
    }
    public void setValue(int value){
        this.value=value;
    }
    public int getValue(){
        return value;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public int getPos(){
        return pos;
    }
    public boolean isUnfolded(){
        return unfolded;
    }
    public void unfold(){
        unfolded=true;
        setEnabled(false);
        setTileBackgroundColor();
        if(value>=0){
            board.decreaseRemainingTiles();
            if(value>0){
                setLabel(""+value);
            }
        }else{
            setLabel("X");
        }
    }
    private void setTileBackgroundColor(){
        Color color;
        switch(value){
            case -1: color=Color.RED; break;
            case 0: color=Color.WHITE; break;
            case 1: color=Color.LIGHT_GRAY; break;
            case 2: color=Color.PINK; break;
            case 3: color=Color.ORANGE; break;
            case 4: color=Color.YELLOW; break;
            case 5: color=Color.MAGENTA; break;
            case 6: color=Color.CYAN; break;
            case 7: color=Color.LIGHT_GRAY; break;
            default: color=Color.DARK_GRAY;
        }
        setBackground(color);
    }
}
