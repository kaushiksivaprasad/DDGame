/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.ui.views;

import com.game.models.Armour;
import com.game.models.Configuration;
import static com.game.models.Configuration.*;
import com.game.models.GameBean;
import com.game.models.Item;
import com.game.models.Potion;
import com.game.models.Ring;
import com.game.models.TileInformation;
import com.game.models.Treasure;
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
 * This panel uses factory pattern. This panel behaves as a Ring, Armour,
 * Potion and Treasure depending on the parameters passed while initializing.
 * @author Kaushik
 */
public class ItemPanel extends JPanel implements ActionListener {

    private String type = null;
    boolean ringPanel = false;
    boolean armourPanel = false;
    boolean potionPanel = false;
    boolean treasurePanel = false;
    private JComboBox comboBox = null;
    private JLabel validationMess = null;
    private int location = -1;
    private JCheckBox chkBox = null;
    private JComboBox armourBox = null;
    private JComboBox ringModifier = null;
    private JComboBox armorModifier = null;
    /**
     * This is a constructor used to create the object of this class. Parameters are
     * passed to make it behave differently..
     * @param type
     * @param location
     * @param chkBox 
     */
    public ItemPanel(String type,int location, JCheckBox chkBox) {
        this.type = type;
        if ("Ring".equalsIgnoreCase(type)) {
            ringPanel = true;
        } else if ("Armour".equalsIgnoreCase(type)) {
            armourPanel = true;
        } else if ("Potion".equalsIgnoreCase(type)) {
            potionPanel = true;
        } else if ("Treasure".equalsIgnoreCase(type)) {
            treasurePanel = true;
        } else {
            ringPanel = true;
            type = "Ring";
        }
        this.location = location;
        this.chkBox = chkBox;
        doGui();
    }
    /**
     * This method lays the gui fields on the panel..
     */
    public void doGui() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel noteLbl = new JLabel("<html><div style='width : 500px;'>Pls select a value from the dropdown or you can create a new "
                + "entity below. Once selected an Item, its' details will be available below</div></html>");
        noteLbl.setAlignmentX(0);
        add(noteLbl);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        if (ringPanel) {
            for (Item item : GameBean.ringDetails) {
                model.addElement(((Ring) item).getName());
            }
        } else if (armourPanel) {
            for (Item item : GameBean.armourDetails) {
                model.addElement(((Armour) item).getName());
            }
        } else if (potionPanel) {
            for (Item item : GameBean.potionDetails) {
                model.addElement(((Potion) item).getName());
            }
        } else if (treasurePanel) {
            for (Item item : GameBean.treasureDetails) {
                model.addElement(((Treasure) item).getName());
            }
        }
        doCommonStuffForDropDown(model);
        doCommonStuffForContent();
    }
    /**
     * This method does the common stuffs needed for populating the drop down field.
     * @param model 
     */
    public void doCommonStuffForDropDown(DefaultComboBoxModel model) {
        comboBox = new JComboBox(model);
        comboBox.setSelectedIndex(-1);
        comboBox.setMaximumSize(new Dimension(100, 30));
        comboBox.setAlignmentX(0);
        comboBox.setActionCommand("dropDown");
        comboBox.addActionListener(this);
        add(Box.createVerticalStrut(10));
        add(comboBox);
        add(Box.createVerticalStrut(10));
    }
    /**
     * This method does the common gui laying stuff to put the common content 
     * on the panel.
     */
    public void doCommonStuffForContent() {
        JPanel panel1 = new JPanel();
        panel1.setAlignmentX(0);
        panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 1;
        c.weighty = 0.2;
        c.gridwidth = 2;
        JLabel dtlLbl = new JLabel(type + "Details : ");
        dtlLbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
        panel1.add(dtlLbl, c);
        c.gridwidth = 1;
        c.gridy = 1;
        JLabel nameLbl = new JLabel("Name : ");
        panel1.add(nameLbl, c);
        c.gridx = 1;
        JTextField name = new JTextField("");
        name.setColumns(20);
        panel1.add(name, c);
        if (ringPanel) {
            createComponentsForRing(panel1, c);
        } else if (armourPanel) {
            createComponentsForArmour(panel1, c);
        } else if (potionPanel) {
            createComponentsForPotion(panel1, c);
        } else if (treasurePanel) {
            createComponentsForTreasure(panel1, c);
        }
        c.gridx = 0;
        c.gridy = c.gridy + 1;
        c.gridwidth = 2;
        JButton submit = new JButton("Save");
        submit.addActionListener(this);
        submit.setActionCommand("button");
        panel1.add(submit, c);
        c.gridx = 0;
        c.gridy = c.gridy + 1;
        c.gridwidth = 2;
        c.weighty = 0;
        c.weightx = 1;
        validationMess = new JLabel("Pls enter all the fields or pls choose a " + type + " from the drop down");
        validationMess.setForeground(Color.red);
        validationMess.setVisible(false);
        panel1.add(validationMess, c);
        c.gridy++;
        c.weighty = 1;
        c.weightx = 1;
        panel1.add(new JPanel(), c);
        panel1.setBorder(LineBorder.createGrayLineBorder());
        add(panel1);
        add(Box.createVerticalGlue());
    }
    /**
     * This method creates fields required for this class to behave as a ring..
     * @param panel1
     * @param c 
     */
    public void createComponentsForRing(JPanel panel1, GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 2;
        JLabel incHealth = new JLabel("Health Increase Pts");
        panel1.add(incHealth, c);
        c.gridx = 1;
        JTextField incHealthTxt = new JTextField("");
        incHealthTxt.setColumns(20);
        panel1.add(incHealthTxt, c);
        c.gridx = 0;
        c.gridy = 3;
        JLabel incArmour = new JLabel("Armour Increase Pts");
        panel1.add(incArmour, c);
        c.gridx = 1;
        JTextField incArmourTxt = new JTextField("");
        incArmourTxt.setColumns(20);
        panel1.add(incArmourTxt, c);
        c.gridy = 4;
        c.gridx = 0;
        JLabel incAttack = new JLabel("Attack Increase Pts");
        panel1.add(incAttack, c);
        c.gridx = 1;
        JTextField incAttackTxt = new JTextField("");
        incAttackTxt.setColumns(20);
        panel1.add(incAttackTxt, c);
        c.gridx = 0;
        c.gridy = 5;
        JLabel ringModi = new JLabel("Modifier");
        panel1.add(ringModi, c);
        c.gridx = 1;
        ringModifier = new JComboBox(Configuration.ringModifier);
        ringModifier.setSelectedIndex(-1);
        ringModifier.setAlignmentX(0);
        ringModifier.setMaximumSize(new Dimension(100, 30));
        panel1.add(ringModifier, c);
    }
    /**
     * This method creates fields needed for the panel to behave as a potion editor.
     * @param panel1
     * @param c 
     */
    public void createComponentsForPotion(JPanel panel1, GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 2;
        JLabel potionPts = new JLabel("Potion Pts");
        panel1.add(potionPts, c);
        c.gridx = 1;
        JTextField potionPtsTxt = new JTextField("");
        potionPtsTxt.setColumns(20);
        panel1.add(potionPtsTxt, c);
    }

    public void createComponentsForArmour(JPanel panel1, GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 2;
        JLabel armourPts = new JLabel("Armour Pts");
        panel1.add(armourPts, c);
        c.gridx = 1;
        JTextField armourPtsTxt = new JTextField("");
        armourPtsTxt.setColumns(20);
        panel1.add(armourPtsTxt, c);      
        c.gridx = 0;
        c.gridy = 3;
        JLabel armourType = new JLabel("Armour Type");
        panel1.add(armourType, c);
        c.gridx = 1; 
        armourBox = new JComboBox(armourTypes);
        armourBox.setSelectedIndex(-1);
        armourBox.setAlignmentX(0);
        armourBox.setMaximumSize(new Dimension(100, 30));
        panel1.add(armourBox, c);
        c.gridx = 0;
        c.gridy = 4;
        JLabel armourModi = new JLabel("Modifier");
        panel1.add(armourModi, c);
        c.gridx = 1;
        armorModifier = new JComboBox(Configuration.armorModifier);
        armorModifier.setSelectedIndex(-1);
        armorModifier.setAlignmentX(0);
        armorModifier.setMaximumSize(new Dimension(100, 30));
        panel1.add(armorModifier, c);
        
    }
    /**
     * This method creates fields needed for the panel to behave as a treasure
     * editor
     * @param panel1
     * @param c 
     */
    public void createComponentsForTreasure(JPanel panel1, GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 2;
        JLabel treasureVal = new JLabel("Treasure Value");
        panel1.add(treasureVal, c);
        c.gridx = 1;
        JTextField treasureValTxt = new JTextField("");
        treasureValTxt.setColumns(20);
        panel1.add(treasureValTxt, c);
    }
    /**
     * This method gets the ring detail when we supply a ring name..
     * @param name
     * @param panel 
     */
    public void getRingDetailForName(String name, JPanel panel) {
        for (Item item : GameBean.ringDetails) {
            Ring ring = (Ring) item;
            if (ring.getName().equalsIgnoreCase(name)) {
                ((JTextField) panel.getComponent(2)).setText(name);
                ((JTextField) panel.getComponent(4)).setText("" + ring.getIncHealth());
                ((JTextField) panel.getComponent(6)).setText(Integer.toString(ring.getIncArmour()));
                ((JTextField) panel.getComponent(8)).setText(Integer.toString(ring.getIncAttack()));
                if(ring.getModifierInForce() != null)
                	ringModifier.setSelectedItem(ring.getModifierInForce().toString());
                else
                	ringModifier.setSelectedIndex(-1);
                return;
            }
        }
    }
    /**
     * This method gets the armour detais when we supply a armour name..
     *
     * @param name
     * @param panel
     */
    public void getArmourDetailForName(String name, JPanel panel) {
        for (Item item : GameBean.armourDetails) {
            Armour armour = (Armour) item;
            if (armour.getName().equalsIgnoreCase(name)) {
                ((JTextField) panel.getComponent(2)).setText(name);
                ((JTextField) panel.getComponent(4)).setText("" + armour.getArmourPts());
                if(armour.getModifierInForce() != null)
                	armorModifier.setSelectedItem(armour.getModifierInForce());
                else
                	armorModifier.setSelectedIndex(-1);
                String temp = armour.getArmourType();
                for(int i = 0; i < Configuration.armourTypes.length; i++){
                    if(temp.equalsIgnoreCase(Configuration.armourTypes[i])){
                        armourBox.setSelectedIndex(i);
                    }
                }
                return;
            }
        }
    }
    /**
     * This method gets the treasure detais when we supply a treasure name..
     *
     * @param name
     * @param panel
     */
    public void getTreasureDetailForName(String name, JPanel panel) {
        for (Item item : GameBean.treasureDetails) {
            Treasure treasure = (Treasure) item;
            if (treasure.getName().equalsIgnoreCase(name)) {
                ((JTextField) panel.getComponent(2)).setText(name);
                ((JTextField) panel.getComponent(4)).setText("" + treasure.getValue());
                return;
            }
        }
    }
    /**
     * This method gets the potion detais when we supply a potion name..
     *
     * @param name
     * @param panel
     */
    public void getPotionDetailForName(String name, JPanel panel) {
        for (Item item : GameBean.potionDetails) {
            Potion potion = (Potion) item;
            if (potion.getName().equalsIgnoreCase(name)) {
                ((JTextField) panel.getComponent(2)).setText(name);
                ((JTextField) panel.getComponent(4)).setText("" + potion.getPotionPts());
                return;
            }
        }
    }
    /**
     * This function persists the ring information into the xml file..
     * @param name
     * @param panel 
     */
    public void persistRingData(String name, JPanel panel) {
        String incHealth = ((JTextField) panel.getComponent(4)).getText();
        String incArmour = ((JTextField) panel.getComponent(6)).getText();
        String incAttack = ((JTextField) panel.getComponent(8)).getText();
        Object mod = ringModifier.getSelectedItem();
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(incHealth) && StringUtils.isNotBlank(incArmour) && StringUtils.isNotBlank(incAttack) && mod != null) {
            int position = GameUtils.getPositionOfRingItem(name);
            if (GameBean.ringDetails == null) {
                GameBean.ringDetails = new ArrayList<>();
            }
            if (position != -1) {
                GameBean.ringDetails.remove(position);
            }
            Ring ring = new Ring();
            ring.setName(name);
            ring.setIncArmour(Integer.parseInt(incArmour));
            ring.setIncHealth(Integer.parseInt(incHealth));
            ring.setIncAttack(Integer.parseInt(incAttack));
            ring.setModifierInForce(mod.toString());
            GameBean.ringDetails.add(ring);
            try {
                GameUtils.writeItemsToXML(GameBean.ringDetails, Configuration.PATH_FOR_RINGS);
                validationMess.setText("Saved Successfully..");
                validationMess.setVisible(true);
                if (position == -1) {
                    comboBox.removeActionListener(this);
                    comboBox.addItem(name);
                    comboBox.setSelectedItem(name);
                    comboBox.addActionListener(this);
                }
                TileInformation tileInfo = GameBean.mapInfo.getPathMap().get(location);
                if (tileInfo == null) {
                    tileInfo = new TileInformation();
                }
                tileInfo.setRing(ring);
                GameBean.mapInfo.getPathMap().put(location, tileInfo);
                chkBox.setSelected(true);
            } catch (Exception e) {
                validationMess.setText("Some error occured...");
                e.printStackTrace();
                validationMess.setVisible(true);
            }
        } else {
            validationMess.setText("Pls enter all the fields or pls choose a weapon from the drop down");
            validationMess.setVisible(true);
        }
    }
    /**
     * This function persists the armour information into the xml file..
     *
     * @param name
     * @param panel
     */
    public void persistArmourData(String name, JPanel panel) {
        String armourPts = ((JTextField) panel.getComponent(4)).getText();
        Object temp = armourBox.getSelectedItem();
        String armourType = null;
        if(temp != null)
            armourType = temp.toString();
        Object mod = armorModifier.getSelectedItem();
        if (StringUtils.isNotBlank(armourPts) && StringUtils.isNotBlank(name)&& StringUtils.isNotBlank(armourType) && mod != null) {
            int position = GameUtils.getPositionOfArmourItem(name);
            if (position != -1) {
                GameBean.armourDetails.remove(position);
            }
            if (GameBean.armourDetails == null) {
                GameBean.armourDetails = new ArrayList<>();
            }
            Armour armour = new Armour();
            armour.setName(name);
            armour.setArmourPts(Integer.parseInt(armourPts));
            armour.setArmourType(armourType);
            armour.setModifierInForce(mod.toString());
            GameBean.armourDetails.add(armour);
            try {
                GameUtils.writeItemsToXML(GameBean.armourDetails, Configuration.PATH_FOR_ARMOURS);
                validationMess.setText("Saved Successfully..");
                validationMess.setVisible(true);
                if (position == -1) {
                    comboBox.removeActionListener(this);
                    comboBox.addItem(name);
                    comboBox.setSelectedItem(name);
                    comboBox.addActionListener(this);
                }
                TileInformation tileInfo = GameBean.mapInfo.getPathMap().get(location);
                if (tileInfo == null) {
                    tileInfo = new TileInformation();
                }
                tileInfo.setArmour(armour);
                GameBean.mapInfo.getPathMap().put(location, tileInfo);
                chkBox.setSelected(true);
            } catch (Exception e) {
                validationMess.setText("Some error occured...");
                e.printStackTrace();
                validationMess.setVisible(true);
            }
        } else {
            validationMess.setText("Pls enter all the fields or pls choose a weapon from the drop down");
            validationMess.setVisible(true);
        }
    }
    /**
     * This function persists the potion information into the xml file..
     *
     * @param name
     * @param panel
     */
    public void persistPotionData(String name, JPanel panel) {
        String potionPts = ((JTextField) panel.getComponent(4)).getText();
        if (StringUtils.isNotBlank(potionPts) && StringUtils.isNotBlank(name)) {
            int position = GameUtils.getPositionOfPotionItem(name);
            if (position != -1) {
                GameBean.potionDetails.remove(position);
            }
            if (GameBean.potionDetails == null) {
                GameBean.potionDetails = new ArrayList<>();
            }
            Potion potion = new Potion();
            potion.setName(name);
            potion.setPotionPts(Integer.parseInt(potionPts));
            GameBean.potionDetails.add(potion);
            try {
                GameUtils.writeItemsToXML(GameBean.potionDetails, Configuration.PATH_FOR_POTIONS);
                validationMess.setText("Saved Successfully..");
                validationMess.setVisible(true);
                if (position == -1) {
                    comboBox.removeActionListener(this);
                    comboBox.addItem(name);
                    comboBox.setSelectedItem(name);
                    comboBox.addActionListener(this);
                }
                TileInformation tileInfo = GameBean.mapInfo.getPathMap().get(location);
                if (tileInfo == null) {
                    tileInfo = new TileInformation();
                }
                tileInfo.setPotion(potion);
                GameBean.mapInfo.getPathMap().put(location, tileInfo);
                chkBox.setSelected(true);
            } catch (Exception e) {
                validationMess.setText("Some error occured...");
                e.printStackTrace();
                validationMess.setVisible(true);
            }
        } else {
            validationMess.setText("Pls enter all the fields or pls choose a weapon from the drop down");
            validationMess.setVisible(true);
        }
    }
    /**
     * This function persists the treasure information into the xml file..
     *
     * @param name
     * @param panel
     */
    public void persistTreasure(String name, JPanel panel) {
        String value = ((JTextField) panel.getComponent(4)).getText();
        if (StringUtils.isNotBlank(value) && StringUtils.isNotBlank(name)) {
            int position = GameUtils.getPositionOfTreasureItem(name);
            if (position != -1) {
                GameBean.treasureDetails.remove(position);
            }
            if (GameBean.treasureDetails == null) {
                GameBean.treasureDetails = new ArrayList<>();
            }
            Treasure treasure = new Treasure();
            treasure.setName(name);
            treasure.setValue(Integer.parseInt(value));
            GameBean.treasureDetails.add(treasure);
            try {
                GameUtils.writeItemsToXML(GameBean.treasureDetails, Configuration.PATH_FOR_TREASURES);
                validationMess.setText("Saved Successfully..");
                validationMess.setVisible(true);
                if (position == -1) {
                    comboBox.removeActionListener(this);
                    comboBox.addItem(name);
                    comboBox.setSelectedItem(name);
                    comboBox.addActionListener(this);
                }
                TileInformation tileInfo = GameBean.mapInfo.getPathMap().get(location);
                if (tileInfo == null) {
                    tileInfo = new TileInformation();
                }
                tileInfo.setTreasure(treasure);
                GameBean.mapInfo.getPathMap().put(location, tileInfo);
                chkBox.setSelected(true);
            } catch (Exception e) {
                validationMess.setText("Some error occured...");
                e.printStackTrace();
                validationMess.setVisible(true);
            }
        } else {
            validationMess.setText("Pls enter all the fields or pls choose a weapon from the drop down");
            validationMess.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        validationMess.setText("");
        validationMess.setVisible(false);
        if (ae.getActionCommand().equalsIgnoreCase("dropDown")) {
            JPanel panel = (JPanel) comboBox.getParent().getComponent(4);
            String name = comboBox.getSelectedItem().toString();
            if (ringPanel) {
                getRingDetailForName(name, panel);
            } else if (armourPanel) {
                getArmourDetailForName(name, panel);
            } else if (potionPanel) {
                getPotionDetailForName(name, panel);
            } else if (treasurePanel) {
                getTreasureDetailForName(name, panel);
            }
        }
        else {
            JButton btn = (JButton) ae.getSource();
            JPanel panel = (JPanel) btn.getParent();
            String name = ((JTextField) panel.getComponent(2)).getText();
            if (ringPanel) {
                persistRingData(name, panel);
            } else if (armourPanel) {
                persistArmourData(name, panel);
            } else if (potionPanel) {
                persistPotionData(name, panel);
            } else if (treasurePanel) {
                persistTreasure(name, panel);
            }
        }
        this.revalidate();
    }
}