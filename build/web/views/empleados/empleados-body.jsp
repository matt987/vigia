    <%-- 
    Document   : clientes-body
    Created on : 26/04/2014, 12:56:18
    Author     : matts
--%>
<%@ page import="com.vigia.struts.vo.*,java.util.*"%>
<%
    HttpSession sess = request.getSession(false);
    
    List<EmpleadoVO> empleados = (List<EmpleadoVO>)request.getAttribute("empleados");
    
    /*Obtiene la session*/

%>

<div class="container well">

    <div class="row ">
      <div class="col-xs-6">
        <h1>
          <a href="#" id="title">
              Empleados
          </a>
        </h1>
      </div>
    </div>
  <br/><br/>
      <form role="form" class="" action="/todo_hogar/empleados.do?action='buscar'" method="post">
        <div class="row">
            <div class=" col-xs-5">
              <input id="search" type="text" name="query" class="form-control" placeholder="Buscar por nombre/direccion/tipo de empleado">
            </div>
            <button type="submit" class="col-xs-1 btn btn-default">Buscar</button>

            <div class=" pull-right">
              <a class="btn btn-success" href="empleados-new.jsp">Agregar Nuevo</a>
            </div>
        </div>
      </form>
    
      <br/><br/>
    <div id="table-container" class="row">
        <table class="table table-bordered table-striped table-hover">
            <thead>
                <tr>
                    <th><h4>Nombre y Apellido</h4></th>
                    <th><h4>Direcci&oacute;n</h4></th>
                    <th><h4>Acciones</h4></th>
                </tr>              
            </thead>
            <tbody>
                <% 
                    if (null == empleados ){
                        EmpleadoVO empleadoVO = new EmpleadoVO();
                        if (empleadoVO != null) {
                            empleados = (List<EmpleadoVO>) empleadoVO.selectEmpleadoInfo(); 
                        }
                    }else if(empleados.isEmpty()){
                        EmpleadoVO empleadoVO = new EmpleadoVO();
                        if (empleadoVO != null) {
                            empleados = (List<EmpleadoVO>) empleadoVO.selectEmpleadoInfo(); 
                        }
                    }
                    if (empleados != null) {
                        for (EmpleadoVO empleado : empleados) {
                %>
                <tr>
                    <td><%=empleado.getNombreCompleto()%></td>
                    <td><%=empleado.getDireccion()%></td>
                    <td>
                        <a href="empleado.jsp?empleadoCi= <%= empleado.getCi() %>">Ver</a>|
                        <a href="empleado-edit.jsp?empleadoCi= <%= empleado.getCi() %>">Editar</a>|
                        <a href="/todo_hogar/empleados.do?action='eliminar'&empleadoCi=<%= empleado.getCi() %>" data-confirm="¿Desea eliminar a <%= empleado.getNombreCompleto() %>?">Eliminar</a>
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



 