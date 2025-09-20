<%-- 
    Document   : login
    Created on : 18 de set. de 2025, 22:26:01
    Author     : gabi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        request.setAttribute("pageTitle", "Login");
    %>
    <%@ include file="header.jsp" %> 
    <body>
        <div class="content">
            <!--FORMULÁRIO DE LOGIN-->
            <div id="login">
                <form name="formCadastro" method="post" action="Login">
                    <h1>Login</h1>
                    <p>
                        <label for="email">E-mail</label>
                        <input id="email" name="email" type="email"
                               placeholder="ex. email@email.com" />
                    </p>

                    <p>
                        <label for="senha">Senha</label>
                        <input id="senha" name="senha" type="password" placeholder="ex. senha" maxlength="8" />
                    </p>

                    <p>
                        <input type="submit" value="Entrar" onclick="return validarLogin()"/>
                    </p>

                    <p class="link">
                        Não tem uma conta?
                        <a href="index.jsp">Cadastrar</a>
                    </p>
                </form>
            </div>
        </div>
        <script>
            function validarLogin() {
                if (document.formLogin.email.value === '') {
                    alert('O campo e-mail não foi informado!');
                    return false;
                }

                if (document.formLogin.senha.value === '') {
                    alert('O campo senha não foi informado!');
                    return false;
                }

                document.formLogin.submit();
            }
        </script>
    </body>
</html>
