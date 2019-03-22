<%-- 
    Document   : admin
    Created on : Feb 20, 2019, 8:20:01 AM
    Author     : Raimundo Jose
--%>

<%@page import="app.controller.ClienteJpaController"%>
<%@page import="app.methods.Functions"%>
<%@page import="app.model.Credito"%>
<%@page import="java.util.List"%>
<%@page import="app.controller.CreditoJpaController"%>
<%@page import="sun.awt.AppContext"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>

    <body class="animsition">

        <jsp:include page="../fragments/header_admin.jsp" />
        <!-- PAGE CONTENT-->
        <div class="page-content--bgf7">

            <!-- END WELCOME-->
            <section class="au-breadcrumb-content">
              <br> <br> <br>
            </section>

            <!-- END WELCOME-->

            <%
                EntityManagerFactory emf = new app.context.AppContext().getAppContext();

                List<Credito> creditos = new CreditoJpaController(emf).findCreditoEntities();
                int paid = 0, not_paid = 0;
                for (Credito c : creditos) {

                    if (c.getIdestado().getIdestado() == 2L) {
                        paid++;
                    } else {
                        not_paid++;
                    }
                }


            %>
            <!-- STATISTIC-->
            <section class="statistic statistic2">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6 col-lg-3">
                            <div class="statistic__item statistic__item--green">

                                <h2 class="number"><%=paid%> </h2>

                                <span class="desc">EMPRESTIMOS PAGOS</span>
                                <div class="icon">
                                    <i class="zmdi zmdi-account-o"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-3">
                            <div class="statistic__item statistic__item--orange">
                                <h2 class="number"><%=not_paid%></h2>
                                <span class="desc">PAGAMENTOS PENDENTES</span>
                                <div class="icon">
                                    <i class="zmdi zmdi-shopping-cart"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-3">
                            <div class="statistic__item statistic__item--blue">
                                <h2 class="number"><%= new ClienteJpaController(emf).getClienteCount()%></h2>
                                <span class="desc">TOTAL CLIENTE</span>
                                <div class="icon">
                                    <i class="zmdi zmdi-calendar-note"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-3">
                            <div class="statistic__item statistic__item--red">
                                <h2 class="number"><%= creditos.size()%></h2>
                                <span class="desc">TOTAL EMPRESTIMOS</span>
                                <div class="icon">
                                    <i class="zmdi zmdi-money"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- END STATISTIC-->


            <!-- STATISTIC CHART-->
            <section class="statistic-chart">
                <div class="container">
                    <div class="row">
                        <div class="col-md-5 col-lg-4">
                            <!-- TOP CAMPAIGN-->
                            <div class="top-campaign">
                                <div style="font-weight: bold">PAGAMETOS NAO EFECTUADOS</div>
                                <div class="table-responsive">
                                    <table class="table table-top-campaign">
                                        <tbody>
                                            <tr>
                                                <td>Credito ao Consumo</td>
                                                <td><%= new Functions().getSomaCredito(1, 1,1) %></td>
                                            </tr>
                                            <tr>
                                                <td>Credito para Negocio</td>
                                                <td><%= new Functions().getSomaCredito(2, 1,1) %></td>
                                            </tr>
                                            <tr>
                                                <td>Credito pela Penhor</td>
                                                <td><%= new Functions().getSomaCredito(3, 1,1) %></td>
                                                
                                            </tr>
                                            <tr>
                                                <td>Credito VIP</td>
                                                <td><%= new Functions().getSomaCredito(4, 1,1) %></td>
                                            </tr>
                                            
                                            <tr class="alert-dark">
                                                <td>Total</td>
                                                <td><%= new Functions().getSomaCredito(1, 1,1) +
                                                        new Functions().getSomaCredito(2, 1,1) +
                                                new Functions().getSomaCredito(3, 1,1)+
                                                new Functions().getSomaCredito(4, 1,1) %></td>
                                            </tr>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- END TOP CAMPAIGN-->
                        </div>

<!--                        <div class="col-lg-4">
                            <div class="au-card m-b-30">
                                <div class="au-card-inner">
                                    <h3 class="title-2 m-b-40">Evolução Mensal</h3>
                                    <canvas id="singelBarChart"></canvas>
                                </div>
                            </div>
                        </div>-->

                        <div class="col-md-5 col-lg-4">
                            <!-- TOP CAMPAIGN-->
                            <div class="top-campaign">
                                <div style="font-weight: bold">PAGAMETOS EFECTUADOS</div>
                                <div class="table-responsive">
                                    <table class="table table-top-campaign">
                                        <tbody>
                                            <tr>
                                                <td>Credito ao Consumo</td>
                                                <td><%= new Functions().getSomaCredito(1, 1,2) %></td>
                                            </tr>
                                            <tr>
                                                <td>Credito para Negocio</td>
                                                <td><%= new Functions().getSomaCredito(2, 1,2) %></td>
                                            </tr>
                                            <tr>
                                                <td>Credito pela Penhor</td>
                                                <td><%= new Functions().getSomaCredito(3, 1,2) %></td>
                                                
                                            </tr>
                                            <tr>
                                                <td>Credito VIP</td>
                                                <td><%= new Functions().getSomaCredito(4, 1,2) %></td>
                                            </tr>
                                            
                                            <tr class="alert-dark">
                                                <td>Total</td>
                                                <td><%= new Functions().getSomaCredito(1, 1,2) +
                                                        new Functions().getSomaCredito(2, 1,2) +
                                                new Functions().getSomaCredito(3, 1,2)+
                                                new Functions().getSomaCredito(4, 1,2) %></td>
                                            </tr>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- END TOP CAMPAIGN-->
                        </div>

                        <div class="col-md-5 col-lg-4">
                            <!-- TOP CAMPAIGN-->
                            <div class="top-campaign">
                                <div style="font-weight: bold">CONTAS CANCELADAS</div>
                                <div class="table-responsive">
                                    <table class="table table-top-campaign">
                                        <tbody>
                                             <tr>
                                                <td>Credito ao Consumo</td>
                                                <td><%= new Functions().getSomaCredito(1, 1,5) %></td>
                                            </tr>
                                            <tr>
                                                <td>Credito para Negocio</td>
                                                <td><%= new Functions().getSomaCredito(2, 1,5) %></td>
                                            </tr>
                                            <tr>
                                                <td>Credito pela Penhor</td>
                                                <td><%= new Functions().getSomaCredito(3, 1,5) %></td>
                                                
                                            </tr>
                                            <tr>
                                                <td>Credito VIP</td>
                                                <td><%= new Functions().getSomaCredito(4, 1,5) %></td>
                                            </tr>
                                            
                                            <tr class="alert-dark">
                                                <td>Total</td>
                                                <td><%= new Functions().getSomaCredito(1, 1,5) +
                                                        new Functions().getSomaCredito(2, 1,5) +
                                                new Functions().getSomaCredito(3, 1,5)+
                                                new Functions().getSomaCredito(4, 1,5) %></td>
                                            </tr>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- END TOP CAMPAIGN-->
                        </div>


                    </div>
                </div>
            </section>

            <jsp:include page="../modal/creditos.jsp"/>
            <jsp:include page="../modal/instituicao.jsp"/>

        </div>
    </body>

</html>
