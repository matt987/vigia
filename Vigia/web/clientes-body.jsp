<%-- 
    Document   : clientes-body
    Created on : 26/04/2014, 12:56:18
    Author     : matts
--%>
<%@ page import="com.vigia.struts.vo.*,java.util.*"%>
<%
    HttpSession sess = request.getSession(false);
    List<ClienteVO> clientes = (List<ClienteVO>)request.getAttribute("clientes");
    
    /*Obtiene la session*/

%>

<div class="container well">

    <div class="row ">
      <div class="col-xs-6">
        <h1>
          <a href="#" id="title">
              Clientes
          </a>
        </h1>
      </div>
    </div>
  <br/><br/>
      <form role="form" class="" action="./clientes.jsp?action='buscar'" method="post">
        <div class="row">
            <div class=" col-xs-5">
              <input id="search" type="text" name="query" class="form-control" placeholder="Buscar por nombre/direccion/tipo de cliente">
            </div>
            <button type="submit" class="col-xs-1 btn btn-default">Buscar</button>

            <div class=" pull-right">
              <a class="btn btn-success">Agregar Nuevo</a>
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
                    if (null == clientes){
                        ClienteVO clienteVO = new ClienteVO();
                        if (clienteVO != null) {
                            clientes = (List<ClienteVO>) clienteVO.selectClienteInfo(); 
                        }
                    }
                    if (clientes != null) {
                        for (ClienteVO cliente : clientes) {
                %>
                <tr>
                    <td><%=cliente.getNombreCompleto()%></td>
                    <td><%=cliente.getDireccion()%></td>
                    <td>
                        <a href=\"#\">Ver</a>|
                        <a href=\"#\">Editar</a>|
                        <a href=\"#\">Eliminar</a>
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



 