package org.example.controllers.producto;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/uploads/*")
public class ImagenServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "C:/Users/andres/Pictures/crudcarro";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename = request.getPathInfo().substring(1); // lo que viene después de /uploads/
        File file = new File(UPLOAD_DIR, filename);

        if (file.exists()) {
            response.setContentType(getServletContext().getMimeType(file.getName()));
            Files.copy(file.toPath(), response.getOutputStream());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
