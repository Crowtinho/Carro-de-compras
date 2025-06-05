<%@ page contentType="text/html; charset=UTF-8"
         import="java.util.*, java.text.NumberFormat, java.util.Locale, org.example.models.*" %>

<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    boolean esAdmin = usuario != null && "admin".equals(usuario.getRol());

    NumberFormat formatoCOP = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
%>

<h1 class="text-center mb-4">Productos</h1>

<% if (username.isPresent() && esAdmin) { %>
    <div class="mb-3 text-end">
        <a class="btn btn-sm btn-dark" href="<%= request.getContextPath() %>/productos/form">Agregar producto</a>
    </div>
<% } %>

<div class="table-responsive">
    <table class="table table-hover table-striped align-middle text-center">
        <thead class="table-dark">
        <tr>
            <th>Producto</th>
            <th>Categoría</th>
            <th>Precio</th>
            <% if (username.isPresent()) { %>
                <th>Carro</th>
            <% } %>
            <% if (esAdmin) { %>
                <th>Editar</th>
                <th>Eliminar</th>
            <% } %>
        </tr>
        </thead>
        <tbody>
        <% for (Producto p : productos) {
            String precioFormateado = p.getPrecio() != null ? formatoCOP.format(p.getPrecio()) : "$0";
        %>
            <tr>
                <td><%= p.getNombre() %></td>
                <td><%= p.getCategoria().getNombre() %></td>
                <td><%= precioFormateado %></td>

                <% if (username.isPresent()) { %>
                    <td>
                        <a class="btn btn-sm btn-success"
                           href="<%= request.getContextPath() %>/carro/agregar?id=<%= p.getId() %>">Agregar</a>
                    </td>
                <% } %>

                <% if (esAdmin) { %>
                    <td>
                        <a class="btn btn-sm btn-warning"
                           href="<%= request.getContextPath() %>/productos/form?id=<%= p.getId() %>">Editar</a>
                    </td>
                    <td>
                        <a class="btn btn-sm btn-danger"
                           onclick="return confirm('¿Está seguro que desea eliminar este producto?');"
                           href="<%= request.getContextPath() %>/productos/eliminar?id=<%= p.getId() %>">Eliminar</a>
                    </td>
                <% } %>
            </tr>
        <% } %>
        </tbody>
    </table>
</div>

