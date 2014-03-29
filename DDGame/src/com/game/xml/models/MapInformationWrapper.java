/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.xml.models;

import com.game.models.MapInformation;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 *  Map Information wrapper class is used for fetching whole list of maps or we can set list of  maps.
 */
@XmlRootElement(name = "Map")
@XmlAccessorType(XmlAccessType.FIELD)
public class MapInformationWrapper {
    private ArrayList<MapInformation> mapList = new ArrayList<MapInformation>();

    public ArrayList<MapInformation> getMapList() {
        return mapList;
    }

    public void setMapList(ArrayList<MapInformation> mapList) {
        this.mapList = mapList;
    }
}
