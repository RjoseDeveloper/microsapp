<%-- 
    Document   : header_cliente
    Created on : Feb 22, 2019, 6:13:51 PM
    Author     : Raimundo Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <jsp:include page="../layouts/_header.html"/>
    </head>
    
    <body class="animsition">
        
        <!-- HEADER DESKTOP-->
    <header class="header-mobile-2 d-none d-lg-block" style="background: #004085">
        <div class="section__content section__content--p35">
            <div class="header3-wrap">
                <div class="header__logo">
                    <a href="#"> CLIENTE </a>
                </div>

                <div class="header__navbar">
                    <ul class="list-unstyled">
                        <li>
                            <a href="/">
                                <i class="fas fa-home"></i>
                                <span class="bot-line"></span>HOME</a>
                        </li>
                        
                        <li class="has-sub">
                            <a href="client">
                                <i class="fas fa-credit-card"></i>CRÉDITOS
                                <span class="bot-line"></span>
                            </a>
                        </li>

                        <li class="has-sub">
                            <a href="/payments/">
                                <i class="fas fa-cart-plus"></i>PAGAMENTOS
                                <span class="bot-line"></span>
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="header__tool">
                    <div class="header-button-item js-item-menu">
                        <i class="zmdi zmdi-settings"></i>
                        <div class="setting-dropdown js-dropdown">
                            <div class="account-dropdown__body">
                                <div class="account-dropdown__item">
                                    <a href="configuracao.jsp">
                                        <i class="zmdi zmdi-account"></i>Cadastro Gerais</a>

                                </div>
                                <div class="account-dropdown__item">
                                    <a href="RecoverPassword.html">
                                        <i class="zmdi zmdi-settings"></i>Bens e Imoveis</a>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="account-wrap">
                        <div class="account-item account-item--style2 clearfix js-item-menu">
                            <div class="image">
                                <img src="/images/icon/avatar-01.jpg" alt="INFOSS.NET" />
                            </div>
                            <div class="content">
                                <a class="js-acc-btn" href="#">Raimundo Jose</a>
                            </div>
                            <div class="account-dropdown js-dropdown">
                                <div class="info clearfix">
                                    <div class="image">
                                        <a href="#">
                                            <img src="../resources/images/icon/avatar-01.jpg" alt="INFOSS.NET" />
                                        </a>
                                    </div>
                                    <div class="content">
                                        <h5 class="name">
                                            <a href="#">INFOSS.NET</a>
                                        </h5>
                                        <span class="email">infoss.net@yahoo.com</span>
                                    </div>
                                </div>
                                <div class="account-dropdown__body">
                                    <div class="account-dropdown__item">
                                        <a href="Utilizador.jsp">
                                            <i class="zmdi zmdi-account"></i>Minha Conta</a>
                                    </div>
                                    <div class="account-dropdown__item">
                                        <a href="RecoverPassword.html">
                                            <i class="zmdi zmdi-settings"></i>Gerir Password</a>
                                    </div>

                                </div>
                                <div class="account-dropdown__footer">
                                    <a href="../tamplates/destrySession.jsp">
                                        <i class="zmdi zmdi-power"></i>Logout</a>
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
                        <img src="/images/icon/logo-white.png" alt="CoolAdmin" />
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

                    <li>

                        <a href="/client">
                            <i class="fas fa-object-group"></i>
                            <span class="bot-line"></span>CREDITOS
                        </a>
                    </li>


                    <li class="has-sub">
                        <a href="/payments">
                            <i class="fas fa-users"></i>PAGAMENTOS
                            <span class="bot-line"></span>
                        </a>

                    </li>

                </ul>
            </div>
        </nav>
    </header>

    <div class="sub-header-mobile-2 d-block d-lg-none">
        <div class="header__tool">

            <div class="header-button-item js-item-menu">
                <i class="zmdi zmdi-settings"></i>
                <div class="setting-dropdown js-dropdown">
                    <div class="account-dropdown__body">
                        <div class="account-dropdown__item">
                            <a href="Utilizador.jsp">
                                <i class="zmdi zmdi-account"></i>Minha Conta</a>
                        </div>
                        <div class="account-dropdown__item">
                            <a href="RecoverPassword.jsp">
                                <i class="zmdi zmdi-settings"></i>Gerir Password</a>
                        </div>
                        <div class="account-dropdown__item">
                            <a href="#">
                                <i class="zmdi zmdi-notifications-active"></i>Notficações</a>
                        </div>
                    </div>

                </div>
            </div>

            <div class="account-wrap">
                <div class="account-item account-item--style2 clearfix js-item-menu">
                    <div class="image">
                        <img src="/images/icon/avatar-01.jpg" alt="John Doe" />
                    </div>
                    <div class="content">
                        <a class="js-acc-btn" href="#">Raimundo Jose</a>
                    </div>
                    <div class="account-dropdown js-dropdown">
                        <div class="info clearfix">
                            <div class="image">
                                <a href="#">
                                    <img src="/images/icon/avatar-01.jpg" alt="John Doe" />
                                </a>
                            </div>

                            <div class="content">
                                <h5 class="name">
                                    <a href="#">INFOSS.NET</a>
                                </h5>
                                <span class="email">infoss.net@yahoo.com</span>
                            </div>

                        </div>
                        <div class="account-dropdown__body">

                            <div class="account-dropdown__item">
                                <a href="Utilizador.jsp">
                                    <i class="zmdi zmdi-account"></i>Minha Conta</a>
                            </div>

                            <div class="account-dropdown__item">
                                <a href="RecoverPassword.jsp">
                                    <i class="zmdi zmdi-settings"></i>Gerir Password</a>
                            </div>

                            <div class="account-dropdown__footer">
                                <a href="../tamplates/destrySession.jsp">
                                    <i class="zmdi zmdi-power"></i>Logout</a>
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
