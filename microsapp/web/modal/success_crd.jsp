<%-- 
    Document   : instituicao_modal
    Created on : Feb 22, 2019, 6:23:25 PM
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
        
        <!-- modal static -->
			<div class="modal fade" id="staticModal" tabindex="-1" role="dialog" aria-labelledby="staticModalLabel" aria-hidden="true"
			 data-backdrop="static">
				<div class="modal-dialog modal-sm" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="staticModalLabel">Mensagem do Sistema</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<p>
								Operação realizada com sucesso
							</p>
						</div>
						<div class="modal-footer">
                                                    <button type="button" onclick="history.go(-1)" style="color: white" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
							
						</div>
					</div>
				</div>
			</div>
			<!-- end modal static -->

    </body>
    <jsp:include page="../layouts/_footer.html"/>
</html>

<script>
    
        $('#staticModal').modal();
  
    
</script>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 