/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * This class has all the characteristics of a Weapon.. This class is a bean class
 * and hence it has getters and setters..
 *
 * @author Kaushik
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Weapon extends Item
{

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(getName());
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
        final Weapon other = (Weapon) obj;
        if (!Objects.equals(getName(), other.getName())) {
            return false;
        }
        return true;
    }
    private String weaponType;
    private int attackRange;
    private int attackPts;

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public int getAttackPts() {
        return attackPts;
    }

    public void setAttackPts(int attackPts) {
        this.attackPts = attackPts;
    }
}