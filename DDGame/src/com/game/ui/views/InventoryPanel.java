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
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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
         
         basicPanel.setLayout(new BoxLayout(basicPanel, BoxLayout.Y_AXIS));
         
         Buttons = new JButton[inventorySize];
         
         helmet = new JButton("helmet");
         helmet.setPreferredSize(new Dimension(130,50));
         helmet.addActionListener(this);
         helmet.setActionCommand("helmet");
         equipments[0].add(helmet);
         
         chest = new JButton("chest");
         chest.setPreferredSize(new Dimension(130,50));
         chest.addActionListener(this);
         chest.setActionCommand("chest");
         equipments[1].add(chest);
         
         weapon = new JButton("weapon");
         weapon.setPreferredSize(new Dimension(130,50));
         weapon.addActionListener(this);
         weapon.setActionCommand("weapon");
         equipments[2].add(weapon);
         
         shield = new JButton("sheild");
         shield.setPreferredSize(new Dimension(130,50));
         shield.addActionListener(this);
         shield.setActionCommand("shield");
         equipments[3].add(shield);
         
         boot = new JButton("boot");
         boot.setPreferredSize(new Dimension(130,50));
         boot.addActionListener(this);
         boot.setActionCommand("boot");
         equipments[4].add(boot);
         
         ring = new JButton("ring");
         ring.setPreferredSize(new Dimension(130,50));
         ring.addActionListener(this);
         ring.setActionCommand("ring");
         equipments[5].add(ring);
         
         gloves = new JButton("gloves");
         gloves.setPreferredSize(new Dimension(130,50));
         gloves.addActionListener(this);
         gloves.setActionCommand("gloves");
         equipments[6].add(gloves);
         
         belt = new JButton("belt");
         belt.setPreferredSize(new Dimension(130,50));
         belt.addActionListener(this);
         belt.setActionCommand("belt");
         equipments[7].add(belt);
         
         bracers = new JButton("bracers");
         bracers.setPreferredSize(new Dimension(130,50));
         bracers.addActionListener(this);
         bracers.setActionCommand("bracers");
         equipments[8].add(bracers);
         
         Armour ar = characterInventory.getBelt();
         String equipName = null;
         if(ar != null){
             equipName = ar.getName();
             belt.setText(equipName);
         }
         ar = characterInventory.getBoot();
         if(ar != null){
             equipName = ar.getName();
             boot.setText(equipName);
         }
         ar = characterInventory.getBracers();
         if(ar != null){
             equipName = ar.getName();
             bracers.setText(equipName);
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
        
         informationLable.setPreferredSize(new Dimension(400,100));
         
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
         
         bottomPanel.add(informationLable);
         bottomPanel.add(RightBottomPanel);
         
         basicPanel.add(Pane);
         basicPanel.add(bottomPanel);
         
         panel.add(equipmentsPanel);
         panel.add(basicPanel);
         
         add(panel);
         setModalityType(ModalityType.APPLICATION_MODAL);
        setTitle("Inventory Panel");
        setSize(550,800);
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
        String Information = InformationSB.toString();
        return Information;
    }
    
    public String makingInformationForCharacter(){
    	String result = null;
    	
    	StringBuilder SB = new StringBuilder();
    	SB.append("Strength: ");
    	SB.append("Constitution: ");
    	SB.append("Dexterity: ");
    	SB.append("Intelligence: ");
    	SB.append("Charisma: ");
    	SB.append("Wisdom: ");
    	
    	return result;
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
                    if(ri2 == null){
                        Ring ri = (Ring)currentInventory;
                        String name = ring.getName();
                        characterInventory.setRing(ri);
                        Buttons[currentItemNum].setText("");
                        newEmptyNumber = currentItemNum;
                        ring.setText(name);
                        informationLable.setText("");
                        inventoryButtonMap.remove(currentItemNum);
                    }else{
                        Ring ri = (Ring)currentInventory;
                        String name = ring.getName();
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
                    weapon.setText("weapon");
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
                            boot.setText("boot");
                            characterInventory.setBoot(null);
                            break;
                        case "belt":  
                            belt.setText("belt");
                            characterInventory.setBelt(null);
                            break;
                        case "gloves, ":
                            gloves.setText("gloves");
                            characterInventory.setGloves(null);
                            break;
                        case "bracers":
                            bracers.setText("bracers");
                            characterInventory.setBracers(null);
                            break;
                        case "helmet, ":
                            helmet.setText("helmet");
                            characterInventory.setHelmet(null);
                            break;
                        case "chest":
                            chest.setText("chest");
                            characterInventory.setChest(null);
                            break;
                        case "shield":
                            shield.setText("shield");
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
                    ring.setName("ring");
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
                informationLable.setText(makingInformationForOthers(r.getName()));
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
          
          Ring r2 = new Ring();
          r.setName("smallRing");
          r.setItemID(3);
          
          Armour a = new Armour();
          a.setArmourPts(2);
          a.setName("LeatherBoot");
          a.setArmourType("boot");
          
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
          
          Armour a5 = new Armour();
          a5.setArmourPts(2);
          a5.setName("LeatherBelt");
          a5.setArmourType("belt");
          
          Armour a6 = new Armour();
          a6.setArmourPts(7);
          a6.setName("studdedLeatherBracer");
          a6.setArmourType("bracers");
          
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
          System.out.println("current weapon1:" + p.getInventory().getEquippedWeapon().getName());
          InventoryPanel ex = new InventoryPanel(p);
          System.out.println("current weapon2:" + p.getInventory().getEquippedWeapon().getName());
	}
}
