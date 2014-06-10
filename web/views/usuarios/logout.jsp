<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
    /*Seccion que cierra la session de manera correcta, */
    HttpSession sess = request.getSession(false);
    sess.setAttribute("id", null);
    sess.setAttribute("usuario", null);
    sess.invalidate();
    
%>

<jsp:forward page="/User.do"/>
