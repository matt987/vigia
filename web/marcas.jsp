<%-- 
    Document   : marcas
    Created on : 14-abr-2014, 21:36:13
    Author     : hsendoa
--%>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!-- utiliza el template definido que se llama "compañy-template" la definicion del Company-template esta wn WEB_INF/tiles-def 
-->
<tiles:insert definition="company-template" >
    <tiles:put name="body" value="/marcas-body.jsp" />
</tiles:insert>