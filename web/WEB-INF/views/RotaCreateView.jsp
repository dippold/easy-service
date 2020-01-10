<%-- 
    Document   : RotaCreateView.jsp
    Created on : 31/08/2017
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
        <!-- MAIN CONTAINER -->   
        <div id="main" class="container-fluid">

            <div class="row">
                <div class="col-md-6"><h2>${title}</h2></div>
            </div>

            <!-- FORM MAIN -->
            <form id="formCreate" name="formCreate" method="POST" action="mvc">
                <!-- VARIAVEIS-DE-CONTROLE-MVC -->
                <input type="hidden" id="model" name="model" value="${model}">
                <input type="hidden" id="controller" name="controller" value="${controller}">
                <input type="hidden" id="class" name="class" value="${controller}">
                <input type="hidden" id="do" name="do" value="create">
                <!-- /VARIAVEIS-DE-CONTROLE-MVC -->

                <!-- LINHA-1 (2 UNIDADES DE TELA) -->
                <div class="row">
                    <div class="form-group col-md-2">
                        <label for="processoNegocioInput">Processo de Negócio:</label>
                        <input type="text" class="form-control" id="processoNegocioInput" name="processoNegocioInput" max="20" required="required" placeholder="Processo de Negócio"
                               value="${entity.processoNegocio}">
                    </div>                   
                </div><!-- /LINHA-1 -->                 
                
                <!-- LINHA-2 (6 UNIDADES DE TELA) -->
                <div class="row">
                    <div class="form-group col-md-2">
                        <label for="grupoMenuInput">Agrupador Menu:</label>
                        <input type="text" class="form-control" id="grupoMenuInput" name="grupoMenuInput" max="20" required="required" placeholder="Agrupador do menu">
                    </div>
                    <div class="form-group col-md-2">
                        <label for="nomeMenuInput">Nome Menu:</label>
                        <input type="text" class="form-control" id="nomeMenuInput" name="nomeMenuInput" max="20" required="required" placeholder="Nome no menu">
                    </div>
                    <div class="form-group col-md-2">
                        <label for="mostrarNoMenuCombo">Mostrar no Menu?</label>                        
                        <!-- COMBOBOX MOSTRAR-NO-MENU -->
                        <SELECT id="mostrarNoMenuCombo" name="mostrarNoMenuCombo" size="1" class="form-control">
                            <option value="true">Sim</option> 
                            <option value="false">Não</option> 
                        </SELECT><!-- /COMBOBOX MOSTRAR-NO-MENU -->   
                    </div>                    
                </div><!-- /LINHA-2 -->           

                <!-- LINHA-3 (6 UNIDADES DE TELA) -->
                <div class="row">
                    <div class="form-group col-md-2">
                        <label for="recursoInput">Model:</label>
                        <input type="text" class="form-control" id="recursoInput" name="recursoInput" required="required" max="50" placeholder="Classe Model">
                    </div>
                    <div class="form-group col-md-2">
                        <label for="acaoCombo">Ação:</label>
                        <!-- COMBOBOX ACAO-NO-MODEL -->
                        <SELECT id="acaoCombo" name="acaoCombo" size="1" class="form-control">
                            <c:forEach var="action" items="${actions}">
                                <option value="${action.id}">${action.name}</option>
                            </c:forEach> 
                        </SELECT><!-- COMBOBOX ACAO-NO-MODEL -->
                    </div>
                    <div class="form-group col-md-2">
                        <label for="telaInput">View:</label>
                        <input type="text" class="form-control" id="telaInput" name="telaInput" required="required" max="50" placeholder="Página Jsp">
                    </div>                    
                </div><!-- /LINHA-3 -->                 

                <!-- LINHA-4 (6 UNIDADES DE TELA) -->
                <div class="row">
                    <div class="form-group col-md-2">
                        <label for="controladorInput">Controller:</label>
                        <input type="text" class="form-control" id="controladorInput" name="controladorInput" max="50" required="required" placeholder="Classe Controller">
                    </div>
                    <div class="form-group col-md-2">
                        <label for="acaoSucessoCombo">Ação Sucesso:</label>
                        <!-- COMBOBOX ACAO-NO-MODEL -->
                        <SELECT id="acaoSucessoCombo" name="acaoSucessoCombo" size="1" class="form-control">
                            <c:forEach var="action" items="${actions}">
                                <option value="${action.id}">${action.name}</option>
                            </c:forEach> 
                        </SELECT><!-- COMBOBOX ACAO-NO-MODEL -->
                    </div>                    
                    <div class="form-group col-md-2">
                        <label for="acaoFalhaCombo">Ação Falha:</label>
                        <!-- COMBOBOX ACAO-NO-MODEL -->
                        <SELECT id="acaoFalhaCombo" name="acaoFalhaCombo" size="1" class="form-control">
                            <c:forEach var="action" items="${actions}">
                                <option value="${action.id}">${action.name}</option>
                            </c:forEach> 
                        </SELECT><!-- COMBOBOX ACAO-NO-MODEL -->
                    </div>                                                             
                </div><!-- /LINHA-4 -->                

                <!-- LINHA-5 (6 UNIDADES DE TELA) -->
                <div class="row">
                    <div class="form-group col-md-2">
                        <label for="permissoesIdInput">Permissões:</label>
                        <input type="text" class="form-control" id="permissoesIdInput" name="permissoesIdInput" required="required" max="50"  placeholder="IDs entre vírgulas!">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="dicaTelaInput">Dica de Tela:</label>
                        <input type="text" class="form-control" id="dicaTelaInput" name="dicaTelaInput" required="required" max="100" placeholder="Dica de tela">
                    </div>                    
                </div><!-- /LINHA-5 -->

                <!-- LINHA-6 (6 UNIDADES DE TELA) -->
                <div class="row">
                    <div class="form-group col-md-2">
                        <label for="btnTypeInput">Tipo no Menu:</label>                        
                        <input type="text" class="form-control" id="btnTypeInput" name="btnTypeInput" required="required" max="20"  placeholder="Tipo de botão bootStract"
                               value="btn btn-primary">
                    </div>
                    <div class="form-group col-md-2">
                        <label for="iconInput">Ícone no Menu:</label>
                        <input type="text" class="form-control" id="iconInput" name="iconInput" required="required" max="20" placeholder="Tipo de ícone bootStract"
                               value="glyphicon-menu-right">
                    </div>                    
                </div><!-- /LINHA-6 -->                
                
                <!-- LINHA-7 (6 UNIDADES DE TELA) -->
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="observacaoInput">Observações:</label>
                        <textarea class="form-control" id="observacaoInput" name="observacaoInput" placeholder="Observações"
                                  rows="5" maxlength="250"></textarea>
                        <span id="contadorInputObservação" class="label label-warning">255 Restantes!</span>
                    </div>
                </div><!-- /LINHA-7 -->                  

                <!-- LINHA-8 : BUTTONS SAVE AND CANCEL  (6 UNIDADES DE TELA)-->
                <div class="row">
                    <div class="col-md-6">
                        <button type="submit" class="btn btn-primary">Salvar</button>
                        <a href="mvc?class=${model}&do=list" class="btn btn-default">Cancelar</a>
                    </div>
                </div><!-- /LINHA-8 -->
                
                <br><br>
                
            </form><!-- /FORM MAIN -->

            <!-- MESSAGE BAR -->
            <jsp:include page="../includes/MessageBarInclude.jsp" /> 
            <!-- /MESSAGE BAR -->

        </div> <!--/MAIN CONTAINER --> 
        
        <!-- CORE JAVASCRIPT LYBRARIES... -->
        <script type="text/javascript" src="assets/core/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="assets/core/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <!-- /CORE JAVASCRIPT LYBRARIES... -->
        
        <!-- DATA MODEL & EVENTS FUNCTIONS --> 
        <script type="text/javascript">
            $(document).ready(function () {

                $("#observacaoInput").keyup(
                        function () {
                            var limite = 255;
                            var caracteresDigitados = $(this).val().length;
                            var caracteresRestantes = limite - caracteresDigitados;
                            $("#contadorInputObservação").text(caracteresRestantes + " Restantes!");
                        }
                );

            });
        </script><!-- /DATA MODEL & EVENTS FUNCTIONS --> 
        
    </body><!-- BODY -->        
</html>
