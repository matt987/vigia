<%-- 
    Document   : clientes-new-body
    Created on : 28/04/2014, 10:16:08
    Author     : matts
--%>

<%@page import="java.util.List"%>
<%@page import="com.vigia.struts.vo.ClienteVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sess = request.getSession(false);
    
    List<ClienteVO> clientes = (List<ClienteVO>)request.getAttribute("clientes");
    
    ClienteVO clienteVO = new ClienteVO();
    if (clienteVO != null) {
        clientes = (List<ClienteVO>) clienteVO.selectClienteInfo(); 
    }
%>
<tiles:insert definition="company-template" >
    <div class="container well">
        <div class="row ">
            <div class="col-xs-6">
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
        <br/><br/>
        
    </div>
    
    <script type="text/javascript">
        
        function getClientes(){
            var colArray = []
            <% for (ClienteVO cliente : clientes) { %>
                colArray[<%= cliente.getCi() %>] = "<%= cliente.getCi() %>"; 
            <% } %>
            return colArray;
        }
        
        function validateUniquenness(list, element){
            if(list.indexOf(element.val()) !== -1){
                $("#" + element.attr("id") + "-error").show();
                $("#submit-aceptar").attr("disabled", "disabled");
            }else{
                $("#" + element.attr("id") + "-error").hide();
                $("#submit-aceptar").removeAttr("disabled");
            }
        }

        $(document).ready(function(){
           var clientes = getClientes();
           //$(".error").hide();
           //$('#cedula').on({
           //  blur: function(){{
           //         if($("#cedula").val().length > 5){
           //           validateUniquenness(clientes, $("#cedula"));
            //        }
           //      }}
           //});
        });
        
    </script>
</tiles:insert>
