/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.ui.views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 *   Class Turn panel used for creating information panel which show the information like current player, level, hit point
 */
public class TurnPanel extends JPanel implements ActionListener
{
    public JLabel playerLabelLabel = new JLabel();
    public JLabel hitPointsLabel = new JLabel();
    public JLabel levelLabel = new JLabel();
    public int currentPlayer;
    int hitPoints;
    int level;
    JButton[] inventory ;

    public TurnPanel(int numberofPlayers)
    {
        this.currentPlayer = currentPlayer;
        playerLabelLabel.setText("CurrentPlayer " + currentPlayer);
        hitPointsLabel.setText("HitPoints " + hitPoints);
        levelLabel.setText("Level " + level);
         inventory = new JButton[numberofPlayers];
        setLayout(new GridLayout(numberofPlayers + 3,1,10,10));
        add(playerLabelLabel);
        add(hitPointsLabel);
        add(levelLabel);
        for(int i = 0; i < numberofPlayers; i++)
        {
            inventory[i] = new JButton();
            inventory[i].setText(MapPanel.users.get(i).getName());
            inventory[i].setActionCommand("Inventory " + i);
           // inventory[i].addActionListener(this);
            add(inventory[i]);            
        }
       UIManager.installLookAndFeel("SeaGlass", "com.seaglasslookandfeel.SeaGlassLookAndFeel");
    }
    /**
     *  Setting the current player
     * @param currentPlayer 
     */
    public void setCurrentPlayer(int currentPlayer)
    {
        playerLabelLabel.setText("CurrentPlayer " + currentPlayer);
    }

    /**
     * Setting hit points
     * @param hitPoints 
     */
    public void setHitPoints(int hitPoints)
    {
        hitPointsLabel.setText("HitPoints " + hitPoints);
    }
    
    /**
     * Setting level information
     * @param level 
     */
    public void setLevel(int level)
    {
        levelLabel.setText("Level " + level);
    }
        @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
}