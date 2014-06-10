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
                    Nuevo Proveedor
                </a>
              </h1>
                
              <form role="form" class="" action="/todo_hogar/proveedores.do?action='crear'" method="post">
                <div class="row">
                    <div>
                      <label for="ruc">RUC</label>                        
                      <input id="ruc" type="number" name="ruc" maxlength="30" validate="true" required="true" class="form-control" placeholder="RUC">
                    </div><br> 
                    <div>
                      <label for="nombre">Nombre</label>
                      <input id="nombre" type="text" name="nombre" maxlength="30" validate="true" required="true" class="form-control" placeholder="Nombre" >
                    </div><br>
                    <div>
                      <label for="direccion">Direcci&oacute;n</label>                        
                      <input id="direccion" type="text" name="direccion" maxlength="100" validate="true" required="true" class="form-control" placeholder="Direcci&oacute;n" >
                    </div><br>     
                    <div>
                        <label for="telefono">T&eacute;lefono</label>                        
                      <input id="telefono" type="text" name="telefono" maxlength="30" validate="true" class="form-control" placeholder="Tel&eacute;fono">
                    </div><br>      
                    <div>
                        <label for="telefono">Email</label>                        
                      <input id="email" type="email" name="email" maxlength="50" validate="true" class="form-control" placeholder="Email" >
                    </div><br>      
                    <div>
                        <label for="telefono">P&aacute;gina Web</label>                        
                        <input id="pagina-web" type="text" name="paginaWeb" maxlength="50" class="form-control" placeholder="P&aacute;gina Web" >
                    </div><br>                    
                    
                    
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="proveedores.jsp">Cancelar</a>    
                    <button type="submit" class="btn btn-success">Aceptar</button>
                </div>
              </form>
            </div>
          </div>
        <br/><br/>
        
    </div>
</tiles:insert>
