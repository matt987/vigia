/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vigia.struts.empleados;

import com.vigia.struts.vo.EmpleadoVO;
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
public class EmpleadosAction extends org.apache.struts.action.Action {

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
            Logger.getLogger(EmpleadoVO.class.getName()).log(Level.INFO, "HOLA:");
                RequestDispatcher dispacher = request.getRequestDispatcher("");
                if (dispacher != null) {

                }
                //metodo que maneja la accion en de acuerdo al hidden metodo que es indicado
                HttpSession session = request.getSession();
                EmpleadosActionFormBean efb = (EmpleadosActionFormBean) form;
                Logger.getLogger(EmpleadoVO.class.getName()).log(Level.INFO, "ACTION ES:" + efb.getAction());
                if (efb.getAction().equalsIgnoreCase("'buscar'")) {
                    Logger.getLogger(EmpleadoVO.class.getName()).log(Level.INFO, "entra a buscar");
                    List<EmpleadoVO> empleados = this.buscar(efb.getQuery());
                    
                    request.setAttribute("empleados", empleados);
                    forward = mapping.findForward("buscar");
                }else if (efb.getAction().equalsIgnoreCase("'crear'")){
                    this.crear(efb);
                    forward = mapping.findForward("crear");
                }else if (efb.getAction().equalsIgnoreCase("'editar'")) {
                    this.editar(efb);
                    forward = mapping.findForward("editar");
                }else if (efb.getAction().equalsIgnoreCase("'eliminar'")) {
                    String empleadoCi = request.getParameter("empleadoCi");
                    this.eliminar(empleadoCi);
                    forward = mapping.findForward("eliminar");
                }
                
                return forward;
    }
    
    
        //private try-catch
        public List<EmpleadoVO> buscar(String query) throws SQLException {
             Logger.getLogger(EmpleadoVO.class.getName()).log(Level.INFO, "buscando"+query);
            EmpleadoVO empleado = new EmpleadoVO();
            return empleado.selectEmpleadoInfo(query);
        }
        
        public EmpleadoVO crear(EmpleadosActionFormBean efb) throws SQLException {
            EmpleadoVO empleado = EmpleadoVO.crear(efb);      
            return empleado;
        }
        
        public void eliminar(String empleadoCi) throws SQLException {
           EmpleadoVO.eliminar(empleadoCi);
        }

        private EmpleadoVO editar(EmpleadosActionFormBean efb) throws SQLException{
            EmpleadoVO empleado = EmpleadoVO.editar(efb);      
            return empleado;
        }
        
}
