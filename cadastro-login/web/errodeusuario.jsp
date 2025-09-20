<%-- 
    Document   : errodeusuario
    Created on : 18 de set. de 2025, 22:25:29
    Author     : gabi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERRO</title>
        <style>
            .conteudo-alinhado {
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
            }
        </style>
    </head>
    <body>
        <div class="conteudo-alinhado">
            <img src="imagens/erro1.png" width="250" height="250" alt="alt"/>
            <hr />
            <h3>Ocorreu um erro ao realizar login...</h3>
            <p>
                <a href="login.jsp">Tentar novamente</a>
            </p>
            <p>
                <a href="index.jsp">Cadastre-se</a>
            </p>
        </div>
    </body>
</html>
