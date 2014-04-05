/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.ui.views;

import com.game.models.Armour;
import com.game.models.Configuration;
import com.game.models.GameCharacter;
import com.game.models.Inventory;
import com.game.models.Item;
import com.game.models.Player;
import com.game.models.Potion;
import com.game.models.Ring;
import com.game.models.Weapon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *this class is for creating inventory panel in which player can equip and unequip items and check the packsack
 * @author Hanxin
 */
public class InventoryPanel extends JDialog implements ActionListener{
    private int inventoryRow ;
    private int inventoryColumn;
    private int inventorySize;
    //public  static final Inventory in = new Inventory();
    
    public Inventory characterInventory = new Inventory();
    public ArrayList<Item> itemsOfCharacter = new ArrayList<>();
    public Item currentInventory;
    public int currentItemNum;
    public HashMap<Integer,Item> inventoryButtonMap = new HashMap<>();
    public HashMap<String, Item> equipmentMap = new HashMap<>();
    public String itemInformation = null;
    public JTextArea informationLable;
    public JButton equip;
    public JButton unequip;
    public JButton use;
    public JButton[] Buttons;
    public JButton helmet;
    public JButton chest;
    public JButton weapon;
    public JButton shield;
    public JButton boot;
    public JButton belt;
    public JButton bracers;
    public JButton gloves;
    public JButton ring;
    public GameCharacter player;
    public int emptyNumber; //this is used to diceied which item is empty now so when unequip we can put item here
    public int newEmptyNumber = 99; //this is used to keep the number when we equip an item
    public JTextArea characterInformation;
    
    public InventoryPanel(GameCharacter player){
        this.player = player ;
        characterInventory = player.getInventory();
//        System.out.println("Item: " + Inventories.getItems());
        inventoryRow = Configuration.INVENTORY_ROW;
        inventoryColumn = Configuration.INVENTORY_COLUMN;
        inventorySize = Configuration.INVENTORY_SIZE;
        informationLable = new JTextArea();
        informationLable.setEditable(false);
        equip = new JButton("Equip");
        unequip = new JButton("Unequip");
        use = new JButton("Use");
        putInventoriesIntoItem();
        initUI(player);
        //ItemInformation = "name:\n" + "Damage:\n" + "AttackRange:\n";
        //initUI();
    }
    
    /**
     * this method is to initiate inventory panel UI
     */
     public void initUI(GameCharacter player){
         JPanel panel = new JPanel();
         JPanel topPanel = new JPanel();
         JPanel bottomPanel = new JPanel();
         JPanel RightBottomPanel = new JPanel();
         JPanel basicPanel = new JPanel();
         JPanel[] buttonPanel = new JPanel[59];
         JPanel[] equipments = new JPanel[9];
         for(int f = 0; f < 9; f++){
             equipments[f] = new JPanel();
         }
         JPanel equipmentsPanel = new JPanel();
         equipmentsPanel.setLayout(new BorderLayout());
         JPanel ringAndSheildPanel = new JPanel();
         ringAndSheildPanel.setLayout(new BoxLayout(ringAndSheildPanel, BoxLayout.Y_AXIS));
         JPanel weaponAndBracersPanel = new JPanel();
         weaponAndBracersPanel.setLayout(new BoxLayout(weaponAndBracersPanel, BoxLayout.Y_AXIS));
         JPanel chestAndBeltPanel = new JPanel();
         chestAndBeltPanel.setLayout(new BoxLayout(chestAndBeltPanel, BoxLayout.Y_AXIS));
         JPanel helmetAndBracers = new JPanel();
         JPanel characterView = new JPanel();
         characterInformation = new JTextArea();
         String s = makingInformationForCharacter();
         System.out.print(s);
         characterInformation.setText(s);
         //characterInformation.setPreferredSize(new Dimension(200,200));
         //characterInformation.setMinimumSize(new Dimension(200,200));
         //characterInformation.setPreferredSize(new Dimension(200,200));
         characterView.add(characterInformation);
         characterView.setBorder(BorderFactory.createLineBorder(Color.BLACK));
         
         basicPanel.setLayout(new BoxLayout(basicPanel, BoxLayout.Y_AXIS));
         
         Buttons = new JButton[inventorySize];
         
         helmet = new JButton("None");
         helmet.setPreferredSize(new Dimension(130,50));
         helmet.addActionListener(this);
         helmet.setActionCommand("helmet");
         equipments[0].add(helmet);
         
         chest = new JButton("None");
         chest.setPreferredSize(new Dimension(130,50));
         chest.addActionListener(this);
         chest.setActionCommand("chest");
         equipments[1].add(chest);
         
         weapon = new JButton("None");
         weapon.setPreferredSize(new Dimension(130,50));
         weapon.addActionListener(this);
         weapon.setActionCommand("weapon");
         equipments[2].add(weapon);
         
         shield = new JButton("None");
         shield.setPreferredSize(new Dimension(130,50));
         shield.addActionListener(this);
         shield.setActionCommand("shield");
         equipments[3].add(shield);
         
         boot = new JButton("None");
         boot.setPreferredSize(new Dimension(130,50));
         boot.addActionListener(this);
         boot.setActionCommand("boot");
         equipments[4].add(boot);
         
         ring = new JButton("None");
         ring.setPreferredSize(new Dimension(130,50));
         ring.addActionListener(this);
         ring.setActionCommand("ring");
         equipments[5].add(ring);
         
         gloves = new JButton("None");
         gloves.setPreferredSize(new Dimension(130,50));
         gloves.addActionListener(this);
         gloves.setActionCommand("gloves");
         equipments[6].add(gloves);
         
         belt = new JButton("None");
         belt.setPreferredSize(new Dimension(130,50));
         belt.addActionListener(this);
         belt.setActionCommand("belt");
         equipments[7].add(belt);
         
         bracers = new JButton("None");
         bracers.setPreferredSize(new Dimension(130,50));
         bracers.addActionListener(this);
         bracers.setActionCommand("bracers");
         equipments[8].add(bracers);
         
         Armour ar = characterInventory.getBelt();
         String equipName = null;
         if(ar != null){
             equipName = ar.getName();
             belt.setText(equipName);
             int a = ar.getConstitutionModifier();
             int b = this.player.getConstitutionModifier();
             this.player.setConstitutionModifier(a+b);
             a = ar.getStrengthModifer();
             b = this.player.getStrengthModifier();
             this.player.setStrengthModifier(a+b);
             characterInformation.setText(this.makingInformationForCharacter());
         }
         ar = characterInventory.getBoot();
         if(ar != null){
             equipName = ar.getName();
             boot.setText(equipName);
             int a = ar.getDexterityModifer();
             int b = this.player.getDexterityModifier();
             this.player.setDexterityModifier(a+b);
             characterInformation.setText(this.makingInformationForCharacter());
         }
         ar = characterInventory.getBracers();
         if(ar != null){
             equipName = ar.getName();
             bracers.setText(equipName);
             int a = ar.getStrengthModifer();
             int b = this.player.getStrengthModifier();
             this.player.setStrengthModifier(a+b);
             characterInformation.setText(this.makingInformationForCharacter());
         }
         ar = characterInventory.getChest();
         if(ar != null){
             equipName = ar.getName();
             chest.setText(equipName);
         }
         ar = characterInventory.getGloves();
         if(ar != null){
             equipName = ar.getName();
             gloves.setText(equipName);
         }
         ar = characterInventory.getHelmet();
         if(ar != null){
             equipName = ar.getName();
             helmet.setText(equipName);
             int a = ar.getIntelligenceModifier();
             int b = this.player.getIntelligenceModifier();
             this.player.setIntelligenceModifier(a+b);;
             a = ar.getWisdomModifier();
             b = this.player.getWisdomModifier();
             this.player.setWisdomModifier(a+b);
             characterInformation.setText(this.makingInformationForCharacter());
         }
         ar = characterInventory.getShield();
         if(ar != null){
             equipName = ar.getName();
             shield.setText(equipName);
         }
         Ring ri = characterInventory.getRing();
         if(ri != null){
             equipName = ri.getName();
             ring.setText(equipName);
             int a = ri.getConstitutionModifier();
             int b = this.player.getConstitutionModifier();
             System.out.println("player: " + b);
             System.out.println("Ring: " + a);
             this.player.setConstitutionModifier(a+b);
             //System.out.println(this.player.getConstitutionModifier());
             a = ri.getStrengthModifer();
             b = this.player.getStrengthModifier();
             this.player.setStrengthModifier(a+b);
             a = ri.getWisdomModifier();
             b = this.player.getWisdomModifier();
             this.player.setWisdomModifier(a+b);
             a = ri.getCharismaModifer();
             b = this.player.getCharismaModifier();
             this.player.setCharismaModifier(a+b);
             characterInformation.setText(this.makingInformationForCharacter());
         }
         Weapon wa = characterInventory.getEquippedWeapon();
         if(wa != null){
             equipName = wa.getName();
             weapon.setText(equipName);
         }
         
         ringAndSheildPanel.add(equipments[3]);
         ringAndSheildPanel.add(equipments[8]);
         ringAndSheildPanel.add(equipments[5]);
         
         weaponAndBracersPanel.add(equipments[2]);
         weaponAndBracersPanel.add(equipments[6]);
         
         chestAndBeltPanel.add(equipments[1]);
         chestAndBeltPanel.add(equipments[7]);
         
         helmetAndBracers.add(equipments[0]);
         
         equipmentsPanel.add(equipments[4], BorderLayout.SOUTH);
         equipmentsPanel.add(equipments[0], BorderLayout.NORTH);
         equipmentsPanel.add(weaponAndBracersPanel, BorderLayout.WEST);
         equipmentsPanel.add(ringAndSheildPanel, BorderLayout.EAST);
         equipmentsPanel.add(chestAndBeltPanel, BorderLayout.CENTER);
         
         
         JTextArea Money;
         if(characterInventory.getTotGold() != null){
            Money = new JTextArea("Gold:" + characterInventory.getTotGold().toString());
         }else{
            Money = new JTextArea("Gold: ");
         }
         Money.setEditable(false);
         
         use.setEnabled(false);
         
         RightBottomPanel.setLayout(new GridLayout(4, 1, 5, 5));
         RightBottomPanel.add(Money);
         RightBottomPanel.add(equip);
         RightBottomPanel.add(unequip);
         RightBottomPanel.add(use);
         
         
         JScrollPane Pane = new JScrollPane();
         JScrollPane Pane2 = new JScrollPane();
         
         Pane2.setPreferredSize(new Dimension(400,100));
        
         //informationLable.setPreferredSize(new Dimension(400,100));
         Pane2.getViewport().add(informationLable);
         
         Pane.setPreferredSize(new Dimension(450,450));
         
         topPanel.setLayout(new GridLayout(inventoryRow, inventoryColumn, 0, 0));
         
         Iterator it = itemsOfCharacter.iterator();
         int i = 0;
         for(; i < itemsOfCharacter.size(); i++){
             Item in = (Item)it.next();
             inventoryButtonMap.put(i,in);
             buttonPanel[i] = new JPanel();
             Buttons[i] = new JButton();
             if(in instanceof Weapon){
                Weapon w = new Weapon();
                w = (Weapon)in;
                Buttons[i].setText(w.getName());
                System.out.println(w.getName());
                Buttons[i].setActionCommand(Integer.toString(i));
                Buttons[i].addActionListener(this);
                Buttons[i].setPreferredSize(new Dimension(130,50));
                buttonPanel[i].add(Buttons[i]);            
                topPanel.add(buttonPanel[i]);
             }else if ( in instanceof Armour){
                 Armour a = new Armour();
                 a = (Armour)in;
                 Buttons[i].setText(a.getName());
                 Buttons[i].setActionCommand(Integer.toString(i));
                 Buttons[i].addActionListener(this);
                 Buttons[i].setPreferredSize(new Dimension(130,50));
                 buttonPanel[i].add(Buttons[i]);            
                 topPanel.add(buttonPanel[i]);
             }else if ( in instanceof Potion){
                 Potion p = new Potion();
                 p = (Potion)in;
                 Buttons[i].setText(p.getName());
                 Buttons[i].setActionCommand(Integer.toString(i));
                 Buttons[i].addActionListener(this);
                 Buttons[i].setPreferredSize(new Dimension(130,50));
                 buttonPanel[i].add(Buttons[i]);            
                 topPanel.add(buttonPanel[i]);
             }else if ( in instanceof Ring){
                 Ring r = new Ring();
                 r = (Ring)in;
                 Buttons[i].setText(r.getName());
                 Buttons[i].setActionCommand(Integer.toString(i));
                 Buttons[i].addActionListener(this);
                 Buttons[i].setPreferredSize(new Dimension(130,50));
                 buttonPanel[i].add(Buttons[i]);            
                 topPanel.add(buttonPanel[i]);
             } 
         }
         emptyNumber = i;
         for(; i < inventorySize; i++){
             Buttons[i] = new JButton();
             buttonPanel[i] = new JPanel();
             Buttons[i].setPreferredSize(new Dimension(130,50));
             buttonPanel[i].add(Buttons[i]);            
             topPanel.add(buttonPanel[i]);
         }
         
          equip.setEnabled(false); // equip action
          equip.setActionCommand("equip");
         equip.addActionListener(this);
         
         unequip.setEnabled(false);
         unequip.setActionCommand("unequip");
         unequip.addActionListener(this);
         
         
         Pane.getViewport().add(topPanel);
         
         bottomPanel.add(Pane2);//usedtobe informationLable
         bottomPanel.add(RightBottomPanel);
         
         basicPanel.add(Pane);
         basicPanel.add(bottomPanel);
         
         panel.add(characterView);//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
         panel.add(equipmentsPanel);
         panel.add(basicPanel);
         
         add(panel);
         setModalityType(ModalityType.APPLICATION_MODAL);
        setTitle("Inventory Panel");
        setSize(550,1200);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        //setLocationRelativeTo(frame);
        setVisible(true); 
     }
    
     /**
      * this method is used to make the weapon information
      * @param W the weapon which is used to make information
      * @return the information of the weapon
      */
    public String makingInformationOfWeapon(Weapon W){
        String AttackPts = Integer.toString(W.getAttackPts());
        String AttackRange = Integer.toString(W.getAttackRange());
        String WeaponName = W.getName();
        String WeaponType = W.getWeaponType();
        StringBuilder InformationSB = new StringBuilder();
        InformationSB.append("WeaponName: ");
        InformationSB.append(WeaponName);
        InformationSB.append("\n");
        InformationSB.append("WeaponType: ");
        InformationSB.append(WeaponType);
        InformationSB.append("\n");
        InformationSB.append("AttackRange: ");
        InformationSB.append(AttackRange);
        InformationSB.append("\n");
        InformationSB.append("AttackPts: ");
        InformationSB.append(AttackPts);
        InformationSB.append("\n");
        String Information = InformationSB.toString();
        return Information;
    }
    
    /**
     * this method is used to make information for armor
     * @param A an object of armor 
     * @return information of armor
     */
    public String makingInformationOfArmor(Armour A){
        String ArmourPts = Integer.toString(A.getArmourPts());
        String ArmourName = A.getName();
        StringBuilder InformationSB = new StringBuilder();
        InformationSB.append("ArmourName: ");
        InformationSB.append(ArmourName);
        InformationSB.append("\n");
        InformationSB.append("ArmourClass: ");
        InformationSB.append(ArmourPts);
        InformationSB.append("\n");
        InformationSB.append("StrengthModifer: ");
        InformationSB.append(A.getStrengthModifer());
        InformationSB.append("\n");
        InformationSB.append("CharismaModifer: ");
        InformationSB.append(A.getCharismaModifer());
        InformationSB.append("\n");
        InformationSB.append("WisdomModifier: ");
        InformationSB.append(A.getWisdomModifier());
        InformationSB.append("\n");
        InformationSB.append("IntelligenceModifier: ");
        InformationSB.append(A.getIntelligenceModifier());
        InformationSB.append("\n");
        InformationSB.append("DexterityModifer: ");
        InformationSB.append(A.getDexterityModifer());
        InformationSB.append("\n");
        InformationSB.append("ConstitutionModifier: ");
        InformationSB.append(A.getConstitutionModifier());
        InformationSB.append("\n");
        
        String Information = InformationSB.toString();
        return Information;
    }
    
    public String makingInformationForCharacter(){
    	String result = null;
    	StringBuilder sb = new StringBuilder();
    	sb.append("level: ");
    	sb.append(this.player.getLevel());
    	sb.append("\n");
    	
    	sb.append("Strength: ");
    	sb.append(this.player.getStrength());
    	if(this.player.getStrengthModifier()<0){
    		sb.append(this.player.getStrengthModifier());
    	}else{
    		sb.append(" + ");
    		sb.append(this.player.getStrengthModifier());
    	}
    	sb.append("\n");
    	
    	sb.append("Constitution: ");
    	sb.append(this.player.getConstitution());
    	if(this.player.getConstitutionModifier()<0){
    		sb.append(this.player.getConstitutionModifier());
    	}else{
    		sb.append(" + ");
    		sb.append(this.player.getConstitutionModifier());
    	}
    	sb.append("\n");
    	
    	sb.append("Dexterity: ");
    	sb.append(this.player.getDexterity());
    	if(this.player.getDexterityModifier()<0){
    		sb.append(this.player.getDexterityModifier());
    	}else{
    		sb.append(" + ");
    		sb.append(this.player.getDexterityModifier());
    	}
    	sb.append("\n");
    	
    	sb.append("Intelligence: ");
    	sb.append(this.player.getIntelligence());
    	if(this.player.getDexterityModifier()<0){
    		sb.append(this.player.getIntelligenceModifier());
    	}else{
    		sb.append(" + ");
    		sb.append(this.player.getIntelligenceModifier());
    	}
    	sb.append("\n");
    	
    	sb.append("Charisma: ");
    	sb.append(this.player.getCharisma());
    	if(this.player.getCharismaModifier()<0){
    		sb.append(this.player.getCharismaModifier());
    	}else{
    		sb.append(" + ");
    		sb.append(this.player.getCharismaModifier());
    	}
    	sb.append("\n");
    	
    	sb.append("Wisdom: ");
    	sb.append(this.player.getWisdom());
    	if(this.player.getWisdomModifier()<0){
    		sb.append(this.player.getWisdomModifier());
    	}else{
    		sb.append(" + ");
    		sb.append(this.player.getWisdomModifier());
    	}
    	sb.append("\n");
    	
    	sb.append("hit points: ");
    	sb.append(this.player.getHealth());
    	sb.append("\n");
    	
    	sb.append("base Attack Modifier: ");
    	sb.append(this.player.getLevel());
    	sb.append("\n");
    	
    	sb.append("number of attacks per round: ");
    	sb.append(calculateBaseAttackBonuss(this.player.getLevel()));
    	sb.append("\n");
    	
    	sb.append("Attack modifier for melee: ");
    	int a = this.player.getLevel() + this.player.getStrengthModifier();
    	sb.append(a);
    	sb.append("\n");
    	
    	sb.append("Attack modifier for range: ");
    	a = this.player.getLevel() + this.player.getDexterityModifier();
    	sb.append(a);
    	sb.append("\n");
    	
    	sb.append("Armor Class: ");
    	int ac = calculateArmorClass();
    	sb.append(ac);
    	
    	result = sb.toString();
    	return result;
    }
    
    public int calculateBaseAttackBonuss(int level) {


        if (level <= 5) {
            return 1;
        } else {
            int div = level / 5;

            if (level % 5 != 0) {
                div = div + 1;
            }
            return div;
        }
    }
    
    public int calculateArmorClass() {
            GameCharacter player = this.player;
            //player.getArmorModifier() + player.sheildModifier
            // player.getInventory().

            Inventory inventory = player.getInventory();
            int ArmorModifier = 0;
            int ShieldModifier = 0;

            //  System.out.println("CurrentPlayer" +currentplayer+ "Inventory " +inventory.getEquippedWeapon().getName());

            if (inventory.getBoot() != null) {
                ArmorModifier = ArmorModifier + inventory.getBoot().getArmourPts();
            }
            if (inventory.getBracers() != null) {
                ArmorModifier = ArmorModifier + inventory.getBracers().getArmourPts();
            }
            if (inventory.getChest() != null) {
                ArmorModifier = ArmorModifier + inventory.getChest().getArmourPts();
            }
            if (inventory.getGloves() != null) {
                ArmorModifier = ArmorModifier + inventory.getGloves().getArmourPts();
            }
            if (inventory.getShield() != null) {
                ShieldModifier = inventory.getShield().getArmourPts();
            }
            if (inventory.getHelmet() != null) {
                ArmorModifier = ArmorModifier + inventory.getHelmet().getArmourPts();
            }

            System.out.println("Armor Modifier" + ArmorModifier + "ShieldModifier" + ShieldModifier);

            return (10 + ArmorModifier + ShieldModifier);
    }
    
    /**
     * this method is put all itmes of CharacterInventory into an arrylist of itme
     */
    public boolean putInventoriesIntoItem(){
        LinkedList<Item> I = characterInventory.getItems();
        System.out.println("item:" + I);
        
        if(I != null){
            Iterator it = I.iterator();
        while(it.hasNext()){
            Item item = (Item)it.next();
            if(item != null){
                if (item instanceof Weapon){
                Weapon w = (Weapon)item;
                itemsOfCharacter.add(w);
                System.out.println("have a weapon");
                }
                else if(item instanceof Armour){
                Armour a = (Armour)item;
                itemsOfCharacter.add(a);
                System.out.println("have an armor");
                }else if(item instanceof Ring){
                Ring r = (Ring)item;
                itemsOfCharacter.add(r);
                }else if(item instanceof Potion){
                Potion p = (Potion)item;
                itemsOfCharacter.add(p);
                }
            }
            }
        }
        return true;
        }
    
    /**
     * this method is used to make information for other items
     * @param name
     * @return the information of item
     */
    public String makingInformationForOthers(String name){
        String information = "Name: " + name + "\n";
        return information;
    }
    
    
    public String makingInformationFOrRing(Ring r){
    	String s = null;
        String ArmourName = r.getName();
        StringBuilder InformationSB = new StringBuilder();
        InformationSB.append("Name: ");
        InformationSB.append(ArmourName);
        InformationSB.append("\n");
    	InformationSB.append("ArmourClass: ");
        InformationSB.append(r.getArmourPts());
        InformationSB.append("\n");
        InformationSB.append("StrengthModifer: ");
        InformationSB.append(r.getStrengthModifer());
        InformationSB.append("\n");
        InformationSB.append("CharismaModifer: ");
        InformationSB.append(r.getCharismaModifer());
        InformationSB.append("\n");
        InformationSB.append("WisdomModifier: ");
        InformationSB.append(r.getWisdomModifier());
        InformationSB.append("\n");
        InformationSB.append("IntelligenceModifier: ");
        InformationSB.append(r.getIntelligenceModifier());
        InformationSB.append("\n");
        InformationSB.append("DexterityModifer: ");
        InformationSB.append(r.getDexterityModifer());
        InformationSB.append("\n");
        InformationSB.append("ConstitutionModifier: ");
        InformationSB.append(r.getConstitutionModifier());
        InformationSB.append("\n");
        s = InformationSB.toString();
        return s;
    }
    /**
     * this method is used to creat the function of buttons include equip and unequip
     * @param event this is an action event
     */
    @Override
    public void actionPerformed(ActionEvent event){
        String command = event.getActionCommand();
        System.out.println(command);
        if("equip".equals(command)){
            LinkedList<Item> li = characterInventory.getItems();
            //int id = currentInventory.getItemID();
            for(int i =0; i<li.size();i++){
                Item it = li.get(i);
                if(it == currentInventory){
                    System.out.println("remove it!");
                    li.remove(i);
                    break;
                }
            }
            characterInventory.setItems(li);
            if(currentInventory instanceof Weapon){
                Weapon wea2 = characterInventory.getEquippedWeapon();
                if(wea2 == null){
                    Weapon wea = (Weapon)currentInventory;
                    String name = wea.getName();
                    characterInventory.setEquippedWeapon(wea);
                    Buttons[currentItemNum].setText("");
                    newEmptyNumber = currentItemNum;
                    weapon.setText(name);
                    informationLable.setText("");
                    inventoryButtonMap.remove(currentItemNum);
                }else{
                    Weapon wea = (Weapon)currentInventory;
                    String name = wea.getName();
                    characterInventory.setEquippedWeapon(wea);
                    Buttons[currentItemNum].setText(wea2.getName());
                    weapon.setText(name);
                    informationLable.setText("");
                    inventoryButtonMap.remove(currentItemNum);
                    inventoryButtonMap.put(currentItemNum, wea2);
                    li.add(wea2);
                    characterInventory.setItems(li);
                }
           }else if(currentInventory instanceof Armour){
                    Armour armor = (Armour)currentInventory;
                    String name = armor.getName();
                    String type = armor.getArmourType();
                    switch(type){
                        case "boot": Armour ar = characterInventory.getBoot();
                        			 int a = armor.getDexterityModifer();
                        			 int d = player.getDexterityModifier();
                        			 player.setDexterityModifier(a+d);
                                     if(ar == null){
                                        characterInventory.setBoot(armor);
                                        Buttons[currentItemNum].setText("");
                                        newEmptyNumber = currentItemNum;
                                        boot.setText(name);
                                        informationLable.setText("");
                                        inventoryButtonMap.remove(currentItemNum);
                                     }else{
                                         characterInventory.setBoot(armor);
                                        Buttons[currentItemNum].setText(ar.getName());
                                        boot.setText(name);
                                        informationLable.setText("");
                                        inventoryButtonMap.remove(currentItemNum);
                                        inventoryButtonMap.put(currentItemNum, ar);
                                        li.add(ar);
                                        characterInventory.setItems(li);
                                     }
                                     break;
                        case "helmet": ar = characterInventory.getHelmet();
                        				a = armor.getIntelligenceModifier();
                        				d = player.getIntelligenceModifier();
                        				player.setIntelligenceModifier(a+d);
                        				a = armor.getWisdomModifier();
                        				d = player.getWisdomModifier();
                        				player.setWisdomModifier(a+d);;
                                     if(ar == null){
                                        characterInventory.setHelmet(armor);
                                        Buttons[currentItemNum].setText("");
                                        newEmptyNumber = currentItemNum;
                                        helmet.setText(name);
                                        informationLable.setText("");
                                        inventoryButtonMap.remove(currentItemNum);
                                     }else{
                                        characterInventory.setHelmet(armor);
                                        Buttons[currentItemNum].setText(ar.getName());
                                        helmet.setText(name);
                                        informationLable.setText("");
                                        inventoryButtonMap.remove(currentItemNum);
                                        inventoryButtonMap.put(currentItemNum, ar);
                                        li.add(ar);
                                        characterInventory.setItems(li);
                                     }
                                     break;
                        case "chest":ar = characterInventory.getChest();
                                     if(ar == null){
                                        characterInventory.setChest(armor);
                                        Buttons[currentItemNum].setText("");
                                        newEmptyNumber = currentItemNum;
                                        chest.setText(name);
                                        informationLable.setText("");
                                        inventoryButtonMap.remove(currentItemNum);
                                     }else{
                                        characterInventory.setChest(armor);
                                        Buttons[currentItemNum].setText(ar.getName());
                                        chest.setText(name);
                                        informationLable.setText("");
                                        inventoryButtonMap.remove(currentItemNum);
                                        inventoryButtonMap.put(currentItemNum, ar);
                                        li.add(ar);
                                        characterInventory.setItems(li);
                                     }
                                     break;
                        case "shield": ar = characterInventory.getShield();
                                     if(ar == null){
                                        characterInventory.setShield(armor);
                                        Buttons[currentItemNum].setText("");
                                        newEmptyNumber = currentItemNum;
                                        shield.setText(name);
                                        informationLable.setText("");
                                        inventoryButtonMap.remove(currentItemNum);
                                     }else{
                                        characterInventory.setShield(armor);
                                        Buttons[currentItemNum].setText(ar.getName());
                                        shield.setText(name);
                                        informationLable.setText("");
                                        inventoryButtonMap.remove(currentItemNum);
                                        inventoryButtonMap.put(currentItemNum, ar);
                                        li.add(ar);
                                        characterInventory.setItems(li);
                                     }
                                     break;
                        case "gloves": ar = characterInventory.getGloves();
                                     if(ar == null){
                                        characterInventory.setGloves(armor);
                                        Buttons[currentItemNum].setText("");
                                        newEmptyNumber = currentItemNum;
                                        gloves.setText(name);
                                        informationLable.setText("");
                                        inventoryButtonMap.remove(currentItemNum);
                                     }else{
                                        characterInventory.setGloves(armor);
                                        Buttons[currentItemNum].setText(ar.getName());
                                        gloves.setText(name);
                                        informationLable.setText("");
                                        inventoryButtonMap.remove(currentItemNum);
                                        inventoryButtonMap.put(currentItemNum, ar);
                                        li.add(ar);
                                        characterInventory.setItems(li);
                                     }
                                     break;
                        case "belt": ar = characterInventory.getBelt();
                        			 a = armor.getConstitutionModifier();
                        			 d = player.getConstitutionModifier();
                        			 player.setConstitutionModifier(a+d);
                        			 a = armor.getStrengthModifer();
                        			 d = player.getStrengthModifier();
                        			 player.setStrengthModifier(a+d);;
                                     if(ar == null){
                                        characterInventory.setBelt(armor);
                                        Buttons[currentItemNum].setText("");
                                        newEmptyNumber = currentItemNum;
                                        belt.setText(name);
                                        informationLable.setText("");
                                        inventoryButtonMap.remove(currentItemNum);
                                     }else{
                                        characterInventory.setBelt(armor);
                                        Buttons[currentItemNum].setText(ar.getName());
                                        belt.setText(name);
                                        informationLable.setText("");
                                        inventoryButtonMap.remove(currentItemNum);
                                        inventoryButtonMap.put(currentItemNum, ar);
                                        li.add(ar);
                                        characterInventory.setItems(li);
                                     }
                                     break;
                        case "bracers": ar = characterInventory.getBracers();
                        				a = armor.getStrengthModifer();
                        				d = player.getStrengthModifier();
                        				player.setStrengthModifier(a+d);
                                     if(ar == null){
                                        characterInventory.setBracers(armor);
                                        Buttons[currentItemNum].setText("");
                                        newEmptyNumber = currentItemNum;
                                        bracers.setText(name);
                                        informationLable.setText("");
                                        inventoryButtonMap.remove(currentItemNum);
                                     }else{
                                        characterInventory.setBracers(armor);
                                        Buttons[currentItemNum].setText(ar.getName());
                                        bracers.setText(name);
                                        informationLable.setText("");
                                        inventoryButtonMap.remove(currentItemNum);
                                        inventoryButtonMap.put(currentItemNum, ar);
                                        li.add(ar);
                                        characterInventory.setItems(li);
                                     }
                                     break;
                    }
                }else if(currentInventory instanceof Ring){
                    Ring ri2 = characterInventory.getRing();
                    Ring ri = (Ring)currentInventory;
                    int r = ri.getStrengthModifer();
                    int s = player.getStrengthModifier();
                    player.setStrengthModifier(s+r);
                    r = ri.getConstitutionModifier();
                    s = player.getConstitutionModifier();
                    player.setConstitutionModifier(s+r);;
                    r = ri.getWisdomModifier();
                    s = player.getWisdomModifier();
                    player.setWisdomModifier(s+r);;
                    r = ri.getCharismaModifer();
                    s = player.getCharismaModifier();
                    player.setCharismaModifier(s+r);;
                    if(ri2 == null){                       
                        String name = ri.getName();
                        characterInventory.setRing(ri);
                        Buttons[currentItemNum].setText("");
                        newEmptyNumber = currentItemNum;
                        ring.setText(name);
                        informationLable.setText("");
                        inventoryButtonMap.remove(currentItemNum);
                    }else{
                        String name = ri.getName();
                        characterInventory.setRing(ri);
                        Buttons[currentItemNum].setText(ri2.getName());
                        ring.setText(name);
                        informationLable.setText("");
                        inventoryButtonMap.remove(currentItemNum);
                        inventoryButtonMap.put(currentItemNum, ri2);
                        li.add(ri2);
                        characterInventory.setItems(li);
                    }
                }
            	characterInformation.setText(this.makingInformationForCharacter());
                player.setInventory(characterInventory);
                
                equip.setEnabled(false);
                unequip.setEnabled(false);
        }else if("unequip".equals(command)){
                LinkedList<Item> li;
                if(currentInventory instanceof Weapon){
                    Weapon wea = (Weapon)currentInventory;
                    String name = wea.getName();
                    li = characterInventory.getItems();
                    if(li != null){
                        li.add(wea);
                    }else{
                        li = new LinkedList<>();
                        li.add(wea);
                    }
                    characterInventory.setEquippedWeapon(null);
                    characterInventory.setItems(li);
                    int z = emptyNumber;
                    if(newEmptyNumber<emptyNumber){
                        emptyNumber = newEmptyNumber;
                    }
                    
                    Buttons[emptyNumber].setText(name);
                    inventoryButtonMap.put(emptyNumber, currentInventory);
                    Buttons[emptyNumber].addActionListener(this);
                    Buttons[emptyNumber].setActionCommand(Integer.toString(emptyNumber));
                    weapon.setText("None");
                    characterInventory.setEquippedWeapon(null);
                    if(emptyNumber<z){
                        emptyNumber = z;
                    }else{
                        z++;
                        emptyNumber=z;
                    }
                    newEmptyNumber = 99;
                }else if (currentInventory instanceof Armour){
                    Armour armor = (Armour)currentInventory;
                    String name = armor.getName();
                    String type = armor.getArmourType();
                    li = characterInventory.getItems();
                    if(li != null){
                        li.add(armor);
                    }else{
                        li = new LinkedList<>();
                        li.add(armor);
                    }
                    characterInventory.setItems(li);
                    int z = emptyNumber;
                    if(newEmptyNumber<emptyNumber){
                        emptyNumber = newEmptyNumber;
                    }
                    Buttons[emptyNumber].setText(name);
                    inventoryButtonMap.put(emptyNumber, currentInventory);
                    Buttons[emptyNumber].addActionListener(this);
                    Buttons[emptyNumber].setActionCommand(Integer.toString(emptyNumber));
                    switch (type){
                        case "boot":
               			 	int a = armor.getDexterityModifer();
               			 	int d = player.getDexterityModifier();
               			 	player.setDexterityModifier(d-a);
                            boot.setText("None");
                            characterInventory.setBoot(null);
                            break;
                        case "belt":
                        	a = armor.getConstitutionModifier();
               			 	d = player.getConstitutionModifier();
               			 	player.setConstitutionModifier(d-a);
               			 	a = armor.getStrengthModifer();
               			 	d = player.getStrengthModifier();
               			 	player.setStrengthModifier(d-a);;
                            belt.setText("None");
                            characterInventory.setBelt(null);
                            break;
                        case "gloves, ":
                            gloves.setText("None");
                            characterInventory.setGloves(null);
                            break;
                        case "bracers":
                        	a = armor.getStrengthModifer();
            				d = player.getStrengthModifier();
            				player.setStrengthModifier(d-a);
                            bracers.setText("None");
                            characterInventory.setBracers(null);
                            break;
                        case "helmet":
            				a = armor.getIntelligenceModifier();
            				d = player.getIntelligenceModifier();
            				player.setIntelligenceModifier(d-a);
            				a = armor.getWisdomModifier();
            				d = player.getWisdomModifier();
            				player.setWisdomModifier(d-a);;
                            helmet.setText("None");
                            characterInventory.setHelmet(null);
                            break;
                        case "chest":
                            chest.setText("None");
                            characterInventory.setChest(null);
                            break;
                        case "shield":
                            shield.setText("None");
                            characterInventory.setShield(null);
                            break;
                    }
                    if(emptyNumber<z){
                        emptyNumber = z;
                    }else{
                        z++;
                        emptyNumber=z;
                    }
                    newEmptyNumber = 99;
                }else if(currentInventory instanceof Ring){
                    Ring wea = (Ring)currentInventory;
                    int r = wea.getStrengthModifer();
                    int s = player.getStrengthModifier();
                    player.setStrengthModifier(s-r);
                    r = wea.getConstitutionModifier();
                    s = player.getConstitutionModifier();
                    player.setConstitutionModifier(s-r);;
                    r = wea.getWisdomModifier();
                    s = player.getWisdomModifier();
                    player.setWisdomModifier(s-r);;
                    r = wea.getCharismaModifer();
                    s = player.getCharismaModifier();
                    player.setCharismaModifier(s-r);;
                    String name = wea.getName();
                    li = characterInventory.getItems();
                    if(li != null){
                        li.add(wea);
                    }else{
                        li = new LinkedList<>();
                        li.add(wea);
                    }
                    characterInventory.setRing(null);
                    characterInventory.setItems(li);
                    int z = emptyNumber;
                    if(newEmptyNumber<emptyNumber){
                        emptyNumber = newEmptyNumber;
                    }
                    Buttons[emptyNumber].setText(name);
                    inventoryButtonMap.put(emptyNumber, currentInventory);
                    Buttons[emptyNumber].addActionListener(this);
                    Buttons[emptyNumber].setActionCommand(Integer.toString(emptyNumber));
                    ring.setText("None");
                    characterInventory.setRing(null);
                    if(emptyNumber<z){
                        emptyNumber = z;
                    }else{
                        z++;
                        emptyNumber=z;
                    }
                    newEmptyNumber = 99;
                }
                MapPanel.inventory = characterInventory;
                characterInformation.setText(this.makingInformationForCharacter());
                equip.setEnabled(false);
                unequip.setEnabled(false);
        }else if("ring".equals(command)){
            currentInventory = characterInventory.getRing();
            if(currentInventory != null){
                equip.setEnabled(false);
                unequip.setEnabled(true);
            }else{
                equip.setEnabled(false);
                unequip.setEnabled(false);
            }
        }else if("weapon".equals(command)){
            currentInventory = characterInventory.getEquippedWeapon();
            if(currentInventory != null){
                equip.setEnabled(false);
                unequip.setEnabled(true);
            }else{
                equip.setEnabled(false);
                unequip.setEnabled(false);
            }
        }else if("helmet".equals(command)){
            currentInventory = characterInventory.getHelmet();
            if(currentInventory != null){
                equip.setEnabled(false);
                unequip.setEnabled(true);
            }else{
                equip.setEnabled(false);
                unequip.setEnabled(false);
            }
        }else if("chest".equals(command)){
            currentInventory = characterInventory.getChest();
            if(currentInventory != null){
                equip.setEnabled(false);
                unequip.setEnabled(true);
            }else{
                equip.setEnabled(false);
                unequip.setEnabled(false);
            }
        }else if("shield".equals(command)){
            currentInventory = characterInventory.getShield();
            if(currentInventory != null){
                equip.setEnabled(false);
                unequip.setEnabled(true);
            }else{
                equip.setEnabled(false);
                unequip.setEnabled(false);
            }
        }else if("boot".equals(command)){
            currentInventory = characterInventory.getBoot();
            if(currentInventory != null){
                equip.setEnabled(false);
                unequip.setEnabled(true);
            }else{
                equip.setEnabled(false);
                unequip.setEnabled(false);
            }
        }else if("gloves".equals(command)){
            currentInventory = characterInventory.getGloves();
            if(currentInventory != null){
                equip.setEnabled(false);
                unequip.setEnabled(true);
            }else{
                equip.setEnabled(false);
                unequip.setEnabled(false);
            }
        }else if("belt".equals(command)){
            currentInventory = characterInventory.getBelt();
            if(currentInventory != null){
                equip.setEnabled(false);
                unequip.setEnabled(true);
            }else{
                equip.setEnabled(false);
                unequip.setEnabled(false);
            }
        }else if("bracers".equals(command)){
            currentInventory = characterInventory.getBracers();
            if(currentInventory != null){
                equip.setEnabled(false);
                unequip.setEnabled(true);
            }else{
                equip.setEnabled(false);
                unequip.setEnabled(false);
            }
        }else{
            int commandNum = Integer.parseInt(command);
            System.out.println(commandNum);
            currentItemNum = commandNum;
            Item item = inventoryButtonMap.get(commandNum);
            currentInventory = item;
        
            if(item instanceof Weapon){
                Weapon w = (Weapon) item;
                informationLable.setText(makingInformationOfWeapon(w));
                    equip.setEnabled(true);
                    unequip.setEnabled(false);
                    use.setEnabled(false);
                    //currentInventory = w;
            }else if (item instanceof Armour) {
                Armour a = (Armour) item;
                informationLable.setText(makingInformationOfArmor(a));
                equip.setEnabled(true);
                unequip.setEnabled(false);
                use.setEnabled(false);
            }else if (item instanceof Ring) {
                Ring r = (Ring) item;
                informationLable.setText(makingInformationFOrRing(r));
                equip.setEnabled(true);
                unequip.setEnabled(false);
                use.setEnabled(false);
            }else if (item instanceof Potion){
                Potion p = (Potion) item;
                informationLable.setText(makingInformationForOthers(p.getName()));
                equip.setEnabled(false);
                unequip.setEnabled(false);
                use.setEnabled(true);
            }
        }
    }
    
      public static void main(String[] args) {
		// TODO Auto-generated method stub
         Inventory in = new Inventory();
         Player p = new Player();
         LinkedList<Item> i = new LinkedList<>();
         Weapon w = new Weapon();
          w.setAttackPts(12);
          w.setAttackRange(2);
          w.setName("Sword");
          w.setItemID(0);
          w.setWeaponType("Meele");
          
          Weapon w2 = new Weapon();
          w2.setAttackPts(10);
          w2.setAttackRange(5);
          w2.setName("LongBow");
          w2.setItemID(1);
          w2.setWeaponType("Range");
          
          Ring r = new Ring();
          r.setName("bigRing");
          r.setItemID(2);
          r.setStrengthModifer(2);
          r.setConstitutionModifier(3);
          r.setWisdomModifier(1);
          r.setCharismaModifer(4);
          
          Ring r2 = new Ring();
          r.setName("smallRing");
          r.setItemID(3);
          
          Armour a = new Armour();
          a.setArmourPts(2);
          a.setName("LeatherBoot");
          a.setArmourType("boot");
          a.setDexterityModifer(2);
          
          Armour a2 = new Armour();
          a2.setArmourPts(1);
          a2.setName("PaddedGloves");
          a2.setArmourType("gloves");
          
          Armour a3 = new Armour();
          a3.setArmourPts(4);
          a3.setName("chainShirt");
          a3.setArmourType("chest");
          
          Armour a4 = new Armour();
          a4.setArmourPts(2);
          a4.setName("LeatherHelmet");
          a4.setArmourType("helmet");
          a4.setIntelligenceModifier(2);
          a4.setWisdomModifier(3);
          
          Armour a5 = new Armour();
          a5.setArmourPts(0);
          a5.setName("LeatherBelt");
          a5.setArmourType("belt");
          a5.setConstitutionModifier(1);
          a5.setStrengthModifer(2);
          
          Armour a6 = new Armour();
          a6.setArmourPts(7);
          a6.setName("studdedLeatherBracer");
          a6.setArmourType("bracers");
          a6.setStrengthModifer(3);
          
          Armour a7 = new Armour();
          a7.setArmourPts(8);
          a7.setName("fullPlate");
          a7.setArmourType("chest");
          
          Armour a8 = new Armour();
          a8.setArmourPts(1);
          a8.setName("bucker");
          a8.setArmourType("shield");
          
          Armour a9 = new Armour();
          a9.setArmourPts(2);
          a9.setName("heavyShield");
          a9.setArmourType("shield");
          
          Armour a10 = new Armour();
          a10.setArmourPts(4);
          a10.setName("TowerShield");
          a10.setArmourType("shield");
          
          i.add(w);
          i.add(a);
          i.add(a2);
          i.add(a3);
          i.add(a4);
          i.add(a5);
          i.add(a6);
          i.add(a7);
          i.add(a8);
          i.add(a9);
          i.add(a10);
          //in.setBoot(a);
          in.setEquippedWeapon(w2);
          in.setRing(r);
          in.setTotGold(new Long(178972));
          in.setItems(i);
          p.setInventory(in);
          p.setStrength(10);
          p.setStrengthModifier(2);
          p.setCharismaModifier(11);
          p.setCharismaModifier(3);
          p.setConstitution(12);
          p.setConstitutionModifier(4);
          p.setDexterity(13);
          p.setDexterityModifier(5);
          p.setHealth(20);
          p.setWisdom(14);
          p.setWisdomModifier(6);
          p.setIntelligence(15);
          p.setIntelligenceModifier(7);
          p.setLevel(3);
          //System.out.println("current weapon1:" + p.getInventory().getEquippedWeapon().getName());
          InventoryPanel ex = new InventoryPanel(p);
          //System.out.println("current weapon2:" + p.getInventory().getEquippedWeapon().getName());
	}
}
