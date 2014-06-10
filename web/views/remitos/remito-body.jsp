<%-- 
    Document   : remito-body
    Created on : 12/05/2014, 15:23:41
    Author     : matts
--%>

<%@page import="java.util.List"%>
<%@page import="com.vigia.struts.vo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sess = request.getSession(false);
    String usuarioSessionId = (String) sess.getAttribute("usuario");
    UserVO usuario = new UserVO(usuarioSessionId);
    String proveedores = ProveedorVO.allJSON();
    String productos = ProductoVO.allJSON();
    int numeroRemito = RemitoVO.getNextNumber();

%>
<tiles:insert definition="company-template" >
    <form role="form" class="" action="/todo_hogar/remitos.do?action='crear'" method="post" data-toggle="validator">
        <div class="container well">
            <div class="row ">
                <div class="col-xs-6">
                    <h1>
                        <a href="#" id="title">
                            Nuevo Remito
                        </a>
                    </h1>
                </div>
            </div>
            <br/>
            <div class="row text-right">
                <h1>Remito <small>#000<%= numeroRemito%></small></h1>
            </div>
            <br/>
            <div class="row">
                <div class="col-xs-5">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4>Recibido por: <a href="#"><%= usuario.getNombreEmpleado()%></a></h4>
                        </div>
                        <div class="panel-body">
                            <p>Dirección: <%= usuario.getDireccionEmpleado()%></p>
                            <input type="hidden" name="value(usuarioId)" value="<%= usuario.getUsuarioId() %>" />
                        </div>
                    </div>
                </div>

                <div class="col-xs-5 text-right col-xs-offset-2">
                    <div class="form-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <label class="sr-only" for="busqueda-proveedor"><h4>Ingresar RUC</h4></label>
                                <input class="form-control" id="busqueda-proveedor" placeholder="Ingresar RUC a buscar" type="text">
                            </div>
                            <div class="panel-body">
                                <p>
                                <div class="row pull-right">
                                    <label class="col-xs-4" for="nombre-proveedor">Nombre</label>
                                    <div class="col-xs-6">
                                        <input class="form-control input-sm " id="nombre-proveedor" name="value(nombreProveedor)" placeholder="Ingresar Nombre" type="text">
                                    </div>
                                </div>
                                <div class="row pull-right">
                                    <label class="col-xs-4" for="telefono-proveedor">Teléfono</label>
                                    <div class="col-xs-6">
                                        <input class="form-control input-sm" id="telefono-proveedor" name="value(telefonoProveedor)" placeholder="Ingresar Teléfono" type="text">
                                    </div>
                                </div>
                                <div class="row pull-right">
                                    <label class="col-xs-4" for="ruc-proveedor">RUC</label>
                                    <div class="col-xs-6">
                                        <input class="form-control input-sm " id="ruc-proveedor" name="value(rucProveedor)" placeholder="Ingresar RUC   " type="text">
                                    </div>
                                </div>
                                </p>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row form-group">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th><h4>C&oacute;digo</h4></th>
                    <th><h4>Nombre</h4></th>
                    <th><h4>Cantidad</h4></th>
                    <th><h4>Acción</h4></th>
                    </tr>
                    </thead>
                    <tbody>

                        <tr id="form-agregar-producto">
                            <td class="col-xs-3">
                                <input class="form-control input-sm " id="codigo-producto" placeholder="Código de producto" type="text" >
                            </td>
                            <td class="col-xs-3">
                                <input class="form-control input-sm " id="nombre-producto" placeholder="Nombre de producto" type="text">
                            </td>
                            <td class="col-xs-3">
                                <input class="form-control input-sm " id="cantidad-producto" placeholder="Cantidad de producto" type="numeric" >
                            </td>     
                            <td>
                                <a href="#agregar" id="agregar-producto">+</a>
                            </td>
                        </tr>

                    </tbody>
                </table>
            </div>  
            <div class="eliminar"></div>
            <div class="pull-right">
                <a class="btn btn-default" href="/todo_hogar/views/remitos/remitos.jsp">Cancelar</a>    
                <button type="submit" class="btn btn-success">Aceptar</button>
            </div>

        </div>
    </form>
    <script type="text/javascript">
        index_productos = 0;
        productos_object = <%= productos%>
        function proveedorData(toSearch) {
            var newProveedor = {ruc: "", nombre: "", telefono: ""};
            var proveedores_object = <%= proveedores%>;
            var proveedores = proveedores_object.proveedores;
            for (var i = 0; i < proveedores.length; i++) {
                if (proveedores[i]["ruc"] === toSearch) {
                    setProveedorFields(proveedores[i]);
                    break;
                } else {
                    setProveedorFields(newProveedor);
                }
            }
        }

        function setProveedorFields(proveedor) {
            $("#nombre-proveedor").val(proveedor.nombre);
            $("#telefono-proveedor").val(proveedor.telefono);
            $("#ruc-proveedor").val(proveedor.ruc);
        }

        function productoData(toSearch) {

            var newProducto = {codigo: toSearch, nombre: "", descripcion: ""};
            var productos = productos_object.productos;
            for (var i = 0; i < productos.length; i++) {
                if (productos[i]["codigo"] === toSearch) {
                    setProductoFields({productos: productos, i: i});
                    break;
                } else {
                    setProductoFields({producto: newProducto, productos: [], i: ""});
                }
            }
        }

        function setProductoFields(object) {
            producto = object.productos[object.i];
            if (producto === undefined) {
                producto = object.producto;
            }
            $("#codigo-producto").val(producto.codigo);
            $("#nombre-producto").val(producto.nombre);
        }



        $(document).ready(function() {
            proveedor = $("#busqueda-proveedor");
            producto = $("#codigo-producto");
            proveedor.bind("keyup", function() {
                proveedorData($(this).val());

            });

            producto.bind("keyup", function() {
                productoData($(this).val());
            });

            $(document.body).on('click', '.eliminar', function() {
                var producto_id = $(this).attr('id');
                var row = $("#" + producto_id + "-row");
                if (confirm("¿Está seguro?")) {
                    row.remove();
                }
            });
            $("#agregar-producto").on('click', function() {
                var producto = $("#codigo-producto").val();
                var cantidad = $("#cantidad-producto").val();
                var nombre = $("#nombre-producto").val();
                if (producto !== "" && cantidad !== "" && nombre !== "") {
                    var html = '<tr id="' + producto + '-row">\n\
                                  <td class="col-xs-3">\n\
                                    <input id="' + producto + '-codigo" class="form-control input-sm " name="value(detalles' + index_productos + 'Codigo)" placeholder="" type="text" value="' + producto + '">\n\
                                  </td>\n\
                                  <td class="col-xs-3">\n\
                                    <input id="' + producto + '-nombre" class="form-control input-sm " name="value(detalles' + index_productos + 'Nombre)" placeholder="" type="text" value="' + nombre + '">\n\
                                  </td>\n\
                                  <td class="col-xs-3">\n\
                                    <input id="' + producto + '-cantidad" class="form-control input-sm " name="value(detalles' + index_productos + 'Cantidad)" placeholder="" type="text" value="' + cantidad + '">\n\
                                  </td>\n\
                                  <td>\n\
                                    <a href="javascript: void()" class="eliminar" id="' + producto + '">X</a>\n\
                                  </td>\n\
                                </tr>';
                    if ($("#" + producto + "-codigo").length > 0) {
                        var cantidad = $("#" + producto + "-cantidad");
                        var cant = parseInt(cantidad.val()) + parseInt($("#cantidad-producto").val());
                        cantidad.val(cant);
                    } else {
                        $(html).insertBefore("#form-agregar-producto");
                        index_productos++;
                    }
                    $("#codigo-producto").val('');
                    $("#cantidad-producto").val('');
                    $("#nombre-producto").val('');

                } else {
                    alert("Debe ingresar todos los valores");
                }
            })
        });

    </script>    
</tiles:insert>
