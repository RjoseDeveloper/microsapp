<%-- 
    Document   : register
    Created on : Feb 20, 2019, 9:29:25 AM
    Author     : Raimundo Jose
--%>

<%@page import="app.controller.InstituicaoJpaController"%>
<%@page import="app.model.Instituicao"%>
<%@page import="app.controller.EstadocivilJpaController"%>
<%@page import="app.controller.EstadoJpaController"%>
<%@page import="app.model.Estadocivil"%>
<%@page import="app.controller.ProvinciaJpaController"%>
<%@page import="app.model.Provincia"%>
<%@page import="app.controller.DistritoJpaController"%>
<%@page import="app.model.Distrito"%>
<%@page import="app.controller.SexoJpaController"%>
<%@page import="app.model.Sexo"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="app.model.Tipocredito"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <jsp:include page="../layouts/_header.html"/>
    </head>
    <body class="animsition">

        <%
            EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");

            List<Distrito> distrito = new DistritoJpaController(emf).findDistritoEntities();
            List<Provincia> provincia = new ProvinciaJpaController(emf).findProvinciaEntities();
            List<Estadocivil> estadocivils = new EstadocivilJpaController(emf).findEstadocivilEntities();
            List<Instituicao> instituicao = new InstituicaoJpaController(emf).findInstituicaoEntities();
        %>


        <div class="page-wrapper">
            <div class="page-content--bgf7">
                <div class="container">
                    <div class="login-wrap">
                        <div class="login-content">

                            <div class="login-logo">
                                <a href="#" class="alert-heading">
                                    <img src="../resources//images/icon/logo.png" alt="CoolAdmin">
                                </a>

                            </div>

                            <div class="login-form">
                                <form action="/jmicrocreditosapp/UsersController" method="post" novalidate="novalidate">

                                    <div class="row">
                                        <div class="col-6">
                                            <label for="nome" class="control-label mb-1">Primeiro nome: </label>
                                            <input id="nome" name="nome" type="text" class="form-control" aria-required="true"
                                                   aria-invalid="false" value="">
                                        </div>
                                        <div class="col-6">
                                            <label for="apelido" class="control-label mb-1">Apelido</label>
                                            <input id="apelido" name="apelido" type="text" class="form-control apelido valid"
                                                   data-val="true"
                                                   data-val-required="Please enter the name on card"
                                                   autocomplete="apelido" aria-required="true" aria-invalid="false"
                                                   aria-describedby="apelido-error">
                                            <span class="help-block field-validation-valid" data-valmsg-for="apelido"
                                                  data-valmsg-replace="true"></span>
                                        </div>

                                    </div>

                                    <div class="form-group">

                                        <label for="nr_documento" class="control-label mb-1">Nr. Documento:</label>
                                        <div class="input-group">
                                            <input id="nr_documento" name="nr_documento" type="text" class="form-control cc-cvc"
                                                   value=""
                                                   data-val="true" data-val-required="Please enter the security code"
                                                   data-val-cc-cvc="Please enter a valid security code" autocomplete="off">
                                        </div>
                                    </div>

                                    <div class="row">

                                        <div class="col-6">
                                            <label for="sexo" class="control-label mb-1">Sexo:</label>
                                            <select id="sexo" name="sexo" class="form-control">

                                                <%
                                                    List<Sexo> sexos = new SexoJpaController(emf).findSexoEntities();
                                                    for (Sexo s : sexos) {

                                                %>
                                                <option value="<%= s.getIdsexo()%>"><%= s.getDescricao()%></option>

                                                <% } %>
                                            </select>
                                        </div>

                                        <div class="col-6">
                                            <div class="form-group">
                                                <label for="estadocivil" class="control-label mb-1">Estado Civil</label>
                                                <select class="form-control" id="estadocivil" name="estadocivil">
                                                    <%
                                                        for (Estadocivil e : estadocivils) {

                                                    %>
                                                    <option value="<%= e.getIdestadocivil()%>"><%= e.getDescricao()%></option>

                                                    <% } %>

                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">

                                        <div class="col-6">
                                            <label for="provincia" class="control-label mb-1">Provincia:</label>

                                            <select class="form-control" id="provincia" name="provincia">
                                                <%
                                                    for (Provincia p : provincia) {

                                                %>
                                                <option value="<%= p.getIdprovincia()%>"><%= p.getDescricao()%></option>

                                                <% } %>

                                            </select>
                                        </div>

                                        <div class="col-6">
                                            <div class="form-group">
                                                <label for="distrito" class="control-label mb-1">Distrito</label>
                                                <select class="form-control" id="distrito" name="distrito">
                                                    <%
                                                        for (Distrito d : distrito) {

                                                    %>
                                                    <option value="<%= d.getIdidstrito()%>"><%= d.getDescricao()%></option>

                                                    <% }%>

                                                </select>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="form-group">

                                        <label for="endereco1" class="control-label mb-1">Endereco 1:</label>
                                        <input id="endereco1" name="endereco1" type="text" class="form-control endereco1 identified visa" value="" data-val="false"
                                               data-val-required="Insira o endereço" autocomplete="endereco1">
                                        <span class="help-block" data-valmsg-for="endereco1" data-valmsg-replace="true"></span>
                                    </div>

                                    <div class="form-group">
                                        <div class="form-group">
                                            <label for="endereco2" class="control-label mb-1">Endereco 2:</label>
                                            <input id="endereco2" name="endereco2" type="text"
                                                   class="form-control endereco2 identified visa" value="" data-val="false"
                                                   data-val-required="Insira o endereço" autocomplete="endereco2">
                                            <span class="help-block" data-valmsg-for="endereco1"
                                                  data-valmsg-replace="true"></span>
                                        </div>
                                    </div>


                                    <div class="row">

                                        <div class="col-6">

                                            <label for="contacto1" class="control-label mb-1">Contacto 1:</label>
                                            <input id="contacto1" name="contacto1" type="text"
                                                   class="form-control contacto1 identified visa" value="" data-val="false"
                                                   data-val-required="Insira o seu contacto"
                                                   data-val-username="Please enter a valid contacto"
                                                   autocomplete="contacto1">
                                            <span class="help-block" data-valmsg-for="contacto1"
                                                  data-valmsg-replace="true"></span>

                                        </div>

                                        <div class="col-6">
                                            <div class="form-group">
                                                <label for="contacto2" class="control-label mb-1">Contacto 2:</label>
                                                <input id="contacto2" name="contacto2" type="text"
                                                       class="form-control contacto1 identified visa" value="" data-val="false"
                                                       data-val-required="Insira o seu contacto"
                                                       data-val-username="Please enter a valid contacto"
                                                       autocomplete="contacto2">
                                                <span class="help-block" data-valmsg-for="contacto1"
                                                      data-valmsg-replace="true"></span>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="form-group">

                                        <label for="email" class="control-label mb-1">Email:</label>
                                        <div class="input-group">
                                            <input id="email" name="email" type="email" class="form-control" value=""
                                                   data-val="true"
                                                   data-val-required="Inserira o email"
                                                   data-val-cc-cvc="Insira email" autocomplete="off">
                                        </div>
                                    </div>

                                    <div class="row">

                                        <div class="col-6">

                                            <label for="password" class="control-label mb-1">Password:</label>
                                            <input id="password" name="password" type="password"
                                                   class="form-control password identified visa" value="" data-val="false"
                                                   data-val-required="Insira o seu passord"
                                                   data-val-username="Please enter a valid password"
                                                   autocomplete="password">
                                            <span class="help-block" data-valmsg-for="username"
                                                  data-valmsg-replace="true"></span>

                                        </div>

                                        <div class="col-6">
                                            <div class="form-group">
                                                <label for="password1" class="control-label mb-1">Confirmar Password:</label>
                                                <input id="password1" name="password1" type="password"
                                                       class="form-control password" value="" data-val="true"
                                                       data-val-required="Please enter the card expiration"
                                                       data-val-password="Please enter a valid month and year" placeholder=""
                                                       autocomplete="password1">
                                                <span class="help-block" data-valmsg-for="password"
                                                      data-valmsg-replace="true"></span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-6">

                                            <div class="form-check">
                                                <div class="checkbox">
                                                    <label for="checkbox1" class="form-check-label ">
                                                        <input onclick="active_func()" type="checkbox"
                                                               id="checkbox1" name="checkbox1" value="option1"
                                                               class="form-check-input">É Funcionario ?
                                                    </label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-12 func">

                                            <div class="form-group">

                                                <label for="instituicao" class="control-label mb-1">
                                                    Nome da Instituição</label>
                                                <div class="input-group">

                                                    <select class="form-control" name="instituicao" id="instituicao" onchange="new_record(this.value)">
                                                        <%         for (Instituicao i : instituicao) {

                                                        %>
                                                        <option value="<%= i.getIdinstituicao()%>"><%= i.getDescricao()%></option>

                                                        <% }%>

                                                    </select>

                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <button id="btn_register" type="submit" class="btn btn-lg btn-info btn-block">
                                        <span id="btn_register-amount">Registar</span>
                                        <span id="btn_register-sending" style="display:none;">Sending…</span>
                                    </button>

                                    <div class="register-link">
                                        <p> Já Esta registado?
                                            <a href="login.jsp">Login</a>
                                        </p>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <jsp:include page="../layouts/_footer.html"/>


        </div>
    </th:block>
</body>
</html>
