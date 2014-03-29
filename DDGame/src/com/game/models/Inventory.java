/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import java.util.LinkedList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * The object of this class is used by both the Enemy and Player. This class 
 * contains all the information regarding the contents that might be present in a 
 * characters inventory. This class is a bean class and hence it has getters
 * and setters..
 * @author Kaushik
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Inventory {
    private Weapon equippedWeapon;
    private Armour helmet;
    private Armour chest;
    private Armour gloves;
    private Armour bracers;
    private Armour belt;
    private Armour shield;
    private Ring ring;
    private Armour boot;
    private Long totGold;
    private LinkedList<Item> items;
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public Armour getHelmet() {
        return helmet;
    }

    public void setHelmet(Armour helmet) {
        this.helmet = helmet;
    }

    public Armour getChest() {
        return chest;
    }

    public void setChest(Armour chest) {
        this.chest = chest;
    }
    
    public Armour getShield() {
        return shield;
    }

    public void setShield(Armour shield) {
        this.shield = shield;
    }
    
    public Armour getGloves() {
        return gloves;
    }

    public void setGloves(Armour gloves) {
        this.gloves = gloves;
    }
    
    public Armour getBracers() {
        return bracers;
    }

    public void setBracers(Armour bracers) {
        this.bracers = bracers;
    }
    
    public Armour getBelt() {
        return belt;
    }

    public void setBelt(Armour belt) {
        this.belt = belt;
    }
    
    public Ring getRing() {
        return ring;
    }

    public void setRing(Ring ring) {
        this.ring = ring;
    }

    public Armour getBoot() {
        return boot;
    }

    public void setBoot(Armour boot) {
        this.boot = boot;
    }

    public Long getTotGold() {
        return totGold;
    }

    public void setTotGold(Long totGold) {
        this.totGold = totGold;
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    public void setItems(LinkedList<Item> items) {
        this.items = items;
    }
    
}
