<%-- 
    Document   : remover
    Created on : 18 de set. de 2025, 22:25:52
    Author     : gabi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remover sessão</title>
    </head>
    <body>
        <%
        session.removeAttribute("email");
        response.sendRedirect("login.jsp");
        %>
    </body>
</html>
