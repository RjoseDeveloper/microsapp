<%-- 
    Document   : provincia
    Created on : Sep 6, 2018, 6:23:55 AM
    Author     : SI2-PC8
--%>

<%@page import="app.model.Distrito"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" type="text/css" href="_css/login_style.css" />

        <style>
            label{
                color: black;
            }

            body{
                background: rgb(233, 235, 238);  
            }

            .login-box{
                width: 100%;

                background: white;
            }
            /* button{
                 font-family:  sans-serif;
                 font-size: 20px;
                 color: white;
                 border-radius: 5px;
                 background:  #207dbb;
                 border: none;
                 border-bottom: 6px solid #006600;
                 cursor: pointer;
             }*/
        </style>
        <title>Adicionar Distrito</title>
    </head>
    <body style=" background: white">
        
        <%! Distrito distrito ;   %>
        <%
            this.distrito=(Distrito) request.getAttribute("distrito");
           
        %>
        
        
        
        <div class="login-box" >
            <span class="close">&times;</span>
            <form action="/microsapp/AdicionarDistrito" method="GET">
                <label style=" text-align: center"t> <h3 style="color: black">Cadastrar Distrito</h3></label>

                <label>Distrito:</label>
                <input type="hidden" name="ide" value="${distrito.iddistrito}">
                <div class="textbox">
                    <input type="text" name="distrito" value="${distrito.descricao}" placeholder="Nome do Distrito">
                </div>

                <label>Provincia:</label>
                <select class="textbox" name="provincia">    
                    <option value="1">Cabo Delgado</option>
                    <option value="2">Nampula </option>
                    <option value="3">Niassa </option>
                    <option value="4">Tete </option>
                    <option value="5">Manica </option>
                    <option value="6">Sofala </option>
                    <option value="7">Inhambane </option>
                    <option value="8">Gaza </option>
                    <option value="9">Maputo </option>
                    <option value="10">Maputo Cidade </option>
                </select>
                <button type="submit" class="btn">Adicionar</button>
            </form>
        </div>

        <script>
            var span = document.getElementsByClassName("close")[0];
            span.onclick = function () {
                document.getElementById('cadastroDistrito').style.display = "none";
            }
        </script>
    </body>
</html>
