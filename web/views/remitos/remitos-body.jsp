<%-- 
    Document   : remitos-body
    Created on : 25/05/2014, 21:20:35
    Author     : matts
--%>
<%@ page import="com.vigia.struts.vo.*,java.util.*"%>
<%
    HttpSession sess = request.getSession(false);

    List<RemitoVO> remitos = (List<RemitoVO>) request.getAttribute("remitos");

    /*Obtiene la session*/

%>

<div class="container well">

    <div class="row ">
        <div class="col-xs-6">
            <h1>
                <a href="#" id="title">
                    Remitos
                </a>
            </h1>
        </div>
    </div>
    <br/><br/>
    <form role="form" class="" action="/todo_hogar/remitos.do?action='buscar'" method="post">
        <div class="row">
            <div class=" col-xs-5">
                <input id="search" type="text" name="query" class="form-control" placeholder="Buscar por nombre/direccion/tipo de cliente">
            </div>
            <button type="submit" class="col-xs-1 btn btn-default">Buscar</button>

            <div class=" pull-right">
                <a class=" fancybox btn btn-success" href="/todo_hogar/views/remitos/remito.jsp">Agregar Nuevo</a>
            </div>
        </div>
    </form>

    <br/><br/>
    <div id="table-container" class="row">
        <table class="table table-bordered table-striped table-hover">
            <thead>
                <tr>
                    <th><h4>Fecha</h4></th>
            <th><h4>Emisor</h4></th>
            <th><h4>Receptor</h4></th>
            <th><h4>Destino</h4></th>
            <th><h4>Acciones</h4></th>
            </tr>              
            </thead>
            <tbody>
                <%  if (null == remitos || remitos.isEmpty()) {
                        remitos = (List<RemitoVO>) RemitoVO.all();
                    } 
                    if (remitos != null) {
                        for (RemitoVO remito : remitos) {
                %>
                <tr>
                    <td><%=remito.getFecha()%></td>
                    <td><%=remito.getNombreEmisor()%></td>
                    <td><%=remito.getNombreReceptor()%></td>
                    <td><%=remito.getDestino()%></td>
                    <td>
                        <a class="fancybox" href="#ver<%= remito.getRemitoId() %>">Ver</a>
                        <div style="display:none;">
                            <div style="padding: 20px;" id="ver<%= remito.getRemitoId() %>">
                                                        
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


<script type="text/javascript">
    $(document).ready(function() {
        $(".fancybox").fancybox({
            "width": 800,
            "height": 500,
        });
    });
</script>