/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vigia.struts.clientes;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author matts
 */
public class ClientesActionFormBean extends org.apache.struts.action.ActionForm {
    
    private String name;
    
    private int number;
    
    private String _nombre;
    
    private String _apellido;
    
    private String _direccion;
    
    private String _tipo_cliente;
    
    private int _id;
    
    private String _ci;
    
    private String _ruc;
    
    private String _telefono;
    
    private String _action;
    
    private String _query;

    /**
     *
     */
    public ClientesActionFormBean() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public String getCi() {
        return _ci;
    }

    public void setCi(String _ci) {
        this._ci = _ci;
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

    public String getApellido() {
        return _apellido;
    }

    public void setApellido(String _apellido) {
        this._apellido = _apellido;
    }

    public String getDireccion() {
        return _direccion;
    }

    public void setDireccion(String _direccion) {
        this._direccion = _direccion;
    }

    public String getTipo_cliente() {
        return _tipo_cliente;
    }

    public void setTipo_cliente(String _tipo_cliente) {
        this._tipo_cliente = _tipo_cliente;
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
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getName() == null || getName().length() < 1) {
            errors.add("name", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }
}
