<%-- 
    Document   : alterar
    Created on : 19 de set. de 2025, 20:57:36
    Author     : gabi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        request.setAttribute("pageTitle", "Alterar usuário");
        request.setAttribute("carregarBootstrap", true);
    %>
    <%@ include file="header.jsp" %>
    <body class="container">
        <h3 class="my-5">Atualizar cadastro do usuário: <%= request.getParameter("nomeUsuario")%></h3>
        <form action="AlteraCadastroLogin" method="post">
            <div class="row">
                <div class="col">
                    <label for="id" class="form-label">ID</label>
                    <input name="id" id="id" class="form-control" readonly value="<%= request.getParameter("idUsuario")%>" />
                </div>      
                <div class="col">
                    <label for="nome" class="form-label">Nome</label>
                    <input name="nome" id="nome" value="<%= request.getParameter("nomeUsuario")%>" style="width: 100%" />

                </div>
                <div class="col">
                    <label for="email" class="form-label">E-mail</label>
                    <input name="email" type="email" id="email" value="<%= request.getParameter("emailUsuario")%>" style="width: 100%" />

                </div>
                <div class="col">
                    <label for="senha" class="form-label">Senha</label>
                    <input name="senha" id="senha" type="password" maxlength="8" value="<%= request.getParameter("senhaUsuario")%>" style="width: 100%" />

                </div>
                <div class="col d-flex align-items-center">
                    <button class="btn btn-primary" type="submit">Atualizar</button>
                </div>
            </div>
        </form>
    </body>
</html>
