/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vigia.struts;

import com.vigia.struts.vo.Conexion;
import com.vigia.struts.vo.UserVO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hsendoa
 */
public class UsuariosAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";
    public List<UserVO> usuariosList = null;
    private String SEARCH_TERM;
    private String COL_NAME;
    private String DIR;
    private int START;
    private int AMOUNT;

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
        /*doGet(request, response);
         mapping.setAttribute("usuariosList");*/
        //request.setAttribute("usuariosList", selectUsuariosInfo());
        RequestDispatcher dispacher = request.getRequestDispatcher("");
        if (dispacher != null) {

        }
        //doGet(request, response);
        HttpSession session = request.getSession();
        UsuariosActionFormBean ufb = (UsuariosActionFormBean) form;
         Logger.getLogger(UsuariosAction.class.getName()).log(Level.SEVERE, ufb.getMetodo()+"---"+ufb.getId()+"-------------------------------");
        if (ufb.getMetodo().equalsIgnoreCase("eliminar")) {
            UserVO usuario = new UserVO();
            usuario.eliminarUsuario(ufb.getId());
            Logger.getLogger(UsuariosAction.class.getName()).log(Level.SEVERE, "eliminando---------"+ufb.getId()+"----------");
            
            
        } else if (ufb.getMetodo().equalsIgnoreCase("guardar")) {
            Logger.getLogger(UsuariosAction.class.getName()).log(Level.SEVERE, "guardar-------------------");
            this.guardar(ufb);
        }

        return mapping.findForward(SUCCESS);
    }

    public void guardar(UsuariosActionFormBean ufb) {
        if (ufb.getPassword().equals(ufb.getPasswordConfirm())) {
            UserVO usuario = new UserVO(ufb.getUsuario(), ufb.getPassword(), ufb.getId());
            if (ufb.getId() == 0) { //guarda nuevo
                if (!usuario.esUsuario()) {//verifica que no sea usuario
                    usuario.crearUsuario();
                } else {
                    usuario.setPassword(ufb.getPasswordConfirm());
                    usuario.modificarUsuario();
                }

            } else {//guarda editado
                usuario.setName(ufb.getUsuario());
                usuario.setPassword(ufb.getPasswordConfirm());
                usuario.modificarUsuario(ufb.getId());
            }
        }
    }

    public void eliminar(UserVO usuario) {
        Logger.getLogger(UsuariosAction.class.getName()).log(Level.SEVERE, "entro en eliminar -------------------");
        
        
        Logger.getLogger(UsuariosAction.class.getName()).log(Level.SEVERE, "creo usuario -------------------");
        
        Logger.getLogger(UsuariosAction.class.getName()).log(Level.SEVERE, usuario.getId()+"--------"+!usuario.esUsuario());
        if (usuario.getId() != 0 && !usuario.esUsuario()) { //guarda nuevo
            Logger.getLogger(UsuariosAction.class.getName()).log(Level.SEVERE, "eliminando -------------------");
            usuario.eliminarUsuario(usuario.getId());
        }
    }
}
