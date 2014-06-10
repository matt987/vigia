/*
 * Action del Login, es el controlador segun el patron MVC
Solo contiene el metodo execute que valida el login.
 */
package com.vigia.struts;
//todos los imports necesarios
import com.vigia.struts.vo.UserVO;
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
public class LoginAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

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
        //here actual login goes. !!
        //obtiene la sesion
        HttpSession session = request.getSession();
        //formulario que maneja las variables
        LoginActionFormBean lfb = (LoginActionFormBean) form;
        //crea el usuario con el pass y el username
        UserVO usuario = new UserVO(lfb.getUsername(), lfb.getPassword());
        
        //TODO comparacion debe hacerse contra bd en jdbc
        if (usuario.validar()) {
            session.setAttribute("id", usuario.getId());
            session.setAttribute("usuario", usuario.getNombre());
            session.removeAttribute("errorLogin");
            return mapping.findForward(SUCCESS);
        } else {
            session.removeAttribute("id");
            session.removeAttribute("usuario");
            //aca setea el error login para mostrar en la pagina inicial
            session.setAttribute("errorLogin", "Usuario o Clave Invalida");
            return mapping.findForward(FAILURE);
        }

    }
}
