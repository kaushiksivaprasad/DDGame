/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.ui.views;

import com.game.models.Configuration;
import com.game.models.GameBean;
import com.game.models.MapInformation;
import com.game.models.TileInformation;
import com.game.util.GameUtils;
import com.game.xml.models.MapInformationWrapper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import org.apache.commons.lang3.StringUtils;

/**
 * This class creates a frame with which we will be able to create a map.
 * @author Kaushik
 */
public class MapEditor extends JFrame implements ActionListener {

    private SimpleDialog dialog = null;
    private JPanel bottomPanel = null;

    public SimpleDialog getDialog() {
        return dialog;
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    public MapEditor() throws IOException {
        generateGUI();
    }
    /**
     * This class will generate gui required for creating the map..
     * @throws IOException 
     */
    public void generateGUI() throws IOException {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setResizable(false);
        JMenuBar menubar = new JMenuBar();
        ImageIcon icon = null;
        try {
            icon = GameUtils.shrinkImage("save.png", 20, 20);
        } catch (IOException e) {
            System.out.println("Dialog : showDialogForMap(): Exception occured :" + e);
            e.printStackTrace();
        }
        JMenu file = new JMenu("File");
        JMenuItem save = new JMenuItem("Save", icon);
        save.setToolTipText("Save Map Information");
        save.setActionCommand("Save Map");
        save.addActionListener(this);
        file.add(save);
        menubar.add(file);
        setJMenuBar(menubar);
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topPanel.setLayout(new GridBagLayout());
        JLabel headerLbl = new JLabel("Legend : ");
        headerLbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
        JLabel lbl1 = new JLabel();
        lbl1.setPreferredSize(new Dimension(50, 20));
        lbl1.setBackground(Configuration.pathColor);
        lbl1.setOpaque(true);
        JLabel lbl2 = new JLabel("- Represents the path.");
        JLabel lbl3 = new JLabel();
        lbl3.setPreferredSize(new Dimension(50, 20));
        lbl3.setBackground(Configuration.enemyColor);
        lbl3.setOpaque(true);
        JLabel lbl4 = new JLabel("- Represents the path with monsters");
        JLabel lbl5 = new JLabel();
        lbl5.setPreferredSize(new Dimension(50, 20));
        lbl5.setBackground(Configuration.startPointColor);
        lbl5.setOpaque(true);
        JLabel lbl6 = new JLabel("- Represents the starting point in the path");
        JLabel lbl7 = new JLabel();
        lbl7.setBackground(Configuration.endPointColor);
        lbl7.setOpaque(true);
        lbl7.setPreferredSize(new Dimension(50, 20));
        JLabel lbl8 = new JLabel("- Ending point in the path");
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.weightx = 1;
        c.weighty = 0;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridwidth = 2;
        topPanel.add(headerLbl, c);
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.gridwidth = 1;
        c.ipadx = 5;
        c.ipady = 5;
        topPanel.add(lbl1, c);
        c.gridx = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        topPanel.add(lbl2, c);
        c.gridx = 0;
        c.gridy = 2;
        topPanel.add(lbl3, c);
        c.gridx = 1;
        topPanel.add(lbl4, c);
        c.gridx = 0;
        c.gridy = 3;
        topPanel.add(lbl5, c);
        c.gridx = 1;
        topPanel.add(lbl6, c);
        c.gridx = 0;
        c.gridy = 4;
        topPanel.add(lbl7, c);
        c.gridx = 1;
        topPanel.add(lbl8, c);
        add(topPanel, BorderLayout.NORTH);
        bottomPanel = new JPanel();
        add(bottomPanel, BorderLayout.CENTER);
        bottomPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//        bottomPanel.add(new JButton("kaushik"));
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        setMaximizedBounds(env.getMaximumWindowBounds());
        setVisible(true);
        callDialogForUsersInput();
    }
    /**
     * This method calls the dialog to prompt the user to enter the rows and columns 
     * required for the generation of map..
     * @throws IOException 
     */
    public void callDialogForUsersInput() throws IOException {
        dialog = new SimpleDialog(this);
        int rows = dialog.getRows();
        int columns = dialog.getColumn();
        JButton buttons[] = new JButton[rows * columns];
        if (rows != 0 && columns != 0) {
            bottomPanel.setLayout(new GridLayout(rows, columns, 5, 5));
            for (int i = 1, k = 0; i <= buttons.length; i++, k++) {
                buttons[k] = new JButton();
                buttons[k].addActionListener(this);
                buttons[k].setActionCommand(new Integer(i).toString());
                bottomPanel.add(buttons[k]);
            }
            bottomPanel.revalidate();
            bottomPanel.getParent().revalidate();
        }
        GameBean.mapInfo = new MapInformation();
        GameBean.mapInfo.setColumns(columns);
        GameBean.mapInfo.setRows(rows);
    }

    public static void main(String[] args) throws Exception {
        GameBean.doInit();
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equalsIgnoreCase("Save Map")) {
            UserDialog dialog = new UserDialog("Pls Enter the Map Name.", this);
            String mapName = dialog.getValue();
            if (StringUtils.isNotBlank(mapName)) {
                GameBean.mapInfo.setMapName(mapName);
                File file = new File(Configuration.PATH_FOR_MAP);
                MapInformationWrapper wrapper = null;
                if (file.exists()) {
                    try {
                        wrapper = GameUtils.readMapInformation(Configuration.PATH_FOR_MAP);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    wrapper = new MapInformationWrapper();
                }
                if (wrapper != null) {
                    try {
                        wrapper.getMapList().add(GameBean.mapInfo);
                        GameUtils.writeMapInformation(wrapper, Configuration.PATH_FOR_MAP);
                        JOptionPane.showMessageDialog(this, "Saved Successfully..");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } else {
            JButton src = (JButton) ae.getSource();
            String location = src.getActionCommand();
            GameBean.mapInfo.getPathMap().put(Integer.parseInt(location), null);
            GameBean.mapInfo.getStartPointInfo().remove(new Integer(location));
            new ComplexDialog(location);
            TileInformation info = GameBean.mapInfo.getPathMap().get(Integer.parseInt(location));
            if (info != null) {
                if (info.isStartTile()) {
                    src.setBackground(Configuration.startPointColor);
                } else if (info.isEndTile()) {
                    src.setBackground(Configuration.endPointColor);
                } else if (info.getEnemy() != null) {
                    src.setBackground(Configuration.enemyColor);
                } else {
                    src.setBackground(Configuration.pathColor);
                }
            }
            else{
                Color color = UIManager.getColor ( "Button.background" );
                src.setBackground(color);
            }
        }
    }
}
