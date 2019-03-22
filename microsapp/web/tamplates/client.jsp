<%-- 
    Document   : client
    Created on : Feb 22, 2019, 3:15:39 PM
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

        <!-- PAGE CONTENT-->
        <div class="page-content--bgf7">
            <!-- BREADCRUMB-->
            <section class="au-breadcrumb2">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="pull-left">
                                logo image of money
                            </div>
                        </div>
                    </div>
                </div>
            </section>

                <div class="container">

                    <div class="row">
                        <div class="col-md-12">
                           <h3 style="color: #ff182b">Lista de Creditos</h3>
                            <hr class="line-seprate">
                        </div>
                    </div>

                </div>

            <!-- END WELCOME-->


            <!-- STATISTIC-->
            <section class="statistic statistic2">
                <div class="container">
                    <div class="row">

                        <div class="col-md-6 col-lg-4">
                            <div class="statistic__item statistic__item--green">
                                <h2 class="number">10,368</h2>
                                <span class="desc">TOTAL EMPRESTIMOS PAGOS</span>
                                <div class="icon">
                                    <i class="zmdi zmdi-account-o"></i>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6 col-lg-4">
                            <div class="statistic__item statistic__item--blue">
                                <h2 class="number">10,368</h2>
                                <span class="desc">TOTAL EMPRESTIMOS NÃO PAGOS</span>
                                <div class="icon">
                                    <i class="zmdi zmdi-account-o"></i>
                                </div>
                            </div>
                        </div>


                        <div class="col-md-6 col-lg-4">
                            <div class="statistic__item statistic__item--orange">
                                <h2 class="number">388,688</h2>
                                <span class="desc">TOTAL NÃO AUTORIZADO</span>
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

                                    <a href="#" class="au-btn au-btn-icon btn_modal_credits au-btn--green au-btn--small"
                                       data-toggle="modal" data-target="#credits_modal" role="dialog">
                                        <i class="zmdi zmdi-plus"></i>Adicionar Credito</a>
                                </div>
                            </div>

                            <div class="table-responsive table-responsive-data2">
                                <table class="table table-data2">
                                    <thead>
                                        <tr>
                                            <th>
                                                <label class="au-checkbox">
                                                    <input type="checkbox">
                                                    <span class="au-checkmark"></span>
                                                </label>
                                            </th>
                                            <th>name</th>
                                            <th>email</th>
                                            <th>description</th>
                                            <th>date</th>
                                            <th>status</th>
                                            <th>price</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <tr class="spacer"></tr>
                                        <tr class="spacer"></tr>
                                        <tr class="tr-shadow">
                                            <td>
                                                <label class="au-checkbox">
                                                    <input type="checkbox">
                                                    <span class="au-checkmark"></span>
                                                </label>
                                            </td>
                                            <td>Lori Lynch</td>
                                            <td>
                                                <span class="block-email">lyn@example.com</span>
                                            </td>
                                            <td class="desc">iPhone X 256Gb Black</td>
                                            <td>2018-09-25 19:03</td>
                                            <td>
                                                <span class="status--denied">Denied</span>
                                            </td>
                                            <td>$1199.00</td>
                                            <td>
                                                <div class="table-data-feature">
                                                    <button class="item" data-toggle="tooltip" data-placement="top" title="Send">
                                                        <i class="zmdi zmdi-mail-send"></i>
                                                    </button>
                                                    <button class="item" data-toggle="tooltip" data-placement="top" title="More">
                                                        <i class="zmdi zmdi-more"></i>
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>

                                        <tr class="spacer"></tr>
                                        <tr class="tr-shadow">
                                            <td>
                                                <label class="au-checkbox">
                                                    <input type="checkbox">
                                                    <span class="au-checkmark"></span>
                                                </label>
                                            </td>
                                            <td>Lori Lynch</td>
                                            <td>
                                                <span class="block-email">doe@example.com</span>
                                            </td>
                                            <td class="desc">Camera C430W 4k</td>
                                            <td>2018-09-24 19:10</td>
                                            <td>
                                                <span class="status--process">Processed</span>
                                            </td>
                                            <td>$699.00</td>
                                            <td>
                                                <div class="table-data-feature">

                                                    <button class="item" data-toggle="tooltip" data-placement="top" title="Send">
                                                        <i class="zmdi zmdi-mail-send"></i>
                                                    </button>

                                                    <button class="item" data-toggle="tooltip" data-placement="top" title="More">
                                                        <i class="zmdi zmdi-more"></i>
                                                    </button>

                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- END DATA TABLE-->
            <!-- COPYRIGHT-->
            <section class="p-t-60 p-b-20">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="copyright">
                                <p>Copyright © 2018 Colorlib. All rights reserved. <a href="https://colorlib.com">Colorlib</a>.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- END COPYRIGHT-->
        </div>
    </div>
    <jsp:include page="../layouts/_footer.html"/>
    <jsp:include page="../modal/creditos.jsp"/>

</body>
</html>
