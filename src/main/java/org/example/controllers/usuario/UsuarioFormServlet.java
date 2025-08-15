package org.example.controllers.usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.models.Usuario;
import org.example.services.usuario.UsuarioService;
import org.example.services.usuario.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/usuarios/form")
public class UsuarioFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService usuarioService = new UsuarioServiceImpl(conn);

        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e ){
            id = 0L;
        }
        Usuario usuario = new Usuario();
        if (id>0){
            Optional<Usuario> o = usuarioService.porId(id);
            if (o.isPresent()){
                usuario = o.get();
            }
        }
        req.setAttribute("usuario",usuario);
        getServletContext().getRequestDispatcher("/loginForm.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
//        String rol =req.getParameter("rol");


        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Map<String, String> errores = new HashMap<>();
        if (username == null || username.isBlank()){
            errores.put("username","el usuario es requerido");
        }

        Optional<Usuario> existenteUsername = service.username(username);
        if (existenteUsername.isPresent() && existenteUsername.get().getId() != id) {
            errores.put("username", "el usuario ya existe");
        }

   /*     if (service.username(username).isPresent()){
            errores.put("username","el usuario ya existe");
        }*/
        if (email == null || !email.contains("@") || !email.contains(".")){
            errores.put("email","ingrese un formato de email valido");
        }

      /*  if (service.email(email).isPresent()){
            errores.put("email","el email ya está registrado");
        }*/


        if (password ==null || password.isBlank()){
            errores.put("password","ingrese una contraseña");
        }

        Optional<Usuario> existenteEmail = service.email(email);
        if (existenteEmail.isPresent() && existenteEmail.get().getId() != id) {
            errores.put("email", "el email ya está registrado");
        }



        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEmail(email);
        usuario.setRol("user");



        if (errores.isEmpty()){
            service.guardar(usuario);
            resp.sendRedirect(req.getContextPath()+"/home");
        }else {
            req.setAttribute("errores",errores);
            req.setAttribute("usuario",usuario);
            getServletContext().getRequestDispatcher("/loginForm.jsp").forward(req,resp);
        }
    }
}
