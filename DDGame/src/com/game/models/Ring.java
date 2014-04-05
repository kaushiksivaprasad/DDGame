/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class has all the characteristics of a ring.. This class is a bean
 * class and hence it has getters and setters..
 *
 * @author Kaushik
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Ring extends Item{
    private int incHealth;
    private int incArmour;
    private int incAttack;
    public int getIncHealth() {
        return incHealth;
    }

    public void setIncHealth(int incHealth) {
        this.incHealth = incHealth;
    }

    public int getIncArmour() {
        return incArmour;
    }

    public void setIncArmour(int incArmour) {
        this.incArmour = incArmour;
    }

    public int getIncAttack() {
        return incAttack;
    }

    public void setIncAttack(int incAttack) {
        this.incAttack = incAttack;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(getName());
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
        final Ring other = (Ring) obj;
        if (!Objects.equals(getName(), other.getName())) {
            return false;
        }
        return true;
    }
    
}
