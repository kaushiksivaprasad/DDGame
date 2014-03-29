/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.ui.views;

import com.game.models.*;
import com.game.util.GameUtils;
import com.game.xml.models.MapInformationWrapper;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This class is used to generate the UI of character and map panel
 * and select characters and map to play game.
 * @author Hanxin
 */
public class InitCharacterAndMapPanel extends JFrame implements ActionListener{
    private String mapDirectory;
    private String characterDirectory;
    private JList mapList;
    private JList characterList;
    private Label warningLabel; 
    
    private String mapName = null; //is used to save the name of map which is selected by user
    
    public ArrayList<GameCharacter> collectionOfPlayers = new ArrayList(); //is used to load players
    public MapInformation selectedMap = new MapInformation();
    public MapInformationWrapper totalMaps = new MapInformationWrapper(); 
    public ArrayList<Player> gamePlayers = new ArrayList();   //is used to save players selected by user
    
    public InitCharacterAndMapPanel() throws Exception{
        mapDirectory=Configuration.PATH_FOR_MAP;
        characterDirectory=Configuration.PATH_FOR_USER_CHARACTERS;
        initUI();
    }
    
    /**
     * this method is used to generate UI for character and map choose panel
     */
    public void initUI() throws Exception {
        JPanel basicPanel = new JPanel();
        basicPanel.setLayout(new BoxLayout(basicPanel, BoxLayout.Y_AXIS));
        
        JPanel panel = new JPanel();
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        
        JPanel submitPanel = new JPanel();
        JPanel creatPanel = new JPanel();
        
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        
        JPanel characterPanel = new JPanel();
        characterPanel.setLayout(new BorderLayout());
        characterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        
        JPanel mapsPanel = new JPanel();
        mapsPanel.setLayout(new BorderLayout());
        mapsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Label selectCharacterLabel = new Label ("SelectCharacter");
        Label selectMapsLabel = new Label ("SelectMaps");
        warningLabel = new Label ("Please choose characters !");
        warningLabel.setVisible(true);
        
        String[] totalNames1 = getCharacterNames();
        characterList = new JList(totalNames1);
        characterList.setEnabled(false);


        
        
        String[] totalNames2 = getMapNames(mapDirectory);
        mapList = new JList(totalNames2);
        mapList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mapList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String name = (String) mapList.getSelectedValue();
                    System.out.println(name);
                    try {
                        MapInformation M = GameUtils.fetchParticularMapData(mapDirectory, name);
                        if(M.getUserLocation().size() != 0){
                            characterList.setEnabled(false);
                            warningLabel.setText("this map has been used ! Can't choose character again !");
                        }else{
                            characterList.setEnabled(true);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(InitCharacterAndMapPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
        
 
    
        
        JScrollPane characterPane = new JScrollPane();
        characterPane.getViewport().add(characterList);
        characterPane.setPreferredSize(new Dimension(150, 100));
        
        JScrollPane MapPane = new JScrollPane();
        MapPane.getViewport().add(mapList);
        MapPane.setPreferredSize(new Dimension(150, 100));
        
        JButton SubmitButton = new JButton("Submit");
        SubmitButton.setPreferredSize(new Dimension(120, 30));
        SubmitButton.setActionCommand("Submit");
        SubmitButton.addActionListener(this);
        submitPanel.add(SubmitButton);
        
        JButton creatPlayer = new JButton("CreatPlayer");
        creatPlayer.setPreferredSize(new Dimension(120, 30));
        creatPlayer.setActionCommand("CreatPlayer");
        creatPlayer.addActionListener(this);
        creatPanel.add(creatPlayer);
        
        leftPanel.add(selectCharacterLabel);
        leftPanel.add(characterPane);
        
        rightPanel.add(selectMapsLabel);
        rightPanel.add(MapPane);
        
        panel.add(leftPanel);
        panel.add(rightPanel);
        
        buttonPanel.add(submitPanel);
        buttonPanel.add(creatPanel);
        
        panel.add(buttonPanel);
        
        basicPanel.add(panel);
        basicPanel.add(warningLabel);
        
        add(basicPanel);
        
        setTitle("SelectCharatersAndMap");
        setSize(400, 350);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /**
     * this method is used to switch interface and load map and character
     * at same time check whether the user has choosed the right value
     * @param event 
     */
    @Override
     public void actionPerformed(ActionEvent event){
         String command = event.getActionCommand();
        if("Submit".equals(command)){ 
        if(mapList.getSelectedValue() != null){
            mapName = (String) mapList.getSelectedValue();
            
            MapInformation M = null;
            try {
                M = GameUtils.fetchParticularMapData(mapDirectory, mapName);
            } catch (Exception ex) {
                Logger.getLogger(InitCharacterAndMapPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
             System.out.println("M.getUserLocation().size()" + M.getUserLocation().size());
            if( characterList.getSelectedValue() != null && M.getUserLocation().size() == 0){
            List o = characterList.getSelectedValuesList();
                Player GC = new Player();
                for(int i =0;i<o.size();i++){
                        Iterator it = collectionOfPlayers.iterator();
                        while(it.hasNext()){
                            GC = (Player)it.next();
                            System.out.println(GC.getName());
                            System.out.println(o.get(i));
                            if(GC.getName() == o.get(i)){
                                gamePlayers.add(GC);
                                System.out.println("Level of Player: " + GC.getLevel());
                            }
                        }           
           }
            System.out.println(gamePlayers.size());
                try{
                        MapInformation finalMapInformation = loadMap(M);
                        MapPanel Map = new MapPanel(finalMapInformation);
                    } catch (Exception ex) {
                        Logger.getLogger(InitCharacterAndMapPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                this.dispose();
            }else if(M.getUserLocation().size() != 0){
                try{
                MapInformation finalMapInformation = GameUtils.fetchParticularMapData(mapDirectory, mapName);
                MapPanel Map = new MapPanel(finalMapInformation);
                }catch (Exception ex){
                    Logger.getLogger(InitCharacterAndMapPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(characterList.getSelectedValue() == null && M.getUserLocation().size() == 0){
                characterList.setEnabled(true);
                warningLabel.setText("Please select characters!");
            }
        }else{
            warningLabel.setText("please choose map and characters !");
        }
        }else{
             try {
            GameBean.doInit();
            if (GameBean.weaponDetails.size() > 0) {
                JDialog frame = new JDialog();
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.add(new CharacterEditor());
                frame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                frame.setName("Frame");
                frame.pack();
                frame.setVisible(true);
            }
            String[] totalNames1 = getCharacterNames();
            DefaultListModel Model =  new DefaultListModel();
            characterList.setModel(Model);
            for(int i = 0;i<totalNames1.length;i++){
                System.out.println(totalNames1[i]);
                Model.addElement(totalNames1[i]);
            }
        } catch (Exception ex) {
//            Logger.getLogger(PlayerEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
     /**
      * this method is used to load maps
      * @param NameOfMap the name of map
      * @return return an object of mapinformation
      */
    public MapInformation loadMap(MapInformation M) throws Exception{
        TreeMap<Integer,TileInformation>  NewpathMap = new TreeMap<>();
        LinkedHashMap<Integer,Integer> NewuserLocation = new LinkedHashMap<>();
        MapBuilder mb = new MapBuilder();
        if(M.getUserLocation().size() == 0){
        System.out.println("don't have userlocation");
        ArrayList<Integer> S = M.getStartPointInfo();
        System.out.println("StartPointInfo" + S.size());
        Iterator it = S.iterator();
        int i =0;
        while(it.hasNext() && i<gamePlayers.size()){
            TreeMap HM = M.getPathMap();
            System.out.println(HM);
            int j =(int)it.next();
            TileInformation T = (TileInformation)HM.get(j);//it.next is the start point i is the user number
            T.setPlayer(gamePlayers.get(i));
            HM.put(j, T);
            NewpathMap=HM;
            NewuserLocation.put(i,j);
            i++;
        }
        M.setPathMap(NewpathMap);
        M.setUserLocation(NewuserLocation);
        System.out.println(M.getUserLocation().size());
        M = mb.BuildMap(M);
        System.out.println(NewuserLocation);
        System.out.println(NewpathMap);
        return M;
        }else{
            System.out.println("have userlocation");
        return M;}
    }
    
    /**
     * this method is used to load characters and get names
     * @return a collection of characters
     * @throws java.lang.Exception
     */
    public String[] getCharacterNames () throws Exception{
        collectionOfPlayers = GameUtils.getCharacterDetailsFromFile(characterDirectory);
        String[] names = new String[collectionOfPlayers.size()];
        Iterator it = collectionOfPlayers.iterator();
        int i=0;
        while(it.hasNext()){
            GameCharacter GC = new GameCharacter();
            GC = (GameCharacter)it.next();
            names[i] = GC.getName();
            i++;
        }
        return names;
    }
    
    
    
    
    /**
     * this method is used to get name of file
     * @param strPath the entire directory of the file
     * @return names of total file in that floder 
     */
    public String[] getMapNames(String strPath) throws Exception{
        totalMaps = GameUtils.readMapInformation(strPath);
        ArrayList<MapInformation> M = totalMaps.getMapList();
        String[] fileName = new String[M.size()];
        Iterator it = M.iterator();
        int i = 0;
        while(it.hasNext()){
            MapInformation Ma = (MapInformation) it.next();
            fileName[i] = Ma.getMapName();
            i++;
        }
        return fileName;
    }
    
    public static void main(String[] args) throws Exception {
        /*InitCharacterAndMapPanel i;
        try {
            i = new InitCharacterAndMapPanel();
            MapInformation M = i.loadMap("TestMap");
            System.out.println("finished?");
        } catch (Exception ex) {
            Logger.getLogger(InitCharacterAndMapPanel.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        InitCharacterAndMapPanel icam = new InitCharacterAndMapPanel();

	}
}