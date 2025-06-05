<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, org.example.models.*" %>
<%
    String username = (String) session.getAttribute("username");
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    boolean esAdmin = (usuario != null && "admin".equals(usuario.getRol()));
    String contenido = (String) request.getAttribute("contenido");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title><%= request.getAttribute("titulo") != null ? request.getAttribute("titulo") : "Crowpage" %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%= request.getContextPath() %>/home">Crowpage</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent"
                aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="<%= request.getContextPath() %>/productos">Productos</a>
                </li>
                <% if (username != null) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/carro/ver">Carro</a>
                    </li>
                <% } %>
                <% if (esAdmin) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/usuarios">Usuarios</a>
                    </li>
                <% } %>
            </ul>

            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="cuentaDropdown" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        Cuenta
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="cuentaDropdown">
                        <% if (username == null) { %>
                            <li><a class="dropdown-item" href="<%= request.getContextPath() %>/login">Iniciar Sesión</a></li>
                        <% } else { %>
                            <li><span class="dropdown-item-text">Hola, <strong><%= username %></strong></span></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="<%= request.getContextPath() %>/logout">Cerrar Sesión</a></li>
                        <% } %>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Contenido dinámico -->
<div class="container mt-5">
    <% if (contenido != null) { %>
        <jsp:include page="<%= contenido %>" />
    <% } else { %>
        <div class="alert alert-warning">No se pudo cargar el contenido solicitado.</div>
    <% } %>
</div>

<!-- Footer -->
<jsp:include page="/layout/footer.jsp" />

</body>
</html>
