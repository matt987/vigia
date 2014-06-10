<%-- 
    Document   : clientes-new-body
    Created on : 28/04/2014, 10:16:08
    Author     : matts
--%>

<%@page import="com.vigia.struts.vo.ProveedorVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sess = request.getSession(false);
    
    ProveedorVO empleado = ProveedorVO.findByRuc((String)request.getParameter("proveedorRuc"));
    /*Obtiene la session*/

%>
<tiles:insert definition="company-template" >
    <div class="container well">
        <div class="row ">
            <div class="col-xs-6">
              <h1>
                <a href="#" id="title">
                    Proveedor
                </a>
              </h1>
                
                <div class="row">
                    <div>
                      <label for="ruc">RUC</label>                        
                      <%= empleado.getRuc() %>
                    </div><br> 
                    <div>
                      <label for="nombre">Nombre:</label>
                      <%= empleado.getNombre() %>
                    </div><br>
                    <div>
                      <label for="direccion">Direcci&oacute;n</label>                        
                      <%= empleado.getDireccion() %>
                    </div><br>     
                    <div>
                      <label for="email">Email</label>                        
                      <%= empleado.getEmail()%>
                    </div><br> 
                    <div>
                      <label for="pagina_web">P&aacute;gina Web</label>                        
                      <%= empleado.getPaginaWeb()%>
                    </div><br> 
                    <div>
                        <label for="telefono">T&eacute;lefono</label>                        
                        <%= empleado.getTelefono() %>
                    </div><br>                       
                    
                </div>
            </div>
          </div>
        <div class=" pull-right">
            <a class="btn btn-default" href="proveedores.jsp">Volver</a>
            <a class="btn btn-success" href="proveedor-edit.jsp?proveedorRuc=<%= empleado.getRuc()%>">Editar</a>
        </div>                    
        <br/><br/>
        
    </div>
</tiles:insert>
