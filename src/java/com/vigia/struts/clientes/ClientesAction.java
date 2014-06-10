/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vigia.struts.clientes;

import com.vigia.struts.vo.ClienteVO;
import java.sql.SQLException;
import java.util.List;
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
            ActionForward forward = null;
            Logger.getLogger(ClienteVO.class.getName()).log(Level.INFO, "HOLA:");
                RequestDispatcher dispacher = request.getRequestDispatcher("");
                if (dispacher != null) {

                }
                //metodo que maneja la accion en de acuerdo al hidden metodo que es indicado
                HttpSession session = request.getSession();
                ClientesActionFormBean cfb = (ClientesActionFormBean) form;
                Logger.getLogger(ClienteVO.class.getName()).log(Level.INFO, "ACTION ES:" + cfb.getAction());
                if (cfb.getAction().equalsIgnoreCase("'buscar'")) {
                    Logger.getLogger(ClienteVO.class.getName()).log(Level.INFO, "entra a buscar");
                    List<ClienteVO> clientes = this.buscar(cfb.getQuery());
                    
                    request.setAttribute("clientes", clientes);
                    forward = mapping.findForward("buscar");
                }else if (cfb.getAction().equalsIgnoreCase("'crear'")){
                    this.crear(cfb);
                    forward = mapping.findForward("crear");
                }else if (cfb.getAction().equalsIgnoreCase("'editar'")) {
                    this.editar(cfb);
                    forward = mapping.findForward("editar");
                }else if (cfb.getAction().equalsIgnoreCase("'eliminar'")) {
                    String clienteCi = request.getParameter("clienteCi");
                    this.eliminar(clienteCi);
                    forward = mapping.findForward("eliminar");
                }
                
                return forward;
    }
    
    
        //private try-catch
        public List<ClienteVO> buscar(String query) throws SQLException {
             Logger.getLogger(ClienteVO.class.getName()).log(Level.INFO, "buscando"+query);
            ClienteVO cliente = new ClienteVO();
            return cliente.selectClienteInfo(query);
        }
        
        public ClienteVO crear(ClientesActionFormBean cfb) throws SQLException {
            ClienteVO cliente = ClienteVO.crear(cfb);      
            return cliente;
        }
        
        public void eliminar(String clienteCi) throws SQLException {
           ClienteVO.eliminar(clienteCi);
        }

        private ClienteVO editar(ClientesActionFormBean cfb) throws SQLException{
            ClienteVO cliente = ClienteVO.editar(cfb);      
            return cliente;
        }
        
}
