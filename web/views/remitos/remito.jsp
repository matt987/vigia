<%-- 
    Document   : remito
    Created on : 12/05/2014, 15:23:17
    Author     : matts
--%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!-- utiliza el template definido que se llama "compañy-template" la definicion del Company-template esta wn WEB_INF/tiles-def 
-->
<tiles:insert definition="company-template" >
    <tiles:put name="body" value="/views/remitos/remito-body.jsp" />
</tiles:insert>
