/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class has all the characteristics of a treasure.. This class is a bean class
 * and hence it has getters and setters..
 *
 * @author Kaushik
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Treasure extends Item
{
    private int value;
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
