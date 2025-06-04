package org.example.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.example.services.ServiceExeption;
import org.example.util.ConnectionDataBase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConnectionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try (Connection conn = ConnectionDataBase.getConnection();){
            if (conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try{
                servletRequest.setAttribute("conn",conn);
                filterChain.doFilter(servletRequest,servletResponse);
                conn.commit();
            } catch (IOException | ServletException | SQLException | ServiceExeption e) {
                conn.rollback();
                ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
