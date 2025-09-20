<%-- 
    Document   : cadastrado
    Created on : 18 de set. de 2025, 22:51:27
    Author     : gabi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        request.setAttribute("pageTitle", "Usuário cadastrado");
        request.setAttribute("carregarBootstrap", true);
    %>
    <%@ include file="header.jsp" %>
    <body>
        <%  String email = (String) request.getAttribute("email");%>

        <div class="container mt-5">
            <div class="alert alert-success" role="alert">
                <span>E-mail <%= email%> cadastrado com sucesso!</span>
            </div>

            <h2>Você será redirecionado para a página de login...</h2>
        </div>


        <script>
            setTimeout(() => {
                location.href = 'login.jsp';
            }, 3000);
        </script>
    </body>
</html>
