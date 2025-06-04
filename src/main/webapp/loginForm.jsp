<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map,org.example.models.*"%>
<%
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
    Usuario usuario = (Usuario) request.getAttribute("usuario");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro de Usuario</title>
</head>
<body>
    <h2>Formulario de Registro</h2>

    <form action="<%=request.getContextPath()%>/usuarios/form" method="post">
        <input type="hidden" name="id" value="<%= (usuario != null) ? usuario.getId() : "" %>">

        <!-- Campo: Username -->
        <label for="username">Nombre de usuario:</label><br>
        <input type="text" id="username" name="username"
               value="<%= (usuario != null && usuario.getUsername() != null) ? usuario.getUsername() : "" %>"><br>
        <span style="color:red;">
            <%= (errores != null && errores.containsKey("username")) ? errores.get("username") : "" %>
        </span><br><br>

        <!-- Campo: Contraseña -->
        <label for="password">Contraseña:</label><br>
        <input type="password" id="password" name="password"
                value="<%= (usuario != null && usuario.getPassword() != null) ? usuario.getPassword() : "" %>"><br>
        <span style="color:red;">
             <%= (errores != null && errores.containsKey("password")) ? errores.get("password") : "" %>
        </span><br><br>

        <!-- Campo: Email -->
        <label for="email">Correo electrónico:</label><br>
        <input type="email" id="email" name="email"
               value="<%= (usuario != null && usuario.getEmail() != null) ? usuario.getEmail() : "" %>"><br>
        <span style="color:red;">
            <%= (errores != null && errores.containsKey("email")) ? errores.get("email") : "" %>
        </span><br><br>


        <div><input type="submit" value="<%=(usuario.getId()!=null && usuario.getId()>0)? "Editar": "Crear"%>"></div>
        <input type="hidden" name="id" value="<%=usuario.getId()%>">
    </form>

</body>
</html>
