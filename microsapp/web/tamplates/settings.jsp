<%-- 
    Document   : settings
    Created on : Mar 16, 2019, 5:36:44 PM
    Author     : Raimundo Jose
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="app.controller.TipocreditoJpaController"%>
<%@page import="app.model.Tipocredito"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Conf</title>
    </head>
    <body class="animsition">
              <%
            if (session.getAttribute("role_name").equals("ADMIN") || session.getAttribute("role_name").equals("STANDARD")) {%>
            <jsp:include page="../fragments/header_admin.jsp"/>
        <%} %>
        <!-- PAGE CONTAINER-->
        <div class="page-container">

            <!-- MAIN CONTENT-->
            <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">

                        <div class="row">

                            <!-- /# column -->
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h4>SESSÃO DE CONFIGURAÇÕES</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="custom-tab">

                                            <nav>
                                                <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                                    <a class="nav-item nav-link active" id="custom-nav-home-tab" data-toggle="tab" href="#custom-nav-home" role="tab" aria-controls="custom-nav-home"
                                                       aria-selected="true">Linhas de Credito</a>
                                                    <a class="nav-item nav-link" id="custom-nav-profile-tab" data-toggle="tab" href="#custom-nav-profile" role="tab" aria-controls="custom-nav-profile"
                                                       aria-selected="false">Dados Instituição</a>
                                                    <!--                                                    <a class="nav-item nav-link" id="custom-nav-contact-tab" data-toggle="tab" href="#custom-nav-contact" role="tab" aria-controls="custom-nav-contact"
                                                                                                           aria-selected="false">Nova</a>-->
                                                </div>
                                            </nav>
                                            <div class="tab-content pl-3 pt-2" id="nav-tabContent">
                                                <div class="tab-pane fade show active" id="custom-nav-home" role="tabpanel" aria-labelledby="custom-nav-home-tab">
                                                    <p><br>

                                                        <%
                                                            if (request.getParameter("msg") != null) {


                                                        %>

                                                    <div class="sufee-alert alert with-close alert-success alert-dismissible fade show col-9" id="div_results">
                                                        <span class="badge badge-pill badge-success">Success</span>
                                                        Operação efectuada com sucesso
                                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    
                                                    <% }%>

                                                    <div class="login-form">
                                                        <form action="/microsapp/ConfigController?acao=1" method="post" class="">

                                                            <div class="row">
                                                                <div class="col-3">
                                                                    <label>Tipo de Credito:</label>

                                                                    <input class="au-input au-input--full" type="text" name="tipocredito" required="">


                                                                </div>

                                                                <div class="col-3">
                                                                    <label>Percentagem do Juro:</label>
                                                                    <input class="au-input au-input--full" type="text" name="juro" required="">

                                                                </div>
                                                                <div class="col-3">
                                                                    <label>Estado:</label>
                                                                    <select id="status" name="status" class="form-control" required="">

                                                                        <option value="true">On</option>
                                                                        <option value="false">Off</option>

                                                                    </select>
                                                                </div>

                                                            </div>

                                                            <div class="row">

                                                                <div class="col-3">
                                                                    <label>QTD. Mes:</label>
                                                                    <input class="au-input au-input--full" type="number" name="mes" required="">

                                                                </div>


                                                                <div class="col-3">
                                                                    <label>&nbsp;</label>

                                                                    <button class="btn  btn-success btn-block" type="submit">Guardar</button>
                                                                </div>
                                                                <div class="col-3">
                                                                    &nbsp;
                                                                </div>

                                                            </div>

                                                        </form>

                                                    </div>
                                                    <hr>


                                                    <jsp:include page="lists/juros.jsp?acao=1"></jsp:include>

                                                    </div>


                                                    <div class="tab-pane fade" id="custom-nav-profile" role="tabpanel" aria-labelledby="custom-nav-profile-tab">
                                                        <p><br>

                                                        <form action="/microsapp/ConfigController?acao=2" method="post">


                                                        <%
                                                            if (request.getParameter("msg1") != null) {
                                                        %>

                                                        <div class="sufee-alert alert with-close alert-success alert-dismissible fade show col-12">
                                                            <span class="badge badge-pill badge-success">Success</span>
                                                            Operação efectuada com sucesso
                                                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <% }%>
                                                        <div class="row">
                                                            <div class="col-4">
                                                                <label>Nome da Instituição:</label>
                                                                <input class="au-input au-input--full" type="text"
                                                                       name="nomeinstituicao" required="valores" value="${profile.instituicao}">

                                                            </div>

                                                            <div class="col-3">
                                                                <label>Nome Fantasia:</label>
                                                                <input class="au-input au-input--full" type="text"
                                                                       name="nomefantasia" required="" value="${profile.nomefantasia}">

                                                            </div>

                                                            <div class="col-4">
                                                                <label>Nome o Gestor:</label>
                                                                <input class="au-input au-input--full" type="text" 
                                                                       name="nomegestor" required="" value="${profile.gestor}">

                                                            </div>

                                                        </div>
                                                        <div class="row">
                                                            <div class="col-4">
                                                                <label>Conatcto Gestor:</label>
                                                                <input class="au-input au-input--full" type="text" 
                                                                       name="contacto" required="" value="${profile.contacto}">

                                                            </div>
                                                            <div class="col-3">
                                                                <label>Email Gestor:</label>
                                                                <input class="au-input au-input--full" type="text" 
                                                                       name="email" required="" value="${profile.email}">

                                                            </div>

                                                            <div class="col-4">
                                                                <label>Endereço Instituição:</label>
                                                                <input class="au-input au-input--full" type="text" 
                                                                       name="endereco" required="" value="${profile.endereco}">

                                                            </div>

                                                        </div>

                                                        <div class="row">

                                                            <div class="col-4">
                                                                <label>Logotipo:</label>
                                                                <input class="au-input au-input--full" type="file" 
                                                                       name="logotipo" required="" value="${profile.urlimagem}">
                                                            </div>


                                                            <div class="col-3">
                                                                <label>&nbsp;</label>

                                                                <button class="au-btn au-btn--block au-btn--green m-b-6" type="submit">Guardar ou Editar</button>
                                                            </div>
                                                        </div>

                                                    </form>


                                                    </p>
                                                </div>
                                                <div class="tab-pane fade" id="custom-nav-contact" role="tabpanel" aria-labelledby="custom-nav-contact-tab">
                                                    <p>



                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /# column -->
                                </div>
                            </div>
                        </div>
                                                            
                                                                      
                    </div>
                    <!-- END PAGE CONTAINER-->

                </div>
            </div>
        </div>

                </body>
                <script type="text/javascript">
                    $(document).ready(function(){
                       
                        $.ajax({
                            url:'editCtr',
                            data:{acao:1},
                            success:function(rs){
                                alert(rs);
                            }
                        });
                    })
                </script>

                </html>
