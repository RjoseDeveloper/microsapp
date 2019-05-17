<%-- 
    Document   : listaDistrito
    Created on : Nov 4, 2018, 12:12:07 PM
    Author     : Rofino Chunga Jr
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="app.controller.DistritoJpaController"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="app.model.Distrito"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="_css/search-box.css" />
        <link rel="stylesheet" type="text/css" href="_css/modal.css" />
        <link rel="stylesheet" type="text/css" href="_css/login_style.css" />
        <link rel="stylesheet" type="text/css" href="_css/listaEstudantes_style.css" />
        <title>Lista de Distritos</title>

        <script src="jquery/jquery"></script>
        <script>
            /* $(document).ready(function (e) {
             $('#loadPage').load('adicionarDistrito.jsp', function (data) {
             $(this).html(data);
             });
             });*/
        </script>
        <style>
            .login-box{
                width: 100%;

                background: white;
            }
            #loadPage{
                width: 50%;
                margin: 10px auto;
                background-color: white ;
                height:200px;
            }
            td a{
                color:white;
                background: #00adee;  
                padding: 5px;
                border-radius: 5px;
                text-decoration: none;
            }
            td  a:hover{
                background: yellowgreen;
            }
            .modal-content{
                background: white;
                height: 20px;

                border: none;
                border-radius: 0px;
            }
            .modal-content p{
                font-family:  sans-serif;
                font-size: 20px;
            }
            tr:hover{
                background: #A7C3DB;
            }
        </style>

    </head>
    <body style=" background: white">

        <div id="cadastroDistrito" class="modal" > 
            <div id="loadPage" class="modal-content">
                <div class="login-box" >
                    <span class="close">&times;</span>
                    <form action="AdicionarDistrito" method="GET">
                        <label style=" text-align: center"t> <h3 style="color: black">Cadastrar Distrito</h3></label>

                        <label>Distrito:</label>
                        <input type="hidden" name="ide" value="${distrito.idDistrito}">
                        <div class="textbox">
                            <input type="text" name="distrito" value="${distrito.nomeDestrito}" placeholder="Nome do Distrito">
                        </div>

                        <label>Provincia:</label>
                        <select class="textbox" name="provincia">    
                            <option>Cabo Delgado</option>
                            <option>Nampula </option>
                            <option>Niassa </option>
                            <option>Tete </option>
                            <option>Manica </option>
                            <option>Sofala </option>
                            <option>Inhambane </option>
                            <option>Gaza </option>
                            <option>Maputo </option>
                            <option>Maputo Cidade </option>
                        </select>
                        <button type="submit" class="btn">Adicionar</button>
                    </form>
                </div>
            </div>
        </div>


        <%if (request.getAttribute("distrito") != null) {

        %>
        <script>
            document.getElementById("cadastroDistrito").style.display = 'block';
        </script>
        <% }%>
       
        <div class="search_box">
            <input type="text" class="search_box_input" id="complete-field" placeholder="Pesquisar..." onkeyup="doCompletion()">
        </div>

        <div id="myModal" class="modal" >
            <!-- Modal content -->
            <div class="modal-content" style=" height: 70px">
                <b style=" font-family:  sans-serif; font-size: 20px">Eliminar?</b><br><br>

                <button class="yes" onclick="eliminarP();">Sim </button>
                <button class="no" onclick="fechar()">  Não </button>
            </div>                  
        </div>
        <div id="sucessoModal" class="modal">
            <div class="modal-content" style=" height: 70px">
                <b style=" font-family:  sans-serif; font-size: 20px">Eliminado com sucesso!</b><br>
                <button class="no" onclick="fechar()">Ok</button>
            </div>
        </div>
        <div id="adicionado" class="modal" >
            <!-- Modal content -->
            <div class="modal-content" style=" background: white; border: none; height: 70px; border-radius: 0px">
                <b style=" font-family:  sans-serif; font-size: 20px; ">Adicionado com sucesso!</b><br>
                <button class="no" onclick="fechar()">  Ok </button>
            </div>                  
        </div>

        <% if (request.getAttribute("verificarAdd") != null) {%>
        <script>
            document.getElementById("adicionado").style.display = 'block';
        </script>
        <%}%>
        <!-- < % if (request.getAttribute("verificarEli") != null) {%>
       <script>
           document.getElementById("adicionado").style.display = 'block';
       </script>
       < %}%> -->

        <input type="hidden" id="linha" value="">
        <input type="hidden" id="id" value="">

        <h2>Lista de Distritos</h2>

        <%!List<Distrito> distritos;
            ArrayList<Distrito> distrit = new ArrayList<>();%>
        <%
            List<Distrito> distritoList = (List<Distrito>) request.getAttribute("distritos");
            if (distritoList != null && !distritoList.isEmpty()) {
                distritos = distritoList;
            } else {
                EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
                DistritoJpaController distritoController = new DistritoJpaController(emf);
                distritos = distritoController.findDistritoEntities();
            }


        %>

        <a onclick=" cadastroDistrito(); return false;" href="adicionarDistrito.jsp" style=" border-radius: 0px" target="body" class="adicionar"> Adicionar</a>
        <table >
            <thead>
                <tr>
                    <th>Nome do Distrito</th>
                    <th>Provincia</th>
                    <th>Operação</th>
                </tr>
            </thead>
            <tbody id="tb">
                <% for (int i = 0; i < this.distritos.size(); i++) {

                %>
                <tr id="<%=i%>">
                    <td><%=distritos.get(i).getDescricao() %></td>
                    <td><%= distritos.get(i).getIdprovincia().getDescricao() %></td>


                    <td>
                        <a href="/microsapp/EditarDistrito?ide=<%=distritos.get(i).getIdidstrito() %>">Editar</a>

                        <a onclick="eliminar(<%=i%>,<%=distritos.get(i).getIdidstrito() %>);
                                return false;"  href="#">Eliminar</a>
                    </td>

                </tr>
                <% }%>
            </tbody>
        </table>


        <script>
            var idl, id;
            function clearTable() {
                var t = document.getElementById('tb');
                if (t.getElementsByTagName("tr").length > 0) {
                    // t.style.display = 'none';
                    for (loop = t.childNodes.length - 1; loop >= 0; loop--) {
                        t.removeChild(t.childNodes[loop]);
                    }
                }
            }

            function doCompletion() {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    clearTable();
                    if (this.readyState == 4 && this.status == 200) {
                        myFunction(this);
                    }
                };
                var url = "/microsapp/PesquisarDistrito2?action=complete&id=" + encodeURI(document.getElementById("complete-field").value)
                xhttp.open("POST", url, true);
                xhttp.send();
            }

            function myFunction(xml) {
                var i;
                // var jso = JSON.parse(this.responseText);
                var xmlDoc = xml.responseXML;
                var table = "";//<tr><th>Artist</th><th>Title</th></tr>";

                //alert("gjhgj");
                var x = xmlDoc.getElementsByTagName("distrito");
                for (i = 0; i < x.length; i++) {
                    var row = document.getElementById("tb").insertRow();
                    var c = row.insertCell();

                    c.innerHTML = x[i].getElementsByTagName("nome")[0].childNodes[0].nodeValue;
                    c = row.insertCell();
                    c.innerHTML = x[i].getElementsByTagName("provincia")[0].childNodes[0].nodeValue;
                    c = row.insertCell();

                    var ide = x[i].getElementsByTagName("ide")[0].childNodes[0].nodeValue;
                    c.innerHTML = '<div><a href="/microsapp/EditarDistrito?ide=' + ide + '">Editar</a>' +
                            ' <a onclick="eliminar(' + idl + ',' + ide + '); return false;" href="/microsapp/EliminarDistrito?ide=' + ide + '"> Eliminar</a>' +
                            '</div>';
                }
            }

            var id = 0; // ID do ultimo objecto recebido
            var source = new EventSource("AdicionarDistrito");
            source.onmessage = function (event) {
                var ar = event.data.split("|"); //Convertendo uma String em um Array
                if (ar[0] != id) { // Se for um novo objecto
                    var row = document.getElementById("tb").insertRow(0);
                    //row.id=ar[0];
                    for (loop = 0; loop < ar.length; loop++) {
                        var c = row.insertCell();
                        c.innerHTML = ar[loop];
                    }
                    var c = row.insertCell();
                    c.innerHTML = '<div><a href="/microsapp/EditarDistrito?ide=' + ar[0] + '">Editar</a>' +
                            ' <a href="/microsapp/EliminarDistrito?ide=' + ar[0] + '">Eliminar</a>' +
                            '</div>';


                    id = ar[0];
                    var t = document.getElementById('tb');
                    if (t.getElementsByTagName("tr").length > 0) {
                        // t.style.display = 'none';
                        for (loop = t.childNodes.length - 1; loop >= 0; loop--) {
                            if (t.childNodes[loop].id === ar[0]) {
                                t.removeChild(t.childNodes[loop]);
                            }
                        }
                    }
                }

            };
            function eliminar(idlinha, idI) {
                idl = idlinha;
                id = idI;

                document.getElementById("myModal").style.display = 'block';
            }
            function mostrar() {
                document.getElementById("sucessoModal").style.display = 'block';
            }
            function cadastroDistrito() {
                document.getElementById("cadastroDistrito").style.display = 'block';
            }
            ;

            function eliminarP() {

                idl = document.getElementById("linha").value;
                 id = document.getElementById("id").value;
                 
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        removerLinha(idl, id);
                    }
                };
                fechar();
                var url = "/microsapp/EliminarDistrito?ide=" + id;
                xhttp.open("POST", url, true);
                xhttp.send();
                mostrar();
            }
            ;
            function removerLinha(idl) {
                document.getElementById("tb").removeChild(document.getElementById(idl));

            }
            ;
            function fechar() {
                document.getElementById("myModal").style.display = 'none';
                document.getElementById("sucessoModal").style.display = 'none';
                document.getElementById("adicionado").style.display = 'none';

            }
           
        </script>

        <script>
            function openCity(evt, cityName) {
                var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName("tabcontent");
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = "none";
                }
                tablinks = document.getElementsByClassName("tablinks");
                for (i = 0; i < tablinks.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace(" active", "");
                }
                document.getElementById(cityName).style.display = "block";
                evt.currentTarget.className += " active";
            }

// Get the element with id="defaultOpen" and click on it
            document.getElementById("defaultOpen").click();
        </script>
        <script>
            var span = document.getElementsByClassName("close")[0];
            span.onclick = function () {
                document.getElementById('cadastroDistrito').style.display = "none";
            }
        </script>
    </body>
</html>
