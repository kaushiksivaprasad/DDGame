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
 * This class has all the characteristics of a Weapon.. This class is a bean class
 * and hence it has getters and setters..
 *
 * @author Kaushik
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Weapon extends Item
{
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