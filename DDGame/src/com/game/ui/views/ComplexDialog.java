/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.ui.views;

import com.game.models.GameBean;
import com.game.models.MapInformation;
import com.game.models.TileInformation;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *this panel creates the UI to initialize the information about weapon, character, potion etc
 * diffrent panel(characterEditor armourEditor) will be called according to the requiement of the user
 * @author Kaushik
 */
public class ComplexDialog extends JDialog implements ActionListener,WindowListener{
    private JPanel rightWrapper = null;
    private int location = -1;
    private JCheckBox checkBox[] = null;
    public ComplexDialog(String location)
    {
        this.location = Integer.parseInt(location);
        TileInformation information = new TileInformation();
        doGui();
    }
    /**
     * this function will create the "index" panel for user, the choise will be show on the rigt side (startpoints, endpoints, armour, character,potion, etc)
     * when finish creating some parts and save them, the check box will be "clicked".
     * the left side of the paneld is using card layout, diffrent panel will show according to the choise of user.
     */
    public void doGui()
    {
        setResizable(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        addWindowListener(this);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        JPanel wrapper = new JPanel(new GridBagLayout());
        JPanel leftWrapper = new JPanel();
        rightWrapper = new JPanel();
        rightWrapper.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        leftWrapper.setLayout(new BoxLayout(leftWrapper, BoxLayout.Y_AXIS));
        String text[] = {"Enemy","Weapon","Armour","Ring","Potion","Treasure","Start Point","Stop Point"};
        checkBox = new JCheckBox[text.length];
        JButton btn[] = new JButton[text.length];
        JPanel panel[] = new JPanel[text.length];
        for(int i = 0; i < checkBox.length; i++)
        {
            panel[i] = new JPanel();
            panel[i].setLayout(new BoxLayout(panel[i],BoxLayout.X_AXIS));
            checkBox[i] = new JCheckBox(text[i], false);
            checkBox[i].setEnabled(false);
            checkBox[i].setPreferredSize(new Dimension(100,30));
            checkBox[i].setMinimumSize(new Dimension(100,30));
            checkBox[i].setMaximumSize(new Dimension(100,30));
            if(i < 6){
                btn[i] = new JButton("Edit "+text[i]+" >>");
            }
            else{
                btn[i] = new JButton(text[i]);
            }
            btn[i].setActionCommand(text[i]);
            btn[i].setPreferredSize(new Dimension(200,30));
            btn[i].setMinimumSize(new Dimension(200,30));
            btn[i].setMaximumSize(new Dimension(200,30));
            
            btn[i].addActionListener(this);
            panel[i].add(checkBox[i]);
            panel[i].add(Box.createHorizontalStrut(5));
            panel[i].add(btn[i]);
            panel[i].add(Box.createHorizontalGlue());
            leftWrapper.add(panel[i]);
            leftWrapper.add(Box.createVerticalStrut(30));
        }
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        JButton clearAll = new JButton("Clear All");
        clearAll.setActionCommand("Clear");
        clearAll.setPreferredSize(new Dimension(90, 30));
        clearAll.addActionListener(this);
        JButton saveButton = new JButton("Save");
        saveButton.setActionCommand("Save");
        saveButton.setPreferredSize(new Dimension(80, 30));
        saveButton.addActionListener(this);
        panel1.add(clearAll);
        panel1.add(Box.createHorizontalStrut(5));
        panel1.add(saveButton);
        panel1.add(Box.createHorizontalGlue());
        leftWrapper.add(panel1);
        leftWrapper.setPreferredSize(new Dimension(400,1024));
        leftWrapper.add(Box.createVerticalStrut(30));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(5, 5, 5, 5);
//        c.weighty = 1;
        wrapper.add(leftWrapper,c);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        rightWrapper.setBorder(LineBorder.createBlackLineBorder());
        rightWrapper.setLayout(new CardLayout());
        JPanel dummyPanel = new JPanel();
        dummyPanel.add(new JLabel(" "));
//        CharachterEditorPanel charPanel = new CharachterEditorPanel(location,checkBox[0]);
        CharacterEditor charPanel = new CharacterEditor(location,checkBox[0]);
        rightWrapper.add(dummyPanel,"Dummy");
        rightWrapper.add(charPanel,"Enemy");
        WeaponEditorPanel weaponPanel = new WeaponEditorPanel(location,checkBox[1]);
        rightWrapper.add(weaponPanel,"Weapon");
        rightWrapper.add(new ItemPanel("Ring",location,checkBox[3]),"Ring");
        rightWrapper.add(new ItemPanel("Armour",location,checkBox[2]),"Armour");
        rightWrapper.add(new ItemPanel("Potion",location,checkBox[4]),"Potion");
        rightWrapper.add(new ItemPanel("Treasure",location,checkBox[5]),"Treasure");
        rightWrapper.setBackground(Color.GRAY);
//        rightWrapper.add(new JButton(" sdfds"));
        wrapper.add(rightWrapper,c);
//        JScrollPane scrollPane = new JScrollPane(wrapper);
//        scrollPane.setSize(env.getMaximumWindowBounds().getSize());
        getContentPane().add(wrapper);
        pack();
        setSize(env.getMaximumWindowBounds().getSize());
        setVisible(true);
     }
    public static void main(String[] args) throws Exception {
//        GameBean.treasureDetails = GameUtils.getAllItems(Configuration.PATH_FOR_TREASURES);
//        GameBean.ringDetails = GameUtils.getAllItems(Configuration.PATH_FOR_RINGS);
//        GameBean.potionDetails = GameUtils.getAllItems(Configuration.PATH_FOR_POTIONS);
//        GameBean.weaponDetails = GameUtils.getAllItems(Configuration.PATH_FOR_WEAPONS);
        GameBean.mapInfo = new MapInformation();
        GameBean.doInit();
        ComplexDialog dialog = new ComplexDialog(""+1);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        CardLayout c = (CardLayout)rightWrapper.getLayout();
        if(ae.getActionCommand().equalsIgnoreCase("Clear")){
            for(JCheckBox chkBox : checkBox){
                chkBox.setSelected(false);
            }
            GameBean.mapInfo.getPathMap().remove(location);
            GameBean.mapInfo.getStartPointInfo().remove(new Integer(location));
        }
        else if(ae.getActionCommand().equalsIgnoreCase("Start Point")){
            TileInformation info = GameBean.mapInfo.getPathMap().get(location);
            if(info == null){
                info = new TileInformation();
            }
            info.setStartTile(true);
            GameBean.mapInfo.getPathMap().put(location, info);
            GameBean.mapInfo.getStartPointInfo().add(location);
            checkBox[6].setSelected(true);
        }
        else if(ae.getActionCommand().equalsIgnoreCase("Stop Point")){
            TileInformation info = GameBean.mapInfo.getPathMap().get(location);
            if(info == null){
                info = new TileInformation();
            }
            info.setEndTile(true);
            GameBean.mapInfo.getPathMap().put(location, info);
            checkBox[7].setSelected(true);
        }
        else if(ae.getActionCommand().equalsIgnoreCase("Save")){
            TileInformation info = GameBean.mapInfo.getPathMap().get(location);
            if(info == null){
                info = new TileInformation();
            }
            info.setLocation(location);
            GameBean.mapInfo.getPathMap().put(location, info);
            this.dispose();
            return;
        }
        else{
            c.show(rightWrapper, ae.getActionCommand());
        }
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        GameBean.mapInfo.getPathMap().remove(location);
        GameBean.mapInfo.getStartPointInfo().remove(new Integer(location));
        this.dispose();
    }

    @Override
    public void windowClosed(WindowEvent we) {
//        System.out.println("hi..");
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }
}
