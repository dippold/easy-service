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
        <!-- MAIN CONTAINER -->   
        <div id="main" class="container"> 
            <!-- SIMPLE MENU BAR -->            
            <jsp:include page="../includes/SystemAdminInclude.jsp" /> 
            <!-- /SIMPLE MENU BAR -->

            <!-- DIV-PORTLETS -->
            <div id="portlets" class="row">                
                <br>
                <div class="col-md-4 margin-bottom-30">
                    <!-- BEGIN Portlet PORTLET-->
                    <div class="portlet">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="glyphicon glyphicon-calendar"></i>&nbsp;&nbsp;
                                <span class="caption-subject text-uppercase">Base de Dados</span>
                                <span class="caption-helper">Contagens...</span>
                            </div>
                            <div class="actions"></div>
                        </div>
                        <div class="portlet-body">
                            <h4>Indicadores</h4>
                            <ul class="list-group">
                                <li class="list-group-item"><span class="badge">${userCount}</span>Usuários</li>
                                <li class="list-group-item"><span class="badge">${ruleCount}</span>Perfis</li>
                                <li class="list-group-item"><span class="badge">${profileCount}</span>Papéis</li>
                                <li class="list-group-item"><span class="badge">${configKeyCount}</span>Chaves</li>
                                <li class="list-group-item"><span class="badge">${rotaCount}</span>Rotas</li>
                            </ul>

                        </div>
                    </div>
                    <!-- END Portlet PORTLET-->
                </div>

                <div class="col-md-4 margin-bottom-30">
                    <!-- BEGIN Portlet PORTLET-->
                    <div class="portlet">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="glyphicon glyphicon-calendar"></i>&nbsp;&nbsp;
                                <span class="caption-subject text-uppercase">Base de Dados</span>
                                <span class="caption-helper">Contagens...</span>
                            </div>
                            <div class="actions"></div>
                        </div>
                        <div class="portlet-body">
                            <h4>Indicadores</h4>
                            <ul class="list-group">
                                <li class="list-group-item"><span class="badge">${userCount}</span>Usuários</li>
                                <li class="list-group-item"><span class="badge">${ruleCount}</span>Perfis</li>
                                <li class="list-group-item"><span class="badge">${profileCount}</span>Papéis</li>
                                <li class="list-group-item"><span class="badge">${configKeyCount}</span>Chaves</li>
                                <li class="list-group-item"><span class="badge">${rotaCount}</span>Rotas</li>
                            </ul>
                        </div>
                    </div>
                    <!-- END Portlet PORTLET-->
                </div> 
                                
                <div class="col-md-4 margin-bottom-30">
                    <!-- BEGIN Portlet PORTLET-->
                    <div class="portlet">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="glyphicon glyphicon-calendar"></i>&nbsp;&nbsp;
                                <span class="caption-subject text-uppercase"> Portlet</span>
                                <span class="caption-helper">weekly stats...</span>
                            </div>
                            <div class="actions">
                                <a href="javascript:;" class="btn">
                                    <i class="glyphicon glyphicon-pencil"></i>
                                    Edit 
                                </a>
                                <a href="javascript:;" class="btn">
                                    <i class="glyphicon glyphicon-paperclip"></i>
                                    Add
                                </a>
                                <a href="javascript:;" class="btn btn-circle">
                                    <i class="glyphicon glyphicon-resize-full"></i>
                                </a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <h4>Heading Text</h4>
                            <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. consectetur purus sit amet fermentum. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum.</p>
                        </div>
                    </div>
                    <!-- END Portlet PORTLET-->
                </div>
                                
                <div class="col-md-4 margin-bottom-30">
                    <!-- BEGIN Portlet PORTLET-->
                    <div class="portlet">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="glyphicon glyphicon-calendar"></i>&nbsp;&nbsp;
                                <span class="caption-subject text-uppercase"> Portlet</span>
                                <span class="caption-helper">weekly stats...</span>
                            </div>
                            <div class="actions">
                                <a href="javascript:;" class="btn">
                                    <i class="glyphicon glyphicon-pencil"></i>
                                    Edit 
                                </a>
                                <a href="javascript:;" class="btn">
                                    <i class="glyphicon glyphicon-paperclip"></i>
                                    Add
                                </a>
                                <a href="javascript:;" class="btn btn-circle">
                                    <i class="glyphicon glyphicon-resize-full"></i>
                                </a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <h4>Heading Text</h4>
                            <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. consectetur purus sit amet fermentum. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum.</p>
                        </div>
                    </div>
                    <!-- END Portlet PORTLET-->
                </div>
                                
            </div><!-- /DIV-PORTLETS -->

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