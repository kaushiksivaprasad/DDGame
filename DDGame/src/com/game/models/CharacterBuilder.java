/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import java.util.LinkedList;

/**
 * This class uses builder pattern to construct Enemy or Player Characters required for the 
 * gameplay. It also includes the respective getters and setters that are required to construct
 * the Enemy or player object.
 * @author Kaushik
 */
public class CharacterBuilder {
    private GameCharacter character = null;
    /**
     * Constructs either an enemy or player object depending on the parameter 
     * passed.
     * @param isEnemy 
     */
    public CharacterBuilder(boolean isEnemy){
        if(isEnemy)
            character = new Enemy();
        else
            character = new Player();
    }
    /**
     * This constructor constructs the builder object from an existing character
     * object
     * @param character 
     */
    public CharacterBuilder(GameCharacter character){
        if(character instanceof Player){
            this.character = new Player();
        }
        else{
            this.character = new Enemy();
        }
        this.character.setCharisma(character.getCharisma());
        this.character.setConstitution(character.getConstitution());
        this.character.setConstitutionModifier(character.getConstitutionModifier());
        this.character.setDexterity(character.getDexterity());
        this.character.setDexterityModifier(character.getDexterityModifier());
        this.character.setHealth(character.getHealth());
        this.character.setImagePath(character.getImagePath());
        this.character.setIntelligence(character.getIntelligence());
        Inventory inv = new Inventory();
        inv.setChest(character.getInventory().getChest());
        inv.setItems(new LinkedList<Item>(character.getInventory().getItems()));
        inv.setEquippedWeapon(character.getInventory().getEquippedWeapon());
        inv.setShield(character.getInventory().getShield());
        this.character.setInventory(inv);
        this.character.setLevel(character.getLevel());
        this.character.setName(character.getName());
        this.character.setStrength(character.getStrength());
        this.character.setStrengthModifier(character.getStrengthModifier());
        this.character.setType(character.getType());
        this.character.setVitality(character.getVitality());
        this.character.setWisdom(character.getWisdom());
//        this.character.setInventory(character.getInventory());
//        this.character.set
    }
    public CharacterBuilder setInventory(Inventory inventory) {
        this.character.setInventory(inventory);
        return this;
    }
    public CharacterBuilder setStrength(int strength) {
        this.character.setStrength(strength);
        return this;
    }
    public CharacterBuilder setConstitution(int constitution) {
        this.character.setConstitution(constitution);
        return this;
    }
    public CharacterBuilder setIntelligence(int intelligence) {
        this.character.setIntelligence(intelligence);
        return this;
    }
    public CharacterBuilder setCharisma(int charisma) {
        this.character.setCharisma(charisma);
        return this;
    }
    public CharacterBuilder setVitality(int vitality) {
        this.character.setVitality(vitality);
        return this;
    }
    public CharacterBuilder setLevel(int level) {
        this.character.setLevel(level);
        return this;
    }
    public CharacterBuilder setConstitutionModifier(int constitutionModifier) {
        this.character.setConstitutionModifier(constitutionModifier);
        return this;
    }
    public CharacterBuilder setStrengthModifier(int strengthModifier) {
        this.character.setStrengthModifier(strengthModifier);
        return this;
    }
    public CharacterBuilder setDexterityModifier(int dexterityModifier) {
        this.character.setDexterityModifier(dexterityModifier);
        return this;
    }
    public CharacterBuilder setImagePath(String imagePath) {
        this.character.setImagePath(imagePath);
        return this;
    }
    public CharacterBuilder setName(String name) {
        this.character.setName(name);
        return this;
    }
    public CharacterBuilder setHealth(int health) {
        this.character.setHealth(health);
        return this;
    }
    public CharacterBuilder setDexterity(int dexterity) {
        this.character.setDexterity(dexterity);
        return this;
    }
    public CharacterBuilder setWisdom(int wisdom) {
        this.character.setWisdom(wisdom);
        return this;
    }
    public CharacterBuilder setType(String type) {
        this.character.setType(type);
        return this;
    }
    /**
     * This method returns the finally constructed character 
     * @return GameCharater 
     */
    public GameCharacter build(){
        return character;
    }
}
