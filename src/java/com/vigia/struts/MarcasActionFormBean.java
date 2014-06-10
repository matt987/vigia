/*
 *Action form tiene todos los valores cargados en el formulario JSP, permite comunicar al jsp con los java
 */

package com.vigia.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author hsendoa
 */
public class MarcasActionFormBean extends org.apache.struts.action.ActionForm {
    
    private String name;
    private int number;
    private String _marca;
    private String _descripcion;
    private int _id;
    private String _metodo;

/*metodos getters and setters*/
    public String getMarca() {
        return _marca;
    }

    public void setMarca(String _marca) {
        this._marca = _marca;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getMetodo() {
        return _metodo;
    }

    public void setMetodo(String _metodo) {
        this._metodo = _metodo;
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
     * contructor por defecto
     */
    public MarcasActionFormBean() {
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
