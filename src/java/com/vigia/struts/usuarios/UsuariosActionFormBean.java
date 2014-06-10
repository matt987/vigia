/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vigia.struts.usuarios;

/**
 *
 * @author hsendoa
 */
public class UsuariosActionFormBean extends org.apache.struts.action.ActionForm {

    private String name;
    private int number;
    private String _usuario;
    private String _password;
    private String _passwordConfirm;
    private int _id;
    private String _metodo;
    private String _query;
    private String _action;
    private String _login;
    private int _empleadoId;

    public int getEmpleadoId() {
        return _empleadoId;
    }

    public void setEmpleadoId(int _empleadoId) {
        this._empleadoId = _empleadoId;
    }

    public String getLogin() {
        return _login;
    }

    public void setLogin(String _login) {
        this._login = _login;
    }

    public String getQuery() {
        return _query;
    }

    public String getAction() {
        return _action;
    }

    public void setAction(String _action) {
        this._action = _action;
    }

    public void setQuery(String _query) {
        this._query = _query;
    }

    public String getMetodo() {
        return _metodo;
    }

    public void setMetodo(String _metodo) {
        this._metodo = _metodo;
    }

    public String getUsuario() {
        return _usuario;
    }

    public void setUsuario(String usuario) {
        this._usuario = usuario;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    public String getPasswordConfirm() {
        //Logger.getLogger(UsuariosActionFormBean.class.getName()).log(Level.INFO, _passwordConfirm + " getPassConf", "-get confirmacion");

        return _passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
       // Logger.getLogger(UsuariosActionFormBean.class.getName()).log(Level.INFO, passwordConfirm + "-confirmacion", "-confirmacion");

        this._passwordConfirm = passwordConfirm;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
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
     *
     */
    public UsuariosActionFormBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    /* public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
     ActionErrors errors = new ActionErrors();
     if (getName() == null || getName().length() < 1) {
     errors.add("name", new ActionMessage("error.name.required"));
     // TODO: add 'error.name.required' key to your resources
     }
     return errors;
     }*/
}
