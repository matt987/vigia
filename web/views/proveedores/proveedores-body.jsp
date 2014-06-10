    <%-- 
    Document   : clientes-body
    Created on : 26/04/2014, 12:56:18
    Author     : matts
--%>
<%@ page import="com.vigia.struts.vo.*,java.util.*"%>
<%
    HttpSession sess = request.getSession(false);
    
    List<ProveedorVO> proveedores = (List<ProveedorVO>)request.getAttribute("proveedores");
    
    /*Obtiene la session*/

%>

<div class="container well">

    <div class="row ">
      <div class="col-xs-6">
        <h1>
          <a href="#" id="title">
              Proveedores
          </a>
        </h1>
      </div>
    </div>
  <br/><br/>
      <form role="form" class="" action="/todo_hogar/proveedores.do?action='buscar'" method="post">
        <div class="row">
            <div class=" col-xs-5">
              <input id="search" type="text" name="query" class="form-control" placeholder="Buscar por nombre/direccion de proveedor">
            </div>
            <button type="submit" class="col-xs-1 btn btn-default">Buscar</button>

            <div class=" pull-right">
              <a class="btn btn-success" href="proveedores-new.jsp">Agregar Nuevo</a>
            </div>
        </div>
      </form>
    
      <br/><br/>
    <div id="table-container" class="row">
        <table class="table table-bordered table-striped table-hover">
            <thead>
                <tr>
                    <th><h4>Nombre</h4></th>
                    <th><h4>Direcci&oacute;n</h4></th>
                    <th><h4>Acciones</h4></th>
                </tr>              
            </thead>
            <tbody>
                <% 
                    if (null == proveedores ){
                        ProveedorVO proveedorVO = new ProveedorVO();
                        if (proveedorVO != null) {
                            proveedores = (List<ProveedorVO>) proveedorVO.selectProveedorInfo(); 
                        }
                    }else if(proveedores.isEmpty()){
                        ProveedorVO proveedorVO = new ProveedorVO();
                        if (proveedorVO != null) {
                            proveedores = (List<ProveedorVO>) proveedorVO.selectProveedorInfo(); 
                        }
                    }
                    if (proveedores != null) {
                        for (ProveedorVO proveedor : proveedores) {
                %>
                <tr>
                    <td><%=proveedor.getNombre()%></td>
                    <td><%=proveedor.getDireccion()%></td>
                    <td>
                        <a href="proveedor.jsp?proveedorRuc= <%= proveedor.getRuc() %>">Ver</a>|
                        <a href="proveedor-edit.jsp?proveedorRuc= <%= proveedor.getRuc() %>">Editar</a>|
                        <a href="/todo_hogar/proveedores.do?action='eliminar'&proveedorRuc=<%= proveedor.getRuc() %>" data-confirm="¿Desea eliminar a <%= proveedor.getNombre() %>?">Eliminar</a>
                    </td>


                </tr>
                <%
                        }
                    }
                %>
            </tbody>

        </table>
    </div>
  </div>



 