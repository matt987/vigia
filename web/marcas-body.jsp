<%-- 
    Document   : marcas-body
    Created on : 14-abr-2014, 21:37:03
    Author     : hsendoa
--%>
<%@ page import="com.vigia.struts.vo.*,java.util.*"%>
<%
    HttpSession sess = request.getSession(false);
    /*Obtiene la session*/

%>
<div class="container">
    <div class="row well">
        <!-- divide la pantalla en dos columnas, la primera tiene 2/12 -->
        <div class="col-md-2">
            &nbsp;<!--Utiliza el espacio-->
        </div>
        <div class="col-md-10">
            <!-- Pasa los valores por un metodo post a la accion marcas.do, esta accion redirecciona a otra pagina. Para ver que hace marcas.do ver Struts-config-->
            <form class="form-horizontal" id="formRegistro" method='post' action='marcas.do'>
                <fieldset>
                    <!-- utilizamos el legend para mostrar el titulo-->
                    <legend>Marca</legend>

                    <div class="control-group">
                        <label class="control-label">Marca</label>
                        <div class="controls">
                            <input type="hidden" name="id" id="id" value="0" />
                            <input type="text" class="input-xlarge" id="marca" name="marca">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">Descripcion</label>
                        <div class="controls">
                            <input type="text" class="input-xlarge" id="descripcion" name="descripcion" rel="popover" data-content="Ingrese la descripcion" data-original-title="descripcion">
                        </div>
                    </div>


                    .............
                    .............
                    <!-- botonera: notar que la accion del metodo es mediante un input del tipo hidden, no es la forma mas elegante de hacerlo -->

                    <div class="control-group">
                        <label class="control-label"></label>
                        <div class="controls">
                            <input type="hidden" id="metodo" name="metodo" value="default"/> 
                            <input type="hidden" name="val"/>
                            <input type="submit" class="btn btn-success" onclick='document.getElementById("metodo").value = "guardar";'/>
                            <input type="reset" class="btn btn-warning"  value="Cancelar"/>
                            <input type="submit" class="btn btn-danger" onclick='document.getElementById("metodo").value = "eliminar";' value="Eliminar" />

                        </div>
                    </div>



                </fieldset> 
            </form>
            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="tbMarcas">
                <thead><th>ID</th><th>Marca</th><th>Descripcion</th></thead>
                <tbody>
                    <%  /*Seccion que obtiene el objeto marcasList en donde estan todas las marcas, lo recorre con un for y luego carga los valores en las celdas*/
                        MarcaVO lista = new MarcaVO();
                        if (lista != null) {
                            List<MarcaVO> marcasList = (List<MarcaVO>) lista.selectMarcaInfo();              
                            if (marcasList != null) {
                                for (MarcaVO marca : marcasList) {
                    %>
                    <tr>
                        <td><%=marca.getId()%></td>
                        <td><%=marca.getMarca()%></td>
                        <td><%=marca.getDescripcion()%></td>


                    </tr>
                    <%          }
                            }
                        }%>
                </tbody>

            </table>
            <script type="text/javascript" >
                /* Table initialisation, inicializa la tabla para poder darle la funcion del plugin de datatables
                 * Para mas informacion de este plugin ver datatables.net de Alan Jardine
                 * */
                $(document).ready(function() {
                    /*Cuando la pagina este lista a todos los objetos de ID tbMarcas aplicarle el plugin datatable*/
                    var oTable = $('#tbMarcas').dataTable({
                        "sDom": "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
                        /*"sPaginationType": "bootstrap",*/
                        /*"sAjaxSource" : "<%=request.getContextPath()%>/usuarios.do",*/
                        "oLanguage": {
                            "sLengthMenu": "_MENU_ records per page"
                        }
                    });
                    /*Por cada tr de la tabla agregar que en el evento click se copien los valores a los input correspondientes*/ 
                   
                    $("#tbMarcas tbody").delegate("tr", "click", function() {
                        var iPos = oTable.fnGetPosition(this);
                        if (iPos != null) {
                            //couple of example on what can be done with the clicked row...
                            var aData = oTable.fnGetData(iPos); //get data of the clicked row
                            var id = aData[0];
                            var marca = aData[1]; //get column data of the row
                            var descripcion = aData[2];
                            //userTable.fnDeleteRow(iPos);//delete row
                            $("#marca").val(marca);
                            $("#id").val(id);
                            $("#descripcion").val(descripcion);


                        }
                    });
                });
            </script>
        </div>
    </div>
</div>