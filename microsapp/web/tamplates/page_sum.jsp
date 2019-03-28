<%-- 
    Document   : page_sum
    Created on : Mar 27, 2019, 8:13:14 AM
    Author     : Raimundo Jose
--%>

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
        <title>JSP Page</title>
    </head>
    <body>
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

                            <div class="col-md-6 col-lg-4">
                                <div class="statistic__item statistic__item--green">
                                    <h2 class="number">12.000,00</h2>
                                    <span class="desc">EMPRESTIMOS PAGOS</span>
                                    <div class="icon">
                                        <i class="zmdi zmdi-account-o"></i>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6 col-lg-4">
                                <div class="statistic__item statistic__item--blue">
                                    <h2 class="number">10.681,00</h2>
                                    <span class="desc">EMPRESTIMOS NÃO PAGOS</span>
                                    <div class="icon">
                                        <i class="zmdi zmdi-account-o"></i>
                                    </div>
                                </div>
                            </div>


                            <div class="col-md-6 col-lg-4">
                                <div class="statistic__item statistic__item--orange">
                                    <h2 class="number">38.888,00</h2>
                                    <span class="desc">NÃO AUTORIZADO</span>
                                    <div class="icon">
                                        <i class="zmdi zmdi-shopping-cart"></i>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </section>

    </body>
</html>
