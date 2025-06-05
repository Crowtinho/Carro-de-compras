package org.example.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Usuario;

import java.io.IOException;

@WebFilter({"/usuarios","/usuarios/eliminar"})
public class UsuariosFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Obtener el usuario desde la sesión
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        // Verificar si es admin
        if (usuario != null && "admin".equals(usuario.getRol())) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acceso denegado: solo administradores");
        }
    }
}
