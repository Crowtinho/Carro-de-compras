<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.example.models.*"%>
<% String error = (String) request.getAttribute("error"); %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">
<div class="container d-flex align-items-center justify-content-center" style="min-height: 100vh;">
    <div class="card shadow-sm p-4" style="max-width: 400px; width: 100%;">
        <h3 class="text-center mb-4">Iniciar Sesión</h3>

        <% if (error != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= error %>
            </div>
        <% } %>

        <form action="<%=request.getContextPath()%>/login" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Usuario</label>
                <input type="text" class="form-control" id="username" name="username" required autofocus>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Contraseña</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <div class="d-grid gap-2 mb-3">
                <button type="submit" class="btn btn-dark">Ingresar</button>
                <a href="<%=request.getContextPath()%>/usuarios/form" class="btn btn-outline-secondary">Registrarse</a>
            </div>

            <div class="text-center">
                <a href="<%=request.getContextPath()%>/home" class="btn btn-link">Volver al menú principal</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
