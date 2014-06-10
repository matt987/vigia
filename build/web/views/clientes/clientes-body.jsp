<%-- 
Document   : clientes-body
Created on : 26/04/2014, 12:56:18
Author     : matts
--%>
<%@ page import="com.vigia.struts.vo.*,java.util.*"%>
<%
    HttpSession sess = request.getSession(false);

    List<ClienteVO> clientes = (List<ClienteVO>) request.getAttribute("clientes");

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
    <form role="form" class="" action="/todo_hogar/clientes.do?action='buscar'" method="post">
        <div class="row">
            <div class=" col-xs-5">
                <input id="search" type="text" name="query" class="form-control" placeholder="Buscar por nombre/direccion/tipo de cliente">
            </div>
            <button type="submit" class="col-xs-1 btn btn-default">Buscar</button>

            <div class=" pull-right">
                <a class=" fancybox btn btn-success" href="#new-cliente">Agregar Nuevo</a>
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
                <%                    if (null == clientes) {
                        ClienteVO clienteVO = new ClienteVO();
                        if (clienteVO != null) {
                            clientes = (List<ClienteVO>) clienteVO.selectClienteInfo();
                        }
                    } else if (clientes.isEmpty()) {
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
                        <a class="fancybox" href="#ver<%=cliente.getCi().trim()%>">Ver</a>|
                        <a class="fancybox" href="#editar<%= cliente.getCi().trim()%>">Editar</a>|
                        <a href="/todo_hogar/clientes.do?action='eliminar'&clienteCi=<%= cliente.getCi()%>" data-confirm="true">Eliminar</a>
                        <div style="display:none;">
                            <div style="padding: 20px;" class="container well" id="ver<%= cliente.getCi().trim()%>">
                                <div class="row">
                                    <div>
                                        <label for="nombre">Nombre:</label>
                                        <%= cliente.getNombre()%>
                                    </div><br>
                                    <div>
                                        <label for="apellido">Apellido</label>                        
                                        <%= cliente.getApellido()%>
                                    </div><br>
                                    <div>
                                        <label for="direccion">Direcci&oacute;n</label>                        
                                        <%= cliente.getDireccion()%>
                                    </div><br> 
                                    <div>
                                        <label for="cedula">C&eacute;dula</label>                        
                                        <%= cliente.getCi()%>
                                    </div><br>   
                                    <div>
                                        <label for="ruc">RUC</label>                        
                                        <%= cliente.getRuc()%>
                                    </div><br>   
                                    <div>
                                        <label for="telefono">T&eacute;lefono</label>                        
                                        <%= cliente.getTelefono()%>
                                    </div><br>                       

                                </div>                          
                            </div>
                            <div class="container well" id="editar<%= cliente.getCi().trim()%>">
                                <h1>
                                    <a href="#" id="title">
                                        Editar
                                    </a>
                                </h1>

                                <form role="form" class="" action="/todo_hogar/clientes.do?action='editar'" method="post" data-toggle="validator">
                                    <div class="row">
                                        <div class="form-group">
                                            <label for="cedula">C&eacute;dula(*)</label>                        
                                            <input id="cedula" type="number" name="ci" max="999999999" min="9999" validate="true" required="true" class="form-control" placeholder="Cédula" data-error="Debe contener valores numéricos entre 10.000 y 1.000.000.000" value="<%= cliente.getCi().trim()%>">
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


<div style="display: none">
    <div id="new-cliente" class="container well">
        <h1>
            <a href="#" id="title">
                Nuevo Cliente
            </a>
        </h1>

        <form id="new-cliente" role="form" class="" action="/todo_hogar/clientes.do?action='crear'" method="post" data-toggle="validator">
            <div class="row">
                <div class="form-group">
                    <label for="cedula">C&eacute;dula(*)</label>                       
                    <input id="cedula" type="number" name="ci" max="999999999" min="9999" validate="true" required="true" class="form-control" placeholder="Cédula" data-error="Debe contener valores numéricos entre 10.000 y 1.000.000.000">
                    <span class="help-block with-errors">
                        <span id="cedula-error" class="error"> </span> 
                    </span>
                </div><br> 
                <div class="form-group">
                    <label for="nombre">Nombre(*)</label>
                    <input id="nombre" type="text" name="nombre" maxlength="30" validate="true"  class="form-control" data-toggle="validator" placeholder="Nombre" required>
                    <span class="help-block with-errors"></span>
                </div><br>
                <div class="form-group">
                    <label for="apellido">Apellido(*)</label>                        
                    <input id="apellido" type="text" name="apellido" maxlength="30" validate="true" required="true" class="form-control" placeholder="Apellido" >
                    <div class="help-block with-errors"></div>
                </div><br>
                <div class="form-group">
                    <label for="direccion">Direcci&oacute;n(*)</label>                        
                    <input id="direccion" type="text" name="direccion" maxlength="100" validate="true" required="true" class="form-control" placeholder="Direcci&oacute;n" >
                    <div class="help-block with-errors"></div>
                </div><br>   
                <div class="form-group">
                    <label for="ruc">RUC(*)</label>                        
                    <input id="ruc" type="number" name="ruc" max="999999999" min="9999" validate="true" required="true" class="form-control" placeholder="RUC" data-error="Debe contener valores numéricos entre 10.000 y 1.000.000.000">
                    <div class="help-block with-errors"></div>
                </div><br>   
                <div class="form-group">
                    <label for="telefono">T&eacute;lefono(*)</label>                        
                    <input id="telefono" type="text" name="telefono" maxlength="30" validate="true" class="form-control" placeholder="Tel&eacute;fono">
                    <div class="help-block with-errors"></div>
                </div><br>                       


            </div>
            <div class="pull-right">
                (*)Campos obligatorios
                <a class="btn btn-default" href="clientes.jsp">Cancelar</a>    
                <button type="submit" class="btn btn-success" id="submit-aceptar">Aceptar</button>
            </div>
        </form>
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

