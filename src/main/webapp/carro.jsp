<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, org.example.models.*" %>

<%
    Carro carro = (Carro) session.getAttribute("carro");
%>

<!DOCTYPE html>
<html lang="es">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<head>
    <meta charset="UTF-8">
    <title>Carro de Compras</title>
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
        .bottom-links {
            text-align: center;
            margin-top: 20px;
        }
        .bottom-links a {
            margin: 0 10px;
            text-decoration: none;
            color: #007BFF;
        }
        .bottom-links a:hover {
            text-decoration: underline;
        }
        input[type="number"] {
            width: 60px;
            padding: 4px;
        }
    </style>
</head>
<body>
    <h1>Carro de Compras</h1>

    <% if (carro == null || carro.getItems().isEmpty()) { %>
        <p style="text-align: center; color: red;">Lo sentimos, no hay productos en el carro de compras.</p>
    <% } else { %>
        <form name="formcarro" action="<%=request.getContextPath()%>/carro/actualizar" method="post">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Total</th>
                        <th>Borrar</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (ItemCarro item : carro.getItems()) { %>
                        <tr>
                            <td><%= item.getProducto().getId() %></td>
                            <td><%= item.getProducto().getNombre() %></td>
                            <td>$<%= item.getProducto().getPrecio() %></td>
                            <td>
                                <input type="number" name="cant_<%= item.getProducto().getId() %>"
                                       value="<%= item.getCantidad() %>" min="1" step="1" />
                            </td>
                            <td>$<%= item.getImporte() %></td>
                            <td>
                                <input type="checkbox" value="<%= item.getProducto().getId() %>" name="deleteProductos" />
                            </td>
                        </tr>
                    <% } %>
                    <tr>
                        <td colspan="4" style="text-align: right; font-weight: bold;">Total:</td>
                        <td colspan="2">$<%= carro.getTotal() %></td>
                    </tr>
                </tbody>
            </table>
            <div class="bottom-links" style="margin-top: 15px;">
                <a href="javascript:document.formcarro.submit();">Actualizar</a>
            </div>
        </form>
    <% } %>

    <div class="bottom-links">
        <a href="<%=request.getContextPath()%>/productos">Seguir comprando</a>
        <a href="<%=request.getContextPath()%>/home">Menú principal</a>
    </div>
</body>
</html>
