<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map,org.example.models.*"%>
<%String error = (String) request.getAttribute("error");%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
</head>
<body>
    <h1>Login</h1>

    <form action="/crudcarro/login" method="post">
        <div>
            <label for="username">Usuario:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <br>
        <div>
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <br>
        <% if(error != null ){%>
            <div style="color:red;"><%=error%></div>
            <br>
        <% } %>

        <div>
            <input type="submit" value="Login">
        </div>
        <br>
        <div>
            <a href="<%=request.getContextPath()%>/home">menú</a>
        </div>
        <br>
        <div>
            <a href="<%=request.getContextPath()%>/usuarios/form">Registrarse</a>
        </div>
    </form>

</body>
</html>
