/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

/**
 * This Abstract class is used by all the different classes such as Weapon, Armour
 * Potion, Ring, Treasure..
 * @author Kaushik
 */
public abstract class Item {
	private String name;
    private int itemID;
    private int intelligenceModifier;
    private int wisdomModifier;
    private int strengthModifer;
    private int constitutionModifier;
    private int charismaModifer;
    private int dexterityModifer;
    private int armourPts;
    private String modifierInForce = null;
    
    public int getArmourPts() {
		return armourPts;
	}
	public void setArmourPts(int armourPts) {
		this.armourPts = armourPts;
	}
	public String getModifierInForce() {
		return modifierInForce;
	}
	public void setModifierInForce(String modifierInForce) {
		this.modifierInForce = modifierInForce;
	}
	public int getItemID(){
        return itemID;
    }
    public void setItemID(int itemID){
        this.itemID = itemID;
    }
	public int getIntelligenceModifier() {
		return intelligenceModifier;
	}
	public void setIntelligenceModifier(int intelligenceModifier) {
		this.intelligenceModifier = intelligenceModifier;
	}
	public int getWisdomModifier() {
		return wisdomModifier;
	}
	public void setWisdomModifier(int wisdomModifier) {
		this.wisdomModifier = wisdomModifier;
	}
	public int getStrengthModifer() {
		return strengthModifer;
	}
	public void setStrengthModifer(int strengthModifer) {
		this.strengthModifer = strengthModifer;
	}
	public int getConstitutionModifier() {
		return constitutionModifier;
	}
	public void setConstitutionModifier(int constitutionModifier) {
		this.constitutionModifier = constitutionModifier;
	}
	public int getCharismaModifer() {
		return charismaModifer;
	}
	public void setCharismaModifer(int charismaModifer) {
		this.charismaModifer = charismaModifer;
	}
	public int getDexterityModifer() {
		return dexterityModifer;
	}
	public void setDexterityModifer(int dexterityModifer) {
		this.dexterityModifer = dexterityModifer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
}
