/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vigia.struts.authorization;

import java.io.IOException;
import javax.servlet.Filter;
import java.util.logging.LogRecord;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author matts
 */
public class AuthorizationFilter implements Filter {

    private String onErrorUrl;

    public void init(FilterConfig arg0) throws ServletException {
//        String roles = arg0.getInitParameter("roles");
//        if (roles == null || "".equals(roles))
//          roleNames = new String[0];
//        else {
//          roles.trim();
//          roleNames = roles.split(" ");
//        }

        onErrorUrl = arg0.getInitParameter("onError");
        if (onErrorUrl == null || "".equals(onErrorUrl)) {
            onErrorUrl = "/index.jsp";
        }
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
            FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) arg0;
        HttpServletResponse res = (HttpServletResponse) arg1;
        HttpSession session = req.getSession();

        ActionErrors errors = new ActionErrors();
        if (session == null) {
            errors.add(ActionErrors.GLOBAL_MESSAGE,
                    new ActionMessage("error.authentication.required"));
        } else {
            String sessionId_Logon = (String) session.getAttribute("usuario");

            if (sessionId_Logon == null) {
                errors.add(ActionErrors.GLOBAL_MESSAGE,
                        new ActionMessage("error.authentication.required"));
            }
        }

        if (errors.isEmpty()) {
            arg2.doFilter(arg0, arg1);
        } else {
            req.setAttribute(Globals.ERROR_KEY, errors);
            req.getRequestDispatcher(onErrorUrl).forward(req, res);
        }
    }

    public void destroy() {
    }

}
