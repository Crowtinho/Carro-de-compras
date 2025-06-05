<%@ page contentType="text/html; charset=UTF-8" import="java.util.List, org.example.models.Usuario" %>

<%
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    boolean esAdmin = (usuario != null && "admin".equals(usuario.getRol()));
%>

<h1 class="text-center mb-4">Listado de Usuarios</h1>

<% if (usuarios != null && !usuarios.isEmpty()) { %>
    <table class="table table-bordered table-hover table-striped">
        <thead class="table-dark text-center">
            <tr>
                <th>ID</th>
                <th>Usuario</th>
                <th>Email</th>
                <% if (esAdmin) { %>
                    <th>Editar</th>
                    <th>Eliminar</th>
                <% } %>
            </tr>
        </thead>
        <tbody class="align-middle">
            <% for (Usuario u : usuarios) { %>
                <tr>
                    <td class="text-center"><%= u.getId() %></td>
                    <td class="text-start"><%= u.getUsername() %></td>
                    <td class="text-start"><%= u.getEmail() %></td>
                    <% if (esAdmin) { %>
                        <td>
                            <a class="btn btn-sm btn-warning" href="<%= request.getContextPath() %>/usuarios/form?id=<%= u.getId() %>">Editar</a>
                        </td>
                        <td>
                            <a class="btn btn-sm btn-danger"
                               onclick="return confirm('¿Está seguro que desea eliminar este usuario?');"
                               href="<%= request.getContextPath() %>/usuarios/eliminar?id=<%= u.getId() %>">
                               Eliminar
                            </a>
                        </td>
                    <% } %>
                </tr>
            <% } %>
        </tbody>
    </table>
<% } else { %>
    <p class="text-center text-danger">No hay usuarios registrados.</p>
<% } %>

