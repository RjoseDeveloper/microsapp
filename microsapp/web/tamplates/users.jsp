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

    </head>
    <body class="animsition">


        <div class="page-wrapper">

            <!-- PAGE CONTENT-->
            <div class="page-content--bgf7">
                <!-- BREADCRUMB-->
                <section class="au-breadcrumb-content">
                    <h3 class="title-3 m-b-30">
                        <i class="zmdi zmdi-account-calendar"></i>user data</h3>
                </section>

                <!-- END WELCOME-->


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

                                        <a href="../tamplates/register.jsp" class="au-btn au-btn-icon au-btn--green au-btn--small"
                                           >
                                            <i class="zmdi zmdi-plus"></i>Adicionar Utilizador</a>
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

                                            <tr class="spacer"></tr>
                                            <tr class="spacer"></tr>
                                            <tr class="tr-shadow">
                                                <%
                                                    EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
                                                    SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd");

                                                    List<Cliente> cls = new ClienteJpaController(emf).findClienteEntities(6, 1);
                                                    for (Cliente c : cls) {
                                                        if (c.getUser().getRoleId().getRoleId() != 3) {


                                                %>

                                                <td>
                                                    <label class="au-checkbox">
                                                        <input type="checkbox">
                                                        <span class="au-checkmark"></span>
                                                    </label>
                                                </td>
                                                <td><%=c.getNrBi()%></td>
                                                <td><%=c.getUser().getLastName() + "," + c.getUser().getName()%></td>
                                                <td>
                                                    <span class="block-email"><%= c.getUser().getEmail()%></span>
                                                </td>
                                                <td class="desc"><%= c.getLinhaendereco1()%></td>
                                                <td><%= df.format(c.getUser().getDataAdded())%></td>
                                                <td>
                                                    <span class="status--process">Processado</span>
                                                </td>
                                                <td> 
                                                    <%
                                                        String role_class = "";
                                                        if (c.getUser().getRoleId().getRole().equals("ADMIN")) {
                                                            role_class = "role admin";
                                                        } else {

                                                            role_class = "role user";
                                                        }%>

                                                    <span class="<%=role_class%>"><%=c.getUser().getRoleId().getRole()%></span></td>

                                                <td><%=2%></td>
                                                <td>
                                                    <div class="table-data-feature">
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

                                    <div class="user-data__footer">
                                        <button class="au-btn au-btn-load">Mais Registos</button>
                                    </div>
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
                                    <p>Copyright © 2019 Todos os Direitos reservados. <a href="https://colorlib.com">Colorlib</a>.</p>
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

    <% }%>
</html>
