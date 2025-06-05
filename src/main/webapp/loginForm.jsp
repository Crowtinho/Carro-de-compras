<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map, org.example.models.*"%>
<%
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
    Usuario usuario = (Usuario) request.getAttribute("usuario");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro de Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4"><%= (usuario != null && usuario.getId() != null && usuario.getId() > 0) ? "Editar Usuario" : "Registro de Usuario" %></h2>

    <form class="mx-auto" style="max-width: 500px;" action="<%= request.getContextPath() %>/usuarios/form" method="post">
        <input type="hidden" name="id" value="<%= (usuario != null) ? usuario.getId() : "" %>">

        <!-- Username -->
        <div class="mb-3">
            <label for="username" class="form-label">Nombre de usuario</label>
            <input type="text" class="form-control <%= (errores != null && errores.containsKey("username")) ? "is-invalid" : "" %>"
                   id="username" name="username"
                   value="<%= (usuario != null && usuario.getUsername() != null) ? usuario.getUsername() : "" %>">
            <div class="invalid-feedback">
                <%= (errores != null && errores.containsKey("username")) ? errores.get("username") : "" %>
            </div>
        </div>

        <!-- Contraseña -->
        <div class="mb-3">
            <label for="password" class="form-label">Contraseña</label>
            <input type="password" class="form-control <%= (errores != null && errores.containsKey("password")) ? "is-invalid" : "" %>"
                   id="password" name="password"
                   value="<%= (usuario != null && usuario.getPassword() != null) ? usuario.getPassword() : "" %>">
            <div class="invalid-feedback">
                <%= (errores != null && errores.containsKey("password")) ? errores.get("password") : "" %>
            </div>
        </div>

        <!-- Email -->
        <div class="mb-3">
            <label for="email" class="form-label">Correo electrónico</label>
            <input type="email" class="form-control <%= (errores != null && errores.containsKey("email")) ? "is-invalid" : "" %>"
                   id="email" name="email"
                   value="<%= (usuario != null && usuario.getEmail() != null) ? usuario.getEmail() : "" %>">
            <div class="invalid-feedback">
                <%= (errores != null && errores.containsKey("email")) ? errores.get("email") : "" %>
            </div>
        </div>

        <!-- Botón -->
        <div class="text-center">
            <button type="submit" class="btn btn-dark">
                <%= (usuario != null && usuario.getId() != null && usuario.getId() > 0) ? "Actualizar" : "Registrar" %>
            </button>
            <a href="<%= request.getContextPath() %>/home" class="btn btn-secondary ms-2">Cancelar</a>
        </div>
    </form>
</div>
</body>
</html>
