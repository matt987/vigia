<%-- 
    Document   : clientes-new-body
    Created on : 28/04/2014, 10:16:08
    Author     : matts
--%>

<%@page import="com.vigia.struts.vo.ClienteVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sess = request.getSession(false);
    
    ClienteVO cliente = ClienteVO.findByCi((String)request.getParameter("clienteCi"));
    /*Obtiene la session*/

%>
<tiles:insert definition="company-template" >
    <div class="container well">
        <div class="row ">
            <div class="col-xs-6">
              <h1>
                <a href="#" id="title">
                    Cliente
                </a>
              </h1>
                
                <div class="row">
                    <div>
                      <label for="nombre">Nombre:</label>
                      <%= cliente.getNombre() %>
                    </div><br>
                    <div>
                      <label for="apellido">Apellido</label>                        
                      <%= cliente.getApellido() %>
                    </div><br>
                    <div>
                      <label for="direccion">Direcci&oacute;n</label>                        
                      <%= cliente.getDireccion() %>
                    </div><br> 
                    <div>
                      <label for="cedula">C&eacute;dula</label>                        
                      <%= cliente.getCi() %>
                    </div><br>   
                    <div>
                      <label for="ruc">RUC</label>                        
                      <%= cliente.getRuc() %>
                    </div><br>   
                    <div>
                        <label for="telefono">T&eacute;lefono</label>                        
                        <%= cliente.getTelefono() %>
                    </div><br>                       
                    
                </div>
            </div>
          </div>
        <div class=" pull-right">
            <a class="btn btn-default" href="clientes.jsp">Volver</a>
            <a class="btn btn-success" href="cliente-edit.jsp?clienteCi=<%= cliente.getCi() %>">Editar</a>
        </div>                    
        <br/><br/>
        
    </div>
</tiles:insert>
