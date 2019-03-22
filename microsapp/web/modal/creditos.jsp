<%-- 
    Document   : modalCreditos
    Created on : Feb 20, 2019, 9:32:55 AM
    Author     : Raimundo Jose
--%>

<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="app.model.Instituicao"%>
<%@page import="java.util.List"%>
<%@page import="app.controller.InstituicaoJpaController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <jsp:include page="../layouts/_header.html"/>
    </head>
   <body class="animsition">
            <!-- modal medium -->
            
            <%
                 EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
            %>
            <div class="modal fade" id="credits_modal" tabindex="-1" role="dialog"
                 aria-labelledby="mediumModalLabel" aria-hidden="true" data-backdrop="static" th:fragment="modal">

                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">

                        <div class="modal-header alert alert-warning">
                            <h3 class="modal-title" id="modal_credits">SOLICITAR CRÉDITO</h3>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div >

                            <div class="modal-body" style="padding: 30px 50px">

                                <div class="sufee-alert alert with-close alert-success alert-dismissible fade show">
                                    <span class="badge badge-pill badge-success">Success</span>
                                    Mensagem de successo
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <hr>

                                <form action="/jmicrocreditosapp/CreditoController" method="post" novalidate="novalidate">

                                    <div class="row">
                                        <div class="col-6">

                                            <div class="form-group">

                                                <label for="montante" class="control-label mb-1">Indique o valor</label>
                                                <input required="required" id="montante" name="montante" type="text" class="form-control" aria-required="true" aria-invalid="false" value="">

                                            </div>
                                        </div>

                                        <div class="col-6">
                                            <div class="form-group">
                                                <label for="datapag" class="control-label mb-1">Data de Pagamento</label>
                                                <input required="required" id="datapag" name="datapag" type="date" class="form-control"
                                                       aria-required="true" aria-invalid="false" value="">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-6">

                                            <div class="form-group">

                                                <label for="dataemp" class="control-label mb-1">Data de Emprestimo</label>
                                                <input required="required" id="dataemp" name="dataemp" type="date" class="form-control"
                                                       aria-required="true" aria-invalid="false" value="">
                                            </div>
                                        </div>

                                        <div class="col-6">

                                            <div class="form-group">

                                                <label for="datapag" class="control-label mb-1">Finalidade do Credito</label>

                                                <select required="required" name="destino" id="destino" onchange="get_val(this.value)" class="form-control">
                                                    <option value="0">Please Select</option>
                                                    <option value="1">Consumo</option>
                                                    <option value="2">Negocio</option>
                                                    <option value="3">Penhor</option>
                                                   
                                                </select>

                                            </div>
                                        </div>
                                    </div>

                                    <div class="funcionario">
                                        <div class="alert alert-secondary" role="alert">Dados do Funcionario</div>

                                        <div class="row">
                                            <div class="col-6">

                                                <label for="instituicao" class="control-label mb-1">Instituição</label>
                                                <select required="required" name="instituicao" id="instituicao" class="form-control">
                                                    <option value="0">Please Select</option>
                                                     <%
                                                    List<Instituicao> ins = new InstituicaoJpaController(emf).findInstituicaoEntities();
                                                    for (Instituicao i : ins) {

                                                %>
                                                <option value="<%= i.getIdinstituicao() %>"><%= i.getDescricao() %></option>

                                                <% } %>
                                                    <option value="criar">Adicionar</option>

                                                </select>
                                            </div>

                                            <div class="col-6">

                                                <div class="form-group has-success">
                                                    <label for="funcao" class="control-label mb-1">Função</label>
                                                    <input required="required" id="funcao" name="funcao" type="text" class="form-control datapay valid" data-val="true" data-val-required="Please enter the name on card"
                                                           autocomplete="datapay" aria-required="true" aria-invalid="false" aria-describedby="datapay-error">
                                                    <span class="help-block field-validation-valid" data-valmsg-for="datapay" data-valmsg-replace="true"></span>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">

                                            <label for="contactBoss" class="control-label mb-1">Contacto Gestor</label>
                                            <input required="required" id="contactBoss" name="contactBoss" type="text" class="form-control"
                                                   aria-required="true" aria-invalid="false" value="">
                                        </div>

                                        <div class="alert alert-secondary" role="alert">Dados Bancarios</div>


                                        <div class="form-group" data-validate="O Campo Bem por Penhorar é Obrigatório, Insira!">
                                            <label for="titular">Titular do Conta:</label>
                                            <input required="required" class="form-control" type="text" id="titular" name="titular" value="">
                                        </div>


                                        <div class="row">
                                            <div class="col-6">
                                                <div class="form-group" data-validate="O Campo Bem por Penhorar é Obrigatório, Insira!">
                                                    <label for="nr_conta">Numero da Conta</label>

                                                    <input required="required" class="form-control" type="text" id="nr_conta" name="nr_conta" value="">
                                                </div>

                                            </div>
                                            <div class="col-6">
                                                <div class="form-group" data-validate="O Campo Bem por Penhorar é Obrigatório, Insira!">
                                                    <label for="banco">Nome do Banco</label>
                                                    <input required="required" class="form-control" type="text" id="banco" name="banco" value="">
                                                </div>
                                            </div>
                                        </div>

                                        <h3>Anexar Documentos</h3>
                                        <hr>

                                        <div class="row">
                                            <div class="col-6">
                                                <div class="form-group" data-validate="O Campo Bem por Penhorar é Obrigatório, Insira!">
                                                    <label for="declaracaoServico">Declaração de Serviço</label>
                                                    <input required="required" class="form-control" type="file" id="declaracaoServico" name="declaracaoServico" value="">
                                                </div>

                                            </div>

                                            <div class="col-6">
                                                <div class="form-group" data-validate="O Campo Bem por Penhorar é Obrigatório, Insira!">
                                                    <label for="bi">Fotocopia do B.I</label>
                                                    <input required="required" class="form-control" type="file" id="bi" name="bi" value="">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-6">
                                                <div class="form-group" data-validate="O Campo Bem por Penhorar é Obrigatório, Insira!">
                                                    <label for="banco">Extrato Bancario</label>
                                                    <input required="required" class="form-control" type="file" id="extrato" name="extrato" value="">
                                                </div>

                                            </div>
                                            <div class="col-6">
                                                <div class="form-group" data-validate="O Campo Bem por Penhorar é Obrigatório, Insira!">
                                                    <label for="penhor">Declaração Válida:</label>
                                                    <input required="required" class="form-control" type="file" id="penhor" name="penhor" value="">
                                                </div>
                                            </div>
                                        </div>

                                    </div> <!--  fim div funcionario-->


                                    <div class="comerciante">

                                        <div class="alert alert-secondary" role="alert">Dados do Credito - Negocio</div>


                                        <div class="row">
                                            <div class="col-6">

                                                <label for="titular">Testemunha 1:</label>
                                                <input required="required" class="form-control" type="text" id="testemunha1" name="testemunha1" value="">
                                            </div>
                                            <div class="col-6">

                                                <label for="nr_conta">Testemunha 2</label>
                                                <input required="required" class="form-control" type="text" id="testemunha2" name="testemunha2" value="">

                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-6">

                                                <div class="form-group" data-validate="O Campo Bem por Penhorar é Obrigatório, Insira!">
                                                    <label for="banco">Bem a Penhorar</label>
                                                    <input required="required" class="form-control" type="text" id="bempenhor" name="bempenhor" value="">
                                                </div>
                                            </div>
                                            <div class="col-6">

                                                <div class="form-group" data-validate="O Campo Bem por Penhorar é Obrigatório, Insira!">
                                                    <label for="decbairro">Declaração do Bairro</label>
                                                    <input required="required" class="form-control" type="file" id="decbairro" name="decbairro" value="">
                                                </div>

                                            </div>
                                        </div>

                                    </div> <!--- fim comerciante -->

                                    <div class="creditoPenhor">

                                        <div class="alert alert-secondary" role="alert">Credito - Pela Penhor</div>
                                        <div class="row">
                                            <div class="col-6">

                                                <div class="form-group" data-validate="O Campo Bem por Penhorar é Obrigatório, Insira!">
                                                    <label for="banco">Declaração do Imóvel</label>
                                                    <input required="required" class="form-control" type="file"
                                                           id="urldecimovel" name="urldecimovel" value="">
                                                </div>
                                            </div>
                                            <div class="col-6">

                                                <div class="form-group" data-validate="O Campo Bem por Penhorar é Obrigatório, Insira!">

                                                    <label for="banco">Declaração do Penhor</label>
                                                    <input required="required" class="form-control" type="file"
                                                           id="ulrdeclaracobairro" name="urldeclaracaobairro" value="">
                                                </div>

                                            </div>
                                        </div>

                                    </div>

                                    <div class="modal-footer">
                                        <!--<button type="reset" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>-->
                                        <!---->
                                        <button type="submit" class="btn btn-primary">Enviar Pedido</button>
                                    </div>
                                </form>
                            </div>

                        </div>
                </div>
            </div>
                </div>

            <!-- end modal medium -->
            <jsp:include page="../layouts/_footer.html"/>
    </body>
</html>
