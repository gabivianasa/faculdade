<%-- 
    Document   : Index
    Created on : 18 de set. de 2025, 19:59:31
    Author     : gabi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <%
        request.setAttribute("pageTitle", "Página Inicial");
    %>
    <%@ include file="header.jsp" %> 

    <body>
        <div class="content">
            <div id="cadastro">
                <form name="formCadastro" method="post" action="CadastroLogin">
                    <h1>Cadastro</h1>

                    <p>
                        <label for="nome">Seu nome</label>
                        <input id="nome" name="nome" required type="text" placeholder="nome" />
                    </p>

                    <p>
                        <label for="email">Seu e-mail</label>
                        <input id="email" name="email" required type="email" placeholder="contato@projecaoeilandia.com" />
                    </p>

                    <p>
                        <label for="senha">Sua senha</label>
                        <input id="senha" name="senha" required type="password" placeholder="ex. 1234" maxlength="8" />
                    </p>

                    <p>
                        <input type="submit" value="Cadastrar" onclick="return validarCadastro()" />
                    </p>

                    <p class="link">
                        Já tem uma conta?
                        <a href="login.jsp"> Ir para login</a>
                        
                    </p>
                </form>
            </div>
            
        </div>

        <script>
            function validarCadastro() {
                const nome = document.formCadastro.nome.value;
                const email = document.formCadastro.email.value;
                const senha = document.formCadastro.senha.value;

                if (nome === "") {
                    alert('Preencha o campo com seu nome');
                    document.formCadastro.nome.focus();
                    return false;
                } 
                if (email === "") {
                    alert('Preencha o campo com seu e-mail');
                    document.formCadastro.email.focus();
                    return false;
                } 
                if (senha === "" || senha.length < 6 || senha.length > 8) {
                    alert('Preencha o campo com sua senha (mínimo 6 e máximo 8 caracteres)');
                    document.formCadastro.senha.focus();
                    return false;
                }
                return true;
            }
        </script>

    </body>
</html>