package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class FrontControllerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/plain");
        
        // processRequest
        this.processRequest(req, res);
    }

    // function to get uri 
    public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();

        // get URI
        String uri = req.getRequestURI();

        // print uri
        out.println(uri);
    }
}