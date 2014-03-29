/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.ui.views;

import com.game.util.GameUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 *   Class UserDialog is used for generating dialog to ask user for the map name
 */
public class UserDialog extends JDialog implements ActionListener{
    private String value;
    private JTextField txt;
    private JLabel validationMess = null;
    public UserDialog(String message,JFrame frame){
        setLayout(new BorderLayout(5,5));
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        ImageIcon icon = null;
        try {
            icon = GameUtils.shrinkImage("warning.gif", 30, 30);
        } catch (IOException e) {
            System.out.println("Dialog : showDialogForMap(): Exception occured :" + e);
            e.printStackTrace();
        }
        JPanel panel = new JPanel();
        JLabel label = new JLabel(icon);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        label.setText(message);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        label.setHorizontalAlignment(0);
        panel.add(label);
        add(panel,BorderLayout.NORTH);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        txt = new JTextField();
        txt.setPreferredSize(new Dimension(150,30));
        txt.setAlignmentX(.5f);
        txt.setMaximumSize(new Dimension(150,30));
        contentPanel.add(txt);
        contentPanel.add(Box.createVerticalStrut(10));
        JButton btn = new JButton("Submit.");
        btn.setAlignmentX(.5f);
        btn.setPreferredSize(new Dimension(50,25));
        btn.addActionListener(this);
        validationMess = new JLabel("All fields are mandatory");
        validationMess.setVisible(false);
        validationMess.setForeground(Color.red);
        validationMess.setAlignmentX(.5f);
        contentPanel.add(btn);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(validationMess);
        contentPanel.add(Box.createVerticalGlue());
        add(contentPanel,BorderLayout.CENTER);
        pack();
        setSize(new Dimension(300,200));
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String txt1 = txt.getText();
        if(StringUtils.isNotBlank(txt1)){
            int index = txt1.indexOf(".");
            if(index != -1){
                txt1 = txt1.substring(0, index);
            }
            value = txt1.trim();
            System.out.println(value);
            this.dispose();
        }
        else{
            validationMess.setVisible(true);
            this.revalidate();
        }
    }
    public static void main(String[] args) {
        new UserDialog("Pls Enter the File Name.", null);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
