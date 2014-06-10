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
                    Editar Cliente
                </a>
              </h1>
                
              <form role="form" class="" action="/todo_hogar/clientes.do?action='editar'" method="post" data-toggle="validator">
                <div class="row">
                    <div class="form-group">
                      <label for="cedula">C&eacute;dula(*)</label>                        
                      <input id="cedula" type="number" name="ci" max="999999999" min="9999" validate="true" required="true" class="form-control" placeholder="Cédula" data-error="Debe contener valores numéricos entre 10.000 y 1.000.000.000" value="<%= cliente.getCi()%>">
                    </div><br> 
                    <div class="form-group">
                      <label for="nombre">Nombre(*)</label>
                      <input id="nombre" type="text" name="nombre" maxlength="30" validate="true"  class="form-control" data-toggle="validator" placeholder="Nombre" required value="<%= cliente.getNombre()%>">
                    </div><br>
                    <div class="form-group">
                      <label for="apellido">Apellido(*)</label>                        
                      <input id="apellido" type="text" name="apellido" maxlength="30" validate="true" required="true" class="form-control" placeholder="Apellido" value="<%= cliente.getApellido()%>">
                    </div><br>
                    <div class="form-group">
                      <label for="direccion">Direcci&oacute;n(*)</label>                        
                      <input id="direccion" type="text" name="direccion" maxlength="100" validate="true" required="true" class="form-control" placeholder="Direcci&oacute;n" value="<%= cliente.getDireccion()%>">
                    </div><br>   
                    <div class="form-group">
                      <label for="ruc">RUC(*)</label>                        
                      <input id="ruc" type="number" name="ruc" max="999999999" min="9999" validate="true" required="true" class="form-control" placeholder="RUC" data-error="Debe contener valores numéricos entre 10.000 y 1.000.000.000" value="<%= cliente.getRuc()%>">
                    </div><br>   
                    <div class="form-group">
                        <label for="telefono">T&eacute;lefono(*)</label>                        
                      <input id="telefono" type="text" name="telefono" maxlength="30" validate="true" class="form-control" placeholder="Tel&eacute;fono" value="<%= cliente.getTelefono()%>">
                    </div><br>                       
                    
                    
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="/todo_hogar/views/clientes/clientes.jsp">Cancelar</a>    
                    <button type="submit" class="btn btn-success">Aceptar</button>
                </div>
              </form>
            </div>
          </div>
        <br/><br/>
        
    </div>
</tiles:insert>
