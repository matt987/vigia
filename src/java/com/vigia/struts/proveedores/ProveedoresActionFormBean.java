/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vigia.struts.proveedores;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


/**
 *
 * @author matts
 */
public class ProveedoresActionFormBean extends org.apache.struts.action.ActionForm {
    
    private String name;
    
    private int number;
    
    private String _nombre;
        
    private String _direccion;
    
    
    private int _id;
        
    private String _ruc;
    
    private String _telefono;
    
    private String _action;
    
    private String _query;
    
    private String _email;
    
    private String _paginaWeb;

    /**
     *
     */
    public ProveedoresActionFormBean() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public String getEmail() {
        return _email;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    public String getPaginaWeb() {
        return _paginaWeb;
    }

    public void setPaginaWeb(String _paginaWeb) {
        this._paginaWeb = _paginaWeb;
    }

    public String getRuc() {
        return _ruc;
    }

    public void setRuc(String _ruc) {
        this._ruc = _ruc;
    }

    public String getTelefono() {
        return _telefono;
    }

    public void setTelefono(String _telefono) {
        this._telefono = _telefono;
    }    

    public String getQuery() {
        return _query;
    }

    public void setQuery(String _query) {
        this._query = _query;
    }

    public String getAction() {
        return _action;
    }

    public void setAction(String _action) {
        this._action = _action;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }


    public String getDireccion() {
        return _direccion;
    }

    public void setDireccion(String _direccion) {
        this._direccion = _direccion;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param string
     */
    public void setName(String string) {
        name = string;
    }

    /**
     * @return
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param i
     */
    public void setNumber(int i) {
        number = i;
    }    

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getName() == null || getName().length() < 1) {
            errors.add("name", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }
}
