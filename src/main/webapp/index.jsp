
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, org.example.models.*" %>


<section class="bg-dark text-white text-center p-5">
    <h1 class="display-4 fw-bold">Crowpage</h1>
    <p class="lead">Aplicación web CRUD desarrollada en Java 17 con Jakarta EE y JSP</p>
    <a href="https://github.com/TU-USUARIO/TU-REPO" target="_blank" class="btn btn-primary btn-lg mt-3">
        Ver repositorio en GitHub
    </a>
</section>

<!-- Sobre el proyecto -->
<section class="container my-5">
    <div class="row">
            <h2>Sobre el proyecto</h2>
            <p>
                Crowpage es una aplicación web diseñada para la gestión de productos (carros).
                Permite realizar operaciones CRUD (crear, leer, actualizar y eliminar) de manera sencilla,
                incluyendo la carga de imágenes asociadas a cada producto.
            </p>
            <p>
                El proyecto fue desarrollado sin frameworks adicionales, demostrando el dominio de la programación
                web con Java 17, Jakarta EE y JSP.
            </p>

    </div>
</section>

<!-- Seguridad y control de acceso -->
<section class="bg-light py-5">
    <div class="container">
        <h2>Seguridad y gestión de usuarios</h2>
        <p>
            La aplicación implementa autenticación y control de acceso mediante sesiones.
            Cada usuario debe iniciar sesión para acceder al sistema. Se asigna un rol a cada usuario:
        </p>
        <ul>
            <li><strong>ADMIN:</strong> Tiene permisos completos para agregar, editar y eliminar productos,
            así como gestionar usuarios dentro del sistema.</li>
            <li><strong>USUARIO:</strong> Puede visualizar los productos disponibles, pero no modificar ni eliminar datos.</li>
        </ul>
        <p>
            Este control de roles garantiza que solo usuarios autorizados puedan realizar acciones críticas,
            manteniendo la integridad y seguridad de la información.
        </p>
        <p>
            Además, la aplicación incluye un CRUD de usuarios, permitiendo a los administradores gestionar cuentas de manera eficiente.
        </p>
    </div>
</section>

<!-- Tecnologías -->
<section class="container my-5 text-center">
    <h2>Tecnologías utilizadas</h2>
    <div class="row mt-4">
        <div class="col-md-3"><span class="badge bg-primary p-3">Java 17</span></div>
        <div class="col-md-3"><span class="badge bg-secondary p-3">Jakarta EE (Servlets/JSP)</span></div>
        <div class="col-md-3"><span class="badge bg-success p-3">Tomcat 11</span></div>
        <div class="col-md-3"><span class="badge bg-danger p-3">MySQL</span></div>
    </div>
</section>