<%-- 
    Document   : login
    Created on : Feb 20, 2019, 9:13:21 AM
    Author     : Raimundo Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <jsp:include page="../layouts/_header.html"/>

    <body class="animsition">

        <div class="page-wrapper">
            <div class="page-content--bge5">
                <div class="container">

                    <div class="login-wrap">
                        <div class="login-content">

                            <div class="login-logo">
                                <a href="#">
                                    <img src="../resources/images/icon/logo.png" alt="Jamal Microcreditos">
                                </a>
                            </div>

                            <div class="container">
                                <h2 style="text-align: center">Operação efectuada com sucesso</h2>
                                
                                
                            </div>
                            
                            <hr>
                            
                            <div class="pull-right"><a class="btn btn-info" href="#" onclick="back_to_login()">Entrar no Sistema?</a></div>
                            <div class="pull-left"><a class="" href="../index.jsp" ><< Home</a></div>

                            <br>
                        </div>

                    </div>
                </div>
                <!--  fim login  -->

            </div>
        </div>
    </div>   <!-- Jquery JS-->

    <jsp:include page="../layouts/_footer.html"/>
</body>
</html>
