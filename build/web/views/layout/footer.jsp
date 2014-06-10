<%-- 
    Document   : footer
    Created on : 29-mar-2014, 22:23:30
    Author     : hsendoa
    Desc       : Pagina contenedora del footer
--%>

<div class="footer">
    Todos los derechos reservados - Universidad Autonoma de Encarnacion 2014
</div>

<script type="text/javascript">
    $(document).ready(function(){
        $("a[data-confirm]").on('click', function(){
            if($(this).data('confirm') == true){
                var msg = "¿Está seguro?";
            }else{
                var msg = $(this).data('confirm');
            }
           return confirm(msg);
        }); 
    });
</script>
