<%-- 
    Document   : client
    Created on : Feb 22, 2019, 3:15:39 PM
    Author     : Raimundo Jose
--%>

<%@page import="app.controller.HistoricopagamentoJpaController"%>
<%@page import="app.model.Historicopagamento"%>
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
        <title>Client</title>
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


                <section class="p-t-60">
                    <div class="container">

                        <div class="row">
                            <div class="col-md-12">
                                <div class="table-data__tool">
                                    <div class="table-data__tool-left">

                                        <!--                                        <div class="au-breadcrumb-content">
                                                                                    <form class="au-form-icon--sm" action="" method="post">
                                                                                        <input class="au-input--w300 au-input--style2" type="text"
                                                                                               placeholder="Search for datas &amp; reports...">
                                                                                        <button class="au-btn--submit2" type="submit">
                                                                                            <i class="zmdi zmdi-search"></i>
                                                                                        </button>
                                                                                    </form>
                                                                                </div>-->


                                    </div>

                                    <div class="table-data__tool-right">

                                        <a href="#" class="au-btn au-btn-icon btn_modal_credits au-btn--green au-btn--small"
                                           data-toggle="modal" data-target="#credits_modal" role="dialog">
                                            <i class="zmdi zmdi-plus"></i>Adicionar Credito
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row m-t-30">
                            <div class="col-md-12">
                                <!-- DATA TABLE-->

                                <%

                                    int idcredito = Integer.parseInt(request.getParameter("idcredito"));

                                    EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
                                    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
                                    Credito cli = new CreditoJpaController(emf).findCredito(idcredito);
                                    int idcliente = cli.getIdcliente().getIdcliente();

                                    Historicopagamento i = new HistoricopagamentoJpaController(emf).findHistoricopagamento(idcredito);
                                %>

                                <h4 class="pull-left">

                                    <div class="col-md-8 col-lg-12">
                                        <!-- TOP CAMPAIGN-->


                                        <div class="table-responsive">
                                            <table class="table table-data-feature">
                                                <tbody>
                                                    <tr>
                                                        <td>Valor Credito:</td>
                                                        <td><span class="role user"><%= format.format(cli.getValor())%></span></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Tipo de Credito:</td>
                                                        <td><span class="role user"><%= cli.getIdtipocredito().getDescricao()%></span><span style="color:red">&nbsp; [<%=cli.getIdtipocredito().getJuro() + "%"%>]</span></td>
                                                    </tr>

                                                </tbody>
                                            </table>
                                        </div>

                                        <!-- END TOP CAMPAIGN-->
                                    </div>
                                </h4>


                                <h4 class="pull-right" >CLIENTE: <%= cli.getIdcliente().getUser().getLastName() + ", " + cli.getIdcliente().getUser().getName()%></span></h4>

                                <br> <br>
                                <div class="table-responsive m-b-40">
                                    <table class="table table-borderless table-data3">
                                        <thead>
                                            <tr>
                                                <th>PGTO</th>

                                                <th>PTGO MENSAL</th>
                                                <th>JUROS</th>
                                                <th>AMORTIZACAO</th>
                                                <th>MODALIDADE PGTO</th>

                                                <th>DATA</th>
                                                <th>STATUS</th>
                                                <th>ACCOES</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr class="tr-shadow">
                                                <%

                                                    SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd");
                                                    List<Historicopagamento> history = new HistoricopagamentoJpaController(emf).findHistoricopagamentoEntities();

                                                    for (Historicopagamento c : history) {
                                                        if (c.getIdcredito().getIdcredito().equals(idcredito)) {

                                                            double pmensal = (c.getIdcredito().getValor() - c.getValor()) / c.getIdcredito().getIdtipocredito().getPgto();
                                                            double tjuro = (c.getIdcredito().getValor() - c.getValor()) * (c.getIdcredito().getIdtipocredito().getJuro() / 100);

                                                %>

                                                <td><%=c.getOrdem()%></td>
                                                <td class="desc"><%=format.format(pmensal)%></td>
                                                <td><%=format.format(tjuro)%></td>

                                                <td>
                                                    <span class="block-email">
                                                        <%=format.format(c.getValor())%></span>
                                                </td>

                                                <td><%=c.getIdmodopagamento().getDescricao()%></td>

                                                <td><%=df.format(c.getData())%></td>
                                                <td>
                                                    <%
                                                        String label = "";
                                                        if (c.getIdcredito().getIdestado().getIdestado() == 1) {
                                                            label = "role member";
                                                        }
                                                        if (c.getIdcredito().getIdestado().getIdestado() == 3) {
                                                            label = "role admin";
                                                        }
                                                        if (c.getIdcredito().getIdestado().getIdestado() == 2) {
                                                            label = "role user";
                                                        }
                                                    %>

                                                    <span class="<%=label%>"><%=c.getIdcredito().getIdestado().getStatus()%></span>

                                                </td>

                                                <td>
                                                    <div class="table-data-feature">

                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Editar">
                                                            <i class="zmdi zmdi-card"></i>
                                                        </button>

                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Editar">
                                                            <i class="zmdi zmdi-edit"></i>
                                                        </button>
                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Apagar">
                                                            <i class="zmdi zmdi-delete"></i>
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>
                                            <% }
                                                }%>

                                        </tbody>
                                    </table>
                                </div>
                                <!-- END DATA TABLE-->
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
</html>
