package org.danicv.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.danicv.apiservlet.webapp.headers.service.LoginService;
import org.danicv.apiservlet.webapp.headers.service.LoginServiceImpl;
import org.danicv.apiservlet.webapp.headers.service.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> username = auth.getUsername(req);
        if (username.isPresent()) {
            HttpSession session = req.getSession();
            session.invalidate();

        }
        resp.sendRedirect("/java-servlet-httpSession/login.html");
    }
}
