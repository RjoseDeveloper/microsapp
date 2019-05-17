<%-- 
    Document   : client
    Created on : Feb 22, 2019, 3:15:39 PM
    Author     : Raimundo Jose
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="app.model.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="app.controller.ClienteJpaController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Client</title>
        <%
            if (session.getAttribute("role_name").equals("ADMIN")) {%>
        <jsp:include page="../fragments/header_admin.jsp"/>

        <link href="../easyDTable/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
        <link href="../easyDTable/css/bootstrap.min.css"  rel="stylesheet">
        <link href="../easyDTable/css/bootstrap-theme.min.css"  rel="stylesheet">
        <link href="../easyDTable/css/font-awesome.min.css" rel="stylesheet">
        <link href="../easyDTable/easyTable.css"  rel="stylesheet">



    </head>
    <body class="animsition">


        <div class="page-wrapper">

            <!-- PAGE CONTENT-->
            <div class="page-content--bgf7">

                <!-- STATISTIC-->
                <section class="statistic statistic2">
                    <div class="container">
                        <div class="row">

                            <div class="col-md-6 col-lg-4">
                                <div class="statistic__item statistic__item--green">
                                    <h2 class="number">15999.00</h2>
                                    <span class="desc">TOTAL CLIENTES</span>
                                    <div class="icon">
                                        <i class="zmdi zmdi-account-o"></i>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6 col-lg-4">
                                <div class="statistic__item statistic__item--blue">
                                    <h2 class="number">1000.00</h2>
                                    <span class="desc">TOTAL ADMIN</span>
                                    <div class="icon">
                                        <i class="zmdi zmdi-account-o"></i>
                                    </div>
                                </div>
                            </div>


                            <div class="col-md-6 col-lg-4">
                                <div class="statistic__item statistic__item--orange">
                                    <h2 class="number">388.00</h2>
                                    <span class="desc">TOTAL CAIXA</span>
                                    <div class="icon">
                                        <i class="zmdi zmdi-shopping-cart"></i>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </section>


                <!-- END STATISTIC CHART-->

                <!-- DATA TABLE-->
                <section class="p-t-20">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="table-data__tool">

                                    <div class="table-data__tool-left">
                                        <a href="../tamplates/register.jsp" class="au-btn au-btn-icon au-btn--green au-btn--small"
                                           >
                                            <i class="zmdi zmdi-plus"></i>Adicionar Utilizador</a>

                                    </div>

                                </div>

                                <div class="table-responsive table-responsive-data2">
                                    <table class="table table-data2" id="tbl_users">
                                        <thead>
                                        <hr>
                                        <tr>
                                            <th>
                                                <label class="au-checkbox">
                                                    <input type="checkbox">
                                                    <span class="au-checkmark"></span>
                                                </label>
                                            </th>
                                            <th>ID</th>
                                            <th>Nome Completo</th>
                                            <th>Email</th>
                                            <th>Endereco</th>
                                            <th>Data de Registo</th>
                                            <th>Contactos</th>
                                            <th>Previlegios</th>
                                            <th>Status</th>
                                            <th>Acções</th>
                                        </tr>
                                        </thead>
                                        <tbody>



                                        </tbody>
                                    </table>

                                </div>

                            </div>
                        </div>
                    </div>
                </section>

            </div>
        </div>
        <jsp:include page="../layouts/_footer.html"/>
        <jsp:include page="../modal/creditos.jsp"/>

        <script src="../easyDTable/js/jquery.min.js"></script>
        <script src="../easyDTable/js/bootstrap.min.js"></script>
        <script src="../easyDTable/easyTable.js"></script>

    </body>

    <% }%>


    <script>


        $(document).ready(function () {
            
            function load() {
                
                var foo = '';
                
                $.ajax({
                    
                    url: "/microsapp/CRUDController",
                    type: 'GET',
                    dataType: 'json',
                    data: {action: "list"},
                    contentType: 'application/json',
                    mimeType: 'application/json',
                    success: function (data) {

                        for (var i = 0; i < data.email.length; i++) {
                            foo += '<tr class="spacer"></tr><tr class="tr-shadow">';
                            foo += '   <td> <label class="au-checkbox">';
                            foo += '  <input type="checkbox"><span class="au-checkmark"></span></label></td>';

                            foo += '<td>' + data.userid[i] + '</td>';
                            foo += '<td>' + data.email[i] + '</td>';
                            foo += '<td>' + data.firstname[i] + '</td>';
                            foo += '<td>' + data.firstname[i] + '</td>';
                            foo += '<td>' + data.firstname[i] + '</td>';
                            foo += '<td>' + data.firstname[i] + '</td>';
                            foo += '<td>' + data.firstname[i] + '</td>';
                            foo += '<td>' + data.firstname[i] + '</td>';
                            foo += '<td>' + data.firstname[i] + '</td>';

                            foo += '<td><div class="table-data-feature">';
                            foo += '<button class="item" data-toggle="tooltip" data-placement="top" title="Editar"><i class="zmdi zmdi-edit"></i></button>';
                            foo += '<button class="item" data-toggle="tooltip" data-placement="top" title="Apagar"><i class="zmdi zmdi-delete"></i></button>';
                            foo += '</div></td></tr>';
                        }
                        
                        $("#tbl_users").find('tbody').append(foo);
                        var table = $("#tbl_users").easyTable();

                        $("#getSelected").click(function () {
                            console.log(table.getSelected(0));
                        });

                    },
                    error: function (data, status, er) {
                        alert("error: " + data + " status: " + status + " er:" + er);
                    }
                });
            }

        });</script>

</html>

