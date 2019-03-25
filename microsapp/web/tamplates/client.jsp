<%-- 
    Document   : client
    Created on : Feb 22, 2019, 3:15:39 PM
    Author     : Raimundo Jose
--%>

<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="app.model.Credito"%>
<%@page import="app.controller.CreditoJpaController"%>
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
        <title>Cliente</title>
        <%
            if (session.getAttribute("role_name").equals("ADMIN") || session.getAttribute("role_name").equals("STANDARD")) {%>
        <jsp:include page="../fragments/header_admin.jsp"/>
        <%} else {%>

        <jsp:include page="../fragments/header_cliente.jsp"/>
        <% }%>

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
                                    Sistema de Gestao de Creditos
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <!-- END WELCOME-->


                <!-- STATISTIC-->
                <section class="statistic statistic2">
                    <div class="container">
                        <div class="row">

                            <div class="col-md-6 col-lg-4">
                                <div class="statistic__item statistic__item--green">
                                    <h2 class="number">15.9999</h2>
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
                <section class="p-t-60">
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

                                            <i class="zmdi zmdi-plus"></i>Adicionar Credito
                                        </a>
                                    </div>
                                </div>

                                <div class="table-responsive table-responsive-data2">
                                    <table class="table table-data2">
                                        <thead>
                                            <tr class="alert-secondary">
                                                <th>
                                                    <label class="au-checkbox">
                                                        <input type="checkbox">
                                                        <span class="au-checkmark"></span>
                                                    </label>
                                                </th>
                                                
                                                <th>ID</th>
                                                <th>Nome Completo</th>
                                                <th>Emprestimo</th>
                                                <th>Endereco</th>
                                                <th>Data de Registo</th>
                                                <th>Contactos</th>
                                                <th>Status</th>
                                                <th style="text-align: center">Acções</th>
                                            </tr>
                                        </thead>
                                        <tbody>


                                            <%
                                                EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
                                                SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd");
                                                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());

                                                List<Credito> cls = new CreditoJpaController(emf).findCreditoEntities();
                                                for (Credito c : cls) {
                                                    if (c.getIdcliente().getUser().getRoleId().getRoleId() > 0) {
                                            %>

                                            <tr class="spacer"></tr>
                                            <tr class="tr-shadow">

                                                <td>
                                                    <label class="au-checkbox">
                                                        <input type="checkbox">
                                                        <span class="au-checkmark"></span>
                                                    </label>
                                                </td>

                                                <td><%=c.getIdcliente().getNrBi()%></td>
                                                <td><%=c.getIdcliente().getUser().getLastName() + "," + c.getIdcliente().getUser().getName()%></td>
                                                <td><span class="block-email"><%=format.format(c.getValor())%></span></td>
                                                <td class="desc"><%=c.getIdcliente().getLinhaendereco1()%></td>
                                                <td><%= df.format(c.getIdcliente().getUser().getDataAdded())%></td>

                                                <td><span class="status--process"><%=c.getIdcliente().getContacto1() + "/" + c.getIdcliente().getContacto2()%></span>
                                                </td>

                                                <td><%=c.getIdestado().getStatus()%></td>
                                                <td>
                                                    <div class="table-data-feature">

                                                        <button onclick="_view_detail(this.value)" value="<%=c.getIdcredito()%>" class="item" data-toggle="tooltip" data-placement="top" title="Detalhes de Pagamentos">
                                                            <i class="zmdi zmdi-more"></i>
                                                        </button>

                                                        <a href="details_credito.jsp?idcredito="<%=c.getIdcredito()%> class="item" data-toggle="tooltip" data-placement="top" title="Detalhes de Pagamento">
                                                            <i class="zmdi zmdi-settings"></i>
                                                        </a>

                                                        <a href="/microsapp/CRUDController?acao=2&idcredito="<%=c.getIdcredito()%> class="item" data-toggle="tooltip" data-placement="top" title="Actualizar Credito">
                                                            <i class="zmdi zmdi-edit"></i>
                                                        </a>

                                                    </div>
                                                </td>
                                            </tr>
                                            <% }
                                                }
                                            %>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <section class="p-t-60 p-b-20">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="copyright">
                                    <p>Copyright © 2019. <a href="#">Infoss.net</a>.</p>
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

    <script>
        function _view_detail(item) {

            window.location = 'payments.jsp?idcredito=' + item;

        }
    </script>
</html>
