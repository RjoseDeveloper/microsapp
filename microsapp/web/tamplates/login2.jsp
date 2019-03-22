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
                                <img src="../resources/images/icon/logo.png" alt="CoolAdmin">
                            </a>
                        </div>

                        <div class="login-form">
                            <form action="/jmicrocreditosapp/UserLogin" method="post">

                                <div class="form-group">
                                    <label>Correio Electronico</label>
                                    <input class="au-input au-input--full" type="email" 
                                           name="email" placeholder="Email" required="Obrigatorio">
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input class="au-input au-input--full" type="password"
                                           name="password" placeholder="Password" required="Obrigatorio">
                                </div>
                                <button class="au-btn au-btn--block au-btn--green m-b-20" 
                                        type="submit">Entrar</button>
                            </form>
                            
                            
                            
                            <div class="register-link">
                                <p>
                                    Não possui Conta?
                                    <a href="register.jsp">Registar - se </a>

                                </p>
                            </div>
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
