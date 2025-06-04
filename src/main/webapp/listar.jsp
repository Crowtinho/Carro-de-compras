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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</head>
<body>
<div class="container">

    <h1 class="text-center">Productos</h1>

    <% if (username.isPresent()) { %>
        <div class="top-actions">

            <a class="btn btn-sm btn-primary" href="<%=request.getContextPath()%>/productos/form">Agregar producto</a>
        </div>
        <br>
    <% } %>

    <table class="table table-hover table-striped">
        <thead class="table-primary">
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
                        <td><a class="btn btn-sm btn-success" href="<%= request.getContextPath() %>/carro/agregar?id=<%= p.getId() %>">Agregar</a></td>
                    <% } %>
                    <% if (username != null && usuario != null && "admin".equals(usuario.getRol())) { %>
                        <td><a class="btn btn-sm btn-warning" href="<%= request.getContextPath() %>/productos/form?id=<%= p.getId() %>">Editar</a></td>
                        <td><a class="btn btn-sm btn-danger" onclick="return confirm('¿Está seguro que desea eliminar este producto?');"
                               href="<%= request.getContextPath() %>/productos/eliminar?id=<%= p.getId() %>">Eliminar</a></td>
                    <% } %>
                </tr>
            <% } %>
        </tbody>
    </table>

    <div class="bottom-link">
        <a class="btn btn-sm btn-primary" href="<%= request.getContextPath() %>/home">Menú</a>
    </div>
</div>
</body>
</html>
