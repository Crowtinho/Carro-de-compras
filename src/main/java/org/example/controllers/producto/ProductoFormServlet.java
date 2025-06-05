package org.example.controllers.producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Categoria;
import org.example.models.Producto;
import org.example.services.categoria.CategoriaService;
import org.example.services.categoria.CategoriaServiceImpl;
import org.example.services.producto.ProductoServiceImpl;
import org.example.services.producto.ProductoSnService;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoSnService service = new ProductoServiceImpl(conn);
        CategoriaService categoriaService = new CategoriaServiceImpl(conn);
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e){
            id = 0L;
        }
        Producto producto = new Producto();
        producto.setCategoria(new Categoria());
        if (id > 0) {
            Optional<Producto> o = service.porId(id);
            if (o.isPresent()) {
                producto = o.get();
            }
        }
        req.setAttribute("categorias", categoriaService.listar());
        req.setAttribute("producto", producto);
        getServletContext().getRequestDispatcher("/productosform.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoSnService service = new ProductoServiceImpl(conn);
        CategoriaService categoriaService = new CategoriaServiceImpl(conn);

        String nombre = req.getParameter("nombre");
        String precioStr = req.getParameter("precio");
        String fechaStr = req.getParameter("fecha_registro");

        Long categoriaId;
        try {
            categoriaId = Long.parseLong(req.getParameter("categoria"));
        } catch (NumberFormatException e){
            categoriaId = 0L;
        }

        Map<String, String> errores = new HashMap<>();

        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre es requerido.");
        }

        BigDecimal precio = null;
        if (precioStr == null || precioStr.isBlank()) {
            errores.put("precio", "El precio es requerido.");
        } else {
            try {
                precio = new BigDecimal(precioStr);
            } catch (NumberFormatException | NullPointerException e) {
                errores.put("precio", "El precio debe ser un número válido.");
            }
        }

        LocalDate fecha = null;
        if (fechaStr == null || fechaStr.isBlank()) {
            errores.put("fecha_registro", "La fecha es requerida.");
        } else {
            try {
                fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                errores.put("fecha_registro", "La fecha tiene un formato inválido.");
            }
        }

        if (categoriaId.equals(0L)) {
            errores.put("categoria", "La categoría es requerida.");
        }

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setPrecio(precio); // puede ser null si hubo error
        producto.setFechaRegistro(fecha);
        producto.setCategoria(new Categoria());
        producto.getCategoria().setId(categoriaId);

        if (errores.isEmpty()) {
            service.guardar(producto);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("categorias", categoriaService.listar());
            req.setAttribute("producto", producto);
            getServletContext().getRequestDispatcher("/productosform.jsp").forward(req, resp);
        }
    }


}
