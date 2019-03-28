<%-- 
    Document   : header
    Created on : Feb 20, 2019, 8:21:01 AM
    Author     : Raimundo Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN</title>
        <jsp:include page="../layouts/_header.html"/>
    </head>
    <body>

        <!-- HEADER DESKTOP-->
        <header class="header-desktop3 d-none d-lg-block" style="background: #002752">
            <div class="section__content section__content--p30">
                <div class="header3-wrap">

                    <div class="header__logo">
                        <a href="#"> ADMINISTRATOR </a>
                    </div>

                    <div class="header__navbar">
                        <ul class="list-unstyled">
                           
                            <li>
                                <a href="../tamplates/admin.jsp">
                                    <i class="fas fa-home"></i>
                                    <span class="bot-line"></span>HOME</a>
                            </li>

                            <li class="has-sub">
                                <a href="../tamplates/client.jsp">
                                    <i class="fas fa-users"></i>CLIENTES
                                    <span class="bot-line"></span>
                                </a>
                            </li>

                            <li class="has-sub">
                                <a href="#">
                                    <i class="fas fa-credit-card"></i>CREDITOS
                                    <span class="bot-line"></span>
                                </a>

                                <ul class="header3-sub-list list-unstyled">
                                    <li>
                                        <a href="../tamplates/payments.jsp">SOLICITAR</a>
                                    </li>
                                    <li>
                                        <a href="../tamplates/formPayments.jsp">PAGAMENTOS</a>
                                    </li>
                                    
                                     <li>
                                         <a href="../tamplates/simularcredito.jsp">SIMULAÇÃO</a>
                                    </li>

                                </ul>
                            </li>



                            <li class="has-sub">
                                <a href="#">
                                    <i class="fas fa-copy"></i>
                                    <span class="bot-line"></span>CONFIGURAÇÕES</a>
                                <ul class="header3-sub-list list-unstyled">
                                    <li>
                                        <a href="../tamplates/users.jsp">UTILIZADORES DO SISTEMA</a>
                                    </li>
                                    <li>
                                        <a href="../tamplates/settings.jsp">SESSÕES DO SISTEMA</a>
                                    </li>
                                    <li>
                                        <a href="../tamplates/recoverpass.html">INTEGRAÇÃO BANCÁRIA</a>
                                    </li>
                                </ul>
                            </li>

                            <li>

                                <a href="#">
                                    <i class="fas fa-print"></i>
                                    <span class="bot-line"></span>RELATORIOS
                                </a>

                            </li>

                        </ul>


                    </div>


                    <div class="header__tool">

                        <div class="account-wrap">
                            <div class="account-item account-item--style2 clearfix js-item-menu">
                                <div class="image">
                                    <img src="../resources/images/icon/avatar-01.jpg" alt="John Doe" />
                                </div>
                                <div class="content">
                                    <a class="js-acc-btn" href="#"> <%= session.getAttribute("nome") + " " + session.getAttribute("apelido")%></a>
                                </div>
                                <div class="account-dropdown js-dropdown">
                                    <div class="info clearfix">
                                        <div class="image">
                                            <a href="#">
                                                <img src="../resources/images/icon/avatar-01.jpg" alt="John Doe" />
                                            </a>
                                        </div>
                                        <div class="content">
                                            <h5 class="name">
                                                <a href="#"><%= session.getAttribute("nome") + " " + session.getAttribute("apelido")%></a>
                                            </h5>
                                            <span class="email"><%= session.getAttribute("username")%></span>
                                        </div>
                                    </div>
                                    <div class="account-dropdown__body">
                                        <!--                                        <div class="account-dropdown__item">
                                                                              </div>-->

                                        <div class="account-dropdown__item">
                                            <a href="../tamplates/users.jsp?user="<%= session.getAttribute("username")%>>
                                                <i class="zmdi zmdi-settings"></i>DEFINIÇÕES</a>
                                        </div>

                                    </div>
                                    <div class="account-dropdown__footer">
                                        <a href="../tamplates/sessionDestroy.jsp">
                                            <i class="zmdi zmdi-power"></i>SAIR</a>
                                    </div>
                                                
                                                
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- END HEADER DESKTOP-->
        
        

         <!-- HEADER MOBILE-->
    <header class="header-mobile header-mobile-2 d-block d-lg-none">
        <div class="header-mobile__bar">
            <div class="container-fluid">
                <div class="header-mobile-inner">
                    <a class="logo" href="/">
                        ADMINISTRATOR
                    </a>
                    <button class="hamburger hamburger--slider" type="button">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                    </button>
                </div>
            </div>
        </div>

        <nav class="navbar-mobile">
            <div class="container-fluid">
                <ul class="navbar-mobile__list list-unstyled">

                    <li>
                        <a href="/">
                            <i class="fas fa-home"></i>
                            <span class="bot-line"></span>HOME</a>
                    </li>

                    <li class="has-sub">
                                <a href="../tamplates/client.jsp">
                                    <i class="fas fa-users"></i>CLIENTES
                                    <span class="bot-line"></span>
                                </a>
                            </li>

                            <li class="has-sub">
                                <a href="#">
                                    <i class="fas fa-credit-card"></i>CREDITOS
                                    <span class="bot-line"></span>
                                </a>

                                <ul class="header3-sub-list list-unstyled">
                                    <li>
                                        <a href="../tamplates/creditos.jsp">SOLICITAR</a>
                                    </li>
                                    <li>
                                        <a href="../tamplates/formPayments.jsp">PAGAMENTOS</a>
                                    </li>
                                    
                                     <li>
                                         <a href="../tamplates/simularcredito.jsp">SIMULAÇÃO</a>
                                    </li>

                                </ul>
                            </li>



                            <li class="has-sub">
                                <a href="#">
                                    <i class="fas fa-copy"></i>
                                    <span class="bot-line"></span>CONFIGURAÇÕES</a>
                                <ul class="header3-sub-list list-unstyled">
                                    <li>
                                        <a href="../tamplates/users.jsp">UTILIZADORES DO SISTEMA</a>
                                    </li>
                                    <li>
                                        <a href="../tamplates/settings.jsp">SESSÕES DO SISTEMA</a>
                                    </li>
                                    <li>
                                        <a href="../tamplates/forget-pass.html">INTEGRAÇÃO BANCÁRIA</a>
                                    </li>
                                </ul>
                            </li>

                            <li>

                                <a href="#">
                                    <i class="fas fa-print"></i>
                                    <span class="bot-line"></span>RELATORIOS
                                </a>

                            </li>

                        </ul>

            </div>
        </nav>
    </header>

    <div class="sub-header-mobile-2 d-block d-lg-none">
        <div class="header__tool">

            <div class="account-wrap">
                <div class="account-item account-item--style2 clearfix js-item-menu">
                    <div class="image">
                        <img src="../resources/images/icon/avatar-01.jpg" alt="John Doe" />
                    </div>
                    <div class="content">
                        <a class="js-acc-btn" href="#"><%= session.getAttribute("nome") + " " + session.getAttribute("apelido")%></a>
                    </div>
                    <div class="account-dropdown js-dropdown">
                        <div class="info clearfix">
                            <div class="image">
                                <img src="../resources/images/icon/avatar-01.jpg" alt="John Doe" />
                            </div>

                            <div class="content">
                                <h5 class="name">
                                    <a href="#"><%= session.getAttribute("nome") + " " + session.getAttribute("apelido")%></a>
                                </h5>
                                <span class="email"><%= session.getAttribute("username")%></span>
                            </div>

                        </div>
                        <div class="account-dropdown__body">

                            <div class="account-dropdown__item">
                                <a href="../tamplates/users.jsp">
                                    <i class="zmdi zmdi-account"></i>Minha Conta</a>
                            </div>


                            <div class="account-dropdown__footer">
                                <a href="../tamplates/sessionDestroy.jsp">
                                    <i class="zmdi zmdi-power"></i>SAIR</a>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    
    <jsp:include page="../layouts/_footer.html" />
    </body>
</html>