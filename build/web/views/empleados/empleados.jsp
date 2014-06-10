<%-- 
    Document   : clientes
    Created on : 26/04/2014, 11:50:31
    Author     : matts
--%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!-- utiliza el template definido que se llama "compañy-template" la definicion del Company-template esta wn WEB_INF/tiles-def 
-->
<tiles:insert definition="company-template" >
    <tiles:put name="body" value="/views/empleados/empleados-body.jsp" />
</tiles:insert>

