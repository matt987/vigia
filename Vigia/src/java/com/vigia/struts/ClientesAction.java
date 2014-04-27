/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vigia.struts;

import com.vigia.struts.vo.ClienteVO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author matts
 */
public class ClientesAction extends org.apache.struts.action.Action {

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
                ClientesActionFormBean cfb = (ClientesActionFormBean) form;
                if (cfb.getAction().equalsIgnoreCase("buscar")) {
                    List<ClienteVO>clientes = this.buscar(cfb.getQuery());
                    request.setAttribute("clientes", clientes);
                }
                return mapping.findForward(SUCCESS);
    }
    
    
    
         public List<ClienteVO> buscar(String query) throws SQLException {
        
            ClienteVO cliente = new ClienteVO();
            return cliente.selectClienteInfo(query);
        }
        
}
