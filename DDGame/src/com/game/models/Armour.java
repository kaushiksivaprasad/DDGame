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
    private String armourType; 
    public String getArmourType() {
        return armourType;
    }

    public void setArmourType(String armourType) {
        this.armourType = armourType;
    }
}
