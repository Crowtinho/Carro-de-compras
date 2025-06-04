<%@ page contentType="text/html; charset=UTF-8"
    import="java.util.*, java.text.NumberFormat, java.util.Locale, org.example.models.*"%>

<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    NumberFormat formatoCOP = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Productos</title>
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
        .top-actions {
            text-align: center;
            margin-bottom: 20px;
        }
        .bottom-link {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h1>Productos</h1>

    <% if (username.isPresent()) { %>
        <div class="top-actions">
            <a href="<%=request.getContextPath()%>/productos/form">Agregar producto</a>
        </div>
    <% } %>

    <table>
        <thead>
            <tr>
                <th>Producto</th>
                <th>Categoría</th>
                <% if (username.isPresent()) { %>
                    <th>Precio</th>
                    <th>Carro</th>
                <% } %>
                <% if (username != null && usuario != null && "admin".equals(usuario.getRol())) { %>
                    <th>Editar</th>
                    <th>Eliminar</th>
                <% } %>
            </tr>
        </thead>
        <tbody>
            <% for (Producto p : productos) {
                String precioFormateado = (p.getPrecio() != null) ? formatoCOP.format(p.getPrecio()) : "$0";
            %>
                <tr>
                    <td><%= p.getNombre() %></td>
                    <td><%= p.getCategoria().getNombre() %></td>
                    <% if (username.isPresent()) { %>
                        <td><%= precioFormateado %></td>
                        <td><a href="<%= request.getContextPath() %>/carro/agregar?id=<%= p.getId() %>">Agregar al carro</a></td>
                    <% } %>
                    <% if (username != null && usuario != null && "admin".equals(usuario.getRol())) { %>
                        <td><a href="<%= request.getContextPath() %>/productos/form?id=<%= p.getId() %>">Editar</a></td>
                        <td><a onclick="return confirm('¿Está seguro que desea eliminar este producto?');"
                               href="<%= request.getContextPath() %>/productos/eliminar?id=<%= p.getId() %>">Eliminar</a></td>
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
