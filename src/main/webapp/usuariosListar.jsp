<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, org.example.models.*" %>

<%
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    Usuario usuario = (Usuario) session.getAttribute("usuario"); // usuario logueado
    boolean esAdmin = (usuario != null && "admin".equals(usuario.getRol()));
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuarios</title>
    <style>
        table {
            border-collapse: collapse;
            width: 90%;
            margin: auto;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #eee;
        }
        h1 {
            text-align: center;
        }
        .actions a {
            margin-right: 8px;
        }
        .bottom-link {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h1>Lista de Usuarios</h1>

    <table>
        <thead>
            <tr>
                <th>Username</th>
                <th>Email</th>
                <% if (esAdmin) { %>
                    <th>Editar</th>
                    <th>Eliminar</th>
                <% } %>
            </tr>
        </thead>
        <tbody>
            <% for (Usuario u : usuarios) { %>
                <tr>
                    <td><%= u.getUsername() %></td>
                    <td><%= u.getEmail() %></td>
                    <% if (esAdmin) { %>
                        <td><a href="<%= request.getContextPath() %>/usuarios/form?id=<%= u.getId() %>">Editar</a></td>
                        <td>
                            <a onclick="return confirm('¿Está seguro que desea eliminar este usuario?');"
                               href="<%= request.getContextPath() %>/usuarios/eliminar?id=<%= u.getId() %>">
                               Eliminar
                            </a>
                        </td>
                    <% } %>
                </tr>
            <% } %>
        </tbody>
    </table>

    <div class="bottom-link">
        <a href="<%= request.getContextPath() %>/home">Menú</a>
    </div>
</body>
</html>
