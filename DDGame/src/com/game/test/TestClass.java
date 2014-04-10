package com.game.test;


import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JCheckBox;
import javax.xml.bind.JAXBException;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.game.models.Armour;
import com.game.models.ChestBuilder;
import com.game.models.Configuration;
import com.game.models.GameBean;
import com.game.models.Inventory;
import com.game.models.Item;
import com.game.models.MapInformation;
import com.game.models.Player;
import com.game.models.Potion;
import com.game.models.Ring;
import com.game.models.TileInformation;
import com.game.models.Weapon;
import com.game.ui.views.ChestView;
import com.game.ui.views.InitCharacterAndMapPanel;
import com.game.ui.views.InventoryPanel;
import com.game.ui.views.MapEditor;
import com.game.ui.views.MapPanel;
import com.game.ui.views.WeaponEditorPanel;
import com.game.util.GameUtils;

public class TestClass extends TestCase {

	 public   InitCharacterAndMapPanel panel ;
	   public   Weapon w;
	   public   Player in ;
	   public   Armour a;
	   public   InventoryPanel ip;
	   public   Inventory iin;
	   public   MapEditor me;
	   public   JCheckBox jc;
	   public   WeaponEditorPanel wep;
	   public   MapInformation map;
	   public   MapPanel mapPanel;
	   public   InitCharacterAndMapPanel icamp;
	   public   MapInformation mi;
	   public   Ring ring;
	   public   Potion potion;
	   public   TileInformation tileInformation;
	   public   Ring ri;
	   public   ChestBuilder cb;
	   
	    public TestClass() throws Exception {
	          try {
	            GameBean.doInit();
	        } catch (Exception ex) {
	            Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
	        }
	          panel = new InitCharacterAndMapPanel() ;
	          in = new Player();
	          iin = new Inventory();
	          w = new Weapon();
	          w.setAttackPts(12);
	          w.setAttackRange(2);
	          w.setName("Sword");
	          w.setWeaponType("Meele");
	          a = new Armour();
	          a.setName("armor");
	          a.setModifierInForce("Intelligence");
	          ri = new Ring();
	          ri.setName("ring");
	          ri.setStrengthModifer(2);
	          iin.setEquippedWeapon(w);
	          iin.setChest(a);
	          in.setInventory(iin);
	          in.setLevel(5);
	          jc = new JCheckBox();
	          map = new MapInformation();
	          mi = new MapInformation();
	          ring = new Ring();
	          ring.setName("gold ring");
	          ring.setStrengthModifer(5);
	          ring.setModifierInForce("Strength");
	          potion = new Potion();
	          potion.setItemID(10);
	          potion.setName("small");
	          potion.setPotionPts(40);
	          tileInformation = new TileInformation();
	          tileInformation.setArmour(a);
	          tileInformation.setRing(ring);
	    }
	    
	    @BeforeClass
	    public static void setUpClass() {
	    }
	    public void testTileInformationGetArmour()
	    {
	        Assert.assertTrue(tileInformation.getArmour() != null);
	    }
	    public void testGetSetPotionPts()
	    {
	        Assert.assertTrue(potion.getPotionPts() > -1);
	    }
	    public void testGetPotionName()
	    {
	        Assert.assertTrue(potion.getName() != null);
	    }
	    public void testGetPotionId()
	    {
	        Assert.assertTrue(potion.getItemID() > -1);
	    }
	     public void testGetIncAttack()
	    {
	        Assert.assertTrue(ring.getIncArmour() == 0);
	    }
	    public void testGetIncHealth()
	    {
	        Assert.assertTrue(ring.getName() != null);
	    }
	    public void testUserLocation()
	    {
	        Assert.assertTrue(mi.getUserLocation().size() == 0);
	    }
	    public void testStartPointInfo()
	    {
	        Assert.assertTrue(mi.getStartPointInfo() != null);
	    }
	    public void testGetRow()
	    {
	        Assert.assertTrue(mi.getRows() > -1);
	    }
	    public void testGetColumn()
	    {
	        Assert.assertTrue(mi.getColumns() > -1);
	    }
	     public void testgetPositionOfArmourItem(){
	       Assert.assertTrue(GameUtils.getPositionOfArmourItem("LeatherBoot") == -1);
	    }
	    public void testgetPositionOfRingItem(){
	        
	       Assert.assertTrue(GameUtils.getPositionOfRingItem("Gold Ring1") == -1);
	    }
	    public void testgetPositionOfTreasureItem(){
	        
	       Assert.assertTrue(GameUtils.getPositionOfTreasureItem("Large Loot") == -1);
	    }
	    public void testgetPositionOfWeaponItem(){
	      
	       Assert.assertTrue(GameUtils.getPositionOfWeaponItem("Long Sword") == -1);
	    }
	     public void testgetAllItems(){
	        try {
	           ArrayList<Item> Items1 = (GameUtils.getAllItems(Configuration.PATH_FOR_TREASURES));
	           ArrayList<Item> Items2 = (GameUtils.getAllItems(Configuration.PATH_FOR_RINGS));
	           ArrayList<Item> Items3 = (GameUtils.getAllItems(Configuration.PATH_FOR_POTIONS));
	           ArrayList<Item> Items4 = (GameUtils.getAllItems(Configuration.PATH_FOR_ARMOURS));
	         
	           Assert.assertTrue(Items1.size() > 0);
	           Assert.assertTrue(Items2.size() > 0) ;
	           Assert.assertTrue(Items3.size() > 0) ;
	           Assert.assertTrue(Items4.size() > 0) ;
	         
	        } catch (JAXBException ex) {
	            Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (Exception ex) {
	            Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
	        }
	     }
	    
	    public void testloadingMap() throws Exception{
	        
	     Assert.assertTrue(panel.getCharacterNames()!= null );
	    }
	    public void testgetMapNames() throws Exception{
	     Assert.assertTrue(panel.getMapNames(Configuration.PATH_FOR_MAP) != null );
	    }
	    public void testmakingInformationOfWeapon(){
	     ip = new InventoryPanel(in);
	     ip.dispose();
	     Assert.assertTrue(ip.makingInformationOfWeapon(w) != null);
	    }
	    public void testmakingInformationOfArmor(){
	     ip = new InventoryPanel(in);
	     ip.dispose();
	     Assert.assertTrue(ip.makingInformationOfArmor(a) != null);
	    }
	    
	     public void testputInventoriesIntoItem(){
	     ip = new InventoryPanel(in);
	     ip.dispose();
	     Assert.assertTrue(ip.putInventoriesIntoItem());
	    }
	    
	    public void testmakingInformationForOthers(){
	     ip = new InventoryPanel(in);
	     ip.dispose();
	     Assert.assertTrue(ip.makingInformationForOthers("name") != null);
	    }
	    
	     public void testmakingSimpleDialog() throws IOException{
	     me = new MapEditor();
	     me.dispose();
	     Assert.assertTrue(me.getDialog() != null);
	     }
	     
	     public void testmakingMapPanel() throws IOException{
	     me = new MapEditor();
	     me.dispose();
	     Assert.assertTrue(me.getBottomPanel() != null);
	     }
	     
	     public void testmakingWeaponBox() throws IOException{
	     wep = new WeaponEditorPanel(1, jc);
	     Assert.assertTrue(wep.getComboBox() != null);
	     }
	     
	     public void testmakingWeaponEditorLable() throws IOException{
	     wep = new WeaponEditorPanel(1, jc);
	     Assert.assertTrue(wep.getValidationMess() != null);
	     }
	    
	     public void testgetMapInformation(){
	     map.setColumns(10);
	     map.setRows(5);
	     mapPanel = new MapPanel();
	     mapPanel.getMapInformation(map);
	     Assert.assertTrue(mapPanel.getMapRows() != 0);
	     }
	     
	     public void testMapInformation() throws Exception{
	         icamp = new InitCharacterAndMapPanel();
	         icamp.gamePlayers.add(in);
	         MapInformation Map = GameUtils.fetchParticularMapData(Configuration.PATH_FOR_MAP, "TestMap");
	         Assert.assertTrue(icamp.loadMap(Map) != null);
	     }
	     
	     public void testcal1d10Roll(){
	         int rolls = GameUtils.cal1d10Roll();
	         Assert.assertTrue(rolls> 0 &&  rolls < 11) ;
	     }
	     
	     public void testcal1d8Roll(){
	         int rolls = GameUtils.cal1d8Roll();
	         Assert.assertTrue(rolls> 0 &&  rolls < 9) ;
	     }
	     
	     public void testdistanceCalculation(){
	         int X[] = {1,2};
	         int Y[] = {2,3};
	         Assert.assertTrue( MapPanel.calculateDistance(X, Y) == 2);
	     }
	     
	     public void testabilityModifier(){
	          Assert.assertTrue( MapPanel.calculateAbilityModifier(10) == 0);
	     }
	     
	     public void testinventoryRow(){
	    	 ip = new InventoryPanel(in);
	    	 Assert.assertTrue(ip.getInventoryRow() != 0);
	     }
	     
	     public void testChangeRingValue(){
	    	 cb = new ChestBuilder(tileInformation, in);
	    	 Assert.assertTrue(cb.changeRingValue() != null);
	     }
	     
	     public void testinventoryColumn(){
	    	 ip = new InventoryPanel(in);
	    	 Assert.assertTrue(ip.getInventoryColumn() != 0);
	     }
	     
	     public void testCharacterInventoryinit(){
	    	 ip = new InventoryPanel(in);
	    	 Assert.assertTrue(ip.getCharacterInventory() != null);
	     }
	     
	     public void testIventoryPlayer(){
	    	 ip = new InventoryPanel(in);
	    	 Assert.assertTrue(ip.getPlayer() != null);
	     }
	     
	     public void testInventorySize(){
	    	 ip = new InventoryPanel(in);
	    	 Assert.assertTrue(ip.getInventorySize() != 0);
	     }
	     
	     public void testmakinginformationForRing(){
	    	 ip = new InventoryPanel(in);
	    	 Assert.assertTrue(ip.makingInformationFOrRing(ri) != null);
	     }
	     
	     public void testmakingInformationForCharacter(){
	    	 ip = new InventoryPanel(in);
	    	 Assert.assertTrue(ip.makingInformationForCharacter() != null);
	     }
	     
	     public void testChestBuilder(){
	    	 cb = new ChestBuilder(tileInformation, in);
	    	 Assert.assertTrue(cb.changeArmourValue() != null);
	     }
	    @AfterClass
	    public static void tearDownClass() {
	    }
	    
	    @Before
	    public void setUp() {
	    }
	    
	    @After
	    public void tearDown() {
	    }

	    // TODO add test methods here.
	    // The methods must be annotated with annotation @Test. For example:
	    //
	    // @Test
	    // public void hello() {}
	}

