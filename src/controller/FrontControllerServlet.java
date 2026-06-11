package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class FrontControllerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();

        // get URI
        String uri = req.getRequestURI();

        // print uri
        out.println(uri);
    }
}