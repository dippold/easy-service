<%-- 
    Document   : KeyValueUpdateView.jsp
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
                <input type="hidden" id="do" name="do" value="update">
                <input type="hidden" id="id" name="id" value="${entity.id}">

                <!-- LINHA-1 -->
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="keyInput">${fieldKeyLabel}:</label>
                        <c:if test="${fieldKeyType == 'input'}">
                            <input type="text" class="form-control" id="keyInput" name="keyInput" max="${fieldKeyMaxSize}" required="required" placeholder="${fieldKeyPlaceHolder}" value="${entity.key}">                       
                        </c:if>                        
                        <c:if test="${fieldKeyType == 'textArea'}">
                            <textarea class="form-control" id="keyInput" name="keyInput" placeholder="${fieldKeyPlaceHolder}" rows="${fieldKeyRow}" maxlength="${fieldKeyMaxSize}">${entity.key}</textarea>                        
                        </c:if>
                        <span id="countInputKey" class="label label-warning">100 Restantes!</span>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="systemCombo">Sistema?</label>                        
                        <!-- COMBOBOX SISTEMA -->
                        <SELECT id="systemCombo" name="systemCombo" size="1" class="form-control">
                            <c:if test="${entity.system == true}">
                                <option value="true" selected>Sim</option> 
                                <option value="false">Não</option>
                            </c:if> 
                            <c:if test="${entity.system == false}">
                                <option value="true">Sim</option> 
                                <option value="false" selected>Não</option>
                            </c:if> 
                        </SELECT><!-- /COMBOBOX SISTEMA -->   
                    </div>                    
                </div><!-- /LINHA-1 -->           

                <!-- LINHA-2 -->
                <div class="row">
                    <div class="form-group col-md-8">
                        <label for="valueInput">${fieldValueLabel}:</label>
                        <c:if test="${fieldValueType == 'input'}">
                            <input type="text" class="form-control" id="valueInput" name="valueInput" max="${fieldValueMaxSize}" required="required" placeholder="${fieldValuePlaceHolder}" value="${entity.value}">                       
                        </c:if>                        
                        <c:if test="${fieldValueType == 'textArea'}">
                            <textarea class="form-control" id="valueInput" name="valueInput" placeholder="${fieldValuePlaceHolder}" rows="${fieldValueRow}" maxlength="${fieldValueMaxSize}">${entity.value}</textarea>                        
                        </c:if>                                  
                        <span id="countInputValue" class="label label-warning">255 Restantes!</span>
                    </div>
                </div><!-- /LINHA-2 -->                   

                <!-- LINHA-3 -->
                <div class="row">
                    <div class="form-group col-md-8">
                        <label for="descriptionInput">${fieldDescriptionLabel}:</label>
                        <c:if test="${fieldDescriptionType == 'input'}">
                            <input type="text" class="form-control" id="descriptionInput" name="descriptionInput" max="${fieldDescriptionMaxSize}" required="required" placeholder="${fieldDescriptionPlaceHolder}" value="${descriptionValue}">                       
                        </c:if>                        
                        <c:if test="${fieldValueType == 'textArea'}">
                            <textarea class="form-control" id="descriptionInput" name="descriptionInput" placeholder="${fieldDescriptionPlaceHolder}" rows="${fieldDescriptionRow}" maxlength="${fieldDescriptionMaxSize}">${descriptionValue}</textarea>                        
                        </c:if>                         
                        <span id="countInputDescription" class="label label-warning">${fieldDescriptionMaxSize} Restantes!</span>
                    </div>
                </div><!-- /LINHA-3 -->                

                <!-- LINHA-4 : BUTTONS SAVE AND CANCEL -->
                <div class="row">
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-primary">Salvar</button>
                        <a href="mvc?class=${model}&do=list" class="btn btn-default">Cancelar</a>
                    </div>
                </div><!-- /LINHA-4 -->
                <br><br>
            </form><!-- /FORM MAIN -->

            <!-- MESSAGE BAR -->
            <jsp:include page="../includes/MessageBarInclude.jsp" /> 
            <!-- /MESSAGE BAR -->

        </div> <!--/MAIN CONTAINER -->        
        <!-- CORE JAVASCRIPT LYBRARIES...
        ================================================== -->
        <script type="text/javascript" src="assets/core/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="assets/core/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {

                $("#keyInput").keyup(
                        function () {
                            var limite = ${fieldKeyMaxSize};
                            var caracteresDigitados = $(this).val().length;
                            var caracteresRestantes = limite - caracteresDigitados;
                            $("#countInputKey").text(caracteresRestantes + " Restantes!");
                        }
                );

                $("#valueInput").keyup(
                        function () {
                            var limite = ${fieldValueMaxSize};
                            var caracteresDigitados = $(this).val().length;
                            var caracteresRestantes = limite - caracteresDigitados;
                            $("#countInputValue").text(caracteresRestantes + " Restantes!");
                        }
                );

                $("#descriptionInput").keyup(
                        function () {
                            var limite = ${fieldDescriptionMaxSize};
                            var caracteresDigitados = $(this).val().length;
                            var caracteresRestantes = limite - caracteresDigitados;
                            $("#countInputDescription").text(caracteresRestantes + " Restantes!");
                        }
                );

            });
        </script><!-- /DATA MODEL & EVENTS FUNCTIONS -->          
    </body><!-- BODY -->        
</html>
