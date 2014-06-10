<%-- 
    Document   : user-form
    Created on : 29-mar-2014, 22:25:53
    Author     : hsendoa
--%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!-- pagina llamada por el forward (redireccion) solo indica que se utiliza el template y   que en la seccion body sera rellenada con el resultado de login-body.jsp
-->
<tiles:insert definition="company-template" >
    <tiles:put name="body" value="/views/usuarios/login-body.jsp" />
</tiles:insert>