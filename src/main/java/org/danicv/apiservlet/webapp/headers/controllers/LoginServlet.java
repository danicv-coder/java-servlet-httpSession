package org.danicv.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.danicv.apiservlet.webapp.headers.service.LoginService;
import org.danicv.apiservlet.webapp.headers.service.LoginServiceImpl;
import org.danicv.apiservlet.webapp.headers.service.LoginServiceSessionImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "123";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);
        if (usernameOptional.isPresent()) {
            resp.setContentType("text/html");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!Doctype html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Login</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Bienvenido " + usernameOptional.get() + " has iniciado sesión con éxito!</h1>");
                out.println("         <p><a href=java-servlet-httpSession/index.html'>Volver</a></p>");
                out.println("         <p><a href=java-servlet-httpSession/logout'>Cerrar sesión</a></p>");
                out.println("    </body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp); // esto es  para mostrar la interfaz de login.jsp
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect("/java-servlet-httpSession/login.html");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no tiene autorización para ingresar en la pagina!");
        }
    }
}
