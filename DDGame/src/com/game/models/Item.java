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
    private int itemID;
    public int getItemID(){
        return itemID;
    }
    public void setItemID(int itemID){
        this.itemID = itemID;
    }
}
