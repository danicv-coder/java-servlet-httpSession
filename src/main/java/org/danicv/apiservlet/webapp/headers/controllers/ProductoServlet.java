package org.danicv.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.danicv.apiservlet.webapp.headers.models.Producto;
import org.danicv.apiservlet.webapp.headers.service.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/producto.html"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);
        resp.setContentType("text/html");
        ProductoService service = new ProductoServicesImpl();
        List<Producto> productos = service.listar();
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!Doctype html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Listado de productos</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Listado de productos</h1>");
            if (usernameOptional.isPresent()) {
                out.println("           <h5 style='color: blue;'>Usuario: " + usernameOptional.get() + "</h5>");
            }
            out.println("        <table>");
            out.println("           <tr>");
            out.println("           <th> id</th>");
            out.println("           <th> Nombre</th>");
            out.println("           <th> Tipo</th>");
            if (usernameOptional.isPresent()) {
                out.println("           <th> Precio</th>");
            }
            out.println("           </tr>");
            productos.forEach(p -> {
                out.println("           <tr>");
                out.println("           <td>" + p.getId() + "<td>");
                out.println("           <td>" + p.getNombre() + "<td>");
                out.println("           <td>" + p.getTip() + "<td>");
                if (usernameOptional.isPresent()) {
                    out.println("       <td>" + p.getPrecio() + "</td>");
                }
                out.println("           </tr>");
            });
            out.println("         <p><a href='/java-servlet-manejo-cookie/index.html'>Volver</a></p>");
        }
    }
}
