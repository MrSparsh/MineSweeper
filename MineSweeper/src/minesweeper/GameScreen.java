
package minesweeper;

import java.awt.BorderLayout;
import java.awt.*;
import javax.swing.*;

public class GameScreen extends JFrame{
    private final Board gameBoard;
    private int boardRowCnt=9,boardColCnt=9,numOfBombs=10;
    GameScreen(){
        gameBoard = new Board(boardRowCnt,boardColCnt,numOfBombs);
        add(new HeadingPanel(gameBoard),BorderLayout.NORTH);
        add(gameBoard,BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setTitle("MineSweeper");
        setVisible(true);
    }
}
