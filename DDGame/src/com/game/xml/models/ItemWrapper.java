/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.xml.models;

import com.game.models.Armour;
import com.game.models.Item;
import com.game.models.Potion;
import com.game.models.Ring;
import com.game.models.Treasure;
import com.game.models.Weapon;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 *  ItemWrapper class is used for setting or getting all the items
 */
@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Ring.class, Potion.class, Treasure.class, Weapon.class, Armour.class})
public class ItemWrapper {
    public ArrayList<Item> item = new ArrayList<Item>();

    public ArrayList<Item> getItem() {
        return item;
    }

    public void setItem(ArrayList<Item> item) {
        this.item = item;
    }
}
