/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import java.awt.Color;

/**
 * This class contains all the configuration information required for the Game.
 * @author Kaushik
 */
public class Configuration {
    public static final int MAX_HEALTH = 1000;
    public static int MAX_ATTACK_RNGE = 0;
    public static int MAX_MOVEMENT = 0;
    public static final int MAX_ATTACK = 50;
    public static final int MAX_STRENGTH = 10;
    public static final int MAX_DEXTERITY = 10;
    public static final int MAX_VITALITY = 10;
    public static final int MAX_WISDOM = 10;
    public static final int MAX_LEVEL = 40;
    public static final int MAX_ARMOUR = 10;
    public static final int MAX_EXP = 50;
    public static final int MAX_INVENTORY = 10;
    public static final int ENEMIESTOBEKILLED = 3;
    public static final String PATH_FOR_USER_CHARACTERS = "./User Character/user.xml" ;
    public static final String PATH_FOR_ENEMY_CHARACTERS = "./Enemy Character/enemy.xml";
    public static final String PATH_FOR_ITEMS = "./Items/items.xml";
    public static final String PATH_FOR_WEAPONS = "./Items/weapons.xml";
    public static final String PATH_FOR_RINGS = "./Items/rings.xml";
    public static final String PATH_FOR_TREASURES = "./Items/treasure.xml";
    public static final String PATH_FOR_POTIONS = "./Items/potion.xml";
    public static final String PATH_FOR_ARMOURS = "./Items/armour.xml";
    public static final String PATH_FOR_IMAGES = "./Images/";
    public static String pathForUserProgress = null;
    public static String weaponTypes[] = {"Melee Weapon","Ranged Weapon"};
    public static final String PATH_FOR_MAP = "./Map/map.xml";
    public static final int INVENTORY_ROW = 14;
    public static final int INVENTORY_COLUMN = 4;
    public static final int INVENTORY_SIZE = INVENTORY_ROW * INVENTORY_COLUMN;
    public static final Color  pathColor = new Color(64, 128, 128);
    public static final Color  startPointColor = new Color(80,104,112);
    public static final Color  endPointColor = new Color(113,152,39);
    public static final Color  enemyColor = new Color(134,57,67);
    public static String armourTypes[] = {"helmet", "chest", "shield", "boot", "gloves", "belt", "bracers"};
    public static final int defaultLevel = 1;
    public static final int defaultTreasureValue = 1000;
    public static enum modifiers{
    	Intelligence,Wisdom,Armor,Strength,Constitution,Charisma,Dexterity,Attack, damage;
    };
    public static String[] modifiersList = {"Intelligence","Wisdom","Armor","Strength","Constitution","Charisma","Dexterity","Attack", "damage"};
    public static String[] weaponModifier = {"Attack", "damage"};
    public static String[] ringModifier = {"Wisdom","Armor","Strength","Constitution","Charisma"};
    public static enum ringMod{
    	Wisdom,Armor,Strength,Constitution,Charisma
    };
    public static String[] armorModifier = {"Intelligence","Wisdom","Armor","Strength","Constitution","Charisma","Dexterity"};
}
