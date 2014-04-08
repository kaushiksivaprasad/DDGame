/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * GameCharacter class. This class is used for initialize the attributes of GameCharacter.
 * This class serves as the base class for Player class as well as Enemy class.
 * This class also contains getters and setters required to get or set the different variables of this
 * class.
 * @see Player
 * @see Enemy
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class GameCharacter {
    private String type;
    private String imagePath;
    private String name;
    private int health;
    private int constitutionModifier ;
    private int strengthModifier ;
    private int dexterityModifier ;
    private int intelligenceModifier;
    private int charismaModifier;
    private int wisdomModifier;
    private int level;
    private int strength;
    private int constitution;
    private int intelligence;
    private int charisma ;
    private int vitality;
    private Inventory inventory;
    private int dexterity;
    private int wisdom;
    private boolean attackedByPlayer ;
    
    public void setattackedByPlayer(boolean attackedByPlayer){
    	this.attackedByPlayer = attackedByPlayer;
    }
    public boolean getattackedByPlayer(){
    	return attackedByPlayer ;
    }
    
    public int getIntelligenceModifier() {
		return intelligenceModifier;
	}

	public void setIntelligenceModifier(int intelligenceModifier) {
		this.intelligenceModifier = intelligenceModifier;
	}

	public int getCharismaModifier() {
		return charismaModifier;
	}

	public void setCharismaModifier(int charismaModifier) {
		this.charismaModifier = charismaModifier;
	}

	public int getWisdomModifier() {
		return wisdomModifier;
	}

	public void setWisdomModifier(int wisdomModifier) {
		this.wisdomModifier = wisdomModifier;
	}


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }
    

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public int getConstitutionModifier() {
        return constitutionModifier;
    }

    public void setConstitutionModifier(int constitutionModifier) {
        this.constitutionModifier = constitutionModifier;
    }

    public int getStrengthModifier() {
        return strengthModifier;
    }

    public void setStrengthModifier(int strengthModifier) {
        this.strengthModifier = strengthModifier;
    }

    public int getDexterityModifier() {
        return dexterityModifier;
    }

    public void setDexterityModifier(int dexterityModifier) {
        this.dexterityModifier = dexterityModifier;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GameCharacter other = (GameCharacter) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
