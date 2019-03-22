<%-- 
    Document   : pagaments
    Created on : Feb 22, 2019, 7:53:50 PM
    Author     : Raimundo Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="../fragments/header_cliente.jsp"/>
    </head>
 
        <body class="animsition">
       
        <div class="page-wrapper">
            
            <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        
                        
                          <div class="row">
                            <div class="col-md-12">
                                <div class="table-data__tool">

                                    <div class="table-data__tool-left">

                                        <div class="au-breadcrumb-content">

                                            <form class="au-form-icon--sm" action="" method="post">
                                                <input class="au-input--w300 au-input--style2" type="text"
                                                       placeholder="Search for datas &amp; reports...">
                                                <button class="au-btn--submit2" type="submit">
                                                    <i class="zmdi zmdi-search"></i>
                                                </button>
                                            </form>
                                        </div>
                                    </div>

                                    <div class="table-data__tool-right">
                                        <a href="#" class="au-btn au-btn-icon au-btn--green au-btn--small"
                                           data-toggle="modal" data-target="#credits_modal">
                                            <i class="zmdi zmdi-plus"></i>Efectuar Pagamento</a>
                                    </div>
                                  
                                </div>
                            </div>
                            
                        </div>
                        
            
                        <div class="row m-t-30">
                                <div class="col-md-12">
                                    <!-- DATA TABLE-->
                                    
                                    
                                    <div class="table-responsive m-b-40">
                                        <table class="table table-borderless table-data3">
                                            <thead>
                                                <tr>
                                                    <th>Nr. PGTO</th>
                                                    <th>Data de Pagamento</th>
                                                    <th>Pagamento Agendado</th>
                                                    <th>Juros</th>

                                                    <th>Amortizacoes</th>
                                                    <th>Capital em Divida</th>
                                                    <th>Estado</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td>2018-09-29 05:57</td>
                                                    <td>345</td>
                                                    <td>Mobile</td>
                                                    <td>iPhone X 64Gb Grey</td>
                                                    <td class="process">Processed</td>
                                                    <td>$999.00</td>
                                                </tr>
                                                <tr>
                                                    <td>1</td>
                                                    <td>2018-09-29 05:57</td>
                                                    <td>2018-09-28 01:22</td>
                                                    <td>Mobile</td>
                                                    <td>Samsung S8 Black</td>
                                                    <td class="process">Processed</td>
                                                    <td>$756.00</td>
                                                </tr>


                                                <tr>
                                                    <td>2</td>
                                                    <td>2018-09-29 05:57</td>
                                                    <td>2018-09-24 19:10</td>
                                                    <td>Camera</td>
                                                    <td>Camera C430W 4k</td>
                                                    <td class="process">Processed</td>
                                                    <td>$699.00</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- END DATA TABLE-->
                                    
                                    
                                </div>
                            </div>
                    </div>
                </div>
            </div>
        </div>
            
            <jsp:include  page="../modal/creditos.jsp"/>
    </body>
</html>
