<%-- 
    Document   : common-layout
    Created on : 29-mar-2014, 22:24:27
    Author     : hsendoa
    Desc       : Template de la pagina
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="true" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><bean:message key="welcome.title"/></title>
        <html:base/>
        <link href="/todo_hogar/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="/todo_hogar/css/awesome.css" rel="stylesheet" type="text/css"/>
        <link href="/todo_hogar/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="/todo_hogar/css/jquery.fancybox.css" rel="stylesheet" type="text/css"/>
        
        
        <script src="/todo_hogar/js/jquery.js" type="text/javascript"></script>
        <script src="/todo_hogar/js/twitter-bootstrap-hover-dropdown.js" type="text/javascript"></script>
        <script src="/todo_hogar/js/bootstrap-validator.js" type="text/javascript"></script>
        <script src="/todo_hogar/js/jquery.fancybox.pack.js" type="text/javascript"></script>

    </head>
    <body onload="window.history.forward(1);">
        <logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">

            <div  style="color: red">
                ERROR:  Application resources not loaded -- check servlet container
                logs for error messages.
            </div>
        </logic:notPresent>

        <%
            //script para control de sesion
            HttpSession sess = request.getSession();
            if (sess == null) {
                response.sendRedirect("/");
                //out.print("sess nula");
            } else {
                String sessionId_Logon = (String) sess.getAttribute("usuario");

                if (sessionId_Logon == null) {
                    //out.print("sessID nula");
                    response.sendRedirect("/");
                    //out.print(sessionId_Logon);
                }
            }
        %>  

        <tiles:insert attribute="header"/>

        <tiles:insert attribute="body"/> 

        <tiles:insert attribute="footer"/> 
    </body>
</html>
