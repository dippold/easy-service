<%-- 
    Document   : UserCreateView.jsp
    Created on : 31/08/2017
    Author     : Fabio Tavares Dippold
--%>

<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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


                <!-- LINHA-1 (8 UNIDADES DE TELA) -->
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="comboCompany">Empresa:</label>
                        <!-- COMBOBOX COMPANY -->
                        <SELECT id="comboCompany" name="comboCompany" size="1" class="form-control">
                            <c:forEach var="company" items="${companies}">
                                <option value="${company.id}">${company.name}</option>
                            </c:forEach>                                    
                        </SELECT><!-- /COMBOBOX COMPANY -->                      
                    </div>
                    <div class="form-group col-md-4">
                        <label for="nameInput">Nome:</label>
                        <input type="text" class="form-control" id="nameInput" name="nameInput" required="required" placeholder="Digite o nome">
                    </div>
                </div><!-- LINHA-1 -->                

                <!-- LINHA-2 (8 UNIDADES DE TELA) -->
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="emailInput">E-mail:</label>
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1">@</span>
                            <input type="email" class="form-control" id="emailInput" name="emailInput" required="required" placeholder="Digite um e-mail"  aria-describedby="basic-addon1">
                        </div>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="passwdInput">Senha:</label>
                        <input type="password" class="form-control" id="passwdInput" name="passwdInput" required="required" placeholder="Digite uma senha">
                    </div>                   
                </div><!-- /LINHA-2 -->           

                <!-- LINHA-3 (8 UNIDADES DE TELA) -->
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="profileIdsInput">Perfis IDs:</label>
                        <input type="text" class="form-control" id="profileIdsInput" name="profileIdsInput" required="required" max="50" placeholder="Classe Model">
                        <span id="contadorProfileIdsInput" class="label label-warning">50 Restantes!</span>
                    </div>
                    
                    <div class="form-group col-md-2">
                        <label for="comboSystem">Sistema?:</label>                        
                        <!-- COMBOBOX SYSTEM -->
                        <SELECT id="comboSystem" name="comboSystem" size="1" class="form-control">
                            <option value="true">Sim</option> 
                            <option value="false">Não</option> 
                        </SELECT><!-- /COMBOBOX SYSTEM -->   
                    </div>                    
                    <div class="form-group col-md-2">
                        <label for="comboBlocked">Bloqueado?:</label>
                        <!-- COMBOBOX BLOCKED -->
                        <SELECT id="comboBlocked" name="comboBlocked" size="1" class="form-control">
                            <option value="true">Sim</option> 
                            <option value="false">Não</option> 
                        </SELECT><!-- /COMBOBOX BLOCKED -->   
                    </div>                                        
                </div><!-- /LINHA-3 -->                                

                <!-- LINHA-7 : BUTTONS SAVE AND CANCEL  (6 UNIDADES DE TELA)-->
                <div class="row">
                    <div class="col-md-8">
                        <button type="submit" class="btn btn-primary">Salvar</button>
                        <a href="mvc?class=${model}&do=list" class="btn btn-default">Cancelar</a>
                    </div>
                </div><!-- /LINHA-7 -->

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

                $("#profileIdsInput").keyup(
                        function () {
                            var limite = 50;
                            var caracteresDigitados = $(this).val().length;
                            var caracteresRestantes = limite - caracteresDigitados;
                            $("#contadorProfileIdsInput").text(caracteresRestantes + " Restantes!");
                        }
                );

            });
        </script><!-- /DATA MODEL & EVENTS FUNCTIONS --> 

    </body><!-- BODY -->        
</html>
