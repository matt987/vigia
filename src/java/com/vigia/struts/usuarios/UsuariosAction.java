/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vigia.struts.usuarios;

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
        ActionForward forward = null;
        /*doGet(request, response);
         mapping.setAttribute("usuariosList");*/
        //request.setAttribute("usuariosList", selectUsuariosInfo());
        RequestDispatcher dispacher = request.getRequestDispatcher("");
        if (dispacher != null) {

        }
        //doGet(request, response);
        HttpSession session = request.getSession();
        UsuariosActionFormBean ufb = (UsuariosActionFormBean) form;
        Logger.getLogger(UsuariosAction.class.getName()).log(Level.SEVERE, ufb.getMetodo() + "---" + ufb.getId() + "-------------------------------");
        if (ufb.getAction().equalsIgnoreCase("eliminar")) {
            UserVO usuario = new UserVO();
            usuario.eliminarUsuario(ufb.getId());
            forward = mapping.findForward("eliminar");

        } else if (ufb.getAction().equalsIgnoreCase("crear")) {
            Logger.getLogger(UsuariosAction.class.getName()).log(Level.SEVERE, "guardar-------------------");
            this.guardar(ufb);
            forward = mapping.findForward("crear");
        } else if (ufb.getAction().equalsIgnoreCase("'buscar'")) {
            List<UserVO> usuarios = this.buscar(ufb.getQuery());

            request.setAttribute("usuarios", usuarios);
            forward = mapping.findForward("buscar");
        }

        return forward;
    }

    public void guardar(UsuariosActionFormBean ufb) {
        if (ufb.getPassword().equals(ufb.getPasswordConfirm())) {
            UserVO usuario = new UserVO(ufb.getLogin(), ufb.getPassword(), ufb.getId(), ufb.getEmpleadoId());
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

    public List<UserVO> buscar(String query) throws SQLException {
        Logger.getLogger(UserVO.class.getName()).log(Level.INFO, "buscando" + query);
        UserVO usuario = new UserVO();
        return usuario.selectUsuariosInfo(query);
    }

    public void eliminar(UserVO usuario) {
        Logger.getLogger(UsuariosAction.class.getName()).log(Level.SEVERE, "entro en eliminar -------------------");

        Logger.getLogger(UsuariosAction.class.getName()).log(Level.SEVERE, "creo usuario -------------------");

        Logger.getLogger(UsuariosAction.class.getName()).log(Level.SEVERE, usuario.getId() + "--------" + !usuario.esUsuario());
        if (usuario.getId() != 0 && !usuario.esUsuario()) { //guarda nuevo
            Logger.getLogger(UsuariosAction.class.getName()).log(Level.SEVERE, "eliminando -------------------");
            usuario.eliminarUsuario(usuario.getId());
        }
    }
}
