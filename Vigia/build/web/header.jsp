<%-- 
    Document   : header
    Created on : 29-mar-2014, 22:22:56
    Author     : hsendoa
    Desc.      : Header de la pagina, aqui se arma el menu.
--%>
<%@page import="com.vigia.struts.vo.Menu"%>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="css/awesome.css" rel="stylesheet" type="text/css"/>
<link href="css/style.css" rel="stylesheet" type="text/css"/>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/twitter-bootstrap-hover-dropdown.js" type="text/javascript"></script>




<!-- tanto los divs como los css de la pagina fueron extraidos de los ejemplos de bootstrap -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="navbar-collapse">
                <span class="sr-only">Men&uacute; de Navegaci&oacute;n</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="sucess.jsp">Vigia</a>
        </div>
        <div class="collapse navbar-collapse">
            <%
                //script para control de sesion
                HttpSession s = request.getSession(false);
                String sessionId = (String) s.getAttribute("usuario");
                if (sessionId != null && sessionId != "") {
                    Menu m = new Menu(sessionId);
                    out.print(m.getMenu());
                }
            %> 
        </div>

        


    </div><!-- /.container-fluid -->
</nav>
        <br/><br/><br/>
<script type="text/javascript">
    $('.dropdown-toggle').dropdownHover();
</script>
