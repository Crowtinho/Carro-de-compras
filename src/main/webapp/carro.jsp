<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, org.example.models.*" %>
<%
    Carro carro = (Carro) session.getAttribute("carro");
%>

<h1 class="text-center mb-4">Carro de Compras</h1>

<% if (carro == null || carro.getItems().isEmpty()) { %>
    <div class="alert alert-warning text-center">
        <strong>Lo sentimos:</strong> No hay productos en el carro de compras.
    </div>
<% } else { %>

    <form name="formcarro" action="<%=request.getContextPath()%>/carro/actualizar" method="post">
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover align-middle text-center">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Total</th>
                    <th>Eliminar</th>
                </tr>
                </thead>
                <tbody>
                <% for (ItemCarro item : carro.getItems()) { %>
                    <tr>
                        <td><%= item.getProducto().getId() %></td>
                        <td><%= item.getProducto().getNombre() %></td>
                        <td>$<%= item.getProducto().getPrecio() %></td>
                        <td>
                            <input type="number"
                                   class="form-control form-control-sm text-center d-block mx-auto"
                                   name="cant_<%= item.getProducto().getId() %>"
                                   value="<%= item.getCantidad() %>"
                                   min="1" step="1" required
                                   style="width: 70px;" />
                        </td>
                        <td>$<%= item.getImporte() %></td>
                        <td>
                            <input type="checkbox" class="form-check-input"
                                   name="deleteProductos" value="<%= item.getProducto().getId() %>" />
                        </td>
                    </tr>
                <% } %>
                <tr class="table-secondary">
                    <td colspan="4" class="text-end fw-bold">Total:</td>
                    <td colspan="2" class="fw-bold">$<%= carro.getTotal() %></td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="text-center my-3">
            <button type="submit" class="btn btn-warning btn-sm">Actualizar Carro</button>
        </div>
    </form>

<% } %>

<div class="text-center mt-4">
    <a class="btn btn-dark btn-sm me-2" href="<%= request.getContextPath() %>/productos">Seguir comprando</a>
</div>
