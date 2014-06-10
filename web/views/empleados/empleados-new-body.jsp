<%-- 
    Document   : clientes-new-body
    Created on : 28/04/2014, 10:16:08
    Author     : matts
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<tiles:insert definition="company-template" >
    <div class="container well">
        <div class="row ">
            <div class="col-xs-6">
              <h1>
                <a href="#" id="title">
                    Nuevo Empleado
                </a>
              </h1>
                
              <form role="form" class="" action="/todo_hogar/empleados.do?action='crear'" method="post">
                <div class="row">
                    <div>
                      <label for="nombre">Nombre</label>
                      <input id="nombre" type="text" name="nombre" maxlength="30" validate="true" required="true" class="form-control" placeholder="Nombre" >
                    </div><br>
                    <div>
                      <label for="apellido">Apellido</label>                        
                      <input id="apellido" type="text" name="apellido" maxlength="30" validate="true" required="true" class="form-control" placeholder="Apellido" >
                    </div><br>
                    <div>
                      <label for="direccion">Direcci&oacute;n</label>                        
                      <input id="direccion" type="text" name="direccion" maxlength="100" validate="true" required="true" class="form-control" placeholder="Direcci&oacute;n" >
                    </div><br> 
                    <div>
                      <label for="cedula">C&eacute;dula</label>                        
                      <input id="cedula" type="number" name="ci" maxlength="20" validate="true" required="true" class="form-control" placeholder="C&eacute;dula">
                    </div><br>   
                    <div>
                      <label for="ruc">RUC</label>                        
                      <input id="ruc" type="number" name="ruc" maxlength="30" validate="true" required="true" class="form-control" placeholder="RUC">
                    </div><br>   
                    <div>
                        <label for="telefono">T&eacute;lefono</label>                        
                      <input id="telefono" type="text" name="telefono" maxlength="30" validate="true" class="form-control" placeholder="Tel&eacute;fono">
                    </div><br>                       
                    
                    
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="clientes.jsp">Cancelar</a>    
                    <button type="submit" class="btn btn-success">Aceptar</button>
                </div>
              </form>
            </div>
          </div>
        <br/><br/>
        
    </div>
</tiles:insert>
