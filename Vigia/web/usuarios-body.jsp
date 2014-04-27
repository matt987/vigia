<%-- 
    Document   : usuarios-body
    Created on : 07-abr-2014, 12:58:57
    Author     : hsendoa
--%>
<%@ page import="com.vigia.struts.vo.*,java.util.*"%>
<%
    HttpSession sess = request.getSession(false);


%>

<div class="container">
    <div class="row well">
        <div class="col-md-2">
            &nbsp;
        </div>
        <div class="col-md-10">
            <form class="form-horizontal" id="formRegistro" method='post' action='usuarios.do'>
                <fieldset>

                    <legend>Usuarios</legend>

                    <div class="control-group">
                        <label class="control-label">Usuario</label>
                        <div class="controls">
                            <input type="hidden" name="id" id="id" value="0" />
                            <input type="text" class="input-xlarge" id="usuario" name="usuario">
                        </div>
                    </div>


                    <div class="control-group">
                        <label class="control-label">Password</label>
                        <div class="controls">
                            <input type="password" class="input-xlarge" id="password" name="password" rel="popover" data-content="Ingrese el password" data-original-title="password">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">Confirme el Password</label>
                        <div class="controls">
                            <input type="password" class="input-xlarge" id="passwordConfirm" name="passwordConfirm" rel="popover" data-content="Confirme el password" data-original-title="passwordConfirm">
                        </div>
                    </div>

                    .............
                    .............

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
            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="tbUsuarios">
                <thead><th>ID</th><th>Usuario</th></thead>
                <tbody>
                    <%  UserVO lista = new UserVO((String) sess.getAttribute("usuario"));
                        if (lista != null) {
                            List<UserVO> usuariosList = (List<UserVO>) lista.selectUsuariosInfo();
                            if (usuariosList != null) {
                                for (UserVO usuario : usuariosList) {
                    %>
                    <tr>
                        <td><%=usuario.getId()%></td>
                        <td><%=usuario.getNombre()%></td>

                    </tr>
                    <%          }
                            }

                        }%>
                </tbody>

            </table>
            <script type="text/javascript" >
                /* Table initialisation */
                $(document).ready(function() {
                    var oTable = $('#tbUsuarios').dataTable({
                        "sDom": "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
                        "oLanguage": {
                            "sLengthMenu": "_MENU_ records per page"
                        }
                    });
                    
                    $("#tbUsuarios tbody").delegate("tr", "click", function() {
                        var iPos = oTable.fnGetPosition(this);
                        if (iPos != null) {
                            //couple of example on what can be done with the clicked row...
                            var aData = oTable.fnGetData(iPos); //get data of the clicked row
                            var id = aData[0];
                            var usuario = aData[1]; //get column data of the row
                            $("#usuario").val(usuario);
                            $("#id").val(id);
                            
                        }
                    });
                });
            </script>



        </div>
    </div>
</div>