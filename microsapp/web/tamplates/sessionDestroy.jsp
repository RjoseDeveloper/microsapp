<%-- 
    Document   : sessionDestroy
    Created on : Mar 23, 2019, 8:03:30 AM
    Author     : Raimundo Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            
            session.removeAttribute("username");
            session.invalidate();
            response.sendRedirect(request.getContextPath()+"/index.jsp");
            
        %>
    </body>
</html>
