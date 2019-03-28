<%-- 
    Document   : home_admin
    Created on : Mar 27, 2019, 7:59:01 AM
    Author     : Raimundo Jose
--%>

<%@page import="app.methods.Functions"%>
<%@page import="app.controller.ClienteJpaController"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="app.controller.CreditoJpaController"%>
<%@page import="app.model.Credito"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>&nbsp;</title>
    </head>
    <body>
        <div class="page-content--bgf7">

            <!-- END WELCOME-->
            <section class="au-breadcrumb-content">
                <br> <br> <br>
            </section>

            <!-- END WELCOME-->

            <%            
                EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
                
                List<Credito> creditos = new CreditoJpaController(emf).findCreditoEntities();
                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
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
                                                <td><%=format.format(new Functions().getSomaCredito(1, 1))%></td>
                                            </tr>
                                            <tr>
                                                <td>Credito para Negocio</td>
                                                <td><%=format.format(new Functions().getSomaCredito(2, 1))%></td>
                                            </tr>
                                            <tr>
                                                <td>Credito pela Penhor</td>
                                                <td><%=format.format(new Functions().getSomaCredito(3, 1))%></td>

                                            </tr>
                                            <tr>
                                                <td>Credito VIP</td>
                                                <td><%=format.format(new Functions().getSomaCredito(4, 1))%></td>
                                            </tr>

                                            <tr class="alert-dark">
                                                <td>Total</td>
                                                <td><%=format.format(new Functions().getSomaCredito(1, 1)
                                                        + new Functions().getSomaCredito(2, 1)
                                                        + new Functions().getSomaCredito(3, 1)
                                                        + new Functions().getSomaCredito(4, 1))%></td>
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
                                                <td><%=format.format(new Functions().getSomaCredito(1, 2))%></td>
                                            </tr>
                                            <tr>
                                                <td>Credito para Negocio</td>
                                                <td><%=format.format(new Functions().getSomaCredito(2, 2))%></td>
                                            </tr>
                                            <tr>
                                                <td>Credito pela Penhor</td>
                                                <td><%=format.format(new Functions().getSomaCredito(3, 2))%></td>

                                            </tr>
                                            <tr>
                                                <td>Credito VIP</td>
                                                <td><%=format.format(new Functions().getSomaCredito(4, 2))%></td>
                                            </tr>

                                            <tr class="alert-dark">
                                                <td>Total</td>
                                                <td><%=format.format(new Functions().getSomaCredito(1, 2)
                                                        + new Functions().getSomaCredito(2, 2)
                                                        + new Functions().getSomaCredito(3, 2)
                                                        + new Functions().getSomaCredito(4, 2))%></td>
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
                                                <td><%=format.format(new Functions().getSomaCredito(1, 3))%></td>
                                            </tr>
                                            <tr>
                                                <td>Credito para Negocio</td>
                                                <td><%=format.format(new Functions().getSomaCredito(2, 3))%></td>
                                            </tr>
                                            <tr>
                                                <td>Credito pela Penhor</td>
                                                <td><%=format.format(new Functions().getSomaCredito(3, 3))%></td>

                                            </tr>
                                            <tr>
                                                <td>Credito VIP</td>
                                                <td><%=format.format(new Functions().getSomaCredito(4, 3))%></td>
                                            </tr>

                                            <tr class="alert-dark">
                                                <td>Total</td>
                                                <td><%=format.format(new Functions().getSomaCredito(1, 3)
                                                        + new Functions().getSomaCredito(2, 3)
                                                        + new Functions().getSomaCredito(3, 3)
                                                        + new Functions().getSomaCredito(4, 3))%></td>
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
            
        </div>
    </body>
</html>
