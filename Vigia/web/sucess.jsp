
<%-- 
    Document   : sucess
    Created on : 29-mar-2014, 22:25:53
    Author     : hsendoa
--%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%
    //script para control de sesion
    //en caso de exito redireccionar a Inicio.jsp
    HttpSession s = request.getSession(false);
    String sessionId = (String) s.getAttribute("usuario");
    if (sessionId == null || sessionId.isEmpty() || sessionId == "") {

        pageContext.forward("/");
    }
%>
<tiles:insert definition="company-template" >
    <tiles:put name="body" value="/inicio.jsp" />
</tiles:insert>
