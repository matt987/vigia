<%-- 
    Document   : clientes-new
    Created on : 28/04/2014, 10:24:38
    Author     : matts
--%>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!-- utiliza el template definido que se llama "compa�y-template" la definicion del Company-template esta wn WEB_INF/tiles-def 
-->
<tiles:insert definition="company-template" >
    <tiles:put name="body" value="/views/proveedores/proveedores-new-body.jsp" />
</tiles:insert>
