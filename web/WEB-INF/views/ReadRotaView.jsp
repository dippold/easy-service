<%-- 
    Document   : ReadRotaView.jsp
    Created on : 01/09/2017
    Author     : Fabio Tavares Dippold
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <!-- HEAD -->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="PM APP">
        <meta name="author" content="Fábio Tavares Dippold">
        <link rel="icon" href="assets/icons/qb-icon.png">
        <title>${app}</title>
        <!-- Bootstrap -->
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap theme -->
        <link href="assets/core/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
    </head><!-- /HEAD -->
    <body>
        <!-- DIV MODAL -->
        <div class="modal fade" id="delete-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="modalLabel">
                            Excluir o ID
                            &nbsp;<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>&nbsp;
                            ${entity.id} 
                            &nbsp;<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>&nbsp;
                            ${entity.nomeMenu}</h4>
                    </div>
                    <div class="modal-body">
                        Deseja realmente excluir este item?
                    </div>
                    <div class="modal-footer">
                        <button id="btnDeleteYes" type="button" class="btn btn-primary">Sim</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
                    </div>
                </div>
            </div>
        </div><!-- /DIV MODAL -->        
        <div class="panel panel-default">
            <div class="panel-heading">
                ${title} 
                &nbsp;<span class="glyphicon glyphicon-hand-right" title="Model" style="color:blue"></span>&nbsp;
                ${entity.nomeMenu}
            </div>            
            <div class="panel-body">
                <p><span class="glyphicon glyphicon-th-list" title="Grupo no Menu" style="color:blue"></span>
                    ${entity.nomeGrupoMenu}
                    ${entity.sistema == 'true' ? '&nbsp;<span class="glyphicon glyphicon-alert" title="Registro de Sistema!" style="color:red"></span>' : ''}
                    ${entity.mostrarNoMenu == 'true' ? '&nbsp;<span class="glyphicon glyphicon-pushpin" title="Aparece no Menu!" style="color:red"></span>' : ''}                     
                </p>
                <p><span class="glyphicon glyphicon-list" title="Nome no Menu" style="color:blue"></span>&nbsp;${entity.nomeMenu}</p>
                <p><span class="glyphicon glyphicon-export" title="Model MVC" style="color:blue"></span>&nbsp;${entity.recurso}</p>                     
                <p><span class="glyphicon glyphicon-pencil" title="Ação MVC" style="color:blue"></span>&nbsp;${entity.acao}</p>
                <p><span class="glyphicon glyphicon-eye-open" title="View MVC" style="color:blue"></span>&nbsp;${entity.tela}</p>
                <p><span class="glyphicon glyphicon-import" title="Controlador MVC" style="color:blue"></span>&nbsp;${entity.controlador}</p>
                <p><span class="glyphicon glyphicon-thumbs-up" title="Ação MVC - Sucesso" style="color:blue"></span>&nbsp;${entity.acaoSucesso}</p>  
                <p><span class="glyphicon glyphicon-thumbs-down" title="Ação MVC - Falha" style="color:blue"></span>&nbsp;${entity.acaoFalha}</p>
                <p><span class="glyphicon glyphicon-tag" title="Dica na Tela" style="color:blue"></span>&nbsp;${entity.dicaTela}</p>
                <p><span class="glyphicon glyphicon-comment" title="Observação de Utilização" style="color:blue"></span>&nbsp;${entity.observacao}</p>

            </div>
        </div>

        <div id="main" class="container-fluid">

            <!-- LINHA-2 -->                
            <div id="actions" class="row">
                <div class="col-md-12"> 
                    &nbsp;
                    <a href="#" class="btn btn-danger" data-toggle="modal" data-target="#delete-modal">Excluir</a>
                    <a href="mvc?class=${model}&do=list" class="btn btn-default">Cancelar</a>
                </div>
            </div><!-- /LINHA-2 -->       

            <!-- MESSAGE BAR -->
            <br>
            <jsp:include page="../includes/MessageBarInclude.jsp" /> 
            <!-- /MESSAGE BAR -->

            <form id="formDelete" method="POST" action="mvc">
                <input type="hidden" id="model" name="model" value="${model}">
                <input type="hidden" id="controller" name="controller" value="${controller}">
                <input type="hidden" id="class" name="class" value="${controller}">
                <input type="hidden" id="do" name="do" value="delete">
                <input type="hidden" id="id" name="id" value="${entity.id}">
            </form>         

        </div> <!--/MAIN CONTAINER -->

        <!-- CORE JAVASCRIPT LYBRARIES...
        ================================================== -->
        <script type="text/javascript" src="assets/core/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="assets/core/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <!-- DATA MODEL & EVENTS FUNCTIONS ... -->
        <script type="text/javascript">
            $(document).ready(function () {
                $("#btnDeleteYes").click(function () {
                    $("#formDelete").submit();
                });
            });
        </script><!-- /DATA MODEL & EVENTS FUNCTIONS -->
    </body><!-- BODY -->        
</html>
