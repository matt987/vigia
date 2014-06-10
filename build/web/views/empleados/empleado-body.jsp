<%-- 
    Document   : clientes-new-body
    Created on : 28/04/2014, 10:16:08
    Author     : matts
--%>

<%@page import="com.vigia.struts.vo.EmpleadoVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sess = request.getSession(false);
    
    EmpleadoVO empleado = EmpleadoVO.findByCi((String)request.getParameter("empleadoCi"));
    /*Obtiene la session*/

%>
<tiles:insert definition="company-template" >
    <div class="container well">
        <div class="row ">
            <div class="col-xs-6">
              <h1>
                <a href="#" id="title">
                    Empleado
                </a>
              </h1>
                
                <div class="row">
                    <div>
                      <label for="nombre">Nombre:</label>
                      <%= empleado.getNombre() %>
                    </div><br>
                    <div>
                      <label for="apellido">Apellido</label>                        
                      <%= empleado.getApellido() %>
                    </div><br>
                    <div>
                      <label for="direccion">Direcci&oacute;n</label>                        
                      <%= empleado.getDireccion() %>
                    </div><br> 
                    <div>
                      <label for="cedula">C&eacute;dula</label>                        
                      <%= empleado.getCi() %>
                    </div><br>   
                    <div>
                      <label for="ruc">RUC</label>                        
                      <%= empleado.getRuc() %>
                    </div><br>   
                    <div>
                        <label for="telefono">T&eacute;lefono</label>                        
                        <%= empleado.getTelefono() %>
                    </div><br>                       
                    
                </div>
            </div>
          </div>
        <div class=" pull-right">
            <a class="btn btn-default" href="empleados.jsp">Volver</a>
            <a class="btn btn-success" href="empleado-edit.jsp?empleadoCi=<%= empleado.getCi() %>">Editar</a>
        </div>                    
        <br/><br/>
        
    </div>
</tiles:insert>
