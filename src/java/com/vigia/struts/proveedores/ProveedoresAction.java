/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vigia.struts.proveedores;
import com.vigia.struts.vo.ProveedorVO;
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
public class ProveedoresAction extends org.apache.struts.action.Action {

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
            Logger.getLogger(ProveedorVO.class.getName()).log(Level.INFO, "HOLA:");
                RequestDispatcher dispacher = request.getRequestDispatcher("");
                if (dispacher != null) {

                }
                //metodo que maneja la accion en de acuerdo al hidden metodo que es indicado
                HttpSession session = request.getSession();
            
            
            
            
                ProveedoresActionFormBean pfb = (ProveedoresActionFormBean) form;
                Logger.getLogger(ProveedorVO.class.getName()).log(Level.INFO, "ACTION ES:" + pfb.getAction());
                if (pfb.getAction().equalsIgnoreCase("'buscar'")) {
                    Logger.getLogger(ProveedorVO.class.getName()).log(Level.INFO, "entra a buscar");
                    List<ProveedorVO> proveedores = this.buscar(pfb.getQuery());
                    
                    request.setAttribute("proveedores", proveedores);
                    forward = mapping.findForward("buscar");
                }else if (pfb.getAction().equalsIgnoreCase("'crear'")){
                    this.crear(pfb);
                    forward = mapping.findForward("crear");
                }else if (pfb.getAction().equalsIgnoreCase("'editar'")) {
                    this.editar(pfb);
                    forward = mapping.findForward("editar");
                }else if (pfb.getAction().equalsIgnoreCase("'eliminar'")) {
                    String proveedorCi = request.getParameter("proveedorRuc");
                    this.eliminar(proveedorCi);
                    forward = mapping.findForward("eliminar");
                }
                
                return forward;
    }
    
    
        //private try-catch
        public List<ProveedorVO> buscar(String query) throws SQLException {
             Logger.getLogger(ProveedorVO.class.getName()).log(Level.INFO, "buscando"+query);
            ProveedorVO proveedor = new ProveedorVO();
            return proveedor.selectProveedorInfo(query);
        }
        
        public ProveedorVO crear(ProveedoresActionFormBean pfb) throws SQLException {
            ProveedorVO proveedor = ProveedorVO.crear(pfb);      
            return proveedor;
        }
        
        public void eliminar(String proveedorRuc) throws SQLException {
           ProveedorVO.eliminar(proveedorRuc);
        }

        private ProveedorVO editar(ProveedoresActionFormBean pfb) throws SQLException{
            ProveedorVO proveedor = ProveedorVO.editar(pfb);      
            return proveedor;
        }
        
}
