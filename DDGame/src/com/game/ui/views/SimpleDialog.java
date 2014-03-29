/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.ui.views;

import com.game.util.GameUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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

/**
 *
 *  Simple Dialog class is used for prompting user to ask the information like number of rows and number of columns 
 */
public class SimpleDialog extends JDialog implements ActionListener {

    private int rows;
    private int column;
    public SimpleDialog(JFrame frame) throws IOException {
        showDialogForMap(frame);
    }
    
    public void showDialogForMap(JFrame frame) throws IOException {
        setPreferredSize(new Dimension(300, 200));
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        setResizable(false);
        setLayout(new BorderLayout(5, 5));
        JPanel panel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        rightPanel.setName("rightPanel");
        JLabel message = new JLabel("All fields are mandatory");
        message.setName("message");
        message.setVisible(false);
        message.setForeground(Color.red);
        ImageIcon icon = null;
        try {
            icon = GameUtils.shrinkImage("warning.gif", 30, 30);
        } catch (IOException e) {
            System.out.println("Dialog : showDialogForMap(): Exception occured :" + e);
            throw new IOException(e);
        }
        JLabel label = new JLabel("Pls enter the no of rows and columns", icon, 10);
        label.setMaximumSize(new Dimension(250, 30));
        JLabel rowLbl = new JLabel("Rows : ");
        JLabel colLbl = new JLabel("Columns : ");
        rowLbl.setAlignmentX(CENTER_ALIGNMENT);
        rowLbl.setMaximumSize(new Dimension(100, 20));
        colLbl.setMaximumSize(new Dimension(100, 20));
        colLbl.setAlignmentX(CENTER_ALIGNMENT);
        JButton btn = new JButton("Submit");
        JTextField txtBox1 = new JTextField("");
        txtBox1.setName("rows");
        txtBox1.setPreferredSize(new Dimension(20, 20));
        txtBox1.setMaximumSize(new Dimension(60, 20));
        JTextField txtBox2 = new JTextField("");
        txtBox2.setName("columns");
        txtBox2.setMaximumSize(new Dimension(60, 20));
        btn.setMaximumSize(new Dimension(100, 30));
        btn.addActionListener(this);
        btn.setAlignmentX(0.5f);
        message.setAlignmentX(0.5f);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(btn);
        bottomPanel.add(Box.createVerticalStrut(10));
        bottomPanel.add(message);
        bottomPanel.add(Box.createVerticalGlue());
//        bottomPanel.add(message);
        panel.setLayout(new BorderLayout(5, 5));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        leftPanel.setPreferredSize(new Dimension(125, 50));
        leftPanel.add(rowLbl, JDialog.RIGHT_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(colLbl, JDialog.RIGHT_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(Box.createVerticalGlue());

        rightPanel.setPreferredSize(new Dimension(125, 50));
        rightPanel.add(txtBox1);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(txtBox2);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(Box.createVerticalGlue());

        panel.add(label, BorderLayout.NORTH);
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.EAST);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        getContentPane().add(panel, BorderLayout.EAST);
        add(panel);
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        SimpleDialog ex = new SimpleDialog(null);
        ex.getContentPane().add(new JLabel("Text.."));
        System.out.println("finioshed");
//        System.out.println(ex.getArraySize());
        ex.getContentPane().revalidate();
        ex.getContentPane().repaint();
        ex.getContentPane().validate();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Container contentPane = this.getContentPane();
        JPanel firstPanel = (JPanel)((JPanel)contentPane.getComponent(0)).getComponent(2);
        JButton button = (JButton)ae.getSource();
        JPanel parent = (JPanel)button.getParent();
        JLabel message = (JLabel)parent.getComponent(2);
        JTextField txt1 = (JTextField)firstPanel.getComponent(0);
        JTextField txt2 = (JTextField)firstPanel.getComponent(2);
        String val1 = txt1.getText();
        String val2 = txt2.getText();
        
        if(val1.length() == 0 || val2.length() == 0)
        {
            message.setVisible(true);
        }
        else
        {
            rows = Integer.parseInt(val1);
            column = Integer.parseInt(val2);
            message.setVisible(false);
            this.setVisible(false);
        }
        parent.revalidate();
    }

    /**
     *  method used for getting the row number
     * @return 
     */
    public int getRows() {
        return rows;
    }

    /**
     * This method used for setting row number
     * @param rows 
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    
    /**
     * This method used for getting column number
     * @param rows 
     */
    public int getColumn() {
        return column;
    }

    
    /**
     * This method used for setting column number
     * @param rows 
     */
    public void setColumn(int column) {
        this.column = column;
    }
}
