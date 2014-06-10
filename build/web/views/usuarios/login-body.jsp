<%-- 
    Document   : login-body
    Created on : 30-mar-2014, 0:20:38
    Author     : hsendoa
--%>

<%--@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" --%>

<%@page session="true" %>
<%
    /*Crea una nueva session y asigna los valores a nulo.*/
    HttpSession sess = request.getSession(false);
    sess.isNew();
    sess.setAttribute("id", null);
    sess.setAttribute("usuario", null);


%>

<div class="container">
    <div class="row well">
        <div class="col-md-2">
        </div>
        <div class="col-md-6">
            <div class="">
                <img class="pic img-circle" src="images/user.jpg" alt="...">
                <form name="login" action="/todo_hogar/login.do" method="post">
                    <div class="form-group">
                        <label for="inputUsernameEmail">Usuario</label>
                        <input type="text" class="form-control" id="inputUsernameEmail" name="username">
                    </div>
                    <div class="form-group">

                        <label for="inputPassword">Password</label>
                        <input type="password" class="form-control" id="inputPassword" name="password">
                    </div>
                    <div class="alert-danger" >
                        <%   
                            /*Obtiene el error de la variable errorLogin en la session*/
                            String errorL = (String) sess.getAttribute("errorLogin");
                            if (errorL != null && errorL != "") {
                                out.print(errorL);
                            }
                        %>
                    </div>
                    <button type="submit" class="btn btn btn-primary">
                        Ingresar
                    </button>
                </form>
            </div>



        </div>
        <div class="col-md-4"><h3> Bienvenidos a Vigia</h3> <br>
            El águila viuda (Spizaetus melanoleucus), también conocida como aguilucho blanquinegro,y águila tazor chica es una especie de ave Accipitriforme de la familia Accipitridae que vive en América, desde México, América Central, Sudamérica hasta el norte de Argentina, desde el nivel del mar hasta 1.500 msnm. No se conocen subespecies.2
            En Argentina se encuentra en el Listado CITES Cat. II: vulnerable. Es la Convención Sobre el Comercio Internacional de Especies Amenazadas de Fauna y de Flora Silvestre.[cita requerida]</div>
    </div>


</div>
