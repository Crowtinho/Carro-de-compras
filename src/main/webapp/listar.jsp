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

    <div class="row g-4">
        <% for (Producto p : productos) {
            String precioFormateado = p.getPrecio() != null ? formatoCOP.format(p.getPrecio()) : "$0";
        %>
            <div class="col-12 col-sm-6 col-md-4 col-lg-3">
                <div class="card h-100 shadow-sm">
                    <div class="card-img-container position-relative">
                        <img src="<%= request.getContextPath() %>/uploads/<%= p.getImagen() %>"
                             alt="<%= p.getNombre() %>"
                             style="width:200px;height:200px;object-fit:cover;">

                        <!-- Etiquetas -->
                        <span class="badge bg-success position-absolute top-0 start-0 m-2">NUEVO</span>
                        <span class="badge bg-light text-dark position-absolute top-0 end-0 m-2">
                            <%= p.getCategoria().getNombre() %>
                        </span>
                    </div>

                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title"><%= p.getNombre() %></h5>
                        <p class="precio text-success fw-bold mb-2"><%= precioFormateado %></p>

                        <% if (username.isPresent()) { %>
                            <a class="btn btn-sm btn-success mb-2"
                               href="<%= request.getContextPath() %>/carro/agregar?id=<%= p.getId() %>">
                               <i class="bi bi-cart me-2"></i> Agregar al carrito
                            </a>
                        <% } %>

                        <% if (esAdmin) { %>
                            <div class="mt-auto d-flex gap-2">
                                <a class="btn btn-sm btn-warning w-50"
                                   href="<%= request.getContextPath() %>/productos/form?id=<%= p.getId() %>">Editar</a>
                                <a class="btn btn-sm btn-danger w-50"
                                   onclick="return confirm('¿Está seguro que desea eliminar este producto?');"
                                   href="<%= request.getContextPath() %>/productos/eliminar?id=<%= p.getId() %>">Eliminar</a>
                            </div>
                        <% } %>
                    </div>
                </div>
            </div>
        <% } %>
    </div>


