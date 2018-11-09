package minesweeper;

import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class HeadingPanel extends JPanel implements ActionListener{
    JLabel headingLabel;
    JPanel bottomPanel;
    JButton resetBtn;
    private Board gameBoard;
    HeadingPanel(Board gameBoard){
        this.gameBoard=gameBoard;
        setLayout(new BorderLayout());   
        headingLabel = new JLabel("MineSweeper",SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial",Font.BOLD,30));
        
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,50,5));;
        bottomPanel.add(new JLabel("Bombs:  "+gameBoard.getNumOfBombs()));
        resetBtn = new JButton("Reset");
        resetBtn.addActionListener(this);
        bottomPanel.add(resetBtn);   
        
        add(headingLabel,BorderLayout.NORTH);
        add(bottomPanel,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource()==resetBtn){
            gameBoard.reset();
        }
    }
    
}
