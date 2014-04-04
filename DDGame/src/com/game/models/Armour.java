/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * This class has all the characteristics of an armour.. This class is a bean
 * class and hence it has getters and setters..
 * @author Kaushik
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Armour extends Item{
    private String name;
    private int armourPts;
    private String armourType; 
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArmourPts() {
        return armourPts;
    }

    public void setArmourPts(int armourPts) {
        this.armourPts = armourPts;
    }
    
    public String getArmourType() {
        return armourType;
    }

    public void setArmourType(String armourType) {
        this.armourType = armourType;
    }
}
