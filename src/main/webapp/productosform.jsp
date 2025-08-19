<%@page contentType="text/html" pageEncoding="UTF-8"
import="java.util.*,java.time.format.*,java.math.BigDecimal, org.example.models.*"%>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
    Producto producto = (Producto) request.getAttribute("producto");
    String fecha = producto.getFechaRegistro() != null ?
                   producto.getFechaRegistro().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Formulario Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">
        <%= (producto.getId() != null && producto.getId() > 0) ? "Editar Producto" : "Crear Producto" %>
    </h2>

    <!-- 🔹 Importante: enctype para soportar archivos -->
    <form action="<%=request.getContextPath()%>/productos/form" method="post"
          class="mx-auto" style="max-width: 600px;" enctype="multipart/form-data">
        <input type="hidden" name="id" value="<%= producto.getId() != null ? producto.getId() : "" %>">

        <!-- Nombre -->
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text"
                   class="form-control <%= (errores != null && errores.containsKey("nombre")) ? "is-invalid" : "" %>"
                   id="nombre" name="nombre"
                   value="<%= producto.getNombre() != null ? producto.getNombre() : "" %>">
            <div class="invalid-feedback">
                <%= (errores != null && errores.containsKey("nombre")) ? errores.get("nombre") : "" %>
            </div>
        </div>

        <!-- Precio -->
        <div class="mb-3">
            <label for="precio" class="form-label">Precio</label>
            <input type="number"
                   class="form-control <%= (errores != null && errores.containsKey("precio")) ? "is-invalid" : "" %>"
                   id="precio" name="precio"
                   value="<%= (producto.getPrecio() != null && producto.getPrecio().compareTo(BigDecimal.ZERO) != 0) ? producto.getPrecio() : "" %>">
            <div class="invalid-feedback">
                <%= (errores != null && errores.containsKey("precio")) ? errores.get("precio") : "" %>
            </div>
        </div>

        <!-- Fecha -->
        <div class="mb-3">
            <label for="fecha_registro" class="form-label">Fecha de Registro</label>
            <input type="date"
                   class="form-control <%= (errores != null && errores.containsKey("fecha_registro")) ? "is-invalid" : "" %>"
                   id="fecha_registro" name="fecha_registro" value="<%= fecha %>">
            <div class="invalid-feedback">
                <%= (errores != null && errores.containsKey("fecha_registro")) ? errores.get("fecha_registro") : "" %>
            </div>
        </div>

        <!-- Categoría -->
        <div class="mb-3">
            <label for="categoria" class="form-label">Categoría</label>
            <select class="form-select <%= (errores != null && errores.containsKey("categoria")) ? "is-invalid" : "" %>"
                    name="categoria" id="categoria">
                <option value="">--- seleccionar ---</option>
                <% for (Categoria c : categorias) { %>
                    <option value="<%= c.getId() %>"
                            <%= (producto.getCategoria() != null && c.getId().equals(producto.getCategoria().getId())) ? "selected" : "" %>>
                        <%= c.getNombre() %>
                    </option>
                <% } %>
            </select>
            <div class="invalid-feedback">
                <%= (errores != null && errores.containsKey("categoria")) ? errores.get("categoria") : "" %>
            </div>
        </div>

        <!-- 🔹 Imagen -->
        <div class="mb-3">
            <label for="imagen" class="form-label">Imagen</label>
            <input type="file"
                   class="form-control <%= (errores != null && errores.containsKey("imagen")) ? "is-invalid" : "" %>"
                   id="imagen" name="imagen">
            <div class="invalid-feedback">
                <%= (errores != null && errores.containsKey("imagen")) ? errores.get("imagen") : "" %>
            </div>

            <!-- Mostrar vista previa si el producto ya tiene una imagen -->
            <% if (producto.getImagen() != null && !producto.getImagen().isEmpty()) { %>
                <div class="mt-3 text-center">
                    <img src="<%= request.getContextPath() + "/" + producto.getImagen() %>"
                         alt="Imagen del producto" class="img-thumbnail" style="max-height: 150px;">
                </div>
            <% } %>
        </div>

        <!-- Botón -->
        <div class="text-center">
            <button type="submit" class="btn btn-primary">
                <%= (producto.getId() != null && producto.getId() > 0) ? "Actualizar" : "Crear" %>
            </button>
            <a href="<%= request.getContextPath() %>/productos" class="btn btn-secondary ms-2">Cancelar</a>
        </div>
    </form>
</div>
</body>
</html>
