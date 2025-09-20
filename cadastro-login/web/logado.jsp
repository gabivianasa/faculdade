<%-- 
    Document   : logado
    Created on : 18 de set. de 2025, 22:25:40
    Author     : gabi
--%>

<%@page import="br.com.conexao.Conexao"%>
<%@page import="br.com.login.UsuarioDao"%>
<%@page import="br.com.login.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        request.setAttribute("pageTitle", "Home");
        request.setAttribute("carregarBootstrap", true);
    %>
    <%@ include file="header.jsp" %>

    <body>
        <div class="alert alert-primary m-2" role="alert">
            <% String emailUsuario = (String) session.getAttribute("email");%>

            <div class="d-flex align-items-center justify-content-between">
                <span>Seja bem-vindo(a): <%=emailUsuario%></span>
                <a class="link-dark link-offset-2 link-underline-opacity-0 link-underline-opacity-100-hover" href="remover.jsp">Sair</a>
            </div>
        </div>


        <h3 class="mt-5 ms-2">Usuários cadastrados no sistema:</h3>
        <hr />

        <div class="container">
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nome</th>
                        <th scope="col">E-mail</th>
                        <th scope="col">Senha</th>
                        <th scope="col" style="width: 200px">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        UsuarioDao usuarioDao = new UsuarioDao(Conexao.getConexao());
                        List<Usuario> usuarios = usuarioDao.listar();
                        for (Usuario usuario : usuarios) {
                    %>
                    <tr>
                        <td scope="row"><%= usuario.getId()%></td>
                        <td scope="row"><%= usuario.getNome()%></td>
                        <td scope="row"><%= usuario.getEmail()%></td>
                        <td scope="row"><%= usuario.getSenha()%></td>
                        <td scope="row">
                            <form action="alterar.jsp" method="post" style="display:inline;">
                                <input type="hidden" name="idUsuario" value="<%= usuario.getId()%>">
                                <input type="hidden" name="nomeUsuario" value="<%= usuario.getNome()%>">
                                <input type="hidden" name="emailUsuario" value="<%= usuario.getEmail()%>">
                                <input type="hidden" name="senhaUsuario" value="<%= usuario.getSenha()%>">
                                <button class="btn btn-warning" type="submit">Editar</button>
                            </form>

                            <form action="ApagarUsuario" method="post" style="display:inline;">
                                <input type="hidden" name="idUsuario" value="<%= usuario.getId()%>">
                                <button class="btn btn-danger" type="submit" onclick="return confirm('Deseja realmente apagar este usuário?')">Apagar</button>
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <%@ include file="footer.jsp" %>
    </body>
</html>
