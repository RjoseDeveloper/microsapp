<%-- 
    Document   : juros
    Created on : Mar 23, 2019, 7:07:47 AM
    Author     : Raimundo Jose
--%>

<%@page import="java.util.List"%>
<%@page import="app.controller.TipocreditoJpaController"%>
<%@page import="app.model.Tipocredito"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Juros</title>

    </head>
    <body class="animsition">

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
                        <th>PGTO</th>
                        <th>Tipo de Credito</th>
                        <th>Juro</th>


                        <th>Status</th>
                        <th>Acções</th>
                    </tr>
                </thead>
                <tbody>

                   
                        <%
                            EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
                            SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd");

                            List<Tipocredito> cls = new TipocreditoJpaController(emf).findTipocreditoEntities();
                            for (Tipocredito c : cls) {
                        %>
                         <tr class="spacer"></tr>
                    <tr class="spacer"></tr>
                    <tr class="tr-shadow">

                        <td>
                            <label class="au-checkbox">
                                <input type="checkbox">
                                <span class="au-checkmark"></span>
                            </label>
                        </td>
                        <td><%=c.getPgto()%></td>

                        <td class="desc"><%= c.getDescricao()%></td>
                        <td><%= c.getJuro() %></td>
                        <td>
                            <span class="status--denied">Activo</span>
                        </td>

                        <td align="center">
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
                    %>

                </tbody>
            </table>
        </div>

    </body>
</html>
