/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vigia.struts;

import com.vigia.struts.vo.MarcaVO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author hsendoa
 */
public class MarcasAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        RequestDispatcher dispacher = request.getRequestDispatcher("");
        if (dispacher != null) {

        }
        //metodo que maneja la accion en de acuerdo al hidden metodo que es indicado
        HttpSession session = request.getSession();
        MarcasActionFormBean mfb = (MarcasActionFormBean) form;
        if (mfb.getMetodo().equalsIgnoreCase("eliminar")) {
            /*para eliminar crea una marca con el id a eliminar y llama al eliminar de la marca*/
            MarcaVO marca = new MarcaVO(mfb.getId());
            this.eliminar(marca);
        } else if (mfb.getMetodo().equalsIgnoreCase("guardar")) {
            /*para nuevos registros o ediciones llama al metodo guardar*/
            this.guardar(mfb);
        }
        return mapping.findForward(SUCCESS);
    }
     public void guardar(MarcasActionFormBean mfb) {
        
            MarcaVO marca = new MarcaVO(mfb.getMarca());
            if (mfb.getId() == 0) { //guarda nuevo
                if (!marca.esMarca()) {//verifica que no sea usuario
                    marca.setDescripcion(mfb.getDescripcion());
                    marca.crearMarca();
                } else {
                    marca.setDescripcion(marca.getDescripcion());
                    marca.setMarca(marca.getMarca());
                    marca.modificarMarca(marca.getId());
                }

            } else {//guarda editado
                marca.setName(mfb.getMarca());
                marca.setDescripcion(mfb.getDescripcion());
                marca.modificarMarca(mfb.getId());
            }
        
    }

    public void eliminar(MarcaVO marca) {
        if (marca.getId() != 0 && !marca.esMarca()) { //guarda nuevo
            marca.eliminarMarca(marca.getId());
        }
    }
    
}
