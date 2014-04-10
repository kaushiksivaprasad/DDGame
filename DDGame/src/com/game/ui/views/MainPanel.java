/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.ui.views;

import com.game.models.GameBean;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * This class is used to generate UI for MainPanel and implement the 
 * function that switching interface to character and map choose panel.
 * @author 韩信
 */
public class MainPanel extends JFrame implements ActionListener {
     public MainPanel(){
        initUI();
    }
     
    /**
     * initiate the UI for Main Panel
     */
    public void initUI() {
    	GameBean.doInit();
    	System.out.println("Test");
        
        JPanel functionPanel = new JPanel();
        functionPanel.setLayout(new BoxLayout(functionPanel, BoxLayout.Y_AXIS));
        JPanel buttonPanels[] = new JPanel[4];
        
        
        buttonPanels[0] = new JPanel();
        JButton startGame = new JButton("Start");
        startGame.setPreferredSize(new Dimension(120,30));
        startGame.setActionCommand("0");
        startGame.addActionListener(this);
        buttonPanels[0].add(startGame);
        
        buttonPanels[1] = new JPanel();
        JButton creatCharater = new JButton("Create Player");
        creatCharater.setActionCommand("1");
        creatCharater.addActionListener(this);
        creatCharater.setPreferredSize(new Dimension(120,30));
        buttonPanels[1].add(creatCharater);
        
        buttonPanels[2] = new JPanel();
        JButton mapBuilder = new JButton("Map Builder");
        mapBuilder.setPreferredSize(new Dimension(120,30));
        mapBuilder.setActionCommand("2");
        mapBuilder.addActionListener(this);
        buttonPanels[2].add(mapBuilder);
        
        buttonPanels[3] = new JPanel();
        JButton exitGame = new JButton("exit");
        exitGame.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
             System.exit(0);
            }
        });
        exitGame.setPreferredSize(new Dimension(120,30));
        buttonPanels[3].add(exitGame);
        
        functionPanel.add(buttonPanels[0]);
        functionPanel.add(buttonPanels[1]);
        functionPanel.add(buttonPanels[2]);
        functionPanel.add(buttonPanels[3]);
        functionPanel.setBorder(new EmptyBorder(new Insets(50, 30, 10, 10)));
        add(functionPanel);
        
        setTitle("SelectCharatersAndMap");
        setSize(500, 450);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /**
     * this method is used to switch interface
     * @param event 
     */
    public void actionPerformed(ActionEvent event){
        int command = Integer.parseInt(event.getActionCommand());
        if(command == 0){
            this.dispose();
            SwingUtilities.invokeLater(new Runnable() {          
	            public void run() {
                        try {
                           InitCharacterAndMapPanel icam = new InitCharacterAndMapPanel();
                           icam.setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }

	            }
	        });
        }else if (command == 1){
             try {
            if (GameBean.weaponDetails.size() > 0) {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.add(new CharacterEditor());
                frame.setName("Frame");
                frame.pack();
                frame.setVisible(true);
            }
        } catch (Exception ex) {
        }
        }else if (command == 2){
            try {
            } catch (Exception ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MapEditor();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        }
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	MainPanel ex = new MainPanel();
	                ex.setVisible(true);
	            }
	        });
	}
}
