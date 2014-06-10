/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vigia.struts.remitos;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author matts
 */
public class RemitosActionFormBean extends org.apache.struts.action.ActionForm {
//    private String _action;
//    private int _usuarioId;
//    private String _nombreProveedor;
//    private String _rucProveedor;
//    private String _telefonoProvedor;
//    private String[] _detalles;
    private final Map values = new HashMap();

     public String getValues(){
         return this.values.toString();
     }
    public void setValue(String key, Object value) {
        values.put(key, value);
    }

    public Object getValue(String key) {
        return values.get(key);
    }
}
