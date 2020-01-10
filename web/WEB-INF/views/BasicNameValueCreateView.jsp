<%-- 
    Document   : BasicNameValueCreateView.jsp
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
                <input type="hidden" id="model" name="model" value="${model}">
                <input type="hidden" id="controller" name="controller" value="${controller}">
                <input type="hidden" id="class" name="class" value="${controller}">
                <input type="hidden" id="do" name="do" value="create">

                <!-- LINHA-1 -->
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="chaveInput">Chave:</label>
                        <input type="text" class="form-control" id="chaveInput" name="chaveInput" max="100" required="required" placeholder="Chave de configuração">
                        <span id="contadorInputChave" class="label label-warning">100 Restantes!</span>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="sistemaCombo">Sistema?</label>                        
                        <!-- COMBOBOX SISTEMA -->
                        <SELECT id="sistemaCombo" name="sistemaCombo" size="1" class="form-control">
                            <option value="true">Sim</option> 
                            <option value="false">Não</option> 
                        </SELECT><!-- /COMBOBOX SISTEMA -->   
                    </div>                    
                </div><!-- /LINHA-1 -->           

                <!-- LINHA-2 -->
                <div class="row">
                    <div class="form-group col-md-8">
                        <label for="valorInput">Valor</label>
                        <textarea class="form-control" id="valorInput" name="valorInput" placeholder="Valor(es)..."
                                  rows="5" maxlength="255"></textarea>
                        <span id="contadorInputValor" class="label label-warning">255 Restantes!</span>
                    </div>
                </div><!-- /LINHA-2 -->                   

                <!-- LINHA-3 : BUTTONS SAVE AND CANCEL -->
                <div class="row">
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-primary">Salvar</button>
                        <a href="mvc?class=${model}&do=list" class="btn btn-default">Cancelar</a>
                    </div>
                </div><!-- /LINHA-3 -->
                <br><br>
            </form><!-- /FORM MAIN -->

            <!-- MESSAGE BAR -->
            <br>
            <jsp:include page="../includes/MessageBarInclude.jsp" /> 
            <!-- /MESSAGE BAR -->

        </div> <!--/MAIN CONTAINER -->        
        <!-- CORE JAVASCRIPT LYBRARIES...
        ================================================== -->
        <script type="text/javascript" src="assets/core/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="assets/core/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {

                $("#chaveInput").keyup(
                        function () {
                            var limite = 100;
                            var caracteresDigitados = $(this).val().length;
                            var caracteresRestantes = limite - caracteresDigitados;
                            $("#contadorInputChave").text(caracteresRestantes + " Restantes!");
                        }
                );
        
                $("#valorInput").keyup(
                        function () {
                            var limite = 255;
                            var caracteresDigitados = $(this).val().length;
                            var caracteresRestantes = limite - caracteresDigitados;
                            $("#contadorInputValor").text(caracteresRestantes + " Restantes!");
                        }
                );

            });
        </script><!-- /DATA MODEL & EVENTS FUNCTIONS -->          
    </body><!-- BODY -->        
</html>
