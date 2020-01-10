<%-- 
    Document   : SystemAdminInclude
    Created on : 01/09/2017
    Author     : Fabio Tavares Dippold
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- DIV MODAL SAIR -->
<div class="modal fade" id="sair-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modalLabel">Ação: SAIR</h4>
            </div>
            <div class="modal-body">Deseja sair do sistema?</div>
            <div class="modal-footer">
                <button id="btnSair" type="button" class="btn btn-primary" onclick="javascript:$('#formSair').submit();">Sim</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
            </div>
        </div>
    </div>
</div><!-- /DIV MODAL SAIR-->

<!-- DIV MODAL ABOUT -->
<div class="modal fade" id="about-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modalLabel">Sobre o sistema</h4>
            </div>
            <div class="modal-body">
                <span class="glyphicon glyphicon-apple"></span>&nbsp; Produto: Easy Service<br>
                <span class="glyphicon glyphicon-info-sign"></span>&nbsp; Versão: 2.0.1<br>
                <span class="glyphicon glyphicon-refresh"></span>&nbsp; Release 2017/07/29<br>
                <span class="glyphicon glyphicon-user"></span>&nbsp; Autor: Fábio Tavares Dippold<br>
                <span class="glyphicon glyphicon-envelope"></span>&nbsp; <a href="mailto:dippold.br@gmail.com">dippold.br@gmail.com</a><br>
                <span class="glyphicon glyphicon-home"></span>&nbsp; FTD Software Engineer
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
            </div>
        </div>
    </div>
</div><!-- /DIV MODAL ABOUT-->

<!-- DIV TITLE -->
<div class="row">
    <div class="col-md-12">
        <h4>
            &nbsp;&nbsp;&nbsp;
            ${app} 
            <span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
            ${title} 
        </h4>
    </div>
</div><!-- /DIV TITLE -->

<!-- DIV MENU -->
<div class="row">
    <div class="col-md-12">
        &nbsp;&nbsp;&nbsp;&nbsp;
        <c:if test="${showBtnCreate == true}">
            <a href="mvc?class=HomeModel&do=list" class="btn btn-default btn-sm" title="VOLTAR TELA INICIAL">
                <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
            </a>            
        </c:if>

        <c:if test="${showBtnChangeProfile == true}">
            <div class="btn-group">
                <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" title="PERFIL SELECIONADO" aria-haspopup="true" aria-expanded="false">
                    ${profileName}
                    <span class="caret"></span>
                </button>                
                <ul class="dropdown-menu">
                    <c:forEach var="profile" items="${userProfiles}">
                        <li><a href="mvc?class=HomeModel&do=list&perfil=${profile.id}">${profile.name}</a></li>
                    </c:forEach> 
                </ul>
            </div>
        </c:if>

        <c:if test="${showBtnCreate == true}">
            <a href="mvc?class=${model}&do=create" class="btn btn-primary btn-sm" title="ADICIONAR NOVO REGISTRO">Novo</a>
        </c:if>

        <c:if test="${showBtnMsg == true}">
            <c:if test="${nrMsgs == 0}">
                <a href="mvc?class=HomeModel&do=list" class="btn btn-default btn-sm" title="MENSAGENS">
                    <span class="glyphicon glyphicon-bell" aria-hidden="true"></span>&nbsp;${userNickName}&nbsp;(${nrMsgs})&nbsp;
                </a>
            </c:if>
            <c:if test="${nrMsgs > 0}">
                <a href="mvc?class=HomeModel&do=list" class="btn btn-info btn-sm" title="MENSAGENS">
                    <span class="glyphicon glyphicon-bell" aria-hidden="true"></span>&nbsp;${userNickName}&nbsp;(${nrMsgs})&nbsp;
                </a>
            </c:if>
        </c:if>

        <c:if test="${showBtnAbout == true}">
            <a id="linkAbout" href="#" class="btn btn-default btn-sm" data-toggle="modal" data-target="#about-modal" title="INF. SOBRE O SISTEMA">Sobre</a>  
        </c:if>

        <c:if test="${showBtnLogout == true}">
            <a id="linkSair" href="#" class="btn btn-default btn-sm" data-toggle="modal" data-target="#sair-modal" title="SAIR">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
            </a> 
        </c:if>    

        <!-- DIV MENSAGEM -->
        <c:if test="${!msg.equals('')}">
            &nbsp;&nbsp;&nbsp;<span class="label label-success">${msg}</span></div>  
        </c:if> 
    <!-- /DIV MENSAGEM --> 

</div> <!-- DIV MENU -->

<!-- FORM TO SIGNOUT -->
<form id="formSair" name="formSair" method="POST" action="mvc?class=LogOutModel"></form> 
<!-- /FORM TO SIGNOUT -->  

