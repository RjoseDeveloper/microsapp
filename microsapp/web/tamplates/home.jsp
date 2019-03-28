<%-- 
    Document   : home
    Created on : Mar 22, 2019, 10:14:39 AM
    Author     : Raimundo Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HomePage</title>
    </head>

    <body class="animsition">
       
        <%
            String page_init = "";
            if (session.getAttribute("role_name").equals("ADMIN")) {%>
            
            <jsp:include page="../fragments/header_admin.jsp" />
            <% page_init = "admin.jsp";}%>

        <%
             if (session.getAttribute("role_name").equals("USER")) {%>
            <jsp:include page="../fragments/header_user.jsp" />
            <% page_init = "user.jsp";  }%>

        <%
             if (session.getAttribute("role_name").equals("CLIENT")) {%>
            <jsp:include page="../fragments/header_cliente.jsp" />
            <% page_init = "client.jsp"; }%>

        <iframe src="<%=page_init %>" width="100%" hidden="1000px" frameborder="1" name="home"></iframe>
    
    </body>


</html>
