/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.ui.views;

import com.game.models.Configuration;
import com.game.models.GameBean;
import com.game.models.Item;
import com.game.models.TileInformation;
import com.game.models.Weapon;
import com.game.util.GameUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import org.apache.commons.lang3.StringUtils;

/**
 *
 *  This class provide the functionality of weapon editor. Using this class, you can create/ Edit the weapons.
 */
public class WeaponEditorPanel extends JPanel implements ActionListener {

    private JComboBox comboBox = null;
    private JComboBox weaponModifier = null;
    public JComboBox getComboBox() {
        return comboBox;
    }

    public JLabel getValidationMess() {
        return validationMess;
    }

    public JCheckBox getChkBox() {
        return chkBox;
    }
    private JLabel validationMess = null;
    private int location = -1;
    private JCheckBox chkBox = null;
    
    public WeaponEditorPanel(int location, JCheckBox chkBox) {
        doGui();
        this.location = location;
        this.chkBox = chkBox;
    }
    
    /**
     *  This method used for initialize the UI part for weapon editing functionality. 
     */

    public void doGui() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel noteLbl = new JLabel("<html><div style='width : 500px;'>Pls select a value to choose an Weapon or you can create a new "
                + "Weapon entity below. Once selected a weapon, its' details will be available below</div></html>");
        noteLbl.setAlignmentX(0);
        add(noteLbl);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (Item item : GameBean.weaponDetails) {
            if (item instanceof Weapon) {
                model.addElement(((Weapon) item).getName());
            }
        }
        comboBox = new JComboBox(model);
        comboBox.setSelectedIndex(-1);
        comboBox.setMaximumSize(new Dimension(100, 30));
        comboBox.setAlignmentX(0);
        comboBox.setActionCommand("dropDown");
        comboBox.addActionListener(this);
        add(Box.createVerticalStrut(10));
        add(comboBox);
        add(Box.createVerticalStrut(10));
        JPanel panel1 = new JPanel();
        panel1.setAlignmentX(0);
        panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        JLabel weaponDtsLbl = new JLabel("Weapon Details : ");
        weaponDtsLbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
        panel1.add(weaponDtsLbl, c);
        c.gridwidth = 1;
        c.gridy = 1;
        JLabel nameLbl = new JLabel("Name : ");
        panel1.add(nameLbl, c);
        c.gridx = 1;
        JTextField name = new JTextField("");
        name.setColumns(20);
        panel1.add(name, c);
        c.gridx = 0;
        c.gridy = 2;
        JLabel weaponTypeLbl = new JLabel("Weapon Type : ");
        panel1.add(weaponTypeLbl, c);
        c.gridx = 1;
        JComboBox weaponType = new JComboBox(Configuration.weaponTypes);
        weaponType.setSelectedIndex(0);
        weaponType.setPreferredSize(name.getPreferredSize());
        System.out.println(name.getPreferredSize());
        panel1.add(weaponType, c);
        c.gridx = 0;
        c.gridy = 3;
        JLabel attackRangeLbl = new JLabel("Attack Range : ");
        panel1.add(attackRangeLbl, c);
        c.gridx = 1;
        JTextField attackRange = new JTextField("");
        attackRange.setColumns(20);
        panel1.add(attackRange, c);
        c.gridx = 0;
        c.gridy = 4;
        JLabel attackPtsLbl = new JLabel("Attack Points : ");
        panel1.add(attackPtsLbl, c);
        c.gridx = 1;
        JTextField attackPts = new JTextField("");
        attackPts.setColumns(20);
        panel1.add(attackPts, c);
        c.gridx = 0;
        c.gridy = 5;
        JLabel modifier = new JLabel("Modifier");
        panel1.add(modifier,c);
        c.gridx = 1;
        weaponModifier = new JComboBox(Configuration.weaponModifier);
        weaponModifier.setSelectedIndex(-1);
        weaponModifier.setAlignmentX(0);
        weaponModifier.setMaximumSize(new Dimension(100, 30));
        panel1.add(weaponModifier, c);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        JButton submit = new JButton("Save");
        submit.addActionListener(this);
        submit.setActionCommand("button");
        panel1.add(submit, c);
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        c.weighty = 0;
        c.weightx =1;
        validationMess = new JLabel("Pls enter all the fields or pls choose a weapon from the drop down");
        validationMess.setForeground(Color.red);
        validationMess.setVisible(false);
        panel1.add(validationMess, c);
//        c.fill = GridBagConstraints.BOTH;
//        c.gridy = 7;
//        c.weightx = 1;
//        c.weighty = 1;
//        panel1.add(new JLabel(""), c);
        panel1.setBorder(LineBorder.createGrayLineBorder());
        add(panel1);
        add(Box.createVerticalGlue());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equalsIgnoreCase("dropDown")) {
            JPanel panel = (JPanel) comboBox.getParent().getComponent(4);
            String name = comboBox.getSelectedItem().toString();
            for (Item item : GameBean.weaponDetails) {
                if (item instanceof Weapon) {
                    Weapon weapon = (Weapon) item;
                    if (weapon.getName().equalsIgnoreCase(name)) {
                        ((JTextField) panel.getComponent(2)).setText(name);
                        ((JComboBox) panel.getComponent(4)).setSelectedItem(weapon.getWeaponType());
                        ((JTextField) panel.getComponent(6)).setText(Integer.toString(weapon.getAttackRange()));
                        ((JTextField) panel.getComponent(8)).setText(Integer.toString(weapon.getAttackPts()));
                        String modifier = weapon.getModifierInForce();
                        if(modifier != null)
                        	weaponModifier.setSelectedItem(modifier);
                        else
                        	weaponModifier.setSelectedIndex(-1);
                        return;
                    }
                }
            }
        } 
        else{
            JButton btn = (JButton) ae.getSource();
            JPanel panel = (JPanel) btn.getParent();
            String name = ((JTextField) panel.getComponent(2)).getText();
            
            String weaponType = (String)(((JComboBox) panel.getComponent(4)).getSelectedItem());
            String attackRnge = ((JTextField) panel.getComponent(6)).getText();
            String attackPts = ((JTextField) panel.getComponent(8)).getText();
//            JLabel message = ((JLabel) this.getComponent(5));
            validationMess.setText("");
            validationMess.setVisible(false);
            if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(weaponType) && StringUtils.isNotBlank(attackRnge)
                    && StringUtils.isNotBlank(attackPts)) {
                validationMess.setVisible(false);
                Weapon weapon = new Weapon();
                weapon.setName(name);;
                weapon.setAttackRange(Integer.parseInt(attackRnge));
                weapon.setAttackPts(Integer.parseInt(attackPts));
                weapon.setWeaponType(weaponType);
                weapon.setModifierInForce(weaponModifier.getSelectedItem().toString());
                boolean weaponAlrdyPresent = false;
                int position = GameUtils.getPositionOfWeaponItem(name);
                if (GameBean.weaponDetails == null) {
                    GameBean.weaponDetails = new ArrayList<Item>();
                }
                if(position != -1){
                    GameBean.weaponDetails.remove(position);
                }
                GameBean.weaponDetails.add(weapon);
                try {
                    GameUtils.writeItemsToXML(GameBean.weaponDetails,Configuration.PATH_FOR_WEAPONS);
                    validationMess.setText("Saved Successfully..");
                    validationMess.setVisible(true);
                    if (!weaponAlrdyPresent) {
                        comboBox.removeActionListener(this);
                        comboBox.addItem(name);
                        comboBox.setSelectedItem(name);
                        comboBox.addActionListener(this);
                    }
                    TileInformation tileInfo = GameBean.mapInfo.getPathMap().get(location);
                    if(tileInfo == null){
                        tileInfo = new TileInformation();
                    }
                    tileInfo.setWeapon(weapon);
                    GameBean.mapInfo.getPathMap().put(location, tileInfo);
                    chkBox.setSelected(true);
                    this.revalidate();
                    return;
                } catch (Exception e) {
                    System.out.println("WeaponEditorPanel : actionPerformed() : Some error occured " + e);
                }

            } else {
                validationMess.setText("Pls enter all the fields or pls choose a weapon from the drop down");
                validationMess.setVisible(true);
                panel.revalidate();
            }
        }
    }
}
