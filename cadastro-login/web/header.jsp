<%-- 
    Document   : header
    Created on : 19 de set. de 2025, 21:44:49
    Author     : gabi
--%>

<head>
    <meta charset="UTF-8">
    <title><%= request.getAttribute("pageTitle") != null ? request.getAttribute("pageTitle") : "Título padrão"%></title>
    <link rel="stylesheet" type="text/css" href="estilo.css" />

    <%
        Boolean carregarBootstrap = (Boolean) request.getAttribute("carregarBootstrap");
        if (carregarBootstrap != null && carregarBootstrap) {
    %>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <%
        }
    %>
</head>