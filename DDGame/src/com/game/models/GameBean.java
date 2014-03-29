/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import com.game.ui.views.MapEditor;
import com.game.util.GameUtils;
import java.io.File;
import java.util.ArrayList;

/**
 * This class contains all the necessary information needed for the successfull execution of the 
 * MapEditor.
 * @see MapEditor
 * @author Kaushik
 */
public class GameBean {
    public static ArrayList<GameCharacter> enemyDetails = null;
    public static ArrayList<GameCharacter> playerDetails = null;
    public static ArrayList<Item> weaponDetails = null;
    public static ArrayList<Item> ringDetails = null;
    public static ArrayList<Item> potionDetails = null;
    public static ArrayList<Item> armourDetails = null;
    public static ArrayList<Item> treasureDetails = null;
    public static MapInformation mapInfo= null;
    /**
     * This method initializes respective variables, by reading from the respective XML's.
     */
    public static void doInit(){
        File file = new File(Configuration.PATH_FOR_ARMOURS);
        if(file.exists()){
            try {
                GameBean.armourDetails = GameUtils.getAllItems(Configuration.PATH_FOR_ARMOURS);
            } catch (Exception ex) {
                ex.printStackTrace();
                GameBean.armourDetails = new ArrayList<>();
            }
        }
        else{
            GameBean.armourDetails  = new ArrayList<>();
        }
        file = new File(Configuration.PATH_FOR_TREASURES);
        if(file.exists()){
            try {
                GameBean.treasureDetails = GameUtils.getAllItems(Configuration.PATH_FOR_TREASURES);
            } catch (Exception ex) {
                ex.printStackTrace();
                GameBean.treasureDetails = new ArrayList<>();
            }
        }
        else{
            GameBean.treasureDetails  = new ArrayList<>();
        }
        file = new File(Configuration.PATH_FOR_RINGS);
        if(file.exists()){
            try {
                GameBean.ringDetails = GameUtils.getAllItems(Configuration.PATH_FOR_RINGS);
            } catch (Exception ex) {
                ex.printStackTrace();
                GameBean.ringDetails = new ArrayList<>();
            }
        }
        else{
            GameBean.ringDetails  = new ArrayList<>();
        }
        file = new File(Configuration.PATH_FOR_POTIONS);
        if(file.exists()){
            try {
                GameBean.potionDetails = GameUtils.getAllItems(Configuration.PATH_FOR_POTIONS);
            } catch (Exception ex) {
                ex.printStackTrace();
                GameBean.potionDetails = new ArrayList<>();
            }
        }
        else{
            GameBean.potionDetails  = new ArrayList<>();
        }
        file = new File(Configuration.PATH_FOR_WEAPONS);
        if(file.exists()){
            try {
                GameBean.weaponDetails = GameUtils.getAllItems(Configuration.PATH_FOR_WEAPONS);
            } catch (Exception ex) {
                ex.printStackTrace();
                GameBean.weaponDetails = new ArrayList<>();
            }
        }
        else{
            GameBean.weaponDetails  = new ArrayList<>();
        }
        file = new File(Configuration.PATH_FOR_USER_CHARACTERS);
        if(file.exists()){
            try {
                GameBean.playerDetails = GameUtils.getCharacterDetailsFromFile(Configuration.PATH_FOR_USER_CHARACTERS);
            } catch (Exception ex) {
                ex.printStackTrace();
                GameBean.playerDetails = new ArrayList<>();
            }
        }
        else{
            GameBean.playerDetails  = new ArrayList<>();
        }
        file = new File(Configuration.PATH_FOR_ENEMY_CHARACTERS);
        if(file.exists()){
            try {
                GameBean.enemyDetails = GameUtils.getCharacterDetailsFromFile(Configuration.PATH_FOR_ENEMY_CHARACTERS);
            } catch (Exception ex) {
                ex.printStackTrace();
                GameBean.enemyDetails = new ArrayList<>();
            }
        }
        else{
            GameBean.enemyDetails  = new ArrayList<>();
        }
    }
}