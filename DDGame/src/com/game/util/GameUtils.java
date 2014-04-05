 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.util;

import com.game.models.Armour;
import com.game.models.Configuration;
import com.game.models.GameBean;
import com.game.models.GameCharacter;
import com.game.models.Item;
import com.game.models.MapInformation;
import com.game.models.Potion;
import com.game.models.Ring;
import com.game.models.Treasure;
import com.game.models.Weapon;
import com.game.xml.models.CharacterWrapper;
import com.game.xml.models.ItemWrapper;
import com.game.xml.models.MapInformationWrapper;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *this class contains all useful utils for game. 
 * @author Kaushik
 */
public class GameUtils {

    /**
     * this method is used to get mapinformation
     * @param mapName name of the map
     * @return an object of mapInformation catain all information of the map
     */
    public static MapInformation getMapInformation(String mapName) {
        return new MapInformation();
    }
    
    /**
     * this method is used to roll a 8 aspects dice
     * @return the random number between 1 and 8
     */
    public static int cal1d8Roll() {
        Random r = new Random();
        int ans = r.nextInt(9);
        while (ans == 0) {
            ans = r.nextInt(9);
        }
        return (ans);

    }

    /**
     * this method is used to roll a 10 aspects dice
     * @return the random number between 1 and 10
     */
    public static int cal1d10Roll() {
        Random r = new Random();
        int ans = r.nextInt(11);
        while (ans == 0) {
            ans = r.nextInt(11);
        }
        return (ans);

    }

    /**
     * this method is used to roll 4 dices which has 6 aspects
     * @return the sum of 4 dices
     */
    public static int cal4d6Roll() {
        int j = 0;
        int leastVal = 0;
        int ans = 0;
        while(j < 4){
            Random r = new Random();
            int temp = r.nextInt(7);
            while (temp == 0) {
                temp = r.nextInt(7);
            }
            if(j == 3){
                if(temp > leastVal)
                {
                    ans -= leastVal;
                    ans += temp;
                }
            }
            else{
                ans += temp;
                if(temp <= leastVal)
                    leastVal = temp;
            }
            j++;
        }
         return ans;
    }

    /**
     * this method is used to roll a 20 aspects dice
     * @return the random number between 1 and 20
     */
    public static int cal1d20Roll() {
        Random r = new Random();
        int ans = r.nextInt(21);
        while (ans == 0) {
            ans = r.nextInt(21);
        }
        return ans;
    }
    
    /**
     * this method is used to calculate the ability modifier
     * @param abilityScore the ability score of the player
     * @return the modifier
     */
    public static int calculateAbilityModifier(int abilityScore) {
        if (abilityScore % 2 == 0) {
            return (abilityScore - 10) / 2;
        } else {
            return ((abilityScore - 11) / 2);
        }
    }
    
    /**
     * this method is used to get character information from file
     * @param fileName the name of file which contain character information
     * @return an arraylist contain character information
     * @throws JAXBException
     * @throws Exception 
     */
    public static ArrayList<GameCharacter> getCharacterDetailsFromFile(String fileName) throws JAXBException, Exception {
        ArrayList<GameCharacter> charList = null;
        try {
            JAXBContext context = JAXBContext.newInstance(CharacterWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            CharacterWrapper wrapper = (CharacterWrapper) um.unmarshal(new File(fileName));
            charList = wrapper.getCharacter();
        } catch (JAXBException e) {
            System.out.println("GameUtils : getCharacterDetailsFromFile() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : getCharacterDetailsFromFile() : Execption occured : " + e);
            throw new Exception(e);
        }
        return charList;
    }

    /**
     * 
     * @param fileName the name of file which contain item information
     * @return an arraylist which contain item information
     * @throws JAXBException
     * @throws Exception 
     */
    public static ArrayList<Item> getAllItems(String fileName) throws JAXBException, Exception {
        ArrayList<Item> itemList = null;
        try {
            JAXBContext context = JAXBContext.newInstance(ItemWrapper.class,Ring.class,Weapon.class,Armour.class,Treasure.class,Potion.class);
            Unmarshaller um = context.createUnmarshaller();
            ItemWrapper wrapper = (ItemWrapper) um.unmarshal(new File(fileName));
            itemList = wrapper.getItem();
        } catch (JAXBException e) {
            System.out.println("GameUtils : getAllItems() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : getAllItems() : Execption occured : " + e);
            throw new Exception(e);
        }
        return itemList;
    }

    /**
     * this method is used to save character's information into xml file
     * @param characters what we need to save in the file
     * @param fileName the name of file which will keep character information
     * @throws JAXBException
     * @throws Exception 
     */
    public static void writeCharactersToXML(ArrayList<GameCharacter> characters, String fileName) throws JAXBException, Exception {
        CharacterWrapper wrapper = new CharacterWrapper();
        wrapper.setCharacter(characters);
        try {
            JAXBContext context = JAXBContext.newInstance(CharacterWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(wrapper, new File(fileName));
        } catch (JAXBException e) {
            System.out.println("GameUtils : writeCharactersToXML() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : writeCharactersToXML() : Execption occured : " + e);
            throw new Exception(e);
        }
    }
    /**
     * this method is used to save item's information into xml file
     * @param items what we need to save in the file
     * @param fileName the name of file which will keep item information
     * @throws JAXBException
     * @throws Exception 
     */
    public static void writeItemsToXML(ArrayList<Item> items, String fileName) throws JAXBException, Exception {
        ItemWrapper wrapper = new ItemWrapper();
        wrapper.setItem(items);
        try {
            JAXBContext context = JAXBContext.newInstance(ItemWrapper.class,Item.class,Ring.class,Weapon.class,Armour.class,Treasure.class,Potion.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(wrapper, new File(fileName));
        } catch (JAXBException e) {
            System.out.println("GameUtils : writeItemsToXML() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : writeItemsToXML() : Execption occured : " + e);
            throw new Exception(e);
        }
    }
    /**
    *this method is used to update the information of character
    * @param characters the information we need to update
    * @param fileName the name of file which will keep character information
    * @throws JAXBException
    * @throws Exception 
    */
    public static void updateCharacterDetails(ArrayList<GameCharacter> characters, String fileName) throws JAXBException, Exception {
        ArrayList<GameCharacter> charList = null;
        try {
            charList = getCharacterDetailsFromFile(fileName);
            charList.addAll(characters);
            writeCharactersToXML(charList, fileName);
        } catch (JAXBException e) {
            System.out.println("GameUtils : updateCharacterDetails() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : updateCharacterDetails() : Execption occured : " + e);
            throw new Exception(e);
        }
    }
    
    /**
    *this method is used to update the information of item
    * @param items the information we need to update
    * @param fileName the name of file which will keep item information
    * @throws JAXBException
    * @throws Exception 
    */
    public static void updateItemsXml(ArrayList<Item> items, String fileName) throws JAXBException, Exception {
        ArrayList<Item> itemList = null;
        try {
            itemList = getAllItems(fileName);
            itemList.addAll(items);
            writeItemsToXML(itemList, fileName);
        } catch (JAXBException e) {
            System.out.println("GameUtils : updateItemsXml() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : updateItemsXml() : Execption occured : " + e);
            throw new Exception(e);
        }
    }
    
    /**
     * this method is used to shrink image
     * @param fileName the name of image  need to be shrinked
     * @param width  width we want
     * @param height height we want
     * @return image has been shrinked
     * @throws IOException 
     */
    public static ImageIcon shrinkImage(String fileName, int width, int height) throws IOException {
        StringBuilder imagePath = new StringBuilder(Configuration.PATH_FOR_IMAGES);
        imagePath.append(fileName);
        System.out.println(imagePath.toString());
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imagePath.toString()));
        } catch (IOException e) {
            System.out.println("GameUtils : shrinkImage() : Exception Occured : " + e);
            throw new IOException(e);
        }
        return new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
    
    /**
     * this method is used to get the position of weapon Item
     * @param name the name of weapon which we want to know its position
     * @return the position of weapon
     */
    public static int getPositionOfWeaponItem(String name) {
        int position = -1;
        if (GameBean.weaponDetails != null) {
            for (int i = 0; i < GameBean.weaponDetails.size(); i++) {
                Weapon weapon = (Weapon) GameBean.weaponDetails.get(i);
                if (weapon.getName().equalsIgnoreCase(name)) {
                    position = i;
                    break;
                }
            }
        }
        return position;
    }
    
    /**
     * this method is used to get the position of ring Item
     * @param name the name of ring which we want to know its position
     * @return the position of ring
     */
    public static int getPositionOfRingItem(String name) {
        int position = -1;
        if (GameBean.ringDetails != null) {
            for (int i = 0; i < GameBean.ringDetails.size(); i++) {
                Ring ring = (Ring) GameBean.ringDetails.get(i);
                if (ring.getName().equalsIgnoreCase(name)) {
                    position = i;
                    break;
                }
            }
        }
        return position;
    }
    
    /**
     * this method is used to get the position of Armour Item
     * @param name the name of Armour which we want to know its position
     * @return the position of Armour
     */
    public static int getPositionOfArmourItem(String name) {
        int position = -1;
        if (GameBean.armourDetails != null) {
            for (int i = 0; i < GameBean.armourDetails.size(); i++) {
                Armour armour = (Armour) GameBean.armourDetails.get(i);
                if (armour.getName().equalsIgnoreCase(name)) {
                    position = i;
                    break;
                }
            }
        }
        return position;
    }

    /**
     * this method is used to get the position of Treasure Item
     * @param name the name of Treasure which we want to know its position
     * @return the position of Treasure
     */
    public static int getPositionOfTreasureItem(String name) {
        int position = -1;
        if (GameBean.treasureDetails != null) {
            for (int i = 0; i < GameBean.treasureDetails.size(); i++) {
                Treasure treasure = (Treasure) GameBean.treasureDetails.get(i);
                if (treasure.getName().equalsIgnoreCase(name)) {
                    position = i;
                    break;
                }
            }
        }
        return position;
    }
    
    /**
     * this method is used to get the position of Potion Item
     * @param name the name of Potion which we want to know its position
     * @return the position of Potion
     */
    public static int getPositionOfPotionItem(String name) {
        int position = -1;
        if (GameBean.potionDetails != null) {
            for (int i = 0; i < GameBean.potionDetails.size(); i++) {
                Potion potion = (Potion) GameBean.potionDetails.get(i);
                if (potion.getName().equalsIgnoreCase(name)) {
                    position = i;
                    break;
                }
            }
        }
        return position;
    }
    
    /**
     * this method is used to save map information into file
     * @param wrapper the xml element for map
     * @param fileName the name of file which will keep map information
     * @throws Exception 
     */
    public static void writeMapInformation(MapInformationWrapper wrapper, String fileName) throws Exception {
        try {
            JAXBContext context = JAXBContext.newInstance(MapInformationWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(wrapper, new File(fileName));
        } catch (JAXBException e) {
            System.out.println("GameUtils : writeMapInformation() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : writeMapInformation() : Execption occured : " + e);
            throw new Exception(e);
        }
    }
    
    /**
     * this method is used to read map information from file
     * @param fileName the name of file which has map information
     * @return an object include map information
     * @throws Exception 
     */
    public static MapInformationWrapper readMapInformation(String fileName) throws Exception {
        MapInformationWrapper mapInfo = null;
        try {
            JAXBContext context = JAXBContext.newInstance(MapInformationWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            mapInfo = (MapInformationWrapper) um.unmarshal(new File(fileName));
        } catch (JAXBException e) {
            System.out.println("GameUtils : writeMapInformation() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : writeMapInformation() : Execption occured : " + e);
            throw new Exception(e);
        }
        return mapInfo;
    }

    /**
     * this method is used to read particular map information from file
     * @param fileName the name of file which has map information
     * @param mapName the name of this particular map
     * @return an object include map information
     * @throws Exception 
     */
    public static MapInformation fetchParticularMapData(String fileName, String mapName) throws Exception {
        try {
            JAXBContext context = JAXBContext.newInstance(MapInformationWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            MapInformationWrapper wrapper = (MapInformationWrapper) um.unmarshal(new File(fileName));
            ArrayList<MapInformation> list = wrapper.getMapList();
            for (MapInformation info : list) {
                if (info.getMapName().equalsIgnoreCase(mapName)) {
                    return info;
                }
            }

        } catch (JAXBException e) {
            System.out.println("GameUtils : fetchParticularMapData() : Execption occured : " + e);
            throw new JAXBException(e);
        } catch (Exception e) {
            System.out.println("GameUtils : fetchParticularMapData() : Execption occured : " + e);
            throw new Exception(e);
        }
        return null;
    }
}
