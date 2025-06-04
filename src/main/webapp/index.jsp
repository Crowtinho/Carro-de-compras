<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, org.example.models.*"%>

<%
String username = (String) session.getAttribute("username");
Usuario usuario = (Usuario) session.getAttribute("usuario");

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Crowpage</title>
</head>
<body>
<a href="/crudcarro/productos">ver productos</a>
<br>

<% if (username != null) { %>
    <a href="/crudcarro/carro/ver">carro</a>
    <br>
    <a href="/crudcarro/logout">logout</a>
<% } else { %>
    <a href="/crudcarro/login">login</a>
<% } %>
<% if (username != null && usuario != null && "admin".equals(usuario.getRol())) { %>
     <br>
     <h1>Hola admin</h1>
     <a href="/crudcarro/usuarios">ver usuarios</a>
     <br>
<% } %>



</body>
</html>