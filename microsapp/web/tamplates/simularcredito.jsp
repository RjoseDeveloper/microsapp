<%-- 
    Document   : simularcredito
    Created on : Mar 20, 2019, 1:21:30 AM
    Author     : Raimundo Jose
--%>

<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="app.controller.TipocreditoJpaController"%>
<%@page import="app.model.Tipocredito"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="../fragments/header_admin.jsp" />
    </head>
    <body class="animsition">

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
                                        <h4>SESSÃO DE SIMULAÇÃO DE CRÉDITOS</h4>
                                    </div>
                                    <div class="card-body">

                                        <div class="row">

                                            <div class="col-lg-6">
                                                <div class="card-body card-block">
                                                    <div class="form-horizontal">

                                                        <div class="row form-group">
                                                            <div class="col col-md-4">
                                                                <label for="hf-email" class=" form-control-label">Montante:</label>
                                                            </div>
                                                            <div class="col-12 col-md-8">
                                                                <input type="number" id="valor" name="valor" placeholder="Digite o valor" class="form-control">

                                                            </div>
                                                        </div>
                                                        <div class="row form-group">

                                                            <div class="col col-md-4">
                                                                <label for="tipocredito" class=" form-control-label">Tipo de Credito</label>
                                                            </div>

                                                            <div class="col-12 col-md-8">
                                                                <select class="form-control" onchange="_dados_credits(this.value)" name="tipocredito" id="tipocredito">

                                                                    <option value="-1">Select Option</option>
                                                                    <%
                                                                        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");

                                                                        List<Tipocredito> tiplist = new TipocreditoJpaController(emf).findTipocreditoEntities();
                                                                        for (Tipocredito c : tiplist) {
                                                                    %>

                                                                    <option value="<%= c.getIdcrecredito()%>"><%= c.getDescricao()%></option>
                                                                    <% }%>
                                                                </select>

                                                            </div>
                                                        </div>

                                                        <div class="row form-group">
                                                            <div class="col col-md-4">
                                                                <label for="juro" class=" form-control-label">Taxa de Juro</label>
                                                            </div>
                                                            <div class="col-12 col-md-8">
                                                                <input type="text" id="juro" name="juro" readonly=""  class="form-control">

                                                            </div>
                                                        </div>
                                                        <div class="row form-group">
                                                            <div class="col col-md-4">
                                                                <label for="qtdmes" class=" form-control-label">Qtd. Mes</label>
                                                            </div>
                                                            <div class="col-12 col-md-8">
                                                                <input type="number" id="qtdmes" name="qtdmes" class="form-control">
                                                                <span id="pgto">PGTO Máximo: </span>

                                                            </div>
                                                        </div>
                                                        <hr>

                                                        <button type="submit" class="btn btn-primary btn-sm" id="btn_simular">
                                                            <i class="fa fa-dot-circle-o"></i> Simular
                                                        </button>
                                                        <button type="reset" class="btn btn-danger btn-sm">
                                                            <i class="fa fa-ban"></i> Cancelar
                                                        </button>

                                                    </div>

                                                </div>



                                                </p>
                                            </div>

                                            <div class="col-lg-6">
                                                <br>

                                                <div class="row form-group">
                                                    <div class="col col-md-4">
                                                        <label for="hf-email" class=" form-control-label">Proximo PGTO:</label>
                                                    </div>
                                                    <div class="col-12 col-md-8">
                                                        <input type="number" id="valor_rs" name="valor_rs" readonly="" class="form-control">

                                                    </div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-4">
                                                        <label for="hf-email" class=" form-control-label">Total de Juros:</label>
                                                    </div>
                                                    <div class="col-12 col-md-8">
                                                        <input type="number" id="juro_rs" name="juro_rs" readonly="" class="form-control">

                                                    </div>
                                                </div>
                                                <div class="row form-group">
                                                    <div class="col col-md-4">
                                                        <label for="hf-email" class=" form-control-label">PGTO:</label>
                                                    </div>
                                                    <div class="col-12 col-md-8">
                                                        <input type="number" id="pgto_rs" name="pgto_rs" readonly="" class="form-control">

                                                    </div>
                                                </div>

                                                <div class="row form-group">
                                                    <div class="col col-md-4">
                                                        <label for="hf-email" class=" form-control-label">Valor Final:</label>
                                                    </div>
                                                    <div class="col-12 col-md-8">
                                                        <input type="text" id="vf" name="vf" readonly=""  value="" class="form-control">

                                                    </div>
                                                </div>
                                                <!--
                                                                                                <div class="row form-group">
                                                                                                    <div class="col col-md-4">
                                                                                                        <label for="hf-email" class=" form-control-label">DATA DE PAGAMENTO:</label>
                                                                                                    </div>
                                                                                                    <div class="col-12 col-md-8">
                                                                                                        <input type="text" id="data" name=data readonly="" value="" class="form-control">
                                                
                                                                                                    </div>
                                                                                                </div>-->

                                            </div>


                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="../modal/creditos.jsp"/>
        <script>
            $(document).ready(function () {



                $('#btn_simular').click(function () {

                    var valor = $('#valor').val();
                    var juro = $('#juro').val();
                    var qtdmes = $('#qtdmes').val();

                    var valorNjuro = valor / qtdmes;

                    var juro = valorNjuro * (juro / 100);
                    var tjuro = valorNjuro + juro;
                    var vf = tjuro * qtdmes;

                    $('#vf').val(parseFloat(vf) + ",00");
                    $('#valor_rs').val(parseFloat(tjuro));
                    $('#juro_rs').val(parseFloat(juro));
                    $('#pgto_rs').val(qtdmes);
                    $('#data').val(d);

                });
            });

            function _dados_credits(item) {

                $.ajax({
                    
                    url: "/microsapp/GetJson",
                    type: 'GET',
                    dataType: 'json',
                    data: {acao: 1, idtipocredito: item},
                    contentType: 'application/json',
                    mimeType: 'application/json',
                    success: function (data) {
                        
                        $('#juro').val(data.juro);
                        $('#qtdmes').val(data.pgto);
                        $('#pgto').html('PGTO Maximo: '+data.pgto).css('color','red');

                    },
                    error: function (data, status, er) {
                        alert("error: " + data + " status: " + status + " er:" + er);
                    }
                });


            }
        </script>
</html>
