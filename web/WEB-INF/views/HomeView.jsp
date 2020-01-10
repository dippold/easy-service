<%-- 
    Document   : HomeView
    Created on : 30/08/2017
    Author     : Fabio Tavares Dippold
    Versio     : 0.0.3
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <!-- HEAD -->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="${app}">
        <meta name="author" content="Fábio Tavares Dippold">

        <link rel="icon" href="assets/icons/qb-icon.png">
        <title>${app}</title>

        <!-- Bootstrap -->
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
    </head><!-- /HEAD -->

    <body>
        <!-- SIMPLE MENU BAR -->            
        <jsp:include page="../includes/SystemAdminInclude.jsp" /> 
        <!-- /SIMPLE MENU BAR -->

        <!-- MAIN CONTAINER -->   
        <div id="main" class="container">
            <br>
            <c:forEach var="processo" items="${processos}">
                &nbsp;&nbsp;${processo.name}
                <div class="row">
                    <c:forEach var="rota" items="${rotas}">
                        <c:if test="${processo.name == rota.processoNegocio}">
                            <div class="product_gallery col-lg-2 col-md-4 col-sm-6 col-xs-12">
                                <a href="mvc?class=${rota.recurso}&do=${rota.acao}" class="${rota.btnType} btn-lg btn-block btn-huge" title="${rota.dicaTela}">
                                    <span class="glyphicon ${rota.icon}" aria-hidden="true"></span>&nbsp;
                                    ${rota.nomeMenu}</a>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
                <br>
            </c:forEach>                
        </div><!--/MAIN CONTAINER -->

        <!-- CORE JAVASCRIPT LYBRARIES -->
        <script type="text/javascript" src="assets/core/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="assets/core/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <!-- / CORE JAVASCRIPT LYBRARIES -->

        <!-- DATA MODEL & EVENTS FUNCTIONS --> 
        <script>
            $(document).ready(function () {
            });
        </script><!-- /DATA MODEL & EVENTS FUNCTIONS --> 

    </body>
</html>