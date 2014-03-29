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
import com.game.models.GameCharacter;
import com.game.models.Inventory;
import com.game.models.Item;
import com.game.models.MapInformation;
import com.game.models.Player;
import com.game.models.TileInformation;
import com.game.models.Weapon;
import com.game.util.GameUtils;
import com.game.xml.models.MapInformationWrapper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.apache.commons.lang3.StringUtils;


 
/**
 * MapPanel provides the game playing functionality. It contains movement, Attacks and automatic saving functionality
 * 
 */
public class MapPanel extends JFrame implements ActionListener {
    private JPanel wrapperPanel = new JPanel(new BorderLayout(5, 5));
    private JPanel wrapperPanel2 = new JPanel(new BorderLayout(5, 5));
    private TreeMap<Integer, TileInformation> pathMap;
    static int currentplayer = 0;
    static int previousLocation = 0;
    private int mapRows;

    public int getMapRows() {
        return mapRows;
    }
    private int mapColumns;
    private JPanel mapPanel;
    private int commandCounter;
    private JButton[] tile;
    private TileInformation tileInformation;
    private int numberofPlayers = 0;
    private int numberofEnemys = 0;
    private static boolean firstTime = true;
    static int totalTurns = 0;
    JTextArea fightingInformation;
    TurnPanel turnPanel;
    static ArrayList<Integer> maintainCurrentUser = new ArrayList<Integer>();
    static LinkedHashMap<Integer, Integer> userLocation;
    static LinkedHashMap<Integer, Integer> enemyLocation;
    static LinkedHashMap<Integer, Integer> Activated;
    static LinkedHashMap<Integer, GameCharacter> users;
    static LinkedHashMap<Integer, GameCharacter> usersplayer;
    static ArrayList playersIndex;
    static int tempVariable = 0;
    static LinkedHashMap<Integer, GameCharacter> enemys;
    static ArrayList<Integer> tilesNumber;
    private boolean playerAvailable = false;
    private ImageIcon icon = null;
    static Inventory inventory = null;
    static Player player[] = new Player[4];
    ArrayList winpos = new ArrayList();
    ArrayList position = new ArrayList();
    static String name[] = {"tilu", "Akash", "Kaushik the sivaprasad", "Human", "Viki de drunk", "Roki"};
    boolean checkmonster = false;

    public MapPanel() {
    }

    public MapPanel(MapInformation Map) throws IOException {
        buildMap(Map);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    //here is to get the information about the map
    /**
     * This function used for fetching the mapInformation
     * @param Map 
     */
    public void getMapInformation(MapInformation Map) {
        pathMap = Map.getPathMap();
        mapRows = Map.getRows();
        mapColumns = Map.getColumns();
        userLocation = Map.getUserLocation();
        
        System.out.println("PathMap Info" + pathMap);
        
    }

    /**
     * Function is to build the map panel outline according to the rows and columns, and Initialize map and characters.
     * @param Map
     * @throws IOException 
     */
    public void buildMap(MapInformation Map) throws IOException {
            userLocation = new LinkedHashMap<>();
            getMapInformation(Map);
        
           // Problem can occure with UserLocations
            users = new LinkedHashMap<>();
            usersplayer = new LinkedHashMap<>();
            enemys = new LinkedHashMap<>();
            tilesNumber = new ArrayList<>();
            enemyLocation = new LinkedHashMap<>();
            Activated = new LinkedHashMap<>();
           
        // Don't forget to put it art perfect location. 
        // tilesNumber.add(entry.getValue().getLocation());
        
         
        
        mapPanel = new JPanel();
        mapPanel.setLayout(new GridLayout(mapRows, mapColumns));
        tile = new JButton[mapRows * mapColumns];
        commandCounter = 1;
        playersIndex = new ArrayList();
        for (int x = 0; x < mapRows; x++) {
            for (int y = 0; y < mapColumns; y++) {
                tile[commandCounter - 1] = new JButton();
                (tile[commandCounter - 1]).setActionCommand("" + commandCounter);
                tile[commandCounter - 1].addActionListener(this);
                mapPanel.add(tile[commandCounter - 1]);
                tileInformation = pathMap.get(commandCounter);
                if (tileInformation != null) {
                    tilesNumber.add(tileInformation.getLocation());
                    mapPathPoints();
                    if (tileInformation.isEndTile()) {
                        mapEndPoints();
                    }
                    if (tileInformation.isStartTile()) {
                        
                        
                        System.out.println("Users Locations" + tileInformation.getLocation());
                        mapStartPoints();
                        // 
                        userLocation.put(numberofPlayers, tileInformation.getLocation());
                    //    users.put(numberofPlayers, usersplayer.get(tempVariable));
                     //   enemys.put(numberofPlayers, usersplayer.get(tempVariable));
                       
                        users.put(numberofPlayers, tileInformation.getPlayer());
                        enemys.put(numberofPlayers, tileInformation.getPlayer() );
                        
                        enemyLocation.put(numberofPlayers, tileInformation.getLocation());
                        playersIndex.add(numberofPlayers);
                        numberofPlayers++;
                        tempVariable++;

                    }
                    if (tileInformation.getEnemy() != null) {
                           
                        System.out.println("Enemys Locations" + tileInformation.getLocation());
                      

                        enemyLocation.put(numberofPlayers, tileInformation.getLocation());
                        mapEnemyPoints(tileInformation);
                        enemys.put(numberofPlayers, tileInformation.getEnemy());
                        userLocation.put(numberofPlayers, tileInformation.getLocation());
                        users.put(numberofPlayers, tileInformation.getEnemy());

                        // numberofEnemys++;
                        numberofPlayers++;
                    }
                }
                commandCounter++;
            }
        }
        //   UIManager.installLookAndFeel("SeaGlass", "com.seaglasslookandfeel.SeaGlassLookAndFeel");

        totalTurns = numberofPlayers;
        turnPanel = new TurnPanel(numberofPlayers);

        for (int i = 0; i < numberofPlayers; i++) {
            turnPanel.inventory[i].addActionListener(new InventoryControl());
        }
        //  InformationPanel informationPanel = new InformationPanel();
        //  wrapperPanel2.add(informationPanel.tilePanel,BorderLayout.NORTH


        fightingInformation = new JTextArea(5, 30);
        fightingInformation.setLineWrap(true);
        fightingInformation.setText("Fighting Information:");
        fightingInformation.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(fightingInformation);

        fightingInformation.setCaretPosition(fightingInformation.getDocument().getLength());

        wrapperPanel2.add(scrollPane, BorderLayout.CENTER);
        wrapperPanel2.add(turnPanel, BorderLayout.NORTH);
        wrapperPanel.add(wrapperPanel2, BorderLayout.EAST);

        wrapperPanel.add(mapPanel, BorderLayout.CENTER);
        add(wrapperPanel);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        setMaximizedBounds(env.getMaximumWindowBounds());
        if (users.size() == 1) {
            previousLocation = userLocation.get(users.keySet().iterator().next());
        }
        
  //      System.out.println("UserLocations"+userLocation.toString());
        
   //     System.out.println("EnemyLocations"+userLocation.toString());
        fetchCurrentUser();
        setVisible(true);



    }
    /**
     * This function implements functionality of automatic saving.
     * @param mapNames
     * @throws Exception 
     */
   
    public void savingInformation(String mapNames) throws Exception{
        TreeMap<Integer, TileInformation> pathMapp = new TreeMap<>();
        LinkedHashMap<Integer, Integer> userLoc = new LinkedHashMap<>();
        // users 
        
        
        Iterator iterate = users.keySet().iterator();
        while(iterate.hasNext()){
           TileInformation tileInformation = new TileInformation();
           int playerId =  (int)iterate.next();
           if(playersIndex.contains(playerId)){
               tileInformation.setPlayer((Player) users.get(playerId));
               userLoc.put(playerId, userLocation.get(playerId));
               tileInformation.setLocation(userLocation.get(playerId));
               tileInformation.setStartTile(true);
               
           }else{
               tileInformation.setLocation(userLocation.get(playerId));
               tileInformation.setEnemy(users.get(playerId));
           }    
            pathMapp.put(userLocation.get(playerId), tileInformation);
        }
         
        TileInformation tileInfo;
       System.out.println("CommanCounter" + commandCounter);
        
       for(int i = 1; i <= mapRows * mapColumns; i++){
            tileInfo = pathMap.get(i);
                if (tileInfo != null) {
                    
                    System.out.println("At least not giving null value");
                    
                    if(tileInfo.isEndTile()){
                        pathMapp.put(i, tileInfo);
                         System.out.println("Fianl Tile Drawing"+tileInfo + "at location" + i );
                        
                    }else{
                        if(pathMapp.get(i) == null){
                            tileInfo = new TileInformation();
                            tileInfo.setLocation(i);
                            tileInfo.setStartTile(false);
                            tileInfo.setEndTile(false);
                            pathMapp.put(i, tileInfo);
                            
                            System.out.println("Path Drawing"+tileInfo + "at location" + i );
                        }
                    }
                }
       }
      

       MapInformation mapInfo = new MapInformation();
       mapInfo.setPathMap(pathMapp);
       mapInfo.setUserLocation(userLoc);
       mapInfo.setColumns(mapColumns);
       mapInfo.setRows(mapRows);
       GameBean.mapInfo =   mapInfo    ;
       GameBean.mapInfo.setMapName(mapNames);

       
      MapInformationWrapper  wrapper = GameUtils.readMapInformation(Configuration.PATH_FOR_MAP);
      GameBean.mapInfo.setPathMap(pathMapp);
       
      wrapper.getMapList().add(GameBean.mapInfo);
      GameUtils.writeMapInformation(wrapper, Configuration.PATH_FOR_MAP);
       
    }
    
    /**
     *  Initialize player 
     *   
     */
    public void mapStartPoints() {
        tile[commandCounter - 1].setBackground(startPointColor);
        Player player = pathMap.get(commandCounter).getPlayer();
        System.out.println("Image Path"+player.getImagePath());
        
        if (StringUtils.isNotBlank(player.getImagePath())) {
            try {
                icon = GameUtils.shrinkImage(player.getImagePath(), 60, 60);
            } catch (IOException ex) {
                Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                icon = GameUtils.shrinkImage("Hero1.gif", 60, 60);
            } catch (IOException ex) {
                Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tile[commandCounter - 1].setIcon(icon);
    }

    /**
     *   This function used for end point rendering.
     */
    public void mapEndPoints() {
        tile[commandCounter - 1].setBackground(endPointColor);
    }
    
    /**
     *   This function used for Enemy point rendering.
     */
    
    public void mapEnemyPoints(TileInformation tileInformation) throws IOException {
        
        // Code for including the image 
        
        
        GameCharacter player = pathMap.get(commandCounter).getEnemy();
        if (StringUtils.isNotBlank(player.getImagePath())) {
            try {
                icon = GameUtils.shrinkImage(player.getImagePath(), 60, 60);
            } catch (IOException ex) {
                Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                icon = GameUtils.shrinkImage("Hero1.gif", 60, 60);
            } catch (IOException ex) {
                Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        tile[commandCounter - 1].setBackground(enemyColor);
        tile[commandCounter - 1].setIcon(icon);
        ImageIcon icon = null;
    }

    //here is to build the map panel path points
    public void mapPathPoints() {
        tile[commandCounter - 1].setBackground(pathColor);
    }

    /*
     here is to get the tile's position by maprow and mapcolumn
     the tile's row number is CommandCounter/maprows;
     the tile's column number is CommandCounter%maprows - 1
     */
    
    /**
     * By giving the tile number, it return row number as well as column number.
     * @param commandCounter
     * @return 
     */
    public int[] getTileLocation(int commandCounter) {
        int row;
        int column;
        int position[] = new int[2];
        this.commandCounter = commandCounter;
        if (commandCounter % mapColumns != 0) {
            row = commandCounter / mapColumns;
            column = commandCounter % mapColumns - 1;
            position[0] = row;
            position[1] = column;
            return position;
        } else {
            position[0] = commandCounter / mapColumns - 1;
        }
        position[1] = mapColumns - 1;
        return position;
    }

    public static void main(String[] args) throws IOException {
        try {

            // All intialization variable we should move up to constructor
            
            MapInformation map = new MapInformation();

            
            users = new LinkedHashMap<>();
            usersplayer = new LinkedHashMap<>();
            enemys = new LinkedHashMap<>();
            tilesNumber = new ArrayList<>();
            try {
                map = GameUtils.fetchParticularMapData(Configuration.PATH_FOR_MAP, "akashtest");

                for (int i = 0; i < 4; i++) {
                    player[i] = new Player();
                    player[i].setName(name[i]);
                    player[i].setVitality(20);
                    player[i].setHealth(30);
                //    player[i].setExp(40);
               //     player[i].setMovement(6);
                    player[i].setDexterityModifier(1);
                    player[i].setStrengthModifier(2);
                    player[i].setConstitutionModifier(1);
                    player[i].setLevel(1);
                    Inventory in = new Inventory();
                    // Player p = new Player();
                    LinkedList<Item> iii = new LinkedList<>();
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

                    iii.add(w);
                    iii.add(a);
                    iii.add(a2);
                    iii.add(a3);
                    iii.add(a4);
                    iii.add(a5);
                    iii.add(a6);
                    iii.add(a7);
                    iii.add(a8);
                    iii.add(a9);
                    iii.add(a10);

                    //in.setBoot(a);
                    in.setEquippedWeapon(w2);
                    // in.setRing(r);
                    in.setTotGold(new Long(178972));
                    in.setItems(iii);
                    player[i].setInventory(in);

                    //     position.add(i);
                }
                int user = 0;
                int enemy = 0;
                LinkedHashMap<Integer, Integer> userLocation = new LinkedHashMap<>();
                enemyLocation = new LinkedHashMap<>();
                Activated = new LinkedHashMap<>();
                TreeMap<Integer, TileInformation> tileInfo = map.getPathMap();
                for (Map.Entry<Integer, TileInformation> entry : tileInfo.entrySet()) {

                    // Don't forget to include tilesNumber List
                    
                    tilesNumber.add(entry.getValue().getLocation());
                    if (entry.getValue().getEnemy() != null) {
                        //     enemyLocation.put(enemy, entry.getValue().getLocation());
                        entry.getValue().getEnemy().setDexterityModifier(calculateAbilityModifier(cal4d6Roll()));
                        entry.getValue().getEnemy().setStrengthModifier(calculateAbilityModifier(cal4d6Roll()));
                        entry.getValue().getEnemy().setConstitutionModifier(calculateAbilityModifier(cal4d6Roll()));
                        entry.getValue().getEnemy().setLevel(1);
                    //    entry.getValue().getEnemy().setMovement(6);
                        // enemys.put(enemy, entry.getValue().getEnemy());
                        System.out.println("Health of enemy" + entry.getValue().getEnemy().getHealth());

                        enemy++;
                    }

                    if (entry.getValue().isStartTile()) {
                        //      userLocation.put(user, entry.getValue().getLocation());
                        entry.getValue().setPlayer(player[user]);
                   //     player[user].setPosition(entry.getValue().getLocation());
                        usersplayer.put(user, player[user]);
                        user++;
                    }
                }

                map.setUserLocation(userLocation);
                new MapPanel(map);
                //          System.out.println("The data" + tilesNumber);
            } catch (Exception ex) {
                Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception e) {
        }
    }

    /**
     * @deprecated 
     * @param userPosition
     * @param player
     * @return 
     */
    public boolean monsterAttack(int userPosition, int player) {
        boolean dead = false;
        for (int i = 0; i < numberofEnemys; i++) {
            if (dead == false) {
                if (enemyLocation.get(i) != null) {
                    int enemyLoc = enemyLocation.get(i);
                    int enemyLocXY[] = getTileLocation(enemyLoc);
                    int userLocXY[] = getTileLocation(userPosition);

                    int distance = calculateDistance(enemyLocXY, userLocXY);
                    if (distance <= 6) {

                        int attackBonus;
                        if (isAdjacent(userPosition, enemyLoc)) {
                            attackBonus = calculateAttackBonus(i, "enemy", "melle");
                        } else {
                            attackBonus = calculateAttackBonus(i, "enemy", "ranged");
                        }
                        int armorClass = calculateArmorClass(player, "user");

                        int callRoll = cal1d20Roll();
                        fightingInformation.append("\n Enemy's is Attack bonus  is : " + attackBonus + " \n Roll 1d20 Dice :" + callRoll + "\n" + " Players' Armour class (AC) is :" + armorClass + "\n");
                        System.out.println("User is under attack Attack bonus  of" + i + " :: " + attackBonus + " Armour class" + armorClass);
                        if ((callRoll + attackBonus) > armorClass) {
                            // Decrease the health

                            fightingInformation.append("Player is under Attack" + "\n");
                            fightingInformation.append("Damage Point =(Rolling 1d8 Dice + attackBonus ) = ");
                            int damagePoint = cal1d8Roll() + attackBonus;
                            fightingInformation.append("" + damagePoint);
                            users.get(player).setHealth(users.get(player).getHealth() - (damagePoint));
                            System.out.println("User" + player + "'s health is " + users.get(player).getHealth());
                            fightingInformation.append("\n Players hit Point" + users.get(player).getHealth() + "\n");
                        }
                        if (users.get(player).getHealth() < 0) {
                            // The player is dead
                            dead = true;
                        }
                        if (dead == true) {
                            System.out.println("The user : " + player + " is dead");

                            fightingInformation.append("Player " + player + "is dead now ! \n");
                            System.out.println("Pos of user" + userPosition);
                            tile[userLocation.get(currentplayer) - 1].setIcon(null);
                            users.remove((Object) player);
                            userLocation.remove((Object) player);
                            turnPanel.inventory[player].setVisible(false);

                            System.out.println("Monster Attacking kill" + userLocation);
                            if (users.size() == 0) {
                                System.out.println("Game is over my friend");
                                JOptionPane.showMessageDialog(null, "Game is over !");
                                fightingInformation.append("Game Over !");
                            }
                        }
                    }
                }
            }
        }

        return dead;
    }

    /**
     * This function used for calculating the distance between two tiles.
     * @param X
     * @param Y
     * @return 
     */
    public static int calculateDistance(int X[], int Y[]) {
        int distance = Math.abs(X[0] - Y[0]) + Math.abs(X[1] - Y[1]);
        return distance;
    }

    /**
     * This function return whether player won the match or not.
     * @param Location
     * @return 
     */
    boolean checkWinning(int Location) {

        if (pathMap.get(Location) != null) {
            if (pathMap.get(Location).isEndTile()) {
                if (playersIndex.size() != 0) {
                    if (users.size() - playersIndex.size() == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        boolean sameplace = false;
        int sameplacePos = 0;
        boolean sameplacehero = false;
        boolean canMove = false;
        boolean deadPlayer = false;

        System.out.println(userLocation);

        /* 
         if(userLocation.size() == 1)
         setColorOfCurrentPlayer(e.getActionCommand());
         else
         setColorOfCurrentPlayer("");
       
         */
        int actionLocation = Integer.parseInt(e.getActionCommand());
        int actionLocationXY[] = getTileLocation(Integer.parseInt(e.getActionCommand()));
        int userPosition = userLocation.get(currentplayer);
        int userPositionXY[] = getTileLocation(userPosition);
        // Check for winning Position
        // Monster Around you will attack on you.
        // You can move or not
        // If you can, two possibilities 1) You hit the monster 2) Move to empty place. (Activate Monster)
        boolean finalWin = checkWinning(actionLocation);
        // Find Can Move;
        int distance = calculateDistance(userPositionXY, actionLocationXY);
        //users.get(currentplayer).getMovement()
        if (distance >6 ) {
            canMove = false;
        } else if (!tilesNumber.contains(actionLocation)) {
            canMove = false;
        } else if (pathMap.get(actionLocation) != null && pathMap.get(actionLocation).isEndTile() && finalWin == false) {
            canMove = false;
        } else {
            canMove = true;
        }
        System.out.println(canMove);
        previousLocation = userLocation.get(currentplayer);

        // I change this code in below block
        // && !enemyLocation.containsValue(actionLocation)
/*
         if (userLocation.size() == 1) {
         if (canMove == true && !enemyLocation.containsValue(actionLocation)) {
         setColorOfCurrentPlayer(e.getActionCommand());
         }
         } else {
         setColorOfCurrentPlayer("");
         }

         */
        /*
         if (canMove) {
         deadPlayer = monsterAttack(actionLocation, currentplayer);
         fightingInformation.setCaretPosition(fightingInformation.getDocument().getLength());
       
         } else {
         deadPlayer = monsterAttack(userPosition, currentplayer);
         fightingInformation.setCaretPosition(fightingInformation.getDocument().getLength());
       
         }*/
        if (deadPlayer == false) {
            if (finalWin == false) {
                
                /*
                 int Monsterplacing = 0;
                 for (int index = 0; index < numberofEnemys; index++) {
                 if (sameplace == false) {
                 if (enemyLocation.get(index) != null) {
                 Monsterplacing = enemyLocation.get(index);
                 if (Monsterplacing == actionLocation) {
                 sameplace = true;
                 sameplacePos = index;
                 }
                 }
                 }
                 }
                 */
                if (enemyLocation.containsValue(actionLocation)) {
                    sameplace = true;
                    Iterator iterator = enemyLocation.keySet().iterator();
                    while (iterator.hasNext()) {
                        int index = (int) iterator.next();
                        int location = enemyLocation.get(index);
                        if (location == actionLocation) {
                            sameplacePos = index;
                            break;
                        }
                    }
                }



                if (sameplacehero || !canMove) {
                    if (sameplacehero) {
                        System.out.println("You cannot move on hero");
                    } else {
                        System.out.println("You cannot Move there");
                    }
                } else {
                    if (sameplace) {
                        // This is a same place as Monsters.
                        int attackBonus;
                        if (isAdjacent(sameplacePos, userPosition)) {
                            attackBonus = calculateAttackBonus(currentplayer, "user", "melle");
                        } else {
                            attackBonus = calculateAttackBonus(currentplayer, "user", "ranged");
                        }
                        int armorClass = calculateArmorClass(sameplacePos, "enemy");

                        int rolls = cal1d20Roll();
                        if(!playersIndex.contains(sameplacePos)){
                            System.out.println("\n\n Player's is Attack bonus  is : " + attackBonus + " \n Roll 1d20 Dice : " + rolls + "\n" + " Enemy's Armour class (AC) is :" + armorClass + "\n");
                            System.out.println("User Position" + userPosition);
                            fightingInformation.append("\n\n Player's is Attack bonus  is : " + attackBonus + " \n Roll 1d20 Dice : " + rolls + "\n" + " Enemy's Armour class (AC) is :" + armorClass + "\n");
                        }
                        else{
                            System.out.println("\n\n Enemys's is Attack bonus  is : " + attackBonus + " \n Roll 1d20 Dice : " + rolls + "\n" + " Player's Armour class (AC) is :" + armorClass + "\n");
                            //System.out.println("\n\n Enemys's is Attack bonus  is : " + attackBonus + " \n Roll 1d20 Dice : " + rolls + "\n" + " Player's Armour class (AC) is :" + armorClass + "\n");
                            fightingInformation.append("\n\n Enemys's is Attack bonus  is : " + attackBonus + " \n Roll 1d20 Dice : " + rolls + "\n" + " Player's Armour class (AC) is :" + armorClass + "\n");
                        }

                        /// Coloring the Monsters

                        if (!playersIndex.contains(currentplayer)) {
                            tile[userPosition - 1].setBackground(enemyColor);
                        }

                        System.out.println("Rolls " + rolls);
                        if (rolls + attackBonus > armorClass) {
                            // Decrease the health
                             int damagePoint = cal1d8Roll() + attackBonus;
                            if(!playersIndex.contains(sameplacePos)){
                                 fightingInformation.append("Enemy is under Attack" + "\n");
                           }else{
                                 fightingInformation.append("Player is under Attack" + "\n");
                            }
                            
                            fightingInformation.append("Damage Point =(Rolling 1d8 Dice + attackBonus ) = ");
                          
                            fightingInformation.append("" + damagePoint);
                            enemys.get(sameplacePos).setHealth(enemys.get(sameplacePos).getHealth() - (damagePoint));
                            users.get(sameplacePos).setHealth(users.get(sameplacePos).getHealth() - (damagePoint));

                            // System.out.println("Now health of sameplacePos is" + enemys.get(sameplacePos).getHealth());
                            fightingInformation.append("\n Hit point of " + sameplacePos + " is :" + enemys.get(sameplacePos).getHealth() + "\n");
                            fightingInformation.setCaretPosition(fightingInformation.getDocument().getLength());


                        }
                        if (enemys.get(sameplacePos).getHealth() < 0) {
                            // The player is dead
                              if(!playersIndex.contains(sameplacePos)){
                                  System.out.println("Enemy " + sameplacePos + " is dead now \n");
                              }else{
                                   System.out.println("Player " + sameplacePos + " is dead now \n");
                              }
                           
                            //   turnPanel.inventory[sameplacePos].setVisible(false);
                            turnPanel.remove(turnPanel.inventory[sameplacePos]);


                            enemys.remove((Object) sameplacePos);
                            enemyLocation.remove((Object) sameplacePos);
                            users.remove((Object) sameplacePos);
                            userLocation.remove((Object) sameplacePos);

                            if (playersIndex.contains(sameplacePos)) {
                                playersIndex.remove((Object) sameplacePos);
                                if (playersIndex.size() == 0) {
                                    JOptionPane.showMessageDialog(null, "Game Over");
                                }
                            }

                            turnPanel.revalidate();
                            turnPanel.repaint();
                            wrapperPanel2.revalidate();
                            wrapperPanel2.repaint();

                            Activated.remove((Object) sameplacePos);
                            tile[actionLocation - 1].setBackground(Color.BLUE);
                            int hitting = cal1d10Roll() + users.get(currentplayer).getConstitutionModifier();
                            users.get(currentplayer).setHealth(users.get(currentplayer).getHealth() + (hitting));
                            fightingInformation.append("\n Level up procedure \n (Roll 1d10 dice + Add constitution Modifier) ="+ hitting);
                            
                            if(!playersIndex.contains(currentplayer))
                            fightingInformation.append(" Enemy " + currentplayer + " hit point has been increased by :" + users.get(currentplayer).getHealth() + "\n");
                            else
                            fightingInformation.append(" player " + currentplayer + " hit point has been increased by :" + users.get(currentplayer).getHealth() + "\n");


                            users.get(currentplayer).setLevel(users.get(currentplayer).getLevel() + 1);
                            enemys.get(currentplayer).setLevel(enemys.get(currentplayer).getLevel() + 1);
                            fightingInformation.append("Level increased of " + currentplayer + " is :" + users.get(currentplayer).getLevel() + "\n");
                            fightingInformation.setCaretPosition(fightingInformation.getDocument().getLength());
                            try {
                                savingInformation(JOptionPane.showInputDialog("Enter The name of Map"));
                            } catch (Exception ex) {
                                Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }                   
                    } else {
                        // This place where users can move
                     //   tile[actionLocation - 1].setIcon(new ImageIcon("Images//Hero1.gif"));
                       
                         ImageIcon icon = null;
                            try {
                                icon = GameUtils.shrinkImage(users.get(currentplayer).getImagePath(), 60, 60);
                            } catch (IOException ex) {
                                Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            tile[actionLocation - 1].setIcon(icon);
                        
                        tile[userPosition - 1].setIcon(null);
                        if (!playersIndex.contains(currentplayer)) {
                            tile[actionLocation - 1].setBackground(enemyColor);
                           
                        }
                        if (deadPlayer == false) {
                            userLocation.put(currentplayer, Integer.parseInt(e.getActionCommand()));
                            enemyLocation.put(currentplayer, Integer.parseInt(e.getActionCommand()));
                            // checkActivatedMonsters(actionLocationXY);
                        }
                    }
                }

            } else {

                JOptionPane.showMessageDialog(null, "You won the match");
            }
        }
        //   currentplayer++;
        //   currentplayer = currentplayer % numberofPlayers;

        if (users.size() != 0) {
            fetchCurrentUser();
        }

    }

    /**
     * Provides Sorting Functionality
     * @param unsortMap
     * @param order
     * @return 
     */
    public static Map<Integer, Integer> sortByComparator(Map<Integer, Integer> unsortMap, final boolean order) {

        List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1,
                    Map.Entry<Integer, Integer> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public HashMap sortTheOrderofPlayer() {

        HashMap map = new HashMap();

        Iterator iterator = users.keySet().iterator();
        while (iterator.hasNext()) {
            int key = (int) iterator.next();
            int points = cal1d20Roll() + users.get(key).getDexterityModifier();
            map.put(key, points);
        }
        return map;
    }

    /**
     *    This function used for fetching the current user (whose turn comes)
     */
    public void fetchCurrentUser() {

        int tempCurrentplayer = currentplayer;
        boolean nullChecking = false;
        if (users.size() == 1) {
            fightingInformation.append("\n Initiate Rolling for turns :\n ");

            currentplayer = users.keySet().iterator().next();
            fightingInformation.append("\n Turn : " + currentplayer);
            setColorOfCurrentPlayer(previousLocation, currentplayer, true);

        } else {

            if (maintainCurrentUser.size() == 0) {

                fightingInformation.append("\n Initiate Rolling for turns :\n ");
                fightingInformation.append("\n rolling 18d + Dextirity Modifier :\n ");
                HashMap map = sortTheOrderofPlayer();
                map = (HashMap) sortByComparator(map, true);
                Iterator iterator = map.keySet().iterator();

                fightingInformation.append(map.keySet().toString() + "\n");
                while (iterator.hasNext()) {
                    maintainCurrentUser.add((int) iterator.next());

                }
                currentplayer = maintainCurrentUser.remove(0);

                if (firstTime == true) {

                    System.out.println("Ya i am here" + currentplayer);
                    setColorOfCurrentPlayer(currentplayer, currentplayer, false);
                    firstTime = false;
                } else {

                    System.out.println("CurrentPlaye" + currentplayer + "Previous Location" + previousLocation);
                    setColorOfCurrentPlayer(previousLocation, currentplayer, true);

                }

                //      System.out.println("Turn Got By" + currentplayer);
                //     System.out.println("Maintained Users" + maintainCurrentUser);


            } else {


                currentplayer = maintainCurrentUser.remove(0);

                while (userLocation.get(currentplayer) == null && (nullChecking == false)) {
                    if (maintainCurrentUser.size() >= 1) {
                        currentplayer = maintainCurrentUser.remove(0);
                    } else {
                        nullChecking = true;
                        System.out.println("--------------Recursion ------");

                    }
                }

                if (nullChecking == false) {
                    System.out.println("CurrentPlaye" + currentplayer + "Previous Location" + previousLocation);

                    setColorOfCurrentPlayer(previousLocation, currentplayer, true);
                } else {
                    fetchCurrentUser();
                }

                //    System.out.println("Turn Got By" + currentplayer);
                //    System.out.println("Maintained Users" + maintainCurrentUser);

            }

        }

        turnPanel.setCurrentPlayer(currentplayer);
        turnPanel.setLevel(users.get(currentplayer).getLevel());
        turnPanel.setHitPoints(users.get(currentplayer).getHealth());
        //    Inventory inventory = users.get(currentplayer).getInventory();

        /*
         System.out.println("CurrentPlayer" +currentplayer+ "Inventory " +inventory.getEquippedWeapon().getName());
         if(inventory.getBoot() != null)
         System.out.println("currnet boot:" + inventory.getBoot().getName());
         if(inventory.getBracers()!=null)
         System.out.println("currnet boot:" + inventory.getBracers().getName());
         if(inventory.getChest() != null)
         System.out.println("currnet boot:" + inventory.getChest().getName());
         if(inventory.getGloves() != null)
         System.out.println("currnet boot:" + inventory.getGloves().getName());
         if(inventory.getShield() != null)
         System.out.println("currnet boot:" + inventory.getShield().getName());
         if(inventory.getHelmet() != null)
         System.out.println("currnet boot:" + inventory.getHelmet().getName());
    
         */
        /*
         currentplayer = (currentplayer + 1) % numberofPlayers;
         if (userLocation.size() >= 2) {
         while (!userLocation.containsKey(currentplayer)) {
         currentplayer = (currentplayer + 1) % numberofPlayers;
         System.out.println("Current Player" + currentplayer);
         }
         } else if (userLocation.size() == 1) {
         currentplayer = userLocation.keySet().iterator().next();
         System.out.println("Only one guy is there");
         } else {
         System.out.println("Nothing");
         }*/
    }

    public void checkActivatedMonsters(int playerPosition[]) {

        for (int x = 0; x < numberofEnemys; x++) {
            if (enemyLocation.get(x) != null) {
                int getplace = enemyLocation.get(x);
                int caldistance = calculateDistance(getTileLocation(getplace), playerPosition);
                if (caldistance <= 6) {
                    tile[getplace - 1].setBackground(Color.RED);
                    Activated.put(x, getplace);
                    if (checkmonster == false) {
                        checkmonster = true;


                    }
                }
            }
        }
    }

    /**
     * checking for attacker and defender are in adjacent cell or not.
     * @param targetCell
     * @param sourceCell
     * @return 
     */
    public boolean isAdjacent(int targetCell, int sourceCell) {
        if (((sourceCell - 1) == targetCell) || ((sourceCell - mapColumns) == targetCell) || ((sourceCell + mapColumns) == targetCell)) {
            return true;
        }
        if ((sourceCell % mapColumns) != 0) {
            if ((sourceCell + 1) == targetCell) {
                return true;
            }
        }
        return false;
    }

    // Optimized
    /*
     public static int Roll(int a, int b){
     Random r = new Random();
     int ans = r.nextInt(b);
     while (ans == 0) {
     ans = r.nextInt(b);
     }
     return (ans*a);
     }
    
     */
    /**
     * This function is used for rendering the background of current player
     * @param previousPlayer
     * @param currentPlayer
     * @param check 
     */
    public void setColorOfCurrentPlayer(int previousPlayer, int currentPlayer, boolean check) {


        // May be problem can occure here

        if (check == false) {

            // Check here
            tile[userLocation.get(previousPlayer) - 1].setBackground(pathColor);


        } else {

            if (tile[previousPlayer - 1].getBackground().equals(enemyColor)) {
                tile[previousPlayer - 1].setBackground(enemyColor);
            } else {
                tile[previousPlayer - 1].setBackground(pathColor);
            }

        }
        tile[userLocation.get(currentPlayer) - 1].setBackground(Color.GREEN);



        /*
         if (userLocation.size() >= 1) {
         if (userLocation.get(currentplayer % numberofPlayers) != null) {
         tile[userLocation.get(currentplayer % numberofPlayers) - 1].setBackground(pathColor);
         }
         }
         int tempCurrentPlayer = 0;
         // boolean process = false;
         if (userLocation.size() > 1) {
         tempCurrentPlayer = (currentplayer + 1) % numberofPlayers;

         while (!userLocation.containsKey(tempCurrentPlayer)) {
         tempCurrentPlayer = (tempCurrentPlayer + 1) % numberofPlayers;
         System.out.println("TemCurrent player" + tempCurrentPlayer + "Users" + userLocation);
         }
         tile[userLocation.get(tempCurrentPlayer) - 1].setBackground(Color.GREEN);

         } else if (userLocation.size() == 1 && !str.equals("")) {
         tempCurrentPlayer = Integer.parseInt(str);
         tile[tempCurrentPlayer - 1].setBackground(Color.GREEN);

         } else {
         System.out.println("It is okey with me");
         }
         */
    }

    public static int cal1d8Roll() {
        Random r = new Random();
        int ans = r.nextInt(8);
        while (ans == 0) {
            ans = r.nextInt(8);
        }
        return (ans);

    }

    public static int cal1d10Roll() {
        Random r = new Random();
        int ans = r.nextInt(10);
        while (ans == 0) {
            ans = r.nextInt(10);
        }
        return (ans);

    }

    public static int cal4d6Roll() {

        Random r = new Random();
        int ans = r.nextInt(6);
        while (ans == 0) {
            ans = r.nextInt(6);
        }
        return (ans * 4);

    }

    public int cal1d20Roll() {
        Random r = new Random();
        int ans = r.nextInt(20);
        while (ans == 0) {
            ans = r.nextInt(20);
        }
        return ans;
    }
 
    /**
     * This class is used for calculating Armor class of defender.
     * @param playerID
     * @param type
     * @return 
     */
    public int calculateArmorClass(int playerID, String type) {
        if (type.equals("user")) {
            GameCharacter player = users.get(playerID);
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
        } else {
            GameCharacter player = enemys.get(playerID);
            
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
            
            
            
            
            
            // (10 + ArmorModifier + ShieldModifier)
             return (10 + ArmorModifier + ShieldModifier);
        }
    }

    /**
     * This function is used for calculating Attack bonus of Attacker
     * @param playerID
     * @param playerType
     * @param WeaponType
     * @return 
     */
    public int calculateAttackBonus(int playerID, String playerType, String WeaponType) {

        GameCharacter player;
        int baseAttackBonus = calculateBaseAttackBonus(playerID, playerType);
        if (playerType.equals("enemy")) {
            player = enemys.get(playerID);
        } else {
            player = users.get(playerID);
        }

        if (WeaponType.equals("melle")) {
            return baseAttackBonus + player.getStrengthModifier();
        } else {
            return baseAttackBonus + player.getDexterityModifier();
        }

    }
             
    /**
     * Calculating the base attack bonus which decide how many attack attackers can do.
     * @param playerID
     * @param type
     * @return 
     */
    public int calculateBaseAttackBonus(int playerID, String type) {

        int level;
        if (type.equals("user")) {
            GameCharacter player = users.get(playerID);
            level = player.getLevel();
        } else {
            GameCharacter player = enemys.get(playerID);
            level = player.getLevel();
        }
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

    //  public int calculateshieldModifier(Player player) {
    // According to inventories calculate it
    //  }
    public static int calculateAbilityModifier(int abilityScore) {
        if (abilityScore % 2 == 0) {
            return (abilityScore - 10) / 2;
        } else {
            return ((abilityScore - 11) / 2);
        }
    }
    
    /**
     *  This class is used for Inventory Control. (Generate Inventorypanel to change the inventory of player)
     */

    public class InventoryControl implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            StringTokenizer str = new StringTokenizer(ae.getActionCommand(), " ");
            str.nextToken();
            int index = Integer.parseInt(str.nextToken());
            System.out.println(index);
            inventory = users.get(index).getInventory();
            GameCharacter player = users.get(index);
            InventoryPanel inventoryPanel = new InventoryPanel( player);
//            System.out.println("currnet weapon:" + inventory.getEquippedWeapon().getName());
            
            
        }
    }
}