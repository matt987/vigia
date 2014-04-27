/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vigia.struts.vo;

import java.sql.Timestamp;

/**
 *
 * @author hsendoa
 */
public class BaseVO implements java.io.Serializable {

    //variables globales
    private int _id;
    private Timestamp _timeCreated = null;
    private String _description;
    private String _name;

    //Constructor por Default
    public BaseVO() {
        super();//con super satisface los req de la superclase
        setTimeCreated(new Timestamp(System.currentTimeMillis()));
    }

    // Constructor
    public BaseVO(int id, String name, String desc) {
        this(); //usa para construir el constructor por default
//le agrega nuevas caracteristicas
        this._id = id;
        this._name = name;
        this._description = desc;
    }

    public void setName(String name) {
        this._name = name;
    }

    public void setTimeCreated(Timestamp now) {
        _timeCreated = now;
    }

    public void setDescription(String description) {
        this._description = description;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _description;
    }

    public int getId() {
        return _id;
    }

    public Timestamp getTimeCreated() {
        return _timeCreated;
    }

}
