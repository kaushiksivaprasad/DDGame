/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.game.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * This class contains all the information needed by the Map. This class's object will be
 * persisted to the XML file. This class contains all the getters and setters as this class is
 * a bean class. 
 * @author Kaushik
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MapInformation {
    private String mapName;
    @XmlElement(name = "Path")
    private TreeMap<Integer,TileInformation> pathMap = new TreeMap<Integer,TileInformation>();
    @XmlElement(name = "UserLocation")
    private LinkedHashMap<Integer,Integer> userLocation = new LinkedHashMap<Integer, Integer>();
    private ArrayList<Integer> startPointInfo = new ArrayList<Integer>();
    private int rows;
    private int columns;
    public TreeMap<Integer, TileInformation> getPathMap() {
        return pathMap;
    }

    public void setPathMap(TreeMap<Integer, TileInformation> pathMap) {
        this.pathMap = pathMap;
    }

    public LinkedHashMap<Integer, Integer> getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(LinkedHashMap<Integer, Integer> userLocation) {
        this.userLocation = userLocation;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.mapName);
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
        final MapInformation other = (MapInformation) obj;
        if (!Objects.equals(this.mapName, other.mapName)) {
            return false;
        }
        return true;
    }

    public ArrayList<Integer> getStartPointInfo() {
        return startPointInfo;
    }

    public void setStartPointInfo(ArrayList<Integer> startPointInfo) {
        this.startPointInfo = startPointInfo;
    }
    public String toString(){
        return mapName;
    }
}
