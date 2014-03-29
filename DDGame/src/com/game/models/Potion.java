/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * This class has all the characteristics of a potion.. This class is a bean
 * class and hence it has getters and setters..
 *
 * @author Kaushik
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Potion extends Item{
    private int potionPts;
    private String name;

    public int getPotionPts() {
        return potionPts;
    }

    public void setPotionPts(int potionPts) {
        this.potionPts = potionPts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
