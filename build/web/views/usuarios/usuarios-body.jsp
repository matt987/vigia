<%-- 
    Document   : usuarios-body
    Created on : 07-abr-2014, 12:58:57
    Author     : hsendoa
--%>
<%@ page import="com.vigia.struts.vo.*,java.util.*"%>
<%
    HttpSession sess = request.getSession(false);

    List<UserVO> usuarios = (List<UserVO>) request.getAttribute("usuarios");
%>

<div class="container well">

    <div class="row ">
        <div class="col-xs-6">
            <h1>
                <a href="#" id="title">
                    Usuarios
                </a>
            </h1>
        </div>
    </div>
    <br/><br/>
    <form role="form" class="" action="/todo_hogar/usuarios.do?action='buscar'" method="post">
        <div class="row">
            <div class=" col-xs-5">
                <input id="search" type="text" name="query" class="form-control" placeholder="Buscar usuario">
            </div>
            <button type="submit" class="col-xs-1 btn btn-default">Buscar</button>

            <div class=" pull-right">
                <%  List<EmpleadoVO> empleados = null;
                    empleados = EmpleadoVO.withoutUser(); 
                    String html = "";
                    if (empleados == null || empleados.size() <= 0){
                        html = "title= 'Todos los empleados tienen usuario asignado' disabled='disabled'";
                    }else{
                        html = "";
                    }
                %>
                <a class="btn btn-success fancybox" href="#new-usuario" <%= html %>>Agregar Nuevo</a>
                               
            </div>
        </div>
    </form>

    <br/><br/>
    <div id="table-container" class="row">
        <table class="table table-bordered table-striped table-hover">
            <thead>
                <tr>
                    <th><h4>Login</h4></th>
                    <th><h4>Empleado</h4></th>
            <th><h4>Acciones</h4></th>
            </tr>              
            </thead>
            <tbody>
                <%
                    if (null == usuarios) {
                        UserVO usuarioVO = new UserVO();
                        if (usuarioVO != null) {
                            usuarios = (List<UserVO>) usuarioVO.selectUsuariosInfo();
                        }
                    } else if (usuarios.isEmpty()) {
                        UserVO usuarioVO = new UserVO();
                        if (usuarioVO != null) {
                            usuarios = (List<UserVO>) usuarioVO.selectUsuariosInfo();
                        }
                    }
                    if (usuarios != null) {
                        for (UserVO usuario : usuarios) {
                %>
                <tr>
                    <td><%= usuario.getNombre()%></td>
                    <td><%= usuario.getNombreEmpleado() %></td>
                    <td>
                        <a href="/todo_hogar/usuarios.do?action=eliminar&id=<%= usuario.getUsuarioId()%>" data-confirm="¿Desea eliminar a <%= usuario.getNombre()%>?">Eliminar</a>
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

<div style="display: none;">
                    <div class="container well" id="new-usuario">
                        <div class="row ">
                            <div class="col-xs-6">
                                <h1>
                                    <a href="#" id="title">
                                        Nuevo Usuario
                                    </a>
                                </h1>
                                    <form  role="form" class="" action="/todo_hogar/usuarios.do?action=crear" method="post" data-toggle="validator">
                                        <div class="row">
                                            <div class="form-group">
                                                <label for="login">Login(*)</label>                       
                                                <input id="login" type="text" name="login" validate="true" required="true" class="form-control" placeholder="Login" data-error="">
                                                <span class="help-block with-errors">
                                                    <span id="login-error" class="error"> </span> 
                                                </span>
                                            </div><br> 
                                            <div class="form-group">
                                                <label for="inputPassword">Password(*)</label>                       
                                                <input id="inputPassword" type="password" name="password" validate="true" required="true" class="form-control" placeholder="Password" data-error="">
                                                <span class="help-block with-errors">
                                                    <span id="login-error" class="error"> </span> 
                                                </span>
                                            </div><br> 
                                            <div class="form-group">
                                                <label for="passwordConfirm">Password Confirmation(*)</label>                       
                                                <input id="login" type="password" name="passwordConfirm" validate="true" required="true" class="form-control" placeholder="Password Confirmation" data-match="#inputPassword" data-match-error="Los passwords no coinciden">
                                                <span class="help-block with-errors">
                                                    <span id="login-error" class="error"> </span> 
                                                </span>
                                            </div><br>  
                                            <div class="form-group">
                                                <label for="empleado">Empleado(*)</label>                       
                                                <select class="form-control" name="empleadoId" id=""empleado>

                                                    <%if (empleados != null) {
                                                        for (EmpleadoVO empleado : empleados) {%>
                                                          <option value="<%= empleado.getEmpleadoId()%>"><%= empleado.getNombre()%></option>
                                                    <%  } }%>
                                                </select>
                                            </div><br> 
                                            <div class="pull-right">
                                                (*)Campos obligatorios
                                                <a class="btn btn-default" href="/todo_hogar/views/usuarios/usuarios.jsp">Cancelar</a>    
                                                <button type="submit" class="btn btn-success" id="submit-aceptar">Aceptar</button>
                                            </div>                    </div>
                                    </form>
                            </div>
                        </div>
                    </div>
                </div> 

<script type="text/javascript">
    $(document).ready(function() {
        $(".fancybox").fancybox({
            "width": 800,
            "height": 500,
        });
    });
</script>
