<%-- 
    Document   : logo
    Created on : Mar 22, 2019, 2:45:34 PM
    Author     : Raimundo Jose
--%>

<%@page import="app.controller.ClienteJpaController"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <jsp:include page="../layouts/_header.html"/>
    <body>
        <h1>
            <%
                EntityManagerFactory  emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
                out.print(new ClienteJpaController(emf).findCliente(21).getContacto2());
                %>
           
        </h1>
    </body>
    
    <jsp:include page="../layouts/_footer.html"/>
</html>
