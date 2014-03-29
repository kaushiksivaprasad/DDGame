/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.ui.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import com.game.models.Armour;
import com.game.models.CharacterBuilder;
import com.game.models.Configuration;
import com.game.models.GameBean;
import com.game.models.GameCharacter;
import com.game.models.Inventory;
import com.game.models.Item;
import com.game.models.TileInformation;
import com.game.models.Weapon;
import com.game.util.GameUtils;

/**
 *  This class extends from JPanel and it contains all the fields that are required to 
 *  edit/create a new player or enemy.
 * @author Kaushik
 */
public class CharacterEditor extends JPanel implements ActionListener {

    private int location = -1;
    private JCheckBox chkBox = null;
    private boolean isEnemy = false;
    private JComboBox comboBox = null;
    private String lblContent = null;
    private JTextField name = null;
    private JTextField imgPath = null;
    private JTextField hitPoints = null;
    private JTextField level = null;
    private JComboBox meleeWeapon = null;
    private JComboBox rangedWeapon = null;
    private JComboBox armour = null;
    private JComboBox shield = null;
    private JTextArea ta = null;
    private JLabel validationMess = null;
    private boolean generated = false;
    private CharacterBuilder builder = null;
    private GameCharacter character = null;
    private HashMap<String, Weapon> weaponMap = new HashMap<String, Weapon>();
    private HashMap<String, Armour> armorMap = new HashMap<String, Armour>();
    private DefaultComboBoxModel model = new DefaultComboBoxModel();
    private LinkedHashMap<String, Integer> charPosition = new LinkedHashMap<String, Integer>();
    private HashMap<String, GameCharacter> charMap = new HashMap<String, GameCharacter>();
    /**
     * This constructor denotes that now this class is going to behave as a EnemyEditor. This takes two
     * parameters to integrate with the complex dialog class.
     * @param location
     * @param checkBox 
     */
    public CharacterEditor(int location, JCheckBox checkBox) {
        isEnemy = true;
        this.location = location;
        this.chkBox = checkBox;
        lblContent = "<html><b>Pls select a value to choose an enemy or you can create a new "
                + "Enemy entity below. Once selected an Enemy character, its' details will be available below</b></html>";
        int i = 0;
        for (GameCharacter character : GameBean.enemyDetails) {
            model.addElement(character.getName());
            charMap.put(character.getName(), character);
            charPosition.put(character.getName(), i++);
        }
        doGui();
    }
    /**
     * This constructor denotes that now this class is going to behave as a
     * PlayerEditor.
     *
     */
    public CharacterEditor() {
        lblContent = "<html><b>Pls select a value to choose a player or you can create a new "
                + "Player entity below. Once selected a Player character, its' details will be available below</b></html>";
        int i = 0;
        for (GameCharacter character : GameBean.playerDetails) {
            model.addElement(character.getName());
            charMap.put(character.getName(), character);
            charPosition.put(character.getName(), i++);
        }
        doGui();
    }
    /**
     * This method draws up the gui on the panel.
     */
    public void doGui() {
        JPanel outerPane = new JPanel();
        outerPane.setLayout(new GridBagLayout());
//        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel noteLbl = new JLabel(lblContent);
        noteLbl.setAlignmentX(0);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridwidth = 4;
        c.gridy = 0;
        c.gridx = 0;
        outerPane.add(noteLbl, c);
        System.out.println(c.gridy);
        c.gridy++;
        System.out.println(c.gridy);
        c.gridx = 0;
        c.gridwidth = 1;
        comboBox = new JComboBox(model);
        comboBox.setSelectedIndex(-1);
        comboBox.setMaximumSize(new Dimension(100, 30));
        comboBox.setAlignmentX(0);
        comboBox.setActionCommand("dropDown");
        comboBox.addActionListener(this);
        outerPane.add(comboBox, c);
        c.gridy++;
        JLabel nameLbl = new JLabel("Name : ");
        outerPane.add(nameLbl, c);
        c.gridx++;
        name = new JTextField();
        name.setColumns(20);
        outerPane.add(name, c);
        c.gridx = 0;
        c.gridy++;
        JLabel imageLbl = new JLabel("Image Path : ");
        outerPane.add(imageLbl, c);
        c.gridx++;
        imgPath = new JTextField();
        imgPath.setColumns(20);
        outerPane.add(imgPath, c);
        c.gridx = 0;
        c.gridy++;
        JLabel hitPtsLbl = new JLabel("Hit Points : ");
        outerPane.add(hitPtsLbl, c);
        c.gridx++;
        hitPoints = new JTextField();
        hitPoints.setColumns(20);
        outerPane.add(hitPoints, c);
        c.gridx = 0;
        c.gridy++;
        JLabel lvl = new JLabel("Level : ");
        outerPane.add(lvl, c);
        c.gridx++;
        level = new JTextField();
        level.setColumns(20);
        if(!isEnemy){
            level.setText("1");
            level.setEnabled(false);
        }
        outerPane.add(level, c);
        c.gridx = 0;
        c.gridy++;
        JLabel mlWpn = new JLabel("Melee Weapon : ");
        outerPane.add(mlWpn, c);
        c.gridx++;
        model = new DefaultComboBoxModel();
        LinkedList<String> meleeWpnList = new LinkedList<String>();
        LinkedList<String> rngdWpnList = new LinkedList<String>();
        for (Item item : GameBean.weaponDetails) {
            Weapon wpn = (Weapon) item;
            if (wpn.getWeaponType().equalsIgnoreCase(Configuration.weaponTypes[0])) {
                meleeWpnList.add(wpn.getName());
            } else {
                rngdWpnList.add(wpn.getName());
            }
            weaponMap.put(wpn.getName(), wpn);
        }
        meleeWeapon = new JComboBox(new DefaultComboBoxModel(meleeWpnList.toArray()));
        meleeWeapon.setSelectedIndex(-1);
        meleeWeapon.setMaximumSize(new Dimension(100, 30));
        outerPane.add(meleeWeapon, c);
        c.gridx++;
        JLabel rngdWpn = new JLabel("Ranged Weapon : ");
        outerPane.add(rngdWpn, c);
        c.gridx++;
        rangedWeapon = new JComboBox(new DefaultComboBoxModel(rngdWpnList.toArray()));
        rangedWeapon.setSelectedIndex(-1);
        rangedWeapon.setMaximumSize(new Dimension(100, 30));
        outerPane.add(rangedWeapon, c);
        c.gridy++;
        c.gridx = 0;
        JLabel armourLbl = new JLabel("Armour : ");
        outerPane.add(armourLbl, c);
        c.gridx++;
        LinkedList<String> armourList = new LinkedList<String>();
        LinkedList<String> shildList = new LinkedList<String>();
        for (Item item : GameBean.armourDetails) {
            Armour temp = (Armour) item;
            if(temp.getArmourType() != null){
                if(temp.getArmourType().equalsIgnoreCase(Configuration.armourTypes[1])){
                    armourList.add(temp.getName());
                }
                else{
                    shildList.add(temp.getName());
                }
            }
            else{
                armourList.add(temp.getName());
//            else if(temp.getArmourType().equalsIgnoreCase(Configuration.armourTypes[2]))
                shildList.add(temp.getName());
            }
            armorMap.put(temp.getName(), temp);
        }
        armour = new JComboBox(new DefaultComboBoxModel(armourList.toArray()));
        armour.setSelectedIndex(-1);
        armour.setMaximumSize(new Dimension(100, 30));
        outerPane.add(armour, c);
        c.gridx++;
        JLabel shieldLbl = new JLabel("Shield : ");
        outerPane.add(shieldLbl, c);
        c.gridx++;
        shield = new JComboBox(new DefaultComboBoxModel(shildList.toArray()));
        shield.setSelectedIndex(-1);
        shield.setMaximumSize(new Dimension(100, 30));
        outerPane.add(shield, c);
        ta = new JTextArea(10, 50);
        ta.setRows(40);
        ta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JScrollPane scrollPane = new JScrollPane(ta);
        scrollPane.setPreferredSize(new Dimension(600, 250));
        c.gridx = 0;
        c.gridy++;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 4;
        c.gridheight = 4;
        c.weightx = .5;
        c.weighty = 1;
        outerPane.add(scrollPane, c);
        c.gridy += 4;
        c.gridx = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = 1;
        JButton generate = new JButton("Generate");
        JButton submit = new JButton("Submit");
        outerPane.add(generate, c);
        submit.addActionListener(this);
        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ta.setText("");
                HashSet<Integer> set = new HashSet<Integer>();
                int strength = getUniqueVal(set);
                int constitution = getUniqueVal(set);
                int dext = getUniqueVal(set);
                int intel = getUniqueVal(set);
                int charisma = getUniqueVal(set);
                int wisdom = getUniqueVal(set);
                if (character == null) {
                    builder = new CharacterBuilder(isEnemy);
                } else {
                    builder = new CharacterBuilder(character);
                }
                builder.setStrength(strength);
                builder.setConstitution(constitution);
                builder.setDexterity(dext);
                builder.setIntelligence(intel);
                builder.setCharisma(charisma);
                builder.setWisdom(wisdom);
                int strModifier = GameUtils.calculateAbilityModifier(strength);
                int conModifier = GameUtils.calculateAbilityModifier(constitution);
                int dexModifier = GameUtils.calculateAbilityModifier(dext);
                builder.setStrengthModifier(strModifier);
                builder.setConstitutionModifier(conModifier);
                builder.setDexterityModifier(dexModifier);
                String type = null;
                if (constitution < strength && constitution > dext) {
                    type = "Bully";
                    builder.setType("Bully");
                } else if (dext > constitution && constitution > strength) {
                    builder.setType("Nimble");
                    type = "Nimble";
                } else {
                    builder.setType("Tank");
                    type = "Tank";
                }
                ta.append("Strength : " + strength);
                ta.append("\nDexterity : " + dexModifier);
                ta.append("\nConstitution : " + constitution);
                ta.append("\nIntelligence : " + intel);
                ta.append("\nCharisma: " + charisma);
                ta.append("\nWisdom : " + wisdom);
                ta.append("\nType :" + type);
                ta.append("\nStrength Modifier :" + strModifier);
                ta.append("\nConstitution Modifier :" + conModifier);
                ta.append("\nDexterity Modifier :" + dexModifier);
                generated = true;
                if (character != null) {
                    character = builder.build();
                }
            }
        });
        c.gridx++;
        outerPane.add(submit, c);
        validationMess = new JLabel();
        validationMess.setForeground(Color.red);
//        validationMess.setVisible(false);
        c.gridy++;
        c.gridx = 0;
        c.gridwidth = 4;
        outerPane.add(validationMess, c);
        JScrollPane outerScrollPane = new JScrollPane(outerPane);
        setLayout(new BorderLayout());
        add(outerScrollPane, BorderLayout.CENTER);
    }
    /**
     * This method integrates with GameUtils.cal4d6Roll() to generate all unique values for different 
     * set of modifiers
     * @param set
     * @return 
     */
    public int getUniqueVal(HashSet<Integer> set) {
        int temp = GameUtils.cal4d6Roll();
        while (set.contains(temp)) {
            temp = GameUtils.cal4d6Roll();
        }
        set.add(temp);
        return temp;
    }
//
//    public static void main(String[] args) throws Exception {
//        GameBean.doInit();
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.add(new CharacterEditor(0, null));
//        frame.pack();
////        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
////        frame.setMaximizedBounds(env.getMaximumWindowBounds());
//        frame.setVisible(true);
//    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equalsIgnoreCase("dropDown")) {
            JComboBox cbox = (JComboBox) ae.getSource();
            String nameTxt = cbox.getSelectedItem().toString();
            character = charMap.get(nameTxt);
            name.setText(nameTxt);
            imgPath.setText(character.getImagePath());
            hitPoints.setText("" + character.getHealth());
            level.setText("" + character.getLevel());
            Inventory inv = character.getInventory();
            if (inv != null) {
                Weapon dummy = inv.getEquippedWeapon();
                if (dummy != null) {
                    meleeWeapon.setSelectedItem(dummy.getName());
                }
                for (Item item : inv.getItems()) {
                    if (item instanceof Weapon) {
                        Weapon wpn = (Weapon) item;
                        if (wpn.getWeaponType().equalsIgnoreCase(Configuration.weaponTypes[1])) {
                            {
                                rangedWeapon.setSelectedItem(wpn.getName());
                                break;
                            }
                        }
                    }
                }
                Armour chest = inv.getChest();
                Armour shld = inv.getShield();
                if (chest != null) {
                    armour.setSelectedItem(chest.getName());
                }
                if (shld != null) {
                    shield.setSelectedItem(shld.getName());
                }
            }
            ta.setText("");
            ta.append("Strength : " + character.getStrength());
            ta.append("\nDexterity : " + character.getDexterity());
            ta.append("\nConstitution : " + character.getConstitution());
            ta.append("\nIntelligence : " + character.getIntelligence());
            ta.append("\nCharisma: " + character.getCharisma());
            ta.append("\nWisdom : " + character.getWisdom());
            ta.append("\nType :" + character.getType());
            ta.append("\nStrength Modifier :" + character.getStrengthModifier());
            ta.append("\nConstitution Modifier :" + character.getConstitutionModifier());
            ta.append("\nDexterity Modifier :" + character.getDexterityModifier());
            validationMess.setText("");
//            validationMess.setvi
            builder = new CharacterBuilder(character);
            generated = true;
            this.revalidate();
        } else {
            String nameTxt = name.getText();
            String img = imgPath.getText();
            String lvl = level.getText();
            String melWpn = meleeWeapon.getSelectedItem().toString();
            String health = hitPoints.getText();
            String rngdWpn = rangedWeapon.getSelectedItem().toString();
            String armr = armour.getSelectedItem().toString();
            String shld = shield.getSelectedItem().toString();
            if (StringUtils.isNotBlank(nameTxt) && StringUtils.isNotBlank(img) && StringUtils.isNotBlank(lvl)
                    && StringUtils.isNotBlank(melWpn) && StringUtils.isNotBlank(rngdWpn) && StringUtils.isNotBlank(armr)
                    && StringUtils.isNotBlank(shld) && StringUtils.isNotBlank(health)) {
                if (generated) {
                    if (isEnemy) {
                        persistEnemyWrapper(nameTxt, img, lvl, melWpn, rngdWpn, armr, shld, health);
                    } else {
                        persistPlayerWrapper(nameTxt, img, lvl, melWpn, rngdWpn, armr, shld, health);
                    }
                } else {
                    validationMess.setText("Pls hit generate button before you submit..");
                }
            } else {
                validationMess.setText("All the fields are mandatory..");
            }
            validationMess.setVisible(true);
            this.revalidate();
        }
    }
    /**
     * This method serves as a wrapper to persist the player entity to the XML file.
     * @param nameTxt
     * @param img
     * @param lvl
     * @param melWpn
     * @param rngdWpn
     * @param armr
     * @param shld
     * @param health 
     */
    public void persistPlayerWrapper(String nameTxt, String img, String lvl, String melWpn, String rngdWpn, String armr, String shld, String health) {
        Integer i = null;
        doCharacterBuild(nameTxt, img, lvl, melWpn, rngdWpn, armr, shld, health);
        character = builder.build();
        i = charPosition.get(nameTxt);
        if (i != null) {
            GameBean.playerDetails.remove(i.intValue());
            GameBean.playerDetails.add(i, character);
        } else {
            GameBean.playerDetails.add(character);
        }
        charMap.put(nameTxt, character);
        try {
            persistPlayer();
            if (i == null) {
                charPosition.put(nameTxt, charPosition.size());
                comboBox.removeActionListener(this);
                comboBox.addItem(nameTxt);
                comboBox.addActionListener(this);
            }
            clearAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * This method serves as a wrapper to persist the enemy entity to the XML file.
     *
     * @param nameTxt
     * @param img
     * @param lvl
     * @param melWpn
     * @param rngdWpn
     * @param armr
     * @param shld
     * @param health
     */
    public void persistEnemyWrapper(String nameTxt, String img, String lvl, String melWpn, String rngdWpn, String armr, String shld, String health) {
        Integer i = null;
        doCharacterBuild(nameTxt, img, lvl, melWpn, rngdWpn, armr, shld, health);
        character = builder.build();
        i = charPosition.get(nameTxt);
        if (i != null) {
            GameBean.enemyDetails.remove(i.intValue());
            GameBean.enemyDetails.add(i, character);
        } else {
            GameBean.enemyDetails.add(character);
        }
        charMap.put(nameTxt, character);
        try {
            persistEnemy();
            if (i == null) {
                charPosition.put(nameTxt, charPosition.size());
                comboBox.removeActionListener(this);
                comboBox.addItem(nameTxt);
                comboBox.addActionListener(this);
            }
            clearAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * This method uses the character builder to build the character object.
     * @param nameTxt
     * @param img
     * @param lvl
     * @param melWpn
     * @param rngdWpn
     * @param armr
     * @param shld
     * @param health 
     */
    public void doCharacterBuild(String nameTxt, String img, String lvl, String melWpn, String rngdWpn, String armr, String shld, String health) {
        builder.setName(nameTxt).setImagePath(img).setLevel(Integer.parseInt(lvl)).setHealth(Integer.parseInt(health));
        Inventory inventory = new Inventory();
        inventory.setChest(armorMap.get(armr));
        inventory.setShield(armorMap.get(shld));
        inventory.setEquippedWeapon(weaponMap.get(melWpn));
        LinkedList<Item> list = new LinkedList<Item>();
        list.add(weaponMap.get(rngdWpn));
        inventory.setItems(list);
        builder.setInventory(inventory);
    }
    /**
     * This method empties all the fields that are present on this panel.
     */
    public void clearAll() {
        ta.setText("");
        character = null;
        generated = false;
        name.setText("");
        imgPath.setText("");
        hitPoints.setText("");
        level.setText("");
        meleeWeapon.setSelectedIndex(-1);
        rangedWeapon.setSelectedIndex(-1);
        armour.setSelectedIndex(-1);
        shield.setSelectedIndex(-1);
    }
    /**
     * This method persists the enemy to the xml file.
     * @throws Exception 
     */
    public void persistEnemy() throws Exception {
        try {
            GameUtils.writeCharactersToXML(GameBean.enemyDetails, Configuration.PATH_FOR_ENEMY_CHARACTERS);
            validationMess.setText("Saved Successfully..");
            if(chkBox != null && location != -1){
                TileInformation tileInfo = GameBean.mapInfo.getPathMap().get(location);
                if (tileInfo == null) {
                    tileInfo = new TileInformation();
                }
                tileInfo.setEnemy(character);
                GameBean.mapInfo.getPathMap().put(location, tileInfo);
                chkBox.setSelected(true);
            }
        } catch (Exception e) {
            validationMess.setText("Some error occurred..");
            throw e;
        }
    }
    /**
     * This method persists the player to the xml file.
     *
     * @throws Exception
     */
    public void persistPlayer() throws Exception {
        try {
            GameUtils.writeCharactersToXML(GameBean.playerDetails, Configuration.PATH_FOR_USER_CHARACTERS);
            validationMess.setText("Saved Successfully..");
        } catch (Exception e) {
            validationMess.setText("Some error occurred..");
            throw e;
        }
    }
    public static void main(String[] args) {
		
	}
}
